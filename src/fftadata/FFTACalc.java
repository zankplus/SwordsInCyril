package fftadata;

public class FFTACalc
{
	static boolean wasCritical;	// indicates whether the last calculated attack resulted in a critical hit
	
	// Returns an integer representing the first unit's position relative to the second.
	// 1 means au1 is in front of au2, 2 means beside, and 4 means behind.
	public static int getRelativeFacing(ActiveUnit au1, ActiveUnit au2)
	{
		int fD1, lD1, fD2, lD2;		// fD = fDimension, lD = lateralDimension
		
		if (au2.dir == 1)			// NE
		{
			fD1 = au1.x * -1;
			lD1 = au1.y;
			fD2 = au2.x * -1;
			lD2 = au2.y;
		}
		else if (au2.dir == 2)		// NW
		{
			fD1 = au1.y * -1;
			lD1 = au1.x;
			fD2 = au2.y * -1;
			lD2 = au2.x;
		}
		else if (au2.dir == 3)		// SW
		{
			fD1 = au1.x;
			lD1 = au1.y;
			fD2 = au2.x;
			lD2 = au2.y;
		}
		else if (au2.dir == 4)		// SE
		{
			fD1 = au1.y;
			lD1 = au1.x;
			fD2 = au2.y;
			lD2 = au2.x;
		}
		else return -1;
		
		int fDist = fD1 - fD2;				// forward distance
		int lDist = Math.abs(lD1 - lD2);	// lateral distance
		
		if (fDist >= lDist)			// front attack
			return 1;
		else if (lDist >= -fDist)	// side attack
			return 2;
		else						// back attack
			return 4;
	}
	
	public static int getATypeHitRate(ActiveUnit attacker, ActiveUnit defender, FFTASkill skill,
									  GameState state, double hitFactor)
	{
		int hitRate = 0;
		
		// 1. Automatic hit conditions
		if (defender.currHP == 0 ||
			defender.status[StatusEffect.PETRIFY.ordinal()] != 0 ||
			defender.status[StatusEffect.HIBERNATE.ordinal()] != 0 ||
			defender.status[StatusEffect.STOP.ordinal()] != 0 ||
			defender.status[StatusEffect.SLEEP.ordinal()] != 0 ||
			defender.status[StatusEffect.EXPERT_GUARD.ordinal()] != 0)
		{
			hitRate = 100;
		}
		else // Steps 2-9 are skipped in the event of an automatic hit
		{
			// 2. Reaction check
			if (reactionNegates(attacker, defender, skill, state))
				return 0;
			
			// 3. Retrieve defender's Evade stat
			int evade;
			if (defender.status[StatusEffect.FROG.ordinal()] != 0)
				evade = defender.unit.evade;
			else
				evade = Math.min(defender.unit.getTotalEvade(), 100);
			
			// 4. Work out relative facing
			evade /= getRelativeFacing(attacker, defender);
			
			// 5. Status check
			if (defender.status[StatusEffect.DARKNESS.ordinal()] != 0)
				evade -= 20;
			if (defender.status[StatusEffect.CONFUSE.ordinal()] != 0)
				evade -= 10;
			if (attacker.status[StatusEffect.DARKNESS.ordinal()] != 0)
				evade += 50;
			
			// 6. Support check
			if (attacker.unit.support == FFTASupport.CONCENTRATE)
				evade -= 50;
			else if (attacker.unit.support == FFTASupport.TURBO_MP)
				evade -= 15;
			
			// 7. Cap Evade
			evade = Math.max(evade, 5);
			evade = Math.min(evade, 95);
			
			// 8. Work out Hit rate
			hitRate = 100 - evade;
			
			// 9. Apply hit factor
			hitRate *= hitFactor;
			
			// 10. Thief Armlets check
			
			// 11. Cap again
			hitRate = Math.max(hitRate, 0);
			hitRate = Math.min(hitRate, 100);
			
		}
		// 9. Unknown sanity checks?
		// ? ? ? ? ? ? ? ? ? ? ? ? ?
		
		return hitRate;
	}
	
