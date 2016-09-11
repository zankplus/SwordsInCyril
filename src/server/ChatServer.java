package server;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import zank.*;

public class ChatServer
{
	private ExecutorService pool;
	protected static List<ActiveUser> userlist;
	protected static List<ActiveGame> gamelist;
	protected static LinkedBlockingQueue<ZankMessage> masterMessageQueue;

	public ChatServer()
	{
		pool = Executors.newFixedThreadPool(10);
		userlist = Collections.synchronizedList(new ArrayList<ActiveUser>());
		gamelist = Collections.synchronizedList(new ArrayList<ActiveGame>());
		masterMessageQueue = new LinkedBlockingQueue<ZankMessage>();
	}
	
	public void launch()
	{
		System.out.println("Server online");
		try (ServerSocket server = new ServerSocket(55555))
		{
			MessageHandler msgh = new MessageHandler();
			pool.submit(msgh);
			
			while(true)
			{
				System.out.println("confirmation 1");
				Socket connection = server.accept();
				System.out.println("confirmation 2");
				ActiveUser user = new ActiveUser(connection);
				System.out.println("confirmation 3");
				synchronized (userlist) { userlist.add(user); }
				System.out.println("confirmation 4");
				pool.submit(user);
				System.out.println("confirmation 5");
			}
		}
		catch (IOException e) { System.err.println(e); }
	}
	
	public static ActiveUser findUser(String name)
	{
		synchronized(userlist)
		{
			for (ActiveUser u : userlist)
				if (u.nickname.equals(name))
					return u;
		}
		return null;
	}
	
	public static ActiveGame findGame(String id)
	{
		synchronized(gamelist)
		{
			for (ActiveGame ag : gamelist)
				if (ag.id.equals( id ))
					return ag;
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		ChatServer cs = new ChatServer();
		cs.launch();
	}
	
	 

	private class MessageHandler extends Thread 
	{
		public void run()
		{
			this.setDaemon(true);
			System.out.println("Message handler is running.");
			while(true)
			{
				if (!masterMessageQueue.isEmpty())
				{
					try
					{
						System.out.println("Message handler 1");
						ZankMessage message = masterMessageQueue.take();
						
						// If it's a Game message, send it to everyone in the specified room
						if (message.type == ZankMessageType.GAME)
						{
							System.out.println("does this ever actually get used lol");
							ZankGameAction action = (ZankGameAction) message.data;
							ActiveGame ag = findGame(action.gameID);
							
							if (ag != null)
								ag.sendToAll(message);
						}
						
						// If it's a CHALLENGE, send it only to the recipient
						else if (message.type == ZankMessageType.CHALLENGE)
						{
							System.out.println("does this either?");
							ActiveUser dest = findUser((String) message.data);
							if (dest != null)
								dest.messageQueue.put(message);
						}
						
						// Anything else, send it to everyone
						else
							synchronized(userlist)
							{
								for (int i = 0; i < userlist.size(); i++)
									userlist.get(i).messageQueue.put(message);
							}
							
					}
					catch (InterruptedException e) { e.printStackTrace(); }
					
					if (masterMessageQueue.isEmpty())
						yield();
				}
			}
		}
	}
}

