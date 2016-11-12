package fftamap;

import fftadata.ActiveUnit;

public class FFTAMapTile
{
	public int x, y, z;
	public Terrain terrain;
	public ActiveUnit unit;
	public ForegroundObject fgobj;
	
	public FFTAMapTile(int x, int y, int z, Terrain terrain)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.terrain = terrain;
		unit = null;
	}

	public FFTAMapTile(int x, int y, int z)
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
	
	
	
	
}

enum Terrain { NORMAL, WATER, BLOCK };