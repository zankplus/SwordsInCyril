package fftadata;

import java.awt.Image;
import java.io.Serializable;

public class ActiveUnit implements Serializable
{
	public FFTAUnit unit;
	public int x, y, z;
	public int currHP, currMP;
	public int team;
	public Direction dir;
		
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
}

enum Direction { NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST };