package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import fftadata.ActiveUnit;
import zank.ZankGameAction;
import zank.ZankGameActionType;
import zank.ZankMessage;
import zank.ZankMessageType;
import zank.ZankUser;

public class SocketMonitor extends SwingWorker<Void, Object>
{
	ZankClient client;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public SocketMonitor(ZankClient client)
	{
		super();
		this.client = client;
	}
	
	public Void doInBackground()
	{
		// Connect to server
		try
		{
			// Prepare socket and i/o streams
			socket = new Socket("158.69.197.70", 55555);
			client.socket = socket;
			
			out = new ObjectOutputStream(new BufferedOutputStream(client.socket.getOutputStream()));
			out.flush();
			client.out = out;
			
			client.loginWindow.connectSuccess();
			
			

			try { Thread.sleep(250); } catch (InterruptedException e) {}
			
			
		}
		catch (IOException e) { client.loginWindow.connectFailed(); return null;	}
		
		
		// Watch for incoming messages and deal with them accordingly
		try
		{
			boolean done = false;
			System.out.println("in it");
			in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			System.out.println("to win it!");
			while (!done)
			{
				ZankMessage msg = null;
				ZankUser zu = client.zu;
				ChatWindow cw = client.chatWindow;
				
				
				System.out.println("trying to read object");
				while ((msg = (ZankMessage) in.readObject()) != null)
				{
					System.out.println("read object!");
					try
					{
						System.out.println("IN:\t" + msg);
						ZankMessageType type = msg.type;
						String user = msg.user;
						
						
						// CHAT: a user has sent a message for the lobby chat
						if (type.equals(ZankMessageType.CHAT))
						{
							cw.receiveChat(user, (String) msg.data);
						}
						
						
						// LOGIN: another user has logged in to the server
						else if (type.equals(ZankMessageType.LOGIN))
						{
							cw.receiveLogin(user);
						}
						
						
						// LOGIN: a user has logged out of the server
						else if (type.equals(ZankMessageType.LOGOUT))
						{
							cw.receiveLogout(msg.user);
						}	
						
						
						// ONLINE: the server has notified the client of which users are online
						else if (type.equals(ZankMessageType.ONLINE))
						{
							cw.receiveOnline((String) msg.data);
						}
						
						
						// CHALLENGE: another user has issued a challenge to the client
						else if (type.equals(ZankMessageType.CHALLENGE))
						{
							cw.receiveChallenge(msg.user);
						}
						
						// ENGAGE: two users are engaging in a battle
						else if (type.equals(ZankMessageType.ENGAGE))
						{
							cw.receiveEngage(msg.user, (String) msg.data);
						}
						
						// GAME: a category of actions, indicating that some action has been taken pertaining to the client in a game they are a part of
						else if (type.equals(ZankMessageType.GAME))
						{
							ZankGameAction action = (ZankGameAction) msg.data;
							EngagementWindow ew = client.gameWindow;
							
							// START: the challenge has been accepted and the client's game is beginning
							if (action.type.equals(ZankGameActionType.START))
							{
								client.launchEngagementWindow(action);
							}
							
							// CHAT: a user in the engagement has sent a message to the engagement chat 
							else if (action.type.equals(ZankGameActionType.CHAT))
							{
								ew.receiveChat(user, (String) action.data); 
							}
							
							// READY: the server has sent confirmation that both players are ready to begin the battle
							else if (action.type.equals(ZankGameActionType.READY))
							{
								ew.receiveReady((ArrayList<ActiveUnit>) action.data);
							}
							
							// NEXT: the server has indicated that a new unit's turn should begin
							else if (action.type.equals(ZankGameActionType.NEXT))
							{
								ew.receiveNext((Integer) action.data);
							}
							
							// MOVE: the server has indicated that one of the participants in the engagement has moved a unit
							else if (action.type.equals(ZankGameActionType.MOVE))
							{
								ew.receiveMove((int[]) action.data);
							}
							
							// ACT: the server has indicated that a unit has taken an action
							else if (action.type.equals(ZankGameActionType.ACT))
							{
								ew.receiveAct((int[]) action.data);
							}
							
							// HIT: the server has indicated that a unit in combat has taken damage
							else if (action.type.equals(ZankGameActionType.HIT))
							{
								ew.receiveHit((int[]) action.data);
							}
							
							
							// WAIT: the server has indicated that some unit has settled their direction, indicating the end of their turn
							else if (action.type.equals(ZankGameActionType.WAIT))
							{
								ew.receiveWait((int[]) action.data);
							}
							
							// GAMEOVER: the server has declared the outcome of the battle
							else if (action.type.equals(ZankGameActionType.GAMEOVER))
							{
								ew.receiveGameOver((boolean[]) action.data);
							}
							
							// EXIT: Another player has left the room
							else if (action.type.equals(ZankGameActionType.EXIT))
							{
								ew.receiveExit((String) action.data);
							}
						}
						else
							System.out.println("received bad game message from server: " + msg);

						cw.chat.setCaretPosition(cw.chat.getDocument().getLength());							
					}
					catch (NullPointerException e) { System.err.println("received bad message from server: " + msg); e.printStackTrace(); }
				}
				done = true;
				
				// Disconnection handler
				// TODO: idk do something about this
				cw.appendToChat("<br><span style=\"color:red\"><em>* you have been disconnected from the server");					
			}
		}
		catch (IOException e) { System.err.println(e); } catch (ClassNotFoundException e) {	System.err.println(e);	}
		
		return null;
	}
	
	public void done()
	{
	}
}