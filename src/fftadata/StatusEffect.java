package fftadata;

public enum StatusEffect
{
	// Basic debuffs
	POISON			("Poison",			1,	false,	" is <strong><span style=\"color:maroon\">poisoned</strong></span>!"),
	DARKNESS 		("Darkness",		1,	false,	" 's eyes go <strong><span style=\"color:maroon\">dark</strong></span>!"),
	SILENCE			("Silence",			3,	false,	" is <strong><span style=\"color:maroon\">silenced</strong></span>!"),
	SLEEP 			("Sleep",			3,	false,	" falls <strong><span style=\"color:maroon\">asleep</strong></span>!"),
	IMMOBILIZE 		("Immobilize",		3,	false,	" is <strong><span style=\"color:maroon\">immobilized</strong></span>!"),
	DISABLE			("Disable",			3,	false,	" is <strong><span style=\"color:maroon\">disabled</strong></span>!"),
	SLOW 			("Slow",			3,	false,	" is <strong><span style=\"color:maroon\">slowed</strong></span>!"),
	STOP 			("Stop",			2,	false,	" <strong><span style=\"color:maroon\">stops moving</strong></span>!"),
	PETRIFY			("Petrify",			1,	false,	" is <strong><span style=\"color:maroon\">petrified</strong></span>!"),
	ADDLE	 		("Addle",			3,	false,	" is <strong><span style=\"color:maroon\">addled</strong></span>!"),
	DOOM	 		("Doom",			3,	false,	" is <strong><span style=\"color:maroon\">doomed</strong></span>!"),
	
	// AI-controlling debuffs
	CONFUSE 		("Confuse",			3,	false,	" is <strong><span style=\"color:maroon\">confused</strong></span>!"),
	CHARM	 		("Charm",			3,	false,	" is <strong><span style=\"color:maroon\">charmed</strong></span>!"),
	BERSERK 		("Berserk",			1,	false,	" is driven <strong><span style=\"color:maroon\">berserk</strong></span>!"),
	
	// Stat-down debuffs
	WATK_DOWN 		("WAtk Down",		1,	true,	"'s <strong><span style=\"color:maroon\">WAtk drops</strong></span>!"),
	WDEF_DOWN 		("WDef Down",		1,	true,	"'s <strong><span style=\"color:maroon\">WDef drops</strong></span>!"),
	MPOW_DOWN 		("MPow Down",		1,	true,	"'s <strong><span style=\"color:maroon\">MPow drops</strong></span>!"),
	MRES_DOWN 		("MRes Down",		1,	true,	"'s <strong><span style=\"color:maroon\">MRes drops</strong></span>!"),
	SPEED_DOWN 		("Speed Down",		1,	true,	"'s <strong><span style=\"color:maroon\">Speed drops</strong></span>!"),
	
	// Death
	DEATH			("Death",			0,	false,	"'s</em> <strong><span style=\"color:black\">spirit leaves the body</span></strong!"),
	
	// Buffs
	SHELL 			("Shell",			3,	true,	" is <strong><span style=\"color:aqua\">shelled</strong></span> against magic!"),
	PROTECT 		("Protect",			3,	true,	" is <strong><span style=\"color:aqua\">protected</strong></span> against weapons!"),
	REGEN	 		("Regen",			1,	true,	" is <strong><span style=\"color:aqua\">regenerating health</strong></span>!"),
	QUICK 			("Quick",			1,	true,	" is <strong><span style=\"color:aqua\">quickened</strong></span>!"),
	HASTE 			("Haste",			3,	true,	" is <strong><span style=\"color:aqua\">hastened</strong></span>!"),
	REFLECT 		("Reflect",			1,	true,	" gains a <strong><span style=\"color:aqua\">reflective barrier</strong></span>!"),
	ASTRA 			("Astra",			1,	true,	" is granted <strong><span style=\"color:aqua\">Astra</strong></span>!"),
	BOOST 			("Boost",			1,	true,	"'s Weapon Attack is <strong><span style=\"color:aqua\">boosted</strong></span>!"),
	DEFENSE 		("Defense",			1,	true,	" is <strong><span style=\"color:aqua\">defending</strong></span>!"),
	EXPERT_GUARD 	("Expert Guard",	1,	true,	" is <strong><span style=\"color:aqua\">immune to damage</strong></span>!"),
	ADVICE		 	("Advice",			1,	true,	"'s <strong><span style=\"color:aqua\">Critical Hit rate improves</strong></span>!"),
	
	
	
	
	HIBERNATE 		("Hibernate",			1,	true,	""),
	
	
	
	
	
	
	
	WATK_UP 		("WAtk UP",			1,	true,	""),
	WDEF_UP 		("WDef UP",			1,	true,	""),
	MPOW_UP 		("MPow UP",			1,	true,	""),
	MRES_UP 		("MRes UP",			1,	true,	""),
	
	
	
	// Status effects that persist after death
	FROG 			("Frog",			1,	false,	" turns into a <strong><span style=\"color:maroon\">frog</strong></span>!"),
	AUTO_LIFE		("Auto-Life",		1,	true,	" is granted an <strong><span style=\"color:aqua\">Auto-Life</strong></span>!");
	
	
	public final String NAME, REPORT;
	public final int DEFAULT_DURATION;
	public final boolean IGNORE_ASTRA;
	
	StatusEffect(String name, int defaultDuration, boolean ignoreAstra, String report)
	{
		NAME = name;
		DEFAULT_DURATION = defaultDuration;
		IGNORE_ASTRA = ignoreAstra;
		REPORT = report;
	}
}
