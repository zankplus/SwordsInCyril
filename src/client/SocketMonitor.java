package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import fftadata.ActiveUnit;
import fftadata.SkillEffectResult;
import zank.ZankGameAction;
import zank.ZankGameActionType;
import zank.ZankMessage;
import zank.ZankMessageType;
import zank.ZankUser;

public class SocketMonitor extends SwingWorker<List<ZankMessage>, ZankMessage>
{
	ZankClient client;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	InputStreamReader readyChecker;
	boolean done;
	
	public SocketMonitor(ZankClient client)
	{
		super();
		this.client = client;
	}
	
	public List<ZankMessage> doInBackground()
	{
		// Connect to server
		try
		{
			// Prepare socket and i/o streams
			socket = new Socket("149.56.102.52", 55555);
			client.socket = socket;
			
			// Initialize object output stream from socket's output stream
			out = new ObjectOutputStream(new BufferedOutputStream(client.socket.getOutputStream()));
			out.flush();
			
			// Let the client have a copy of the reference, too
			client.out = out;
		}
		catch (IOException e)
		{
			// If anything goes wrong, show that connection failed and end the method
			client.loginWindow.connectFailed();
			return null;
		}
		
		// Watch for incoming messages and deal with them accordingly
		done = false;
		try
		{
			
			in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			readyChecker = new InputStreamReader(new BufferedInputStream(socket.getInputStream()));
			System.out.println("Watching for incoming messages.");
			ZankMessage msg;
			
			while (!done)
			{
				msg = null;
				
				while ((msg = (ZankMessage) in.readObject()) != null)
				{
					publish(msg);
				}
				done = true;
//				
//				// Disconnection handler
//				// TODO: idk do something about this
//				client.chatWindow.appendToChat("<br><span style=\"color:red\"><em>* you have been disconnected from the server");					
			}
		}
		
		catch (EOFException | SocketException e)
		{
			done = false;
			client.shutdownPrep();
		}
		
		catch (Exception e)
		{
			e.printStackTrace(); 
		}		
		
		return null;
	}
	
	public void process(List<ZankMessage> messages)
	{
		for (ZankMessage msg : messages)
		{
			try
			{
				ZankMessageType type = msg.type;
				if (msg.type != ZankMessageType.BEEP)
					System.out.println("IN:\t" + msg);
				
				String user = msg.user;
				
				// CHAT: a user has sent a message for the lobby chat
				if (type.equals(ZankMessageType.READY))
				{
					// Show user that connection succeeded
					client.loginWindow.connectSuccess();
				}
				
				else if (type.equals(ZankMessageType.CHAT))
				{
					client.chatWindow.receiveChat(user, (String) msg.data);
				}
										
				// LOGIN: another user has logged in to the server
				else if (type.equals(ZankMessageType.LOGIN))
				{
					client.chatWindow.receiveLogin(user);
				}
				
				// LOGIN: a user has logged out of the server
				else if (type.equals(ZankMessageType.LOGOUT))
				{
					client.chatWindow.receiveLogout(msg.user);
				}	
				
				// ONLINE: the server has notified the client of which users are online
				else if (type.equals(ZankMessageType.ONLINE))
				{
					client.chatWindow.receiveOnline((String) msg.data);
				}
				
				// CHALLENGE: another user has issued a challenge to the client
				else if (type.equals(ZankMessageType.CHALLENGE))
				{
					client.chatWindow.receiveChallenge(msg.user);
				}
				
				// ENGAGE: two users are engaging in a battle
				else if (type.equals(ZankMessageType.ENGAGE))
				{
					client.chatWindow.receiveEngage(msg.user, (String) msg.data);
				}
				
				// BEEP: server has sent a heartbeat
				else if (type.equals(ZankMessageType.BEEP))
				{
					//
				}
				
				// BEEP: the server has sent a heartbeat
				else if (type.equals(ZankMessageType.ENGAGE))
				{
					System.out.println("dub");
				}
				
				// GAME: a category of actions, indicating that some action has been taken pertaining to the client in a game they are a part of
				else if (type.equals(ZankMessageType.GAME))
				{
					ZankGameAction action = (ZankGameAction) msg.data;
					Engagement game = client.game;
					
					// START: the challenge has been accepted and the client's game is beginning
					if (action.type.equals(ZankGameActionType.START))
					{
						client.launchEngagementWindow(action);
					}
					
					// CHAT: a user in the engagement has sent a message to the engagement chat 
					else if (action.type.equals(ZankGameActionType.CHAT))
					{
						game.receiveChat(user, (String) action.data); 
					}
					
					// READY: the server has sent confirmation that both players are ready to begin the battle
					else if (action.type.equals(ZankGameActionType.READY))
					{
						game.receiveReady((ArrayList<ActiveUnit>) action.data);
					}
					
					// NEXT: the server has indicated that a new unit's turn should begin
					else if (action.type.equals(ZankGameActionType.NEXT))
					{
						game.receiveNext((int[]) action.data);
					}
					
					// MOVE: the server has indicated that one of the participants in the engagement has moved a unit
					else if (action.type.equals(ZankGameActionType.MOVE))
					{
						game.receiveMove((int[]) action.data);
					}
					
					// ACT: the server has indicated that a unit has taken an action
					else if (action.type.equals(ZankGameActionType.ACT))
					{
						game.receiveAct((int[]) action.data);
					}
					
					// DOUBLECAST: the server has indicated that a unit is using the Doublecast ability
					else if (action.type.equals(ZankGameActionType.DOUBLECAST))
					{
						game.receiveDoublecast((int[]) action.data);
					}
					
					// HIT: the server has sent the results of a combat action
					else if (action.type.equals(ZankGameActionType.HIT))
					{
						game.receiveHit((SkillEffectResult[]) action.data);
					}
					
					// DCHIT: the server has sent the results of a doublecasted spell
					else if (action.type.equals(ZankGameActionType.DCHIT))
					{
						game.receiveDCHit((SkillEffectResult[]) action.data);
					}
						
					// WAIT: the server has indicated that some unit ended their turn, picking a direction
					else if (action.type.equals(ZankGameActionType.WAIT))
					{
						game.receiveWait((int[]) action.data);
					}
					
					// GAMEOVER: the server has declared the outcome of the battle
					else if (action.type.equals(ZankGameActionType.GAMEOVER))
					{
						game.receiveGameOver((boolean[]) action.data);
					}
					
					// EXIT: Another player has left the room
					else if (action.type.equals(ZankGameActionType.EXIT))
					{
						game.receiveExit((String) action.data);
					}
					
					// REACTION: A unit's R-Ability has activated
					else if (action.type.equals(ZankGameActionType.REACTION))
					{
						game.receiveReaction((int[]) action.data);
					}
					
					// TURN_ORDER: An able unit's turn has begun and the turn order must be updated
					else if (action.type.equals(ZankGameActionType.TURN_ORDER))
					{
						game.receiveTurnOrder((int[]) action.data); 
					}
				}
				else
					System.out.println("received bad game message from server: " + msg);

				if (client.chatWindow != null)
					client.chatWindow.chat.setCaretPosition(client.chatWindow.chat.getDocument().getLength());
			}
			catch (NullPointerException e) { e.printStackTrace(); }
		}
	}
	
	public void done()
	{
	}
}