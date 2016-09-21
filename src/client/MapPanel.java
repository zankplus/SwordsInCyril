package client;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.ZankMapTile.Terrain;
import fftadata.ActiveUnit;
import fftadata.EquipType;
import fftadata.FFTAEquip;
import fftadata.FFTASkill;
import fftadata.FFTAUnit;
import fftadata.Targeting;

//Panel representing the game map. All graphical logic should go here
public class MapPanel extends JPanel
{
	Engagement game;
	EngagementWindow window;
	ZankGameMap map;
	
	boolean drawTile = false;
	ClanBuilderRoster roster;
	int player;
	ArrayList<ActiveUnit> mpUnits;
	TreeSet<ForegroundObject> fgObjects;
	ZankMapTile selectedTile;
	ActiveUnit selectedUnit;
	
	static ForegroundObject selector = new ForegroundObject("resources/maps/selector.png", 0, 0, 0, 0, -31, false, FGObjectType.SELECTOR);
	ArrayList<ForegroundObject> hlTiles;
	int mode;
	
	public MapPanel(EngagementWindow window)
	{
		this.window = window;
		game = window.game;
		
		player = game.playerNumber;
		map = game.map;
		// int targetX = 240 + 16*x - 16*y, targetY = 160 + 8*x + 8*y;
		selectedTile = null;
		mpUnits = new ArrayList<ActiveUnit>();
		hlTiles = new ArrayList<ForegroundObject>();
		fgObjects = map.mapObjects;

		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e)	{ }
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
					selectTile(selectedTile);
				
				// Unit placement mode
				else if (mode == 1)
				{
					placeUnit();
				}
				
				// Movement mode
				else if (mode == 2)
				{
					if (e.getClickCount() == 1)
						selectTile(selectedTile);
					else if (e.getClickCount() == 2 && selectedTile != null)
					{
//						System.out.println("[" + selectedTile.x + ", " + selectedTile.y + "]");
						for (int i = 0; i < hlTiles.size(); i++)
						{
							if (selectedTile.x == hlTiles.get(i).xTile && selectedTile.y == hlTiles.get(i).yTile)
							{
								moveUnit(game.currentUnit(), selectedTile);
								window.finishMovementMode();
								
								i = hlTiles.size();
							}
						}
					}
				}
				
