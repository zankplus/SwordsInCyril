package client;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
import net.miginfocom.swing.MigLayout;
import zank.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clanBuilder.ClanBuilder;
import fftadata.ActiveUnit;

public class SwordsClient
{
	public static String version = "v0.2";
	public static String title;
	public ZankUser zu;
	public ZankMessage message = null;
	public Socket socket = null;
	public SocketMonitor dispatch;
	public ObjectInputStream in = null;
	public ObjectOutputStream out = null;
	
	public SwordsClient self = this;
	
	public Engagement game = null;
	public LoginWindow loginWindow;
	public ChatWindow chatWindow;
	public ClanBuilder clanBuilder;
	
	public ScheduledExecutorService heartbeater;
	
	public SwordsClient()
	{
		message = null;
		clanBuilder = null;
		loginWindow = new LoginWindow(this);
		chatWindow = null;
	}

	public void sendChallenge(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.CHALLENGE, zu.name, user);
		sendZankMessage(message);
	}
	
	public void sendEngage(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.ENGAGE, zu.name, user);
		sendZankMessage(message);
	}
	
	public void sendSpectate(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.SPECTATE, zu.name, user);
		sendZankMessage(message);
	}
	
	public void sendZankMessage(ZankMessage zm)
	{
		if (zm.type != ZankMessageType.BEEP)
		 	System.out.println(Thread.currentThread().getName() + "\tOUT:\t" + zm);
		synchronized(out)
		{
			try
			{
				out.writeObject(zm);
				out.flush();
			}
			
			catch (SocketException e)
			{
				System.out.println("but it failed!");
				shutdownPrep();
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}

	public void launchEngagementWindow(ZankGameAction startMsg, boolean spectator)
	{
		String opponentName;
		int playerNumber;
		
		if (spectator)
		{
			opponentName = startMsg.player2;
			playerNumber = 0;
			
			game = new Engagement(new ZankUser(startMsg.player1), playerNumber, new ZankUser(opponentName), startMsg.gameID, this);
			game.window.appendToChat("<em>You are now watching <strong>" + startMsg.player1 +
									 "</strong> and <strong>" + startMsg.player2 + "</strong> engage.");
		}
		else if (zu.name.equals(startMsg.player1))
		{
			opponentName = startMsg.player2;
			playerNumber = 1;
			
			game = new Engagement(zu, playerNumber, new ZankUser(opponentName), startMsg.gameID, this);
			game.window.appendToChat("<em>You are now engaging with <strong>" + opponentName + "</strong>.");
		}
		else
		{
			opponentName = startMsg.player1;
			playerNumber = 2;
			
			game = new Engagement(zu, playerNumber, new ZankUser(opponentName), startMsg.gameID, this);
			game.window.appendToChat("<em>You are now engaging with <strong>" + opponentName + "</strong>.");
		}
		
		
		
		game.window.setVisible(true);	
	}
	
	public void launchClanBuilder()
	{
		if (clanBuilder != null)
		{
			if (!clanBuilder.isVisible())
			{
				clanBuilder.dispose();
				clanBuilder = new ClanBuilder();
				clanBuilder.setVisible(true);
			}
			else
				clanBuilder.toFront();
		}
		else
		{
			clanBuilder = new ClanBuilder();
			clanBuilder.setVisible(true);
		}
		
	}
	
	public void launchSocketMonitor()
	{
		dispatch = new SocketMonitor(this);
		dispatch.execute();
	}
	
	public void startHeartbeat()
	{
		heartbeater = Executors.newSingleThreadScheduledExecutor();
		heartbeater.scheduleAtFixedRate(
		    new Runnable() {
		        @Override
		        public void run()
		        {
		        	message = new ZankMessage(ZankMessageType.BEEP, null, null);
		        	sendZankMessage(message);
		        }
		    }, 
		    0, 4000, TimeUnit.MILLISECONDS);
	}
	
	public void shutdownPrep()
	{
		try
		{
			socket.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		dispatch.done = true;
		heartbeater.shutdown();
		chatWindow.appendToChat("<br><span style=\"color: red\">oh no, disconnectio!");
		chatWindow.chatLine.setEnabled(false);
		chatWindow.userlist.setEnabled(false);
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwordsClient gui = new SwordsClient();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		title = "frOkTalk " + version;
		System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");	// Lets the program close on command-Q on macs
			
	}

	
}