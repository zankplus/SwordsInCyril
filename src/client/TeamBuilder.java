package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultFormatter;

import fftadata.EquipSet;
import fftadata.EquipType;
import fftadata.FFTACombo;
import fftadata.FFTACommand;
import fftadata.FFTAEquip;
import fftadata.FFTAJob;
import fftadata.FFTARace;
import fftadata.FFTAReaction;
import fftadata.FFTASupport;
import fftadata.FFTAUnit;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.*;

import java.awt.Component;

import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;



@SuppressWarnings("serial")
public class TeamBuilder extends JFrame
{
	private JPanel contentPane;
	private JobIconPanel jobIconPanel;
	private JTextField nameField;
	private JComboBox<FFTARace> raceBox;
	private JComboBox<FFTAJob> jobBox, growthJobBox;
	private JComboBox<FFTACommand> primaryAbilityBox, secondaryAbilityBox;
	private JComboBox<FFTASupport> sAbilityBox;
	private JComboBox<FFTAReaction> rAbilityBox;
	private JComboBox<FFTACombo> cAbilityBox;
	private JList<FFTAEquip> equipmentList;
	private EquipCellRenderer ecr;
	private FFTAUnit unit;
	private JLabel lblHPScore, lblMPScore, lblMoveScore, lblJumpScore, lblEvadeScore, lblWAtkScore, lblWDefScore,
		lblMPowScore, lblMResScore, lblSpeedScore, lblFPowScore;
	private JButton btnLevels;
	private JRadioButton rdbtnAdvanced;
	private JRadioButton rdbtnSimple;
	private BufferedImage charBG;
	private ClanBuilderSpritePanel spritePanel;
	ClanBuilderRosterPanel rosterPanel;
	
	boolean firstRaceUpdate,	// Makes the initial race setting work properly. After this initial update, changing to the same race shouldn't reset your job
			minimalRaceUpdate;	// Makes changing the race box only change combo box models, without updating job or revalidating equipment
	private JSpinner levelSpinner;
	protected boolean updateValueOnly;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamBuilder frame = new TeamBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamBuilder()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		ImageIcon img = new ImageIcon("resources/misc/appicon.png");
		setIconImage(img.getImage());
		
