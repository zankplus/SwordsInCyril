package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import fftadata.ActiveUnit;
import zank.*;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.SystemColor;

public class EngagementWindow extends JFrame {

	private JPanel contentPane;
	JTextArea battleChat;
	private JTextField battleChatEntry;
	Socket socket;
	ZankUser player, opponent;
	String gameID;
	ObjectInputStream in;
	ObjectOutputStream out;
	MapPanel mapPanel;
	ZankGameMap map;
	int playerNumber;
	int gameMode;
	EngagementWindowRosterPanel rosterPanel;
	GamePanel gamePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EngagementWindow frame = new EngagementWindow(null, 1, null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EngagementWindow(ZankUser player, int playerNumber, ZankUser opponent, String id, ObjectInputStream in, ObjectOutputStream out)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.player = player;
		this.playerNumber = playerNumber;
		this.opponent = opponent;
		this.gameID = id;
		this.in = in;
		this.out = out;
		
		// Set up window
		ImageIcon img = new ImageIcon("resources/misc/appicon.png");
		setIconImage(img.getImage());
		
		try
		{
			setTitle("[" + "///" + "] " + player.username + " vs " + opponent.username );
		} catch (NullPointerException e) { setTitle("Testin' mode"); }
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		
		battleChat = new JTextArea();
		battleChat.setLineWrap(true);
		battleChat.setEditable(false);
		battleChat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(battleChat);
		
		battleChatEntry = new JTextField();
		rightPanel.add(battleChatEntry, BorderLayout.SOUTH);
		battleChatEntry.setColumns(10);
		battleChatEntry.setEnabled(true);
		battleChatEntry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s= battleChatEntry.getText();
				if (!s.equals(""))
				{
					try
					{
							sendChat(s);
					} catch (IOException e1) { e1.printStackTrace(); }
					battleChatEntry.setText("");
				}
			}
		});
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(null);
		leftPanel.setPreferredSize(new Dimension(560, 10));
		splitPane.setLeftComponent(leftPanel);
		
//		ClanBuilderRoster roster = new ClanBuilderRoster();
//		panel.add(roster, BorderLayout.SOUTH);
		

		gamePanel = new GamePanel(this);
		mapPanel = gamePanel.mapPanel;
		map = mapPanel.map;
		mapPanel.beginPlacementMode();
		
		leftPanel.add(gamePanel);
		
//		gamePanel = new JPanel(new BorderLayout());
//		gamePanel.setPreferredSize(new Dimension(480, 400));
//		leftPanel.add(gamePanel, BorderLayout.CENTER);
//		
//		mapPanel = new MapPanel(playerNumber);
//		map = mapPanel.map;
//		gamePanel.add(mapPanel, BorderLayout.CENTER);
//		rosterPanel = new EngagementWindowRosterPanel(this);
//		gamePanel.add(rosterPanel, BorderLayout.SOUTH);
//		
//		
//		mapPanel.roster = rosterPanel.roster;
//		
//		mapPanel.beginPlacementMode(mapPanel.player);
//			
//		
//		JPanel turnOrderPanel = new JPanel();
//		turnOrderPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		turnOrderPanel.setPreferredSize(new Dimension(80, 10));
//		leftPanel.add(turnOrderPanel, BorderLayout.EAST);
//		turnOrderPanel.setLayout(new GridLayout(1, 0, 0, 0));
	}
	
	public void sendChat(String content) throws IOException
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.CHAT, gameID, null, null, content);
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendForfeit() throws IOException
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.CHAT, gameID, null, null, null);
		ZankMessage message = new ZankMessage(ZankMessageType.LOGIN, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendReady() throws IOException
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.READY, gameID, null, null, mapPanel.units);
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	// ZankMessage handlers
	
	// CHAT: append the message to the engagement chat and move the caret to the bottom
	public void receiveChat(String user, String msg)
	{
		battleChat.append("\r\n" + user + ": " + msg);
		battleChat.setCaretPosition(battleChat.getDocument().getLength());
	}
	
	// READY: the server 
	public void receiveReady(ArrayList<ActiveUnit> units)
	{
		if (playerNumber == 1)
			map.p2Units = units;
		else if (playerNumber == 2)
			map.p1Units = units;
		
		gamePanel.beginGame();
		repaint();
	}
}

class EngagementWindowRosterPanel extends ClanBuilderRosterPanel
{
	EngagementWindow ew;
	
	public EngagementWindowRosterPanel(EngagementWindow ew)
	{
		this.ew = ew;
		
		JPanel space = new JPanel(), space2 = new JPanel();
		space.setPreferredSize(new Dimension(36, 1));
		space2.setPreferredSize(new Dimension(1, 10));
		add(space, BorderLayout.WEST);
		add(space2, BorderLayout.SOUTH);
		btnNewUnit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSwapLeft.setEnabled(false);
		btnSwapRight.setEnabled(false);
		
		JPanel readyPanel = new JPanel(new FlowLayout());
		JButton btnReady = new JButton("Ready!");
		btnReady.setPreferredSize(new Dimension(100, 30));
		readyPanel.add(btnReady);
		add(readyPanel, BorderLayout.NORTH);
		
		btnReady.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("\r\n" + ew);
				System.out.println("\r\n" + ew.mapPanel);
				System.out.println("\r\n" + ew.mapPanel.units);
				System.out.println("\r\n" + ew.mapPanel.units.size());
				if (ew.mapPanel.units.size() > 0)
				{
					try
					{
						ew.sendReady();
						if (ew.playerNumber == 1)
							ew.map.p1Units = ew.mapPanel.units;
						else if (ew.playerNumber == 2)
							ew.map.p2Units = ew.mapPanel.units;
						
						btnReady.setEnabled(false);
						btnReady.setText("Waiting for other player...");
					}
					catch (IOException ex) { ex.printStackTrace(); }
				}
			}
		});
	}
}