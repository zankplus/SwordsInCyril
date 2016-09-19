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
		
		for (int i = 0; i < units.length; i++)
			units[i].id = i;
	}
	
	public int startOfTurnEffects(int poisonVariance)
	{
		ActiveUnit au = units[currentUnit];
		
		// Apply poison damage
		int poisonDamage = 0;
		if (au.status[StatusEffect.POISON.ordinal()] > 0)
		{
			poisonDamage = (poisonVariance * (int) au.unit.maxHP) / 1000;
			applyDamage(currentUnit, poisonDamage);
			
			// If the poison kills the current unit, skip the remaining checks
			if (au.currHP == 0)
			{
				applyDeath(au, false);
				return poisonDamage;
			}
		}
		
		
		// Decrement doom
		if (au.status[StatusEffect.DOOM.ordinal()] > 1)
			au.status[StatusEffect.DOOM.ordinal()] -= 1;
		else if (au.status[StatusEffect.DOOM.ordinal()] == 1)
		{
			au.currHP = 0;
			applyDeath(au, false);
			
			// If the unit dies, return without applying any of the other effects
			return 0;
		}
		
		// Decrement silence
		if (au.status[StatusEffect.SILENCE.ordinal()] > 0) 
			au.status[StatusEffect.SILENCE.ordinal()] -= 1;
		
		// Decrement sleep
		if (au.status[StatusEffect.SLEEP.ordinal()] > 0) 
			au.status[StatusEffect.SLEEP.ordinal()] -= 1;
		
		// Decrement immobilize
		if (au.status[StatusEffect.IMMOBILIZE.ordinal()] > 0) 
			au.status[StatusEffect.IMMOBILIZE.ordinal()] -= 1;
		
		// Decrement disable
		if (au.status[StatusEffect.DISABLE.ordinal()] > 0) 
			au.status[StatusEffect.DISABLE.ordinal()] -= 1;
				
		// Decrement slow
		if (au.status[StatusEffect.SLOW.ordinal()] > 0) 
			au.status[StatusEffect.SLOW.ordinal()] -= 1;

		// Decrement addle
		if (au.status[StatusEffect.ADDLE.ordinal()] > 0) 
			au.status[StatusEffect.ADDLE.ordinal()] -= 1;

		// Decrement confuse
		if (au.status[StatusEffect.CONFUSE.ordinal()] > 0) 
			au.status[StatusEffect.CONFUSE.ordinal()] -= 1;
		
		// Decrement charm
		if (au.status[StatusEffect.CHARM.ordinal()] > 0) 
			au.status[StatusEffect.CHARM.ordinal()] -= 1;

		// Decrement shell
		if (au.status[StatusEffect.SHELL.ordinal()] > 0) 
			au.status[StatusEffect.SHELL.ordinal()] -= 1;
		
		// Decrement protect
		if (au.status[StatusEffect.PROTECT.ordinal()] > 0) 
			au.status[StatusEffect.PROTECT.ordinal()] -= 1;
		
		// Decrement haste
		if (au.status[StatusEffect.HASTE.ordinal()] > 0) 
			au.status[StatusEffect.HASTE.ordinal()] -= 1;
		
		// Decrement quick
		if (au.status[StatusEffect.QUICK.ordinal()] > 0) 
			au.status[StatusEffect.QUICK.ordinal()] -= 1;
		
		// Apply MP regeneration
		applyMPHealing(currentUnit, 5);
		
		// Return poison damage
		return poisonDamage;
	}
	
	// Returns true if the current unit recovers from stop this turn
	public boolean stopTick()
	{
		ActiveUnit au = units[currentUnit];
		boolean result = false;
		
		if (au.status[StatusEffect.STOP.ordinal()] == 1)
			result = true;
		
		au.status[StatusEffect.STOP.ordinal()] = Math.max(0, au.status[StatusEffect.STOP.ordinal()] - 1);
		
		return result;
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
			applyDeath(au, false);
		}
	}
	
	public void applyHealing(int targ, int hp)
	{
		ActiveUnit au = units[targ];
		au.currHP += hp;
		if (au.currHP > au.unit.maxHP)
			au.currHP = (int) au.unit.maxHP;
	}
	
	public void applyMPDamage(int targ, int dmg)
	{
		ActiveUnit au = units[targ];
		au.currMP -= dmg;
		if (au.currMP <= 0)
			au.currMP = 0;
	}
	
	public void applyMPHealing(int targ, int mp)
	{
		ActiveUnit au = units[targ];
		au.currMP += mp;
		if (au.currMP > au.unit.maxMP)
			au.currMP = (int) au.unit.maxMP;
	}
	
	public void applyStatus(ActiveUnit target, StatusEffect sEff)
	{
		// Individual effect considerations
		switch(sEff)
		{
			case SLOW:
				target.status[StatusEffect.HASTE.ordinal()] = 0;
				break;
				
			case HASTE:
				target.status[StatusEffect.SLOW.ordinal()] = 0;
				break;
		
			case PETRIFY:
				applyDeath(target, true);	
				break;
			
			case STOP:
				target.status[StatusEffect.SLOW.ordinal()] = 0;
				target.status[StatusEffect.HASTE.ordinal()] = 0;
				target.reserve = 0;
				break;
				
			case DEATH:
				target.currHP = 0;
				applyDeath(target, false);
				break;
				
			default:
				break;
		}
		
		target.status[sEff.ordinal()] = sEff.DEFAULT_DURATION;
	}
	
	public void applyStatusRecovery(int targ, StatusEffect[] statuses)
	{
		ActiveUnit target = units[targ];
		
		for (StatusEffect sEff : statuses)
			target.status[sEff.ordinal()] = 0;
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
							result.add(i);
						}
					}
				}
			}
			
		}	
		return result;
	}
	
	public void applyDeath(ActiveUnit au, boolean petrify)
	{
		// Death handler
		if (au.status[StatusEffect.AUTO_LIFE.ordinal()] == 0 || petrify )
		{
			au.counter /= 2;
			au.reserve = 0;
		}
		
		// Wipe all status effects, except for those that persist after death
		for (int i = 0; i < au.status.length - 2; i++)
			au.status[i] = 0;
	}
	
	public boolean checkAutoLife(ActiveUnit au)
	{
		boolean revived = false;
		if (au.currHP == 0 && au.status[StatusEffect.AUTO_LIFE.ordinal()] > 0)
		{
			int healing = (int) au.unit.maxHP / 2;
			applyHealing(au.id, healing);
			
			// Reset target's auto-life status
			au.status[StatusEffect.AUTO_LIFE.ordinal()] = 0;
			
			revived = true;
		}
		
		return revived;
	}
}