		setTitle("Clan Builder");
		unit = new FFTAUnit();
		ecr = new EquipCellRenderer();
		firstRaceUpdate = true;
		minimalRaceUpdate = true;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 460);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		// setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Things to do");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExportToText = new JMenuItem("Export to text");
		mntmExportToText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(unit);
			}
		});
		mnNewMenu.add(mntmExportToText);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		try
		{
			charBG = ImageIO.read(new File("resources/misc/charbg.png"));
		}
		catch (IOException e) { System.err.println("Couldn't find charbg.png"); charBG = null; }
		
		spritePanel = new ClanBuilderSpritePanel(unit.job);
		spritePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		ButtonGroup levelButtonGroup = new ButtonGroup();
		
		
		JPanel upperPanel = new JPanel();
		contentPane.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		upperPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panel_2 = new JPanel();
		upperPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel spriteBorderPanel = new JPanel();
		panel_2.add(spriteBorderPanel, BorderLayout.NORTH);
		spriteBorderPanel.setBorder(new TitledBorder(null, "Sprite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spriteBorderPanel.setLayout(new BorderLayout(0, 0));
		spriteBorderPanel.add(spritePanel, BorderLayout.NORTH);
		
		jobIconPanel = new JobIconPanel() ;
		jobIconPanel.setPreferredSize(new Dimension(16, 44));
		
		panel_2.add(jobIconPanel, BorderLayout.SOUTH);
		
		
		JPanel basicsPanel = new JPanel();
		basicsPanel.setPreferredSize(new Dimension(145, 135));
		basicsPanel.setBorder(new TitledBorder(null, "Basics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upperPanel.add(basicsPanel);
		GridBagLayout gbl_basicsPanel = new GridBagLayout();
		gbl_basicsPanel.columnWidths = new int[] {31, 100, 0};
		gbl_basicsPanel.rowHeights = new int[]{112, 0};
		gbl_basicsPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_basicsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		basicsPanel.setLayout(gbl_basicsPanel);
		
		JPanel basicLabelsPanel = new JPanel();
		GridBagConstraints gbc_basicLabelsPanel = new GridBagConstraints();
		gbc_basicLabelsPanel.anchor = GridBagConstraints.WEST;
		gbc_basicLabelsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_basicLabelsPanel.insets = new Insets(0, 10, 0, 5);
		gbc_basicLabelsPanel.gridx = 0;
		gbc_basicLabelsPanel.gridy = 0;
		basicsPanel.add(basicLabelsPanel, gbc_basicLabelsPanel);
		basicLabelsPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		basicLabelsPanel.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblRace = new JLabel("Race");
		basicLabelsPanel.add(lblRace);
		lblRace.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblJob = new JLabel("Job");
		basicLabelsPanel.add(lblJob);
		lblJob.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblLevel = new JLabel(" Level");
		basicLabelsPanel.add(lblLevel);
		
		JPanel basicFieldsPanel = new JPanel();
		basicFieldsPanel.setPreferredSize(new Dimension(110, 10));
		GridBagConstraints gbc_basicFieldsPanel = new GridBagConstraints();
		gbc_basicFieldsPanel.insets = new Insets(0, 5, 0, 10);
		gbc_basicFieldsPanel.fill = GridBagConstraints.BOTH;
		gbc_basicFieldsPanel.gridx = 1;
		gbc_basicFieldsPanel.gridy = 0;
		basicsPanel.add(basicFieldsPanel, gbc_basicFieldsPanel);
		basicFieldsPanel.setLayout(new GridLayout(5, 1, 2, 2));
		
		nameField = new JTextField();
		basicFieldsPanel.add(nameField);
		nameField.setColumns(10);
		
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				unit.name = nameField.getText();
				rosterPanel.repaint();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				unit.name = nameField.getText();
				rosterPanel.repaint();
			}
		});
		
		raceBox = new JComboBox(new FFTARace[] {FFTARace.BANGAA, FFTARace.HUMAN, FFTARace.MOOGLE, FFTARace.NU_MOU, FFTARace.VIERA});
		basicFieldsPanel.add(raceBox);
		
		jobBox = new JComboBox();
		basicFieldsPanel.add(jobBox);
		jobBox.setMaximumRowCount(11);
		
		levelSpinner = new JSpinner();
		levelSpinner.setModel(new SpinnerNumberModel(50, 1, 50, 1));
		basicFieldsPanel.add(levelSpinner);
		
		jobBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				FFTAJob job = (FFTAJob) jobBox.getSelectedItem();
				changeJob(job);
			}
		});
		
		raceBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!minimalRaceUpdate)
				{
					FFTARace race = (FFTARace) raceBox.getSelectedItem();
					changeRace(race);
				}
				
			}
		});
		
		JComponent comp = levelSpinner.getEditor();
		levelSpinner.addChangeListener(new ChangeListener() {

		    

			@Override
		    public void stateChanged(ChangeEvent e)
		    {
		    	if (!updateValueOnly)	// if this is true, update only the value in the box and none of its dependencies
		    	{
		    		unit.levels[growthJobBox.getSelectedIndex()] = (Integer) levelSpinner.getValue() - 1;
					unit.updateDeepStats();
					updateDeepStatsDisplay();
		    	}
		    }
		});
	    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
	    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
	    formatter.setCommitsOnValidEdit(true);
				
		JPanel abilityPanel = new JPanel();
		abilityPanel.setPreferredSize(new Dimension(184, 135));
		abilityPanel.setBorder(new TitledBorder(null, "Abilities", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upperPanel.add(abilityPanel);
		GridBagLayout gbl_abilityPanel = new GridBagLayout();
		gbl_abilityPanel.columnWidths = new int[] {51, 116, 0};
		gbl_abilityPanel.rowHeights = new int[]{112, 0};
		gbl_abilityPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_abilityPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		abilityPanel.setLayout(gbl_abilityPanel);
		
		JPanel abilityLabelsPanel = new JPanel();
		GridBagConstraints gbc_abilityLabelsPanel = new GridBagConstraints();
		gbc_abilityLabelsPanel.anchor = GridBagConstraints.WEST;
		gbc_abilityLabelsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_abilityLabelsPanel.insets = new Insets(0, 10, 0, 5);
		gbc_abilityLabelsPanel.gridx = 0;
		gbc_abilityLabelsPanel.gridy = 0;
		abilityPanel.add(abilityLabelsPanel, gbc_abilityLabelsPanel);
		abilityLabelsPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblPrimary = new JLabel("Primary");
		abilityLabelsPanel.add(lblPrimary);
		lblPrimary.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSecondary = new JLabel("Secondary");
		abilityLabelsPanel.add(lblSecondary);
		lblSecondary.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSupport = new JLabel("Support");
		abilityLabelsPanel.add(lblSupport);
		lblSupport.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblReaction = new JLabel("Reaction");
		abilityLabelsPanel.add(lblReaction);
		lblReaction.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblCombo = new JLabel("Combo");
		abilityLabelsPanel.add(lblCombo);
		lblCombo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel abilityComboBoxesPanel = new JPanel();
		abilityComboBoxesPanel.setPreferredSize(new Dimension(10, 100));
		GridBagConstraints gbc_abilityComboBoxesPanel = new GridBagConstraints();
		gbc_abilityComboBoxesPanel.insets = new Insets(0, 5, 0, 10);
		gbc_abilityComboBoxesPanel.fill = GridBagConstraints.BOTH;
		gbc_abilityComboBoxesPanel.gridx = 1;
		gbc_abilityComboBoxesPanel.gridy = 0;
		abilityPanel.add(abilityComboBoxesPanel, gbc_abilityComboBoxesPanel);
		abilityComboBoxesPanel.setLayout(new GridLayout(5, 1, 0, 2));
		
		primaryAbilityBox = new JComboBox<FFTACommand>();
		abilityComboBoxesPanel.add(primaryAbilityBox);
		
		secondaryAbilityBox = new JComboBox<FFTACommand>();
		abilityComboBoxesPanel.add(secondaryAbilityBox);
		secondaryAbilityBox.setRenderer(new SecondaryAbilityCellRenderer());
		secondaryAbilityBox.setMaximumRowCount(13);
		
		sAbilityBox = new JComboBox<FFTASupport>();
		abilityComboBoxesPanel.add(sAbilityBox);
		sAbilityBox.setMaximumRowCount(14);
		
		rAbilityBox = new JComboBox<FFTAReaction>();
		abilityComboBoxesPanel.add(rAbilityBox);
		rAbilityBox.setMaximumRowCount(11);
		
		cAbilityBox = new JComboBox<FFTACombo>();
		abilityComboBoxesPanel.add(cAbilityBox);
		cAbilityBox.setMaximumRowCount(12);
		cAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				unit.combo = (FFTACombo) cAbilityBox.getSelectedItem();
			}
		});
		rAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				unit.reaction = (FFTAReaction) rAbilityBox.getSelectedItem();
			}
		});
		sAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				unit.support = (FFTASupport) sAbilityBox.getSelectedItem();
				unit.equips.removeIllegalEquipment();
				equipmentList.setModel(unit.equips.getListModel());
			}
		});
		secondaryAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (secondaryAbilityBox.getSelectedItem() == unit.job.command)
					secondaryAbilityBox.setSelectedIndex(0);
				unit.secondary = (FFTACommand) secondaryAbilityBox.getSelectedItem();
			}
		});
		
