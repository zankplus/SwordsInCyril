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
			socket = new Socket("158.69.221.75", 55555);
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
							EngagementWindow gw = client.gameWindow;
							
							// START: the challenge has been accepted and the client's game is beginning
							if (action.type.equals(ZankGameActionType.START))
							{
								client.launchEngagementWindow(action);
							}
							
							// CHAT: a user in the engagement has sent a message to the engagement chat 
							else if (action.type.equals(ZankGameActionType.CHAT))
							{
								gw.receiveChat(user, (String) action.data); 
							}
							
							// READY: the server has sent confirmation that both players are ready to begin the battle
							else if (action.type.equals(ZankGameActionType.READY))
							{
								gw.receiveReady((ArrayList<ActiveUnit>) action.data);
							}
						}
						else
							System.out.println("received bad game message from server: " + msg);

						cw.chatArea.setCaretPosition(cw.chatArea.getDocument().getLength());							
					}
					catch (NullPointerException e) { System.err.println("received bad message from server: " + msg); e.printStackTrace(); }
				}
				done = true;
				
				// Disconnection handler
				// TODO: idk do something about this
				cw.chatArea.append("\r\n* you have been disconnected from the server");					
			}
		}
		catch (IOException e) { System.err.println(e); } catch (ClassNotFoundException e) {	System.err.println(e);	}
		
		return null;
	}
	
	public void done()
	{
	}
}