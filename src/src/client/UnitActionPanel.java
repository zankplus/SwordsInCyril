package client;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import fftadata.ActiveUnit;
import fftadata.FFTACommand;
import fftadata.FFTAEquip;
import fftadata.FFTAJob;
import fftadata.FFTASkill;
import fftadata.FFTASupport;
import zank.ZankMessage;

import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

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
	private SkillPanel skillPanel1, skillPanel2, alchItemPanel;
	boolean unitHasMoved, unitHasActed, sendMove;
	private JPanel fightPanel;
	private JButton btnFightCancel;
	private JLabel lblClickTheUnit;
	String prevCard;
	
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
				prevCard = "Act";
				showSkillUsePanel(gp.units[gp.currentUnit].getFightSkill());
			}
		});
		btnFight.setPreferredSize(new Dimension(112, 23));
		actInnerPanel.add(btnFight, BorderLayout.CENTER);
		
		actSkillsetPanel = new JPanel();
		actSkillsetPanel.setPreferredSize(new Dimension(100, 10));
		actInnerPanel.add(actSkillsetPanel, BorderLayout.EAST);
		actSkillsetPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
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
		/*skillPanel = new JPanel();
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
		skillInnerPanel.add(skillList, BorderLayout.CENTER);*/
		
		
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
		UnitActionPanel thisRef = this;	// reference to 'this', which is hidden from inside of action listener definitions
		
		// Wipe the old actSkillsetPanel
		actSkillsetPanel.removeAll();
		
		// Add first skillset button
		btnSkillset_1 = new JButton("" + au.unit.job.command);
		actSkillsetPanel.add(btnSkillset_1);
		btnSkillset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				prevCard = "Skillset 1";
				cl.show(thisRef, "Skillset 1");
			}
		});
		add(new SkillPanel(au.unit.job.command), "Skillset 1");
		
		// Add second skillset button
		if (au.unit.secondary != FFTACommand.NONE)
		{
			btnSkillset_2 = new JButton("" + au.unit.secondary);
			actSkillsetPanel.add(btnSkillset_2);
			btnSkillset_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					prevCard = "Skillset 2";
					cl.show(thisRef, "Skillset 2");
				}
			});
			add(new SkillPanel(au.unit.secondary), "Skillset 2");
		}
		
		// Add Item skillset button, if the user is an alchemist
		if (au.unit.job == FFTAJob.ALCHEMIST && au.unit.secondary != FFTACommand.ITEM)
		{
			btnItem = new JButton("Item");
			actSkillsetPanel.add(btnItem);
			btnItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					prevCard = "AlchItem";
					cl.show(thisRef,  "AlchItem");
				}
			});
			
			add(new SkillPanel(FFTACommand.ITEM), "AlchItem");
		}
		actSkillsetPanel.revalidate();
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
	
	public void showPrevPanel()
	{
		cl.show(this, prevCard);
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

	public void showSkillUsePanel(FFTASkill sk)
	{
		gp.beginTargetingMode(sk);
		cl.show(this, "Fight");
	}
	
	public void cancelFight()
	{
		gp.cancelMovementMode();	// Properly we're not even IN movement mode, but since this just clears the highlighted
		gp.selectTile();
		showPrevPanel();				// tiles and sets the map panel's mode to 0, it serves our purpose here
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
			
			// Send the action
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
	
	private class SkillPanel extends JPanel
	{
		public SkillPanel(FFTACommand skillset)
		{
			setLayout(new BorderLayout(0, 0));
			
			// Initialize 'Cancel' button 
			JButton btnSkillCancel = new JButton("Cancel Skill");
			btnSkillCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showActPanel();
				}
			});
			add(btnSkillCancel, BorderLayout.SOUTH);
			
			// Initialize inner panel (for skill list and select button)
			JPanel skillInnerPanel = new JPanel();add(skillInnerPanel, BorderLayout.CENTER);
			skillInnerPanel.setLayout(new BorderLayout(0, 0));
			add(skillInnerPanel, BorderLayout.CENTER);
		
			// Initialize scroll pane
			JScrollPane skillScrollPane = new JScrollPane();
			skillScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			// Initialize skill list and set its cell renderer
			JList<FFTASkill> skillList = new JList<FFTASkill>();
			skillList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			class SkillCellRenderer implements ListCellRenderer<FFTASkill>
			{

				@Override
				public Component getListCellRendererComponent(JList<? extends FFTASkill> list, FFTASkill sk, int index, boolean isSelected, boolean hasCellFocus)
				{
					String label = sk.NAME;
					
					int mpCost = sk.MP_COST;
					if (au.unit.support == FFTASupport.HALF_MP)
						mpCost /= 2;
					else if (au.unit.support == FFTASupport.TURBO_MP)
						mpCost *= 2;
					
					if (sk.MP_COST > 0)
						label += " (" + mpCost + " MP)";

					if (!sk.IMPLEMENTED)
						label = "<html><strike>" + label;
					
					JLabel result = new JLabel(label); 
					result.setBorder(new EmptyBorder(1, 2, 1, 0));
					result.setOpaque(true);
					
					result.setEnabled(FFTASkill.canUseSkill(sk, au));

					
					if (isSelected)
					{
			            result.setBackground(Color.BLUE);
			            result.setForeground(Color.WHITE);
			        }
					else {
			            result.setBackground(Color.WHITE);
			            result.setForeground(Color.BLACK);
			        }
					
					return result;
				}

			}
			skillList.setCellRenderer(new SkillCellRenderer());
			
			// Create and populate list model and assign it to the list
			FFTASkill[] skills = skillset.SKILLS;
			DefaultListModel<FFTASkill> skListModel = new DefaultListModel<FFTASkill>();
			for (FFTASkill sk : skills)
				skListModel.addElement(sk);
			skillList.setModel(skListModel);
			
			// Add the list to the scroll pane and the scroll pane to the panel
			skillInnerPanel.add(skillScrollPane, BorderLayout.CENTER);
			skillScrollPane.add(skillList);
			skillScrollPane.setViewportView(skillList);
			
			// Initialize 'Select' button
			JButton btnSelect = new JButton("Select");
			btnSelect.setPreferredSize(new Dimension(75, 1));
			btnSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					FFTASkill sk = skillList.getSelectedValue();
					if (sk != null && FFTASkill.canUseSkill(sk, au))
					{
						showSkillUsePanel(sk);
					}
				}
			});
			skillInnerPanel.add(btnSelect, BorderLayout.EAST);
		}
	}
}