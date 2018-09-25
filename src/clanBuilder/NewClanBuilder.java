package clanBuilder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatter;

import fftadata.ActiveUnit;
import fftadata.Element;
import fftadata.EquipSet;
import fftadata.FFTACalc;
import fftadata.FFTACombo;
import fftadata.FFTACommand;
import fftadata.FFTAEquip;
import fftadata.FFTAJob;
import fftadata.FFTARace;
import fftadata.FFTAReaction;
import fftadata.FFTASkill;
import fftadata.FFTASkillDesc;
import fftadata.FFTASupport;
import fftadata.FFTAUnit;
import fftadata.KnownSkill;
import fftadata.NegatableStatusEffect;
import fftadata.StatusEffect;
import fftadata.Targeting;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.SystemColor;



public class NewClanBuilder extends JFrame {

	private JPanel contentPane;
	private JobIconPanel jobIconPanel;
	private JTextField nameField;
	private JComboBox<FFTARace> raceBox;
	private JComboBox<FFTAJob> jobBox, growthJobBox;
	private JTextField primaryAbilityBox;
	private JComboBox<FFTACommand> secondaryAbilityBox;
	private JComboBox<FFTASupport> sAbilityBox;
	private JComboBox<FFTAReaction> rAbilityBox;
	private JComboBox<FFTACombo> cAbilityBox;
	protected JList<FFTAEquip> equipmentList;
	protected EquipCellRenderer ecr;
	protected FFTAUnit unit;
	private JLabel lblHPScore, lblMPScore, lblMoveScore, lblJumpScore, lblEvadeScore, lblWAtkScore, lblWDefScore,
		lblMPowScore, lblMResScore, lblSpeedScore, lblFPowScore;
	private JButton btnLevels;
	private JRadioButton rdbtnAdvanced;
	private JRadioButton rdbtnSimple;
	private BufferedImage charBG;
	private ClanBuilderSpritePanel spritePanel;
	NewClanBuilderRosterPanel rosterPanel;
	
	boolean firstRaceUpdate,	// Makes the initial race setting work properly. After this initial update, changing to the same race shouldn't reset your job
			minimalRaceUpdate;	// Makes changing the race box only change combo box models, without updating job or revalidating equipment
	private JSpinner levelSpinner;
	protected boolean updateValueOnly;
	private JTextField notesField;
	private JTable abilityTable, primaryAbilityTable, secondaryAbilityTable;
	private JComboBox<FFTACommand> abilityListBox;
	private JPanel abilityListPanel;
	private JPanel eastPanel;
	private JPanel abilityTablePanel;
	private JScrollPane abilityTableScrollPane;
	private JTable itemTable;
	private JTextField bonusAbilityBox;
	private JTable monsterAbilityTable;
	private ListSelectionModel abilityTableSelectionModel;
	private ListSelectionListener abilityTableSelectionListener;
	private JLabel skillDescLabel;
	private String defaultSkillDesc = "<html><b>No skill selected.";
	private JLabel elementalResLabel;
	private JLabel statusResLabel;
	private JTable growthTable;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewClanBuilder frame = new NewClanBuilder();
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
	public NewClanBuilder() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		ImageIcon img = new ImageIcon("resources/misc/appicon2.png");
		setIconImage(img.getImage());
		
		setTitle("Clan Builder");
		unit = new FFTAUnit();
		ecr = new EquipCellRenderer();
		firstRaceUpdate = true;
		minimalRaceUpdate = true;
		
