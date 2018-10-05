package clanBuilder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.Icon;
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
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.PlainDocument;

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
import javax.swing.border.SoftBevelBorder;



public class ClanBuilder extends JFrame {

	private JPanel contentPane;
	private JPanel jobIconPanel;
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
	protected RosterUnit unit;
	private JLabel lblHPScore, lblMPScore, lblMoveScore, lblJumpScore, lblEvadeScore, lblWAtkScore, lblWDefScore,
		lblMPowScore, lblMResScore, lblSpeedScore, lblFPowScore;
	private JButton btnLevels;
	private JRadioButton rdbtnAdvanced;
	private JRadioButton rdbtnSimple;
	private BufferedImage charBG;
	private ClanBuilderSpritePanel spritePanel;
	
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
	private JTable table_1;
	private JPanel compactRosterPanel;
	private ClanBuilderRoster roster;
	private JTable rosterTable;
	private JLabel primaryIconLabel;
	private JLabel secondaryIconLabel;
	private JLabel reactionTooltipLabel;
	private JLabel supportTooltipLabel;
	private JLabel lblGrowthWDef;
	private JLabel lblGrowthMResScore;
	private JLabel lblGrowthSpeedScore;
	private JLabel lblGrowthMPowScore;
	private JLabel lblGrowthMPScore;
	private JLabel lblGrowthWDefScore;
	private JLabel lblGrowthHPScore;
	private JLabel lblGrowthWAtkScore;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClanBuilder frame = new ClanBuilder();
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
	public ClanBuilder() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		ImageIcon img = new ImageIcon("resources/misc/appicon2.png");
		setIconImage(img.getImage());
		
		setTitle("Clan Builder");
		unit = new RosterUnit();
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
		
		spritePanel = new ClanBuilderSpritePanel(unit.base.job);
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
		
		jobIconPanel = new JPanel() ;
		jobIconPanel.setPreferredSize(new Dimension(16, 10));
		
		greaterSpritePanel.add(jobIconPanel, BorderLayout.CENTER);
		
		primaryIconLabel = new JLabel("");
		primaryIconLabel.setIcon(new ImageIcon(ClanBuilder.class.getResource("/icons/icon_alchemist.png")));
		jobIconPanel.add(primaryIconLabel);
		
		secondaryIconLabel = new JLabel("");
		secondaryIconLabel.setIcon(new ImageIcon(ClanBuilder.class.getResource("/icons/icon_archer_h.png")));
		jobIconPanel.add(secondaryIconLabel);
		
