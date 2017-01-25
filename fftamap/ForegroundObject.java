package fftamap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fftadata.ActiveUnit;

public class ForegroundObject implements Comparable<ForegroundObject>
{
	public BufferedImage img;
	public int x, y, z, xOffset, yOffset;
	public int xTile, yTile;
	public int depth;
	public int reflectX;
	public FGObjectType type;
	public String name;
	
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
	
	public void moveTo(FFTAMapTile selected)
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