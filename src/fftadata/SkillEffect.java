package fftadata;

import java.io.Serializable;

public enum SkillEffect implements Serializable
{
	HEALING_1X										(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev)
	{ 	return Healing1X(result);			}		public String applyEffect(SkillEffectResult result)
	{	return applyHealing(result);		}		}),
	
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
	
	
	static GameState state;
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
	
	public static void setGameState(GameState gameState)
	{
		state = gameState;
	}
		
	// Server-side effect handlers
	public static SkillEffectResult GenericDamageEffect(SkillEffectResult result,
			boolean canCrit, boolean capToTargetHP, boolean healing, boolean neverMiss)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate;
		if (neverMiss)
			hitRate = 100;
		else
			hitRate = FFTACalc.getATypeHitRate(user, target, skill);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate)
		{
			// Calc damage
			int dmg = FFTACalc.getDamage(user, target, skill, healing, canCrit, capToTargetHP, false);
			result.critical = FFTACalc.wasCritical;
			
			// Save results
			result.damage = dmg;
			result.success = true;
		}
		else
			result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult Healing1X(SkillEffectResult result)
	{
		return GenericDamageEffect(result, false, false, true, true);
	}
	
	public static SkillEffectResult FightDamage(SkillEffectResult result)
	{
		return GenericDamageEffect(result, true, false, false, false);
	}
	
	public static SkillEffectResult FightDamageCapped(SkillEffectResult result)
	{
		return GenericDamageEffect(result, true, true, false, false);
	}
	
	public static SkillEffectResult Damage1X(SkillEffectResult result)
	{
		return GenericDamageEffect(result, false, true, false, false);
	}
	
	public static SkillEffectResult Damage1XCapped(SkillEffectResult result)
	{
		return GenericDamageEffect(result, false, true, false, false);
	}
	
	public static SkillEffectResult Eff1DepDrain(SkillEffectResult result, SkillEffectResult prev)
	{
		result.target = result.user;
		if (prev.success)
		{
			state.applyHealing(result.target, prev.damage);
			result.damage = prev.damage;
			result.success = true;
		}
		return result;
	}
	
	// Client-side effect handlers
	public static String applyDamage(SkillEffectResult result)
	{
		String report = "";
		
		if (result.damage < 0)
		{
			result.damage = -result.damage;
			return applyHealing(result);
		}
		
		ActiveUnit target = state.units[result.target];
		if (result.success)
		{
			state.applyDamage(result.target, result.damage);
			report = "<br><em><span style=\"color:gray\">......<strong>";
			if (result.critical)
				report += "<span style=\"color:red\">CRITICAL HIT!</span> ";  
			
			report += target.unit.name + "</strong> takes <strong><span style=\"color:red\">" + 
					result.damage + "</strong> damage! (" + result.hitChance + "%)";
		}
		else
			report = "<br><em><span style=\"color:gray\">......The attack misses <strong>" +
					target.unit.name + "</strong>! (" + result.hitChance + "%)";
				
		return report;
	}
	
	public static String applyHealing(SkillEffectResult result)
	{
		String report = "";
		
		ActiveUnit target = state.units[result.target];
		if (result.success)
		{
			state.applyHealing(result.target, result.damage);
			report = "<br><em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong> recovers <strong><span style=\"color:green\">" + result.damage + "</strong> HP!";
		}
		return report;
	}
}

