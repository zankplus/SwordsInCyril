package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import fftadata.ActiveUnit;
import fftadata.FFTAUnit;

public class MapPanelTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapPanelTest frame = new MapPanelTest();
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
	public MapPanelTest()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480 + 16 + 80, 600);
		ImageIcon img = new ImageIcon("resources/misc/appicon.png");
		setIconImage(img.getImage());
		
		setTitle("Muscadet");
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		GamePanel gp = new GamePanel(new EngagementWindow(null, 2, null, null, null, null));
		
		contentPane.add(gp);
		
		ActiveUnit au1 = new ActiveUnit( (FFTAUnit) gp.rosterPanel.roster.getModel().getElementAt(0), 1, 11, 7, 1);
		ArrayList<ActiveUnit> units1 = new ArrayList<ActiveUnit>();
		units1.add(au1);
		gp.p1Units = units1;
		
		ActiveUnit au2 = new ActiveUnit( (FFTAUnit) gp.rosterPanel.roster.getModel().getElementAt(1), 11, 1, 6, 2);
		ArrayList<ActiveUnit> units2 = new ArrayList<ActiveUnit>();
		units2.add(au2);
		gp.p2Units = units2;
		
		gp.mapPanel.units = units2;
		
		gp.mapPanel.addUnit(au1);
		gp.mapPanel.addUnit(au2);
		
		gp.beginGame();
	}

}

// Panel containing everything needed to interact with the game, including the MapPanel and
// whatever's happening below it. Also contains the game state
class GamePanel extends JPanel
{
	ZankGameMap map;
	MapPanel mapPanel;
	JPanel bottomPanel, turnOrderPanel, leftPanel, blankUnitPreview, unitPreview;
	UnitActionPanel unitAction;
	EngagementWindowRosterPanel rosterPanel;
	EngagementWindow ew;	// Parent frame
	
	ArrayList<ActiveUnit> p1Units, p2Units;
	ActiveUnit[] units;
	
	int player;
	
	public GamePanel(EngagementWindow base)
	{
		setLayout(new BorderLayout());
		ew = base;
		player = ew.playerNumber;
		
		
		leftPanel = new JPanel(new BorderLayout());
		mapPanel = new MapPanel(this);
		rosterPanel = new EngagementWindowRosterPanel(ew);
		map = mapPanel.map;
	
		turnOrderPanel = new JPanel();
		turnOrderPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		turnOrderPanel.setPreferredSize(new Dimension(80, 10));
		turnOrderPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		leftPanel.add(mapPanel, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.CENTER);
		add(turnOrderPanel, BorderLayout.EAST);
		mapPanel.roster = rosterPanel.roster;
		
		ZankGameMap map = mapPanel.map;
		
		
		beginPlacementMode();
	}

	public void beginPlacementMode()
	{
		add(rosterPanel, BorderLayout.SOUTH);
		mapPanel.beginPlacementMode();
	}
	
