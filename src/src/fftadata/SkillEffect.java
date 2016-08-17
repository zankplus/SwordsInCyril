package fftadata;

import java.io.Serializable;

public enum SkillEffect implements Serializable
{
	DAMAGE_1X										(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
	{ 	return Damage1X(result);			}		public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);			}		}),
	
	DAMAGE_1X_CAPPED								(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
	{ 	return Damage1XCapped(result);		}		public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);			}		}),
	
	FIGHT_DAMAGE									(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
	{ 	return FightDamage(result);			}		public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);			}		}),
	
	FIGHT_DAMAGE_CAPPED								(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
	{ 	return FightDamageCapped(result);	}		public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);			}		}),
	
	EFF1DEP_DRAIN									(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
	{ 	return Eff1DepDrain(result, prev);	}		public String applyEffect(SkillEffectResult result)
	{	return applyHealing(result);		}		});
	
	
	static ActiveUnit[] units;
	public final SkillEffectHandler handler;
	
	SkillEffect(SkillEffectHandler handler)
	{
		this.handler = handler;
	}
	
	public interface SkillEffectHandler
	{
		SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev); // serverside
		String applyEffect(SkillEffectResult result); // clientside
	}
	
	public static void setUnitList(ActiveUnit[] unitList)
	{
		units = unitList;
	}
	
	// Ancillary methods
	public static void applyDamage(ActiveUnit au, int dmg)
	{
		au.currHP -= dmg;
		if (au.currHP <= 0)
		{
			au.currHP = 0;
			au.counter = 0;
			au.reserve = 0;
		}
	}
	
	public static void applyHealing(ActiveUnit au, int hp)
	{
		au.currHP += hp;
		if (hp > au.unit.maxHP)
			au.currHP = (int) au.unit.maxHP;
	}
	
	// Server-side effect handlers
	public static SkillEffectResult GenericDamageEffect(SkillEffectResult result,
			boolean canCrit, boolean capToTargetHP)
	{
		ActiveUnit user = units[result.user];
		ActiveUnit target = units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate = FFTACalc.getATypeHitRate(user, target, skill);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate)
		{
			int dmg = FFTACalc.getDamage(user, target, skill);
			int variance = dmg / 10;
			dmg += (int) (2 * variance * Math.random()) - variance;
			
			// Critical check
			if (canCrit)
			{
				rand = (int) (100 * Math.random());
				if (rand < 5)
				{
					result.critical = true;
					dmg = dmg * 3 / 2;
				}
			}
			
			// Cap to 999
			dmg = Math.min(Math.max(dmg, 1), 999);
			
			// Cap to target's HP, if applicable
			if (capToTargetHP)
				dmg = Math.min(dmg, target.currHP);
			
			// Apply changes server-side
			applyDamage(target, dmg);
			
			// Save results
			result.damage = dmg;
			result.success = true;
		}
		else
			result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult FightDamage(SkillEffectResult result)
	{
		return GenericDamageEffect(result, true, false);
	}
	
	public static SkillEffectResult FightDamageCapped(SkillEffectResult result)
	{
		return GenericDamageEffect(result, true, true);
	}
	
	public static SkillEffectResult Damage1X(SkillEffectResult result)
	{
		return GenericDamageEffect(result, false, true);
	}
	
	public static SkillEffectResult Damage1XCapped(SkillEffectResult result)
	{
		return GenericDamageEffect(result, false, true);
	}
	
	public static SkillEffectResult Eff1DepDrain(SkillEffectResult result, SkillEffectResult prev)
	{
		result.target = result.user;
		if (prev.success)
		{
			applyHealing(units[result.target], prev.damage);
			result.damage = prev.damage;
			result.success = true;
		}
		return result;
	}
	
	// Client-side effect handlers
	public static String applyDamage(SkillEffectResult result)
	{
		String report = "";
		
		ActiveUnit target = units[result.target];
		if (result.success)
		{
			applyDamage(target, result.damage);
			report = "<br><em><span style=\"color:gray\">......<strong>";
			if (result.critical)
				report += "<span style=\"color:red\">CRITICAL HIT!</span> ";  
			
			report += units[result.target].unit.name + "</strong> takes <strong><span style=\"color:red\">" + 
					result.damage + "</strong> damage! (" + result.hitChance + "%)";
		}
		else
			report = "<br><em><span style=\"color:gray\">......The attack misses <strong>" +
					units[result.target].unit.name + "</strong>! (" + result.hitChance + "%)";
				
		return report;
	}
	
	public static String applyHealing(SkillEffectResult result)
	{
		String report = "";
		
		ActiveUnit target = units[result.target];
		if (result.success)
		{
			applyHealing(target, result.damage);
			report = "<br><em><span style=\"color:gray\">......<strong>" + units[result.target].unit.name +
					"</strong> recovers <strong><span style=\"color:green\">" + result.damage + "</strong> HP!";
		}
		return report;
	}
}

