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

public class UnitPreviewPanel extends JPanel
{
	
	ActiveUnit au;
	FFTAUnit unit;
	private JLabel lblCurrHP;
	private JLabel lblCurrMP;
	private JLabel lblStatus;
	
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
		
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.CENTER);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel upperPanel = new JPanel();
		leftPanel.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel basicInfoPanel = new JPanel();
		upperPanel.add(basicInfoPanel, BorderLayout.CENTER);
		basicInfoPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblNameAndLevel = new JLabel("<html><b>" + unit.name + "</b> &mdash Lv. " + unit.getLevel() + " " + unit.job);
		lblNameAndLevel.setHorizontalAlignment(SwingConstants.CENTER);
		basicInfoPanel.add(lblNameAndLevel);
		
		JPanel basicStatsAlignmentPanel = new JPanel();
		basicStatsAlignmentPanel.setBorder(null);
		basicInfoPanel.add(basicStatsAlignmentPanel);
		basicStatsAlignmentPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JLabel lblHP = new JLabel("<html><b>HP</b>");
		lblHP.setHorizontalAlignment(SwingConstants.CENTER);
		basicStatsAlignmentPanel.add(lblHP);
		
		lblCurrHP = new JLabel("" + au.currHP);
		lblCurrHP.setHorizontalAlignment(SwingConstants.CENTER);
		basicStatsAlignmentPanel.add(lblCurrHP);
		
		JLabel lblMaxHP = new JLabel("/ " + (int) unit.maxHP);
		basicStatsAlignmentPanel.add(lblMaxHP);
		
		JPanel basicStatsAlignmentGap = new JPanel();
		basicStatsAlignmentPanel.add(basicStatsAlignmentGap);
		
		JLabel lblMP = new JLabel("<html><b>MP</b>");
		lblMP.setHorizontalAlignment(SwingConstants.CENTER);
		basicStatsAlignmentPanel.add(lblMP);
		
		lblCurrMP = new JLabel("" + au.currMP);
		lblCurrMP.setHorizontalAlignment(SwingConstants.CENTER);
		basicStatsAlignmentPanel.add(lblCurrMP);
		
		JLabel lblMaxMP = new JLabel("/ " + (int) unit.maxMP);
		basicStatsAlignmentPanel.add(lblMaxMP);
		
		JLabel lblJobAndSecondary = new JLabel("<html><em>" + unit.secondary + "&emsp " + unit.support + "</em>");
		lblJobAndSecondary.setHorizontalAlignment(SwingConstants.CENTER);
		basicInfoPanel.add(lblJobAndSecondary);
		
		JLabel lblOtherAbilities = new JLabel("<html> <em>" + unit.reaction + "&emsp " + unit.combo);
		lblOtherAbilities.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherAbilities.setAlignmentX(Component.CENTER_ALIGNMENT);
		basicInfoPanel.add(lblOtherAbilities);
		
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
						
					g.drawImage(sprite, t, 0, 32*r, 64, null);
				} catch(IOException e) { System.out.println("Couldn't find sprite!"); }
				
			}
		};
		imagePanel.setPreferredSize(new Dimension(32, 64));
		upperPanel.add(imagePanel, BorderLayout.WEST);
		
		JPanel lowerPanel = new JPanel();
		leftPanel.add(lowerPanel, BorderLayout.CENTER);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		lowerPanel.add(separator, BorderLayout.NORTH);
		
		JPanel statsPanel = new JPanel();
		lowerPanel.add(statsPanel, BorderLayout.CENTER);
		statsPanel.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel lblWAtk = new JLabel("<html><b>WAtk");
		lblWAtk.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWAtk);
		
		JLabel lblWAtkScore = new JLabel(""+unit.getTotalWAtk());
		lblWAtkScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWAtkScore);
		
		JLabel lblSpeed = new JLabel("<html><b>Speed");
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblSpeed);
		
		JLabel lblSpeedScore = new JLabel(""+unit.getTotalSpeed());
		lblSpeedScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblSpeedScore);
		
		JLabel lblWDef = new JLabel("<html><b>WDef");
		lblWDef.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWDef);
		
		JLabel lblWDefScore = new JLabel(""+unit.getTotalWDef());
		lblWDefScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblWDefScore);
		
		JLabel lblMove = new JLabel("<html><b>Move");
		lblMove.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMove);
		
		JLabel lblMoveScore = new JLabel(""+unit.getTotalMove());
		lblMoveScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMoveScore);
		
		JLabel lblMPow = new JLabel("<html><b>MPow");
		lblMPow.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMPow);
		
		JLabel lblMPowScore = new JLabel(""+unit.getTotalMPow());
		lblMPowScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMPowScore);
		
		JLabel lblJump = new JLabel("<html><b>Jump");
		lblJump.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblJump);
		
		JLabel lblJumpScore = new JLabel(""+unit.getTotalJump());
		lblJumpScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblJumpScore);
		
		JLabel lblMRes = new JLabel("<html><b>MRes");
		lblMRes.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMRes);
		
		JLabel lblMResScore = new JLabel(""+unit.getTotalMRes());
		lblMResScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblMResScore);
		
		JLabel lblEvade = new JLabel("<html><b>Evade");
		lblEvade.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblEvade);
		
		JLabel lblEvadeScore = new JLabel(""+unit.getTotalEvade());
		lblEvadeScore.setHorizontalAlignment(SwingConstants.CENTER);
		statsPanel.add(lblEvadeScore);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(100, 95));
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JList equipmentList = new JList();
		equipmentList = new JList<FFTAEquip>();
		equipmentList.setPreferredSize(new Dimension(100, 94));
		equipmentList.setCellRenderer(new EquipCellRenderer());
		equipmentList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		equipmentList.setVisibleRowCount(5);
		equipmentList.setModel(unit.equips.getListModel());
		rightPanel.add(equipmentList, BorderLayout.NORTH);
		
		lblStatus = new JLabel("<html><b>Status:</b> OK");
		lblStatus.setVerticalAlignment(SwingConstants.TOP);
		rightPanel.add(lblStatus, BorderLayout.CENTER);
		
	}

	public void updateStats()
	{
		// Update HP and MP
		lblCurrHP.setText("" + au.currHP);
		lblCurrMP.setText("" + au.currMP);
		
		// Update status
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