				// Targeting mode
				else if (mode == 3)
				{
					selectTile(selectedTile);
					
					for (int i = 0; i < hlTiles.size(); i++)
						if (selectedTile != null && selectedTile.x == hlTiles.get(i).xTile && selectedTile.y == hlTiles.get(i).yTile)
						{
							ArrayList<Integer> targetIDs = game.getTargets(selectedTile.x, selectedTile.y,
									window.selectedSkill, game.currentUnit());
							ArrayList<ActiveUnit> targets = new ArrayList<ActiveUnit>();
							
							
							ActiveUnit au, curr = game.currentUnit();
							for (Integer j : targetIDs)
							{
								au = game.getUnits()[j];
								if (((au.currHP > 0 && window.selectedSkill.TARGET_LIVE) ||
									 (au.currHP == 0 && window.selectedSkill.TARGET_DEAD)) &&
									 (window.selectedSkill.TARGET_ENEMY || au.team == curr.team))
								targets.add(game.getUnits()[j]);
							}
							
							if (targets.size() > 0)
							{
								window.showDamagePreview(targets);
								
								if (e.getClickCount() == 2)
									window.commitAction(targetIDs);
							}
							
							i = hlTiles.size();
						}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0)	{ }
			
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
			ForegroundObject fgo = new ForegroundObject("resources/maps/hltile.png", tile.x, tile.y, tile.z, 0, 1, false, FGObjectType.HIGHLIGHT);
			hlTiles.add(fgo);
			fgObjects.add(fgo);
		}		
	}
	
	public void beginGame(ArrayList<ActiveUnit> otherTeam)
	{
		
		for (ActiveUnit au : otherTeam)
		{
			addUnit(au);
			mpUnits.add(au);
		}
		
		mode = 0;
		removeHighlightedTiles();
		
	}
	
	public void selectTile(ZankMapTile tile)
	{
		if (selectedTile != null)
			System.out.println("Selected " + selectedTile.x + ", " + selectedTile.y + ", " + selectedTile.z);
		this.selectedTile = tile;
		if (selectedTile != null)
		{
			fgObjects.remove(selector);
			selector.moveTo(selectedTile);
			fgObjects.add(selector);
			
			int index = -1;
			for (int i = 0; i < mpUnits.size(); i++)
				if (mpUnits.get(i).x == selectedTile.x && mpUnits.get(i).y == selectedTile.y)
					index = i;
				
			if (index == -1)
			{
				selectedUnit = null;
				window.hideUnitPreview();
			}
			else
			{
				selectedUnit = mpUnits.get(index);
				window.showUnitPreview(selectedUnit);
			}
		}
		
		repaint();
	}
	
	public void placeUnit()
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
				for (int i = 0; i < mpUnits.size(); i++)
				{
					ActiveUnit old = mpUnits.get(i);
					if (old.unit == unit)
					{
						map.mapObjects.remove(map.mapData[old.x][old.y].fgobj);	// Clear the old unit's fgobject from the map
						map.mapData[old.x][old.y].fgobj = null;					// and from its associated map tile
						mpUnits.remove(i);	// Clear the old unit from the unit list
						i = 6;				// Stop looping
					}
				}
				
				// If another unit is already present in this tile, remove it from the map
				for (int i = 0; i < mpUnits.size(); i++)
				{
					ActiveUnit old = mpUnits.get(i);
					if (old.x == selectedTile.x && old.y == selectedTile.y)
					{
						map.mapObjects.remove(selectedTile.fgobj);	// Clear the old unit's fgobject from the object list,
						
						map.mapData[old.x][old.y].fgobj = null;	// and from its associated map tile
						mpUnits.remove(i);						// Remove the old unit from the unit list
						i = 6;									// Stop looping
					}
				}
				
				if (mpUnits.size() < 6)
				{
					// It is now safe to add this unit and its fgobject to the map.
					// Add unit to unit list
					ActiveUnit au = new ActiveUnit(unit, selectedTile.x, selectedTile.y, selectedTile.z, player); 
					mpUnits.add(au);
					
					// Add fgobject to its associated tile and to the map's object list
					
					addUnit(au);
				}
				
				repaint();
			}
		}
	}

	public void highlightWalkableTiles()
	{
		ActiveUnit au = game.currentUnit();
		hlTiles.clear();
		ArrayList<ZankMapTile> reachableTiles = map.getReachableTiles(au);
		for (ZankMapTile tile : reachableTiles)
		{
			ForegroundObject fgo = new ForegroundObject("resources/maps/hltile.png", tile.x, tile.y, tile.z, 0, 1, false, FGObjectType.HIGHLIGHT);
			hlTiles.add(fgo);
			fgObjects.add(fgo);
		}
		repaint();
	}

	public void highlightTargetableTiles(FFTASkill sk)
	{
		ActiveUnit au = game.currentUnit();
		Targeting targ = sk.TARGETING;
		int range = sk.H_RANGE;
		
		hlTiles.clear();
		
		if (targ == Targeting.AS_WEAPON)
		{
			FFTAEquip weapon = au.unit.getWeapon();
			range = weapon.range;
			if (weapon.type == EquipType.SPEAR)
				targ = Targeting.DIRECTIONAL;
			else
				targ = Targeting.FREE_SELECT;
		}
		
		// Free Select targeting
		if (targ == Targeting.FREE_SELECT)
		{
			int xmin = Math.max(au.x - range, 0), xmax = Math.min(au.x + range, 14),
					ymin = Math.max(au.y - range, 0), ymax = Math.min(au.y + range, 14);
			
			for (int x = xmin; x <= xmax; x++)
				for (int y = ymin; y <= ymax; y++)
				{
					int dist = Math.abs(x - au.x) + Math.abs(y - au.y);
					if (map.mapData[x][y] != null && dist <= range && (dist > 0 || sk.SELF_TARGET))
					{
						ForegroundObject fgo = new ForegroundObject("resources/maps/hltarget.png", x, y, map.mapData[x][y].z, 0, 1, false, FGObjectType.HIGHLIGHT);
						hlTiles.add(fgo);
						fgObjects.add(fgo);
					}
				}
		}
		
		else if (targ == Targeting.DIRECTIONAL)
		{
			// Add targets in same x-dimension
			int y = au.y;
			for (int x = au.x + range; x >= au.x - range; x--)
			{	
				if (x < 15 && x >= 0 && x != au.x && map.mapData[x][y] != null)
				{
					ForegroundObject fgo = new ForegroundObject("resources/maps/hltarget.png", x, y, map.mapData[x][y].z, 0, 1, false, FGObjectType.HIGHLIGHT);
					hlTiles.add(fgo);
					fgObjects.add(fgo);
				}
			}
			
			// Add targets in same y-dimension
			int x = au.x;
			for (y = au.y + range; y >= au.y - range; y--)
			{	
				if (y < 15 && y >= 0 && y != au.y && map.mapData[x][y] != null)
				{
					ForegroundObject fgo = new ForegroundObject("resources/maps/hltarget.png", x, y, map.mapData[x][y].z, 0, 1, false, FGObjectType.HIGHLIGHT);
					hlTiles.add(fgo);
					fgObjects.add(fgo);
				}
			}
		}
		repaint();
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
		repaint();
	}
	
	// Add a unit's fgobject to the treeset at a certain location
	public void addUnit(ActiveUnit au)
	{
		ZankMapTile tile = map.mapData[au.x][au.y];
		tile.unit = au;
		tile.fgobj = ForegroundObject.makeFGObject(au);
		fgObjects.add(tile.fgobj);
	}
	
	public void removeUnit(ActiveUnit au)
	{
		ZankMapTile tile = map.mapData[au.x][au.y];
		tile.unit = null;
		
		Iterator<ForegroundObject> itr = fgObjects.iterator();
		while (itr.hasNext())
		{
			ForegroundObject curr = itr.next();
			if (curr == tile.fgobj)
			{
				map.mapData[au.x][au.y].fgobj = null;
				itr.remove();
			}
			
		}
		tile.fgobj = null;
	}
	
	// Remove units' fgobjects from the treeset
	public void removeAllUnits()
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

	public void moveUnit(ActiveUnit au, ZankMapTile dest)
	{
		// au = gamePanel.units[gamePanel.currentUnit]; 
		removeUnit(au);
		au.oldX = au.x;
		au.oldY = au.y;
		au.oldZ = au.z;
		au.x = dest.x;
		au.y = dest.y;
		au.z = dest.z;
		addUnit(au);
		repaint();
	}

	public void updateSprite(ActiveUnit au)
	{
		moveUnit(au, map.mapData[au.x][au.y]);
		repaint();
	}
	
	public BufferedImage makeSpriteTranslucent(BufferedImage source, double alpha)
	{
		BufferedImage target = new BufferedImage(source.getWidth(), source.getHeight(), java.awt.Transparency.TRANSLUCENT);
		Graphics2D g = target.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
		g.drawImage(source, null, 0, 0);
		g.dispose();
		return target;
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
			
			BufferedImage target;
			if (fgobj.type == FGObjectType.HIGHLIGHT)
				target = makeSpriteTranslucent(fgobj.img, 0.85);
			else
				target = fgobj.img;
			
			if (fgobj.z > 0)
				g.drawImage(target, fgobj.x, fgobj.y, fgobj.img.getWidth() * fgobj.reflectX, fgobj.img.getHeight(), null);
		}
		
	}
}

