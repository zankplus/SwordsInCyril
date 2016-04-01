package fftadata;

import java.awt.Image;
import java.io.Serializable;

public class ActiveUnit implements Serializable
{
	// Status effect constants
	static final int QUICK = 0;
	static final int HASTE = 1;
	static final int SLOW = 2;
	static final int SPEED_DOWN = 3;
	
	// Fields
	public FFTAUnit unit;
	public int x, y, z;
	public int oldX, oldY, oldZ;
	public int currHP, currMP;
	public int counter;
	public int reserve;
	public int team;
	public int priority;	// used to decide who goes first if two units both have 1000 counter
	public Direction dir;
	
	public int[] status;
		
	public ActiveUnit(FFTAUnit unit, int x, int y, int z, int team)
	{
		this.unit = unit;
		this.x = x;
		this.y = y;
		this.z = z;
		currHP = (int) unit.maxHP;
		currMP = (int) unit.maxMP;
		this.team = team;
		if (team == 1)
			dir = Direction.SOUTHWEST;
		else if (team == 2)
			dir = Direction.SOUTHEAST;
		
		counter = 0;
		reserve = 0;
		
		status = new int[40];
	}
	
	public String getSpriteURL()
	{
		StringBuilder url = new StringBuilder();
		url.append("resources/jobs/" + unit.job.name());
		
		if (team == 1)
			url.append("_ally");
		else if (team == 2)
			url.append("_enemy");
		
		url.append("_stand");
		
		if (dir == Direction.NORTHEAST || dir == Direction.NORTHWEST)
			url.append("_nw");
		else
			url.append("_sw");
		
		url.append(".png");
		
		return url.toString();
	}
	
	public boolean isSpriteReflected()
	{
		return (dir == Direction.SOUTHEAST || dir == Direction.NORTHEAST);
	}
	
	// Returns the unit's speed as modified by the unit's current status effects 
	public int getTickSpeed()
	{
		double modifier = 1.0;
//		if (status[HASTE] > 0)
//			modifier *= 2;
//		else if (status[SLOW] > 0)
//			modifier *= 0.5;
//		if (status[SPEED_DOWN] > 0)
//			modifier *= 0.5;
		
		return (int) (modifier * unit.getTotalSpeed());
	}
	
	public void face(String dir)
	{
		if (dir.equalsIgnoreCase("NW"))
			this.dir = Direction.NORTHWEST;
		else if (dir.equalsIgnoreCase("NE"))
			this.dir = Direction.NORTHEAST;
		else if (dir.equalsIgnoreCase("SW"))
			this.dir = Direction.SOUTHWEST;
		else
			this.dir = Direction.SOUTHEAST;
	}
}

enum Direction { NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST };