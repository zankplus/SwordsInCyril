package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import zank.ZankMessage;
import zank.ZankMessageType;
import zank.ZankUser;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class LoginWindow extends JFrame {

	ZankClient client;
	private JPanel connectPane, connectingPane;
	
	protected ZankUser zUser;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton btnLogin;
	private JLabel statusLabel;
	private BufferedImage bg;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginWindow frame = new LoginWindow(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LoginWindow(ZankClient client)
	{
		this.client = client;
		
		// Set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ImageIcon img = new ImageIcon("resources/misc/appicon2.png");
		setIconImage(img.getImage());
		
		bg = null;
		try {
			bg = ImageIO.read(new File("resources/misc/cyril.png"));
		} catch (IOException e) { System.out.println("oops"); }
		
		connectPane = new JPanel(null) {
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(bg, 0, 0, null);
			}
		};
		connectPane.setBackground(new Color(255, 255, 255));
		connectPane.setLayout(null);
		
		
		
		
		// Adjust window		
		JLabel lblTitle = new JLabel("Swords in Cyril");
		lblTitle.setBounds(0, 11, 282, 33);
		lblTitle.setForeground(new Color(128, 0, 64));
		lblTitle.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		connectPane.add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("an FFTA battle simulator");
		lblSubtitle.setBounds(10, 43, 262, 16);
		lblSubtitle.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSubtitle.setForeground(new Color(128, 0, 64));
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		connectPane.add(lblSubtitle);
		
		usernameField = new JTextField("fedoratipper" + Integer.toString((int) (Math.random() * 10000)));
		usernameField.setBounds(86, 113, 124, 20);
		connectPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(26, 116, 56, 16);
		lblUsername.setForeground(new Color(128, 0, 64));
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		connectPane.add(lblUsername);
		
		passwordField = new JTextField();
		passwordField.setBounds(86, 144, 124, 20);
		passwordField.setText("(no pw required yet)");
		passwordField.setEnabled(false);
		passwordField.setColumns(10);
		connectPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 147, 52, 16);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword.setForeground(new Color(128, 0, 64));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		connectPane.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setMaximumSize(new Dimension(90, 23));
		btnLogin.setPreferredSize(new Dimension(90, 23));
		btnLogin.setBounds(86, 175, 105, 23);
		btnLogin.setOpaque(false);
		getRootPane().setDefaultButton(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (usernameField.getText().indexOf(" ") >= 0)
				{
					statusLabel.setText("nicks must not contain spaces");
					statusLabel.setForeground(Color.BLACK);
				}
				else if (usernameField.getText().length() < 1)
				{
					statusLabel.setText("nick too short");
					statusLabel.setForeground(Color.BLACK);
				}
				else if (usernameField.getText().length() > 64)
				{
					statusLabel.setText("nick too long");
					statusLabel.setForeground(Color.BLACK);
				}
				else
				{
					btnLogin.setEnabled(false);
					zUser = new ZankUser(usernameField.getText());
					statusLabel.setText("connecting to server...");
					statusLabel.setForeground(Color.BLACK);
					usernameField.setEnabled(false);
					client.launchSocketMonitor();
				}
			}
		});
		connectPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setMaximumSize(new Dimension(90, 23));
		btnRegister.setPreferredSize(new Dimension(90, 23));
		btnRegister.setBounds(86, 209, 105, 23);
		btnRegister.setOpaque(false);
		btnRegister.setEnabled(false);
		connectPane.add(btnRegister);
		
		JButton btnClanBuilder = new JButton("Clan Builder");
		btnClanBuilder.setBounds(86, 276, 105, 23);
		btnClanBuilder.setOpaque(false);
		btnClanBuilder.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e)
			{
				client.launchClanBuilder();
			}
		});
		connectPane.add(btnClanBuilder);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(10, 86, 262, 16);
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		connectPane.add(statusLabel);
		
		setContentPane(connectPane);
		setSize(288, 384);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);		
	}
	
	
	public void connectSuccess()
	{
		statusLabel.setText("connection established!");
		statusLabel.setForeground(Color.BLUE);
		try
		{
			client.zu = new ZankUser(usernameField.getText());
			client.chatWindow = new ChatWindow(client);
			sendLogin();
		}
		catch (IOException e) { e.printStackTrace(); System.exit(0); }
	}
	
	public void connectFailed()
	{
		statusLabel.setText("connection failed! server must be down.");
		statusLabel.setForeground(Color.RED);
		usernameField.setEnabled(true);
		btnLogin.setEnabled(true);
	}
	
	public void sendLogin() throws IOException
	{
		ZankMessage msg = new ZankMessage(ZankMessageType.LOGIN, zUser.username, null);
		
		synchronized(client.out)
		{
			client.out.writeObject(msg);
			client.out.flush();
		}
			
		System.out.println("OUT:\t" + msg);
	}
}
