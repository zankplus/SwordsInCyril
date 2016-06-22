package client;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;

import fftadata.ActiveUnit;
import fftadata.FFTASkill;
import zank.ZankMessage;

import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

public class UnitActionPanel extends JPanel
{
	CardLayout cl;
	EngagementWindow ew;
	GamePanel gp;
	ActiveUnit au;
	
	private JButton btnAct, btnWait, btnMoveCancel, btnMove, btnActCancel, btnWaitCancel,
						btnNe, btnNw, btnSe, btnSw, btnFight, btnSkillset_1, btnSkillset_2,
						btnItem, btnSkillCancel, btnOk;
	private JPanel blankPanel, actionsPanel, movePanel, waitPanel, directionsPanel, actPanel,
		actInnerPanel, actSkillsetPanel, skillPanel, skillInnerPanel;
	private JLabel lblMoveInstruction; 
	private JList skillList;
	boolean unitHasMoved, unitHasActed, sendMove;
	private JPanel fightPanel;
	private JButton btnFightCancel;
	private JLabel lblClickTheUnit;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapPanelTest frame = new MapPanelTest();
					UnitActionPanel uap = new UnitActionPanel(null);
					uap.showActionsPanel();
					frame.getContentPane().add(uap);
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
		gp = ew.gamePanel;
		
		sendMove = false;
		
		setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPreferredSize(new Dimension(240, 162));
		
		cl = new CardLayout();
		setLayout(cl);
		
		ActionListener facingListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int dir = 0;
				if (e.getSource() ==btnNe)
					dir = 1;
				else if (e.getSource() ==btnNw)
					dir = 2;
				else if (e.getSource() ==btnSw)
					dir = 3;
				else if (e.getSource() ==btnSe)
					dir = 4;
				
