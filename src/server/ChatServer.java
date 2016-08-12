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
	protected static List<ActiveUser> userList;
	protected static List<ActiveGame> gameList;
	protected static LinkedBlockingQueue<ZankMessage> masterMessageQueue;

	public ChatServer()
	{
		pool = Executors.newFixedThreadPool(10);
		userList = Collections.synchronizedList(new ArrayList<ActiveUser>());
		gameList = Collections.synchronizedList(new ArrayList<ActiveGame>());
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
				Socket connection = server.accept();
				ActiveUser user = new ActiveUser(connection);
				synchronized (userList) { userList.add(user); }
				pool.submit(user);
			}
		}
		catch (IOException e) { System.err.println(e); }
	}
	
	public static ActiveUser findUser(String name)
	{
		synchronized(userList)
		{
			for (ActiveUser u : userList)
				if (u.nickname.equals(name))
					return u;
		}
		return null;
	}
	
	public static ActiveGame findGame(String id)
	{
		synchronized(gameList)
		{
			for (ActiveGame ag : gameList)
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
						ZankMessage message = masterMessageQueue.take();
						
						// If it's a Game message, send it to everyone in the specified room
						if (message.type == ZankMessageType.GAME)
						{
							ZankGameAction action = (ZankGameAction) message.data;
							ActiveGame ag = findGame(action.gameID);
							
							if (ag != null)
								ag.sendToAll(message);
						}
						
						else if (message.type == ZankMessageType.CHALLENGE)
						{
							ActiveUser dest = findUser((String) message.data);
							if (dest != null)
								dest.messageQueue.put(message);
						}
						
						// Otherwise, send the message to everyone
						else
							synchronized(userList)
							{
								for (int i = 0; i < userList.size(); i++)
									userList.get(i).messageQueue.put(message);
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

