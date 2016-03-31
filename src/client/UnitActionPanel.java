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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UnitActionPanel extends JPanel
{
	JPanel actionsPanel;
	JPanel blankPanel;
	CardLayout cl;
	EngagementWindow ew;
	private JButton btnAct;
	private JButton btnWait;
	private JPanel movePanel;
	private JButton btnMoveCancel;
	private JLabel lblMoveInstruction;
	private JButton btnQuickMove;
	private JButton btnMove;
	ActionListener movelistener, unmoveListener, actionListener;
	boolean unitHasMoved, unitHasActed;
	private JPanel actPanel;
	private JButton btnActCancel;
	private JButton btnQuickAct;
	private JPanel waitPanel;
	private JButton btnWaitCancel;
	private JPanel directionsPanel;
	private JButton btnNe;
	private JButton btnNw;
	private JButton btnSe;
	private JButton btnSw;
	
	
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
		add(blankPanel, "Blank");
		
		waitPanel = new JPanel();
		add(waitPanel, "Wait");
		waitPanel.setLayout(new BorderLayout(0, 0));
		
		btnWaitCancel = new JButton("Cancel");
		waitPanel.add(btnWaitCancel, BorderLayout.SOUTH);
		btnWaitCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				yourTurn();
			}
		});
		
		directionsPanel = new JPanel();
		waitPanel.add(directionsPanel, BorderLayout.CENTER);
		directionsPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		btnNe = new JButton("NE");
		directionsPanel.add(btnNe);
		
		btnNw = new JButton("NW");
		directionsPanel.add(btnNw);
		
		btnSe = new JButton("SE");
		directionsPanel.add(btnSe);
		
		btnSw = new JButton("SW");
		directionsPanel.add(btnSw);
		
		actPanel = new JPanel();
		add(actPanel, "Act");
		actPanel.setLayout(new BorderLayout(0, 0));
		
		btnActCancel = new JButton("Cancel");
		actPanel.add(btnActCancel, BorderLayout.SOUTH);
		btnActCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				yourTurn();
			}
		});
		
		btnQuickAct = new JButton("(Quick Act)");
		actPanel.add(btnQuickAct, BorderLayout.CENTER);
		btnQuickAct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				unitHasActed = true;
				if (unitHasMoved)
				{
					btnWaitCancel.setEnabled(false);
					doWait();
				}
				else
					yourTurn();
			}
		});
		add(actionsPanel, "Actions");
		actionsPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		
		
		ActionListener moveListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if (!unitHasMoved)
					move();
				
				else
					undoMove();
			}
		};
		
		
		btnMove = new JButton("Move");
		btnMove.addActionListener(moveListener);
		
		
		actionsPanel.add(btnMove);
		
		btnAct = new JButton("Act");
		actionsPanel.add(btnAct);
		btnAct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				act();
			}
		});
		
		btnWait = new JButton("Wait");
		btnWait.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				doWait();
			}
		});
		actionsPanel.add(btnWait);
		
		
		movePanel = new JPanel();
		add(movePanel, "Move");
		movePanel.setLayout(new BorderLayout(0, 0));
		
		btnMoveCancel = new JButton("Cancel");
		btnMoveCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				yourTurn();
			}
		});
		movePanel.add(btnMoveCancel, BorderLayout.SOUTH);
		
		lblMoveInstruction = new JLabel("Click the tile to which to move.");
		lblMoveInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		movePanel.add(lblMoveInstruction, BorderLayout.CENTER);
		
		btnQuickMove = new JButton("(Quick Move)");
		movePanel.add(btnQuickMove, BorderLayout.NORTH);
		btnQuickMove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				unitHasMoved = true;
				if (unitHasActed)
				{
					btnWaitCancel.setEnabled(false);
					doWait();
				}
				else
					yourTurn();
			}
		});
		
		yourTurn();
	}
	
	public void yourTurn()
	{
		if (unitHasActed)
			btnAct.setEnabled(false);
		else
			btnAct.setEnabled(true);
		
		if (unitHasMoved)
			btnMove.setText("Undo move");
		else
			btnMove.setText("Move");
		
		cl.show(this, "Actions");
	}
	
	public void enemyTurn()
	{
		cl.show(this, "Blank");
	}
	
	public void move()
	{
		cl.show(this, "Move");
	}
	
	public void act()
	{
		cl.show(this, "Act");
	}
	
	public void undoMove()
	{
		unitHasMoved = false;
		yourTurn();
	}
	
	public void doWait()
	{
		cl.show(this,  "Wait");
	}
}