				if (dir != 0)
					doWait(dir);
			}
		};
		
		
		// Blank Panel: An empty panel displayed during the other player's turns
		blankPanel = new JPanel();
		add(blankPanel, "Blank");
		
		
		// Actions Panel: Main menu containing Move, Act and Wait options
		actionsPanel = new JPanel();
		actionsPanel.setLayout(new GridLayout(3, 1, 0, 0));
		add(actionsPanel, "Actions");
		
		btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if (!unitHasMoved)
					showMovePanel();
				else
					undoMove();
			}
		});
		actionsPanel.add(btnMove);
		
		btnAct = new JButton("Act");
		btnAct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showActPanel();
			}
		});
		actionsPanel.add(btnAct);
		
		btnWait = new JButton("Wait");
		btnWait.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showWaitPanel();
			}
		});
		actionsPanel.add(btnWait);
		
		
		// Move Panel: Prompts the player to click a tile on the map to move to
		movePanel = new JPanel();
		movePanel.setLayout(new BorderLayout(0, 0));
		add(movePanel, "Move");
		
		btnMoveCancel = new JButton("Cancel");
		btnMoveCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cancelMovement();
			}
		});
		movePanel.add(btnMoveCancel, BorderLayout.SOUTH);
		
		lblMoveInstruction = new JLabel("<html>Double-click the tile to which to move.");
		lblMoveInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		movePanel.add(lblMoveInstruction, BorderLayout.CENTER);
		
		
		// Act Panel
		actPanel = new JPanel();
		actPanel.setLayout(new BorderLayout(0, 0));
		add(actPanel, "Act");
		
		btnActCancel = new JButton("Cancel");
		actPanel.add(btnActCancel, BorderLayout.SOUTH);
		btnActCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showActionsPanel();
			}
		});
		
		actInnerPanel = new JPanel();
		actPanel.add(actInnerPanel, BorderLayout.CENTER);
		actInnerPanel.setLayout(new BorderLayout(0, 0));
		
		btnFight = new JButton("<html><strong>Fight");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFightPanel();
			}
		});
		btnFight.setPreferredSize(new Dimension(112, 23));
		actInnerPanel.add(btnFight, BorderLayout.WEST);
		
		actSkillsetPanel = new JPanel();
		actInnerPanel.add(actSkillsetPanel, BorderLayout.CENTER);
		actSkillsetPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnSkillset_1 = new JButton("Skillset 1");
		actSkillsetPanel.add(btnSkillset_1);
		
		btnSkillset_2 = new JButton("Skillset 2");
		actSkillsetPanel.add(btnSkillset_2);
		
		btnItem = new JButton("Item");
		btnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		actSkillsetPanel.add(btnItem);
		
		
		
		// Fight Panel
		fightPanel = new JPanel();
		add(fightPanel, "Fight");
		fightPanel.setLayout(new BorderLayout(0, 0));
		
		btnFightCancel = new JButton("Cancel");
		fightPanel.add(btnFightCancel, BorderLayout.SOUTH);
		btnFightCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cancelFight();
			}
		});
		
		lblClickTheUnit = new JLabel("<html>Click the unit to target.<br>Double-click to confirm action.");
		lblClickTheUnit.setHorizontalAlignment(SwingConstants.CENTER);
		fightPanel.add(lblClickTheUnit, BorderLayout.CENTER);
		
		
		// Skill Panel
		skillPanel = new JPanel();
		add(skillPanel, "Skill");
		skillPanel.setLayout(new BorderLayout(0, 0));
		
		btnSkillCancel = new JButton("Cancel Skill");
		skillPanel.add(btnSkillCancel, BorderLayout.SOUTH);
		
		skillInnerPanel = new JPanel();
		skillPanel.add(skillInnerPanel, BorderLayout.CENTER);
		skillInnerPanel.setLayout(new BorderLayout(0, 0));
		
		btnOk = new JButton("OK");
		skillInnerPanel.add(btnOk, BorderLayout.EAST);
		
		skillList = new JList();
		skillList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		skillInnerPanel.add(skillList, BorderLayout.CENTER);
		
		
		// Wait Panel: Lets the player select a direction for the unit to face before before ending their turn
		waitPanel = new JPanel();
		waitPanel.setLayout(new BorderLayout(0, 0));
		add(waitPanel, "Wait");
		
		btnWaitCancel = new JButton("Cancel");
		waitPanel.add(btnWaitCancel, BorderLayout.SOUTH);
		btnWaitCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showActionsPanel();
			}
		});
		
		directionsPanel = new JPanel();
		waitPanel.add(directionsPanel, BorderLayout.CENTER);
		directionsPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		btnNw = new JButton("NW");
		directionsPanel.add(btnNw);
		btnNw.addActionListener(facingListener);
		
		btnNe = new JButton("NE");
		directionsPanel.add(btnNe);
		btnNe.addActionListener(facingListener);
		
		btnSw = new JButton("SW");
		directionsPanel.add(btnSw);
		btnSw.addActionListener(facingListener);
		
		btnSe = new JButton("SE");
		directionsPanel.add(btnSe);
		btnSe.addActionListener(facingListener);
	}
	
	public void setUnit(ActiveUnit au)
	{
		this.au = au;
		
	}
	
	public void resetTurnVariables()
	{
		sendMove = false;
		unitHasMoved = false;
		unitHasActed = false;
		btnWaitCancel.setEnabled(true);
		btnNe.setEnabled(true);
		btnNw.setEnabled(true);
		btnSw.setEnabled(true);
		btnSe.setEnabled(true);
	}
	
	public void showActionsPanel()
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
	
	public void hideActionPanel()
	{
		cl.show(this, "Blank");
	}
	
	public void showMovePanel()
	{
		gp.beginMovementMode();
		cl.show(this, "Move");
	}
	
	public void showActPanel()
	{
		cl.show(this, "Act");
	}
	
	public void undoMove()
	{
		sendMove = false;
		gp.undoMovement();
		unitHasMoved = false;
		showActionsPanel();
	}
	
	public void cancelMovement()
	{
		gp.cancelMovementMode();
		showActionsPanel();
	}

	public void completeMovement()
	{
		sendMove = true;
		unitHasMoved = true;
		if (unitHasActed)
		{
			btnWaitCancel.setEnabled(false);
			showWaitPanel();
		}
		else
			showActionsPanel();
	}

	public void showFightPanel()
	{
		gp.beginTargetingMode(FFTASkill.FIGHT);
		cl.show(this, "Fight");
	}
	
	public void cancelFight()
	{
		gp.cancelMovementMode();	// Properly we're not even IN movement mode, but since this just clears the highlighted
		gp.selectTile();
		showActPanel();				// tiles and sets the map panel's mode to 0, it serves our purpose here
	}
	
	public void doAct(ArrayList<Integer> targets, FFTASkill sk, int x, int y)
	{
		unitHasActed = true;
		
		try
		{
			hideActionPanel();
			if (unitHasMoved)
			{
				ew.sendMove();
				sendMove = false;
			}
			
			// Clear away the tile highlights and return to the actions menu
			gp.cancelMovementMode();
			
			// Reselect the current unit to remind the active player that further action is required of them
			gp.selectTile(gp.map.mapData[gp.units[gp.currentUnit].x][gp.units[gp.currentUnit].y]);
			
			System.out.println("doAct: target = " + targets.get(0));
			ew.sendAction(targets, sk, x, y);
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void finishAct()
	{
		if (unitHasMoved)
			showWaitPanel();
		else
			showActionsPanel();
	}
	
	public void showWaitPanel()
	{
		if (unitHasMoved && unitHasActed)
			btnWaitCancel.setEnabled(false);
		
		cl.show(this,  "Wait");
	}
	
	public void doWait(int dir)
	{
		try
		{
			if (sendMove)
				ew.sendMove();
			ew.sendWait(dir);
			
			btnNe.setEnabled(false);
			btnNw.setEnabled(false);
			btnSw.setEnabled(false);
			btnSe.setEnabled(false);
			
//			hideActionPanel();
		}
		catch (IOException e) { e.printStackTrace(); }

	}
}
