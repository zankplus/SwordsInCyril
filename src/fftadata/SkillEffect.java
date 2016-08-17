package fftadata;

import java.io.Serializable;

public enum SkillEffect implements Serializable
{
	DAMAGE_1X(new SkillEffectHandler() { 		public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
		{ 	return Damage1X(result, prev);	}	public String applyEffect(SkillEffectResult result)
		{	return applyDamage(result);		}	});
	
	
	
	
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
	
	public static SkillEffectResult Damage1X(SkillEffectResult result, SkillEffectResult prev)
	{
		ActiveUnit user = units[result.user];
		ActiveUnit target = units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate, dmg;
		
		hitRate = FFTACalc.getATypeHitRate(user, target, skill);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate)
		{
			result.success = true;
			
			dmg = FFTACalc.getDamage(user, target, skill);
			int variance = dmg / 10;
			dmg += (int) (2 * variance * Math.random()) - variance;
			
			// Critical check
			rand = (int) (100 * Math.random());
			if (rand < 5)
				dmg = dmg * 3 / 2;
			
			// Cap again
			dmg = Math.min(Math.max(dmg, 0), 999);
			
			// Apply changes server-side
			applyDamage(target, dmg);
			
			// Add damage to message array
			result.damage = dmg;
		}
		else
			result.success = false;
		
		return result;
	}
	
	public static String applyDamage(SkillEffectResult result)
	{
		String report = "";
		
		ActiveUnit target = units[result.target];
		if (result.success)
		{
			target.currHP -= result.damage;

			if (target.currHP <= 0)
				target.currHP = 0;

			report = "<br><em><span style=\"color:gray\">......<strong>" + units[result.target].unit.name +
					"</strong> takes <strong><span style=\"color:red\">" + result.damage + "</strong> damage! (" +
					+ result.hitChance + "%)";
		}
		else
			report = "<br><em><span style=\"color:gray\">......The attack misses <strong>" +
					units[result.target].unit.name + "</strong>! (" + result.hitChance + "%)";
				
		return report;
	}
}