//		JPanel resistancesPanel = new JPanel();
//		resistancesPanel.setBorder(new TitledBorder(null, "Resistances", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		GridBagConstraints gbc_resistancesPanel = new GridBagConstraints();
//		gbc_resistancesPanel.fill = GridBagConstraints.BOTH;
//		gbc_resistancesPanel.gridx = 3;
//		gbc_resistancesPanel.gridy = 0;
//		upperPanel.add(resistancesPanel, gbc_resistancesPanel);
		
		JPanel middlePanel = new JPanel();
		contentPane.add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel equipPanel = new JPanel();
		equipPanel.setBorder(new TitledBorder(null, "Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		middlePanel.add(equipPanel, BorderLayout.WEST);
		equipPanel.setLayout(new BorderLayout(0, 0));
		
		equipmentList = new JList<FFTAEquip>();
		equipmentList.setPreferredSize(new Dimension(100, 94));
		equipmentList.setCellRenderer(ecr);
		equipmentList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		equipmentList.setVisibleRowCount(5);
		
		equipPanel.add(equipmentList, BorderLayout.NORTH);
		
		JButton btnChoose = new JButton("Choose...");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				EquipFrame ef = new EquipFrame(unit);
				ef.setVisible(true);
			}
		});
		equipPanel.add(btnChoose, BorderLayout.SOUTH);
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		middlePanel.add(statsPanel, BorderLayout.CENTER);
		statsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel statNamesPane1 = new JPanel();
		statsPanel.add(statNamesPane1, BorderLayout.WEST);
		statNamesPane1.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblHP = new JLabel("HP");
		lblHP.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane1.add(lblHP);
		
		JLabel lblMP = new JLabel("MP");
		lblMP.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane1.add(lblMP);
		
		JLabel lblMove = new JLabel("Move");
		lblMove.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane1.add(lblMove);
		
		JLabel lblJump = new JLabel("Jump");
		lblJump.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane1.add(lblJump);
		
		JLabel lblEvade = new JLabel(" Evade");
		lblEvade.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane1.add(lblEvade);
		
		JPanel statsPanelLayer2 = new JPanel();
		statsPanelLayer2.setPreferredSize(new Dimension(25, 10));
		statsPanel.add(statsPanelLayer2, BorderLayout.CENTER);
		statsPanelLayer2.setLayout(new BorderLayout(0, 0));
		
		JPanel statScoresPane1 = new JPanel();
		statScoresPane1.setPreferredSize(new Dimension(35, 10));
		statsPanelLayer2.add(statScoresPane1, BorderLayout.WEST);
		statScoresPane1.setLayout(new GridLayout(5, 1, 0, 0));
		
		lblHPScore = new JLabel();
		statScoresPane1.add(lblHPScore);
		lblHPScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHPScore.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMPScore = new JLabel();
		statScoresPane1.add(lblMPScore);
		lblMPScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMPScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblMoveScore = new JLabel();
		statScoresPane1.add(lblMoveScore);
		lblMoveScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoveScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblJumpScore = new JLabel();
		statScoresPane1.add(lblJumpScore);
		lblJumpScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblJumpScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblEvadeScore = new JLabel();
		statScoresPane1.add(lblEvadeScore);
		lblEvadeScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblEvadeScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel statsPaneLayer3 = new JPanel();
		statsPanelLayer2.add(statsPaneLayer3, BorderLayout.CENTER);
		statsPaneLayer3.setLayout(new BorderLayout(0, 0));
		
		JPanel statNamesPane2 = new JPanel();
		statsPaneLayer3.add(statNamesPane2, BorderLayout.WEST);
		statNamesPane2.setLayout(new GridLayout(6, 1, 0, 0));
		
		JLabel lblWeaponAttack = new JLabel("Weapon Attack");
		lblWeaponAttack.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane2.add(lblWeaponAttack);
		
		JLabel lblWeaponDefense = new JLabel("     Weapon Defense");
		lblWeaponDefense.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane2.add(lblWeaponDefense);
		
		JLabel lblMagicPower = new JLabel("Magic Power");
		lblMagicPower.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane2.add(lblMagicPower);
		
		JLabel lblMagicResistance = new JLabel("Magic Resistance");
		lblMagicResistance.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane2.add(lblMagicResistance);
		
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane2.add(lblSpeed);
		
		JLabel lblFightPower = new JLabel("Fight Power");
		lblFightPower.setHorizontalAlignment(SwingConstants.RIGHT);
		statNamesPane2.add(lblFightPower);
		
		JPanel statsPaneLayer4 = new JPanel();
		statsPaneLayer4.setPreferredSize(new Dimension(25, 10));
		statsPaneLayer3.add(statsPaneLayer4, BorderLayout.CENTER);
		statsPaneLayer4.setLayout(new BorderLayout(0, 0));
		
		JPanel statScoresPane2 = new JPanel();
		statScoresPane2.setPreferredSize(new Dimension(35, 10));
		statsPaneLayer4.add(statScoresPane2, BorderLayout.WEST);
		statScoresPane2.setLayout(new GridLayout(6, 1, 0, 0));
		
		lblWAtkScore = new JLabel();
		lblWAtkScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblWAtkScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		statScoresPane2.add(lblWAtkScore);
		
		lblWDefScore = new JLabel();
		lblWDefScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblWDefScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		statScoresPane2.add(lblWDefScore);
		
		lblMPowScore = new JLabel();
		lblMPowScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMPowScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		statScoresPane2.add(lblMPowScore);
		
		lblMResScore = new JLabel();
		lblMResScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMResScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		statScoresPane2.add(lblMResScore);
		
		lblSpeedScore = new JLabel();
		lblSpeedScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		statScoresPane2.add(lblSpeedScore);
		
		lblFPowScore = new JLabel();
		lblFPowScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblFPowScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		statScoresPane2.add(lblFPowScore);
		
		JPanel eastPanel = new JPanel();
		middlePanel.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel levelPane = new JPanel();
		levelPane.setPreferredSize(new Dimension(104, 10));
		levelPane.setBorder(new TitledBorder(null, "Levels", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eastPanel.add(levelPane, BorderLayout.WEST);
		GridBagLayout gbl_levelPane = new GridBagLayout();
		gbl_levelPane.columnWidths = new int[] {90, 0};
		gbl_levelPane.rowHeights = new int[] {25, 25, 25, 25, 0};
		gbl_levelPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_levelPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		levelPane.setLayout(gbl_levelPane);
		
		rdbtnSimple = new JRadioButton("Simple");
		GridBagConstraints gbc_rdbtnSimple = new GridBagConstraints();
		gbc_rdbtnSimple.fill = GridBagConstraints.BOTH;
		gbc_rdbtnSimple.insets = new Insets(2, 0, 2, 0);
		gbc_rdbtnSimple.gridx = 0;
		gbc_rdbtnSimple.gridy = 0;
		rdbtnSimple.setSelected(true);
		rdbtnSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				rdbtnAdvanced.setSelected(false);
				growthJobBox.setEnabled(true);
				btnLevels.setEnabled(false);
			}
		});
		levelButtonGroup.add(rdbtnSimple);
		levelPane.add(rdbtnSimple, gbc_rdbtnSimple);
		
		growthJobBox = new JComboBox<FFTAJob>();
		GridBagConstraints gbc_growthJobBox = new GridBagConstraints();
		gbc_growthJobBox.fill = GridBagConstraints.BOTH;
		gbc_growthJobBox.insets = new Insets(0, 0, 5, 0);
		gbc_growthJobBox.gridx = 0;
		gbc_growthJobBox.gridy = 1;
		growthJobBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				if (!minimalRaceUpdate)
				{
					JComboBox<FFTAJob> cb = (JComboBox<FFTAJob>) e.getSource();
					FFTAJob job = (FFTAJob) cb.getSelectedItem();
					unit.levels = new int[unit.race.jobs.length];
					unit.statBase = job;
					unit.levels[cb.getSelectedIndex()] = (Integer) levelSpinner.getValue() - 1;
					unit.updateDeepStats();
					updateDeepStatsDisplay();
				}
			}
		});
		levelPane.add(growthJobBox, gbc_growthJobBox);
		
		rdbtnAdvanced = new JRadioButton("Advanced");
		GridBagConstraints gbc_rdbtnAdvanced = new GridBagConstraints();
		gbc_rdbtnAdvanced.fill = GridBagConstraints.BOTH;
		gbc_rdbtnAdvanced.insets = new Insets(20, 0, 2, 0);
		gbc_rdbtnAdvanced.gridx = 0;
		gbc_rdbtnAdvanced.gridy = 2;
		rdbtnAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				rdbtnSimple.setSelected(false);
				growthJobBox.setEnabled(false);
				btnLevels.setEnabled(true);
			}
		});
		levelButtonGroup.add(rdbtnAdvanced);
		levelPane.add(rdbtnAdvanced, gbc_rdbtnAdvanced);
		
		btnLevels = new JButton("Levels...");
		btnLevels.setEnabled(false);
		GridBagConstraints gbc_btnLevels = new GridBagConstraints();
		gbc_btnLevels.fill = GridBagConstraints.BOTH;
		gbc_btnLevels.gridx = 0;
		gbc_btnLevels.gridy = 3;
		levelPane.add(btnLevels, gbc_btnLevels);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		
		JPanel saveExitPanel = new JPanel();
		lowerPanel.add(saveExitPanel, BorderLayout.SOUTH);
		
		JButton btnSaveUnit = new JButton("Save Unit");
		btnSaveUnit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
        		{
					FileOutputStream f_out = new FileOutputStream("units/" + Integer.toString(rosterPanel.roster.getSelectedIndex() + 10) + "_" + unit.name.toLowerCase() + ".unit");
        			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
               		obj_out.writeObject ( unit );
               		System.out.println("Saved unit");
               		obj_out.close();
        		}
        		catch (IOException error) { System.out.println(error); }
			}
		});