//Representation of the game map. Contains back-end logic and resources for drawing the base map
class ZankGameMap
{
	BufferedImage background, ground;
	TreeSet<ForegroundObject> mapObjects;
	ZankMapTile[][] mapData;
	ZankMapTile[] tileList, p1StartingTiles, p2StartingTiles;
	
	public ArrayList<ZankMapTile> getReachableTiles(ActiveUnit au)
	{
		
		ArrayList<ZankMapTile> reachableTiles = new ArrayList<ZankMapTile>();
		ZankMapTile start = mapData[au.x][au.y];
		
		getReachableTiles(reachableTiles, start, au.unit.getTotalMove(), au);
		
		return reachableTiles;
	}
	
	private void getReachableTiles(ArrayList<ZankMapTile> reachableTiles, ZankMapTile curr, int move, ActiveUnit au)
	{
		
		FFTAEquip shoes = au.unit.equips.getShoes();
		if (move >= 0)
		{
//			System.out.println("\t(" + move + ") Can move to " + curr.x + ", " + curr.y  + ", " + curr.z);
			if (!reachableTiles.contains(curr) && (curr.unit == null))
				reachableTiles.add(curr);
			
			ArrayList<ZankMapTile> checkNext = new ArrayList<ZankMapTile>();
			if (tileIsWalkable(curr.x+1, curr.y, au))
				checkNext.add(mapData[curr.x+1][curr.y]);
			if (tileIsWalkable(curr.x, curr.y+1, au))
				checkNext.add(mapData[curr.x][curr.y+1]);
			if (tileIsWalkable(curr.x-1, curr.y, au))
				checkNext.add(mapData[curr.x-1][curr.y]);
			if (tileIsWalkable(curr.x, curr.y-1, au))
				checkNext.add(mapData[curr.x][curr.y-1]);
			
			for (ZankMapTile zt : checkNext)
			{
				if ((zt.z <= curr.z + au.unit.getTotalJump() && zt.z >= curr.z - (au.unit.getTotalJump() + 1))
						|| shoes == FFTAEquip.FAIRY_SHOES || shoes == FFTAEquip.GALMIA_SHOES)
					getReachableTiles(reachableTiles, zt, move - 1, au);
			}
		}
		
	}
	
