package fftadata;

public enum StatusEffect
{
	// Basic debuffs
	POISON			("Poison",			1,	false,	" is <strong><span style=\"color:maroon\">poisoned</strong></span>!", 						"Poison causes a unit to take between 5.5 and 11.5% of their max HP as damage every turn. The condition lasts indefinitely."),
	DARKNESS 		("Darkness",		1,	false,	"'s eyes go <strong><span style=\"color:maroon\">dark</strong></span>!",					"Darkness decreases a unit's Evade by 20 and, when they attack, increases their target's Evade by 50. The condition lasts indefinitely."),
	SILENCE			("Silence",			3,	false,	" is <strong><span style=\"color:maroon\">silenced</strong></span>!", 						"Silence prevents a unit from casting magic or using certain abilities involving voice or speech. The condition abates naturally after 3 turns."),
	SLEEP 			("Sleep",			3,	false,	" falls <strong><span style=\"color:maroon\">asleep</strong></span>!", 						"Sleep prevents a unit from taking turns and causes most abilities to hit them automatically. The condition abates when the victim takes damage from an attack, or naturally after 3."),
	IMMOBILIZE 		("Immobilize",		3,	false,	" is <strong><span style=\"color:maroon\">immobilized</strong></span>!", 					"Immobilize prevents a unit from moving. The condition abates naturally after 3 turns."),
	DISABLE			("Disable",			3,	false,	" is <strong><span style=\"color:maroon\">disabled</strong></span>!", 						"Disable prevents a unit from taking actions on their turn and stops certain Reaction abilities from activating. The condition abates naturally after 3 turns."),
	SLOW 			("Slow",			3,	false,	" is <strong><span style=\"color:maroon\">slowed</strong></span>!", 						"Slow halves a unit's Speed, causing them to take their less frequently. The condition abates naturally after 3 turns."),
	STOP 			("Stop",			2,	false,	" <strong><span style=\"color:maroon\">stops moving</strong></span>!", 						"Stop prevents a unit from taking turns. The condition abates naturally after 2 turns."),
	PETRIFY			("Petrify",			1,	false,	" is <strong><span style=\"color:maroon\">petrified</strong></span>!", 						"Petrify prevents a unit from taking turns. The condition lasts indefinitely."),
	ADDLE	 		("Addle",			3,	false,	" is <strong><span style=\"color:maroon\">addled</strong></span>!", 						"Addle prevents a unit from using abilities, including Reaction abilities, although they may still use 'Fight'. The condition abates naturally after 3 turns."),
	DOOM	 		("Doom",			3,	false,	" is <strong><span style=\"color:maroon\">doomed</strong></span>!", 						"Doom starts a timer for a unit that reaches 0 after 3 turns. When the timer runs out, the unit instantly dies."),
	
	// AI-controlling debuffs
	CONFUSE 		("Confuse",			3,	false,	" is <strong><span style=\"color:maroon\">confused</strong></span>!", 						"Confuse causes a unit to fight randomly, automatically taking their own turns and attacking friend and foe alike. The condition abates when the victim is damaged by an attack, or naturally after 3 turns."),
	CHARM	 		("Charm",			3,	false,	" is <strong><span style=\"color:maroon\">charmed</strong></span>!", 						"Charm causes a unit to fight for the enemy's side, automatically taking their own turns and attacking their former teammates. The condition abates when the victim is damaged by an attack, or naturally after 3 turns."),
	BERSERK 		("Berserk",			1,	false,	" is driven <strong><span style=\"color:maroon\">berserk</strong></span>!", 				"Berserk causes a unit to lose control, automatically attacking the closest enemy with 'Fight' command. It also increases their base Weapon ATK by about 20%. The condition lasts indefinitely."),
	
	// Stat-down debuffs
	WATK_DOWN 		("WAtk-",			1,	true,	"'s <strong><span style=\"color:maroon\">WAtk drops</strong></span>!", 						"ATK Down cuts a unit's Weapon ATK by about 10%, reducing the damage they inflict with physical attacks. The condition lasts indefinitely."),
	WDEF_DOWN 		("WDef-",			1,	true,	"'s <strong><span style=\"color:maroon\">WDef drops</strong></span>!", 						"DEF Down cuts a unit's Weapon DEF by about 30%, increasing the damage they take from physical attacks. The condition lasts indefinitely"),
	MPOW_DOWN 		("MPow-",			1,	true,	"'s <strong><span style=\"color:maroon\">MPow drops</strong></span>!", 						"POW Down cuts a unit's Magic POW by about 10%, reducing the damage they inflict with magical attacks. The condition lasts indefinitely."),
	MRES_DOWN 		("MRes-",			1,	true,	"'s <strong><span style=\"color:maroon\">MRes drops</strong></span>!", 						"RES Down cuts a unit's Magic RES by about 30%, increasing the damage they take from magical attacks. The condition lasts indefinitely."),
	SPEED_DOWN 		("Speed-",			1,	true,	"'s <strong><span style=\"color:maroon\">Speed drops</strong></span>!", 					"Speed Down cuts a unit's Speed in half, causing them to take their turn less frequently. The condition lasts indefinitely."),
	
	// Death
	DEATH			("Death",			0,	false,	"'s</em> <strong><span style=\"color:black\">spirit leaves the body</span></strong!", ""),
	