//		saveExitPanel.add(btnSaveUnit);
		
		JButton btnSaveClan = new JButton("Save Clan");
		btnSaveClan.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (File file : (new File("units").listFiles()))
					file.delete();
				
				try
        		{
					for (int i = 0; i < rosterPanel.roster.unitList.size(); i++)
					{
						FileOutputStream f_out = new FileOutputStream("units/" + Integer.toString(10 + i) + "_" + ((FFTAUnit) rosterPanel.roster.unitList.get(i)).name.toLowerCase() + ".unit");
	        			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
	        			obj_out.writeObject ( rosterPanel.roster.unitList.get(i) );
	               		System.out.println("Saved unit");
	               		obj_out.close();
					}
        		}
        		catch (IOException error) { System.out.println(error); }
			}
		});
		saveExitPanel.add(btnSaveClan);
		
		JButton btnExit = new JButton("Exit Clan Builder");
		btnExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		saveExitPanel.add(btnExit);
		
		rosterPanel = new ClanBuilderRosterPanel();
		rosterPanel.setMaximumSize(new Dimension(10, 10));
		lowerPanel.add(rosterPanel, BorderLayout.CENTER);
		
		JLabel current = new JLabel(rosterPanel.roster.getSelectedIndex() + 6 * rosterPanel.roster.currentPage + 1 + "/" + rosterPanel.roster.unitList.size());
		saveExitPanel.add(current);
		
		rosterPanel.roster.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				
				if (rosterPanel.roster.getSelectedValue() == null)
					rosterPanel.roster.setSelectedIndex((rosterPanel.roster.unitList.size() - 1) % 6);
				if (!e.getValueIsAdjusting())
					loadUnit((FFTAUnit) rosterPanel.roster.getSelectedValue(), true);
				
				int index = rosterPanel.roster.getSelectedIndex() + 6 * rosterPanel.roster.currentPage;
				
				if (index == 0)
					rosterPanel.btnSwapLeft.setEnabled(false);
				else
					rosterPanel.btnSwapLeft.setEnabled(true);
				
				if (index == rosterPanel.roster.unitList.size() - 1)
					rosterPanel.btnSwapRight.setEnabled(false);
				else
					rosterPanel.btnSwapRight.setEnabled(true);
				
				
				
				current.setText(rosterPanel.roster.getSelectedIndex() + 6 * rosterPanel.roster.currentPage + 1 + "/" + rosterPanel.roster.unitList.size());
			}
		});
		
		
		
		rosterPanel.roster.setSelectedIndex(0);
		loadUnit(new FFTAUnit(), false);
		loadUnit((FFTAUnit) rosterPanel.roster.getSelectedValue(), true);
	}
	
	public void loadUnit(FFTAUnit newUnit, boolean mru)
	{
		firstRaceUpdate = true;
		minimalRaceUpdate = mru;
		
		FFTAJob newJob = newUnit.job;
		EquipSet newEquips = newUnit.equips;
		FFTACommand newA = newUnit.secondary;
		FFTASupport newS = newUnit.support;
		FFTAReaction newR = newUnit.reaction;
		FFTACombo newC = newUnit.combo;
		FFTAJob newGrowth = newUnit.statBase;
		
		unit = newUnit;
		
		
		nameField.setText(newUnit.name);
		updateValueOnly = true;
		levelSpinner.setValue(newUnit.getLevel());
		updateValueOnly = false;
		raceBox.setSelectedItem(newUnit.race);
		
		jobBox.setModel(new DefaultComboBoxModel<FFTAJob>(newUnit.race.jobs));
		primaryAbilityBox.setModel(new DefaultComboBoxModel<FFTACommand>(new FFTACommand[] {newUnit.job.command}));
		secondaryAbilityBox.setModel(new DefaultComboBoxModel<FFTACommand>(newUnit.race.aAbilities));
		sAbilityBox.setModel(new DefaultComboBoxModel<FFTASupport>(newUnit.race.sAbilities));
		rAbilityBox.setModel(new DefaultComboBoxModel<FFTAReaction>(newUnit.race.rAbilities));
		cAbilityBox.setModel(new DefaultComboBoxModel<FFTACombo>(newUnit.race.cAbilities));
		
		jobBox.setSelectedItem(newUnit.job);
		secondaryAbilityBox.setSelectedItem(newA);
		sAbilityBox.setSelectedItem(newS);
		rAbilityBox.setSelectedItem(newR);
		cAbilityBox.setSelectedItem(newC);
		
		
		
		growthJobBox.setModel(new DefaultComboBoxModel<FFTAJob>(newUnit.race.jobs));
		growthJobBox.setSelectedItem(newGrowth);
		
		equipmentList.setModel(newEquips.getListModel());
		
		spritePanel.setJob(newUnit.job);
		jobIconPanel.update();
		rosterPanel.roster.repaint();
		
		minimalRaceUpdate = false;		
		updateDeepStatsDisplay();
		updateShallowStatsDisplay();
	}
	
	public void updateDeepStatsDisplay()
	{
		lblHPScore.setText(Integer.toString((int) unit.maxHP));
		lblMPScore.setText(Integer.toString((int) unit.maxMP));
		lblWAtkScore.setText(Integer.toString(unit.getTotalWAtk()));
		lblWDefScore.setText(Integer.toString(unit.getTotalWDef()));
		lblMPowScore.setText(Integer.toString(unit.getTotalMPow()));
		lblMResScore.setText(Integer.toString(unit.getTotalMRes()));
		lblSpeedScore.setText(Integer.toString(unit.getTotalSpeed()));
	}
	
	public void updateShallowStatsDisplay()
	{
		lblMoveScore.setText(Integer.toString(unit.getTotalMove()));
		lblJumpScore.setText(Integer.toString(unit.getTotalJump()));
		lblEvadeScore.setText(Integer.toString(unit.getTotalEvade()));
		lblFPowScore.setText(Integer.toString(unit.getFightPower()));
	}
	
	class SecondaryAbilityCellRenderer implements ListCellRenderer<FFTACommand>
	{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		@Override
		public Component getListCellRendererComponent(JList<? extends FFTACommand> list, FFTACommand command, int index, boolean isSelected, boolean hasCellFocus)
		{
			JLabel result = (JLabel) defaultRenderer.getListCellRendererComponent(list, command, index, isSelected, hasCellFocus);
			
			if (command == unit.job.command)
				result.setText(" -");
			
			return result;
		}
	}
		
	
	
	public void changeRace(FFTARace race)
	{
		if (race != unit.race || firstRaceUpdate)
		{
			
			firstRaceUpdate = false;
			unit.changeRace(race, false);
			jobBox.setModel(new DefaultComboBoxModel<FFTAJob>(race.jobs));
			secondaryAbilityBox.setModel(new DefaultComboBoxModel<FFTACommand>(race.aAbilities));
			sAbilityBox.setModel(new DefaultComboBoxModel<FFTASupport>(race.sAbilities));
			rAbilityBox.setModel(new DefaultComboBoxModel<FFTAReaction>(race.rAbilities));
			cAbilityBox.setModel(new DefaultComboBoxModel<FFTACombo>(race.cAbilities));
			
			growthJobBox.setModel(new DefaultComboBoxModel<FFTAJob>(race.jobs));
			growthJobBox.setSelectedIndex(0);
			
			changeJob(race.jobs[0]);
		}
	}
	
	public void changeJob(FFTAJob job)
	{
		unit.changeJob(job);
		primaryAbilityBox.setModel(new DefaultComboBoxModel<FFTACommand>(new FFTACommand[] {job.command}));
		equipmentList.setModel(unit.equips.getListModel());
		spritePanel.setJob(job);
		jobIconPanel.update();
		
		if (job.command == unit.secondary)
			secondaryAbilityBox.setSelectedIndex(0);
		
		updateDeepStatsDisplay();
		updateShallowStatsDisplay();
		rosterPanel.roster.repaint();
	}
	
	class JobIconPanel extends JPanel
	{
		BufferedImage icon;
			
		public void update()
		{
			try {
				icon = ImageIO.read(new File("resources/icons/icon_" + unit.job.name() + ".png"));
			} catch (IOException e) {}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			g.drawImage(icon, 10, 4, null);
		}
	}
	
	@SuppressWarnings("serial")
	class ClanBuilderSpritePanel extends JPanel
	{
		BufferedImage sprite;
		
		public ClanBuilderSpritePanel(FFTAJob job)
		{
			super();
			setJob(job);
			setPreferredSize(new Dimension(40, 68));
		}

		public void setJob(FFTAJob job)
		{
			try {
				sprite = ImageIO.read(new File("resources/roster/" + job.name() + "_ally_stand_sw.png"));
			} catch (IOException e) {
				try
				{
					sprite = ImageIO.read(new File("resources/roster/soldier_ally_stand_sw.png"));
				} catch (IOException e2) {}
			}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(charBG, 2, 2, null);
			g.drawImage(sprite, 4, 2, null);
		}
	}
	
	
	@SuppressWarnings("serial")
	class EquipFrame extends JDialog
	{
		private JPanel contentPane;
		FFTAUnit unit;
		EquipSet newSet;
		JList<FFTAEquip> equippedList, arsenalList;
		JLabel itemNameLabel, itemDescLabel, itemEffectLabel2;
		DefaultListModel<FFTAEquip> equippedListModel;
		JLabel lblProjectedWAtkScore, lblProjectedWDefScore, lblProjectedMPowScore, lblProjectedMResScore,
			lblProjectedSpeedScore, lblProjectedMoveScore, lblProjectedJumpScore, lblProjectedEvadeScore;
		
		/**
		 * Create the frame.
		 */
		public EquipFrame(FFTAUnit unit)
		{
			setResizable(false);
			setModal(true);
			setLocationRelativeTo(this.getParent());
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.unit = unit;
			newSet = new EquipSet(unit.equips);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			equippedListModel = new DefaultListModel<FFTAEquip>();
			
			JPanel panel_5 = new JPanel();
			panel_5.setPreferredSize(new Dimension(296, 10));
			contentPane.add(panel_5, BorderLayout.WEST);
			panel_5.setLayout(new BorderLayout(0, 0));
			
			JPanel descriptionPanel = new JPanel();
			descriptionPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Description", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			descriptionPanel.setPreferredSize(new Dimension(10, 80));
			panel_5.add(descriptionPanel, BorderLayout.NORTH);
			GridBagLayout gbl_descriptionPanel = new GridBagLayout();
			gbl_descriptionPanel.columnWidths = new int[]{300, 0};
			gbl_descriptionPanel.rowHeights = new int[]{15, 15, 0};
			gbl_descriptionPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_descriptionPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			descriptionPanel.setLayout(gbl_descriptionPanel);
			
			itemNameLabel = new JLabel(" ");
			itemNameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_itemNameLabel = new GridBagConstraints();
			gbc_itemNameLabel.insets = new Insets(0, 10, 0, 0);
			gbc_itemNameLabel.fill = GridBagConstraints.BOTH;
			gbc_itemNameLabel.gridx = 0;
			gbc_itemNameLabel.gridy = 0;
			descriptionPanel.add(itemNameLabel, gbc_itemNameLabel);
			
			itemDescLabel = new JLabel("");
			itemDescLabel.setVerticalTextPosition(SwingConstants.TOP);
			itemDescLabel.setVerticalAlignment(SwingConstants.TOP);
			itemDescLabel.setPreferredSize(new Dimension(47, 60));
			GridBagConstraints gbc_itemDescLabel = new GridBagConstraints();
			gbc_itemDescLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_itemDescLabel.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc_itemDescLabel.insets = new Insets(0, 10, 0, 0);
			gbc_itemDescLabel.gridx = 0;
			gbc_itemDescLabel.gridy = 1;
			descriptionPanel.add(itemDescLabel, gbc_itemDescLabel);
			
			JPanel southPanel = new JPanel();
			southPanel.setPreferredSize(new Dimension(100, 10));
			panel_5.add(southPanel, BorderLayout.CENTER);
			southPanel.setLayout(new BorderLayout(0, 0));
			
			JPanel buttonPanel = new JPanel();
			southPanel.add(buttonPanel, BorderLayout.EAST);
			GridBagLayout gbl_buttonPanel = new GridBagLayout();
			gbl_buttonPanel.columnWidths = new int[] {30};
			gbl_buttonPanel.rowHeights = new int[] {30, 0};
			gbl_buttonPanel.columnWeights = new double[]{0.0};
			gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0};
			buttonPanel.setLayout(gbl_buttonPanel);
			
			JButton equipButton = new JButton("<");
			GridBagConstraints gbc_equipButton = new GridBagConstraints();
			gbc_equipButton.insets = new Insets(0, 0, 5, 0);
			gbc_equipButton.gridx = 0;
			gbc_equipButton.gridy = 0;
			equipButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed (ActionEvent e)
				{
					equipSelected();
				}
			});
			buttonPanel.add(equipButton, gbc_equipButton);
			
			JButton unequipButton = new JButton(">\r\n");
			GridBagConstraints gbc_unequipButton = new GridBagConstraints();
			gbc_unequipButton.insets = new Insets(0, 0, 5, 0);
			gbc_unequipButton.gridx = 0;
			gbc_unequipButton.gridy = 1;
			unequipButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e)
				{
					unequipSelected();
				}
			});
			buttonPanel.add(unequipButton, gbc_unequipButton);
			
			JPanel southwestPanel = new JPanel();
			southPanel.add(southwestPanel, BorderLayout.CENTER);
			southwestPanel.setLayout(new BorderLayout(0, 0));
			
			JPanel equipmentPanel = new JPanel();
			equipmentPanel.setPreferredSize(new Dimension(120, 10));
			southwestPanel.add(equipmentPanel, BorderLayout.EAST);
			equipmentPanel.setLayout(new BorderLayout(0, 0));
			
			equippedList = new JList<FFTAEquip>(equippedListModel);
			equippedList.addListSelectionListener(new EquipmentSelectionListener(equippedList));
			equippedList.setCellRenderer(ecr);
			equippedList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			equippedList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 2)
					{
						unequipSelected();
					}
				}
			});
			equipmentPanel.add(equippedList);
			
			JPanel equippedPaddingSouth = new JPanel();
			equippedPaddingSouth.setPreferredSize(new Dimension(10, 43));
			equipmentPanel.add(equippedPaddingSouth, BorderLayout.SOUTH);
			GridBagLayout gbl_equippedPaddingSouth = new GridBagLayout();
			gbl_equippedPaddingSouth.columnWidths = new int[] {45, 45, 0};
			gbl_equippedPaddingSouth.rowHeights = new int[] {25, 0};
			gbl_equippedPaddingSouth.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_equippedPaddingSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			equippedPaddingSouth.setLayout(gbl_equippedPaddingSouth);
			
			JButton btnOk = new JButton("OK");
			GridBagConstraints gbc_btnOk = new GridBagConstraints();
			gbc_btnOk.anchor = GridBagConstraints.WEST;
			gbc_btnOk.fill = GridBagConstraints.VERTICAL;
			gbc_btnOk.insets = new Insets(10, 0, 0, 5);
			gbc_btnOk.gridx = 0;
			gbc_btnOk.gridy = 0;
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					unit.equips = newSet;
					equipmentList.setModel(unit.equips.getListModel());
					updateDeepStatsDisplay();
					updateShallowStatsDisplay();
					dispose();
				}
			});			
			equippedPaddingSouth.add(btnOk, gbc_btnOk);
			
			JButton btnNotOk = new JButton("Not OK");
			GridBagConstraints gbc_btnNotOk = new GridBagConstraints();
			gbc_btnNotOk.insets = new Insets(10, 0, 0, 0);
			gbc_btnNotOk.anchor = GridBagConstraints.WEST;
			gbc_btnNotOk.fill = GridBagConstraints.VERTICAL;
			gbc_btnNotOk.gridx = 1;
			gbc_btnNotOk.gridy = 0;
			equippedPaddingSouth.add(btnNotOk, gbc_btnNotOk);
			btnNotOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			});
			
			JPanel equippedPaddingNorth = new JPanel();
			equippedPaddingNorth.setPreferredSize(new Dimension(10, 40));
			equipmentPanel.add(equippedPaddingNorth, BorderLayout.NORTH);
			equippedPaddingNorth.setLayout(new BorderLayout(10, 0));
			
			JLabel lblEquipped = new JLabel("Equipped");
			lblEquipped.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblEquipped.setHorizontalAlignment(SwingConstants.CENTER);
			lblEquipped.setInheritsPopupMenu(false);
			lblEquipped.setVerticalAlignment(SwingConstants.BOTTOM);
			equippedPaddingNorth.add(lblEquipped, BorderLayout.SOUTH);
			
			JPanel statLabels = new JPanel();
			statLabels.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			southwestPanel.add(statLabels, BorderLayout.CENTER);
			statLabels.setLayout(new BorderLayout(0, 0));
			
			JPanel statNamesPanel = new JPanel();
			statNamesPanel.setBorder(null);
			statLabels.add(statNamesPanel, BorderLayout.WEST);
			statNamesPanel.setLayout(new GridLayout(8, 1, 0, 0));
			
			JLabel lblWeaponAttack = new JLabel("Weapon Attack");
			statNamesPanel.add(lblWeaponAttack);
			lblWeaponAttack.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblWeaponDefense = new JLabel("Weapon Defense");
			statNamesPanel.add(lblWeaponDefense);
			lblWeaponDefense.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblMagicPower = new JLabel("Magic Power");
			statNamesPanel.add(lblMagicPower);
			lblMagicPower.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblMagicResistance = new JLabel("Magic Resistance");
			statNamesPanel.add(lblMagicResistance);
			lblMagicResistance.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblSpeed = new JLabel("Speed");
			statNamesPanel.add(lblSpeed);
			lblSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblMove = new JLabel("Move");
			statNamesPanel.add(lblMove);
			lblMove.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblJump = new JLabel("Jump");
			statNamesPanel.add(lblJump);
			lblJump.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JLabel lblEvade = new JLabel("Evade");
			statNamesPanel.add(lblEvade);
			lblEvade.setHorizontalAlignment(SwingConstants.RIGHT);
			
			JPanel statScoresPanel = new JPanel();
			statScoresPanel.setPreferredSize(new Dimension(38, 10));
			statLabels.add(statScoresPanel, BorderLayout.EAST);
			statScoresPanel.setLayout(new GridLayout(8, 0, 0, 0));
			
			lblProjectedWAtkScore = new JLabel();
			lblProjectedWAtkScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedWAtkScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedWAtkScore);
			
			lblProjectedWDefScore = new JLabel();
			lblProjectedWDefScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedWDefScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedWDefScore);
			
			lblProjectedMPowScore = new JLabel();
			lblProjectedMPowScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedMPowScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedMPowScore);
			
			lblProjectedMResScore = new JLabel();
			lblProjectedMResScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedMResScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedMResScore);
			
			lblProjectedSpeedScore = new JLabel();
			lblProjectedSpeedScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedSpeedScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedSpeedScore);
			
			lblProjectedMoveScore = new JLabel();
			lblProjectedMoveScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedMoveScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedMoveScore);
			
			lblProjectedJumpScore = new JLabel();
			lblProjectedJumpScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedJumpScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedJumpScore);
			
			lblProjectedEvadeScore = new JLabel();
			lblProjectedEvadeScore.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProjectedEvadeScore.setHorizontalAlignment(SwingConstants.CENTER);
			statScoresPanel.add(lblProjectedEvadeScore);
			
			JPanel equipSelectPanel = new JPanel();
			equipSelectPanel.setPreferredSize(new Dimension(128, 10));
			contentPane.add(equipSelectPanel, BorderLayout.EAST);
			GridBagLayout gbl_equipSelectPanel = new GridBagLayout();
			gbl_equipSelectPanel.columnWidths = new int[] {120, 0};
			gbl_equipSelectPanel.rowHeights = new int[] {26, 226, 0, 0};
			gbl_equipSelectPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_equipSelectPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			equipSelectPanel.setLayout(gbl_equipSelectPanel);
			
			//JComboBox equipBox = new JComboBox(new EquipType[] { EquipType.SWORD, EquipType.BLADE, EquipType.SABER, EquipType.KNIGHTSWORD, EquipType.GREATSWORD, EquipType.BROADSWORD, EquipType.KNIFE, EquipType.RAPIER, EquipType.KATANA, EquipType.STAFF, EquipType.ROD, EquipType.KNUCKLES, EquipType.SPEAR, EquipType.BOW, EquipType.GREATBOW, EquipType.INSTRUMENT, EquipType.GUN, EquipType.SOUL, EquipType.HAT, EquipType.HELM, EquipType.ARMOR, EquipType.CLOTHING, EquipType.VIERA_CLOTHES, EquipType.ROBE, EquipType.SHIELD, EquipType.BOOTS, EquipType.ACCESSORY, EquipType.GLOVES});
			JComboBox<EquipType> equipBox = new JComboBox<EquipType>(unit.getEquippableTypes());
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(0, 0, 2, 0);
			gbc_comboBox.gridx = 0;
			gbc_comboBox.gridy = 0;
			equipBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					EquipType eq = (EquipType) equipBox.getSelectedItem();
					changeEquipList(eq);
				}
			});
			
			equipSelectPanel.add(equipBox, gbc_comboBox);
			
			JScrollPane equipScrollPane = new JScrollPane();
			equipScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			GridBagConstraints gbc_equipScrollPane = new GridBagConstraints();
			gbc_equipScrollPane.fill = GridBagConstraints.BOTH;
			gbc_equipScrollPane.gridx = 0;
			gbc_equipScrollPane.gridy = 1;
			equipSelectPanel.add(equipScrollPane, gbc_equipScrollPane);
			
			arsenalList = new JList<FFTAEquip>();
			arsenalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			arsenalList.setCellRenderer(ecr);
			arsenalList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			arsenalList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 2)
					{
						equipSelected();
					}
				}
			});
			arsenalList.addListSelectionListener(new EquipmentSelectionListener(arsenalList));
			
			equipScrollPane.add(arsenalList);
			equipScrollPane.setViewportView(arsenalList);
			
			// Initialize the window
			changeEquipList(unit.job.equips[0]);
			updateEquippedList();
		}
		
		// Adds the item currently selected in the arsenalList to the equippedList
		public void equipSelected()
		{
			FFTAEquip eq = arsenalList.getSelectedValue();
			if (eq != null)
			{
				int slot = equippedList.getSelectedIndex();
				if (slot == -1)
					newSet.equip(eq);
				else
					newSet.equip(eq, slot);
				
				System.out.println("RIGHT: " + newSet.rightHand);
				System.out.println("LEFT:  " + newSet.leftHand);
				System.out.println("HEAD:  " + newSet.head);
				System.out.println("BODY:  " + newSet.body);
				System.out.println("ARMS:  " + newSet.arms);
				System.out.println("FEET:  " + newSet.feet);
				System.out.println();
				
				updateEquippedList();
			}
		}
		
		// Removes the currently selected item from equippedList
		public void unequipSelected()
		{
			int index = equippedList.getSelectedIndex();
			if (index != -1 && newSet.slots[index] != FFTAEquip.NONE)
			{
				newSet.unequip(index);
				updateEquippedList();
			}
		}
		
		// Updates the list of equipment shown in the equippedList according to equipment actually equipped,
		// then update the projected stats accordingly
		public void updateEquippedList()
		{
			equippedListModel.clear();
			
			for (int i = 0; i < 5; i++)
				equippedListModel.addElement(newSet.slots[i]);
			updateProjectedStatsDisplay();
		}
		
		public void updateProjectedStatsDisplay()
		{
			// projected stats
			int pWAtk = (int) unit.wAtk, pWDef = (int) unit.wDef, pMPow = (int) unit.mPow, pMRes = (int) unit.mRes,
					pSpeed = (int) unit.speed, pMove = unit.move, pJump = unit.jump, pEvade = unit.evade;
			
			for (int i = 0; i < 5; i++)
			{
				pWAtk += newSet.slots[i].wAtk;
				pWDef += newSet.slots[i].wDef;
				pMPow += newSet.slots[i].mPow;
				pMRes += newSet.slots[i].mRes;
				pSpeed += newSet.slots[i].speed;
				pMove += newSet.slots[i].move;
				pJump += newSet.slots[i].jump;
				pEvade += newSet.slots[i].evade;
			}
			
			lblProjectedWAtkScore.setText(Integer.toString(pWAtk));
			lblProjectedWDefScore.setText(Integer.toString(pWDef));
			lblProjectedMPowScore.setText(Integer.toString(pMPow));
			lblProjectedMResScore.setText(Integer.toString(pMRes));
			lblProjectedSpeedScore.setText(Integer.toString(pSpeed));
			lblProjectedMoveScore.setText(Integer.toString(pMove));
			lblProjectedJumpScore.setText(Integer.toString(pJump));
			lblProjectedEvadeScore.setText(Integer.toString(pEvade));
		}
		
		// Changes the equipment type displayed by the arsenalList on the right
		public void changeEquipList(EquipType eq)
		{
			DefaultListModel<FFTAEquip> model = new DefaultListModel<FFTAEquip>();
			FFTAEquip[] equipList = FFTAEquip.EQUIP_LIST[eq.ordinal()];
			for (FFTAEquip equip : equipList)
				model.addElement(equip);
			arsenalList.setModel(model);
		}
		
		// Listener for equippedList and arsenalList. Updates the item description whenever a new item
		// is selected from either list
		class EquipmentSelectionListener implements ListSelectionListener
		{
			JList<FFTAEquip> list;
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (list.getSelectedValue() != null)
				{
					itemNameLabel.setIcon(list.getSelectedValue().icon);
					itemNameLabel.setText(list.getSelectedValue().name);
					itemDescLabel.setText(list.getSelectedValue().getEffectString());
				}		
			}
			
			public EquipmentSelectionListener(JList<FFTAEquip> list)
			{
				this.list = list;
			}
		}
	}
}
