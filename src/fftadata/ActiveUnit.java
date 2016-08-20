package fftadata;

import java.awt.Image;
import java.io.Serializable;

public class ActiveUnit implements Serializable
{
	// Status effect constants
	static final int QUICK = 0;
	static final int HASTE = 1;
	static final int SLOW = 2;
	static final int SPEED_DOWN = 3;	// TODO: does Speed Down really stack with haste and slow?
	static final int PETRIFY = 4;
	static final int STOP = 5;
	static final int SLEEP = 6;
	static final int EXPERT_GUARD = 7;
	static final int HIBERNATE = 8;
	static final int FROG = 9;
	static final int BLIND = 10;
	static final int CONFUSE = 11;
	static final int ASTRA = 12;
	static final int BERSERK = 13;
	static final int WATK_UP = 14;
	static final int WATK_DOWN = 15;
	static final int MPOW_UP = 16;
	static final int MPOW_DOWN = 17;
	static final int BOOST = 18;
	static final int SHELL = 19;
	static final int PROTECT = 20;
	static final int DEFENSE = 21;
	static final int WDEF_UP = 22;
	static final int WDEF_DOWN = 23;
	static final int MRES_UP = 24;
	static final int MRES_DOWN = 25;
	static final int ZOMBIE = 26;
	
	
	// Fields
	public final int NORTHEAST = 1, NORTHWEST = 2, SOUTHWEST = 3, SOUTHEAST = 4;
	public FFTAUnit unit;
	public int x, y, z;
	public int oldX, oldY, oldZ, oldDir;
	public int currHP, currMP;
	public int counter;
	public int reserve;
	public int team;
	public int priority;	// used to decide who goes first if two units both have 1000 counter
	public int dir;
	public int jp;
	public int id;
	
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
			dir = SOUTHWEST;
		else if (team == 2)
			dir = SOUTHEAST;
		
		counter = 0;
		reserve = 0;
		jp = 0;
		
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
		
		if (currHP > unit.maxHP / 4)
			url.append("_stand");
		else if (currHP > 0)
			url.append("_kneel");
		else
			url.append("_dead");
		
		if (dir == NORTHEAST || dir == NORTHWEST)
			url.append("_nw");
		else
			url.append("_sw");
		
		url.append(".png");
		
		return url.toString();
	}
	
	public boolean isSpriteReflected()
	{
		return (dir == SOUTHEAST || dir == NORTHEAST);
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
			this.dir = NORTHWEST;
		else if (dir.equalsIgnoreCase("NE"))
			this.dir = NORTHEAST;
		else if (dir.equalsIgnoreCase("SW"))
			this.dir = SOUTHWEST;
		else
			this.dir = SOUTHEAST;
	}
	
	public void face(int dir)
	{
		this.dir = dir;
	}
	
	public FFTASkill getFightSkill()
	{
		if (unit.getWeapon() == FFTAEquip.BLOOD_STRINGS || unit.getWeapon() == FFTAEquip.BLOOD_SWORD)
			return FFTASkill.DRAIN_WEAPON;
		
		return FFTASkill.FIGHT;
	}
	
//	public int getDir()
//	{
//		return dir;
//	}
//	
//	public int getID()
//	{
//		return id;
//	}
}