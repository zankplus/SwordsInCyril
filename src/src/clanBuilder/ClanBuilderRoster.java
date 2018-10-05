package clanBuilder;
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



public class ClanBuilderRoster
{
	ArrayList<RosterUnit> unitList;
	BufferedImage rosterBG, rosterBGhl;
	
	public ClanBuilderRoster()
	{
		unitList = new ArrayList<RosterUnit>();
		
		try {
			rosterBG = ImageIO.read(new File("resources/misc/rosterbg.png"));
			rosterBGhl = ImageIO.read(new File("resources/misc/rosterbg_hl.png"));
		} catch (IOException e) {
			rosterBG = null;
		}
		
		loadUnits();
	}
	
	public RosterUnit getUnit(int index)
	{
		return unitList.get(index);
	}
	
	public void loadUnits()
	{
		ArrayList<RosterUnit> loadedClasses = new ArrayList<RosterUnit>();
		
		try
		{
			File folder = new File("units");
			File[] listOfFiles = folder.listFiles();
			
			System.out.println("Found " + listOfFiles.length + " files");
			
			for (int i = 0; i < listOfFiles.length; i++)
			{				
				FileInputStream f_in = new FileInputStream(listOfFiles[i]);
	    		ObjectInputStream obj_in = new ObjectInputStream (f_in);
	    		RosterUnit unit = (RosterUnit) obj_in.readObject();
	    		
	    		System.out.println("Loaded unit: " + unit.base.name);
	    		loadedClasses.add(unit);
	    		obj_in.close();
			}	
		}
		catch (IOException e) { System.err.println(e); }
		catch (ClassNotFoundException e) {	e.printStackTrace(); }
		
		if (loadedClasses.size() == 0)
			loadedClasses.add(new RosterUnit(new FFTAUnit()));
	
		unitList = loadedClasses;
	}
	
	public void add(RosterUnit unit)
	{
		unitList.add(unit);
	}
	
	public void add(int index, RosterUnit unit)
	{
		unitList.add(index, unit);
	}
}

