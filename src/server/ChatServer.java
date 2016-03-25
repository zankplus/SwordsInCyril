package server;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.*;

import zank.*;

public class ChatServer
{
	private ExecutorService pool;
	protected static ArrayList<ActiveUser> userList;
	protected static ArrayList<ActiveGame> gameList;
	protected static LinkedBlockingQueue<ZankMessage> masterMessageQueue;

	public ChatServer()
	{
		pool = Executors.newFixedThreadPool(10);
		userList = new ArrayList<ActiveUser>();
		gameList = new ArrayList<ActiveGame>();
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
		for (ActiveUser u : userList)
			if (u.nickname.equals(name))
				return u;
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
						for (int i = 0; i < userList.size(); i++)
						{	
						    synchronized (userList)
						    {
						    	userList.get(i).messageQueue.put(message);
						    }
						}
					}
					catch (InterruptedException e) { System.err.println("MessageHandler interrupted"); }
					
					if (masterMessageQueue.isEmpty())
						yield();
				}
			}
		}
	}
}

