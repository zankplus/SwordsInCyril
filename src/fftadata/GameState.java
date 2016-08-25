package fftadata;

import java.util.ArrayList;

public class GameState
{
	public ActiveUnit[] units;
	public int currentUnit;
	
	public GameState(ActiveUnit[] units)
	{
		this.units = units;
		SkillEffect.setGameState(this);
	}
	
	public void startOfTurnEffects()
	{
		ActiveUnit au = units[currentUnit];
		au.currMP = Math.min(au.currMP + 5, (int) au.unit.maxMP);
	}
	
	public void expendMP(FFTASkill sk)
	{
		int cost = sk.MP_COST;
		if (units[currentUnit].unit.support == FFTASupport.HALF_MP)
			cost /= 2;
		else if (units[currentUnit].unit.support == FFTASupport.TURBO_MP)
			cost *= 2;
		units[currentUnit].currMP -= cost;
	}
	
	public String applyEffect(SkillEffectResult result)
	{
		return result.effect.handler.applyEffect(result);
	}
	
	public void applyDamage(int targ, int dmg)
	{
		ActiveUnit au = units[targ];
		au.currHP -= dmg;
		if (au.currHP <= 0)
		{
			au.currHP = 0;
			au.counter = 0;
			au.reserve = 0;
		}
		
		System.out.println(au.unit.name + ": " + au.currHP + " HP");
	}
	
	public void applyHealing(int targ, int hp)
	{
		ActiveUnit au = units[targ];
		au.currHP += hp;
		if (au.currHP > au.unit.maxHP)
			au.currHP = (int) au.unit.maxHP;
		
		System.out.println(au.unit.name + ": " + au.currHP + " HP");
	}
	
	public void applyMPDamage(int targ, int dmg)
	{
		ActiveUnit au = units[targ];
		au.currMP -= dmg;
		if (au.currMP <= 0)
			au.currMP = 0;
		
		System.out.println(au.unit.name + ": " + au.currMP + " MP");
	}
	
	public void applyMPHealing(int targ, int mp)
	{
		ActiveUnit au = units[targ];
		au.currMP += mp;
		if (au.currMP > au.unit.maxMP)
			au.currMP = (int) au.unit.maxMP;
		
		System.out.println(au.unit.name + ": " + au.currMP + " MP");
	}
	
	// Because this function is only called by the click handler when selecting a space it has
	// already confirmed is within your targeting range, the getTargets() method can safely
	// assume that the selected tile is within range
	public ArrayList<Integer> getTargets(int x, int y, FFTASkill sk, ActiveUnit user)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		// Obtain the skill's targeting type
		Targeting targ = sk.TARGETING;
		int range = sk.H_RANGE, radius = sk.H_RADIUS, vertical = sk.V_RADIUS;
		
		// If the skill inherits its targeting type from the weapon, replace it with that
		// weapon's targeting type
		if (targ == Targeting.AS_WEAPON)
		{
			vertical = 3;
			FFTAEquip weapon = units[currentUnit].unit.getWeapon();
			range = weapon.range;
			if (weapon.type == EquipType.SPEAR)
				targ = Targeting.DIRECTIONAL;
			else
				targ = Targeting.FREE_SELECT;
		}
		
		// If the skill uses free selection
		if (targ == Targeting.FREE_SELECT)
			for (int i = 0; i < units.length; i++)
			{
				if ((units[i].currHP > 0 && sk.TARGET_LIVE) ||
					(units[i].currHP == 0 && sk.TARGET_DEAD))
				{
					int dist = Math.abs(units[i].x - x) + Math.abs(units[i].y - y);
					
					if (dist == 0)
						result.add(0, i);
					else if (dist <= radius)
						result.add(i);
				}
			}

		else if (targ == Targeting.DIRECTIONAL)
		{
			int dx = x - user.x, dy = y - user.y;
			if (dx != 0) dx = dx / Math.abs(dx);
			if (dy != 0) dy = dy / Math.abs(dy);
			
			System.out.println("dx: " + dx + "  dy: " + dy);
			
			for (int i = 0; i < units.length; i++)
			{
				if ((units[i].currHP > 0 && sk.TARGET_LIVE) ||
						(units[i].currHP == 0 && sk.TARGET_DEAD))
				{
					int dx2 = units[i].x - user.x, dy2 = units[i].y - user.y;
					if (dx2 != 0) dx2 = dx2 / Math.abs(dx2);
					if (dy2 != 0) dy2 = dy2 / Math.abs(dy2);
					
					
					if ((dx == dx2) && (dy == dy2) && (dx2 == 0 ^ dy2 == 0))	// aligned in one dimension or the other
					{
						int sdist = Math.abs(units[i].x - x) + Math.abs(units[i].y - y);
						if (sdist == 0)
							result.add(0, i);
						else if (sdist <= range)
						{
							System.out.println("Targeting " + units[i].unit.name + " - " + sdist);
							result.add(i);
						}
					}
				}
			}
			
		}	
		return result;
	}
}