	// Buffs
	SHELL 			("Shell",			3,	true,	" is <strong><span style=\"color:aqua\">shelled</strong></span> against magic!", 			"Shell boosts a unit's Magic RES by about 40%, reducing the damage they take from magical attacks. The condition fades after 3 turns."),
	PROTECT 		("Protect",			3,	true,	" is <strong><span style=\"color:aqua\">protected</strong></span> against weapons!", 		"Protect boosts a unit's Weapon DEF by about 40%, reducing the damage they take from physical attacks. The condition fades after 3 turns."),
	REGEN	 		("Regen",			1,	true,	" is <strong><span style=\"color:aqua\">regenerating health</strong></span>!", 				"Regen automatically restores between 5.5 and 11.5% of a unit's max HP at the start of their turn. The condition lasts indefinitely."),
	QUICK 			("Quick",			1,	true,	" is <strong><span style=\"color:aqua\">quickened</strong></span>!", 						"Quick immediately grants a unit an extra turn as if they were next in line to act."),
	HASTE 			("Haste",			3,	true,	" is <strong><span style=\"color:aqua\">hastened</strong></span>!", 						"Haste doubles a unit's speed, causing them to take their turn more frequently. The condition fades after 3 turns."),
	REFLECT 		("Reflect",			3,	true,	" gains a <strong><span style=\"color:aqua\">reflective barrier</strong></span>!", 			"Certain spells will bounce off a Reflected user without effect and target the caster instead. Most White, Black, Red, and Time Magic abilities can be reflected; most others cannot. The condition fades after 3 turns."),
	ASTRA 			("Astra",			1,	true,	" is protected by <strong><span style=\"color:aqua\">Astra</strong></span>!",				"Astra grants a unit total one-time protection against status-inflicting abilities. The condition lasts indefinitely or until it blocks an ability once."),
	BOOST 			("Boost",			1,	true,	"'s Weapon Attack is <strong><span style=\"color:aqua\">boosted</strong></span>!", 			"Boost increases a unit's base Weapon ATK and Magic POW by 50% for one attack. The condition fades after the unit's next action, whether it was an attack or not."),
	DEFENSE 		("Defense",			1,	true,	" is <strong><span style=\"color:aqua\">defending</strong></span>!", 						"Defense increases a unit's base Weapon DEF and Magic RES for one turn, reducing the damage they take."),
	EXPERT_GUARD 	("Expert Guard",	1,	true,	" is <strong><span style=\"color:aqua\">immune to damage</strong></span>!", 				"Expert Guard makes a unit invulnerable to damage for one turn. While Expert Guard is in effect, the unit cannot dodge attacks or resist status effects (unless immune)."),
	ADVICE		 	("Advice",			1,	true,	"'s <strong><span style=\"color:aqua\">Critical Hit rate improves</strong></span>!", 		"Advice increases a unit's chance of landing a Critical Hit from 5% to 25%. The condition lasts indefinitely."),
	
	WATK_UP 		("WAtk+",			1,	true,	"'s <strong><span style=\"color:cyan\">WAtk rises</strong></span>!", 						"ATK Up boosts a unit's Weapon ATK by about 10%, increasing the damage they inflict with physical attacks. The condition lasts indefinitely."),
	WDEF_UP 		("WDef+",			1,	true,	"'s <strong><span style=\"color:cyan\">WDef rises</strong></span>!", 						"DEF Up boosts a unit's Weapon DEF by about 40%, reducing the damage they take from physical attacks. The condition lasts indefinitely."),
	MPOW_UP 		("MPow+",			1,	true,	"'s <strong><span style=\"color:cyan\">MPow rises</strong></span>!", 						"POW Up boosts a unit's Magic POW by about 10%, increasing the damage they take from magical attacks. The condition lasts indefinitely."),
	MRES_UP 		("MRes+",			1,	true,	"'s <strong><span style=\"color:cyan\">MRes rises</strong></span>!", 						"RES Up boosts a unit's Magic RES by about 40%, reducing the damage they take from magical attacks. The condition lasts indefinitely."),
	
	
	HIBERNATE 		("Hibernate",		1,	true,	"", ""),
	
	
	
	
	
	MISC_EFFECT		("Misc. effects",	0,	false,	"", ""),
	
	// Status effects that persist after death
	FROG 			("Frog",			1,	false,	" turns into a <strong><span style=\"color:maroon\">frog</strong></span>!", 			"Frog turns a unit into a frog, cutting their Weapon ATK and DEF by about 90%, negating any stats gained from from equipment, and preventing the use of all abilities except 'Fight' and 'Item'. The condition lasts indefinitely, even if the unit dies."),
	AUTO_LIFE		("Auto-Life",		1,	true,	" is granted <strong><span style=\"color:aqua\">Auto-Life</strong></span>!", 			"Auto-Life causes a unit whose HP drops to 0 to instantly revive with 25% of their max HP. The condition lasts indefinitely.");
	
	public final String NAME, REPORT;
	public final int DEFAULT_DURATION;
	public final boolean IGNORE_ASTRA;
	public final String DESC;
	
	StatusEffect(String name, int defaultDuration, boolean ignoreAstra, String report, String desc)
	{
		NAME = name;
		DEFAULT_DURATION = defaultDuration;
		IGNORE_ASTRA = ignoreAstra;
		REPORT = report;
		DESC = desc;
	}
}