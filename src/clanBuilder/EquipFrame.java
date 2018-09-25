package clanBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fftadata.EquipSet;
import fftadata.EquipType;
import fftadata.FFTAEquip;
import fftadata.FFTAUnit;

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
	
	NewClanBuilder parent;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipFrame frame = new EquipFrame(new NewClanBuilder());
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
	public EquipFrame(NewClanBuilder parent)
	{
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(this.getParent());
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.parent = parent;
		this.unit = parent.unit;
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
		equippedList.setCellRenderer(parent.ecr);
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
				parent.equipmentList.setModel(unit.equips.getListModel());
				parent.updateDeepStatsDisplay();
				parent.updateShallowStatsDisplay();
				parent.updateResistances();
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
		
//		JComboBox equipBox = new JComboBox(new EquipType[] { EquipType.SWORD, EquipType.BLADE, EquipType.SABER, EquipType.KNIGHTSWORD, EquipType.GREATSWORD, EquipType.BROADSWORD, EquipType.KNIFE, EquipType.RAPIER, EquipType.KATANA, EquipType.STAFF, EquipType.ROD, EquipType.KNUCKLES, EquipType.SPEAR, EquipType.BOW, EquipType.GREATBOW, EquipType.INSTRUMENT, EquipType.GUN, EquipType.SOUL, EquipType.HAT, EquipType.HELM, EquipType.ARMOR, EquipType.CLOTHING, EquipType.VIERA_CLOTHES, EquipType.ROBE, EquipType.SHIELD, EquipType.BOOTS, EquipType.ACCESSORY, EquipType.GLOVES});
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
		arsenalList.setCellRenderer(parent.ecr);
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
			
//			System.out.println("RIGHT: " + newSet.rightHand);
//			System.out.println("LEFT:  " + newSet.leftHand);
//			System.out.println("HEAD:  " + newSet.head);
//			System.out.println("BODY:  " + newSet.body);
//			System.out.println("ARMS:  " + newSet.arms);
//			System.out.println("FEET:  " + newSet.feet);
//			System.out.println();
			System.out.println(newSet);
			
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