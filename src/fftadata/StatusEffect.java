package fftadata;

public enum StatusEffect
{
	// Basic debuffs
	POISON			("Poison",			1,	" is <strong><span style=\"color:maroon\">poisoned</strong></span>!"),
	BLIND 			("Blind",			1,	" is <strong><span style=\"color:maroon\">blinded</strong></span>!"),
	SILENCE			("Silence",			3,	" is <strong><span style=\"color:maroon\">silenced</strong></span>!"),
	SLEEP 			("Sleep",			3,	" falls <strong><span style=\"color:maroon\">asleep</strong></span>!"),
	IMMOBILIZE 		("Immobilize",		3,	" is <strong><span style=\"color:maroon\">immobilized</strong></span>!"),
	DISABLE			("Disable",			3,	" is <strong><span style=\"color:maroon\">disabled</strong></span>!"),
	SLOW 			("Slow",			3,	" is <strong><span style=\"color:maroon\">slowed</strong></span>!"),
	STOP 			("Stop",			2,	" <strong><span style=\"color:maroon\">stops moving</strong></span>!"),
	PETRIFY			("Petrify",			1,	" is <strong><span style=\"color:maroon\">petrified</strong></span>!"),
	FROG 			("Frog",			1,	" turns into a <strong><span style=\"color:maroon\">frog</strong></span>!"),
	ADDLE	 		("Addle",			3,	" is <strong><span style=\"color:maroon\">addled</strong></span>!"),
	DOOM	 		("Doom",			3,	" is <strong><span style=\"color:maroon\">doomed</strong></span>!"),
	
	// AI-controlling debuffs
	CONFUSE 		("Confuse",			3,	" is <strong><span style=\"color:maroon\">confused</strong></span>!"),
	CHARM	 		("Charm",			3,	" is <strong><span style=\"color:maroon\">charmed</strong></span>!"),
	BERSERK 		("Berserk",			1,	" is driven <strong><span style=\"color:maroon\">berserk</strong></span>!"),
	
	// Stat-down debuffs
	WATK_DOWN 		("WAtk Down",		1,	"'s <strong><span style=\"color:maroon\">WAtk drops</strong></span>!"),
	WDEF_DOWN 		("WDef Down",		1,	"'s <strong><span style=\"color:maroon\">WDef drops</strong></span>!"),
	MPOW_DOWN 		("MPow Down",		1,	"'s <strong><span style=\"color:maroon\">MPow drops</strong></span>!"),
	MRES_DOWN 		("MRes Down",		1,	"'s <strong><span style=\"color:maroon\">MRes drops</strong></span>!"),
	SPEED_DOWN 		("Speed Down",		1,	"'s <strong><span style=\"color:maroon\">Speed drops</strong></span>!"),
	
	// Death
	DEATH			("Death",			0,	"'s spirit leaves the body!"),
	
	// Buffs
	EXPERT_GUARD 	("Expert Guard",	1,	""),
	HIBERNATE 		("",	1,	""),
	
	
	
	SHELL 			("Shell",			3,	" is shielded against magic!"),
	PROTECT 		("Protect",			3,	" is shielded against weapons!"),
	QUICK 			("Quick",			1,	" is <strong><span style=\"color:cyan\">quickened</strong></span>!"),
	HASTE 			("Haste",			3,	" is <strong><span style=\"color:cyan\">hastened</strong></span>!"),
	
	ASTRA 			("Astra",			1,	" is shielded against status ailments!"),
	
	WATK_UP 		("WAtk UP",			1,	""),
	WDEF_UP 		("WDef UP",			1,	""),
	MPOW_UP 		("MPow UP",			1,	""),
	MRES_UP 		("MRes UP",			1,	""),
	
	BOOST 			("",	1,	""),
	
	DEFENSE 		("",	1,	"");
	
	
	public final String NAME, REPORT;
	public final int DEFAULT_DURATION;
	
	StatusEffect(String name, int defaultDuration, String report)
	{
		NAME = name;
		DEFAULT_DURATION = defaultDuration;
		REPORT = report;
	}
}