	public static int getSTypeHitRate(ActiveUnit attacker, ActiveUnit defender, FFTASkill sk, 
										GameState state, StatusEffect sEff, double hitFactor)
	{
		int hitRate;
		// 1. Retrieve target's Status Resistance
		int sRes = 50;
		
		// 1.5. Return 0 if attempting to charm
		if (sEff == StatusEffect.CHARM && attacker.team == defender.team)
			hitRate = 0;
		
		// 2. Status check | 3. Equipment check | 4. Immunity check | 4.5. Support Check
		if (statusNegates(defender, sEff) || equipmentNegates(defender, sEff) ||
				supportNegates(defender, sEff) || reactionNegates(attacker, defender, sk, state))
			hitRate = 0;
		else
		{
			// 5. 100% hit conditions
			if (defender.currHP == 0 ||
					defender.status[StatusEffect.PETRIFY.ordinal()] != 0 ||
					defender.status[StatusEffect.HIBERNATE.ordinal()] != 0 ||
					defender.status[StatusEffect.STOP.ordinal()] != 0 ||
					defender.status[StatusEffect.SLEEP.ordinal()] != 0 ||
					defender.status[StatusEffect.EXPERT_GUARD.ordinal()] != 0)
			{
				hitRate = 100;
			}
			else
			{
				// 6. Attacker's Support check
				if (attacker.unit.support == FFTASupport.CONCENTRATE)
					sRes -= 20;
				else if (attacker.unit.support == FFTASupport.TURBO_MP)
					sRes -= 10;
				
				// 7. Work out relative facing
				int rf = FFTACalc.getRelativeFacing(attacker, defender);
				if (rf == 2)
					sRes -= 10;
				else if (rf == 4)
					sRes -= 20;
				
				// 8. Cap SRes
				sRes = Math.max(0, sRes);
				
				// 9. Work out hit rate
				hitRate = 100 - sRes;
				
				// 10. Apply hit factor
				hitRate *= hitFactor;
				
				// 11. Cap again
				hitRate = Math.max(0, hitRate);
				hitRate = Math.min(100, hitRate);
			}
		}
		
		// 10. Astra check (and unknown sanity checks?)
		if (defender.status[StatusEffect.ASTRA.ordinal()] != 0)
			hitRate = 0;
		
		return hitRate;
	}
	
