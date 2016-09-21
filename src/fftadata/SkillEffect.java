package fftadata;

import java.io.Serializable;

public enum SkillEffect implements Serializable
{
	HEALING_1X																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 1, preview, false, false, false, true, true);		}	public String applyEffect(SkillEffectResult result)
	{	return applyHealing(result);															}	}),
	
	FULLY_HEAL_HP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	result.damage = (int) -state.units[result.target].unit.maxHP;
		return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return fullHealing(result);																}	}),
	
	ESUNA_EFFECT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyEsuna(result);																}	}),
	
	REVIVE_HALF_HP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	result.damage = (int) -(state.units[result.target].unit.maxHP / 2);
		return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyRevival(result, false);														}	}),
	
	REVIVE_FULL_HP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	result.damage = (int) -state.units[result.target].unit.maxHP;
		return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyRevival(result, true);														}	}),
	
	ADD_REGEN																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.REGEN);											}	}),
	
	ADD_SHELL																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SHELL);											}	}),
	
	ADD_PROTECT																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.PROTECT);										}	}),
	
	ADD_HASTE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.HASTE);											}	}),
	
	ADD_QUICK																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.QUICK);											}	}),

	ADD_REFLECT																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.REFLECT);										}	}),
	
	ADD_ASTRA																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.ASTRA);											}	}),
	
	ADD_AUTO_LIFE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.AUTO_LIFE);										}	}),
	
	ADD_DEFENSE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DEFENSE);										}	}),
	
	ADD_EXPERT_GUARD																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.EXPERT_GUARD);									}	}),
	
	ADD_COVER																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return guaranteedSuccess(result);														}	public String applyEffect(SkillEffectResult result)
	{	return applyCoverEffect(result);														}	}),
	
	FIGHT_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 1, preview, true, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	REGULAR_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 1, preview, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),

	REGULAR_DAMAGE_CAPPED																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 1, preview, false, true, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	DOUBLE_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 2, preview, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	TRIPLE_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 3, preview, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	HALF_FIGHT_DAMAGE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, .5, preview, true, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	HALF_DAMAGE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, .5, preview, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	HALF_DAMAGE_NEVER_MISS																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, .5, preview, false, false, false, false, true);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	DOUBLE_DAMAGE_HALF_HIT																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, .5, 2, preview, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),
	
	HALF_DAMAGE_DOUBLE_HIT																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 2, .5, preview, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyDamage(result);																}	}),

	MP_DAMAGE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericDamageEffect(result, 1, 1, preview, false, false, true, false, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyMPDamage(result);															}	}),
	
	ADD_POISON																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.POISON, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.POISON);										}	}),
	
	ADD_BLIND																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.DARKNESS, false, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DARKNESS);										}	}),
	
	ADD_SILENCE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.SILENCE, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SILENCE);										}	}),
	
	ADD_SLEEP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.SLEEP, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SLEEP);											}	}),
	
	ADD_IMMOBILIZE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.IMMOBILIZE, false, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.IMMOBILIZE);									}	}),
	
	ADD_DISABLE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.DISABLE, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DISABLE);										}	}),
	
	ADD_SLOW																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.SLOW, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SLOW);											}	}),
	
	ADD_STOP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.STOP, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.STOP);											}	}),
	
	ADD_PETRIFY																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.PETRIFY, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.PETRIFY);										}	}),
	
	ADD_FROG																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.FROG, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.FROG);											}	}),

	ADD_ADDLE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.ADDLE, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.ADDLE);											}	}),
	
	ADD_DOOM																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.DOOM, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DOOM);											}	}),
	
	ADD_CONFUSE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.CONFUSE, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.CONFUSE);										}	}),
	
	ADD_CHARM																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.CHARM, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.CHARM);											}	}),
	
	ADD_BERSERK																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.BERSERK, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.BERSERK);										}	}),
	
	ADD_WATK_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.WATK_DOWN, false, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.WATK_DOWN);										}	}),
	
	ADD_WDEF_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.WDEF_DOWN, false, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.WDEF_DOWN);										}	}),
	
	ADD_MPOW_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.MPOW_DOWN, false, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.MPOW_DOWN);										}	}),
	
	ADD_SPEED_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.SPEED_DOWN, true, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SPEED_DOWN);									}	}),
	
	ADD_DEATH																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return genericStatusEffect(result, 1, StatusEffect.DEATH, false, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DEATH);											}	}),
	
	EFF1DEP_ADD_POISON																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.POISON, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.POISON);										}	}),
	
	EFF1DEP_ADD_BLIND																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.DARKNESS, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DARKNESS);										}	}),

	EFF1DEP_ADD_SILENCE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.SILENCE, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SILENCE);										}	}),

	EFF1DEP_ADD_IMMOBILIZE																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.IMMOBILIZE, preview, false);	}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.IMMOBILIZE);									}	}),
	
	EFF1DEP_ADD_DISABLE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.DISABLE, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.DISABLE);										}	}),
	
	EFF1DEP_ADD_SLOW																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.SLOW, preview, false);			}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.SLOW);											}	}),
	
	EFF1DEP_ADD_STOP																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.STOP, preview, false);			}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.STOP);											}	}),
	
	EFF1DEP_ADD_FROG																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.FROG, preview, false);			}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.FROG);											}	}),

	EFF1DEP_ADD_MRES_DOWN																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.MRES_DOWN, preview, true);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.MRES_DOWN);										}	}),
	
	EFF1DEP_ADD_CONFUSE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.CONFUSE, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.CONFUSE);										}	}),
	
	EFF1DEP_ADD_CHARM																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.CHARM, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.CHARM);											}	}),

	EFF1DEP_ADD_BERSERK																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepStatusEffect(result, prev, 1, StatusEffect.BERSERK, preview, false);		}	public String applyEffect(SkillEffectResult result)
	{	return applyStatus(result, StatusEffect.BERSERK);										}	}),
	
	EFF1DEP_DRAIN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult  prev, boolean preview)
	{ 	return eff1DepDrain(result, prev);														}	public String applyEffect(SkillEffectResult result)
	{	return applyHealing(result);															}	});
	
	////////////////////////////////////////
	
	static GameState state;
	public final SkillEffectHandler handler;
	
	SkillEffect(SkillEffectHandler handler)
	{
		this.handler = handler;
	}
	
	public interface SkillEffectHandler
	{
		SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult prev, boolean preview); // determine effect
		String applyEffect(SkillEffectResult result); // apply effect
		// int[] predictEffect(SkillEffectResult result); // predict effect
	}
	
	public static void setGameState(GameState gameState)
	{
		state = gameState;
	}
		
	// Server-side effect handlers
	public static SkillEffectResult genericDamageEffect(SkillEffectResult result,
			double hitFactor, double damageFactor, boolean preview,
			boolean canCrit, boolean capToHP, boolean capToMP, boolean healing, boolean neverMiss)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate;
		if (neverMiss)
			hitRate = 100;
		else
			hitRate = FFTACalc.getATypeHitRate(user, target, skill, hitFactor);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
		{
			// Calc damage
			int dmg = FFTACalc.getDamage(user, target, skill, damageFactor, healing, canCrit, capToHP, capToMP, preview);
			result.critical = FFTACalc.wasCritical;
			
			// Save results
			result.damage = dmg;
			result.success = true;
		}
		else
			result.success = false;
		
		return result;
	}
	
	// Effect resolvers
	public static SkillEffectResult genericStatusEffect(SkillEffectResult result,
			double hitFactor, StatusEffect sEff, boolean aType, boolean preview, boolean neverMiss)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;
		
		// Calculate hit rate
		int hitRate;
		if (neverMiss)
			hitRate = 100;
		else if (aType)
			hitRate = FFTACalc.getATypeHitRate(user, target, skill, hitFactor);
		else
			hitRate = FFTACalc.getSTypeHitRate(user, target, sEff, hitFactor);
		result.hitChance = hitRate;
		
		// Determine whether effect succeeds or fails
		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
			result.success = true;
		else
			result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult guaranteedSuccess(SkillEffectResult result)
	{
		result.hitChance = 100;
		result.success = true;
		return result;
	}
	
	public static SkillEffectResult eff1DepStatusEffect(SkillEffectResult result, SkillEffectResult prev,
			double hitFactor, StatusEffect sEff, boolean preview, boolean neverMiss)
	{
		result.dependent = true;
		ActiveUnit target = state.units[prev.target];
		if (prev.success && target.currHP > 0)
			result = genericStatusEffect(result, hitFactor, sEff, false, preview, neverMiss);
			
		return result;
	}
	
	public static SkillEffectResult eff1DepDrain(SkillEffectResult result, SkillEffectResult prev)
	{
		result.target = result.user;
		if (prev.success)
		{
			result.damage = prev.damage;
			result.success = true;
		}
		return result;
	}
	
	// Client-side effect handlers
	public static String applyDamage(SkillEffectResult result)
	{
		String report = "";
		
		ActiveUnit target = state.units[result.target];
		if (result.success)
		{
			// Treat absorbed damage as healing instead
			if (result.damage < 0)
				return applyHealing(result);
			
			state.applyDamage(result.target, result.damage);
			report = "<em><span style=\"color:gray\">......<strong>";
			if (result.critical)
				report += "</em><span style=\"color:red\">CRITICAL HIT!</span><em> ";  
			
			report += target.unit.name + "</strong> takes </em><strong><span style=\"color:red\">" + 
					result.damage + "</strong><em><span style=\"color:gray\"> damage! (" + result.hitChance + "%)";
			
			// Wake sleeping units
			if (result.damage > 0)
			{
				if (target.status[StatusEffect.SLEEP.ordinal()] > 0)
				{
					target.status[StatusEffect.SLEEP.ordinal()] = 0;
					report += "<br><em><span style=\"color:gray\">.........<strong>" + target.unit.name + "</strong> wakes up!";
				}
				if (target.status[StatusEffect.CHARM.ordinal()] > 0)
				{
					target.status[StatusEffect.CHARM.ordinal()] = 0;
					report += "<br><em><span style=\"color:gray\">.........<strong>" + target.unit.name + "</strong> comes to!";
				}
				if (target.status[StatusEffect.CONFUSE.ordinal()] > 0)
				{
					target.status[StatusEffect.CONFUSE.ordinal()] = 0;
					report += "<br><em><span style=\"color:gray\">.........<strong>" + target.unit.name + "</strong> comes to!";
				}
			}
		}
		
		return report;
	}
	
	private static String applyMPDamage(SkillEffectResult result)
	{
		String report = "";
		
		ActiveUnit target = state.units[result.target];
		if (result.success)
		{
			// Treat absorbed damage as healing instead
			if (result.damage < 0)
				return applyMPHealing(result);
			
			state.applyMPDamage(result.target, result.damage);
			report = "<em><span style=\"color:gray\">......<strong>";
			if (result.critical)
				report += "</em><span style=\"color:red\">CRITICAL HIT!</span><em> ";  
			
			report += target.unit.name + "</strong> takes <strong><span style=\"color:fuchsia\">" + 
					result.damage + "</strong> MP damage! (" + result.hitChance + "%)";
		}
			
		return report;
	}
	
	public static String applyHealing(SkillEffectResult result)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success && target.currHP > 0)
		{
			int healing = Math.abs(result.damage);
			state.applyHealing(result.target, healing);
			report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong> recovers <strong><span style=\"color:lime\">" + healing + "</strong> HP!";
		}
		return report;
	}
	
	public static String applyMPHealing(SkillEffectResult result)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			int healing = Math.abs(result.damage);
			state.applyMPHealing(result.target, healing);
			report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong> recovers <strong><span style=\"color:cyan\">" + healing + "</strong> MP!";
		}
		return report;
	}
	
	public static String applyRevival(SkillEffectResult result, boolean fullLife)
	{
		ActiveUnit target = state.units[result.target];
		String report = "";
		
		if (result.success && target.currHP == 0)
		{
			int healing = (int) target.unit.maxHP;
			if (fullLife)
				report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name + "</strong> rises with full health!";
			else
			{
				healing /= 2;
				report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name + "</strong> rises!";
			}
			state.applyHealing(result.target, healing);
		}
		return report;
	}
	
	public static String applyStatus(SkillEffectResult result, StatusEffect sEff)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (target.status[StatusEffect.ASTRA.ordinal()] > 0 && !sEff.IGNORE_ASTRA)
		{
			target.status[StatusEffect.ASTRA.ordinal()] = 0;
			report += "<em><span style=\"color:gray\">......<strong><span style=\"color:blue\">Astra</span></strong> " +
					  "saved <strong>" + target.unit.name + "</strong> from " + sEff.NAME + "!";
		}
		
		if (result.success)
		{
			state.applyStatus(target, sEff);
			report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					 "</strong>" + sEff.REPORT;
		}
		
		return report;
	}
	
	public static String applyEsuna(SkillEffectResult result)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			boolean healedSomething = false;

			// Store all the status ailments to check for in an array
			StatusEffect[] esuna = new StatusEffect[] { StatusEffect.PETRIFY, StatusEffect.BERSERK, StatusEffect.FROG,
														StatusEffect.POISON, StatusEffect.DARKNESS, StatusEffect.SLEEP,
														StatusEffect.SILENCE, StatusEffect.CONFUSE, StatusEffect.IMMOBILIZE,
														StatusEffect.DISABLE };

			// For any status about to be healed, add notice of its recovery to the report
			for (StatusEffect sEff : esuna)
				if (target.status[sEff.ordinal()] > 0)
				{
					if (healedSomething)
						report += "<br>";
					report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
							"</strong> recovers from " + sEff.NAME.toLowerCase() + "!";
					
					healedSomething = true;
				}
			
			if (!healedSomething)
				report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name + "</strong> is unaffected.";
			
			// Remove the status effects in the game status
			state.applyStatusRecovery(result.target, esuna);
		}
		
		return report;
	}
	
	public static String fullHealing(SkillEffectResult result)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			state.applyHealing(result.target, (int) target.unit.maxHP);
			report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong>'s HP is fully restored!";
		}
		return report;
	}
	
	public static String applyCoverEffect(SkillEffectResult result)
	{
		String report = "";
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			state.applyCover(result.user, result.target);
			report = "<em><span style=\"color:gray\">......<strong>" + user.unit.name +
					"</strong> is <span style=\"color:blue\">covering</span> <strong>"
					+ target.unit.name + "</strong>!";
		}
		return report;
	}

}

