package fftamap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import fftadata.ActiveUnit;
import fftadata.FFTAEquip;

public //Representation of the game map. Contains back-end logic and resources for drawing the base map
class FFTAMap
{
	public BufferedImage background, ground;
	public TreeSet<ForegroundObject> mapObjects;
	public FFTAMapTile[][] mapData;
	public FFTAMapTile[] tileList, p1StartingTiles, p2StartingTiles;
	
	public ArrayList<FFTAMapTile> getReachableTiles(ActiveUnit au)
	{
		
		ArrayList<FFTAMapTile> reachableTiles = new ArrayList<FFTAMapTile>();
		FFTAMapTile start = mapData[au.x][au.y];
		
		getReachableTiles(reachableTiles, start, au.unit.getTotalMove(), au);
		
		return reachableTiles;
	}
	
	private void getReachableTiles(ArrayList<FFTAMapTile> reachableTiles, FFTAMapTile curr, int move, ActiveUnit au)
	{
		
		FFTAEquip shoes = au.unit.equips.getShoes();
		if (move >= 0)
		{
//			System.out.println("\t(" + move + ") Can move to " + curr.x + ", " + curr.y  + ", " + curr.z);
			if (!reachableTiles.contains(curr) && (curr.unit == null))
				reachableTiles.add(curr);
			
			ArrayList<FFTAMapTile> checkNext = new ArrayList<FFTAMapTile>();
			if (tileIsWalkable(curr.x+1, curr.y, au))
				checkNext.add(mapData[curr.x+1][curr.y]);
			if (tileIsWalkable(curr.x, curr.y+1, au))
				checkNext.add(mapData[curr.x][curr.y+1]);
			if (tileIsWalkable(curr.x-1, curr.y, au))
				checkNext.add(mapData[curr.x-1][curr.y]);
			if (tileIsWalkable(curr.x, curr.y-1, au))
				checkNext.add(mapData[curr.x][curr.y-1]);
			
			for (FFTAMapTile zt : checkNext)
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