	public static int getDamage(ActiveUnit attacker, ActiveUnit defender, FFTASkill skill,
			double damageFactor, boolean healing, boolean canCrit, boolean capToHP,
			boolean capToMP, boolean leftHand, boolean preview)
	{
		final int PHYSICAL = 1, MAGICAL = 2;
		int dmg = 0;
		int atk = 0;
		
		// 1. Get base Attack power
		if (skill.IS_PHYSICAL)
			atk = (int) attacker.getBaseWAtk();
		else // if (skill.dmgType == 2)
			atk = (int) attacker.getBaseMPow();
		
		// System.out.println(attacker.unit.name + "'s base WAtk: " + atk);
		
		// 2. Attacker's Support check 
		if (attacker.unit.support == FFTASupport.WATK_PLUS && skill.IS_PHYSICAL)
			atk = atk * 307 / 256;
		else if (attacker.unit.support == FFTASupport.MPOW_PLUS && !skill.IS_PHYSICAL)
			atk = atk * 307 / 256;
		else if (attacker.unit.support == FFTASupport.TURBO_MP && skill.MP_COST > 0)
			atk = atk * 332 / 256;
		else if (attacker.unit.support == FFTASupport.DOUBLEHAND && skill == FFTASkill.FIGHT)
			atk = atk * 307 / 256;
		
		// 3. Attacker's status check
		if (attacker.status[StatusEffect.BERSERK.ordinal()] != 0 && skill.IS_PHYSICAL)
			atk = atk * 307 / 256;
		if (attacker.status[StatusEffect.FROG.ordinal()] != 0)
			atk = atk * 25 / 256;
		if (attacker.status[StatusEffect.BOOST.ordinal()] > 0 && skill.IS_PHYSICAL)
			atk = atk * 384 / 256;
		if (attacker.status[StatusEffect.WATK_UP.ordinal()] != 0 && skill.IS_PHYSICAL)
			atk = atk * 281 / 256;
		if (attacker.status[StatusEffect.WATK_DOWN.ordinal()] != 0 && skill.IS_PHYSICAL)
			atk = atk * 230 / 256;
		if (attacker.status[StatusEffect.MPOW_UP.ordinal()] != 0 && !skill.IS_PHYSICAL)
			atk = atk * 281 / 256;
		if (attacker.status[StatusEffect.MPOW_DOWN.ordinal()] != 0 && !skill.IS_PHYSICAL)
			atk = atk * 230 / 256;
		
		
		
		// 4. Attacker's equipment check
		if (attacker.status[StatusEffect.FROG.ordinal()] == 0)
		{
			if (skill.IS_PHYSICAL)
				if (!leftHand)
					atk += attacker.unit.getWAtkEquipBonus();
				else
					atk += attacker.unit.getWAtkEquipBonusLeft();
			else if (!skill.IS_PHYSICAL)
				atk += attacker.unit.getMPowEquipBonus();
		}
		
		// System.out.println("WAtk Equip Bonus: " + attacker.unit.getWAtkEquipBonus());
		// System.out.println("After bonuses: " + atk);
		
		// 5. Atk cap
		atk = Math.min(999, atk);

		// 6. Get base Defense power
		int def;
		if (skill.IS_PHYSICAL)
			def = (int) defender.unit.wDef;
		else
			def = (int) defender.unit.mRes;
		
		// 7. Target's Support check
		if (defender.unit.support == FFTASupport.WDEF_PLUS && skill.IS_PHYSICAL)
			def = def * 358 / 256;
		
		// 8. Target's status check
		if (defender.status[StatusEffect.SHELL.ordinal()] != 0 && !skill.IS_PHYSICAL)
			def = def * 358 / 256;
		
		if (defender.status[StatusEffect.PROTECT.ordinal()] != 0 && skill.IS_PHYSICAL)
			def = def * 358 / 256;
		
		if (defender.status[StatusEffect.PETRIFY.ordinal()] != 0)
			def = def * 460 / 256;
		
		if (defender.status[StatusEffect.FROG.ordinal()] != 0)
			def = def * 25 / 256;
		
		if (defender.status[StatusEffect.DEFENSE.ordinal()] != 0)
			def = def * 358 / 256;
		
		if (defender.status[StatusEffect.MRES_UP.ordinal()] != 0 && !skill.IS_PHYSICAL)
			def = def * 358 / 256;
		
		if (defender.status[StatusEffect.MRES_DOWN.ordinal()] != 0 && !skill.IS_PHYSICAL)
			def = def * 179 / 256;
		
		if (defender.status[StatusEffect.WDEF_UP.ordinal()] != 0 && skill.IS_PHYSICAL)
			def = def * 358 / 256;
		
		if (defender.status[StatusEffect.WDEF_DOWN.ordinal()] != 0 && skill.IS_PHYSICAL)
			def = def * 179 / 256;
		
		// 9. Target's equipment check
		if (defender.status[StatusEffect.FROG.ordinal()] == 0 && !healing)	
		{
			if (skill.IS_PHYSICAL)
				def += defender.unit.getWDefEquipBonus();
			else if (!skill.IS_PHYSICAL)
				def += defender.unit.getMResEquipBonus();
		}
		
		// 10. Defense cap
		def = Math.min(999, def);
		
		// 11. Base damage
		dmg = Math.max(atk - (def / 2), 1);
		
		// 12. Get ability's Power stat
		int power;
		if (skill.POWER == -1)
		{
			if (leftHand)
				power = attacker.unit.getWAtkEquipBonusLeft();
			else
				power = attacker.unit.getWAtkEquipBonus();
		}
		
		else if (skill.POWER == -2)
		{
			power = defender.unit.getWAtkEquipBonus();
		}
		
		else
		{
			power = skill.POWER;
			System.out.println("Power of " + skill.NAME + ": " + power);
		}
		
			
		// 13. Apply Power bonus
		dmg = Math.max(dmg * power / 100, 1);

		// 14. Elemental check
		// (elements do not affect healing)
		if (!healing)	// 
		{
			// a. Get attack element
			Element element;
			if (skill.ELEMENT == Element.AS_WEAPON)
				element = attacker.unit.getWeapon(leftHand).element;
			else if (skill.ELEMENT == Element.ENEMY_WEAP)
				element = defender.unit.getWeapon(false).element;
			else
				element = skill.ELEMENT;
			
			// b. Retrieve target's resistance
			EquipSet equips = defender.unit.equips;
			int resistance = 0;	//	0=placeholder	1=weak	2=norm	3=half	4=null	5=absb
			int elemIndex = element.ordinal();
			
			if (element != Element.NULL || healing)
			{
				for (int i = 0; i < 5; i++)
				{
					for (int j = 0; j < equips.slots[i].effects.length; j++)
					{
						if (equips.slots[i].effects[j] == ItemEffect.elemEffs[elemIndex - 1][3])
							resistance = Math.max(resistance, 1);
						else if (equips.slots[i].effects[j] == ItemEffect.elemEffs[elemIndex - 1][2])
							resistance = Math.max(resistance, 3);
						else if (equips.slots[i].effects[j] == ItemEffect.elemEffs[elemIndex - 1][1])
							resistance = Math.max(resistance, 4);
						else if (equips.slots[i].effects[j] == ItemEffect.elemEffs[elemIndex - 1][0])
							resistance = Math.max(resistance, 5);
						
						// System.out.println("res: " + resistance);
					}
				}
			}
		
			if (resistance == 0)
				resistance = 2;
			
			// c. Element enhancement from equipment
			equips = attacker.unit.equips;
			int enhanced = 0;
			if (element != Element.NULL)
			{
				for (int i = 0; i < 5; i++)
					for (int j = 0; j < equips.slots[i].effects.length; j++)
						if (equips.slots[i].effects[j] == ItemEffect.enhnEffs[elemIndex - 1])
							enhanced++;
							
				// Preserving the enhancement stacking glitch
				if (element == Element.FIRE || element == Element.WIND || element == Element.EARTH)
					enhanced = Math.min(enhanced, 1);
			}
			resistance = Math.max(resistance - enhanced, 1);
			
			// d. Geomancy check
			if (element != Element.NULL && attacker.unit.support == FFTASupport.GEOMANCY)
				resistance = Math.max(resistance - 1, 1);
			
			// System.out.println("resistance: " + resistance);
			
			// e. Mission item check
			// -- Not currently implemented --
			
			// f. Apply damage bonus
			if (resistance == 1)
				dmg = dmg * 3 / 2;
			else if (resistance == 3)
				dmg = dmg / 2;
			else if (resistance == 4)
				dmg = 0;
			else if (resistance == 5)
				dmg = -dmg;
		}
		
		// 15. Random steps
		wasCritical = false;
		if (!preview)
		{
			// 15a. Critical check
			if (canCrit)
			{
				int rand = (int) (100 * Math.random());
				if (rand < 5 || (rand < 25 && attacker.status[StatusEffect.ADVICE.ordinal()] > 0))
				{
					wasCritical = true;
					dmg = dmg * 3 / 2;
				}
			}
			
			// 15a. Damage variance
			int variance = dmg / 10;
			dmg += (int) (2 * variance * Math.random()) - variance;			
		}
		
		// 15. Expert Guard check
		if (defender.status[StatusEffect.EXPERT_GUARD.ordinal()] != 0)
			dmg = 0;
		
		// 16. Weapon effects (basically just HEAL_HP since there is presently no way to inflict zombie)
		FFTAEquip weapon = attacker.unit.getWeapon(leftHand);
		for (int i = 0; i < weapon.effects.length; i++)
			if (skill == FFTASkill.FIGHT && weapon.effects[i] == ItemEffect.HEAL_HP)
				dmg = -dmg;
		
		// 17. Damage factor
		dmg = (int) (dmg * damageFactor);
		
		// 18. Cap damage
		dmg = Math.max(-999, dmg);
		dmg = Math.min(999, dmg);
		
		// 18b. Cap to target's HP, if applicable
		if (capToHP)
			dmg = Math.min(dmg, defender.currHP);
		
		// 18c. Cap to target's MP, if applicable
		else if (capToMP)
			dmg = Math.min(dmg, defender.currMP);
		
		// 19. If healing, damage is negative
		if (healing)
			dmg = -dmg;
		
		return dmg;
	}
	
	
	// TODO: Make this do something
	public static boolean reactionNegates(ActiveUnit attacker, ActiveUnit defender, FFTASkill sk,
											GameState state)
	{
		if (state.reactionApplies(attacker, defender, sk, false))
		{
			switch (defender.unit.reaction)
			{
				case REFLEX:
					if (!state.reacting)
						return true;
					return false;
					
				case STRIKEBACK:
					if (!state.reacting)
						return true;
					return false;
					
				case BLOCK_ARROWS:
					if (!state.reacting)
						return true;
					return false;
					
				case RETURN_FIRE:
					if (!state.reacting)
						return true;
					return false;
					
				default:
					return false;
			}	
		}
		
		return false;
	}
	
