package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.html.HTMLDocument;

import zank.ZankMessage;
import zank.ZankMessageType;
import zank.ZankUser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Cursor;

public class ChatWindow extends JFrame {

	ZankClient client;
	ZankUser zu;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	JList userlist;
	ArrayList<String> usersOnline;
	JTextPane chat;
	JTextField chatLine;
	JDialog challenge;
	

	/**
	 * Create the frame.
	 */
	public ChatWindow(ZankClient client)
	{
		this.client = client;
		socket = client.socket;
		zu = client.zu;
		out = client.out;
		in = client.in;
		

		JPanel chatPane = new JPanel();
		chatPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		chatPane.add(splitPane);
		
		JScrollPane RightPane = new JScrollPane();
		RightPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane.setRightComponent(RightPane);
		
		chat = new JTextPane();
		chat.setContentType("text/html");
        chat.setText("<html><body style=\"font-family:verdana; font-size:11pt\">");
        
//		chatArea.setLineWrap(true);
		chat.setEditable(false);
		RightPane.setViewportView(chat);
		
		JPanel LeftPane = new JPanel();
		splitPane.setLeftComponent(LeftPane);
		LeftPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblOnline = new JLabel("  Currently online:  ");
		LeftPane.add(lblOnline, BorderLayout.NORTH);
		
		JScrollPane userlistScrollPane = new JScrollPane();
		LeftPane.add(userlistScrollPane, BorderLayout.CENTER);
		userlistScrollPane.setPreferredSize(new Dimension(100, 2));
		
		usersOnline = new ArrayList<String>();
		
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
						if (!user.equals(zu.username))
						{
							ChallengerDialog d = new ChallengerDialog(user, client);
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(640, 480);
		revalidate();
		repaint();
		setResizable(true); 
//		setTitle("Swords in Cyril - Lobby (" + client.zUser.username + ")");
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (socket.isConnected()) // if socket has ever been connected
				{
					try {	sendLogout();	}
					catch (IOException e) { System.out.println("IOException closing window"); }
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClan = new JMenu("Clan");
		menuBar.add(mnClan);
		
		JMenuItem mntmOpenClanBuilder = new JMenuItem("Open Clan Builder");
		mntmOpenClanBuilder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				client.launchClanBuilder();
			}
		});
		mnClan.add(mntmOpenClanBuilder);
		
		setContentPane(chatPane);
		setLocationRelativeTo(null);
	}

	public void sendMessage(String content) throws IOException
	{
		ZankMessage msg = new ZankMessage(ZankMessageType.CHAT, zu.username, content);
		System.out.println("OUT:\t" + msg);
		out.writeObject(msg);
		out.flush();
	}
	
	public void sendLogout() throws IOException
	{
		ZankMessage msg = new ZankMessage(ZankMessageType.LOGOUT, zu.username, null);
		System.out.println("OUT:\t" + msg);
		out.writeObject(msg);
		out.flush();
	}
	
	public void updateUserlist()
	{	
		Collections.sort(usersOnline);
		userlist.setListData(usersOnline.toArray());
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
	
	
	// ZankMessage handlers: When the SocketMonitor receives a new message intended for the ChatWindow, it calls the corresponding method here to handle it
	
	// CHAT: Append the message to the chat
	public void receiveChat(String user, String msg)
	{
		appendToChat("<br><b>" + user + "</b>: " + msg);
	}
	
	// LOGIN: If you're the one logging in, confirm connection. If not, notify that the user has joined.
	// Add their name to the userlist and update its display
	public void receiveLogin(String user)
	{
		if (user.equals(zu.username))
			appendToChat("<em><span style=\"color:blue\">** You are now connected to Swords in Cyril as <strong>" + zu.username + "</strong></span>");
		else
			appendToChat("<br><i><span style=\"color:gray\">* " + user + " has entered the room");
		usersOnline.add(user);
		updateUserlist();
	}
	
	// LOGOUT: Append logout message to chat and remove the exiting user from the userlist; update the userlist display
	public void receiveLogout(String user)
	{
		appendToChat("<br><i><span style=\"color:gray\">* " + user + " has left the room");
		usersOnline.remove(user);
		updateUserlist();
	}
	
	// CHALLENGE: If the challenge originates from another user, open a ChallengeeDialog with information about the challenge
	public void receiveChallenge(String user)
	{
		if (!user.equals(zu.username))
			new ChallengeeDialog(user, client);
	}
	
	// ONLINE: Tokenize the list of users and add each name to the userlist before updating the userlist display
	public void receiveOnline(String users)
	{
		String[] tokens = users.split("\\s+");
		for (int i = 0; i < tokens.length; i++)
			usersOnline.add(tokens[i]);
		updateUserlist();
	}
	
	// ENGAGE: Notify the client that the involved users are engaging
	public void receiveEngage(String user1, String user2)
	{
		appendToChat("<br><i><span style=\"color:gray\">* " + user1 + " and " + user2 + " are engaging!");
	}
}
