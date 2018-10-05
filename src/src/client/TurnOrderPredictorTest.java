package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fftadata.ActiveUnit;
import fftadata.FFTAUnit;

public class TurnOrderPredictorTest extends JFrame {

	private JPanel contentPane;
	ArrayList<ActiveUnit> units;
	ArrayList<TurnOrderBoy> turnOrder;
	int currentUnit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
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
				
				ArrayList<ActiveUnit> units = new ArrayList<ActiveUnit>();
				
				for (int i = 0; i < loadedClasses.size(); i++)
				{
					units.add(new ActiveUnit(loadedClasses.get(i), 0, 0, 0, (i % 2) + 1));
					units.get(i).counter = (int) (1000 * Math.random());
					units.get(i).priority = 0;
				}
				
				
				
				try {
					units.get(2).counter = 1000;
					TurnOrderPredictorTest frame = new TurnOrderPredictorTest(units);
					frame.currentUnit = 2;
					frame.predict();
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
	public TurnOrderPredictorTest(ArrayList<ActiveUnit> units)
	{
		this.units = units;
		turnOrder = new ArrayList<TurnOrderBoy>();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 80, 400);
		
//		predict();
	}

	public void predict()
	{
		boolean keepGoing = true;
		int round = 0;
		
		int speed = units.get(currentUnit).unit.getTotalSpeed();
		TurnOrderBoy wait = new TurnOrderBoy("Wait", 500.0 / speed);
		TurnOrderBoy actOnly = new TurnOrderBoy("Act Only", 700.0 / speed);
		TurnOrderBoy moveOnly = new TurnOrderBoy("Move Only", 800.0 / speed);
		TurnOrderBoy moveAct = new TurnOrderBoy("Move & Act", 1000.0 / speed);
		turnOrder.add(wait);
		turnOrder.add(actOnly);
		turnOrder.add(moveOnly);
		turnOrder.add(moveAct);
		
		while (keepGoing)
		{
			keepGoing = false;
			for (int i = 0; i < units.size(); i++)
			{
				if (!(i == currentUnit && round == 1))
				{
					double ticksTilTurn = (1000.0 * (round + 1) - (units.get(i).counter + 
							units.get(i).reserve)) / units.get(i).unit.getTotalSpeed();
					if (turnOrder.size() < 20 || ticksTilTurn < turnOrder.get(19).position)
					{
						TurnOrderBoy to = new TurnOrderBoy(units.get(i), ticksTilTurn);
						turnOrder.add(to);
						Collections.sort(turnOrder);
						keepGoing = true;
					}
				}
			}
			round++;
		}
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(20, 1, 0, 0));
		for (int i = 0; i < 20; i++)
			contentPane.add(turnOrder.get(i));
		setContentPane(contentPane);
	}
	
	class TurnOrderBoy extends JPanel implements Comparable<TurnOrderBoy>
	{
		ActiveUnit unit;
		double position;
		
		public TurnOrderBoy(ActiveUnit unit, double position)
		{
			this.unit = unit;
			this.position = position;
			
//			FlowLayout flowLayout_1 = (FlowLayout) getLayout();
//			flowLayout_1.setAlignment(FlowLayout.LEFT);
//			flowLayout_1.setVgap(1);
//			flowLayout_1.setHgap(1);
			BorderLayout bl = new BorderLayout();
			setLayout(bl);
			setPreferredSize(new Dimension(1000, 20));
			setBorder(new EmptyBorder(1, 1, 1, 1));
			
			TOJobIconPanel panel = new TOJobIconPanel(unit);
			panel.setPreferredSize(new Dimension(32, 16));
			panel.setOpaque(false);
			add(panel, BorderLayout.WEST);
			
			if (unit.team == 1)
				setBackground(new Color(192, 192, 248));
			else
				setBackground(new Color(248, 192, 192));
			
			JLabel lblUnitName;
			lblUnitName = new JLabel(" " + unit.unit.name);
			lblUnitName.setFont(new Font("Tahoma", Font.BOLD, 11));
			
			add(lblUnitName, BorderLayout.CENTER);
			repaint();
		}

		public TurnOrderBoy(String special, double position)
		{
			this.unit = null;
			this.position = position;
			
			FlowLayout flowLayout_1 = (FlowLayout) getLayout();
			flowLayout_1.setAlignment(FlowLayout.LEFT);
			flowLayout_1.setVgap(1);
			flowLayout_1.setHgap(1);
			setPreferredSize(new Dimension(1000, 20));
			setBackground(new Color(192, 248, 192));
			
			JLabel lblChevrons = new JLabel(">>>>");
			JLabel lblAction = new JLabel(" " + special);
			lblAction.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			add(lblChevrons);
			add(lblAction);
			repaint();
		}
		
		@Override
		public int compareTo(TurnOrderBoy to)
		{
			if (to.position > position)
				return -1;
			else if (to.position < position)
				return 1;
			else if (unit == null || to.unit == null)
				return 1;
			else if (to.unit.priority > unit.priority)
				return -1;
			else
				return 1;
		}	
	}
	
	class TOJobIconPanel extends JPanel
	{
		BufferedImage icon;
		String jobName;
		
		public TOJobIconPanel(ActiveUnit au)
		{
			super();
			if (au != null)
				jobName = au.unit.job.name();
			update();
		}
		
		public void update()
		{
			try {
				if (jobName != null)
					icon = ImageIO.read(new File("resources/icons/icon_" + jobName + ".png"));
				else
					icon = null;
			} catch (IOException e) {}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			if (icon != null)
				g.drawImage(icon, 0, 0, null);
		}
	}
}

