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

import clanBuilder.ClanBuilderRoster;
import fftamap.*;
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
	FFTAMap map;
	
	boolean drawTile = false;
	ClanBuilderRoster roster;
	int player;
	ArrayList<ActiveUnit> mpUnits;
	TreeSet<ForegroundObject> fgObjects;
	FFTAMapTile selectedTile;
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
				for (FFTAMapTile tile : map.tileList)
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
					
					// If using a target-all skill, clicking anywhere will do
					if (window.selectedSkill.TARGETING == Targeting.ALL_ENEMIES ||
						window.selectedSkill.TARGETING == Targeting.ALL ||
						window.selectedSkill.TARGETING == Targeting.SAME_LEVEL_DIGIT)
					{
						selectTarget(e.getClickCount());
					}
					else
					{
						for (int i = 0; i < hlTiles.size(); i++)
							if ((selectedTile != null && selectedTile.x == hlTiles.get(i).xTile && selectedTile.y == hlTiles.get(i).yTile))
							{
								selectTarget(e.getClickCount());
								i = hlTiles.size();
							}
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

		FFTAMapTile[] startingTiles;
		if (player == 1) startingTiles = map.p1StartingTiles;
		else if (player == 2) startingTiles = map.p2StartingTiles;
		else startingTiles = new FFTAMapTile[0];
		
		
		for (FFTAMapTile tile : startingTiles)
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
	
	public void selectTile(FFTAMapTile tile)
	{
		if (selectedTile != null)
			System.out.println("Selected " + selectedTile.x + ", " + selectedTile.y + ", " + selectedTile.z);
		
		window.unitAction.btnFightConfirm.setEnabled(false);
		
		this.selectedTile = tile;
		if (selectedTile != null)
		{
			if (selectedTile.unit == null)
			{
				
			}
			else
				System.out.println(selectedTile.unit.unit.name + "'s here!");
			
			
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
	
	public void selectTarget(int clicks)
	{
		int x = -1, y = -1;
		if (selectedTile != null)
		{
			x = selectedTile.x;
			y = selectedTile.y;
		}
		
		ArrayList<Integer> targetIDs = game.getTargets(x, y, window.selectedSkill, game.currentUnit());
		ArrayList<ActiveUnit> targets = new ArrayList<ActiveUnit>();
		
		ActiveUnit au, curr = game.currentUnit();
		for (Integer j : targetIDs)
		{
			for (int k = 0; k < game.getUnits().length; k++)
				System.out.println(game.getUnits()[k].unit.name + " " + game.getUnits()[k].team);
			
			au = game.getUnits()[j];
			
//			if (((au.currHP > 0 && window.selectedSkill.TARGET_LIVE) ||
//				 (au.currHP == 0 && window.selectedSkill.TARGET_DEAD)) &&
//				((window.selectedSkill.TARGET_ENEMY && au.team != curr.team) ||
//				 (window.selectedSkill.TARGET_ALLY && au.team == curr.team)) )
			if (game.getState().isValidTarget(curr, au, window.selectedSkill))
			{
				targets.add(game.getUnits()[j]);
			}
		}
		
		if (targets.size() > 0)
		{
			window.unitAction.btnFightConfirm.setEnabled(true);
			window.showDamagePreview(targets);
			
			if (clicks == 2)
				window.commitAction(targetIDs);
		}

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
						map.mapData[old.x][old.y].unit = null;					// old position no longer points to this unit
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
		ArrayList<FFTAMapTile> reachableTiles = map.getReachableTiles(au);
		for (FFTAMapTile tile : reachableTiles)
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
		ArrayList<int[]> targetableTiles = game.getState().getTargetableTiles(au, sk);
		hlTiles.clear();
		for (int[] tile : targetableTiles)
		{
			int x = tile[0], y = tile[1];
			ForegroundObject fgo = new ForegroundObject("resources/maps/hltarget.png", x, y, map.mapData[x][y].z, 0, 1, false, FGObjectType.HIGHLIGHT);
			hlTiles.add(fgo);
			fgObjects.add(fgo);
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
		FFTAMapTile tile = map.mapData[au.x][au.y];
		tile.unit = au;
		tile.fgobj = ForegroundObject.makeFGObject(au);
		fgObjects.add(tile.fgobj);
	}
	
	public void removeUnit(ActiveUnit au)
	{
		FFTAMapTile tile = map.mapData[au.x][au.y];
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

	public void moveUnit(ActiveUnit au, FFTAMapTile dest)
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
