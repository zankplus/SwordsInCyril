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
import fftadata.Morph;
import fftadata.StatusEffect;
import fftadata.Targeting;
import zank.ZankMessage;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
	EngagementWindow window;
	ActiveUnit au;
	
	private JButton btnAct, btnWait, btnMoveCancel, btnMove, btnActCancel, btnWaitCancel,
						btnNe, btnNw, btnSe, btnSw, btnFight, btnSkillset_1, btnSkillset_2,
						btnItem, btnSkillCancel, btnOk;
	private JPanel blankPanel, actionsPanel, movePanel, waitPanel, directionsPanel, actPanel,
		actInnerPanel, actSkillsetPanel, skillPanel, skillInnerPanel, doublecastPanel, morphPanel,
		unmorphConfirmPanel;
	private JLabel lblMoveInstruction; 
	boolean unitHasMoved, unitHasActed, sendMove;
	private JPanel fightPanel;
	private JButton btnFightCancel;
	private JLabel lblClickTheUnit;
	SkillPanel dcPanel;
	int doublecastMode;
	String prevCard;
	Engagement game;
	JButton btnFightConfirm, btnUnmorph;
	private String actTarget;
	private String fightDesc;
	

	public UnitActionPanel(EngagementWindow window)
	{
		this.window = window;
		game = window.game;
		
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
		
		actTarget = "Act";
		
		btnAct = new JButton("Act");
		btnAct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showPanel(actTarget);
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
		
		btnUnmorph = new JButton("Unmorph");
		btnUnmorph.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showPanel("Unmorph Confirm");
			}
		});
		
		// Unmorph panel
		unmorphConfirmPanel = new JPanel(new BorderLayout());
		JButton btnUnmorphConfirm = new JButton("Confirm unmorph");
		btnUnmorphConfirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					au.morph = Morph.NONE;
					game.sendUnmorph(au.id);
				}
				catch (IOException ex) { ex.printStackTrace(); }
				cancelMorphUI();
				showPanel("Actions");
			}
		});
		
		JButton btnUnmorphCancel = new JButton("Cancel");
		btnUnmorphCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				showPanel("Actions");
			}
		});
		unmorphConfirmPanel.add(btnUnmorphConfirm, BorderLayout.CENTER);
		unmorphConfirmPanel.add(btnUnmorphCancel, BorderLayout.SOUTH);
		
		add(unmorphConfirmPanel, "Unmorph Confirm");
		
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
		
		fightDesc = "";
		btnFight = new JButton("<html><strong>Fight");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevCard = "Act";
				showSkillUsePanel(game.currentUnit().getFightSkill(), 0);
				window.skillDesc.setText(fightDesc);
				window.deck.show(window.previewDeck, "Blank");
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
		
		btnFightConfirm = new JButton("Confirm");
		btnFightConfirm.setEnabled(false);
		fightPanel.add(btnFightConfirm, BorderLayout.CENTER);
		btnFightConfirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.mapPanel.selectTarget(2);
			}
		});
		
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
		// fightPanel.add(lblClickTheUnit, BorderLayout.CENTER);
			
		// Wait Panel: Lets the player select a direction for the unit to face before before ending their turn
		waitPanel = new JPanel();
		waitPanel.setLayout(new BorderLayout(0, 0));
		add(waitPanel, "Wait");
		
		btnWaitCancel = new JButton("Cancel");
		waitPanel.add(btnWaitCancel, BorderLayout.SOUTH);
		btnWaitCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(unitHasActed && unitHasMoved)
				{
					// undo the movement
					sendMove = false;
					window.undoMovement();
					unitHasMoved = false;
					
					// assume start of new movement
					showMovePanel();
					
				}
				else
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
		
		if (au.morph != Morph.NONE)
		{
			actionsPanel.setLayout(new GridLayout(4, 1, 0, 0));
			actionsPanel.remove(btnWait);
			actionsPanel.add(btnUnmorph);
			actionsPanel.add(btnWait);
		}
		else
		{
			cancelMorphUI();
		}
		
		// Wipe the old actSkillsetPanel
		actSkillsetPanel.removeAll();
		
		// Set fightdesc
		fightDesc = au.getFightSkill().getDesc();
		
		// Add first skillset button
		btnSkillset_1 = new JButton("" + au.unit.job.command);
		btnSkillset_1.setMargin(new Insets(2, 5, 2, 5));
		actSkillsetPanel.add(btnSkillset_1);
		btnSkillset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				prevCard = "Skillset 1";
				cl.show(thisRef, "Skillset 1");
			}
		});
		add(new SkillPanel(au.unit.job.command.SKILLS, "Act"), "Skillset 1");
		if (au.status[StatusEffect.FROG.ordinal()] > 0 || au.status[StatusEffect.ADDLE.ordinal()] > 0)
			btnSkillset_1.setEnabled(false);
		else
			btnSkillset_1.setEnabled(true);
		
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
			
			if ((au.status[StatusEffect.FROG.ordinal()] > 0 && au.unit.secondary != FFTACommand.ITEM) ||
					 au.status[StatusEffect.ADDLE.ordinal()] > 0)
					btnSkillset_2.setEnabled(false);
				else
					btnSkillset_2.setEnabled(true);
			
			add(new SkillPanel(au.unit.secondary.SKILLS, "Act"), "Skillset 2");
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
			if (au.status[StatusEffect.ADDLE.ordinal()] > 0)
				btnItem.setEnabled(false);
			else
				btnItem.setEnabled(true);
			
			add(new SkillPanel(FFTACommand.ITEM.SKILLS, "Act"), "AlchItem");
		}
		
		// Add a morph panel, if the unit is morphed
		if (au.morph != Morph.NONE)
		{
			add(new SkillPanel(au.morph.skillset.SKILLS, "Actions"), "Morph");
			btnAct.setText("Act (" + au.morph.name + ")");
			actTarget = "Morph";
		}
		
		// Add doublecast panel
		if (au.unit.job == FFTAJob.RED_MAGE || au.unit.secondary == FFTACommand.RED_MAGIC)
		{
			// Identify magic skillsets
			FFTASkill[] second = new FFTASkill[0];
			if (au.unit.job.command == FFTACommand.WHITE_MAGIC || 
				au.unit.job.command == FFTACommand.SPIRIT_MAGIC || 
				au.unit.job.command == FFTACommand.SUMMON_MAGIC)
			{
				second = au.unit.job.command.SKILLS;
			}
			else if (au.unit.secondary== FFTACommand.WHITE_MAGIC || 
				au.unit.secondary == FFTACommand.SPIRIT_MAGIC || 
				au.unit.secondary == FFTACommand.SUMMON_MAGIC)
			{
				second = au.unit.secondary.SKILLS;
			}
			
			FFTASkill[] dcSkills = new FFTASkill[FFTACommand.RED_MAGIC.SKILLS.length + second.length - 1];
			for (int i = 0; i < FFTACommand.RED_MAGIC.SKILLS.length - 1; i++)
				dcSkills[i] = FFTACommand.RED_MAGIC.SKILLS[i];
			for (int i = 0; i < second.length; i++)
				dcSkills[i + FFTACommand.RED_MAGIC.SKILLS.length - 1] = second[i];
			
			String prev;
			if (au.unit.job == FFTAJob.RED_MAGE)
				prev = "Skillset 1";
			else
				prev = "Skillset 2";
			dcPanel = new SkillPanel(dcSkills, prev);
			add(dcPanel, "Doublecast");
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
		if (unitHasActed || au.status[StatusEffect.DISABLE.ordinal()] > 0)
			btnAct.setEnabled(false);
		else
			btnAct.setEnabled(true);
		
		if (unitHasMoved)
		{
			btnMove.setText("Undo move");
			btnMove.setEnabled(true);
		}
		else if (au.status[StatusEffect.IMMOBILIZE.ordinal()] > 0)
		{
			btnMove.setText("Move");
			btnMove.setEnabled(false);
		}
		else
		{
			btnMove.setText("Move");
			btnMove.setEnabled(true);
		}
		
		cl.show(this, "Actions");
	}
	
	public void hideActionPanel()
	{
		cl.show(this, "Blank");
	}
	
	public void showMovePanel()
	{
		game.window.beginMovementMode();
		cl.show(this, "Move");
	}
	
	public void showPanel(String panelName)
	{
		cl.show(this, panelName);
	}
	
	public void showPrevPanel()
	{
		cl.show(this, prevCard);
	}
	
	public void undoMove()
	{
		sendMove = false;
		window.undoMovement();
		unitHasMoved = false;
		showActionsPanel();
	}
	
	public void cancelMovement()
	{
		window.cancelMovementMode();
		showActionsPanel();
	}

	public void completeMovement()
	{
		sendMove = true;
		unitHasMoved = true;
		if (unitHasActed)
		{
			showWaitPanel();
		}
		else
			showActionsPanel();
	}

	public void showSkillUsePanel(FFTASkill sk, int doublecastMode)
	{
		window.beginTargetingMode(sk, doublecastMode);
		btnFightConfirm.setText("Execute " + window.selectedSkill.NAME);
		cl.show(this, "Fight");
	}
	
	public void cancelFight()
	{
		window.cancelMovementMode();	// Properly we're not even IN movement mode, but since this just 
										// clears the highlighted tiles and sets the map panel's mode to 0,
		window.selectTile();			// it serves our purpose here
		
		if (doublecastMode > 0)
			showPanel("Doublecast");
		else if (au.morph != Morph.NONE)
			showPanel("Morph");
		else
			showPrevPanel();
		
		if (prevCard.equals("Act"))
			window.skillDesc.setText("");
	}
	
	
	
	public void finishAct()
	{
		window.skillDesc.setText("");
		if (au.cannotAct())
		{
			doWait(au.dir);
		}
		else if (unitHasMoved)
		{
			btnWaitCancel.setEnabled(false);
			showWaitPanel();
		}
		else
			showActionsPanel();
	}
	
	public void showWaitPanel()
	{
//		if (unitHasMoved && unitHasActed)
//			btnWaitCancel.setEnabled(false);
		
		cl.show(this,  "Wait");
	}
	
	public void doWait(int dir)
	{
		try
		{
			if (sendMove)
			{
				System.out.println("sending move");
				game.sendMove();
			}
			game.sendWait(dir);
			System.out.println("sending wait");
			
			btnNe.setEnabled(false);
			btnNw.setEnabled(false);
			btnSw.setEnabled(false);
			btnSe.setEnabled(false);
			btnWaitCancel.setEnabled(false);
			
//			hideActionPanel();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void cancelMorphUI()
	{
		actionsPanel.setLayout(new GridLayout(3, 1, 0, 0));
		actionsPanel.remove(btnUnmorph);
		btnAct.setText("Act");
		actTarget = "Act";
		actionsPanel.revalidate();
	}
	
	class SkillPanel extends JPanel
	{
		public JButton btnSkillCancel;
		
		public SkillPanel(FFTASkill[] skills, String prevPanel)
		{
			doublecastMode = 0;
			setLayout(new BorderLayout(0, 0));
			
			// Initialize 'Cancel' button 
			btnSkillCancel = new JButton("Cancel Skill");
			btnSkillCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if (doublecastMode < 2)
					{
						showPanel(prevPanel);
						doublecastMode = 0;
						btnSkillCancel.setText("Cancel Skill");
						window.mapPanel.selectTile(window.mapPanel.selectedTile);
						window.skillDesc.setText("");
					}
					else
					{
						showPanel("Doublecast");
						doublecastMode = 1;
						btnSkillCancel.setText("Cancel Doublecast");
						revalidate();
						repaint();
					}
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
					
					if (doublecastMode == 2)
					{
						int offset = window.dcSkill.MP_COST;
						if (au.unit.support == FFTASupport.HALF_MP)
							offset /= 2;
						else if (au.unit.support == FFTASupport.TURBO_MP)
							offset *= 2;
						
						result.setEnabled(FFTASkill.canUseSkill(sk, au, offset));
					}
					else
						result.setEnabled(FFTASkill.canUseSkill(sk, au, 0));

					
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
			
			ListSelectionModel model = skillList.getSelectionModel();
			model.addListSelectionListener(new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent arg0) 
				{
					game.window.skillDesc.setText(skillList.getSelectedValue().getDesc());
					game.window.deck.show(game.window.previewDeck, "Blank");
				}
				
			});
			
			// Create and populate list model and assign it to the list
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
					int offset = 0;
					if (doublecastMode == 2)
					{
						offset = window.dcSkill.MP_COST;
						if (au.unit.support == FFTASupport.HALF_MP)
							offset /= 2;
						else if (au.unit.support == FFTASupport.TURBO_MP)
							offset *= 2;
					}
					
					if (sk != null && FFTASkill.canUseSkill(sk, au, offset))
					{
						if (sk == FFTASkill.DOUBLECAST)
						{
							dcPanel.btnSkillCancel.setText("Cancel Doublecast");
							showPanel("Doublecast");
							doublecastMode = 1;
							System.out.println("boops");
							revalidate();
						}
						else
						{
							showSkillUsePanel(sk, doublecastMode);
							if (sk.TARGETING == Targeting.SELF_CENTER || sk.TARGETING == Targeting.ALL_ENEMIES ||
								sk.TARGETING == Targeting.ALL || sk.TARGETING == Targeting.SAME_LEVEL_DIGIT)
							{
								// window.selectTile();
								window.selectTarget(0);
							}
						}
					}
				}
			});
			skillInnerPanel.add(btnSelect, BorderLayout.EAST);
		}
	}
}