	public void beginGame()
	{
		remove(rosterPanel);
		bottomPanel = new JPanel(new BorderLayout());
		add(bottomPanel, BorderLayout.SOUTH);
		
		blankUnitPreview = new JPanel();
		blankUnitPreview.setPreferredSize(new Dimension(320, 162));
		blankUnitPreview.setBorder(new TitledBorder(null, "Unit Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		unitPreview = blankUnitPreview;
		
		bottomPanel.add(unitPreview, BorderLayout.WEST);
	
		unitAction = new UnitActionPanel(ew);
		bottomPanel.add(unitAction, BorderLayout.EAST);
		
		ArrayList<ActiveUnit> otherTeam;
		if (player == 1)
			otherTeam = p2Units;
		else
			otherTeam = p1Units;
		
		for (ActiveUnit au : otherTeam)
		{
			mapPanel.addUnit(au);
			mapPanel.units.add(au);
		}
		
		
		mapPanel.beginGame();
	}
}


// Panel representing the game map. All graphical logic should go here
class MapPanel extends JPanel
{
	ZankGameMap map;
	int clickX, clickY;
	boolean drawTile = false;
	ClanBuilderRoster roster;
	int player;
	ArrayList<ActiveUnit> units;
	TreeSet<ForegroundObject> fgObjects;
	ZankMapTile selectedTile;
	ActiveUnit selectedUnit;
	GamePanel gamePanel;
	static ForegroundObject selector = new ForegroundObject("resources/maps/selector.png", 0, 0, 0, 0, -32, false, FGObjectType.SELECTOR);
	ArrayList<ForegroundObject> hlTiles;
	int mode;
	
	public MapPanel(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
		player = gamePanel.player;
		map = MuscadetMapLoader.getMap();
		// int targetX = 240 + 16*x - 16*y, targetY = 160 + 8*x + 8*y;
		selectedTile = null;
		units = new ArrayList<ActiveUnit>();
		hlTiles = new ArrayList<ForegroundObject>();
		fgObjects = map.mapObjects;

		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent arg0) {	}
			@Override
			public void mouseExited(MouseEvent arg0) { }
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				// Select the clicked tile
				selectedTile = null;
				for (ZankMapTile tile : map.tileList)
				{
					int dist = (Math.abs(e.getX() - tile.renderCenterX())) + Math.abs((e.getY() - tile.renderCenterY())) * 2;
					if (dist <= 16)
						selectedTile = tile;
				}
				
				
				// Generic selection mode
				if (mode == 0)
				{
					selectTile(selectedTile);
				}
				
				// Unit placement mode
				else if (mode == 1)
				{
					if (selectedTile != null)
					{
						boolean selectedIsStartingTile = false;
						
						if (player == 1)
						{
							for (int i = 0; i < map.p1StartingTiles.length; i++)
								if (map.p1StartingTiles[i] == selectedTile)
									selectedIsStartingTile = true;
						}
						else if (player == 2)
							for (int i = 0; i < map.p2StartingTiles.length; i++)		
								if (map.p2StartingTiles[i] == selectedTile)
									selectedIsStartingTile = true;
						
						// If a valid starting point has been selected
						if (selectedIsStartingTile && roster.getSelectedValue() != null)
						{
							FFTAUnit unit = (FFTAUnit) roster.getSelectedValue();
							
							// If this unit has already been placed, remove that unit from the map
							for (int i = 0; i < units.size(); i++)
							{
								ActiveUnit old = units.get(i);
								if (old.unit == unit)
								{
									map.mapObjects.remove(map.mapData[old.x][old.y].fgobj);	// Clear the old unit's fgobject from the map
									map.mapData[old.x][old.y].fgobj = null;					// and from its associated map tile
									units.remove(i);	// Clear the old unit from the unit list
									i = 6;				// Stop looping
								}
							}
							
							// If another unit is already present in this tile, remove it from the map
							for (int i = 0; i < units.size(); i++)
							{
								ActiveUnit old = units.get(i);
								if (old.x == selectedTile.x && old.y == selectedTile.y)
								{
									System.out.println(map.mapObjects.remove(selectedTile.fgobj));	// Clear the old unit's fgobject from the object list,
									
									map.mapData[old.x][old.y].fgobj = null;	// and from its associated map tile
									units.remove(i);						// Remove the old unit from the unit list
									i = 6;									// Stop looping
								}
							}
							
							if (units.size() < 6)
							{
								// It is now safe to add this unit and its fgobject to the map.
								// Add unit to unit list
								ActiveUnit au = new ActiveUnit(unit, selectedTile.x, selectedTile.y, selectedTile.z, player); 
								units.add(au);
								
								// Add fgobject to its associated tile and to the map's object list
								
								addUnit(au);
							}
							
							repaint();
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	public void beginPlacementMode()
	{
		mode = 1;	// 1 = placement mode
		// fgObjects.remove(selector);

		ZankMapTile[] startingTiles;
		if (player == 1) startingTiles = map.p1StartingTiles;
		else if (player == 2) startingTiles = map.p2StartingTiles;
		else startingTiles = new ZankMapTile[0];
		
		for (ZankMapTile tile : startingTiles)
		{
			ForegroundObject fgo = new ForegroundObject("resources/maps/hltile.png", tile.x, tile.y, tile.z, 0, 0, false, FGObjectType.HIGHLIGHT);
			hlTiles.add(fgo);
			fgObjects.add(fgo);
		}

		
	}
	
	public void beginGame()
	{
		mode = 0;
		removeHighlightedTiles();
		
	}
	
	public void removeHighlightedTiles()
	{
		Iterator<ForegroundObject> itr = fgObjects.iterator();
		while (itr.hasNext())
		{
			ForegroundObject curr = itr.next();
			if (hlTiles.contains(curr))
				itr.remove();
		}
	}
	
	// Remove units' fgobjects from the treeset
	public void removeUnits()
	{
		Iterator<ForegroundObject> itr = fgObjects.iterator();
		while (itr.hasNext())
		{
			ForegroundObject curr = itr.next();
			if (curr.type == FGObjectType.UNIT)
			{
				map.mapData[curr.xTile][curr.yTile].fgobj = null;
				itr.remove();
			}
			
		}
	}
	
	// Add a unit's fgobject to the treeset at a certain location
	public void addUnit(ActiveUnit au)
	{
		ZankMapTile tile = map.mapData[au.x][au.y];
		tile.fgobj = ForegroundObject.makeFGObject(au);
		fgObjects.add(tile.fgobj);
	}
	
	public void selectTile(ZankMapTile selectedTile)
	{
		this.selectedTile = selectedTile;
		if (selectedTile != null)
		{
			fgObjects.remove(selector);
			selector.moveTo(selectedTile);
			fgObjects.add(selector);
			
			int index = -1;
			for (int i = 0; i < units.size(); i++)
				if (units.get(i).x == selectedTile.x && units.get(i).y == selectedTile.y)
					index = i;
			
			if (index == -1)
			{
				selectedUnit = null;
				
				gamePanel.bottomPanel.remove(gamePanel.unitPreview);
				gamePanel.unitPreview = gamePanel.blankUnitPreview;
				gamePanel.bottomPanel.add(gamePanel.unitPreview, BorderLayout.WEST);
				gamePanel.revalidate();
			}
			else
			{
				long start = System.currentTimeMillis();
				selectedUnit = units.get(index);
				
				System.out.println(gamePanel);
				
				gamePanel.bottomPanel.remove(gamePanel.unitPreview);
				gamePanel.unitPreview = new UnitPreviewPanel(selectedUnit);
				gamePanel.bottomPanel.add(gamePanel.unitPreview, BorderLayout.WEST);
				gamePanel.revalidate();
			}
		}	
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		g.drawImage(map.background, 0, 0, null);
		g.drawImage(map.ground, 0, 0, null);
		
		ForegroundObject fgobj;
		Iterator<ForegroundObject> itr = map.mapObjects.iterator();
		while (itr.hasNext())
		{
			fgobj = itr.next(); 
			if (fgobj.z > 0)
				g.drawImage(fgobj.img, fgobj.x, fgobj.y, fgobj.img.getWidth() * fgobj.reflectX, fgobj.img.getHeight(), null);
			
			// System.out.println(fgobj.name);
		}
		
	}
}

// Representation of the game map. Contains back-end logic and resources for drawing the base map
class ZankGameMap
{
	BufferedImage background, ground;
	TreeSet<ForegroundObject> mapObjects;
	ZankMapTile[][] mapData;
	ZankMapTile[] tileList, p1StartingTiles, p2StartingTiles;
	
	
	
}

class ForegroundObject implements Comparable<ForegroundObject>
{
	BufferedImage img;
	int x, y, z, xOffset, yOffset;
	int xTile, yTile;
	int depth;
	int reflectX;
	FGObjectType type;
	String name;
	
	public ForegroundObject(String url, int x, int y, int z, int xOffset, int yOffset, boolean reflectX, FGObjectType type)
	{
		try
		{
			img = ImageIO.read(new File(url));
		}
		catch (IOException e) { System.err.println("ForegroundObject couldn't find image: " + url); }
		name = url;
		if (reflectX)
			this.reflectX = -1;
		else
			this.reflectX = 1;
		
		this.type = type;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		moveTo(x,y,z);		
	}
	
	public void moveTo(ZankMapTile selected)
	{
		moveTo(selected.x, selected.y, selected.z);
	}

	public void moveTo(int x, int y, int z)
	{
		xTile = x;
		yTile = y;
		
		this.x = 224 - 16*x + 16*y + xOffset;
		this.y = 152 + 8*(x + y - z) + yOffset;
		this.z = z;
		
		depth = (x + y) * 10;
		if (type == FGObjectType.HIGHLIGHT)
			depth += 2;
		else if (type == FGObjectType.SELECTOR)
			depth += 4;
		else if (type == FGObjectType.UNIT)
			depth += 6;
		
		if (reflectX == -1)
			this.x += img.getWidth();
	}
	
	@Override
	public int compareTo(ForegroundObject other)
	{
		int diff = this.depth - other.depth;
//		System.out.println(this + " vs " + other);
		if (this == other)	// Comparing two identical objects returns a 0
			return 0;
		
		if (diff == 0 && type != FGObjectType.SELECTOR && other.type != FGObjectType.SELECTOR)	// Non-selectors should be allowed to share depths.
			return 1;																			// However, it needs to return 0 for comparisons with the selector or it'll never get removed
		return this.depth - other.depth;
	}
	
	public static ForegroundObject makeFGObject(ActiveUnit au)
	{
		return new ForegroundObject(au.getSpriteURL(), au.x, au.y, au.z, 8, -18, au.isSpriteReflected(), FGObjectType.UNIT);
	}
}

enum FGObjectType { GROUND, UNIT, OBJECT, SELECTOR, HIGHLIGHT; }

// Sorted list that orders ForegroundObjects by their depth

class ZankMapTile
{
	int x, y, z;
	Terrain terrain;
	FFTAUnit unit;
	ForegroundObject fgobj;
	
	public ZankMapTile(int x, int y, int z, Terrain terrain)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.terrain = terrain;
		unit = null;
	}

	public ZankMapTile(int x, int y, int z)
	{
		this(x, y, z, Terrain.NORMAL);
	}
	
	public int renderCenterX()
	{
		return 240 - 16*x + 16*y;
	}
	
	public int renderCenterY()
	{
		return 160 + 8*(x + y - z);
	}
	
	public int renderCornerX()
	{
		return renderCenterX() - 16;
	}
	
	public int renderCornerY()
	{
		return renderCenterY() - 8;
	}
	
	public String toString()
	{
		return x + ", " + y + ", " + z + ". " + terrain;
	}
	
	enum Terrain { NORMAL, WATER, BLOCK };
}

