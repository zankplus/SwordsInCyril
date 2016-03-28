package client;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;

import fftadata.ActiveUnit;
import zank.ZankMessage;

import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.CardLayout;

public class UnitActionPanel extends JPanel
{
	JPanel actionsPanel;
	JPanel blankPanel;
	CardLayout cl;
	private JButton btnWait;
	private JButton btnAct;
	private JButton btnMove;
	private JButton btnActMove;
	EngagementWindow ew;
	
	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapPanelTest frame = new MapPanelTest();
					frame.getContentPane().add(new UnitActionPanel(null));
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UnitActionPanel(EngagementWindow ew)
	{
		this.ew = ew;
		
		setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPreferredSize(new Dimension(240, 162));
		
		cl = new CardLayout();
		setLayout(cl);
		
		actionsPanel = new JPanel();
		blankPanel = new JPanel();
		add(blankPanel);
		add(actionsPanel);
		
		TOListener tol = new TOListener();
		
		actionsPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		btnWait = new JButton("Wait");
		btnWait.addActionListener(tol);
		actionsPanel.add(btnWait);
		
		btnAct = new JButton("Act");
		btnAct.addActionListener(tol);
		actionsPanel.add(btnAct);
		
		btnMove = new JButton("Move");
		btnMove.addActionListener(tol);
		actionsPanel.add(btnMove);
		
		btnActMove = new JButton("Act & Move");
		btnActMove.addActionListener(tol);
		actionsPanel.add(btnActMove);
	}
	
	public void yourTurn()
	{
		cl.last(this);
	}
	
	public void enemyTurn()
	{
		cl.first(this);
	}
	
	private class TOListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
				int ct = 0;
				
				if (e.getSource() == btnWait)
					ct = 500;
				
				else if (e.getSource() == btnAct)
					ct = 700;
				
				else if (e.getSource() == btnMove)
					ct = 800;
				
				else if (e.getSource() == btnActMove)
					ct = 1000;
				
				if (ct > 0)
					ew.sendTurnTest(ct);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