		setBounds(100, 100, 800, 600);
		setResizable(false);
		
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		ToolTipManager.sharedInstance().setInitialDelay(0);
		
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
			charBG = ImageIO.read(new File("resources/misc/charbg_large.png"));
		}
		catch (IOException e) { System.err.println("Couldn't find charbg_large.png"); charBG = null; }
		
		ButtonGroup levelButtonGroup = new ButtonGroup();
	    
		
		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.CENTER);
		westPanel.setLayout(new BorderLayout(0, 0));
		
		spritePanel = new ClanBuilderSpritePanel(unit.job);
		spritePanel.setPreferredSize(new Dimension(100, 100));
		spritePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		JPanel upperPanel = new JPanel();
		westPanel.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		upperPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel greaterSpritePanel = new JPanel();
		upperPanel.add(greaterSpritePanel, BorderLayout.WEST);
		greaterSpritePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel spriteBorderPanel = new JPanel();
		greaterSpritePanel.add(spriteBorderPanel, BorderLayout.NORTH);
		spriteBorderPanel.setBorder(new TitledBorder(null, "Sprite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spriteBorderPanel.setLayout(new BorderLayout(0, 0));
		spriteBorderPanel.add(spritePanel, BorderLayout.NORTH);
		
		jobIconPanel = new JobIconPanel(unit) ;
		jobIconPanel.setPreferredSize(new Dimension(16, 10));
		
		greaterSpritePanel.add(jobIconPanel, BorderLayout.SOUTH);
		
		JPanel basicsPanel = new JPanel();
		basicsPanel.setPreferredSize(new Dimension(220, 150));
		basicsPanel.setBorder(new TitledBorder(null, "Basics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upperPanel.add(basicsPanel, BorderLayout.CENTER);
		GridBagLayout gbl_basicsPanel = new GridBagLayout();
		gbl_basicsPanel.columnWidths = new int[] {31, 100, 0};
		gbl_basicsPanel.rowHeights = new int[] {112, 0};
		gbl_basicsPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
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
		basicLabelsPanel.setLayout(new GridLayout(0, 1, 0, 2));
		
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
		
		JLabel lblNotes = new JLabel("Notes");
		basicLabelsPanel.add(lblNotes);
		
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
		nameField.setDocument(new JTextFieldLimit(32));
		
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
		
		jobBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				FFTAJob job = (FFTAJob) jobBox.getSelectedItem();
				changeJob(job);
			}
		});
		
		JPanel levelSpinnerPanel = new JPanel();
		basicFieldsPanel.add(levelSpinnerPanel);
		levelSpinnerPanel.setLayout(new BorderLayout(0, 0));
		
		levelSpinner = new JSpinner();
		levelSpinnerPanel.add(levelSpinner, BorderLayout.WEST);
		levelSpinner.setModel(new SpinnerNumberModel(50, 1, 50, 1));
		
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
		
		notesField = new JTextField();
		basicFieldsPanel.add(notesField);
		notesField.setColumns(10);
		
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
		
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
	    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
	    formatter.setCommitsOnValidEdit(true);
		NewClanBuilder thisFrame = this;
		
		JPanel abilityPanel = new JPanel();
		abilityPanel.setPreferredSize(new Dimension(215, 150));
		abilityPanel.setBorder(new TitledBorder(null, "Abilities", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upperPanel.add(abilityPanel, BorderLayout.EAST);
		GridBagLayout gbl_abilityPanel = new GridBagLayout();
		gbl_abilityPanel.columnWidths = new int[] {51, 116, 20, 0};
		gbl_abilityPanel.rowHeights = new int[] {130, 0, 0};
		gbl_abilityPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_abilityPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		abilityPanel.setLayout(gbl_abilityPanel);
		
		JPanel abilityLabelsPanel = new JPanel();
		GridBagConstraints gbc_abilityLabelsPanel = new GridBagConstraints();
		gbc_abilityLabelsPanel.anchor = GridBagConstraints.WEST;
		gbc_abilityLabelsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_abilityLabelsPanel.insets = new Insets(0, 10, 5, 5);
		gbc_abilityLabelsPanel.gridx = 0;
		gbc_abilityLabelsPanel.gridy = 0;
		abilityPanel.add(abilityLabelsPanel, gbc_abilityLabelsPanel);
		abilityLabelsPanel.setLayout(new GridLayout(6, 1, 0, 2));
		
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
		
		JLabel lblBonus = new JLabel("Bonus");
		lblBonus.setHorizontalAlignment(SwingConstants.RIGHT);
		abilityLabelsPanel.add(lblBonus);
		
		JPanel abilityComboBoxesPanel = new JPanel();
		abilityComboBoxesPanel.setPreferredSize(new Dimension(10, 100));
		GridBagConstraints gbc_abilityComboBoxesPanel = new GridBagConstraints();
		gbc_abilityComboBoxesPanel.insets = new Insets(0, 5, 5, 5);
		gbc_abilityComboBoxesPanel.fill = GridBagConstraints.BOTH;
		gbc_abilityComboBoxesPanel.gridx = 1;
		gbc_abilityComboBoxesPanel.gridy = 0;
		abilityPanel.add(abilityComboBoxesPanel, gbc_abilityComboBoxesPanel);
		abilityComboBoxesPanel.setLayout(new GridLayout(6, 1, 0, 2));
		
		primaryAbilityBox = new JTextField();
		primaryAbilityBox.setEditable(false);
		abilityComboBoxesPanel.add(primaryAbilityBox);
		
		secondaryAbilityBox = new JComboBox<FFTACommand>();
		abilityComboBoxesPanel.add(secondaryAbilityBox);
		updateSecondaryAbilityBox();
		secondaryAbilityBox.setMaximumRowCount(13);
		
		sAbilityBox = new JComboBox<FFTASupport>();
		abilityComboBoxesPanel.add(sAbilityBox);
		sAbilityBox.setMaximumRowCount(14);
		
		rAbilityBox = new JComboBox<FFTAReaction>();
		abilityComboBoxesPanel.add(rAbilityBox);
		rAbilityBox.setMaximumRowCount(11);
		
		cAbilityBox = new JComboBox<FFTACombo>();
		cAbilityBox.setEnabled(false);
		abilityComboBoxesPanel.add(cAbilityBox);
		cAbilityBox.setMaximumRowCount(12);
		
		bonusAbilityBox = new JTextField();
		bonusAbilityBox.setEditable(false);
		abilityComboBoxesPanel.add(bonusAbilityBox);
		
		JPanel abilityInfoPanel = new JPanel();
		GridBagConstraints gbc_abilityInfoPanel = new GridBagConstraints();
		gbc_abilityInfoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_abilityInfoPanel.fill = GridBagConstraints.BOTH;
		gbc_abilityInfoPanel.gridx = 2;
		gbc_abilityInfoPanel.gridy = 0;
		abilityPanel.add(abilityInfoPanel, gbc_abilityInfoPanel);
		abilityInfoPanel.setLayout(new GridLayout(6, 1, 0, 2));
		
		JLabel primaryTooltipLabel = new JLabel("");
		abilityInfoPanel.add(primaryTooltipLabel);
		
		JLabel secondaryTooltipLabel = new JLabel("");
		abilityInfoPanel.add(secondaryTooltipLabel);
		
		JLabel supportTooltipLabel = new JLabel("<html>(<u>?</u>)");
		supportTooltipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		abilityInfoPanel.add(supportTooltipLabel);
		
		JLabel reactionTooltipLabel = new JLabel("<html>(<u>?</u>)");
		reactionTooltipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		abilityInfoPanel.add(reactionTooltipLabel);
		
		JLabel comboTooltipLabel = new JLabel("<html>(<u>?</u>)");
		comboTooltipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		comboTooltipLabel.setToolTipText("The Combo mechanic is not yet implemented.");
		abilityInfoPanel.add(comboTooltipLabel);
		
		JLabel bonusTooltipLabel = new JLabel("");
		abilityInfoPanel.add(bonusTooltipLabel);
		
//		JPanel resistancesPanel = new JPanel();
//		resistancesPanel.setBorder(new TitledBorder(null, "Resistances", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		GridBagConstraints gbc_resistancesPanel = new GridBagConstraints();
//		gbc_resistancesPanel.fill = GridBagConstraints.BOTH;
//		gbc_resistancesPanel.gridx = 3;
//		gbc_resistancesPanel.gridy = 0;
//		upperPanel.add(resistancesPanel, gbc_resistancesPanel);
		
		JPanel middlePanel = new JPanel();
		westPanel.add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
		levelButtonGroup.add(rdbtnSimple);
		levelButtonGroup.add(rdbtnAdvanced);
		
		JPanel statsAndGrowthsPanel = new JPanel();
		middlePanel.add(statsAndGrowthsPanel, BorderLayout.CENTER);
		statsAndGrowthsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		statsAndGrowthsPanel.add(statsPanel, BorderLayout.WEST);
		GridBagLayout gbl_statsPanel = new GridBagLayout();
		gbl_statsPanel.columnWidths = new int[] {30, 40, 110, 40, 0};
		gbl_statsPanel.rowHeights = new int[] {120, 0};
		gbl_statsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_statsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		statsPanel.setLayout(gbl_statsPanel);
		
		JPanel statNamesPane1 = new JPanel();
		GridBagConstraints gbc_statNamesPane1 = new GridBagConstraints();
		gbc_statNamesPane1.fill = GridBagConstraints.BOTH;
		gbc_statNamesPane1.insets = new Insets(0, 0, 0, 5);
		gbc_statNamesPane1.gridx = 0;
		gbc_statNamesPane1.gridy = 0;
		statsPanel.add(statNamesPane1, gbc_statNamesPane1);
		statNamesPane1.setLayout(new GridLayout(6, 1, 0, 0));
		
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
		
		JPanel statScoresPane1 = new JPanel();
		GridBagConstraints gbc_statScoresPane1 = new GridBagConstraints();
		gbc_statScoresPane1.fill = GridBagConstraints.BOTH;
		gbc_statScoresPane1.insets = new Insets(0, 0, 0, 5);
		gbc_statScoresPane1.gridx = 1;
		gbc_statScoresPane1.gridy = 0;
		statsPanel.add(statScoresPane1, gbc_statScoresPane1);
		statScoresPane1.setPreferredSize(new Dimension(35, 10));
		statScoresPane1.setLayout(new GridLayout(6, 1, 0, 0));
		
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
		
		JPanel statNamesPane2 = new JPanel();
		GridBagConstraints gbc_statNamesPane2 = new GridBagConstraints();
		gbc_statNamesPane2.fill = GridBagConstraints.BOTH;
		gbc_statNamesPane2.insets = new Insets(0, 0, 0, 5);
		gbc_statNamesPane2.gridx = 2;
		gbc_statNamesPane2.gridy = 0;
		statsPanel.add(statNamesPane2, gbc_statNamesPane2);
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
		
		JPanel statScoresPane2 = new JPanel();
		GridBagConstraints gbc_statScoresPane2 = new GridBagConstraints();
		gbc_statScoresPane2.fill = GridBagConstraints.BOTH;
		gbc_statScoresPane2.gridx = 3;
		gbc_statScoresPane2.gridy = 0;
		statsPanel.add(statScoresPane2, gbc_statScoresPane2);
		statScoresPane2.setPreferredSize(new Dimension(35, 10));
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
		
		JPanel levelsPanel = new JPanel();
		statsAndGrowthsPanel.add(levelsPanel);
		levelsPanel.setPreferredSize(new Dimension(104, 10));
		levelsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Stat Growths", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		levelsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		// levelsPanel.add(rdbtnSimple);
		
		growthJobBox = new JComboBox<FFTAJob>();
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
		levelsPanel.add(growthJobBox);
		
		growthTable = new JTable();
		growthTable.setPreferredSize(new Dimension(180, 90));
		updateGrowthTable();
		
		// levelsPanel.add(growthTable);
		
		JPanel equipLevelsPane = new JPanel();
		middlePanel.add(equipLevelsPane, BorderLayout.WEST);
		equipLevelsPane.setLayout(new BorderLayout(0, 0));
		
		JPanel equipPanel = new JPanel();
		equipLevelsPane.add(equipPanel, BorderLayout.NORTH);
		equipPanel.setBorder(new TitledBorder(null, "Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		equipPanel.setLayout(new BorderLayout(0, 0));
		
		equipmentList = new JList<FFTAEquip>();
		equipmentList.setPreferredSize(new Dimension(100, 94));
		equipmentList.setCellRenderer(ecr);
		equipmentList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		equipmentList.setVisibleRowCount(5);
		
		equipPanel.add(equipmentList, BorderLayout.NORTH);
		
		JButton btnChoose = new JButton("Choose...");
		equipPanel.add(btnChoose, BorderLayout.CENTER);
		
		JPanel resistancePanel = new JPanel();
		middlePanel.add(resistancePanel, BorderLayout.SOUTH);
		resistancePanel.setPreferredSize(new Dimension(10, 90));
		GridBagLayout gbl_resistancePanel = new GridBagLayout();
		gbl_resistancePanel.columnWidths = new int[] {279, 279, 0};
		gbl_resistancePanel.rowHeights = new int[]{90, 0};
		gbl_resistancePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_resistancePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		resistancePanel.setLayout(gbl_resistancePanel);
		
		JPanel elementalResistance = new JPanel();
		elementalResistance.setBorder(new TitledBorder(null, "Elemental Resistance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_elementalResistance = new GridBagConstraints();
		gbc_elementalResistance.fill = GridBagConstraints.BOTH;
		gbc_elementalResistance.insets = new Insets(0, 0, 0, 5);
		gbc_elementalResistance.gridx = 0;
		gbc_elementalResistance.gridy = 0;
		resistancePanel.add(elementalResistance, gbc_elementalResistance);
		elementalResistance.setLayout(new BorderLayout(0, 0));
		
		elementalResLabel = new JLabel("<html>\r\n<strong>Absorb:</strong> None.<br>\r\n<strong>Immune:</strong> None.<br>\r\n<strong>Half:</strong> None.<br>\r\n<strong>Weak:</strong> None.<br>");
		elementalResistance.add(elementalResLabel, BorderLayout.NORTH);
		elementalResLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
		
		JPanel statusResPanel = new JPanel();
		statusResPanel.setBorder(new TitledBorder(null, "Status Resistance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_statusResPanel = new GridBagConstraints();
		gbc_statusResPanel.fill = GridBagConstraints.BOTH;
		gbc_statusResPanel.gridx = 1;
		gbc_statusResPanel.gridy = 0;
		resistancePanel.add(statusResPanel, gbc_statusResPanel);
		statusResPanel.setLayout(new BorderLayout(0, 0));
		
		statusResLabel = new JLabel("<html>\r\n<strong>Immune:</strong> None.<br>");
		statusResPanel.add(statusResLabel, BorderLayout.NORTH);
		statusResLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				EquipFrame ef = new EquipFrame(thisFrame);
				ef.setVisible(true);
			}
		});
		
		rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.setSelected(true);
		rdbtnSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				rdbtnAdvanced.setSelected(false);
				growthJobBox.setEnabled(true);
				btnLevels.setEnabled(false);
			}
		});
		
		JPanel lowerPanel = new JPanel();
		westPanel.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel saveExitPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) saveExitPanel.getLayout();
		lowerPanel.add(saveExitPanel, BorderLayout.SOUTH);
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
				// lowerPanel.add(rosterPanel, BorderLayout.CENTER);
				
				// JLabel current = new JLabel(rosterPanel.roster.getSelectedIndex() + 6 * rosterPanel.roster.currentPage + 1 + "/" + rosterPanel.roster.unitList.size());
				// saveExitPanel.add(current);
				
				JPanel compactRosterPanel = new JPanel();
				compactRosterPanel.setBorder(new TitledBorder(null, "Roster", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				compactRosterPanel.setPreferredSize(new Dimension(10, 150));
				lowerPanel.add(compactRosterPanel, BorderLayout.CENTER);
		
		rdbtnAdvanced = new JRadioButton("Advanced");
		rdbtnAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				rdbtnSimple.setSelected(false);
				growthJobBox.setEnabled(false);
				btnLevels.setEnabled(true);
			}
		});
		// levelsPanel.add(rdbtnAdvanced);
		
		btnLevels = new JButton("Levels...");
		btnLevels.setEnabled(false);
		// levelsPanel.add(btnLevels);
		
		
		
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
				FFTAReaction rab = (FFTAReaction) rAbilityBox.getSelectedItem(); 
				unit.reaction = rab;
			
				if (rab == FFTAReaction.NONE)
					reactionTooltipLabel.setToolTipText(null);
				else
				{
					String tt = "<html><p width=\"225\"><strong>" +rab.shortDesc + "</strong>";
					if (rab.longDesc != null)
						tt += "<br>" + rab.longDesc;
					reactionTooltipLabel.setToolTipText(tt);
				}
			}
		});
		sAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FFTASupport sab = (FFTASupport) sAbilityBox.getSelectedItem(); 
				unit.support = sab;
				unit.equips.removeIllegalEquipment();
				equipmentList.setModel(unit.equips.getListModel());
				updateResistances();
				
				if (sab == FFTASupport.NONE)
					supportTooltipLabel.setToolTipText(null);
				else
				{
					String tt = "<html><p width=\"225\"><strong>" +sab.shortDesc + "</strong>";
					if (sab.longDesc != null)
						tt += "<br>" + sab.longDesc;
					supportTooltipLabel.setToolTipText(tt);
				}
			}
		});
		secondaryAbilityBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				if (ItemEvent.DESELECTED != ItemEvent.SELECTED)
					changeSecondaryAbility((FFTACommand) secondaryAbilityBox.getSelectedItem());
			}
		});
		
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(225, 10));
		contentPane.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		abilityListPanel = new JPanel();
		abilityListPanel.setBorder(new TitledBorder(null, "Ability List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		eastPanel.add(abilityListPanel, BorderLayout.NORTH);
		
		// Ability list panel
		// Ability table
		primaryAbilityTable = makeAbilityTable(unit.job.command);
		if (unit.secondary != FFTACommand.NONE)
			secondaryAbilityTable = makeAbilityTable(unit.secondary);
		else
			secondaryAbilityTable = null;
		
		abilityTable = primaryAbilityTable;
		
		abilityTableSelectionListener = new ListSelectionListener() 
		{
			
			public void valueChanged(ListSelectionEvent e)
			{
				if (e.getValueIsAdjusting())
			        return;
				
			    // e.getSource() returns an object like this
			    // javax.swing.DefaultListSelectionModel 1052752867 ={11}
			    // where 11 is the index of selected element when mouse button is released

			    String strSource= e.getSource().toString();
			    
		    	int start = strSource.indexOf("{") + 1;
		    	String indexStr = strSource.substring(start, strSource.length() - 1);
			    
		    	// If the index string is empty, it means this listener was called upon deselection, in which case the skill description label
		    	// should display the default description, rather than that of a specific skill
		    	if (indexStr.length() > 0)
		    	{
		    		FFTASkill sk = (FFTASkill) abilityTable.getModel().getValueAt(Integer.parseInt(indexStr), 1);
				    skillDescLabel.setText(makeSkillDesc(sk));
		    	}
		    	else
		    		skillDescLabel.setText(defaultSkillDesc);
			}
			
		};
		
		// Other tables
		itemTable = makeAlwaysActiveAbilityTable(FFTACommand.ITEM);
		
		skillDescLabel = new JLabel(defaultSkillDesc);
		
		abilityListBox = new JComboBox<FFTACommand>();
		abilityListBox.addActionListener(new ActionListener() {
			@Override

			// Replace the currently displayed ability list with the one selected
			public void actionPerformed(ActionEvent e)
			{
				FFTACommand cmd = (FFTACommand) abilityListBox.getSelectedItem();
				// abilityTableScrollPane.remove(abilityTable);
				
				if (cmd == FFTACommand.ITEM)
				{
					abilityTable = itemTable;
				}
				else if (cmd == unit.job.command)
				{
					abilityTable = primaryAbilityTable;
				}
				else if (cmd == unit.secondary)
				{
					abilityTable = secondaryAbilityTable;
				}
				else
					abilityTable = makeAlwaysActiveAbilityTable(cmd);
				
				// skillDescLabel.setText(defaultSkillDesc);
				abilityTableScrollPane.setViewportView(abilityTable);
				
				abilityTable.clearSelection();
				skillDescLabel.setText(defaultSkillDesc);
				
				repaint();
				revalidate();
			}
		});
		

		abilityListPanel.setLayout(new BorderLayout(0, 0));
		abilityListPanel.add(abilityListBox, BorderLayout.NORTH);
		
		abilityTablePanel = new JPanel();
		abilityTablePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		abilityTablePanel.setBackground(Color.WHITE);
		abilityListPanel.add(abilityTablePanel, BorderLayout.CENTER);
		abilityTablePanel.setLayout(new BoxLayout(abilityTablePanel, BoxLayout.X_AXIS));
		
		JPanel abilityTableInnerPanel = new JPanel();
		abilityTableInnerPanel.setBackground(Color.WHITE);
		abilityTableInnerPanel.setMaximumSize(new Dimension(32767, 200));
		abilityTableInnerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		abilityTablePanel.add(abilityTableInnerPanel);
		abilityTableInnerPanel.setLayout(new BorderLayout(0, 0));
		
		abilityTableScrollPane = new JScrollPane(abilityTable);
		abilityTableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		abilityTableScrollPane.getViewport().setBackground(Color.WHITE);
		abilityTableScrollPane.setPreferredSize(new Dimension(10, 133));
		abilityTableScrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		abilityTableScrollPane.setMaximumSize(new Dimension(32767, 200));
		abilityTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		abilityTableInnerPanel.add(abilityTableScrollPane, BorderLayout.NORTH);
		abilityTablePanel.add(abilityTableInnerPanel);
		
		JPanel abilityDescPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) abilityDescPanel.getLayout();
		abilityListPanel.add(abilityDescPanel, BorderLayout.SOUTH);
		abilityDescPanel.setBackground(SystemColor.menu);
		abilityDescPanel.setPreferredSize(new Dimension(10, 355));
		
		skillDescLabel = new JLabel(defaultSkillDesc);
		abilityDescPanel.add(skillDescLabel);
		
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
		
		rosterPanel = new NewClanBuilderRosterPanel();
		rosterPanel.setMaximumSize(new Dimension(10, 10));
		
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
				
				
				
				// current.setText(rosterPanel.roster.getSelectedIndex() + 6 * rosterPanel.roster.currentPage + 1 + "/" + rosterPanel.roster.unitList.size());
			}
		});
		changeSecondaryAbility(unit.secondary);
		
		rosterPanel.roster.setSelectedIndex(0);
		loadUnit(new FFTAUnit(), false);
		loadUnit((FFTAUnit) rosterPanel.roster.getSelectedValue(), true);

		abilityListBox.setSelectedIndex(0);
	}
	
	private JTable makeAbilityTable(FFTACommand command)
	{
		KnownSkill[] skillList = null;
		if (command == unit.job.command)
			skillList = unit.primarySkills;
		else if (command == unit.secondary)
			skillList = unit.secondarySkills;
		
		// Create data and header
		Object[][] abilityData = new Object[skillList.length][3];
		for (int i = 0; i < skillList.length; i++)
		{
			KnownSkill ks = skillList[i];
			abilityData[i] = new Object[] { ks.active, ks.skill, ""};
			if (ks.skill.MP_COST > 0)
				abilityData[i][2] = ks.skill.MP_COST + " MP";
		}
		String[] headers = new String[] {"Active", "Name", "Cost"};
		
		return makeTableModelAndFormat(abilityData, headers, true);
	}
	
	private JTable makeAlwaysActiveAbilityTable(FFTACommand command)
	{
		// Create data and header
		Object[][] abilityData = new Object[command.SKILLS.length][3];
		for (int i = 0; i < command.SKILLS.length; i++)
		{
			abilityData[i] = new Object[] { "", command.SKILLS[i], ""};
		}
		
		String[] headers = new String[] {"Active", "Name", "Cost"};
        return makeTableModelAndFormat(abilityData, headers, false);
	}
	
	private JTable makeTableModelAndFormat(Object[][] abilityData, String[] headers, boolean editable)
	{
		// Create table model
		DefaultTableModel model = new DefaultTableModel(abilityData, headers);
		
		JTable table = new JTable(model) {

			private static final long serialVersionUID = 1L;
			private boolean learnable = editable;
			private DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			private DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		    
		    {
		    	leftRenderer = new DefaultTableCellRenderer() {
		    		
		    		@Override
		    		public void setValue(Object aValue) 
		    		{
		    			Object result = aValue;
		    			if (aValue != null)
		    			{
		    				if (aValue instanceof FFTASkill)
		    					if (! ((FFTASkill) aValue).IMPLEMENTED )
		    						setForeground(Color.LIGHT_GRAY);
		    					else
		    						setForeground(Color.BLACK);
		    				else if (aValue instanceof String && (((String) aValue).contains(" MP")))
		    					super.setHorizontalAlignment(SwingConstants.RIGHT);
		    			}
		    			
		    			super.setValue(result);
		    		}
		    	};
		    }

		    @Override
		    public Class getColumnClass(int column) {
		        switch (column) {
		            case 0:
		            	if (learnable)
		            		return Boolean.class;
		            	else
		            		return String.class;
		            default:
		            	return String.class;
		        }
		    }
		    
		    @Override
		    public boolean isCellEditable(int row, int col)
		    {
		    	if (col == 0 && learnable)
		    		return true;
		    	else
		    		return false;
		    }
		    
//		    @Override
		    public TableCellRenderer getCellRenderer(int row, int col)
		    {
		    	TableCellRenderer renderer;
		    	if (col == 2)
		    		renderer = rightRenderer;
		    	else if (col == 1)
		    		renderer = leftRenderer;
		    	else
		    		renderer = super.getCellRenderer(row, col);
		    	
		    	return renderer;
		    }
		    
		    
		};
		
		// Format the table
        table.setShowGrid(false);
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(1);
        table.setBorder(null);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFocusable(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(abilityTableSelectionListener);
        table.getTableHeader().setUI(null);
        
        return table;
	}
	
	private void updateSecondaryAbilityBox()
	{
		Object currentSelection = secondaryAbilityBox.getSelectedItem();
		
		FFTACommand[] aabs = unit.race.aAbilities;
		FFTACommand[] cmds;
		if (unit.job == FFTAJob.ALCHEMIST)
			cmds = new FFTACommand[aabs.length - 2];
		else
			cmds = new FFTACommand[aabs.length - 1];
		
		int exclude = 0;
		for (int i = 0; i < aabs.length; i++)
		{
			if (aabs[i] == unit.job.command)
				exclude++;
			else if (unit.job == FFTAJob.ALCHEMIST && aabs[i] == FFTACommand.ITEM)
				exclude++;
			else
				cmds[i - exclude] = aabs[i];
		}
		
		secondaryAbilityBox.setModel(new DefaultComboBoxModel<FFTACommand>(cmds));
		if (currentSelection != null)
			secondaryAbilityBox.setSelectedItem(currentSelection);
	}
	
	private void updateAbilityListBox()
	{
		ArrayList<FFTACommand> commands = new ArrayList<FFTACommand>();
		commands.add(unit.job.command);
		if (unit.secondary != FFTACommand.NONE)
			commands.add(unit.secondary);
		if (unit.bonus != FFTACommand.NONE)
			commands.add(unit.bonus);
		if (unit.job == FFTAJob.MORPHER || unit.secondary == FFTACommand.MORPH)
		{
			// Check morpher abilities
			KnownSkill[] morphs = null;
			if (unit.job == FFTAJob.MORPHER)
				morphs = unit.primarySkills;
			else
				morphs = unit.secondarySkills;
			
			for (int i = 0; i < morphs.length; i++)
				if (morphs[i].active)
					commands.add(FFTACommand.values()[FFTACommand.GOBLIN.ordinal() + i]);
		}
		abilityListBox.setModel(new DefaultComboBoxModel<FFTACommand>(commands.toArray(new FFTACommand[0])));
	}
	
	void updateResistances()
	{
		// Elemental resistances
		String text = "<html><p width=\"250\">";
		// Determine what elements the unit resists and encode them in ints
		int[] resLevels = new int[5];
		for (int i = 0; i < 8; i++)
		{
			int res = unit.getElementalResistance(Element.values()[i + 1], false);
			resLevels[res - 1] = resLevels[res - 1] | (1 << i);
			
		}
		
		// Decode the resistance ints and print resistances/weaknesses
		text += getElementalResistanceString("Absorb", resLevels[4]);
		text += getElementalResistanceString("Null", resLevels[3]);
		text += getElementalResistanceString("Half", resLevels[2]);
		text += getElementalResistanceString("Weak", resLevels[0]);
		
		elementalResLabel.setText(text);
		
		// Status resistances
		text = "<html><p width=\"250\"><strong>Immune:</strong> ";

		String effList = "";
		
		for (NegatableStatusEffect nEff : NegatableStatusEffect.values())
			if (FFTACalc.checkForItemEffects(unit, nEff.NEGATORS) || FFTACalc.supportNegates(unit, nEff.STATUS_EFFECT))
				effList += nEff.STATUS_EFFECT.NAME + ", ";
		
		if (effList.length() == 0)
			text += "None";
		else
			text += effList.substring(0, effList.length() - 2);
		
		statusResLabel.setText(text);
	}
	
	private void updateGrowthTable()
	{
		Object[][] growthData = new Object[][] {
			{ "<html><strong>Stat",	"<html><strong>Base",	"<html><strong>Per Level"}, 
			{ "HP",		unit.job.baseHP,	unit.job.growthHP },
			{ "MP",		unit.job.baseMP,	unit.job.growthMP },
			{ "WAtk",	unit.job.baseWAtk,	unit.job.growthWAtk },
			{ "WDef",	unit.job.baseWDef,	unit.job.growthWDef },
			{ "MPow",	unit.job.baseMPow,	unit.job.growthMPow },
			{ "MRes",	unit.job.baseMRes,	unit.job.growthMRes },
			{ "Speed",	unit.job.baseSpeed,	unit.job.growthSpeed }
		};
		
		String[] headers = new String[] {"Stat", "Base", "Per level"};
		
		growthTable = new JTable(growthData, headers);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		growthTable.setShowGrid(false);
		TableColumn column = growthTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column.setCellRenderer(renderer);
        column = growthTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(50);
        column.setCellRenderer(renderer);
        column = growthTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(70);
        column.setCellRenderer(renderer);
        
        growthTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		growthTable.setRowSelectionAllowed(false);
        growthTable.getTableHeader().setUI(null);
	}
	
	
	private String getElementalResistanceString(String type, int resLevel)
	{
		String result = "";
		
		result += "<strong>" + type + ":</strong> ";
		
		// Don't print resistance
		if (resLevel == 0)
			return result + "None<br>";
		
	
		for (int j = 0; j < 8; j++)
			if (((resLevel >> j) & 1) == 1)
				result += Element.values()[j + 1] + ", ";
		
		return result.substring(0, result.length() - 2) + "<br>";
	}
	
	private String makeSkillDesc(FFTASkill sk)
	{
		FFTASkillDesc desc = FFTASkillDesc.values()[sk.ordinal()];
		String result = "<html><p width=\"200\"><strong>" + sk.NAME + "</strong>";
		if (!sk.IMPLEMENTED)
			return result + "<br><span color=\"red\">This ability is not implemented.</span>"; 
		
		// Basic description
		if (desc.SHORT_DESC.length() > 0)
			result += "<br><em>" + desc.SHORT_DESC + "</em><br>";
		
		// Extra details
		if (desc.LONG_DESC.length() > 0)
			result += "<br>" + desc.LONG_DESC + "<br>";
		
		// Basic statistics
		result += "<br>Range: ";
		switch (sk.TARGETING)
		{
			case ALL:
				result += "All units on map"; break;
			case AS_WEAPON:
				result += "As weapon"; break;
			case BIDIRECTIONAL:
				result += "Infinite in front and behind"; break;
			case ALL_ENEMIES:
				result += "All enemies"; break;
			case CONE:
				result += "Cone"; break;
			case DIRECTIONAL:
				result += "Infinite in front"; break;
			case FREE_SELECT:
				
				if (sk.H_RANGE > 0)
					result += sk.H_RANGE;
				else
					result += "Self";
				
				if (sk.H_RADIUS == 1)
					result += " (small area)";
				else if (sk.H_RADIUS == 2)
					result += " (large area)";
				break;
			case SAME_LEVEL_DIGIT:
				result += "All applicable units on map"; break;
			case SELF_CENTER:
				if (sk.H_RADIUS == 0)
					result += "Self";
				else
				{
					result += "Surrounding units";
					if (sk.SELF_TARGET)
						result += " (including self)";
				}
				break;
			default:
				break;
		}
		
		// Power
		if (sk.POWER != 0)
		{
			result += "<br>Power: ";
			if (sk.POWER > 0)
				result += sk.POWER;
			else
				result += "As weapon";
		}
		
		// Element
		if (sk.ELEMENT != Element.NULL)
			result += "<br>Element: " + sk.ELEMENT;
		
		// Damage type
		if (sk.POWER != 0)
		{
			result += "<br>Damage type: ";
			if (sk.IS_PHYSICAL)
				result += "Physical";
			else
				result += "Magical";
		}
		
		// MP Cost
		if (sk.MP_COST > 0)
			result += "<br>Cost: " + sk.MP_COST + " MP";

		result += "<br>";
		
		
		
		return result;
	}
	
	private void loadUnit(FFTAUnit newUnit, boolean mru)
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
		updateSecondaryAbilityBox();
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
		lblFPowScore.setText(Integer.toString(unit.getWAtkEquipBonus()));
	}
	
	
		
	
	
	public void changeRace(FFTARace race)
	{
		if (race != unit.race || firstRaceUpdate)
		{
			
			firstRaceUpdate = false;
			unit.changeRace(race, false);
			jobBox.setModel(new DefaultComboBoxModel<FFTAJob>(race.jobs));
			
			sAbilityBox.setModel(new DefaultComboBoxModel<FFTASupport>(race.sAbilities));
			rAbilityBox.setModel(new DefaultComboBoxModel<FFTAReaction>(race.rAbilities));
			cAbilityBox.setModel(new DefaultComboBoxModel<FFTACombo>(race.cAbilities));
			
			growthJobBox.setModel(new DefaultComboBoxModel<FFTAJob>(race.jobs));
			growthJobBox.setSelectedIndex(0);
			
			changeJob(race.jobs[0]);
			updateSecondaryAbilityBox();
		}
	}
	
	public void changeJob(FFTAJob job)
	{
		unit.changeJob(job);
		primaryAbilityBox.setText(job.command.NAME);
		equipmentList.setModel(unit.equips.getListModel());
		spritePanel.setJob(job);
		jobIconPanel.update();
		
		if (job.command == unit.secondary)
			secondaryAbilityBox.setSelectedIndex(0);
		
		updateSecondaryAbilityBox();
		
		if (job == FFTAJob.ALCHEMIST)
			bonusAbilityBox.setText("Item");
		else
			bonusAbilityBox.setText(" - ");
		
		updateDeepStatsDisplay();
		updateShallowStatsDisplay();
		rosterPanel.roster.repaint();
		
		// Update skills list
		updateAbilityListBox();
		primaryAbilityTable = makeAbilityTable(job.command);
		abilityTable = primaryAbilityTable;
		abilityTableScrollPane.setViewportView(abilityTable);

		repaint();
		revalidate();
		
		abilityTable.getSelectionModel().addListSelectionListener(abilityTableSelectionListener);
		abilityTable.clearSelection();
		skillDescLabel.setText(defaultSkillDesc);

		updateResistances();
	}
	
	public void changeSecondaryAbility(FFTACommand secondary)
	{
		// Deselect secondary ability if same as primary
		if (secondary == unit.job.command)
		{
			secondaryAbilityBox.setSelectedIndex(0);
			secondary = FFTACommand.NONE;
		}
		
		// Set secondary ability in unit's object
		unit.setSecondary(secondary);
		
		// Make secondary ability table
		JTable oldSecondaryTable = secondaryAbilityTable;
		if (secondary == FFTACommand.ITEM)
			secondaryAbilityTable = itemTable;
		else
			secondaryAbilityTable = makeAbilityTable(secondary);
		
		// Set secondary ability in ability list combobox
		updateAbilityListBox();
		
		// If presently displaying the secondary ability table, update the display to reflect the change by reselecting it;
		// or, if selecting the NONE ability, select and display the primary ability table instead
		// if (abilityTable == oldSecondaryTable)
			if (secondary == FFTACommand.NONE)
				abilityListBox.setSelectedIndex(0);
			else
			{
				abilityListBox.setSelectedItem(secondary);
			}
		
		// If presently displaying the item table, and Item isn't the unit's secondary ability (i.e., the unit is an Alchemist),
		// keep item selected
//		else if (abilityTable == itemTable)
//			abilityListBox.setSelectedItem(FFTACommand.ITEM);
//		
//		// Otherwise, we've been looking at a Morph user's monster ability, and the procedure is the same as in the first case
//		else if (abilityTable != primaryAbilityTable)
//			if (secondary == FFTACommand.NONE)
//				abilityListBox.setSelectedIndex(0);
//			else
//			{
//				abilityListBox.setSelectedItem(secondary);
//				abilityTable.clearSelection();
//			}
	}
	
	
}