	public static boolean statusNegates(ActiveUnit defender, StatusEffect sEff)
	{
		// I
		if (defender.status[sEff.ordinal()] > 0)
			return true;
		
		// TODO: STOP prevents SLOW
		// TODO: does it also prevent HASTE?
		
		return false;
	}
	
	public static boolean equipmentNegates(ActiveUnit def, StatusEffect sEff)
	{
		boolean result = false;
		switch (sEff)
		{
			case FROG:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_FROG, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case STOP:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_STOP, ItemEffect.RIBBON, ItemEffect.CACHUSHA});
				break;
				
			case SLOW:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_SLOW, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case CHARM:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_CHARM, ItemEffect.RIBBON, ItemEffect.CACHUSHA});
				break;
				
			case IMMOBILIZE:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_IMMOBILIZE, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case DISABLE:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_DISABLE, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case BERSERK:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_BERSERK, ItemEffect.RIBBON, ItemEffect.CACHUSHA});
				break;
				
			case DARKNESS:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_BLIND, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case CONFUSE:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_CONFUSION, ItemEffect.MINDU_GEM, ItemEffect.RIBBON});
				break;
				
			case DOOM:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_DOOM, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case SLEEP:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_SLEEP, ItemEffect.RIBBON, ItemEffect.CACHUSHA});
				break;
				
			case PETRIFY:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_PETRIFY, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.CACHUSHA});
				break;
				
			case DEATH:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_KO, ItemEffect.RIBBON, ItemEffect.CACHUSHA});
				break;
				
			case SILENCE:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_SILENCE, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case POISON:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.NULL_POISON, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE});
				break;
				
			case ADDLE:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.RIBBON});
				break;
				
			case MISC_EFFECT:
				result = checkForItemEffects(def, new ItemEffect[] {ItemEffect.RIBBON});
				break;
		}
		
		return result;
	}
	
	private static boolean checkForItemEffects(ActiveUnit defender, ItemEffect[] effs)
	{
		for (int i = 0; i < 5; i++)
		{
			if (defender.unit.equips.slots[i] != FFTAEquip.NONE)
				for (int j = 0; j < defender.unit.equips.slots[i].effects.length; j++)
					for (int k = 0; k < effs.length; k++)
						if (effs[k] == defender.unit.equips.slots[i].effects[j])
						{
							System.out.println("Blocked by " + effs[k]); 
							return true;
						}
		}
				
		return false;
	}
	
	public static boolean supportNegates(ActiveUnit defender, StatusEffect sEff)
	{
		if (defender.unit.support == FFTASupport.IMMUNITY &&
			(sEff == StatusEffect.FROG 		||
			sEff == StatusEffect.DARKNESS	||
			sEff == StatusEffect.CONFUSE	||
			sEff == StatusEffect.SLEEP		||	
			sEff == StatusEffect.PETRIFY	||
			sEff == StatusEffect.SILENCE	||
			sEff == StatusEffect.POISON)	)
		{
			return true;
		}
		
		return false;
	}
}
