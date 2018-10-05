package fftadata;
import java.io.Serializable;

public enum FFTAJob implements Serializable
{						//Name			HP			MP			WAtk			WDef		MPow		MRes		Speed
	WARRIOR			(	"Warrior",		46,	9.2,    10,  2.1,    92,  9.2,    84,  8.4,    62,  6.2,    68,  6.8,    95,  0.9, 4, 2, 50, 10, FFTACommand.BATTLE_TECH_B,		new EquipType[] {EquipType.SWORD, EquipType.BROADSWORD, EquipType.SHIELD, EquipType.HELM, EquipType.HAT, EquipType.ARMOR, EquipType.CLOTHING}),
	DRAGOON			(	"Dragoon",		40,	8.2,     8,  1.2,    96,  9.6,    84,  8.4,    56,  5.6,    60,  6.4,   104,  1.1, 3, 3, 40, 10, FFTACommand.DRAGON_TECH,  		new EquipType[] {EquipType.SWORD, EquipType.SPEAR, EquipType.HELM, EquipType.ARMOR}),
	DEFENDER 		(	"Defender",		42,	8.4,     6,  1.1,    88,  8.8,    94,  9.4,    65,  6.5,    72,  7.2,    92,  0.9, 3, 2, 35, 10, FFTACommand.DEFEND,			new EquipType[] {EquipType.KNIGHTSWORD, EquipType.BROADSWORD, EquipType.HELM, EquipType.ARMOR}),
	GLADIATOR		(	"Gladiator",	40,	8.2,    18,  2.8,    94,  9.4,    88,  8.8,    59,  5.9,    64,  6.4,    97,  1.1, 4, 2, 45, 10, FFTACommand.SPELLBLADE_TECH,	new EquipType[] {EquipType.BLADE, EquipType.HAT, EquipType.CLOTHING}),
	WHITE_MONK		(	"White Monk",	32,	6.4,    12,  1.1,    80,  8.1,    76,  7.6,    83,  8.3,    68,  6.8,   108,  1.4, 4, 3, 60, 20, FFTACommand.MONK_TECH,			new EquipType[] {EquipType.KNUCKLES, EquipType.CLOTHING}),
	BISHOP 			(	"Bishop",		30,	6.1,    28,  4.6,    72,  7.2,    68,  6.8,    88,  8.8,    76,  7.6,    88,  0.9, 3, 2, 30, 10, FFTACommand.PRAYER, 			new EquipType[] {EquipType.STAFF, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	TEMPLAR			(	"Templar",		36,	7.2,    20,  3.2,    92,  8.2,    97,  9.7,    84,  8.4,    72,  7.2,    86,  0.8, 3, 2, 40, 10, FFTACommand.SACRED_TECH, 		new EquipType[] {EquipType.KNIGHTSWORD, EquipType.SPEAR, EquipType.HELM, EquipType.HAT, EquipType.ARMOR, EquipType.CLOTHING, EquipType.ROBE}),
	
	SOLDIER			(	"Soldier",		40,	8.4,    14,  1.1,    88,  8.8,    88,  8.8,    60,  6.0,    72,  7.2,   100,  1.1, 4, 2, 50, 10, FFTACommand.BATTLE_TECH_H, 	new EquipType[] {EquipType.SWORD, EquipType.GREATSWORD, EquipType.SHIELD, EquipType.HELM, EquipType.HAT, EquipType.ARMOR, EquipType.CLOTHING}),
	PALADIN			(	"Paladin",		38,	7.6,    22,  2.2,    80,  8.1,    92,  9.2,    72,  7.2,    88,  8.8,    98,  0.8, 3, 2, 35, 10, FFTACommand.CHIVALRY, 			new EquipType[] {EquipType.KNIGHTSWORD, EquipType.GREATSWORD, EquipType.SHIELD, EquipType.HELM, EquipType.ARMOR, EquipType.ROBE}),
	FIGHTER			(	"Fighter",		36,	7.2,    12,  1.1,    92,  9.2,    80,  8.1,    56,  5.6,    68,  6.8,   104,  1.1, 4, 2, 45, 10, FFTACommand.FIGHTER_TECH, 		new EquipType[] {EquipType.BLADE, EquipType.HAT, EquipType.CLOTHING}),
	THIEF_H			(	"Thief",		33,	6.6,    15,  1.1,    76,  7.6,    76,  7.6,    76,  7.6,    64,  6.4,   110,  1.8, 4, 3, 65, 10, FFTACommand.STEAL_H, 			new EquipType[] {EquipType.KNIFE, EquipType.HAT, EquipType.CLOTHING}),
	NINJA			(	"Ninja",		29,	5.8,    21,  2.1,    84,  8.4,    72,  7.2,    80,  8.1,    76,  7.6,   120,  2.1, 4, 3, 60, 10, FFTACommand.NINJA_SKILL, 		new EquipType[] {EquipType.KATANA, EquipType.HAT, EquipType.CLOTHING}),
	WHITE_MAGE_H	(	"White Mage",	30,	6.1,    40,  4.8,    60,  6.2,    73,  7.3,    84,  8.4,    80,  8.2,   108,  1.2, 3, 2, 40, 10, FFTACommand.WHITE_MAGIC_H, 	new EquipType[] {EquipType.STAFF, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	BLACK_MAGE_H	(	"Black Mage",	28,	5.6,    36,  4.4,    64,  6.4,    68,  6.8,    88,  8.8,    96,  9.6,    96,  1.1, 3, 2, 35, 10, FFTACommand.BLACK_MAGIC_H, 	new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	ILLUSIONIST_H	(	"Illusionist",	26,	5.2,    38,  7.1,    60,  6.1,    64,  6.4,    92,  9.2,    84,  8.4,    89,  0.9, 3, 2, 30, 10, FFTACommand.PHANTASM_SKILL_H,	new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	BLUE_MAGE		(	"Blue Mage",	34,	6.8,    30,  3.6,    80,  8.1,    84,  8.4,    84,  8.4,    92,  9.2,   100,  1.2, 4, 2, 55, 10, FFTACommand.BLUE_MAGIC, 		new EquipType[] {EquipType.SABER, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	ARCHER_H		(	"Archer",		36,	7.2,    18,  1.1,    72,  7.2,    72,  7.2,    64,  6.4,    80,  8.1,   106,  1.4, 4, 2, 50, 10, FFTACommand.AIM_H, 			new EquipType[] {EquipType.BOW, EquipType.HAT, EquipType.CLOTHING}),
	HUNTER			(	"Hunter",		34,	6.8,    26,  3.2,    88,  8.8,    68,  6.8,    68,  6.8,    84,  8.4,   112,  1.7, 4, 2, 55, 10, FFTACommand.HUNT, 				new EquipType[] {EquipType.GREATBOW, EquipType.HAT, EquipType.CLOTHING}),
	
	ANIMIST			(	"Animist",		36,	7.2,    14,  2.6,    76,  7.6,    88,  8.8,    72,  7.2,    88,  8.8,   100,  1.2, 4, 2, 55, 10, FFTACommand.CALL, 				new EquipType[] {EquipType.INSTRUMENT, EquipType.HAT, EquipType.CLOTHING}),
	MOG_KNIGHT		(	"Mog Knight",	35,	7.1,    12,  3.2,    88,  8.8,    96,  9.6,    60,  6.2,    80,  8.3,    98,  1.0, 3, 2, 45, 10, FFTACommand.CHARGE, 			new EquipType[] {EquipType.BLADE, EquipType.SHIELD, EquipType.HELM, EquipType.HAT, EquipType.ARMOR, EquipType.CLOTHING}),
	GUNNER			(	"Gunner",		30,	6.2,     8,  1.1,    68,  6.8,    92,  9.2,    56,  5.6,    80,  8.1,    95,  1.1, 3, 2, 65, 10, FFTACommand.GUNMANSHIP, 		new EquipType[] {EquipType.GUN, EquipType.HAT, EquipType.CLOTHING}),
	THIEF_M			(	"Thief",		29,	5.8,    12,  2.2,    73,  7.3,    84,  8.4,    68,  6.8,    76,  7.6,   110,  1.9, 4, 3, 70, 10, FFTACommand.STEAL_M,			new EquipType[] {EquipType.KNIFE, EquipType.HAT, EquipType.CLOTHING}),
	JUGGLER			(	"Juggler",		34,	6.8,    16,  1.6,    80,  8.2,   100,  9.9,    64,  6.4,    64,  6.4,   108,  1.7, 4, 2, 40, 10, FFTACommand.STUNT, 			new EquipType[] {EquipType.KNIFE, EquipType.HAT, EquipType.CLOTHING}),
	GADGETEER		(	"Gadgeteer",	35,	7.2,    20,  2.4,    80,  8.2,    96,  9.6,    80,  8.3,   100, 10.2,    90,  0.9, 3, 2, 50, 10, FFTACommand.PANDORA,			new EquipType[] {EquipType.KNUCKLES, EquipType.HAT, EquipType.CLOTHING}),
	BLACK_MAGE_M	(	"Black Mage",	27,	5.4,    28,  4.8,    57,  5.7,    76,  7.6,    84,  8.4,    97,  9.7,    96,  1.1, 3, 2, 35, 10, FFTACommand.BLACK_MAGIC_M,		new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	TIME_MAGE_M		(	"Time Mage",	26,	5.2,    22,  3.6,    56,  5.6,    76,  7.6,    92,  9.2,    94,  9.4,    98,  1.2, 3, 2, 30, 10, FFTACommand.TIME_MAGIC_M, 		new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	
	WHITE_MAGE_N	(	"White Mage",	29,	5.8,    52,  5.6,    57,  5.7,    73,  7.3,    88,  8.8,    84,  8.4,   108,  1.1, 3, 2, 40, 10, FFTACommand.WHITE_MAGIC_N,		new EquipType[] {EquipType.STAFF, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	BLACK_MAGE_N	(	"Black Mage",	27,	5.4,    42,  5.2,    62,  6.2,    68,  6.8,    92,  9.2,   100, 10.2,    96,  0.9, 3, 2, 35, 10, FFTACommand.BLACK_MAGIC_N,		new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	TIME_MAGE_N		(	"Time Mage",	26,	5.2,    36,  3.6,    56,  5.6,    68,  6.8,   100, 10.2,    96,  9.6,    98,  1.2, 3, 2, 30, 10, FFTACommand.TIME_MAGIC_N, 		new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	ILLUSIONIST_N	(	"Illusionist",	25,	5.1,    46,  7.6,    57,  5.7,    64,  6.4,    96,  9.6,    88,  8.8,    89,  0.8, 3, 2, 30, 10, FFTACommand.PHANTASM_SKILL_N, 	new EquipType[] {EquipType.ROD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	ALCHEMIST		(	"Alchemist",	30,	6.1,    48,  8.4,    59,  5.9,    65,  6.5,    92,  9.2,    96,  9.6,    92,  0.9, 3, 3, 35, 10, FFTACommand.ALCHEMY_SKILL,	 	new EquipType[] {EquipType.MACE, EquipType.HAT, EquipType.CLOTHING}),
	BEASTMASTER		(	"Beastmaster",	37,	7.4,    22,  2.1,    88,  8.8,    88,  8.8,    68,  6.8,    84,  8.4,   104,  1.2, 4, 3, 55, 10, FFTACommand.CONTROL, 			new EquipType[] {EquipType.INSTRUMENT, EquipType.HAT, EquipType.CLOTHING}),
	MORPHER			(	"Morpher",		34,	6.8,    32,  2.8,    70,  7.1,    80,  8.2,    76,  7.6,    80,  8.1,   110,  1.6, 4, 3, 30, 10, FFTACommand.MORPH, 			new EquipType[] {EquipType.SOUL, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	SAGE			(	"Sage",			38,	7.6,    38,  8.8,    84,  8.4,    76,  7.6,    92,  9.2,    76,  7.6,    85,  0.8, 4, 3, 40, 10, FFTACommand.SAGACITY_SKILL, 	new EquipType[] {EquipType.MACE, EquipType.SHIELD, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	
	FENCER			(	"Fencer",		38,	7.6,     8,  1.1,    84,  8.4,    80,  8.1,    76,  7.6,    72,  7.2,   110,  1.5, 4, 2, 60, 10, FFTACommand.LUNGE_TECH, 		new EquipType[] {EquipType.RAPIER, EquipType.SHIELD, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING}),
	ELEMENTALIST	(	"Elementalist",	34,	6.8,	18,	 4.2,	 80,  8.1,    76,  7.6,    88,  8.8,    76,  7.6,   104,  1.1, 3, 2, 50, 10, FFTACommand.SPIRIT_MAGIC, 		new EquipType[] {EquipType.RAPIER, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	RED_MAGE		(	"Red Mage",		34,	6.8,    22,  2.6,    81,  8.1,    76,  7.6,    84,  8.4,    76,  7.6,    96,  1.3, 4, 2, 50, 10, FFTACommand.RED_MAGIC, 		new EquipType[] {EquipType.RAPIER, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	WHITE_MAGE_V	(	"White Mage",	30,	6.2,    31,  4.8,    62,  6.2,    72,  7.2,    88,  8.8,    76,  7.6,   115,  1.2, 3, 2, 40, 10, FFTACommand.WHITE_MAGIC_V,		new EquipType[] {EquipType.STAFF, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	SUMMONER		(	"Summoner",		28,	5.6,    34,  6.2,    65,  6.5,    64,  6.4,   100, 10.1,    80,  8.4,    90,  0.9, 3, 2, 30, 10, FFTACommand.SUMMON_MAGIC,		new EquipType[] {EquipType.STAFF, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING, EquipType.ROBE}),
	ARCHER_V		(	"Archer",		36,	7.2,    14,  1.6,    80,  8.1,    68,  6.8,    72,  7.2,    76,  7.6,   112,  1.6, 4, 2, 50, 10, FFTACommand.AIM_V, 			new EquipType[] {EquipType.BOW, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING}),
	ASSASSIN		(	"Assassin",		26,	5.2,    18,  5.2,    88,  8.8,    68,  6.8,    92,  9.2,    72,  7.2,   125,  2.3, 4, 4, 65, 10, FFTACommand.CORNER, 			new EquipType[] {EquipType.KATANA, EquipType.GREATBOW, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING}),
	SNIPER			(	"Sniper",		31,	6.2,    10,  2.4,    94,  9.4,    68,  6.8,    76,  7.6,    76,  7.6,   105,  1.8, 4, 3, 60, 10, FFTACommand.SHARPSHOOT,		new EquipType[] {EquipType.GREATBOW, EquipType.RIBBON, EquipType.HAT, EquipType.CLOTHING});  
	
	public final String name;
	
	public final int move;
	public final int jump;
	public final int evade;
	
	public final int baseHP;
	public final int baseMP;
	public final int baseWAtk;
	public final int baseWDef;
	public final int baseMPow;
	public final int baseMRes;
	public final int baseSpeed;
	
	public final double growthHP;
	public final double growthMP;
	public final double growthWAtk;
	public final double growthWDef;
	public final double growthMPow;
	public final double growthMRes;
	public final double growthSpeed;
	public final int unarmedPower;
	
	public final FFTACommand command;
	
	public EquipType[] equips;
	
	FFTAJob(String name, int bHP, double gHP, int bMP, double gMP, int bWA, double gWA, int bWD, double gWD, int bMG, double gMG,
			int bMR, double gMR, int bSp, double gSp, int move, int jump, int evade, int unarmedPower, FFTACommand as, EquipType[] equips)
	{
		this.name = name;
		
		baseHP = bHP;
		baseMP = bMP;
		baseWAtk = bWA;
		baseWDef = bWD;
		baseMPow = bMG;
		baseMRes = bMR;
		baseSpeed = bSp;
		
		growthHP = gHP;
		growthMP = gMP;
		growthWAtk = gWA;
		growthWDef = gWD;
		growthMPow = gMG;
		growthMRes = gMR;
		growthSpeed = gSp;
		
		
		this.move = move;
		this.jump = jump;
		this.evade = evade;
		this.unarmedPower = unarmedPower;
		
		command = as;
		
		this.equips = new EquipType[equips.length + 3];
		for (int i = 0; i <equips.length; i++)
			this.equips[i] = equips[i];
		this.equips[equips.length] 		= EquipType.BOOTS;
		this.equips[equips.length + 1]	= EquipType.GLOVES;
		this.equips[equips.length + 2]	= EquipType.ACCESSORY;
	}
	
	public boolean canEquip(EquipType type)
	{
		for (int i = 0; i < equips.length; i++)
			if (equips[i] == type)
				return true;
		return false;
	}
	
	public String toString()
	{
		return name;
	}
}