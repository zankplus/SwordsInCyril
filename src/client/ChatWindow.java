package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLDocument;

import fftadata.FFTACommand;
import zank.ZankMessage;
import zank.ZankMessageType;
import zank.ZankUser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Cursor;

public class ChatWindow extends JFrame {

	ZankClient client;
	ZankUser self;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	JList userlist;
	ArrayList<ZankUser> usersOnline;
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
		self = client.zu;
		out = client.out;
		in = client.in;
		

		JPanel chatPane = new JPanel();
		chatPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		chatPane.add(splitPane);
		
		JPanel LeftPane = new JPanel();
		splitPane.setLeftComponent(LeftPane);
		LeftPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblOnline = new JLabel("  Currently online:  ");
		LeftPane.add(lblOnline, BorderLayout.NORTH);
		
		JScrollPane userlistScrollPane = new JScrollPane();
		LeftPane.add(userlistScrollPane, BorderLayout.CENTER);
		userlistScrollPane.setPreferredSize(new Dimension(100, 2));
		
		usersOnline = new ArrayList<ZankUser>();
		
		userlist = new JList<ZankUser>();
		userlist.setFont(new Font("Tahoma", Font.PLAIN, 11));
		userlist.setCellRenderer(new UserCellRenderer());
		userlist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				JList list = (JList) e.getSource();
				if (e.getClickCount() == 2)
				{
					int index = list.locationToIndex(e.getPoint());
					if (index >= 0)
					{
						ZankUser user = (ZankUser) list.getModel().getElementAt(index);
						if (!user.name.equals(self.name))
						{
							if (user.status.contentEquals("Ready") && self.status.equals("Ready"))
							{
								ChallengerDialog d = new ChallengerDialog(user.name, client);
							}
							else
							{
								SpectatorDialog d = new SpectatorDialog(user, client);
							}
							
						}
					}
				}
			}
		});
		userlistScrollPane.setViewportView(userlist);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane RightPane = new JScrollPane();
		panel_1.add(RightPane);
		RightPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		chat = new JTextPane();
		chat.setContentType("text/html");
		chat.setText("<html><body style=\"font-family:verdana; font-size:11pt\">");
		
//		chatArea.setLineWrap(true);
		chat.setEditable(false);
		RightPane.setViewportView(chat);
		
		chatLine = new JTextField();
		panel_1.add(chatLine, BorderLayout.SOUTH);
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
		
		setSize(640, 480);
		revalidate();
		repaint();
		setResizable(true); 
//		setTitle("Swords in Cyril - Lobby (" + client.zUser.username + ")");
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				// Close the engagement window, if it's still open
				if (client.game != null)
					client.game.window.closeEngagementWindow();
			
				// Sleep for a fraction of a second to give the server time to process the above
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Notify the server of departure
				sendLogout();
			
				// Exit program
				client.shutdownPrep();
				System.exit(0);
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClan = new JMenu("Clan Builder");
		menuBar.add(mnClan);
		
		JMenuItem mntmOpenClanBuilder = new JMenuItem("Open");
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
		ZankMessage msg = new ZankMessage(ZankMessageType.CHAT, self.name, content);
		client.sendZankMessage(msg);
	}
	
	public void sendLogout()
	{
		ZankMessage msg = new ZankMessage(ZankMessageType.LOGOUT, self.name, null);
		client.sendZankMessage(msg);
	}
	
	public void updateUserlist()
	{	
		Collections.sort(usersOnline);
		userlist.setListData(usersOnline.toArray());
		userlist.repaint();
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
		if (user.equals(self.name))
		{
			appendToChat("<em><span style=\"color:blue\">** You are now connected to Swords in Cyril as <strong>" + self.name + "</strong></span>");
			setVisible(true);
			client.loginWindow.dispose();
			usersOnline.add(self);
		}
		else
		{
			appendToChat("<br><i><span style=\"color:gray\">* " + user + " has entered the room");
			usersOnline.add(new ZankUser(user));
		}
		
		updateUserlist();
	}
	
	// LOGOUT: Append logout message to chat and remove the exiting user from the userlist; update the userlist display
	public void receiveLogout(String user)
	{
		appendToChat("<br><i><span style=\"color:gray\">* " + user + " has left the room");
		for (int i = 0; i < usersOnline.size(); i++)
			if (user.equals(usersOnline.get(i).name))
			{
				usersOnline.remove(i);
				i = usersOnline.size();
			}
		
		updateUserlist();
	}
	
	// CHALLENGE: If the challenge originates from another user, open a ChallengeeDialog with information about the challenge
	public void receiveChallenge(String user)
	{
		if (!user.equals(self.name))
			new ChallengeeDialog(user, client);
	}
	
	// ONLINE: Tokenize the list of users and add each name to the userlist before updating the userlist display
	public void receiveOnline(ZankUser[] data)
	{
		for (int i = 0; i < data.length; i++)
		{
			System.out.println("Online: \"" + data[i] + "\"...");
			if (data[i] != null)
				usersOnline.add(data[i]);
		}
		updateUserlist();
	}
	
	// ENGAGE: Notify the client that the involved users are engaging
	public void receiveEngage(String user1, String user2, String gameID)
	{
		appendToChat("<br><i><span style=\"color:gray\">* " + user1 + " and " + user2 + " are engaging!");
		setStatus(user1, "vs. " + user2, gameID);
		setStatus(user2, "vs. " + user1, gameID);
		
		System.out.println("Game ID: " + gameID);
		
		updateUserlist();
	}

	public void receiveAvailable(String user)
	{
		setStatus(user, "Ready", null);
		updateUserlist();
	}
	
	public void setStatus(String username, String status, String battle)
	{
		for (int i = 0; i < usersOnline.size(); i++)
			if (usersOnline.get(i).name.equals(username))
			{
				usersOnline.get(i).status = status;
				usersOnline.get(i).battleID = battle;
				i = usersOnline.size();
			}
	}
}

class ChallengeeDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	Socket socket;
	String user; 
		
	public ChallengeeDialog(String user, ZankClient gui)
	{
		this.user = user;
		setModal(true);
		setBounds(100, 100, 200, 125);
		setLocationRelativeTo(gui.chatWindow);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblChallenge = new JLabel(user + " has challenged you!!");
		contentPanel.add(lblChallenge);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					gui.sendEngage(user);
				} catch (IOException e) {
					System.out.println("Couldn't challenge user " + user);
				}
				dispose();
			}
		});
		okButton.setHorizontalAlignment(SwingConstants.LEFT);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Not OK");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		buttonPane.add(cancelButton);
			
		setVisible(true);
	}
}

class ChallengerDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	Socket socket;
	String user; 
		
	public ChallengerDialog(String user, ZankClient client)
	{
		this.user = user;
		setModal(true);
		
		setBounds(100, 100, 200, 125);
		setLocationRelativeTo(client.chatWindow);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblChallenge = new JLabel("Challenge " + user + " ???");
		contentPanel.add(lblChallenge);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					client.sendChallenge(user);
				} catch (IOException e) {
					System.out.println("Couldn't challenge user " + user);
				}
				dispose();
			}
		});
		okButton.setHorizontalAlignment(SwingConstants.LEFT);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Not OK");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		buttonPane.add(cancelButton);
			
		setVisible(true);
	}
	
}

class SpectatorDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	Socket socket;
	String user; 
		
	public SpectatorDialog(ZankUser user, ZankClient client)
	{
		this.user = user.name;
		String user2 = user.status.split(" ")[1];
		setModal(true);
		
		setBounds(100, 100, 200, 125);
		setLocationRelativeTo(client.chatWindow);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblChallenge = new JLabel("<html><center>Watch " + user.name + "'s<br>engagement with<br>" + user2 + " ???");
		contentPanel.add(lblChallenge);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					client.sendSpectate(user.battleID);
				} catch (IOException e) {
					System.out.println("Couldn't challenge user " + user);
				}
				dispose();
			}
		});
		okButton.setHorizontalAlignment(SwingConstants.LEFT);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Not OK");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		buttonPane.add(cancelButton);
			
		setVisible(true);
	}
	
}



class UserCellRenderer implements ListCellRenderer<ZankUser>
{
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ZankUser> list, ZankUser user, int index, boolean isSelected, boolean hasCellFocus)
	{
		JLabel result = (JLabel) defaultRenderer.getListCellRendererComponent(list, user, index, isSelected, hasCellFocus);
		String text = "<html>" + user.name + "<br>";
		
		String color = "gray";
		
		if (isSelected)
		{
			result.setBackground(new Color(51, 153, 255));
			color = "white";
		}	
		
		else if (index % 2 == 0)
			result.setBackground(new Color(248, 248, 224));
		else
			result.setBackground(new Color(224, 248, 224));
		
		String text2 = "<span color=\"" + color + "\"><em>" + user.status + "</em></span>";
		
		result.setText(text + text2);
		
		return result;
	}
}