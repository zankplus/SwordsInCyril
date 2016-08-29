package fftadata;

public enum StatusEffect
{
	POISON			("Poison",			1,	"is <em><span style=\"color:maroon\">poisoned</em></span>!"),
	QUICK 			("Quick",			1,	""),
	HASTE 			("Haste",			1,	""),
	SLOW 			("Slow",			1,	""),
	SPEED_DOWN 		("Speed Down",		1,	""),	// TODO: does Speed Down really stack with haste and slow?
	PETRIFY 		("Petrify",			1,	""),
	STOP 			("Stop",			1,	""),
	SLEEP 			("Sleep",			1,	""),
	EXPERT_GUARD 	("Expert Guard",	1,	""),
	HIBERNATE 		("",	1,	""),
	FROG 			("",	1,	""),
	BLIND 			("",	1,	""),
	CONFUSE 		("",	1,	""),
	ASTRA 			("",	1,	""),
	BERSERK 		("",	1,	""),
	WATK_UP 		("",	1,	""),
	WATK_DOWN 		("",	1,	""),
	MPOW_UP 		("",	1,	""),
	MPOW_DOWN 		("",	1,	""),
	BOOST 			("",	1,	""),
	SHELL 			("",	1,	""),
	PROTECT 		("",	1,	""),
	DEFENSE 		("",	1,	""),
	WDEF_UP 		("",	1,	""),
	WDEF_DOWN 		("",	1,	""),
	MRES_UP 		("",	1,	""),
	MRES_DOWN 		("",	1,	"");
	
	public final String NAME, REPORT;
	public final int DEFAULT_DURATION;
	
	StatusEffect(String name, int defaultDuration, String report)
	{
		NAME = name;
		DEFAULT_DURATION = defaultDuration;
		REPORT = report;
	}
}
