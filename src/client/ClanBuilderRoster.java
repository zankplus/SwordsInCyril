package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import fftadata.*;



public class ClanBuilderRoster extends JList
{
	DefaultListModel model;
	ArrayList<FFTAUnit> unitList;
	int currentPage;
	BufferedImage rosterBG, rosterBGhl;
	
	public ClanBuilderRoster()
	{
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setVisibleRowCount(-1);
		setLayoutOrientation(JList.HORIZONTAL_WRAP);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		setCellRenderer(new RosterCellRenderer());
		unitList = new ArrayList<FFTAUnit>();
		currentPage = 0;
		
		try {
			rosterBG = ImageIO.read(new File("resources/misc/rosterbg.png"));
			rosterBGhl = ImageIO.read(new File("resources/misc/rosterbg_hl.png"));
		} catch (IOException e) {
			rosterBG = null;
		}
		
		loadUnits();
	}
	
	public void loadUnits()
	{
		ArrayList<FFTAUnit> loadedClasses = new ArrayList<FFTAUnit>();
		
		try
		{
			File folder = new File("units");
			File[] listOfFiles = folder.listFiles();
			
			for (int i = 0; i < listOfFiles.length; i++)
			{				
				FileInputStream f_in = new FileInputStream(listOfFiles[i]);
	    		ObjectInputStream obj_in = new ObjectInputStream (f_in);
	    		FFTAUnit unit = (FFTAUnit) obj_in.readObject();
	    		loadedClasses.add(unit);
	    		obj_in.close();
			}	
		}
		catch (IOException e) { System.err.println(e); }
		catch (ClassNotFoundException e) {	e.printStackTrace(); }
		
		if (loadedClasses.size() == 0)
			loadedClasses.add(new FFTAUnit());
	
		unitList = loadedClasses;
		updateModel();
	}
	
	public void add(FFTAUnit unit)
	{
		unitList.add(unit);
		updateModel();
	}
	
	public void add(int index, FFTAUnit unit)
	{
		unitList.add(index, unit);
		updateModel();
	}
	
	public void updateModel()
	{
		DefaultListModel newModel = new DefaultListModel();
		int start = 6 * currentPage;
		for (int i = 0; i < 6; i++)
		{
			if (start + i < unitList.size())
				newModel.addElement(unitList.get(start + i));
			else
				newModel.addElement(null);
		}
		setModel(newModel);
		repaint();
	}
	
	public int maxPage()
	{
		return (unitList.size() - 1) / 6;
	}
	
	
	class RosterCellRenderer implements ListCellRenderer<FFTAUnit>
	{
		@Override
		public Component getListCellRendererComponent(JList<? extends FFTAUnit> list, FFTAUnit unit, int index, boolean isSelected, boolean hasCellFocus)
		{
			if (unit != null)
			{
				RosterUnitPanel result = new RosterUnitPanel (unit);
				
				if (isSelected) {
		            result.selected = true;
		        } else {
		            result.selected = false;
		        }
				
				return result;
			}
			else
				return new RosterPanel();
		}
	}
	
	class RosterPanel extends JPanel
	{
		JLabel label;
		
		public RosterPanel()
		{
			super();
			setPreferredSize(new Dimension(64, 96));
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			g.drawImage(rosterBG, 0, 0, null);
		}
	}
	
	class RosterUnitPanel extends RosterPanel
	{
		FFTAUnit unit;
		BufferedImage sprite;
		boolean selected;
		
		public RosterUnitPanel(FFTAUnit unit)
		{
			super();
			this.unit = unit;
			
			try {
				sprite = ImageIO.read(new File("resources/roster/" + unit.job.name() + "_ally_stand_sw.png"));
			} catch (IOException e) {
				sprite = null;
			}
			
			setLayout(new BorderLayout());
			label = new JLabel(unit.name);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(Color.WHITE);
			label.setBackground(Color.BLACK);
			label.setOpaque(true);
			add(label,BorderLayout.SOUTH);
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if (selected)
				g.drawImage(rosterBGhl, 0, 0, null);
			else
				g.drawImage(rosterBG, 0, 0, null);
				
			g.drawImage(sprite, 16, 8, null);
			
		}
	}
	
	class RosterNewUnitPanel extends RosterPanel
	{
		BufferedImage sprite;
		
		public RosterNewUnitPanel()
		{
			super();
			try {
				sprite = ImageIO.read(new File("resources/misc/newunit.png"));
			} catch (IOException e) {
				sprite = null;
			}
			
			setLayout(new BorderLayout());
			label = new JLabel("[New unit]");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(Color.YELLOW);
			label.setBackground(Color.BLACK);
			label.setOpaque(true);
			add(label,BorderLayout.SOUTH);
		}
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(rosterBG, 0, 0, null);		
			g.drawImage(sprite, 16, 8, null);
			
		}
	}
}

