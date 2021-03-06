package client;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;

import fftadata.ActiveUnit;
import fftadata.FFTAEquip;
import fftadata.FFTAUnit;
import fftadata.StatusEffect;

import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

public class UnitPreviewPanel extends JPanel
{
	static Engagement game;
	ActiveUnit au;
	FFTAUnit unit;
	private JLabel lblCurrHP;
	private JLabel lblCurrMP;
	private JLabel lblStatus;
	private JList<FFTAEquip> equipmentList_1;
	private JLabel lblWAtkScore, lblWDefScore, lblMPowScore, lblMResScore,
					lblSpeedScore, lblMoveScore, lblJumpScore, lblEvadeScore;
	
	public UnitPreviewPanel()
	{
		this(new ActiveUnit((FFTAUnit) new ClanBuilderRosterPanel().roster.getSelectedValue(), 0, 0, 0, 2));
	}
	
	public UnitPreviewPanel(ActiveUnit au)
	{
		this.au = au;
		unit = au.unit;
		
		setBorder(new TitledBorder(null, "Unit Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPreferredSize(new Dimension(320, 162));
		setLayout(new BorderLayout(0, 0));

		
		JPanel mainPanel = new JPanel();
		if (au.team == 1)
			mainPanel.setBackground(new Color(192, 192, 248));
		else
			mainPanel.setBackground(new Color(248, 192, 192));
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		mainPanel.add(leftPanel, BorderLayout.CENTER);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setOpaque(false);
		leftPanel.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setOpaque(false);
		upperPanel.add(basicInfoPanel, BorderLayout.CENTER);
		basicInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNameAndLevel = new JLabel("<html><b>" + unit.name + "</b> &mdash Lv. " + unit.getLevel());
		lblNameAndLevel.setHorizontalAlignment(SwingConstants.CENTER);
		basicInfoPanel.add(lblNameAndLevel, BorderLayout.NORTH);
		
		JPanel unitDescPanel = new JPanel();
		unitDescPanel.setOpaque(false);
		basicInfoPanel.add(unitDescPanel, BorderLayout.CENTER);
		unitDescPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel basicStatsAlignmentPanel = new JPanel();
		unitDescPanel.add(basicStatsAlignmentPanel);
		basicStatsAlignmentPanel.setOpaque(false);
		basicStatsAlignmentPanel.setBorder(null);
		basicStatsAlignmentPanel.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel lblHP = new JLabel("<html><b>HP</b>");
		lblHP.setHorizontalAlignment(SwingConstants.RIGHT);
		basicStatsAlignmentPanel.add(lblHP);
		
		lblCurrHP = new JLabel("" + au.currHP);
		lblCurrHP.setHorizontalAlignment(SwingConstants.CENTER);
		basicStatsAlignmentPanel.add(lblCurrHP);
		
		JLabel lblMaxHP = new JLabel("/ " + (int) unit.maxHP);
		basicStatsAlignmentPanel.add(lblMaxHP);
		
		JLabel lblMP = new JLabel("<html><b>MP</b>");
		lblMP.setHorizontalAlignment(SwingConstants.RIGHT);
		basicStatsAlignmentPanel.add(lblMP);
		
		lblCurrMP = new JLabel("" + au.currMP);
		lblCurrMP.setHorizontalAlignment(SwingConstants.CENTER);
		basicStatsAlignmentPanel.add(lblCurrMP);
		
		JLabel lblMaxMP = new JLabel("/ " + (int) unit.maxMP);
		basicStatsAlignmentPanel.add(lblMaxMP);
		
		JLabel lblJobAndSecondary = new JLabel("<html><em>" + unit.secondary + "&emsp " + unit.support + "</em>");
		unitDescPanel.add(lblJobAndSecondary);
		lblJobAndSecondary.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOtherAbilities = new JLabel("<html> <em>" + unit.reaction + "&emsp " + unit.combo);
		unitDescPanel.add(lblOtherAbilities);
		lblOtherAbilities.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherAbilities.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel imagePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				BufferedImage sprite = null;
				try {
					sprite = ImageIO.read(new File(au.getSpriteURL()));
					
					int r = 1, t = 32;
					if (au.isSpriteReflected())
						r = -1;
					else
						t = 0;
						

					g.drawImage(sprite, t + 2, 2, 32*r, 64, null);
				} catch(IOException e) { System.out.println("Couldn't find sprite!"); }
				
			}
		};
		imagePanel.setOpaque(false);
		imagePanel.setPreferredSize(new Dimension(34, 66));
		upperPanel.add(imagePanel, BorderLayout.WEST);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setOpaque(false);
		leftPanel.add(lowerPanel, BorderLayout.CENTER);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		lowerPanel.add(separator, BorderLayout.NORTH);
		
		JPanel statsPanel = new JPanel();
		statsPanel.setOpaque(false);
		lowerPanel.add(statsPanel, BorderLayout.CENTER);
		statsPanel.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel lblWAtk = new JLabel("<html><b>WAtk");
		lblWAtk.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWAtk);
		
