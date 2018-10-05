package fftadata;

import java.io.Serializable;

public enum SkillEffect implements Serializable
{
	HEALING_1X																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 1, preview, false, false, false, true, false, true);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHealing(result, state);																}	}),
	
	FULLY_HEAL_HP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	result.damage = (int) -state.units[result.target].unit.maxHP;
		return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return fullHealing(result, state, false);																}	}),
	
	FULLY_HEAL_MP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	result.damage = (int) -state.units[result.target].unit.maxMP;
		return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return fullHealing(result, state, true);																}	}),
	
	ESUNA_EFFECT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyEsuna(result, state);																}	}),
	
	DISPEL_EFFECT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccessIfNotPetrified(result, state);											}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDispel(result, state);																}	}),
	
	REVIVE_HALF_HP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	result.damage = (int) -(state.units[result.target].unit.maxHP / 2);
		return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyRevival(result, state, false);														}	}),
	
	REVIVE_FULL_HP																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	result.damage = (int) -state.units[result.target].unit.maxHP;
		return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyRevival(result, state, true);														}	}),
	
	ADD_REGEN																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.REGEN);											}	}),
	
	ADD_SHELL																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SHELL);											}	}),
	
	ADD_PROTECT																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.PROTECT);										}	}),
	
	ADD_HASTE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.HASTE);											}	}),
	
	ADD_QUICK																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.QUICK);											}	}),

	ADD_REFLECT																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.REFLECT);										}	}),
	
	ADD_ASTRA																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.ASTRA);											}	}),
	
	ADD_AUTO_LIFE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.AUTO_LIFE);										}	}),
	
	ADD_DEFENSE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DEFENSE);										}	}),
	
	ADD_EXPERT_GUARD																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.EXPERT_GUARD);									}	}),
	
	ADD_BOOST																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.BOOST);											}	}),
	
	ADD_ADVICE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.ADVICE);										}	}),
	
	ADD_WATK_UP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.WATK_UP);										}	}),
	
	ADD_WDEF_UP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.WDEF_UP);										}	}),
	
	ADD_MPOW_UP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.MPOW_UP);										}	}),
	
	ADD_MRES_UP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.MRES_UP);										}	}),
	
	COVER_EFFECT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyCoverEffect(result, state);														}	}),
	
	FIGHT_DAMAGE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	double bc = 0; if (bonecrusher) bc = 0.5;
		return genericDamageEffect(result, state, 1, 1+bc, preview, true, false, false, false, false,	false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	FIGHT_DAMAGE_LEFT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	double bc = 0; if (bonecrusher) bc = 0.5;
		return genericDamageEffect(result, state, 1, 1+bc, preview, true, false, false, false, true, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	REGULAR_DAMAGE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 1, preview, false, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),

	REGULAR_DAMAGE_CAPPED																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 1, preview, false, true, false, false, false,	false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	RETURN_FIRE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	double rfBonus = 1.2 + 0.4 * Math.random();
		return genericDamageEffect(result, state, 1, 1+rfBonus, preview, true, false, false, false, false,	true);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	DOUBLE_DAMAGE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 2, preview, false, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	TRIPLE_DAMAGE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 3, preview, false, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	HALF_FIGHT_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, .5, preview, true, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	HALF_DAMAGE																							(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, .5, preview, false, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	FULL_DAMAGE_NEVER_MISS																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 1, preview, false, false, false, false, false, true);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	HALF_DAMAGE_NEVER_MISS																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, .5, preview, false, false, false, false, false, true);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	DOUBLE_DAMAGE_HALF_HIT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, .5, 2, preview, false, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),
	
	HALF_DAMAGE_DOUBLE_HIT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 2, .5, preview, false, false, false, false, false, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																		}	}),

	MP_DAMAGE																							(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericDamageEffect(result, state, 1, 1, preview, false, false, false, false, false,	false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyMPDamage(result, state);																	}	}),

	QUARTER_HP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return fractionalDamage(result, state, 4, false, preview);										}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	HALVE_HP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return fractionalDamage(result, state, 2, true, preview);										}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	HP_LOST_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return healthLostDamage(result, state, preview);												}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	USER_HP_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return userHPDamage(result, state, preview);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	FIXED_DAMAGE_30																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return fixedDamage(result, state, 30, preview);												}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	SUBDUE_EFFECT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return subdueEffect(result, state, preview);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	RECOIL_DAMAGE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return recoilDamage(result, first, false);												}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyRecoilDamage(result, state);														}	}),
	
	FIERY_RECOIL																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return recoilDamage(result, first, true);												}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyRecoilDamage(result, state);														}	}),
	
	DROP_WEAPON																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 0, 0.5);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 2);															}	}),
	
	BREAK_WEAPON																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 0, 0.5);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 1);															}	}),
	
	BREAK_ARMOR																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 3, 0.5);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 1);															}	}),
	
	STEAL_WEAPON																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 0, 1);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 0);															}	}),
	
	STEAL_SHIELD																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 1, 1);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 0);															}	}),
	
	STEAL_HELM																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 2, 1);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 0);															}	}),
	
	STEAL_ARMOR																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 3, 1);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 0);															}	}),
	
	STEAL_ACCESSORY																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return targetEquipment(result, state, 4, 1);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return stealEquipment(result, state, 0);															}	}),
	
	ADD_POISON																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.POISON, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.POISON);										}	}),
	
	ADD_BLIND																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.DARKNESS, false, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DARKNESS);										}	}),
	
	ADD_SILENCE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.SILENCE, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SILENCE);										}	}),
	
	ADD_SLEEP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.SLEEP, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SLEEP);											}	}),
	
	ADD_IMMOBILIZE																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.IMMOBILIZE, false, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.IMMOBILIZE);									}	}),
	
	ADD_DISABLE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.DISABLE, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DISABLE);										}	}),
	
	ADD_SLOW																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.SLOW, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SLOW);											}	}),
	
	ADD_STOP																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.STOP, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.STOP);											}	}),
	
	ADD_PETRIFY																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.PETRIFY, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.PETRIFY);										}	}),
	
	ADD_FROG																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.FROG, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.FROG);											}	}),

	ADD_ADDLE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.ADDLE, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.ADDLE);											}	}),
	
	ADD_DOOM																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.DOOM, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DOOM);											}	}),
	
	ADD_CONFUSE																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.CONFUSE, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.CONFUSE);										}	}),
	
	ADD_CHARM																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.CHARM, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.CHARM);											}	}),
	
	ADD_BERSERK																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.BERSERK, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.BERSERK);										}	}),
	
	ADD_WATK_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.WATK_DOWN, false, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.WATK_DOWN);										}	}),
	
	ADD_WDEF_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.WDEF_DOWN, false, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.WDEF_DOWN);										}	}),
	
	ADD_MPOW_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.MPOW_DOWN, false, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.MPOW_DOWN);										}	}),
	
	ADD_SPEED_DOWN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.SPEED_DOWN, true, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SPEED_DOWN);									}	}),
	
	ADD_DEATH																						(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.DEATH, false, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DEATH);											}	}),
	
	EFF1DEP_ADD_POISON																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.POISON, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.POISON);										}	}),
	
	EFF1DEP_ADD_BLIND																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.DARKNESS, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DARKNESS);										}	}),

	EFF1DEP_ADD_SILENCE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.SILENCE, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SILENCE);										}	}),

	EFF1DEP_ADD_IMMOBILIZE																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.IMMOBILIZE, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.IMMOBILIZE);									}	}),
	
	EFF1DEP_ADD_DISABLE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.DISABLE, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DISABLE);										}	}),
	
	EFF1DEP_ADD_SLOW																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.SLOW, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.SLOW);											}	}),
	
	EFF1DEP_ADD_STOP																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.STOP, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.STOP);											}	}),
	
	EFF1DEP_ADD_FROG																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.FROG, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.FROG);											}	}),

	EFF1DEP_ADD_MRES_DOWN																			(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.MRES_DOWN, preview, true);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.MRES_DOWN);										}	}),
	
	EFF1DEP_ADD_CONFUSE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.CONFUSE, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.CONFUSE);										}	}),
	
	EFF1DEP_ADD_CHARM																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.CHARM, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.CHARM);											}	}),

	EFF1DEP_ADD_BERSERK																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepStatusEffect(result, state, first, 1, StatusEffect.BERSERK, preview, false);		}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.BERSERK);										}	}),
	
	DRAIN																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepDrain(result, state, previous);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHealing(result, state);															}	}),
	
	DELAY																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return basicATypeAccuracy(result, state);											}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDelay(result, state);																}	}),
	
	EFF1DEP_DELAY																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return eff1DepGuaranteedSuccess(result, state, first);											}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDelay(result, state);																}	}),
	
	SELFDESTRUCT																					(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return selfDestruct(result, state);															}	}),
	
	FIXED_HEALING_25																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 25);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHealing(result, state);															}	}),
	
	FIXED_HEALING_50																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 50);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHealing(result, state);															}	}),
	
	FIXED_HEALING_150																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 150);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHealing(result, state);															}	}),
	
	FIXED_MP_HEALING_80																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 80);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyMPHealing(result, state);															}	}),
	
	HEAL_SILENCE																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.SILENCE});							}	}),
	
	HEAL_FROG																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.FROG});							}	}),
	
	HEAL_PETRIFY																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.PETRIFY});							}	}),
	
	HEAL_POISON																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.POISON});							}	}),
	
	HEAL_DARKNESS																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.DARKNESS});							}	}),
	
	HEAL_BIND																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.IMMOBILIZE, StatusEffect.DISABLE});							}	}),
	
	CUREALL_EFFECT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);													}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return healStatusEffects(result, state, new StatusEffect[] {StatusEffect.PETRIFY, StatusEffect.FROG,
			StatusEffect.POISON, StatusEffect.DARKNESS, StatusEffect.SILENCE, StatusEffect.CONFUSE});							}	}),
	
	GOBLIN_PUNCH_EFFECT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return goblinPunchEffect(result, state, preview);												}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);																}	}),
	
	MUTILATE_EFFECT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return mutilateEffect(result, state, preview);															}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyDamage(result, state);															}	}),
	
	HASTEBREAK_EFFECT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return hastebreakEffect(result, state, preview);												}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHastebreak(result, state);															}	}),
	
	MATRA_MAGIC_EFFECT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return genericStatusEffect(result, state, 1, StatusEffect.MISC_EFFECT, true, preview, false);	}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return switchHPandMP(result, state);															}	}),
	
	WHITE_WIND_EFFECT																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return userHPHealing(result, state);															}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyHealing(result, state);															}	}),
	
	LV3_WDEF_DOWN																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return levelDivisibleTarget(result, state, 3, null);															}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.WDEF_DOWN);															}	}),
	
	LV3_MRES_DOWN																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return levelDivisibleTarget(result, state, 3, null);															}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.MRES_DOWN);															}	}),
	
	LV5_DEATH																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return levelDivisibleTarget(result, state, 5, StatusEffect.DEATH);															}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyStatus(result, state, StatusEffect.DEATH);															}	}),
	
	BAD_BREATH																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return badBreathEffect(result, state);											}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return "";															}	}),
	
	ACID																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return acidEffect(result, state, preview);											}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyAcidEffect(result, state);															}	}),
	
	AIM_VITALS																				(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return aimVitalsEffect(result, state, preview);											}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return applyAimVitalsEffect(result, state);															}	}),
	
	MORPH																							(new SkillEffectHandler() { public SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult previous, SkillEffectResult first, GameState state, boolean preview, boolean bonecrusher)
	{ 	return guaranteedSuccess(result, 0);															}	public String applyEffect(SkillEffectResult result, GameState state)
	{	return morph(result, result.skill.V_RADIUS, state);															}	});
	
	////////////////////////////////////////
	
	public final SkillEffectHandler handler;
	
	SkillEffect(SkillEffectHandler handler)
	{
		this.handler = handler;
	}
	
	public interface SkillEffectHandler
	{
		SkillEffectResult resolveEffect(SkillEffectResult result, SkillEffectResult first,
										SkillEffectResult prev, GameState state, boolean preview,
										boolean bonecrusher); // determine effect
		String applyEffect(SkillEffectResult result, GameState state); // apply effect
		// int[] predictEffect(SkillEffectResult result); // predict effect
	}
		
	// Server-side effect handlers
	public static SkillEffectResult genericDamageEffect(SkillEffectResult result, GameState state,
			double hitFactor, double damageFactor, boolean preview, boolean canCrit, boolean capToHP,
			boolean capToMP, boolean healing, boolean leftHand, boolean neverMiss)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate;
		if (neverMiss && !FFTACalc.reactionNegates(user, target, skill, state))
			hitRate = 100;
		else
			hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, hitFactor);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
		{
			// Calc damage
			int dmg = FFTACalc.getDamage(user, target, skill, damageFactor, healing, canCrit, capToHP,
					capToMP, leftHand, preview);
			result.critical = FFTACalc.wasCritical;
			
			// Save results
			result.damage = dmg;
			result.success = true;
		}
		else
			result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult basicATypeAccuracy(SkillEffectResult result, GameState state)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate;
		hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, 1);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate)
		{
			result.damage = 0;
			result.success = true;
		}
		else
			result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult subdueEffect(SkillEffectResult result, GameState state, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;
		
		int hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, 1);
		result.hitChance = hitRate;
		
		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
		{
			// Calc damage
			int dmg = user.unit.getLevel() / 10;
			if (!preview)
				dmg += (int) (11 * Math.random());

			dmg = Math.max(dmg, 0);
			
			// Save results
			result.damage = dmg;
			result.success = true;
		}
		else
			result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult fractionalDamage(SkillEffectResult result, GameState state, int fraction,
			 boolean roundUp, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;

		int hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, 1);
		result.hitChance = hitRate;

		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
		{
			// 	Calc damage
			int dmg = target.currHP;
			if (roundUp)
				dmg += (fraction - 1);

			dmg = Math.max(dmg / fraction, 1);

			// Save results
			result.damage = dmg;
			result.success = true;
		}
		else
			result.success = false;

		return result;
	}

	public static SkillEffectResult fixedDamage(SkillEffectResult result, GameState state, int amt, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;

		int hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, 1);
		result.hitChance = hitRate;

		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
		{
			result.damage = amt;
			result.success = true;
		}
		else
			result.success = false;

		return result;
	}

	// A-Type: Deals damage equal to the difference between the user's max and current HP
	public static SkillEffectResult healthLostDamage(SkillEffectResult result, GameState state, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;

		int hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, 1);
		result.hitChance = hitRate;

		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
		{
			result.damage = (int) user.unit.maxHP - user.currHP;
			result.success = true;
		}
		else
			result.success = false;

		return result;
	}
	
	// A-Type: Deals damage equal to the user's current HP
	public static SkillEffectResult userHPDamage(SkillEffectResult result, GameState state, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;

		int hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, 1);
		result.hitChance = hitRate;

		int rand = (int) (100 * Math.random());
		if (rand < hitRate)
		{
			result.damage = (int) user.currHP;
			result.success = true;
		}
		else
			result.success = false;

		return result;
	}
	
	// A-Type: Drops target's weapon
	public static SkillEffectResult targetEquipment(SkillEffectResult result, GameState state, int equipType,
													double hitFactor)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		FFTASkill skill = result.skill;

		EquipSet equips = target.unit.equips;
		int targetSlot = -1;
		
		switch(equipType)
		{
			case 0:	// weapon
				for (int i = 0; i < 5; i++)
					if (equips.slots[i] != FFTAEquip.NONE && equips.slots[i].isWeapon())
					{
						targetSlot = i;
						break;
					}
				break;
				
			case 1:	// shield
				for (int i = 0; i < 5; i++)
					if (equips.slots[i] != FFTAEquip.NONE && equips.slots[i].isShield())
					{
						targetSlot = i;
						break;
					}
				break;
				
			case 2:	// headwear
				for (int i = 0; i < 5; i++)
					if (equips.slots[i] != FFTAEquip.NONE && equips.slots[i].isHeadwear())
					{
						targetSlot = i;
						break;
					}
				break;
				
			case 3:	// armor
				for (int i = 0; i < 5; i++)
					if (equips.slots[i] != FFTAEquip.NONE && equips.slots[i].isArmor())
					{
						targetSlot = i;
						break;
					}
				break;
				
			case 4:	// accessory
				for (int i = 0; i < 5; i++)
					if (equips.slots[i] != FFTAEquip.NONE && equips.slots[i].isStealableAccessory())
					{
						targetSlot = i;
						break;
					}
				break;
		}
		
		if (targetSlot != -1 && target.unit.support != FFTASupport.MAINTENANCE)
		{
			int hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, hitFactor);
			result.hitChance = hitRate;

			int rand = (int) (100 * Math.random());
			if (rand < hitRate)
			{
				result.slot = targetSlot;
				result.success = true;
			}
			else
				result.success = false;
		}

		return result;
	}
	
	// Effect resolvers
	public static SkillEffectResult genericStatusEffect(SkillEffectResult result, GameState state,
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
			hitRate = FFTACalc.getATypeHitRate(user, target, skill, state, hitFactor);
		else
			hitRate = FFTACalc.getSTypeHitRate(user, target, skill, state, sEff, hitFactor);
		result.hitChance = hitRate;
		
		// Determine whether effect succeeds or fails
		int rand = (int) (100 * Math.random());
		if (rand < hitRate || preview)
			result.success = true;
		else
			result.success = false;
		
		return result;
	}
	
	// 100% Hit: Heals the target by an amount equal to the caster's current HP
	public static SkillEffectResult userHPHealing(SkillEffectResult result, GameState state)
	{
		result.hitChance = 100;
		result.damage = state.units[result.user].currHP;
		result.success = true;
		
		
		return result;
	}
	
	public static SkillEffectResult guaranteedSuccess(SkillEffectResult result, int amt)
	{
		result.hitChance = 100;
		result.success = true;
		result.damage = amt;
		return result;
	}
	
	public static SkillEffectResult guaranteedSuccessIfNotPetrified(SkillEffectResult result, GameState state)
	{
		if (state.units[result.target].status[StatusEffect.PETRIFY.ordinal()] == 0)
		{
			result.hitChance = 100;
			result.success = true;
		}
		
		return result;
	}
	
	public static SkillEffectResult recoilDamage(SkillEffectResult result, SkillEffectResult prev,
												 boolean takeDamageOnMiss)
	{
		if (prev.success)
		{
			int dmg = Math.abs(prev.damage / 4);
			
			result.hitChance = 100;
			result.damage = dmg;
			result.success = true;
		}
		
		else if (takeDamageOnMiss)
		{
			int dmg = Math.max(Math.abs(prev.damage / 4), 1);
			
			result.hitChance = 100;
			result.damage = dmg;
			result.success = false;
		}
		
		return result;
	}
	
	public static SkillEffectResult levelDivisibleTarget(SkillEffectResult result, GameState state,
															int divisor, StatusEffect sEff)
	{
		ActiveUnit target = state.units[result.target];

		// Check for status immunity
		if (sEff != null &&
			(FFTACalc.equipmentNegates(target, sEff) ||
			 FFTACalc.statusNegates   (target, sEff) ||
			 FFTACalc.supportNegates  (target.unit, sEff) ))
		{
			result.hitChance = 0;
			result.success = false;
		}
		else
		{
			if (target.unit.getLevel() % divisor == 0)
			{
				result.hitChance = 100;
				result.success = true;
			}
			else
			{
				result.hitChance = 0;
				result.success = false;
			}
		}
		
		return result;
	}
	
	// Conditional: 100% hit if eff1 succeeded, 0 otherwise
	public static SkillEffectResult eff1DepGuaranteedSuccess(SkillEffectResult result, GameState state, SkillEffectResult prev)
	{
		result.dependent = true;
		ActiveUnit target = state.units[prev.target];
		if (prev.success && target.currHP > 0)
		{
			result.hitChance = 100;
			result.success = true;
		}
			
		return result;
	}
	
	public static SkillEffectResult eff1DepStatusEffect(SkillEffectResult result, GameState state, SkillEffectResult prev,
			double hitFactor, StatusEffect sEff, boolean preview, boolean neverMiss)
	{
		result.dependent = true;
		ActiveUnit target = state.units[prev.target];
		if (prev.success && target.currHP > 0)
			result = genericStatusEffect(result, state, hitFactor, sEff, false, preview, neverMiss);
			
		return result;
	}
	
	public static SkillEffectResult eff1DepDrain(SkillEffectResult result, GameState state, SkillEffectResult prev)
	{
		result.target = result.user;
		if (prev.success)
		{
			result.damage = prev.damage;
			result.success = true;
		}
		return result;
	}
	
	public static SkillEffectResult hastebreakEffect(SkillEffectResult result, GameState state, boolean preview)
	{
		ActiveUnit target = state.units[result.target];
		if (preview || target.status[StatusEffect.HASTE.ordinal()] == 0)
			return genericStatusEffect(result, state, 1, StatusEffect.SLOW, false, preview, false);

		else
			return genericStatusEffect(result, state, 1, StatusEffect.STOP, false, preview, false);
	}
	
	public static SkillEffectResult goblinPunchEffect(SkillEffectResult result, GameState state, boolean preview)
	{
		SkillEffectResult rResult = genericDamageEffect(result, state, 1, 1, preview, false, false, false,
				false, false, false);
		
		if (!preview)
			rResult.damage =  rResult.damage * (128 + (int) (Math.random() * 257)) / 256;
		
		return rResult;
	}
	
	public static SkillEffectResult mutilateEffect(SkillEffectResult result, GameState state, boolean preview)
	{
		SkillEffectResult rResult = genericDamageEffect(result, state, 1, 1, preview, false, false, false,
				false, false, false);
		
		int base = state.units[result.target].currHP / 4;
		int rand = (int) (Math.random() * 3);
		if (!preview)
			rand = 1;
		rResult.damage =  base + base * rand;
		
		return rResult;
	}
	
	public static SkillEffectResult badBreathEffect(SkillEffectResult result, GameState state)
	{
		ActiveUnit user = state.units[result.target];
		ActiveUnit target = state.units[result.target];
		
		int hit = 0;
		
		hit = Math.max(hit, FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.POISON, 1));
		hit = Math.max(hit, FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DARKNESS, 1));
		hit = Math.max(hit, FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.SILENCE, 1));
		hit = Math.max(hit, FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.SLEEP, 1));
		hit = Math.max(hit, FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.SLOW, 1));
		hit = Math.max(hit, FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.FROG, 1));
		result.hitChance = hit;
		result.success = false;
		
		return result;
	}
	
	public static SkillEffectResult acidEffect(SkillEffectResult result, GameState state, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		
		int hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.MISC_EFFECT, 1);
		
		// Preview shows cumulative % of any of the four ailments landing
		if (preview)
		{
			int applicableAilments = 4;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.STOP, 1) == 0)
				applicableAilments--;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.SLOW, 1) == 0)
				applicableAilments--;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.IMMOBILIZE, 1) == 0)
				applicableAilments--;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DISABLE, 1) == 0)
				applicableAilments--;
			
			hit = hit * applicableAilments / 4;
		}
		
		// Backend uses the base accuracy, rolling first to see which ailment procs, then again to see if
		// it actually lands
		else
		{
			result.damage = 1 + (int) (Math.random() * 4);
			
			if (result.damage == 1)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.STOP, 1);
			else if (result.damage == 2)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.SLOW, 1);
			else if (result.damage == 3)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.IMMOBILIZE, 1);
			else if (result.damage == 4)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DISABLE, 1);
			
			int rand = (int) (100 * Math.random());
			if (rand < hit)
				result.success = true;
			else
				result.success = false;
		}
		result.hitChance = hit;
		
		return result;
	}
	
	public static SkillEffectResult aimVitalsEffect(SkillEffectResult result, GameState state, boolean preview)
	{
		ActiveUnit user = state.units[result.user];
		ActiveUnit target = state.units[result.target];
		
		int hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.MISC_EFFECT, 1);
		
		// Preview shows cumulative % of any of the four ailments landing
		if (preview)
		{
			int applicableAilments = 4;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DARKNESS, 1) == 0)
				applicableAilments--;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.STOP, 1) == 0)
				applicableAilments--;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.IMMOBILIZE, 1) == 0)
				applicableAilments--;
			if (FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DISABLE, 1) == 0)
				applicableAilments--;
			
			hit = hit * applicableAilments / 4;
		}
		
		// Backend uses the base accuracy, rolling first to see which ailment procs, then again to see if
		// it actually lands
		else
		{
			result.damage = 1 + (int) (Math.random() * 101);
			
			if (result.damage <= 11)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.STOP, 1);
			else if (result.damage <= 36)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DISABLE, 1);
			else if (result.damage <= 71)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.DARKNESS, 1);
			else if (result.damage <= 96)
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.IMMOBILIZE, 1);
			else
				hit = FFTACalc.getSTypeHitRate(user, target, result.skill, state, StatusEffect.MISC_EFFECT, 1);
			
			int rand = (int) (100 * Math.random());
			if (rand < hit)
				result.success = true;
			else
				result.success = false;
		}
		result.hitChance = hit;
		
		return result;
	}
	
	
	////////////////////// Client-side effect handlers //////////////////////////
	public static String applyDamage(SkillEffectResult result, GameState state)
	{
		String report = "";
		
		ActiveUnit target = state.units[result.target];
		
		if (target.unit.reaction == FFTAReaction.DAMAGE_TO_MP && target.currMP > 0
			&& !result.skill.IGNORE_REACTION && result.damage > 0)
		{
			return applyMPDamage(result, state);
		}
		
		if (result.success)
		{
			// Treat absorbed damage as healing instead
			if (result.damage < 0)
				return applyHealing(result, state);
			
			state.applyDamage(result.target, result.damage);
			report = "<em><span style=\"color:gray\">......</em>";
			if (result.critical)
				report += "<strong><span style=\"color:red\">CRITICAL HIT!!</span></strong> ";  
			
			report += "<em><span style=\"color:gray\"><strong>" + target.unit.name +
					  "</strong> takes </em><strong><span style=\"color:red\">" + 
					  result.damage + "</strong><span style=\"color:gray\"><em> damage!</em> (" + 
					  result.hitChance + "%) [" + target.currHP + "/" + (int) target.unit.maxHP +
					  " HP]";
			
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
		else if (result.skill == FFTASkill.DOUBLE_SWORD || result.skill == FFTASkill.DOUBLESHOT)
			report += "<em><span style=\"color:gray\">......The attack misses <strong>" +
					target.unit.name + "</strong>! (" + result.hitChance + "%)";
		return report;
	}
	
	public static String applyMPDamage(SkillEffectResult result, GameState state)
	{
		String report = "";
		
		ActiveUnit target = state.units[result.target];
		if (result.success)
		{
			// Treat absorbed damage as healing instead
			if (result.damage < 0)
				return applyMPHealing(result, state);
			
			state.applyMPDamage(result.target, result.damage);
			report = "<em><span style=\"color:gray\">......</em>";
			if (result.critical)
				report += "<strong><span style=\"color:red\">CRITICAL HIT!!</span></strong> ";  
			
			report += "<em><span style=\"color:gray\"><strong>" + target.unit.name +
					  "</strong> takes </em><strong><span style=\"color:fuchsia\">" + 
					  result.damage + "</strong><span style=\"color:gray\"><em> MP damage!</em> (" + 
					  result.hitChance + "%) [" + target.currMP + "/" + (int) target.unit.maxMP +
					  " MP]";
			
		}
		else if (result.skill == FFTASkill.DOUBLE_SWORD || result.skill == FFTASkill.DOUBLESHOT)
			report += "<em><span style=\"color:gray\">......The attack misses <strong>" +
					target.unit.name + "</strong>! (" + result.hitChance + "%)";
		
		return report;
	}
	
	public static String applyRecoilDamage(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit user = state.units[result.user];
		
		// Here the deciding factor is not whether the skill was 'successful' or not (the recoil effect
		// carries its parent's success value), but whether any damage was actually incurred.
		if (result.damage != 0)
		{
			state.applyDamage(result.user, result.damage);
			report = "<em><span style=\"color:gray\">......<strong>";  
			
			report += user.unit.name + "</strong> takes </em><strong><span style=\"color:red\">" + 
					result.damage + "</strong><em><span style=\"color:gray\"> damage from recoil!";
			
			if (user.currHP == 0)
				report += "<br><em><span style=\"color:gray\">......<strong>" + user.unit.name +
				" falls</strong>!";
		}
		
		return report;
	}
	
	public static String applyHealing(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success && target.currHP > 0)
		{
			int healing = Math.abs(result.damage);
			state.applyHealing(result.target, healing);
			report = "<em><span style=\"color:gray\">......</em>";
			if (result.critical)
				report += "<strong><span style=\"color:red\">CRITICAL HIT!!</span></strong> ";  
			
			report += "<em><span style=\"color:gray\"><strong>" + target.unit.name +
					  "</strong> recovers </em><strong><span style=\"color:lime\">" + 
					  Math.abs(result.damage) + "</strong><span style=\"color:gray\"><em> HP!</em> (" + 
					  result.hitChance + "%) [" + target.currHP + "/" + (int) target.unit.maxHP +
					  "HP]";
		}
		return report;
	}
	
	public static String applyMPHealing(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			int healing = Math.abs(result.damage);
			state.applyMPHealing(result.target, healing);
			report = "<em><span style=\"color:gray\">......</em>";
			if (result.critical)
				report += "<strong><span style=\"color:red\">CRITICAL HIT!!</span></strong> ";  
			
			report = "<em><span style=\"color:gray\"><strong>" + target.unit.name +
					  "</strong> recovers </em><strong><span style=\"color:aqua\">" + 
					  Math.abs(result.damage) + "</strong><span style=\"color:gray\"><em> MP!</em> (" + 
					  result.hitChance + "%) [" + target.currMP + "/" + (int) target.unit.maxMP +
					  "MP]";
		}
		return report;
	}
	
	public static String applyRevival(SkillEffectResult result, GameState state, boolean fullLife)
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
	
	public static String applyStatus(SkillEffectResult result, GameState state, StatusEffect sEff)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (target.status[StatusEffect.ASTRA.ordinal()] > 0 && !sEff.IGNORE_ASTRA)
			result.astra = true;
		
		if (result.success)
		{
			state.applyStatus(target, sEff);
			report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					 "</strong>" + sEff.REPORT;
		}
		
		return report;
	}
	
	public static String applyDelay(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			state.applyDelay(target.id);
			report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					 "</strong> is <strong><span style=\"color:maroon\">delayed</span></strong>!";
		}
		
		return report;
	}
	
	public static String healStatusEffects(SkillEffectResult result, GameState state, StatusEffect[] sEffs)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			boolean healedSomething = false;

			// For any status about to be healed, add notice of its recovery to the report
			for (StatusEffect sEff : sEffs)
				if (target.status[sEff.ordinal()] > 0)
				{
					if (healedSomething)
						report += "<br>";
					report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
							"</strong> recovers from " + sEff.NAME.toLowerCase() + "!";
					
					healedSomething = true;
				}
			
			// Remove the status effects in the game status
			state.applyStatusRecovery(result.target, sEffs);
		}
		
		return report;
	}
	
	public static String applyEsuna(SkillEffectResult result, GameState state)
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
//			if (!healedSomething)
//				report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name + "</strong> is unaffected.";
			
			// Remove the status effects in the game status
			state.applyStatusRecovery(result.target, esuna);
		}
		
		return report;
	}
	
	public static String applyDispel(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			boolean healedSomething = false;

			// Store all the status ailments to check for in an array
			StatusEffect[] dispel = new StatusEffect[] { StatusEffect.AUTO_LIFE, StatusEffect.REGEN, StatusEffect.ASTRA,
														 StatusEffect.REFLECT, StatusEffect.DOOM, StatusEffect.HASTE,
														 StatusEffect.SLOW, StatusEffect.STOP, StatusEffect.SHELL,
														 StatusEffect.PROTECT, StatusEffect.CHARM, StatusEffect.ADDLE };

			// For any status about to be healed, add notice of its recovery to the report
			for (StatusEffect sEff : dispel)
				if (target.status[sEff.ordinal()] > 0)
				{
					if (healedSomething)
						report += "<br>";
					report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
							"</strong>'s " + sEff.NAME + " is dispelled!";
					
					healedSomething = true;
				}			
			if (!healedSomething)
				report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name + "</strong> is unaffected.";
			
			// Remove the status effects in the game status
			state.applyStatusRecovery(result.target, dispel);
		}
		
		return report;
	}
	
	public static String stealEquipment(SkillEffectResult result, GameState state, int mode)
	{
		// mode: 0 = steal, 1 = destroy, 2 = disarm
		
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			FFTAEquip itemStolen = target.unit.equips.slots[result.slot];
			state.unequipUnit(result.target, result.slot);
			report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong>'s " + itemStolen + " was ";
			
			if (mode == 0)
				report += "stolen!";
			else if (mode == 1)
				report += "destroyed!";
			else if (mode == 2)
				report += "dropped!";
		}
		return report;
	}
	
	public static String fullHealing(SkillEffectResult result, GameState state, boolean mpHealing)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			if (mpHealing)
			{
				state.applyMPHealing(result.target, (int) target.unit.maxMP);
				report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
						"</strong>'s MP is fully restored!";	
			}
			else
			{
				state.applyHealing(result.target, (int) target.unit.maxHP);
				report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
						"</strong>'s HP is fully restored!";
			}
		}
		return report;
	}
	
	public static String applyCoverEffect(SkillEffectResult result, GameState state)
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
	
	public static String selfDestruct(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit user = state.units[result.user];
		
		if (result.success && user.currHP > 0)
		{
			state.applyDamage(result.user, (int) user.unit.maxHP);
			report += "<em><span style=\"color:gray\">......<strong>" + user.unit.name +
					"</strong> self-destructs!";
			report += "</br><em><span style=\"color:gray\">......<strong>" + user.unit.name +
					" falls</strong>!";
		}
		return report;
	}

	public static String switchHPandMP(SkillEffectResult result, GameState state)
	{
		String report = "";
		ActiveUnit target = state.units[result.target];
		
		if (result.success)
		{
			state.switchHPandMP(result.target);
			report += "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong>'s HP and MP were switched!";
		}
		return report;
	}
	
	public static String applyHastebreak(SkillEffectResult result, GameState state)
	{
		ActiveUnit target = state.units[result.target];
		
		if (target.status[StatusEffect.HASTE.ordinal()] > 0)
			return (applyStatus(result, state, StatusEffect.STOP));
		else
			return (applyStatus(result, state, StatusEffect.SLOW));
	}
	
	public static String morph(SkillEffectResult result, int morph, GameState state)
	{
		ActiveUnit target = state.units[result.target];
		target.morph = Morph.values()[morph];
		
		String report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong> gained the power of a <strong>" + target.morph.name + "</strong>!";
		
		return report;
	}
	
	public static String applyAcidEffect(SkillEffectResult result, GameState state)
	{
		ActiveUnit target = state.units[result.target];
		
		if (result.damage == 1)
			return applyStatus(result, state, StatusEffect.STOP);
		else if (result.damage == 2)
			return applyStatus(result, state, StatusEffect.SPEED_DOWN);
		else if (result.damage == 3)
			return applyStatus(result, state, StatusEffect.IMMOBILIZE);
		else if (result.damage == 4)
			return applyStatus(result, state, StatusEffect.DISABLE);
		else
			return("hey what the fuck? what thef uck? what thenf uckc ?");
	}
	
	public static String applyAimVitalsEffect(SkillEffectResult result, GameState state)
	{
		ActiveUnit target = state.units[result.target];
		
		if (result.damage <= 11)
			return applyStatus(result, state, StatusEffect.STOP);
		else if (result.damage <= 36)
			return applyStatus(result, state, StatusEffect.DISABLE);
		else if (result.damage <= 71)
			return applyStatus(result, state, StatusEffect.DARKNESS);
		else if (result.damage <= 96)
			return applyStatus(result, state, StatusEffect.IMMOBILIZE);
		else
			return(applySweetSpot(result, state));
		
		
	}
	
	public static String applySweetSpot(SkillEffectResult result, GameState state)
	{
		ActiveUnit target = state.units[result.target];
		
		String report = "<em><span style=\"color:gray\">......<strong>" + target.unit.name +
					"</strong> falls to 1 HP!";
		target.currHP = 1;
		return report;
	}
	
	public static void noEffect()
	{
		
	}
}

