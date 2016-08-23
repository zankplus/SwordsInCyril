package client;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTMLDocument;

import fftadata.ActiveUnit;
import fftadata.FFTASkill;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;

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

	
	
	

	/**
	 * Create the frame.
	 */
	public EngagementWindow(Engagement game)
	{
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
					
					// Temporarily do not show warning dialog
					try {
						game.sendExit();
					} catch (IOException e) { e.printStackTrace(); }
					ew.dispose();
					
				}
				
				// If the engagement is over, simply notify the server of the exit and close the window
				else
				{
					try {
						game.sendExit();
					} catch (IOException e) { e.printStackTrace(); }
					ew.dispose();
				}
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
		System.out.println("checkpoint 1");
		
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(null);
		leftPanel.setPreferredSize(new Dimension(560, 10));
		contentPane.add(leftPanel, BorderLayout.WEST);

		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		
		System.out.println("checkpoint 2");
		
		rosterPanel = new EngagementWindowRosterPanel(this);
		mapPanel = new MapPanel(this);
	
		System.out.println("checkpoint 3");
		
		JPanel turnOrderPanel = new JPanel();
		turnOrderPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		turnOrderPanel.setPreferredSize(new Dimension(80, 10));
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
	
	public void moveUnit(ActiveUnit au, ZankMapTile dest)
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
		damagePreviewPanel = new DamagePreviewPanel(game.currentUnit(), targets, selectedSkill);
		previewDeck.add(damagePreviewPanel, "Damage Preview");
		deck.last(previewDeck);
		revalidate();
	}
	
	public void selectTile(ZankMapTile tile)
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
			game.sendAction(targets, selectedSkill, x, y);
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
		
		damagePreviewPanel = new JPanel();
		previewDeck.add(damagePreviewPanel, "Damage Preview");
		
		for (int i = 0; i < units.length; i++)
			System.out.println(i + " " + units[i].unit.name);
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
		ZankMapTile old = game.map.mapData[au.oldX][au.oldY];
		mapPanel.moveUnit(au, old);
		repaint();
	}
	
	public ArrayList<ActiveUnit> getYourUnits()
	{
		return mapPanel.mpUnits;
	}
	
	public void appendToChat(String s)
	{
		try {
			HTMLDocument doc = (HTMLDocument) chat.getDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), s + "<br>");
			chat.setCaretPosition(doc.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class EngagementWindowRosterPanel extends ClanBuilderRosterPanel
{
	Engagement game;
	EngagementWindow window;
	
	public EngagementWindowRosterPanel(EngagementWindow window)
	{
		this.window = window;
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
