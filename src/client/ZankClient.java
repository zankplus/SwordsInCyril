package client;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.io.*;
import net.miginfocom.swing.MigLayout;
import zank.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fftadata.ActiveUnit;

public class ZankClient
{
	public static String version = "v0.2";
	public static String title;
	public ZankUser zu;
	public ZankMessage message = null;
	public Socket socket = null;
	public SocketMonitor dispatch;
	public ObjectInputStream in = null;
	public ObjectOutputStream out = null;
	
	public ZankClient self = this;
	
	public EngagementWindow gameWindow = null;
	public LoginWindow loginWindow;
	public ChatWindow chatWindow;
	public ClanBuilder clanBuilder;

	
	
	
	public ZankClient()
	{
		message = null;
		clanBuilder = null;
		loginWindow = new LoginWindow(this);
		EngagementWindow gameWindow = null;
	}
	
	
	
	
	public void sendChallenge(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.CHALLENGE, zu.username, user);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendEngage(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.ENGAGE, zu.username, user);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void launchEngagementWindow(ZankGameAction startMsg)
	{
		String opponentName;
		int playerNumber;
		
		if (zu.username.equals(startMsg.player1))
		{
			opponentName = startMsg.player2;
			playerNumber = 2;
		}
		
		else
		{
			opponentName = startMsg.player1;
			playerNumber = 1;
		}
		
		gameWindow = new EngagementWindow(zu, playerNumber, new ZankUser(opponentName), startMsg.gameID, in, out);
		gameWindow.appendToChat("<em>You are now engaging with <strong>" + opponentName + "</strong>.");
		gameWindow.setVisible(true);
		
		
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
		SocketMonitor dispatch = new SocketMonitor(this);
		dispatch.execute();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZankClient gui = new ZankClient();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		title = "frOkTalk " + version;
		System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");	// Lets the program close on command-Q on macs
			
	}
	
	
}