	public boolean tileExists(int x, int y)
	{
		return 	x >= 0 && x < 15 &&
				y >= 0 && y < 15 &&
				mapData[x][y] != null;
	}
	
	public boolean tileIsWalkable(int x, int y, ActiveUnit au)
	{
		try
		{
			return (tileExists(x, y) &&
					(mapData[x][y].unit == null || mapData[x][y].unit.team == au.team || 
					mapData[x][y].unit.currHP == 0 || au.unit.equips.getShoes() == FFTAEquip.FAIRY_SHOES));
		}
		catch(NullPointerException e) { System.err.println(x + ", " + y + " | " + mapData[x][y]); return false;}
		
	}
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
	
	// TODO: Have this constructor take an image instead of a URL for one to avoid loading the same image multiple times
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
	
	public void setImage(String url)
	{
		name = url;
		try
		{
			img = ImageIO.read(new File(url));
		}
		catch (IOException e) { System.err.println("ForegroundObject couldn't find image: " + url); }
	}
	
	public static ForegroundObject makeFGObject(ActiveUnit au)
	{
		return new ForegroundObject(au.getSpriteURL(), au.x, au.y, au.z, 8, -18, au.isSpriteReflected(), FGObjectType.UNIT);
	}
	
	public String toString()
	{
		return x + "\t" + y + "\t" + name;
	}
}

enum FGObjectType { GROUND, UNIT, OBJECT, SELECTOR, HIGHLIGHT; }

//Sorted list that orders ForegroundObjects by their depth
class ZankMapTile
{
	int x, y, z;
	Terrain terrain;
	ActiveUnit unit;
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

