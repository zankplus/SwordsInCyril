package fftadata;
import java.io.Serializable;

public enum FFTACombo implements Serializable
{
	NONE			(" -",			0,	0,	0),
	COMBAT_COMBO	("Combat Combo",	10,	1,	100),
	DRAGON_COMBO 	("Dragon Combo",	10,	2,	100),
	DEFEND_COMBO 	("Defend Combo",	15,	1,	70),
	SWORD_COMBO 	("Sword Combo",	10,	1,	100),
	MONK_COMBO 		("Monk Combo",	12,	2,	90),
	PRAY_COMBO 		("Pray Combo",	8,	4,	60),
	SACRED_COMBO 	("Sacred Combo",	12,	1,	100),
	KNIGHT_COMBO	("Knight Combo",	10,	1,	100),
	FIGHT_COMBO 	("Fight Combo",	15,	1,	70),
	THIEF_COMBO 	("Thief Combo",	5,	2,	100),
	NINJA_COMBO 	("Ninja Combo",	5,	2,	100),
	WHITE_COMBO 	("White Combo",	5,	4,	60),
	BLACK_COMBO 	("Black Combo",	5,	4,	60),
	SPELL_COMBO 	("Spell Combo",	10,	4,	40),
	BLUE_COMBO 		("Blue Combo",	10,	4,	40),
	BOW_COMBO 		("Bow Combo",	5,	6,	40),
	HUNT_COMBO 		("Hunt Combo",	8,	4,	50),
	ANIMAL_COMBO 	("Animal Combo",	12,	1,	100),
	CHARGE_COMBO 	("Charge Combo",	15,	1,	70),
	GUN_COMBO 		("Gun Combo",	5,	5,	40),
	JUGGLE_COMBO 	("Juggle Combo",	10,	3,	70),
	GADGET_COMBO 	("Gadget Combo",	12,	6,	30),
	TIME_COMBO 		("Time Combo",	5,	4,	60),
	GOLD_COMBO 		("Gold Combo",	10,	2,	60),
	BEAST_COMBO 	("Beast Combo",	12,	1,	100),
	MORPH_COMBO 	("Morph Combo",	10,	2,	100),
	WISE_COMBO 		("Wise Combo",	10,	2,	60),
	LUNGE_COMBO 	("Lunge Combo",	10,	1,	100),
	SPIRIT_COMBO 	("Spirit Combo",	10,	3,	60),
	RED_COMBO 		("Red Combo",	10,	2,	70),
	SUMMON_COMBO 	("Summon Combo",	10,	4,	40),
	KILLER_COMBO 	("Killer Combo",	10,	2,	100),
	SNIPER_COMBO 	("Sniper Combo",	8,	4,	50);
	
	final String name;
	final int power;
	final int range;
	final int hit;
	
	FFTACombo(String name, int power, int range, int hit)
	{
		this.name = name;
		this.power = power;
		this.range = range;
		this.hit = hit;
	}
	
	public String toString()
	{
		return name;
	}
}
