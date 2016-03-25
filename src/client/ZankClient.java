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
	public ExecutorService pool = null;
	public JFrame mainFrame;
	public JTextArea chatArea = null;
	public JTextField chatLine, nickField;
	public JList userlist = null;
	public JPanel connectPane, connectingPane, chatPane;
	public ZankUser zUser;
	public ZankMessage message = null;
	public MigLayout layout;
	public JLabel statusLabel;
	public Socket socket = null;
	public SocketMonitor dispatch;
	public ObjectInputStream in = null;
	public ObjectOutputStream out = null;
	public ArrayList<String> usersOnline;
	public JDialog challenge = null;
	public ZankClient self = this;
	public EngagementWindow gameWindow = null;
	JButton connectButton;
	
	
	
	public ZankClient(String nickname)
	{
		pool = Executors.newFixedThreadPool(10);
		zUser = new ZankUser(nickname);
		initGUI();
	}
	
	private void initGUI()
	{
		layout = new MigLayout("fillx");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		message = null;
		mainFrame = new JFrame(title);	
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocation(200, 150);
			
		initConnectPane();
		initConnectingPane();
		initChatPane();
		
		mainFrame.setContentPane(connectPane);
		mainFrame.setSize(320, 240);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
	
	public void initConnectPane()
	{
		connectPane = new JPanel(layout);
		
		JLabel welcomeLabel = new JLabel("~ f r O k t a l k ~");
		welcomeLabel.setFont(new Font("Courier New", Font.BOLD, 16));
		
		JLabel nickLabel = new JLabel("nickname: ");
		nickField = new JTextField(12);
		nickField.setText(zUser.username);
		
		connectButton = new JButton("\"just frok me up\"");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (nickField.getText().indexOf(" ") >= 0)
				{
					statusLabel.setText("nicks must not contain spaces");
					statusLabel.setForeground(Color.BLACK);
				}
				else if (nickField.getText().length() < 1)
				{
					statusLabel.setText("nick too short");
					statusLabel.setForeground(Color.BLACK);
				}
				else if (nickField.getText().length() > 64)
				{
					statusLabel.setText("nick too long");
					statusLabel.setForeground(Color.BLACK);
				}
				else
				{
					connectButton.setEnabled(false);
					zUser.username = nickField.getText();
					statusLabel.setText("connecting to server...");
					statusLabel.setForeground(Color.BLACK);
					SwingWorker<Void, Object> dispatch = new SocketMonitor();
					dispatch.execute();
				}
			}
		});
		
		nickField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == 10) connectButton.doClick();
			}
			
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e) {}
		});
		
		statusLabel = new JLabel();
		
		connectPane.add(welcomeLabel, "center, span 2, wrap, gapbottom 20, gaptop 15");
		connectPane.add(nickLabel, "right, gapright 5");
		connectPane.add(nickField, "wrap 55");
		connectPane.add(connectButton, "center, span 2, wrap 15");
		connectPane.add(statusLabel, "center, span 2");
	}
	
	public void initConnectingPane()
	{
		connectingPane = new JPanel(layout);
		JLabel connectingLabel = new JLabel("connecting to server...");
		connectingPane.add(connectingLabel, "center, gaptop 75");
	}
	
	public void initChatPane()
	{
		chatPane = new JPanel();
		chatPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		chatPane.add(splitPane);
		
		JScrollPane RightPane = new JScrollPane();
		RightPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane.setRightComponent(RightPane);
		
		chatArea = new JTextArea();
		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		chatArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		RightPane.setViewportView(chatArea);
		
		JPanel LeftPane = new JPanel();
		splitPane.setLeftComponent(LeftPane);
		LeftPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblOnline = new JLabel("  Currently online:  ");
		LeftPane.add(lblOnline, BorderLayout.NORTH);
		
		JScrollPane userlistScrollPane = new JScrollPane();
		LeftPane.add(userlistScrollPane, BorderLayout.CENTER);
		userlistScrollPane.setPreferredSize(new Dimension(100, 2));
		
		userlist = new JList<String>();
		userlist.setFont(new Font("Tahoma", Font.PLAIN, 11));
		userlist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				JList list = (JList) e.getSource();
				if (e.getClickCount() == 2)
				{
					int index = list.locationToIndex(e.getPoint());
					if (index >= 0)
					{
						String user = (String) list.getModel().getElementAt(index);
						if (!user.equals(zUser.username))
						{
							ChallengerDialog d = new ChallengerDialog(user, self);
						}
					}
					
				}
			}
		});
		userlistScrollPane.setViewportView(userlist);
		
		chatLine = new JTextField();
		chatLine.setColumns(10);
		chatLine.setEnabled(true);
		chatLine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s= chatLine.getText();
				if (!s.equals(""))
				{
					try
					{
							sendMessage(s);
					} catch (IOException e1) { e1.printStackTrace(); }
					chatLine.setText("");
				}
			}
		});
		chatPane.add(chatLine, BorderLayout.SOUTH);
	}
	
	public void sendLogin() throws IOException
	{
		message = new ZankMessage(ZankMessageType.LOGIN, zUser.username, null);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendLogout() throws IOException
	{
		message = new ZankMessage(ZankMessageType.LOGOUT, zUser.username, null);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendMessage(String content) throws IOException
	{
		message = new ZankMessage(ZankMessageType.CHAT, zUser.username, content);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendChallenge(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.CHALLENGE, zUser.username, user);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void sendEngage(String user) throws IOException
	{
		message = new ZankMessage(ZankMessageType.ENGAGE, zUser.username, user);
		System.out.println("OUT:\t" + message);
		out.writeObject(message);
		out.flush();
	}
	
	public void updateUserlist()
	{	
		Collections.sort(usersOnline);
		userlist.setListData(usersOnline.toArray());
	}
	
	public static void main(String[] args)
	{
		String defaultNick = "fedoratipper" + Integer.toString((int) (Math.random() * 10000));
		title = "frOkTalk " + version;
		System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");	// Lets the program close on command-Q on macs
		ZankClient gui = new ZankClient(defaultNick);	
	}
	
	private class SocketMonitor extends SwingWorker<Void, Object>
	{
		public Void doInBackground()
		{
			try
			{
				socket = new Socket("158.69.221.75", 55555);
				statusLabel.setText("connection established!");
				statusLabel.setForeground(Color.BLUE);
				out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				out.flush();
				
				sendLogin();
				usersOnline = new ArrayList<String>();
				
				try { Thread.sleep(250); } catch (InterruptedException e) {}
				
				// update JFrame
				mainFrame.setContentPane(chatPane);
				mainFrame.setSize(640, 480);
				mainFrame.revalidate();
				mainFrame.repaint();
				mainFrame.setResizable(true); 
				mainFrame.setTitle(title + " - " + zUser.username);
				System.out.println("1");
				mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						if (socket.isConnected()) // if socket has ever been connected
						{
							try {	sendLogout();	}
							catch (IOException e) { System.out.println("IOException closing window"); }
						}
					}
				});
				System.out.println("2");
				
			}
			catch (IOException e) { statusLabel.setText("connection failed! server must be down."); statusLabel.setForeground(Color.RED); connectButton.setEnabled(true);	}
			
			try
			{
				System.out.println("3");
				boolean done = false;
				System.out.println("4");
				in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				System.out.println("5");
				while (!done)
				{
					ZankMessage msg = null;
					
					System.out.println("trying to read object");
					while ((msg = (ZankMessage) in.readObject()) != null)
					{
						System.out.println("read object!");
						try
						{
							System.out.println("IN:\t" + msg);
							ZankMessageType type = msg.type;
							String user = msg.user;
							
							if (type.equals(ZankMessageType.CHAT))
							{
								chatArea.append("\r\n" + user + ": " + (String) msg.data);
							}
							
							else if (type.equals(ZankMessageType.LOGIN))
							{
								if (user.equals(zUser.username))
									chatArea.append("** you are now connected to frOktalk as " + zUser.username);
								else
									chatArea.append("\r\n* " + user + " has entered the room");
								usersOnline.add(user);
								updateUserlist();
							}
							
							else if (type.equals(ZankMessageType.LOGOUT))
							{
								chatArea.append("\r\n* " + user + " has left the room");
								usersOnline.remove(user);
								updateUserlist();
							}	
							
							else if (type.equals(ZankMessageType.ONLINE))
							{
								String[] tokens = ((String) msg.data).split("\\s+");
								for (int i = 0; i < tokens.length; i++)
									usersOnline.add(tokens[i]);
								updateUserlist();
							}
							
							else if (type.equals(ZankMessageType.CHALLENGE))
							{
								if (!user.equals(zUser.username))
									new ChallengeeDialog(user, self);
							}
							
							else if (type.equals(ZankMessageType.ENGAGE))
							{
								chatArea.append("\r\n* " + msg.user + " and " + (String) msg.data + " are engaging!");
							}
							
							else if (type.equals(ZankMessageType.GAME))
							{
								ZankGameAction action = (ZankGameAction) msg.data;
								
								if (action.type.equals(ZankGameActionType.START))
								{
									String opponentName;
									int playerNumber;
									
									if (zUser.username.equals(action.player1))
									{
										opponentName = action.player2;
										playerNumber = 2;
									}
									else
									{
										opponentName = action.player1;
										playerNumber = 1;
									}

									gameWindow = new EngagementWindow(zUser, playerNumber, new ZankUser(opponentName), action.gameID, in, out);
									gameWindow.setVisible(true);
									gameWindow.battleChat.append("you are now engaging with " + opponentName);
									
								}
								
								else if (action.type.equals(ZankGameActionType.CHAT))
								{
									gameWindow.battleChat.append("\r\n" + user + ": " + action.data);
									gameWindow.battleChat.setCaretPosition(gameWindow.battleChat.getDocument().getLength());
								}
								
								else if (action.type.equals(ZankGameActionType.READY))
								{
									if (gameWindow.playerNumber == 1)
										gameWindow.map.p2Units = (ArrayList<ActiveUnit>) action.data;
									else if (gameWindow.playerNumber == 2)
										gameWindow.map.p1Units = (ArrayList<ActiveUnit>) action.data;
									
									gameWindow.gamePanel.beginGame();
									gameWindow.repaint();
									
//									gameWindow.battleChat.append("\r\n P1: ");
//									for (ActiveUnit au : gameWindow.map.p1Units)
//										gameWindow.battleChat.append(au.unit.name + "  ");
//									
//									gameWindow.battleChat.append("\r\n P2: ");
//									for (ActiveUnit au : gameWindow.map.p2Units)
//										gameWindow.battleChat.append(au.unit.name + "  ");
									
								}
							}
							else
								System.out.println("received bad game message from server: " + msg);

							chatArea.setCaretPosition(chatArea.getDocument().getLength());							
						}
						catch (NullPointerException e) { System.err.println("received bad message from server: " + msg); e.printStackTrace(); }
					}
					done = true;
					chatArea.append("\r\n* you have been disconnected from the server");					
				}
			}
			catch (IOException e) { System.err.println(e); } catch (ClassNotFoundException e) {	System.err.println(e);	}
			
			return null;
		}
		
		public void done()
		{
		}
	}
}
