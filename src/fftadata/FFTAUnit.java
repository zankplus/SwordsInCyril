package fftadata;
import java.io.Serializable;
import java.util.ArrayList;

public class FFTAUnit implements Serializable
{
	public String name;
	public FFTARace race;
	public FFTAJob job;
	public FFTACommand secondary;
	public FFTASupport support;
	public FFTAReaction reaction;
	public FFTACombo combo;
	
	public EquipSet equips;
	
	public FFTAJob statBase;
	public int[] levels;
	
	public double maxHP, maxMP, wAtk, wDef, mPow, mRes, speed;
	public int move, jump, evade; 
	public int leftHandWAtk;
	
	// No args constructor: make a new bangaa warrior unit
	public FFTAUnit()
	{
		this(FFTARace.BANGAA);
	}
	
	public FFTAUnit(FFTARace race)
	{
		name = ("Foobar");
		this.race = race;
		this.job = race.jobs[0];
		
		secondary = FFTACommand.NONE;
		support = FFTASupport.NONE;
		reaction = FFTAReaction.NONE;
		combo = FFTACombo.NONE;
		
		statBase = race.jobs[0];
		levels = new int[race.jobs.length];
		levels[0] = 49;
		
		equips = new EquipSet(this);
		
		updateDeepStats();
		updateShallowStats();
	}
	
	public int getLevel()
	{
		int result = 1;
		for (int i = 0; i < levels.length; i++)
			result += levels[i];
		return result;
	}
	
	public void changeRace(FFTARace newRace, boolean changeJobToo)
	{
		race = newRace;
		
		secondary = FFTACommand.NONE;
		support = FFTASupport.NONE;
		reaction = FFTAReaction.NONE;
		combo = FFTACombo.NONE;
		
		statBase = newRace.jobs[0];
		levels = new int[newRace.jobs.length];
		levels[0] = 49;
		
		updateDeepStats();
		
		if (changeJobToo)
			changeJob(newRace.jobs[0]);
	}
	
	public void changeJob(FFTAJob newJob)
	{
		job = newJob;
		equips.removeIllegalEquipment();
		updateShallowStats();
	}
	
	public EquipType[] getEquippableTypes()
	{
		// Do we need to add shields?
		if (!job.canEquip(EquipType.SHIELD) && support == FFTASupport.SHIELDBEARER)
		{
			EquipType[] result = new EquipType[job.equips.length + 1];
			for (int i = 0; i < job.equips.length; i++)
				result[i] = job.equips[i];
			result[result.length - 1] = EquipType.SHIELD;
		
			for (int k = result.length - 1; k > 0 && result[k].ordinal() < result[k-1].ordinal(); k--)
			{
				EquipType temp = result[k];
				result[k] = result[k-1];
				result[k-1] = temp;
			}
			return result;
		}
		else
			return job.equips;
	}
	
	public int getTotalWAtk()
	{
		double result = wAtk;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].wAtk;
		}
		
		return (int) result;
	}
	
	public int getTotalLeftWAtk()
	{
		return 0;
	}
	
	public int getTotalWDef()
	{
		double result = wDef;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].wDef;
		}
		return (int) result;
	}
	
	public int getTotalMPow()
	{
		double result = mPow;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].mPow;
		}
		return (int) result;
	}
	
	public int getTotalMRes()
	{
		double result = mRes;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].mRes;
		}
		return (int) result;
	}
	
	public int getTotalSpeed()
	{
		double result = speed;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].speed;
		}
		return (int) result;
	}
	
	public int getTotalMove()
	{
		double result = move;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].move;
		}
		return (int) result;
	}
	
	public int getTotalJump()
	{
		double result = jump;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].jump;
		}
		return (int) result;
	}
	
	public int getTotalEvade()
	{
		double result = evade;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].evade;
		}
		return (int) result;
	}
	
	public int getFightPower()
	{
		int result = 0;
		for (int i = 0; i < equips.slots.length; i++)
		{
			result += equips.slots[i].wAtk;
		}
		if (equips.rightHand == -1)
			result += job.unarmedPower;
		return (int) result;
	}
	
	public FFTAEquip getWeapon()
	{
		int index = equips.rightHand;
		if (index == -1)
			return FFTAEquip.UNARMED;
		else
			return equips.slots[index];
	}
	
	public void updateDeepStats()
	{
		maxHP =	statBase.baseHP;
		maxMP =	statBase.baseMP;
		wAtk = 	statBase.baseWAtk;
		wDef =	statBase.baseWDef;
		mPow =	statBase.baseMPow;
		mRes =	statBase.baseMRes;
		speed =	statBase.baseSpeed;
		
		for (int i = 0; i < levels.length; i++)
		{
			maxHP += race.jobs[i].growthHP * levels[i];
			maxMP += race.jobs[i].growthMP * levels[i];
			wAtk += race.jobs[i].growthWAtk * levels[i];
			wDef += race.jobs[i].growthWDef * levels[i];
			mPow += race.jobs[i].growthMPow * levels[i];
			mRes += race.jobs[i].growthMRes * levels[i];
			speed += race.jobs[i].growthSpeed * levels[i];
		}
	}

	public void updateShallowStats()
	{
		move = 	job.move;
		jump = 	job.jump;
		evade =	job.evade;
	}
	
	public String toString()
	{
		String result = (name + " the " + race + " " + job);
		result += ("\n");
		result += ("Level 50");
		result += (statBase + " growths");
		result += ("\n");
		result += ("A-Ab:	" + job.command);
		result += ("A-Ab:	" + secondary);
		result += ("S-Ab:	" + support);
		result += ("R-Ab:	" + reaction);
		result += ("C-Ab:	" + combo);
		result += ("\n");
		for (int i = 0; i < 5; i++)
			if (equips.slots[i] != FFTAEquip.NONE)
				result += ("- " + equips.slots[i]);
		result += ("\n");
		result += ("**");
		
		return result;
	}
}

