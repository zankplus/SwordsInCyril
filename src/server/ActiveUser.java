package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import fftadata.ActiveUnit;
import fftadata.FFTASkill;
import server.ActiveGame.GameStatus;
import zank.*;

public class ActiveUser extends Thread
{
	public Socket connection;
	public ObjectInputStream in;
	public ObjectOutputStream out;
	public InputStreamReader readyChecker;
	public String nickname = null;
	public LinkedBlockingQueue<ZankMessage> messageQueue;
	public ActiveGame game = null;
	
	
	public ActiveUser(Socket connection)
	{
		this.connection = connection;
		messageQueue = new LinkedBlockingQueue<ZankMessage>();
		try
		{
			connection.setSoTimeout(0);
			connection.setKeepAlive(true);
			in = new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));
			out = new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
			out.flush();
			readyChecker = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));
		}
		catch (SocketException e) { System.out.println("\r" + e); }
		catch (IOException e) { System.err.println("\r" + e); }
	}
	
	@SuppressWarnings({ "unchecked" })
	public void run()
	{
		try
		{
			boolean done = false;
			ZankMessage msg;
			
			while (!done)
			{
				try
				{
					// Check reader for new messages to read
					if (readyChecker.ready())
					{
						msg = null;

						try
						{
							msg = (ZankMessage) in.readObject();
							
							String username = msg.user;
							ZankMessageType command = msg.type;
								
							// Special handlers for different message types
							if (command.equals(ZankMessageType.LOGIN) && nickname == null)
							{								
								if (nickname == null)
								{
									nickname = username;
									synchronized(ChatServer.userlist)
									{
										// Send notification of login to all other users
										StringBuilder toNotify = new StringBuilder();
										for (int i = 0; i < ChatServer.userlist.size(); i++)
										{
											
											String currNick = ChatServer.userlist.get(i).nickname;
											if (currNick != null && !currNick.equals(nickname))
												toNotify.append(" " + currNick);
										}
										ZankMessage result = new ZankMessage(ZankMessageType.ONLINE, username, toNotify.toString());
										messageQueue.put(result);
									}
								}
								ChatServer.masterMessageQueue.put(msg);
							}
							
							else if (command.equals(ZankMessageType.LOGOUT))
							{
//								System.out.print("\r\n* " + username + " has left the room");
								done = true;
								ChatServer.masterMessageQueue.put(msg);
							}
							
							else if (command.equals(ZankMessageType.CHAT))
							{
//								 System.out.print("\r\n" + username + ": " + (String) msg.data);
								ChatServer.masterMessageQueue.put(msg);
							}
							
							else if (command.equals(ZankMessageType.CHALLENGE))
							{
								String challenged = (String) msg.data;
								ActiveUser challengedUser = null;
								for (ActiveUser user : ChatServer.userlist)
									if (user.nickname.equals(challenged))
									{
										challengedUser = user;
										break;
									}

								if (challengedUser == null)
								{
									// System.out.println("challengee not found");
									// If the challengee logs off before the server notifies them of the challenge,
									// notify the challenger by returning their challenge message.
									messageQueue.put(msg); 
								}
								else
								{
									// Otherwise, pass the challenge on to the challengee
									challengedUser.messageQueue.put(msg);
								}
							}
							
							else if (command.equals(ZankMessageType.ENGAGE))
							{
								ActiveUser player2 = ChatServer.findUser((String) msg.data);
								if (player2 != null)
								{
									ActiveUser player1 = this;
									
									System.out.println("player 1 = " + player1.nickname);
									System.out.println("player 2 = " + player2.nickname);
									
									game = new ActiveGame(player1, player2);
									ChatServer.gamelist.add(game);
									ZankMessage startMessage = game.getStartMessage();
									player1.messageQueue.put(startMessage);
									player2.messageQueue.put(startMessage);										
									ChatServer.masterMessageQueue.put(msg);
								}
								else
									System.out.println("\r\nCouldn't find user " + msg.data);
							}
							
							else if (command.equals(ZankMessageType.GAME))
							{
								ZankGameAction action = (ZankGameAction) msg.data;
								ActiveGame ag = ChatServer.findGame(action.gameID);
								
								if (ag != null)
								{
									ag.gameLock.lock();
									try
									{
										if (action.type == ZankGameActionType.CHAT || action.type == ZankGameActionType.START)
										{
											ag.player1.messageQueue.put(msg);
											ag.player2.messageQueue.put(msg);
										}
										
										else if (action.type == ZankGameActionType.READY && ag.status == GameStatus.SETUP)
										{
											// If player signals that they're ready, mark their finishedPrep flag and extract
											// their clan from the message.
											if (msg.user.equals(ag.player1.nickname))
											{
												
												ag.finishedPrep1 = true;
												ag.p1Units = (ArrayList<ActiveUnit>) action.data;
												System.out.println("Received P1's unit " + ag.p1Units.get(0).unit.name + " from " + msg.user);
											}
											
											else if (msg.user.equals(ag.player2.nickname))
											{
												ag.finishedPrep2 = true;
												ag.p2Units = (ArrayList<ActiveUnit>) action.data;
												System.out.println("Received P2's unit: " + ag.p2Units.get(0).unit.name + " from " + msg.user);
											}
											
											
											// If both teams have finished preparing, send each the other's team and update the game's status
											if (ag.finishedPrep1 && ag.finishedPrep2)
											{
												System.out.println("Commencing");
												ag.status = GameStatus.ONGOING;
												
												// Send player 2's team to player 1
												ZankGameAction za = new ZankGameAction(ZankGameActionType.READY, ag.id, null, null, ag.p2Units);
												ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
												ag.player1.messageQueue.put(zm);
												
												// Send player 1's team to player 2
												za = new ZankGameAction(ZankGameActionType.READY, ag.id, null, null, ag.p1Units);
												zm = new ZankMessage(ZankMessageType.GAME, null, za);
												ag.player2.messageQueue.put(zm);
												
												// Initialize turn order
												ag.initializeTurnOrder();
												
												ag.advanceTurn();
											}
										}
										else if (action.type == ZankGameActionType.TURNTEST)
										{
											ag.turnOrder.units[ag.state.currentUnit].counter -= (Integer) action.data;
											ag.turnOrder.units[ag.state.currentUnit].reserve = 0;
											ag.advanceTurn();
										}
										else if (action.type == ZankGameActionType.MOVE)
										{
											int[] data = (int[]) action.data;
											ag.moveUnit(data[0], data[1], data[2], data[3]);
											ag.player1.messageQueue.put(msg);
											ag.player2.messageQueue.put(msg);
										}
										else if (action.type == ZankGameActionType.ACT)
										{
											ag.player1.messageQueue.put(msg);
											ag.player2.messageQueue.put(msg);
											int[] data = (int[]) action.data;
											System.out.println("run: target = " + data[0]);
											
											// Send intermediate facing
											int x = data[data.length - 2], y = data[data.length - 1];
											int[] waitData = {ag.state.currentUnit, ag.intermediateFacing(ag.state.currentUnit, x, y)}; 
											ZankGameAction face = new ZankGameAction(ZankGameActionType.WAIT, ag.id, null, null, waitData);
											ZankMessage waitmsg = new ZankMessage(ZankMessageType.GAME, null, face);
											ag.player1.messageQueue.put(waitmsg);
											ag.player2.messageQueue.put(waitmsg);
											
											int[] targets = new int[data.length - 3];
											for (int i = 0; i < targets.length; i++)
												targets[i] = data[i];
											FFTASkill sk = FFTASkill.values[data[data.length - 3]];
											ag.executeSkill(targets, sk);
											ag.victoryCheck();
										}
										else if (action.type == ZankGameActionType.WAIT)
										{
											int[] data = (int[]) action.data;
											ag.waitUnit(data[0], data[1]);
											ag.player1.messageQueue.put(msg);
											ag.player2.messageQueue.put(msg);
											ag.advanceTurn();
										}
										else if (action.type == ZankGameActionType.EXIT)
										{
											ag.leaveRoom(msg.user);
											if (ag.userlist.size() == 0)
												ChatServer.gamelist.remove(ag);
											
											System.out.println("Closed game " + ag.id); 
											System.out.println("List of active games:");
											for (int i = 0; i < ChatServer.gamelist.size(); i++)
												System.out.println("  " + ChatServer.gamelist.get(i));
										}
									}
									catch (NullPointerException e) { System.out.println("\rUser " + username + " tried to send message to invalid game"); e.printStackTrace(); }
									ag.gameLock.unlock();
								}
							}
							else
								System.out.println("\rnReceived incomprehensible message with command " + command + ": " + msg);
							
						}
						catch (Exception e) { System.out.println("\rError: received incomprehensible message: " + msg); e.printStackTrace();	}
					}
					
					// Check message queue for new messages to send
					if (!messageQueue.isEmpty() && !done)
					{
						ZankMessage m = messageQueue.take();
						System.out.println("OUT: " + nickname + "\t" + m);
						
						out.writeObject(m);
						out.flush();
					}
					sleep(50);
				}
				catch (IOException e) { done = true; e.printStackTrace(); }
			}

			// Close/cleanup
			if (connection != null) connection.close();
			if (in != null) in.close();
			synchronized (ChatServer.userlist) { ChatServer.userlist.remove(this); }
		}
		catch (IOException e) { System.err.println("\rAcceptance error: " + e.getMessage()); }
		catch (InterruptedException e) { System.err.println("\rFailed to add message to queue. "); }
	}
	
}
