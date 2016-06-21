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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLDocument;

import fftadata.ActiveUnit;
import fftadata.FFTASkill;
import zank.*;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.SystemColor;

public class EngagementWindow extends JFrame {

	private JPanel contentPane;
	JTextPane chat;
	private JTextField chatEntry;
	Socket socket;
	ZankUser player, opponent;
	String gameID;
	ObjectInputStream in;
	ObjectOutputStream out;
	ZankGameMap map;
	ZankGameAction action;
	ZankMessage message;
	
	int playerNumber;
	
	boolean gameOver;
	
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
		gameOver = false;
		
		// Set up window
		ImageIcon img = new ImageIcon("resources/misc/appicon2.png");
		setIconImage(img.getImage());
		
		try
		{
			setTitle("[" + "///" + "] " + player.username + " vs " + opponent.username );
		} catch (NullPointerException e) { setTitle("Testin' mode"); }
		
		// Closing
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JFrame ew = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				// If the engagement is still in progress, prompt the user to confirm the window's closing
				if (!gameOver)
				{
					int output = JOptionPane.showConfirmDialog(ew, "Are you sure you want to abandon this engagement?",
							"Warning: Engagement in progress!", JOptionPane.YES_NO_OPTION);
					if (output == JOptionPane.YES_OPTION)
					{
						try {
							sendExit();
						} catch (IOException e) { e.printStackTrace(); }
						ew.dispose();
					}
				}
				else
					ew.dispose();
			}
		});
		
		
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(320, 10));
		contentPane.add(rightPanel, BorderLayout.CENTER);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		
		chat = new JTextPane();
		chat.setContentType("text/html");
		chat.setText("<html><body style=\"font-family:verdana; font-size:11pt\">");
		
		chat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(chat);
		
		chatEntry = new JTextField();
		rightPanel.add(chatEntry, BorderLayout.SOUTH);
		chatEntry.setColumns(10);
		chatEntry.setEnabled(true);
		chatEntry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s= chatEntry.getText();
				if (!s.equals(""))
				{
					try
					{
							sendChat(s);
					} catch (IOException e1) { e1.printStackTrace(); }
					chatEntry.setText("");
				}
			}
		});
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(null);
		leftPanel.setPreferredSize(new Dimension(560, 10));
		contentPane.add(leftPanel, BorderLayout.WEST);

		gamePanel = new GamePanel(this);
		map = gamePanel.map;
		leftPanel.add(gamePanel);
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
		ZankGameAction action = new ZankGameAction(ZankGameActionType.READY, gameID, null, null, gamePanel.getYourUnits());
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendMove() throws IOException
	{
		ActiveUnit au = gamePanel.units[gamePanel.currentUnit];
		int[] data = {gamePanel.currentUnit, au.x, au.y, au.z};
		ZankGameAction action = new ZankGameAction(ZankGameActionType.MOVE, gameID, null, null, data);
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendAction(ArrayList<Integer> targets, FFTASkill sk) throws IOException
	{
		int[] data = new int[targets.size() + 1];
		for (int i = 0; i < targets.size(); i++)
			data[i] = targets.get(i);
		data[data.length - 1] = sk.ordinal();
		
		System.out.println("sendAction: target = " + data[0]);
		
		action = new ZankGameAction(ZankGameActionType.ACT, gameID, null, null, data);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendWait(int dir) throws IOException
	{
		int[] data = {gamePanel.currentUnit, dir};
		action = new ZankGameAction(ZankGameActionType.WAIT, gameID, null, null, data);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendTurnTest(int ct) throws IOException
	{
		action = new ZankGameAction(ZankGameActionType.TURNTEST, gameID, null, null, ct);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendExit() throws IOException
	{
		action = new ZankGameAction(ZankGameActionType.EXIT, gameID, null, null, null);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		out.writeObject(message);
		out.flush();
	}
	
	public void appendToChat(String s)
	{
		try {
			HTMLDocument doc = (HTMLDocument) chat.getDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ZankMessage handlers
	// CHAT: append the message to the engagement chat and move the caret to the bottom
	public void receiveChat(String user, String msg)
	{
		appendToChat("<br><b>" + user + "</b>: " + msg);
	}
	
	// READY: register the received units to the rival team and create a list all the game's units in gamePanel
	// before telling gamePanel to start the game.
	public void receiveReady(ArrayList<ActiveUnit> units)
	{
		if (playerNumber == 1)
			gamePanel.p2Units = units;
		else if (playerNumber == 2)
			gamePanel.p1Units = units;
		
		ActiveUnit[] aus = new ActiveUnit[gamePanel.p1Units.size() + gamePanel.p2Units.size()];
		
		for (int i = 0; i < gamePanel.p1Units.size(); i++)
		{
			aus[i] = gamePanel.p1Units.get(i);
			aus[i].id = i;
			System.out.println(i + " " + aus[i].unit.name);
		}
		
		for (int i = 0; i < gamePanel.p2Units.size(); i++)
		{
			aus[i + gamePanel.p1Units.size()] = gamePanel.p2Units.get(i);
			aus[i + gamePanel.p1Units.size()].id = i + gamePanel.p1Units.size();
		}
		
		gamePanel.units = aus;
		gamePanel.beginGame();
		gamePanel.setupPreviews();
		System.out.println("Finished setting up previews, now beginning game");
		repaint();
	}
	
	// NEXT: initiate the given character's turn
	public void receiveNext(int index)
	{
		gamePanel.currentUnit = index;
		
		if (gamePanel.units[index].team == playerNumber)
		{	
			gamePanel.startPlayerTurn();
		}
		else
			gamePanel.startRivalTurn();
		
		ActiveUnit au = gamePanel.units[index];
		
		gamePanel.selectTile(map.mapData[au.x][au.y]);
		
		appendToChat("<br><em><span style=\"color:gray\"><strong>" + gamePanel.units[index].unit.name + "</strong> takes their turn!");
	}
	
	// MOVE: move the indicated unit using MapPanel's moveUnit method
	public void receiveMove(int[] data)
	{
		ActiveUnit au = gamePanel.units[data[0]];
		ZankMapTile dest = map.mapData[data[1]][data[2]];
		gamePanel.moveUnit(au, dest);
	}
	
	// ACT: announce in chat that the active unit has taken the indicated action
	public void receiveAct(int[] data)
	{
		System.out.println("receiveAct: target = " + data[0]);
		
		FFTASkill sk = FFTASkill.values[data[data.length - 1]];
		if (sk == FFTASkill.FIGHT)
			appendToChat("<br><em><span style=\"color:gray\">...<strong>" + gamePanel.units[gamePanel.currentUnit].unit.name +
				"</strong> attacks <strong>" + gamePanel.units[data[0]].unit.name + "</strong>!");
		else
			appendToChat("<br><em><span style=\"color:gray\">...<strong>" + gamePanel.units[gamePanel.currentUnit].unit.name +
					"</strong> uses " + sk.NAME + " on <strong>" + gamePanel.units[data[0]].unit.name + "</strong>!");
		System.out.println("Received " + sk.NAME);
	}
	
	// HIT: apply the amount of damage specified and announce the results in chat
	public void receiveHit(int[] data)
	{
		System.out.println("receiveHit: target = " + data[0]);
		
		if (data[1] == 1)
		{
			appendToChat("<br><em><span style=\"color:gray\">......<strong>" + gamePanel.units[data[0]].unit.name +
					"</strong> takes <strong><span style=\"color:red\">" + data[2] + "</strong> damage!");
			gamePanel.applyDamage(data[0], data[2]);
		}
		else
			appendToChat("<br><em><span style=\"color:gray\">......The attack misses <strong>" +
					gamePanel.units[data[0]].unit.name + "</strong>!");
	}
	
	// WAIT: change the indicated unit's facing in the MapPanel
	public void receiveWait(int[] data)
	{
		gamePanel.faceUnit(data[1]);
	}
	
	// LOSE: announce the winner's victory (or a draw, if both teams have 'lost') in chat.
	// If the current turn belongs to one of this unit's clients, and the game isn't over, bring up the facing panel.
	public void receiveGameOver(boolean[] data)
	{
		// Determine which player is p1 and which is p2
		String p1name, p2name;
		if (playerNumber == 1)
		{
			p1name = player.username;
			p2name = opponent.username;
		}
		else
		{
			p2name = player.username;
			p1name = opponent.username;
		}
		
		// If data[0] and data[1] are both true, the match has ended in a tie
		if (data[0] && data[1])
		{
			gameOver = true;
			setTitle(getTitle() + " - tie game!");
			appendToChat("<br><em><strong>The engagement has ended in a <span style=" +
					"\"text-decoration: underline; color:yellow\">tie</span></strong>");
		}
		
		// If data[0] is true, player *1* has LOST and player *2* has WON
		else if (data[0])
		{
			gameOver = true;
			setTitle(getTitle() + " - " + p2name + " wins!");
			appendToChat("<br><em><strong><span style=\"text-decoration: underline; color:blue\">" +
					p2name + "</span> has won the engagement!</strong>");
		}
		
		// If data[1] is true, player *2* has LOST and player *1* has WON
		else if (data[1])
		{
			gameOver = true;
			setTitle(getTitle() + " - " + p1name + " wins!"); 
			appendToChat("<br><em><strong><span style=\"text-decoration: underline; color:red\">" +
					p1name + "</span> has won the engagement!</strong>");
		}
		
		// If neither data[0] nor [1] is true, something weird has happened.
		else
		{
			if (gamePanel.isYourTurn())
				gamePanel.finishAct();
		}
	}
	
	public void receiveExit(String username)
	{
		appendToChat("<br><em><strong>" + username + " has left the room.</strong>");
	}
}

class EngagementWindowRosterPanel extends ClanBuilderRosterPanel
{
	EngagementWindow ew;
	
	public EngagementWindowRosterPanel(EngagementWindow ew)
	{
		this.ew = ew;
		setPreferredSize(new Dimension(1, 162));
		JPanel padding = new JPanel(), padding2 = new JPanel(), padding3 = new JPanel();
		padding.setPreferredSize(new Dimension(36, 1));
		padding2.setPreferredSize(new Dimension(1, 22));
		padding3.setPreferredSize(new Dimension(80, 1));
		add(padding, BorderLayout.WEST);
		add(padding2, BorderLayout.SOUTH);
		rightPanel.add(padding3, BorderLayout.EAST);
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
				ArrayList<ActiveUnit> units = ew.gamePanel.getYourUnits();
				if (units.size() > 0)
				{
					try
					{
						ew.sendReady();
						if (ew.playerNumber == 1)
							ew.gamePanel.p1Units = units;
						else if (ew.playerNumber == 2)
							ew.gamePanel.p2Units = units;
						
						btnReady.setEnabled(false);
						btnReady.setText("Waiting for other player...");
					}
					catch (IOException ex) { ex.printStackTrace(); }
				}
			}
		});
	}
}
