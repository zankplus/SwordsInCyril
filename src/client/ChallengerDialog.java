package client;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class ChallengerDialog extends JDialog
	{
		private final JPanel contentPanel = new JPanel();
		Socket socket;
		String user; 
			
		public ChallengerDialog(String user, ZankClient gui)
		{
			this.user = user;
			setModal(true);
			
			setBounds(100, 100, 200, 125);
			setLocationRelativeTo(gui.mainFrame);
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
						gui.sendChallenge(user);
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