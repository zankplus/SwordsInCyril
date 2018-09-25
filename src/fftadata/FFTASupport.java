package fftadata;

public enum FFTASupport
{
	 NONE			(" -",				null, null),
	 CONCENTRATE	("Concentrate",		"Increases the accuracy of all attacks.", "Concentrate adds 50% to the base accuracy of attack effects and 20% to that of status effects."),
	 DOUBLE_SWORD	("Double Sword",	"Allows the user to equip two weapons at once.", "Whichever weapon is highest in the equipment list is considered the right-hand weapon. Abilities based on weapon power gain no benefit from the left-hand weapon."),
	 DOUBLEHAND		("Doublehand",		"Allows the user to equip a one-handed weapon in both hands, increasing its power.", "Doublehand increases the user's base Weapon ATK by about 20% when using the Fight command. Other abilities gain no benefit. The user may still equip a shield, but will not gain the benefit of Doublehand."),
	 GEOMANCY		("Geomancy",		"Boosts the power of all elemental damage.", "Elemental resistance exists in 5 stages: Absorb > Null > Resist > Normal > Weak. Geomancy moves the target's resistance one stage to the right for the duration of the user's attack."),
	 HALF_MP		("Half MP",			"Reduces the MP cost of all abilities by 50%.", null),
	 IMMUNITY		("Immunity",		"Grants immunity to certain status effects.", "Status effects warded by Immunity are Darkness, Confuse, Frog, Petrify, Sleep, and Slow."),
	 LEARNING		("Learning",		"Allows user to learn Blue Magic when hit by a monster's abilities.", "Learning is not supported in Swords in Cyril."),
	 MPOW_PLUS		("Magic Pow+",		"Increases the user's Magic POW.", "Base Magic POW increases by about 20%. This bonus is applied during damage calculations and is not reflected in the unit's stat view."),
	 MAINTENANCE	("Maintenance",		"Prevents the user's equipment from being broken, stolen, or dropped.", null),
	 MONKEYGRIP		("Monkeygrip",		"Allows the user to equip a two-handed weapon with one hand.", "This allows the user to equip a two-handed sword and a shield at the same time."),
	 SHIELDBEARER	("Shieldbearer",	"Allows the user to equip a shield, regardless of job.", null),
	 TURBO_MP		("Turbo MP",		"Increases the user's Magic POW and accuracy, but doubles all MP costs.", "Base Magic POW increases by about 30%; base accuracy increases by 15% for attack effects and 10% for status effects. The increase to accuracy applies to all abilities, regardless of MP cost."),
	 WATK_PLUS		("Weapon Atk+",		"Increases the user's Weapon ATK.", "Base Weapon ATK increases by about 20%. This bonus is applied during damage calculations and is not reflected in the unit's stat view."),
	 WDEF_PLUS		("Weapon Def+",		"Increases the user's Weapon ATK.", "Base Weapon DEF increases by about 40%. This bonus is applied during damage calculations and is not reflected in the unit's stat view.");
	
	public final String name, shortDesc, longDesc;
	
	FFTASupport(String name, String shortDesc, String longDesc)
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