		JPanel basicsPanel = new JPanel();
		basicsPanel.setPreferredSize(new Dimension(220, 150));
		basicsPanel.setBorder(new TitledBorder(null, "Basics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upperPanel.add(basicsPanel, BorderLayout.CENTER);
		GridBagLayout gbl_basicsPanel = new GridBagLayout();
		gbl_basicsPanel.columnWidths = new int[] {31, 100, 0};
		gbl_basicsPanel.rowHeights = new int[] {130, 0};
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
		basicLabelsPanel.setLayout(new GridLayout(6, 1, 0, 2));
		
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
		basicFieldsPanel.setLayout(new GridLayout(6, 1, 2, 2));
		
		nameField = new JTextField();
		nameField.setVerifyInputWhenFocusTarget(false);
		basicFieldsPanel.add(nameField);
		nameField.setColumns(10);
		nameField.setDocument(new JTextFieldLimit(32, true));
		
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			
			String text;
			
			@Override
			public void changedUpdate(DocumentEvent e) { }

			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				text = nameField.getText();
				unit.base.name = text;
				
				// Update name in roster
				rosterTable.setValueAt(" " + text, rosterTable.getSelectedRow(), 0);
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				text = nameField.getText();
				unit.base.name = text;
				
				// Update name in roster
				rosterTable.setValueAt(" " + text, rosterTable.getSelectedRow(), 0);
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
		    		unit.base.levels[growthJobBox.getSelectedIndex()] = (Integer) levelSpinner.getValue() - 1;
					unit.base.updateDeepStats();
					updateDeepStatsDisplay();
		    	}
		    	
		    	rosterTable.setValueAt(unit.base.getLevel(), rosterTable.getSelectedRow(), 1);
		    }
		});
		
		notesField = new JTextField();
		basicFieldsPanel.add(notesField);
		notesField.setColumns(10);
		notesField.setDocument(new JTextFieldLimit(50, false));
		notesField.getDocument().addDocumentListener(new DocumentListener() {
			
			String text;
			
			@Override
			public void changedUpdate(DocumentEvent e) { }

			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				update();
			}
			
			private void update()
			{
				text = notesField.getText();
				unit.note = text;
				
				// Update notes in roster
				rosterTable.setValueAt(" " + text, rosterTable.getSelectedRow(), 7);
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
		
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
	    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
	    formatter.setCommitsOnValidEdit(true);
		ClanBuilder thisFrame = this;
		
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
		
		JLabel lblReaction = new JLabel("Reaction");
		abilityLabelsPanel.add(lblReaction);
		lblReaction.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSupport = new JLabel("Support");
		abilityLabelsPanel.add(lblSupport);
		lblSupport.setHorizontalAlignment(SwingConstants.RIGHT);
		
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
		
		rAbilityBox = new JComboBox<FFTAReaction>();
		abilityComboBoxesPanel.add(rAbilityBox);
		rAbilityBox.setMaximumRowCount(11);
		rAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FFTAReaction rab = (FFTAReaction) rAbilityBox.getSelectedItem(); 
				unit.base.reaction = rab;
			
				if (rab == FFTAReaction.NONE)
					reactionTooltipLabel.setToolTipText(null);
				else
				{
					String tt = "<html><p width=\"225\"><strong>" +rab.shortDesc + "</strong>";
					if (rab.longDesc != null)
						tt += "<br>" + rab.longDesc;
					reactionTooltipLabel.setToolTipText(tt);
				}
				
				// Update roster view
				rosterTable.setValueAt(" " + unit.base.reaction, rosterTable.getSelectedRow(), 5);
			}
		});
		
		sAbilityBox = new JComboBox<FFTASupport>();
		abilityComboBoxesPanel.add(sAbilityBox);
		sAbilityBox.setMaximumRowCount(14);
		
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
		
		reactionTooltipLabel = new JLabel("<html>(<u>?</u>)");
		reactionTooltipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		abilityInfoPanel.add(reactionTooltipLabel);
		
		supportTooltipLabel = new JLabel("<html>(<u>?</u>)");
		supportTooltipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		abilityInfoPanel.add(supportTooltipLabel);
		
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
		
		JPanel growthsPanel = new JPanel();
		statsAndGrowthsPanel.add(growthsPanel);
		growthsPanel.setPreferredSize(new Dimension(104, 10));
		growthsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Stat Growths", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		growthsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel growthSelectorPanel = new JPanel();
		growthsPanel.add(growthSelectorPanel, BorderLayout.NORTH);
		// levelsPanel.add(rdbtnSimple);
		
		growthJobBox = new JComboBox<FFTAJob>();
		growthSelectorPanel.add(growthJobBox);
		
		JLabel growthsLabel = new JLabel("<html><span color=\"gray\">Base / Per level");
		growthsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		growthsPanel.add(growthsLabel, BorderLayout.CENTER);
		
		JPanel growthDisplayPanel = new JPanel();
		growthsPanel.add(growthDisplayPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_growthDisplayPanel = new GridBagLayout();
		gbl_growthDisplayPanel.columnWidths = new int[] {40, 56, 40, 56, 0};
		gbl_growthDisplayPanel.rowHeights = new int[] {14, 14, 14, 14, 0};
		gbl_growthDisplayPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_growthDisplayPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		growthDisplayPanel.setLayout(gbl_growthDisplayPanel);
		
		Insets growthInsets = new Insets(0, 0, 2, 5);
		
		JLabel lblGrowthWAtk = new JLabel("WAtk");
		lblGrowthWAtk.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthWAtk = new GridBagConstraints();
		gbc_lblGrowthWAtk.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthWAtk.insets = growthInsets;
		gbc_lblGrowthWAtk.gridx = 2;
		gbc_lblGrowthWAtk.gridy = 0;
		growthDisplayPanel.add(lblGrowthWAtk, gbc_lblGrowthWAtk);
		
		lblGrowthWAtkScore = new JLabel("<html><strong>100/10");
		lblGrowthWAtkScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthWAtkScore = new GridBagConstraints();
		gbc_lblGrowthWAtkScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthWAtkScore.insets = growthInsets;
		gbc_lblGrowthWAtkScore.gridx = 3;
		gbc_lblGrowthWAtkScore.gridy = 0;
		growthDisplayPanel.add(lblGrowthWAtkScore, gbc_lblGrowthWAtkScore);
		
		JLabel lblGrowthHP = new JLabel("HP");
		lblGrowthHP.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthHP = new GridBagConstraints();
		gbc_lblGrowthHP.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthHP.insets = growthInsets;
		gbc_lblGrowthHP.gridx = 0;
		gbc_lblGrowthHP.gridy = 0;
		growthDisplayPanel.add(lblGrowthHP, gbc_lblGrowthHP);
		
		lblGrowthHPScore = new JLabel("<html><strong>100/10");
		lblGrowthHPScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthHPScore = new GridBagConstraints();
		gbc_lblGrowthHPScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthHPScore.insets = growthInsets;
		gbc_lblGrowthHPScore.gridx = 1;
		gbc_lblGrowthHPScore.gridy = 0;
		growthDisplayPanel.add(lblGrowthHPScore, gbc_lblGrowthHPScore);
		
		lblGrowthWDef = new JLabel("WDef");
		lblGrowthWDef.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthWDef = new GridBagConstraints();
		gbc_lblGrowthWDef.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthWDef.insets = growthInsets;
		gbc_lblGrowthWDef.gridx = 2;
		gbc_lblGrowthWDef.gridy = 1;
		growthDisplayPanel.add(lblGrowthWDef, gbc_lblGrowthWDef);
		
		lblGrowthWDefScore = new JLabel("<html><strong>100/10");
		lblGrowthWDefScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthWDefScore = new GridBagConstraints();
		gbc_lblGrowthWDefScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthWDefScore.insets = growthInsets;
		gbc_lblGrowthWDefScore.gridx = 3;
		gbc_lblGrowthWDefScore.gridy = 1;
		growthDisplayPanel.add(lblGrowthWDefScore, gbc_lblGrowthWDefScore);
		
		JLabel lblGrowthMP = new JLabel("MP");
		lblGrowthMP.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthMP = new GridBagConstraints();
		gbc_lblGrowthMP.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthMP.insets = growthInsets;
		gbc_lblGrowthMP.gridx = 0;
		gbc_lblGrowthMP.gridy = 1;
		growthDisplayPanel.add(lblGrowthMP, gbc_lblGrowthMP);
		
		lblGrowthMPScore = new JLabel("<html><strong>100/10");
		lblGrowthMPScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthMPScore = new GridBagConstraints();
		gbc_lblGrowthMPScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthMPScore.insets = growthInsets;
		gbc_lblGrowthMPScore.gridx = 1;
		gbc_lblGrowthMPScore.gridy = 1;
		growthDisplayPanel.add(lblGrowthMPScore, gbc_lblGrowthMPScore);
		
		JLabel lblGrowthMPow = new JLabel("MPow");
		lblGrowthMPow.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthMPow = new GridBagConstraints();
		gbc_lblGrowthMPow.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthMPow.insets = growthInsets;
		gbc_lblGrowthMPow.gridx = 2;
		gbc_lblGrowthMPow.gridy = 2;
		growthDisplayPanel.add(lblGrowthMPow, gbc_lblGrowthMPow);
		
		lblGrowthMPowScore = new JLabel("<html><strong>100/10");
		lblGrowthMPowScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthMPowScore = new GridBagConstraints();
		gbc_lblGrowthMPowScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthMPowScore.insets = growthInsets;
		gbc_lblGrowthMPowScore.gridx = 3;
		gbc_lblGrowthMPowScore.gridy = 2;
		growthDisplayPanel.add(lblGrowthMPowScore, gbc_lblGrowthMPowScore);
		
		JLabel lblGrowthSpeed = new JLabel("Speed");
		lblGrowthSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthSpeed = new GridBagConstraints();
		gbc_lblGrowthSpeed.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthSpeed.insets = growthInsets;
		gbc_lblGrowthSpeed.gridx = 0;
		gbc_lblGrowthSpeed.gridy = 2;
		growthDisplayPanel.add(lblGrowthSpeed, gbc_lblGrowthSpeed);
		
		lblGrowthSpeedScore = new JLabel("<html><strong>100/10");
		lblGrowthSpeedScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthSpeedScore = new GridBagConstraints();
		gbc_lblGrowthSpeedScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthSpeedScore.insets = growthInsets;
		gbc_lblGrowthSpeedScore.gridx = 1;
		gbc_lblGrowthSpeedScore.gridy = 2;
		growthDisplayPanel.add(lblGrowthSpeedScore, gbc_lblGrowthSpeedScore);
		
		JLabel lblGrowthMRes = new JLabel("MRes");
		lblGrowthMRes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGrowthMRes = new GridBagConstraints();
		gbc_lblGrowthMRes.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthMRes.insets = new Insets(0, 0, 0, 5);
		gbc_lblGrowthMRes.gridx = 2;
		gbc_lblGrowthMRes.gridy = 3;
		growthDisplayPanel.add(lblGrowthMRes, gbc_lblGrowthMRes);
		
		lblGrowthMResScore = new JLabel("<html><strong>100/10");
		lblGrowthMResScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGrowthMResScore = new GridBagConstraints();
		gbc_lblGrowthMResScore.fill = GridBagConstraints.BOTH;
		gbc_lblGrowthMResScore.insets = new Insets(0, 0, 0, 5);
		gbc_lblGrowthMResScore.gridx = 3;
		gbc_lblGrowthMResScore.gridy = 3;
		growthDisplayPanel.add(lblGrowthMResScore, gbc_lblGrowthMResScore);
		
		growthJobBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				if (!minimalRaceUpdate)
				{
					JComboBox<FFTAJob> cb = (JComboBox<FFTAJob>) e.getSource();
					FFTAJob job = (FFTAJob) cb.getSelectedItem();
					unit.base.levels = new int[unit.base.race.jobs.length];
					unit.base.statBase = job;
					unit.base.levels[cb.getSelectedIndex()] = (Integer) levelSpinner.getValue() - 1;
					unit.base.updateDeepStats();
					updateDeepStatsDisplay();
					updateGrowthsDisplay();
				}
			}
		});
		
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
		resistancePanel.setPreferredSize(new Dimension(10, 80));
		GridBagLayout gbl_resistancePanel = new GridBagLayout();
		gbl_resistancePanel.columnWidths = new int[] {279, 279, 0};
		gbl_resistancePanel.rowHeights = new int[] {80, 0};
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
				
		JPanel lowerPanel = new JPanel();
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel saveExitPanel = new JPanel();
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
					for (int i = 0; i < roster.unitList.size(); i++)
					{
						FileOutputStream f_out = new FileOutputStream("units/" + Integer.toString(10 + i) + "_" + 
								((RosterUnit) roster.unitList.get(i)).base.name.toLowerCase() + ".unit");
		    			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
		    			obj_out.writeObject ( roster.unitList.get(i) );
		           		System.out.println("Saved unit " + roster.unitList.get(i).base.name);
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
				
		compactRosterPanel = new JPanel();
		lowerPanel.add(compactRosterPanel);
		
		initializeRosterPanel();
		
		JScrollPane rosterScrollPane = new JScrollPane();
		rosterScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		rosterScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		compactRosterPanel.add(rosterScrollPane, BorderLayout.CENTER);
		rosterTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rosterScrollPane.setViewportView(rosterTable);
		rosterScrollPane.getViewport().setBackground(Color.WHITE);
		
		JPanel rosterButtonPanel = new JPanel();
		compactRosterPanel.add(rosterButtonPanel, BorderLayout.EAST);
		rosterButtonPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JButton btnNewUnit = new JButton("New unit");
		btnNewUnit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// Create a new unit and add it to the roster
				RosterUnit newUnit = new RosterUnit();
				int index = rosterTable.getSelectedRow() + 1;
				roster.add(index, newUnit);
				
				DefaultTableModel model = (DefaultTableModel) rosterTable.getModel();
				model.insertRow(index, unitToRow(newUnit));
				
				rosterTable.setRowSelectionInterval(index, index);
			}
		});
		rosterButtonPanel.add(btnNewUnit);
		
		JButton btnMoveUnitUp = new JButton("Move up");
		btnMoveUnitUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int index = rosterTable.getSelectedRow();
				
				if (index > 0 && roster.unitList.size() > 1)
				{
					// Reorder roster list
					roster.unitList.set(index, roster.getUnit(index - 1));
					roster.unitList.set(index - 1, unit);
					
					// Move row on the table
					DefaultTableModel model = (DefaultTableModel) rosterTable.getModel();
					model.moveRow(index, index, index - 1);
					
					// Reselect unit
					rosterTable.setRowSelectionInterval(index - 1, index - 1);
				}
			}
		});
		rosterButtonPanel.add(btnMoveUnitUp);
		
		JButton btnMoveUnitDown = new JButton("Move down");
		btnMoveUnitDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int index = rosterTable.getSelectedRow();
				
				if (index < roster.unitList.size() - 1 && roster.unitList.size() > 1)
				{
					// Reorder roster list
					roster.unitList.set(index, roster.getUnit(index + 1));
					roster.unitList.set(index + 1, unit);
					
					// Move row on the table
					DefaultTableModel model = (DefaultTableModel) rosterTable.getModel();
					model.moveRow(index, index, index + 1);
					
					// Reselect unit
					rosterTable.setRowSelectionInterval(index + 1, index + 1);
				}
			}
		});
		rosterButtonPanel.add(btnMoveUnitDown);
		
		JButton btnDeleteUnit = new JButton("Delete unit");
		btnDeleteUnit.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
			{
				if (roster.unitList.size() > 1)
				{
					int index = rosterTable.getSelectedRow();
					roster.unitList.remove(index);
					DefaultTableModel model = (DefaultTableModel) rosterTable.getModel();
					model.removeRow(index);
					
					if (index < model.getRowCount())
						rosterTable.setRowSelectionInterval(index, index);
					else
						rosterTable.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
				}
			}
		});
		rosterButtonPanel.add(btnDeleteUnit);
		
		JPanel innerRosterPanel = new JPanel();
		compactRosterPanel.add(innerRosterPanel, BorderLayout.NORTH);
				
		
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
				unit.base.combo = (FFTACombo) cAbilityBox.getSelectedItem();
			}
		});
		sAbilityBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FFTASupport sab = (FFTASupport) sAbilityBox.getSelectedItem(); 
				unit.base.support = sab;
				unit.base.equips.removeIllegalEquipment();
				equipmentList.setModel(unit.base.equips.getListModel());
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
				
				// Update roster view
				rosterTable.setValueAt(" " + unit.base.support, rosterTable.getSelectedRow(), 6);
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
		primaryAbilityTable = makeAbilityTable(unit.base.job.command);
		if (unit.base.secondary != FFTACommand.NONE)
			secondaryAbilityTable = makeAbilityTable(unit.base.secondary);
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
				else if (cmd == unit.base.job.command)
				{
					abilityTable = primaryAbilityTable;
				}
				else if (cmd == unit.base.secondary)
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
		abilityListPanel.add(abilityDescPanel, BorderLayout.SOUTH);
		abilityDescPanel.setBackground(SystemColor.menu);
		abilityDescPanel.setPreferredSize(new Dimension(10, 186));
		abilityDescPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane abilityDescScrollPane = new JScrollPane();
		abilityDescScrollPane.setBorder(new EmptyBorder(2, 2, 0, 2));
		abilityDescScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		abilityDescPanel.add(abilityDescScrollPane);
		
		skillDescLabel = new JLabel(defaultSkillDesc);
		skillDescLabel.setVerticalAlignment(SwingConstants.TOP);
		abilityDescScrollPane.setViewportView(skillDescLabel);
		// abilityDescPanel.add(skillDescLabel);
		
		JButton btnSaveUnit = new JButton("Save Unit");
		btnSaveUnit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
        		{
					FileOutputStream f_out = new FileOutputStream("units/" + Integer.toString(rosterTable.getSelectedRow() + 10) + "_" + unit.base.name.toLowerCase() + ".unit");
        			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
               		obj_out.writeObject ( unit );
               		System.out.println("Saved unit");
               		obj_out.close();
        		}
        		catch (IOException error) { System.out.println(error); }
			}
		});
		
		
		
		
		// loadUnit(new FFTAUnit(), false);
		rosterTable.setRowSelectionInterval(0, 0);
		loadUnit((RosterUnit) roster.getUnit(rosterTable.getSelectedRow()), true);
		
		changeSecondaryAbility(unit.base.secondary);
		abilityListBox.setSelectedIndex(0);
	}
	
	private JTable makeAbilityTable(FFTACommand command)
	{
		KnownSkill[] skillList = null;
		if (command == unit.base.job.command)
			skillList = unit.base.primarySkills;
		else if (command == unit.base.secondary)
			skillList = unit.base.secondarySkills;
		
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
		
		FFTACommand[] aabs = unit.base.race.aAbilities;
		FFTACommand[] cmds;
		if (unit.base.job == FFTAJob.ALCHEMIST)
			cmds = new FFTACommand[aabs.length - 2];
		else
			cmds = new FFTACommand[aabs.length - 1];
		
		int exclude = 0;
		for (int i = 0; i < aabs.length; i++)
		{
			if (aabs[i] == unit.base.job.command)
				exclude++;
			else if (unit.base.job == FFTAJob.ALCHEMIST && aabs[i] == FFTACommand.ITEM)
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
		commands.add(unit.base.job.command);
		if (unit.base.secondary != FFTACommand.NONE)
			commands.add(unit.base.secondary);
		if (unit.base.bonus != FFTACommand.NONE)
			commands.add(unit.base.bonus);
		if (unit.base.job == FFTAJob.MORPHER || unit.base.secondary == FFTACommand.MORPH)
		{
			// Check morpher abilities
			KnownSkill[] morphs = null;
			if (unit.base.job == FFTAJob.MORPHER)
				morphs = unit.base.primarySkills;
			else
				morphs = unit.base.secondarySkills;
			
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
			int res = unit.base.getElementalResistance(Element.values()[i + 1], false);
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
			if (FFTACalc.checkForItemEffects(unit.base, nEff.NEGATORS) || FFTACalc.supportNegates(unit.base, nEff.STATUS_EFFECT))
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
			{ "HP",		unit.base.job.baseHP,	unit.base.job.growthHP },
			{ "MP",		unit.base.job.baseMP,	unit.base.job.growthMP },
			{ "WAtk",	unit.base.job.baseWAtk,	unit.base.job.growthWAtk },
			{ "WDef",	unit.base.job.baseWDef,	unit.base.job.growthWDef },
			{ "MPow",	unit.base.job.baseMPow,	unit.base.job.growthMPow },
			{ "MRes",	unit.base.job.baseMRes,	unit.base.job.growthMRes },
			{ "Speed",	unit.base.job.baseSpeed,	unit.base.job.growthSpeed }
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
			result += "<br>Damage Type: ";
			if (sk.IS_PHYSICAL)
				result += "Physical";
			else
				result += "Magical";
		}
		
		// MP Cost
		if (sk.MP_COST > 0)
			result += "<br>Cost: " + sk.MP_COST + " MP";

		result += "<br>";
		
		// Silence
		if (!sk.IGNORE_SILENCE)
			result += "<br>Blocked by Silence";
		
		// Doublecast
		if (sk.DOUBLECASTABLE && unit.base.race == FFTARace.VIERA)
			result += "<br>Compatible with Doublecast";
		
		// Triggers
		String triggers = "";
		
		if (sk.REFLECTABLE)
			triggers += "Reflect, ";
		if (sk.COVERABLE)
			triggers += "Cover, ";
		if (sk.TRIGGER_RET_MAG)
			triggers += "Return Magic, ";
		if (sk.TRIGGER_RET_MAG)
			triggers += "Absorb MP, ";
		
		if (triggers.length() > 0)
			result += "<br>Triggers " + triggers.substring(0, triggers.length() - 2);
		
		
		return result;
	}
	
	private void loadUnit(RosterUnit newUnit, boolean mru)
	{
		firstRaceUpdate = true;
		minimalRaceUpdate = mru;
		
		FFTAJob newJob = newUnit.base.job;
		EquipSet newEquips = newUnit.base.equips;
		FFTACommand newA = newUnit.base.secondary;
		FFTASupport newS = newUnit.base.support;
		FFTAReaction newR = newUnit.base.reaction;
		FFTACombo newC = newUnit.base.combo;
		FFTAJob newGrowth = newUnit.base.statBase;
		
		System.out.println("RH: " + newEquips.rightHand);
		
		unit = newUnit;
		
		
		
		nameField.setText(newUnit.base.name);
		updateValueOnly = true;
		levelSpinner.setValue(newUnit.base.getLevel());
		updateValueOnly = false;
		raceBox.setSelectedItem(newUnit.base.race);
		notesField.setText(newUnit.note);
		
		System.out.println("RH: " + newEquips.rightHand);
		
		jobBox.setModel(new DefaultComboBoxModel<FFTAJob>(newUnit.base.race.jobs));
		updateSecondaryAbilityBox();
		sAbilityBox.setModel(new DefaultComboBoxModel<FFTASupport>(newUnit.base.race.sAbilities));
		rAbilityBox.setModel(new DefaultComboBoxModel<FFTAReaction>(newUnit.base.race.rAbilities));
		cAbilityBox.setModel(new DefaultComboBoxModel<FFTACombo>(newUnit.base.race.cAbilities));
		
		jobBox.setSelectedItem(newJob);
		secondaryAbilityBox.setSelectedItem(newA);
		sAbilityBox.setSelectedItem(newS);
		rAbilityBox.setSelectedItem(newR);
		cAbilityBox.setSelectedItem(newC);
		
		growthJobBox.setModel(new DefaultComboBoxModel<FFTAJob>(newUnit.base.race.jobs));
		growthJobBox.setSelectedItem(newGrowth);
		
		equipmentList.setModel(newEquips.getListModel());
		
		spritePanel.setJob(newUnit.base.job);
		updateJobIcons();
		
		minimalRaceUpdate = false;
		updateGrowthsDisplay();
		updateDeepStatsDisplay();
		updateShallowStatsDisplay();
	}
	
	private void updateJobIcons()
	{
		primaryIconLabel.setIcon(loadPrimaryJobIcon(unit.base));
		
		Icon secondary = loadSecondaryJobIcon(unit.base);
		if (secondary == null)
		{
			secondaryIconLabel.setVisible(false);
		}
		else
		{
			secondaryIconLabel.setIcon(secondary);
			secondaryIconLabel.setVisible(true);
		}
	}
	
	private void initializeRosterPanel()
	{
		compactRosterPanel.setBorder(new TitledBorder(null, "Roster", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		compactRosterPanel.setPreferredSize(new Dimension(10, 162));
		compactRosterPanel.setLayout(new BorderLayout(0, 0));
		
		rosterTable = new JTable();
		
		roster = new ClanBuilderRoster();
		
		Object[][] data = new Object[roster.unitList.size()][8];
		for (int i = 0; i < roster.unitList.size(); i++)
		{
			Object[] row = unitToRow(roster.unitList.get(i));
			data[i] = row;
		}
		String[] columnNames = new String[] { "Name", "Lv.", "Race", "Job", "Sec.", "Reaction", "Support", "Notes" };
		
		DefaultTableModel model = new DefaultTableModel (data, columnNames)
		{
			public Class getColumnClass(int column)
			{
				Object result = getValueAt(0, column);
			
				if (column == 4)
					return Icon.class;
				
				if (result == null)
					return String.class;
				
				return result.getClass();
			}
			
			
		    @Override
		    public boolean isCellEditable(int row, int col)
		    {
		    	return false;
		    }
		};
		
        
		rosterTable = new JTable(model);
		
		// Selection listener
		ListSelectionModel selectionModel = rosterTable.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (e.getValueIsAdjusting())
					return;
				
				// e.getSource() returns an object like this
				// javax.swing.DefaultListSelectionModel 1052752867 ={11}
				// where 11 is the index of selected element when mouse button is released
				
				int index = rosterTable.getSelectedRow();
				if (index >= 0)
				{
					RosterUnit loadUnit = roster.getUnit(index);
					System.out.println("Loading " + loadUnit.base.name + " (" + index + ")");
					loadUnit(loadUnit, true);
					abilityListBox.setSelectedIndex(0);
				}
			}
		});
		
		// Format table
        rosterTable.setIntercellSpacing(new Dimension(1, 1));
        rosterTable.setRowHeight(17);
		rosterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		rosterTable.getTableHeader().setReorderingAllowed(false);
		rosterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn column = rosterTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(130);
        column = rosterTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(25);
        column = rosterTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(60);
        column = rosterTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(33);
        column = rosterTable.getColumnModel().getColumn(4);
        column.setPreferredWidth(33);
        column = rosterTable.getColumnModel().getColumn(5);
        column.setPreferredWidth(90);
        column = rosterTable.getColumnModel().getColumn(6);
        column.setPreferredWidth(90);
        column = rosterTable.getColumnModel().getColumn(7);
        column.setPreferredWidth(205);
        
        for (int i = 0; i < 8; i++)
        {
        	column = rosterTable.getColumnModel().getColumn(i);
        	column.setMaxWidth(column.getPreferredWidth());
        	column.setMinWidth(column.getPreferredWidth());
        }
        

        
	}
	
	private Object[] unitToRow(RosterUnit rosterUnit)
	{
		FFTAUnit base = rosterUnit.base;
		Object[] row = new Object[] { " " + base.name, base.getLevel(), " " + base.race.toString(), loadPrimaryJobIcon(base), loadSecondaryJobIcon(base), 
				" " + base.reaction, " " + base.support, " " + rosterUnit.note };
		return row;
	}
	
	private void updateGrowthsDisplay()
	{
		FFTAJob job = (FFTAJob) growthJobBox.getSelectedItem();
		lblGrowthHPScore.setText("" + job.baseHP + " / " + job.growthHP);
		lblGrowthMPScore.setText("" + job.baseMP + " / " + job.growthMP);
		lblGrowthWAtkScore.setText("" + job.baseWAtk + " / " + job.growthWAtk);
		lblGrowthWDefScore.setText("" + job.baseWDef + " / " + job.growthWDef);
		lblGrowthMPowScore.setText("" + job.baseMPow + " / " + job.growthMPow);
		lblGrowthMResScore.setText("" + job.baseMRes + " / " + job.growthMRes);
		lblGrowthSpeedScore.setText("" + job.baseSpeed + " / " + job.growthSpeed);
	}
	
	public void updateDeepStatsDisplay()
	{
		lblHPScore.setText(Integer.toString((int) unit.base.maxHP));
		lblMPScore.setText(Integer.toString((int) unit.base.maxMP));
		lblWAtkScore.setText(Integer.toString(unit.base.getTotalWAtk()));
		lblWDefScore.setText(Integer.toString(unit.base.getTotalWDef()));
		lblMPowScore.setText(Integer.toString(unit.base.getTotalMPow()));
		lblMResScore.setText(Integer.toString(unit.base.getTotalMRes()));
		lblSpeedScore.setText(Integer.toString(unit.base.getTotalSpeed()));
	}
	
	public void updateShallowStatsDisplay()
	{
		lblMoveScore.setText(Integer.toString(unit.base.getTotalMove()));
		lblJumpScore.setText(Integer.toString(unit.base.getTotalJump()));
		lblEvadeScore.setText(Integer.toString(unit.base.getTotalEvade()));
		lblFPowScore.setText(Integer.toString(unit.base.getWAtkEquipBonus()));
	}
	
	
		
	
	
	public void changeRace(FFTARace race)
	{
		if (race != unit.base.race || firstRaceUpdate)
		{
			
			firstRaceUpdate = false;
			unit.base.changeRace(race, false);
			jobBox.setModel(new DefaultComboBoxModel<FFTAJob>(race.jobs));
			
			sAbilityBox.setModel(new DefaultComboBoxModel<FFTASupport>(race.sAbilities));
			rAbilityBox.setModel(new DefaultComboBoxModel<FFTAReaction>(race.rAbilities));
			cAbilityBox.setModel(new DefaultComboBoxModel<FFTACombo>(race.cAbilities));
			
			growthJobBox.setModel(new DefaultComboBoxModel<FFTAJob>(race.jobs));
			growthJobBox.setSelectedIndex(0);
			
			changeJob(race.jobs[0]);
			updateSecondaryAbilityBox();
			
			// Update roster panel
			rosterTable.setValueAt(" " + unit.base.race, rosterTable.getSelectedRow(), 2);
			rosterTable.setValueAt(loadSecondaryJobIcon(unit.base), rosterTable.getSelectedRow(), 4);
			rosterTable.setValueAt(" " + unit.base.reaction, rosterTable.getSelectedRow(), 5);
			rosterTable.setValueAt(" " + unit.base.support, rosterTable.getSelectedRow(), 6);
		}
	}
	
	public void changeJob(FFTAJob job)
	{
		unit.base.changeJob(job);
		primaryAbilityBox.setText(job.command.NAME);
		equipmentList.setModel(unit.base.equips.getListModel());
		spritePanel.setJob(job);
		updateJobIcons();
		
		if (job.command == unit.base.secondary)
			secondaryAbilityBox.setSelectedIndex(0);
		
		updateSecondaryAbilityBox();
		
		if (job == FFTAJob.ALCHEMIST)
			bonusAbilityBox.setText("Item");
		else
			bonusAbilityBox.setText(" - ");
		
		updateDeepStatsDisplay();
		updateShallowStatsDisplay();
		
		// Update skills list
		updateAbilityListBox();
		primaryAbilityTable = makeAbilityTable(job.command);
		abilityTable = primaryAbilityTable;
		abilityTableScrollPane.setViewportView(abilityTable);

		// Update icon in roster
		rosterTable.setValueAt(loadPrimaryJobIcon(unit.base), rosterTable.getSelectedRow(), 3);
		
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
		if (secondary == unit.base.job.command)
		{
			secondaryAbilityBox.setSelectedIndex(0);
			secondary = FFTACommand.NONE;
		}
		
		// Set secondary ability in unit's object
		unit.base.setSecondary(secondary);
		
		// Make secondary ability table
		JTable oldSecondaryTable = secondaryAbilityTable;
		if (secondary == FFTACommand.ITEM)
			secondaryAbilityTable = itemTable;
		else
			secondaryAbilityTable = makeAbilityTable(secondary);
		
		updateJobIcons();
		
		// Set secondary ability in ability list combobox
		updateAbilityListBox();
		
		// Update icon in roster view
		rosterTable.setValueAt(loadSecondaryJobIcon(unit.base), rosterTable.getSelectedRow(), 4);
		
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
	
	private Icon loadPrimaryJobIcon(FFTAUnit u)
	{
		return new ImageIcon("resources/icons/icon_" + u.job.name() + ".png");
	}
	
	private Icon loadSecondaryJobIcon(FFTAUnit u)
	{
		String secondJob = "";
		
		if (u.secondary == FFTACommand.NONE)
			return null;
		else if (u.secondary == FFTACommand.ITEM)
			secondJob = "item";
		else
			secondJob = FFTAJob.values()[u.secondary.ordinal() - 2].name();
		
		return new ImageIcon("resources/icons/icon_" + secondJob + ".png");
	}
	
	class JTextFieldLimit extends PlainDocument
	{
		private static final long serialVersionUID = 1L;
		private int limit;
		private boolean forbidIllegalCharacters;
	
		JTextFieldLimit(int limit) 
		{
			super();
			this.limit = limit;
			forbidIllegalCharacters = true;
		}
		
		JTextFieldLimit(int limit, boolean forbidIllegalCharacters) 
		{
			super();
			this.limit = limit;
			this.forbidIllegalCharacters = forbidIllegalCharacters;
		}
		
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException 
		{
			if (forbidIllegalCharacters)
			{
				if (str == null || str.contains("\\") || str.contains("/") || str.contains(":") || str.contains("*") || 
						str.contains("?") || str.contains("\"") || str.contains("<") || str.contains(">") || 
						str.contains("|"))
					return;
			}
			
			if ((getLength() + str.length()) <= limit) 
			{
				super.insertString(offset, str, attr);
			}
		}
	}
}
