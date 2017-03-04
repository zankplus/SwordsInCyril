package client;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTMLDocument;

import fftadata.ActiveUnit;
import fftadata.FFTASkill;
import fftadata.SkillEffectResult;
import fftadata.StatusEffect;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Insets;

import fftamap.*;

public class EngagementWindow extends JFrame
{
	Engagement game;
	
	private JPanel contentPane, previewDeck, blankUnitPreview, bottomPanel,
					damagePreviewPanel;
	private JTextPane chat;
	private JTextField chatEntry;
	
	JPanel gamePanel;
	private MapPanel mapPanel;
	private UnitActionPanel unitAction;
	
	private CardLayout deck;
	private UnitPreviewPanel[] previews;
	// TurnOrderPanel top;
	
	FFTASkill selectedSkill;
	
	EngagementWindowRosterPanel rosterPanel;

	private JPanel turnOrderPanel;
	public TurnOrderBoy wait, actOnly, moveOnly, moveAct;
	
	
	

	/**
	 * Create the frame.
	 */
	
	
	public EngagementWindow(Engagement game)
	{
		wait = new TurnOrderBoy("Wait");
		this.game = game;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Set up window
		ImageIcon img = new ImageIcon("resources/misc/appicon2.png");
		setIconImage(img.getImage());
		
		try
		{
			setTitle("[" + "///" + "] " + game.player.username + " vs " + game.opponent.username );
		} catch (NullPointerException e) { setTitle("Testin' mode"); }
		
		// Closing
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JFrame ew = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				closeEngagementWindow();
			}
		});
		
		
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(320, 10));
		contentPane.add(rightPanel, BorderLayout.CENTER);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		
		chat = new JTextPane();
		chat.setContentType("text/html");
		chat.setText("<html><body style=\"font-family:verdana; font-size:11pt\">");
		chat.setEditable(false);
		chat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(chat);
		
		chatEntry = new JTextField();
		rightPanel.add(chatEntry, BorderLayout.SOUTH);
		chatEntry.setColumns(10);
		chatEntry.setEnabled(true);
		chatEntry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s= chatEntry.getText();
				if (!s.equals(""))
				{
					try
					{
						game.sendChat(s);
					} catch (IOException e1) { e1.printStackTrace(); }
					chatEntry.setText("");
				}
			}
		});
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(null);
		leftPanel.setPreferredSize(new Dimension(560, 10));
		contentPane.add(leftPanel, BorderLayout.WEST);

		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		
		rosterPanel = new EngagementWindowRosterPanel(this);
		mapPanel = new MapPanel(this);
		
		turnOrderPanel = new JPanel();
		turnOrderPanel.setPreferredSize(new Dimension(96, 400));
		turnOrderPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		leftPanel.add(gamePanel, BorderLayout.CENTER);
		gamePanel.add(mapPanel, BorderLayout.CENTER);
		gamePanel.add(turnOrderPanel, BorderLayout.EAST);
		mapPanel.roster = rosterPanel.roster;
		leftPanel.add(gamePanel);
		
		
		damagePreviewPanel = null;
		
		beginPlacementMode();
		
		
	}
	
	public void beginPlacementMode()
	{
		gamePanel.add(rosterPanel, BorderLayout.SOUTH);
		mapPanel.beginPlacementMode();
	}
	
	public void beginGame(ArrayList<ActiveUnit> otherTeam)
	{
		gamePanel.remove(rosterPanel);
		bottomPanel = new JPanel(new BorderLayout());
		gamePanel.add(bottomPanel, BorderLayout.SOUTH);
		
		deck = new CardLayout();
		previewDeck = new JPanel(deck);
		
		blankUnitPreview = new JPanel();
		blankUnitPreview.setPreferredSize(new Dimension(320, 162));
		blankUnitPreview.setBorder(new TitledBorder(null, "Unit Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		previewDeck.add(blankUnitPreview, "Blank");
		
		bottomPanel.add(previewDeck, BorderLayout.WEST);
	
		unitAction = new UnitActionPanel(this);
		bottomPanel.add(unitAction, BorderLayout.EAST);
		
		// Set up other team's units
		mapPanel.beginGame(otherTeam);
		
		// Initialize turn order panel
		wait = new TurnOrderBoy("Wait");
		actOnly = new TurnOrderBoy("Act Only");
		moveOnly = new TurnOrderBoy("Move Only");
		moveAct = new TurnOrderBoy("Move & Act");
		
		turnOrderPanel.setLayout(new GridLayout(20, 1, 0, 0));

	}

	// update turn order display
	public void updateTurnOrder(int[] order)
	{
		turnOrderPanel.removeAll();
		for (int i = 0; i < order.length; i++)
		{
			if (order[i] >= 0)
				turnOrderPanel.add(new TurnOrderBoy(game.getUnits()[order[i]]));
			else if (order[i] == -5)
				turnOrderPanel.add(wait);
			else if (order[i] == -7)
				turnOrderPanel.add(actOnly);
			else if (order[i] == -8)
				turnOrderPanel.add(moveOnly);
			else if (order[i] == -10)
				turnOrderPanel.add(moveAct);
			else
				System.out.println("Notice: Invalid index given for turn order update");
		}
		
		turnOrderPanel.repaint();
	}
	
	public void beginTargetingMode(FFTASkill sk)
	{
		mapPanel.mode = 3;
		selectedSkill = sk;
		mapPanel.highlightTargetableTiles(sk);
	}
	
	public void updateSprite(ActiveUnit au)
	{
		mapPanel.updateSprite(au);
	}
	
	public void moveUnit(ActiveUnit au, FFTAMapTile dest)
	{
		mapPanel.moveUnit(au, dest);
	}
	
	public void hideUnitPreview()
	{
		deck.show(previewDeck, "Blank");
		revalidate();
	}
	
	public void showUnitPreview(ActiveUnit selectedUnit)
	{
		deck.show(previewDeck, String.valueOf(selectedUnit.id));
		revalidate();
	}
	
	public void updateUnitPreview(int unit)
	{
		previews[unit].updateStats();
	}
	
	public void showDamagePreview(ArrayList<ActiveUnit> targets)
	{
		previewDeck.remove(damagePreviewPanel);
		damagePreviewPanel = new DamagePreviewPanel(game.currentUnit(), targets, selectedSkill, game.getState());
		previewDeck.add(damagePreviewPanel, "Damage Preview");
		deck.last(previewDeck);
		revalidate();
	}
	
	public void selectTile(FFTAMapTile tile)
	{
		mapPanel.selectTile(tile);
	}
	
	public void selectTile()
	{
		mapPanel.selectTile(mapPanel.selectedTile);
	}
	
	public void commitAction(ArrayList<Integer> targets)
	{
		int x = mapPanel.selectedTile.x, y = mapPanel.selectedTile.y;
		unitAction.unitHasActed = true;
		
		try
		{
			unitAction.hideActionPanel();
			if (unitAction.unitHasMoved)
			{
				game.sendMove();
				unitAction.sendMove = false;
			}
			
			// Clear away the tile highlights and return to the actions menu
			cancelMovementMode();
			
			// Reselect the current unit to remind the active player that further action is required of them
			ActiveUnit au = game.currentUnit();
			selectTile(game.map.mapData[au.x][au.y]);
			
			// Send the action
			game.sendAction(au.id, selectedSkill, x, y);
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	// Calls UnitActionPanel's finishAct() method to bring up the appropriate menu to finish the current unit's turn
	public void finishAct()
	{
		unitAction.finishAct();
	}
	
	public void setupPreviews()
	{
		ActiveUnit[] units = game.getUnits();
		previews = new UnitPreviewPanel[units.length];
		for (int i = 0; i < units.length; i++)
		{
			previews[i] = new UnitPreviewPanel(units[i]);
			previewDeck.add(previews[i], String.valueOf(i));
		}
		UnitPreviewPanel.game = game;
		damagePreviewPanel = new JPanel();
		previewDeck.add(damagePreviewPanel, "Damage Preview");
	}
	
	public void startPlayerTurn()
	{
		unitAction.setUnit(game.currentUnit());
		unitAction.resetTurnVariables();
		unitAction.showActionsPanel();
	}
	
	public void startRivalTurn()
	{
		unitAction.hideActionPanel();
	}
	
	public void beginMovementMode()
	{
		mapPanel.mode = 2;
		mapPanel.highlightWalkableTiles();
	}
	
	public void cancelMovementMode()
	{
		mapPanel.mode = 0;
		mapPanel.removeHighlightedTiles();
	}
	
	public void finishMovementMode()
	{
		cancelMovementMode();
		unitAction.completeMovement();
	}
	
	public void undoMovement()
	{
		ActiveUnit au = game.currentUnit();
		FFTAMapTile old = game.map.mapData[au.oldX][au.oldY];
		mapPanel.moveUnit(au, old);
		repaint();
	}
	
	public ArrayList<ActiveUnit> getYourUnits()
	{
		return mapPanel.mpUnits;
	}
	
	public void appendToChat(String s)
	{
		if (!s.equals(""))
			try {
				HTMLDocument doc = (HTMLDocument) chat.getDocument();
				doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), s + "<br>");
				chat.setCaretPosition(doc.getLength());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void startOfTurnAnnouncements(ActiveUnit au)
	{
		if (au.status[StatusEffect.DOOM.ordinal()] > 0)
		{
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name +
					"</strong>'s timer drops to <strong>" + (au.status[StatusEffect.DOOM.ordinal()] - 1) + 
					"</strong>!");
			
			if (au.status[StatusEffect.DOOM.ordinal()] == 1)
			{
				appendToChat("<em><span style=\"color:gray\">......The reaper takes <strong>" + 
							  au.unit.name + "</strong>!");
				return;
			}
		}
		
		if (au.status[StatusEffect.SLEEP.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong> wakes up!");
		
		if (au.status[StatusEffect.IMMOBILIZE.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong> can move again!");
		
		if (au.status[StatusEffect.DISABLE.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong> can act again!");
		
		if (au.status[StatusEffect.SLOW.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong>'s speed resets!");
		
		if (au.status[StatusEffect.STOP.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong>'s back in time!");
		
		if (au.status[StatusEffect.ADDLE.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong> remembers!");
		
		if (au.status[StatusEffect.CONFUSE.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong> comes to!");
		
		if (au.status[StatusEffect.CHARM.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong> comes to!");
		
		// Buffs fading
		if (au.status[StatusEffect.PROTECT.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong>'s protect fades!");
		
		if (au.status[StatusEffect.SHELL.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong>'s shell melts!");
		
		if (au.status[StatusEffect.HASTE.ordinal()] == 1)
			appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + "</strong>'s speed resets!");
		
		
	}
	
	public void closeEngagementWindow()
	{
		// If the engagement is still in progress, prompt the user to confirm the window's closing
		if (!game.gameOver)
		{
			/*int output = JOptionPane.showConfirmDialog(ew, "Are you sure you want to abandon this engagement?",
					"Warning: Engagement in progress!", JOptionPane.YES_NO_OPTION);
			if (output == JOptionPane.YES_OPTION)
			{
				// Notify the server of user's leaving the room
				try {
					sendExit();
				} catch (IOException e) { e.printStackTrace(); }
				ew.dispose();
			}*/			
		}
		
		// Temporarily do not show warning dialog
		try {
			game.sendExit();
		} catch (IOException e) { e.printStackTrace(); }
		
		// Remove client's reference to this game
		game.client.game = null;
		
		// Close window
		dispose();
	}
	
	public void selectTarget(int clicks)
	{
		mapPanel.selectTarget(clicks);
	}
	

	class EngagementWindowRosterPanel extends ClanBuilderRosterPanel
	{
		Engagement game;
		EngagementWindow window;
		
		public EngagementWindowRosterPanel(EngagementWindow window)
		{
			this.window = window;
			if (window != null)
				game = window.game;
			
			setPreferredSize(new Dimension(1, 162));
			JPanel padding = new JPanel(), padding2 = new JPanel(), padding3 = new JPanel();
			padding.setPreferredSize(new Dimension(36, 1));
			padding2.setPreferredSize(new Dimension(1, 22));
			padding3.setPreferredSize(new Dimension(80, 1));
			add(padding, BorderLayout.WEST);
			add(padding2, BorderLayout.SOUTH);
			rightPanel.add(padding3, BorderLayout.EAST);
			btnNewUnit.setEnabled(false);
			btnDelete.setEnabled(false);
			btnSwapLeft.setEnabled(false);
			btnSwapRight.setEnabled(false);
			
			JPanel readyPanel = new JPanel(new FlowLayout());
			JButton btnReady = new JButton("Ready!");
			btnReady.setPreferredSize(new Dimension(100, 30));
			readyPanel.add(btnReady);
			add(readyPanel, BorderLayout.NORTH);
			
			btnReady.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					ArrayList<ActiveUnit> units = window.getYourUnits();
					if (units.size() > 0)
					{
						try
						{
							game.sendReady();
							if (game.playerNumber == 1)
								game.p1Units = units;
							else if (game.playerNumber == 2)
								game.p2Units = units;
							
							btnReady.setEnabled(false);
							btnReady.setText("Waiting for other player...");
						}
						catch (IOException ex) { ex.printStackTrace(); }
					}
				}
			});
		}
	}
	
	class TurnOrderBoy extends JPanel implements Comparable<TurnOrderBoy>
	{
		ActiveUnit unit;
		double position;
		
		public TurnOrderBoy(ActiveUnit unit)
		{
			this.unit = unit;
			position = 0;
			
			FlowLayout flowLayout_1 = (FlowLayout) getLayout();
			flowLayout_1.setAlignment(FlowLayout.LEFT);
			flowLayout_1.setVgap(1);
			flowLayout_1.setHgap(1);
			setPreferredSize(new Dimension(96, 20));
			
			TOJobIconPanel panel = new TOJobIconPanel(unit);
			panel.setPreferredSize(new Dimension(32, 16));
			panel.setOpaque(false);
			add(panel);
			
			if (unit.team == 1)
				setBackground(new Color(192, 192, 248));
			else
				setBackground(new Color(248, 192, 192));
			
			JLabel lblUnitName;
			lblUnitName = new JLabel(" " + unit.unit.name);
			lblUnitName.setFont(new Font("Tahoma", Font.BOLD, 11));
			
			add(lblUnitName);
			repaint();
		}

		public TurnOrderBoy(String special)
		{
			this.unit = null;
			this.position = position;
			
			FlowLayout flowLayout_1 = (FlowLayout) getLayout();
			flowLayout_1.setAlignment(FlowLayout.LEFT);
			flowLayout_1.setVgap(1);
			flowLayout_1.setHgap(1);
			setPreferredSize(new Dimension(96, 20));
			setBackground(new Color(192, 248, 192));
			
			JLabel lblChevrons = new JLabel(">>>>");
			JLabel lblAction = new JLabel(" " + special);
			lblAction.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			add(lblChevrons);
			add(lblAction);
			repaint();
		}
		
		@Override
		public int compareTo(TurnOrderBoy to)
		{
			if (to.position > position)
				return -1;
			else if (to.position < position)
				return 1;
			else if (unit == null || to.unit == null)
				return 1;
			else if (to.unit.priority > unit.priority)
				return -1;
			else
				return 1;
		}	
	}
	
	class TOJobIconPanel extends JPanel
	{
		BufferedImage icon;
		String jobName;
		
		public TOJobIconPanel(ActiveUnit au)
		{
			super();
			if (au != null)
				jobName = au.unit.job.name();
			update();
		}
		
		public void update()
		{
			try {
				if (jobName != null)
					icon = ImageIO.read(new File("resources/icons/icon_" + jobName + ".png"));
				else
					icon = null;
			} catch (IOException e) {}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			if (icon != null)
				g.drawImage(icon, 0, 0, null);
		}
	}
}