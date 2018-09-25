package fftadata;

public enum FFTAReaction {

	 NONE			(" -",				null,	null),
	 ABSORB_MP		("Absorb MP",		"Restores MP when hit by an attack with an MP cost.", "The amount of MP restored is equal to amount spent by the caster. Absorb MP will not activate if the incoming attack misses, inflicts a disabling status effect, or has no MP cost."),
	 AUTO_REGEN		("Auto-Regen",		"Grants Regen when hit by an attack.", "Auto-Regen will not activate if the incoming attack inflicts a disabling status effect."),
	 BLOCK_ARROWS	("Block Arrows",	"Grants total protection against all bow attacks.", "Any ability that uses a Bow or Greatbow to determine power or range will be reduced to 0% accuracy."),
	 BONECRUSHER	("Bonecrusher",		"Lets the user counterattack at 1.5x power when hit by a physical attack.", "Bonecrusher will only activate if the incoming attack hits and deals physical damage."),
	 CATCH			("Catch",			"Catches weapons launched with Throw or Hurl.", "This ability is not currently implemented."),
	 COUNTER		("Counter",			"Lets the user counterattack when targeted by a physical attack.", "The incoming attack is not required to hit for Counter to activate."),
	 DAMAGE_TO_MP	("Damage > MP",		"Converts incoming HP damage to MP damage.", "Damage > MP will only activate if the user has more than 0 MP. MP damage in excess of the user's current MP will be lost rather than converted back into HP damage."),
	 DRAGONHEART	("Dragonheart",		"Grants Auto-Life when hit by an attack.", ""),
	 LAST_BERSERK	("Last Berserk",	"Grants Berserk when in critical condition.", "This ability is not currently implemented."),
	 LAST_HASTE		("Last Haste",		"Grants Haste when in critical condition.", "Last Haste triggers when the user's HP falls to 25% or less after an attack. " + StatusEffect.HASTE.DESC),
	 LAST_QUICKEN	("Last Quicken",	"Grants Quick when in critical condition.", "Last Quicken triggers when the user's HP falls to 25% or less after an attack. " + StatusEffect.QUICK.DESC),
	 REFLEX			("Reflex",			"Grants total evasion against all standard attacks.", "The 'Fight' command will always have 0% accuracy against a unit with Reflex. All other attacks are unaffected."),
	 RETURN_FIRE	("Return Fire",		"Catches standard arrow attacks and throws them back at the attacker.", "When attacked with the 'Fight' command by an opponent with a bow or greatbow, a unit with Return Fire avoids the attack and counterattacks for 1.2-1.6x the damage of the attack would have inflicted."),
	 RETURN_MAGIC	("Return Magic",	"Lets the user counter magic by casting the same spell back at the attacker.", "The incoming spell is not required to hit for Return Magic to activate."),
	 STRIKEBACK		("Strikeback",		"Grants total evasion against standard attacks and lets the user counterattack.", "Strikeback's evasion effect will only activate if the attacker is within the user's weapon range.");
	
	public final String name, shortDesc, longDesc;
	
	FFTAReaction(String name, String shortDesc, String longDesc)
	{
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
	}
	
	public String toString()
	{
		return name;
	}
}