		lblWAtkScore = new JLabel(""+unit.getTotalWAtk());
		lblWAtkScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWAtkScore);
		
		JLabel lblSpeed = new JLabel("<html><b>Speed");
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblSpeed);
		
		lblSpeedScore = new JLabel(""+unit.getTotalSpeed());
		lblSpeedScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblSpeedScore);
		
		JLabel lblWDef = new JLabel("<html><b>WDef");
		lblWDef.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWDef);
		
		lblWDefScore = new JLabel(""+unit.getTotalWDef());
		lblWDefScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWDefScore);
		
		JLabel lblMove = new JLabel("<html><b>Move");
		lblMove.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMove);
		
		lblMoveScore = new JLabel(""+unit.getTotalMove());
		lblMoveScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMoveScore);
		
		JLabel lblMPow = new JLabel("<html><b>MPow");
		lblMPow.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMPow);
		
		lblMPowScore = new JLabel(""+unit.getTotalMPow());
		lblMPowScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMPowScore);
		
		JLabel lblJump = new JLabel("<html><b>Jump");
		lblJump.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblJump);
		
		lblJumpScore = new JLabel(""+unit.getTotalJump());
		lblJumpScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblJumpScore);
		
		JLabel lblMRes = new JLabel("<html><b>MRes");
		lblMRes.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMRes);
		
		lblMResScore = new JLabel(""+unit.getTotalMRes());
		lblMResScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMResScore);
		
		JLabel lblEvade = new JLabel("<html><b>Evade");
		lblEvade.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblEvade);
		
		lblEvadeScore = new JLabel(""+unit.getTotalEvade());
		lblEvadeScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblEvadeScore);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		rightPanel.setPreferredSize(new Dimension(100, 95));
		rightPanel.setLayout(new BorderLayout(0, 0));
		equipmentList_1 = new JList<FFTAEquip>();
		equipmentList_1.setEnabled(false);
//		equipmentList_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		equipmentList_1.setPreferredSize(new Dimension(100, 94));
		equipmentList_1.setCellRenderer(new EquipCellRenderer());
		equipmentList_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		equipmentList_1.setVisibleRowCount(5);
		equipmentList_1.setModel(unit.equips.getListModel());
		rightPanel.add(equipmentList_1, BorderLayout.NORTH);
		
		lblStatus = new JLabel("<html><b>Status:</b> OK");
		lblStatus.setVerticalAlignment(SwingConstants.TOP);
		rightPanel.add(lblStatus, BorderLayout.CENTER);
		
	}

	public void updateStats()
	{
		// Update HP and MP
		lblCurrHP.setText("" + au.currHP);
		lblCurrMP.setText("" + au.currMP);

		// Update equipment
		equipmentList_1.setModel(unit.equips.getListModel());
		
		// Update stats
		lblWAtkScore.setText ("" + unit.getTotalWAtk());
		lblWDefScore.setText ("" + unit.getTotalWDef());
		lblMPowScore.setText ("" + unit.getTotalMPow());
		lblMResScore.setText ("" + unit.getTotalMRes());
		lblSpeedScore.setText("" + unit.getTotalSpeed());
		lblMoveScore.setText ("" + unit.getTotalMove());
		lblJumpScore.setText ("" + unit.getTotalJump());
		lblEvadeScore.setText("" + unit.getTotalEvade());
		
		// Update status effects
		int count = 0;
		StringBuilder status = new StringBuilder();
		
		if (au.currHP == 0)
			lblStatus.setText("<html><b>Status:</b> KO");
		else
		{
			for (int i = 0; i < au.status.length; i++)
				if (au.status[i] > 0)
				{
					if (count > 0)
						status.append(", ");
					
					status.append(StatusEffect.values()[i].NAME);
					
					if (i == StatusEffect.DOOM.ordinal())
						status.append(" (" + au.status[i] + ")");
					
					count++; 
				}
			
			if (au.covering != -1)
			{
				if (count > 0)
					status.append(", ");
				status.append("Covering (" + game.getUnits()[au.covering].unit.name + ")");
				count++;
			}
			
			if (status.toString().equals("")) 
				lblStatus.setText("<html><b>Status:</b> OK");
			else
				lblStatus.setText("<html><b>Status:</b> " + status.toString());
		}
		
		revalidate();
	}
	
	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					MapPanelTest frame = new MapPanelTest();
//					frame.getContentPane().add(new UnitPreviewPanel());
//					frame.pack();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
