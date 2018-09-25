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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.PlainDocument;

import clanBuilder.ClanBuilderRosterPanel;
import clanBuilder.EquipCellRenderer;
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
public class ClanBuilder extends JFrame
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
	public ClanBuilder()
	{
		
		
		
		
				
		
		

		
		
		
		
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
		lblFPowScore.setText(Integer.toString(unit.getWAtkEquipBonus()));
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
	
	
	
	
	
	class JTextFieldLimit extends PlainDocument {
		  private int limit;
		  JTextFieldLimit(int limit) {
		    super();
		    this.limit = limit;
		  }

		  JTextFieldLimit(int limit, boolean upper) {
		    super();
		    this.limit = limit;
		  }

		  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		    if (str == null || str.contains("\\") || str.contains("/") || str.contains(":") || str.contains("*") || 
		    		str.contains("?") || str.contains("\"") || str.contains("<") || str.contains(">") || 
		    		str.contains("|"))
		      return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}
}
