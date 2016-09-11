package fftadata;

import java.io.Serializable;

public enum FFTASkill implements Serializable
{
	//FIGHT("-", 0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FIGHT			("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE	}),
	DRAIN_WEAPON	("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE,	SkillEffect.EFF1DEP_DRAIN}),
	CURE			("Cure",			 6, 40, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false, 	true,	new SkillEffect[] {SkillEffect.HEALING_1X}),
	CURA			("Cura", 			10, 60, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false, 	true,	new SkillEffect[] {SkillEffect.HEALING_1X}),
	CURAGA			("Curaga", 			16, 80, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false, 	true,	new SkillEffect[] {SkillEffect.HEALING_1X}),
	ESUNA			("Esuna", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LIFE			("Life", 			10,  0, 4, 0, 0, 0, Targeting.FREE_SELECT, Element.HOLY,		false,	true, 	false,	false,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_HALF_HP}),
	FULL_LIFE		("Full-Life", 		20,  0, 4, 0, 0, 0, Targeting.FREE_SELECT, Element.HOLY,		false,	true, 	false,	false,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_FULL_HP}),
	AUTO_LIFE		("Auto-Life", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SHELL			("Shell", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PROTECT			("Protect", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DISPEL			("Dispel", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HOLY			("Holy", 			32, 50, 3, 0, 1, 1, Targeting.FREE_SELECT,	Element.HOLY,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BARRIER			("Barrier", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	JUDGE			("Judge", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BREAK			("Break", 			20,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_PETRIFY}),
	WATER			("Water", 			12, 34, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.WATER,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	AERO			("Aero", 			12, 34, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.WIND,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	DRAIN			("Drain", 			12, 30, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false, 	false,	false, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE_CAPPED, SkillEffect.EFF1DEP_DRAIN}),
	BLIND			("Blind", 			12,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new	SkillEffect[] {SkillEffect.ADD_BLIND}),
	RAISE			("Raise",			22, 45, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.REVIVE_HALF_HP}),
	GIGA_FLARE		("Giga Flare",		40, 65, 3, 0, 1, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BIO				("Bio", 			12, 45, 3, 0, 2, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON}),
	ULTIMA_BLOW		("Ultima Blow", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	true,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE}),
	FIRE			("Fire",			 6, 30, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.FIRE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	FIRA			("Fira",			12, 40, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.FIRE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	FIRAGA			("Firaga",			24, 50, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.FIRE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	THUNDER			("Thunder", 		 6, 30, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	THUNDARA		("Thundara",		12, 40, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	THUNDAGA		("Thundaga", 		24, 50, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BLIZZARD		("Blizzard", 		 6, 30, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.ICE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BLIZZARA		("Blizzara",		12, 40, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.ICE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BLIZZAGA		("Blizzaga", 		24, 50, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.ICE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	true, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	SLEEP			("Sleep", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DOUBLECAST		("Doublecast", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	QUICKEN			("Quicken", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SLOW			("Slow", 			12, 0, 3, 0, 1, 2,	Targeting.FREE_SELECT,	Element.NULL,		 true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLOW}),
	REFLECT			("Reflect",			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STOP			("Stop", 			24, 0, 3, 0, 1, 2,	Targeting.FREE_SELECT,	Element.NULL,		 true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_STOP}),
	QUARTER			("Quarter", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DEMI			("Demi", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SILENCE			("Silence", 		 8,  0, 3, 0, 1, 2,	Targeting.FREE_SELECT,	Element.NULL,		 true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SILENCE}),
	HASTE			("Haste", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PROMINENCE		("Prominence", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	TEMPEST			("Tempest", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FREEZEBLINK		("Freezeblink", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STAR_CROSS		("Starcross", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STARDUST		("Stardust", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DELUGE			("Deluge", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SOIL_EVIDENCE	("Soil Evidence", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WILD_TORNADO	("Wild Tornado", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FIRE_WHIP		("Fire Whip", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	EARTH_HEAL		("Earth Heal", 		12, 40, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false, 	true,	new SkillEffect[] {SkillEffect.HEALING_1X}),
	WHITE_FLAME		("White Flame", 	24, 40, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false, 	true,	new SkillEffect[] {SkillEffect.HEALING_1X}),
	SHINING_AIR		("Shining Air", 	12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.WIND,		true,	false,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND}),
	EVIL_GAZE		("Evil Gaze", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HEAVY_DUST		("Heavy Dust",		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SLIPRAIN		("Sliprain", 		12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.WATER,		true,	false,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SLOW}),
	ELEMENTALSHIFT	("Elementalshift", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ASTRA			("Astra", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	RASP			("Rasp", 			24, 60, 3, 0, 1, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE}),
	DEATH			("Death", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	METEOR			("Meteor", 			40, 50, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	FLARE			("Flare", 			36, 65, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false, 	false, 	false, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	POISON			("Poison", 			10,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.DARK,		true,	false, 	false,	true,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_POISON}),
	TOAD			("Toad", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	UNICORN			("Unicorn", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	IFRIT			("Ifrit", 			18, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.FIRE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	RAMUH			("Ramuh", 			18, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	SHIVA			("Shiva",			18, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.ICE,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	KIRIN			("Kirin", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CARBUNCLE		("Carbuncle", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PHOENIX			("Phoenix", 		24,  0, 4, 0, 2, 2, Targeting.FREE_SELECT, 	Element.HOLY, 		false,	true, 	false, 	true, 	false, 	false, 	false, 	false, 	true, 	true, 	false, 	false, 	true, 	new SkillEffect[] {SkillEffect.REVIVE_FULL_HP}),
	MADEEN			("Madeen", 			52, 36, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	false, 	false, 	true, 	false, 	false, 	true, 	false, 	true, 	true, 	true, 	true, 	true, 	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	FIRST_AID		("First Aid",		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	POWERBREAK		("Powerbreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.ADD_WATK_DOWN}),
	MINDBREAK		("Mindbreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.ADD_MPOW_DOWN}),
	MAGICBREAK		("Magicbreak", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.MP_DAMAGE}),
	SPEEDBREAK		("Speedbreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.ADD_SPEED_DOWN}),
	MUG				("Mug", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PROVOKE			("Provoke", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SENSOR			("Sensor", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BODY_SLAM		("Body Slam", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GREASED_BOLT	("Greased Bolt",	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DOWNSIZE		("Downsize", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	NURSE			("Nurse", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	COVER			("Cover", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SUBDUE			("Subdue", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PARLEY			("Parley", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SAINT_CROSS		("Saint Cross", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HOLY_BLADE		("Holy Blade",		32, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.HOLY,		true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.DOUBLE_DAMAGE}),
	DEFENSE			("Defense", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DROP_WEAPON		("Drop Weapon", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	TREMOR			("Tremor", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HIBERNATE		("Hibernate", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MOW_DOWN		("Mow Down", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AURA			("Aura", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	EXPERT_GUARD	("Expert Guard", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MELTDOWN		("Meltdown", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WHIRLWIND		("Whirlwind", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	EARTH_RENDER	("Earth Render", 	 0, 34, 15, 0, 0, 0, Targeting.DIRECTIONAL,	Element.EARTH,		true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	CHAKRA			("Chakra", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	REVIVE			("Revive", 			 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_HALF_HP}),
	EXORCISE		("Exorcise", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HOLY_SIGN		("Holy Sign", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AIR_RENDER		("Air Render", 		 0, 45, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}), 
	FAR_FIST		("Far Fist", 		 0, 35, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true, true, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	AIR_BLAST		("Air Blast", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BACKDRAFT		("Backdraft", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	RUSH			("Rush", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WILD_SWING		("Wild Swing", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BEATDOWN		("Beatdown", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.DOUBLE_DAMAGE_HALF_HIT}),
	BLITZ			("Blitz", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.HALF_DAMAGE_DOUBLE_HIT}),
	FIRE_SWORD		("Fire Sword", 		10, 54, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BOLT_SWORD		("Bolt Sword", 		10, 54, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	ICE_SWORD		("Ice Sword", 		10, 54, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.ICE,		true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	ULTIMA_SWORD	("Ultima Sword", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE}),
	WARCRY			("Warcry", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CHEER			("Cheer", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SOUL_SPHERE		("Soul Sphere", 	 0, -1, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.MP_DAMAGE}),
	LIFEBREAK		("Lifebreak", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	JUMP			("Jump", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LANCET			("Lancet", 			0, 35, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true, false, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE_CAPPED, SkillEffect.EFF1DEP_DRAIN}),
	WYRMTAMER		("Wyrmtamer", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FIRE_BREATH		("Fire Breath", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BOLT_BREATH		("Bolt Breath", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ICE_BREATH		("Ice Breath", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WYRMKILLER		("Wyrmkiller", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BANGAA_CRY		("Bangaa Cry", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MOG_ATTACK		("Mog Attack", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MOG_GUARD		("Mog Guard", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MOG_LANCE		("Mog Lance", 		0, -1, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	MOG_RUSH		("Mog Rush", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DOUBLE_DAMAGE_HALF_HIT}),
	MOG_SHIELD		("Mog Shield", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MOG_PEEK		("Mog Peek", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MOG_AID			("Mog Aid", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ULTIMA_CHARGE	("Ultima Charge", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE}),
	SWARMSTRIKE		("Swarmstrike", 	 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON}),
	SHADOWSTICK		("Shadowstick",		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SPEED_DOWN}),
	CHECKMATE		("Checkmate", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FEATHERBLOW		("Featherblow", 	 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.HALF_DAMAGE_DOUBLE_HIT}),
	SWALLOWTAIL		("Swallowtail", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MANASTRIKE		("Manastrike", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.MP_DAMAGE}),
	PIERCETHROUGH	("Piercethrough", 	 0, -1, 2, 2, 0, 0,	Targeting.DIRECTIONAL,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	NIGHTHAWK		("Nighthawk", 		 0, -1, 4, 0, 0, 0,	Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	THROW			("Throw", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WOOD_VEIL		("Wood Veil", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FIRE_VEIL		("Fire Veil", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	EARTH_VEIL		("Earth Veil",		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	METAL_VEIL		("Metal Veil", 		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND}),
	WATER_VEIL		("Water Veil", 		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.WATER,		true,	false,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SILENCE}),
	UNSPELL			("Unspell", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	OBLIVION		("Oblivion", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SHADOWBIND		("Shadowbind", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LAST_BREATH		("Last Breath", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	APHONIA			("Aphonia", 		12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SILENCE}),
	NIGHTMARE		("Nightmare", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AGUE			("Ague", 			12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLOW}),
	ROCKSEAL		("Rockseal", 		32,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[]{SkillEffect.ADD_PETRIFY}),
	ULTIMA_MASHER	("Ultima Masher", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE}),
	STEAL_ARMOR		("Steal: Armor",	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_SHIELD	("Steal: Shield", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_ACCESS	("Steal: Access", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_HELM		("Steal: Helm", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_WEAPON	("Steal: Weapon", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_GIL		("Steal: Gil", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_EXP		("Steal: EXP", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_JP		("Steal: JP", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STEAL_ABILITY	("Steal: Ability", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BOOST			("Boost", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AIM_LEGS		("Aim: Legs", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AIM_ARM			("Aim: Arm", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CUPID			("Cupid", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BURIAL			("Burial", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	TAKE_AIM		("Take Aim", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.HALF_DAMAGE_NEVER_MISS}),
	FASTER			("Faster", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BLACKOUT		("Blackout", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_BLIND}),	
	SONIC_BOOM		("Sonic Boom", 		 0, -1, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	true,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	OUST			("Oust", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ADVICE			("Advice", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AIM_VITALS		("Aim: Vitals", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HUNTING			("Hunting", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ADDLE			("Addle", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ULTIMA_SHOT		("Ultima Shot", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE}),
	SIDEWINDER		("Sidewinder", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	CAPTURE			("Capture", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GOBLIN			("Goblin", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FLAN			("Flan", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BOMB			("Bomb", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DRAGON			("Dragon", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LAMIA			("Lamia", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BUG				("Bug", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PANTHER			("Panther",			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MALBORO			("Malboro", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FLOATEYE		("Floateye", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HURL			("Hurl", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	RING			("Ring", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FIREBOMB		("Firebomb", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BALL			("Ball", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DAGGER			("Dagger", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SMILE			("Smile", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GIL_TOSS		("Gil Toss", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BESO_TOXICO		("Beso Toxico", 	 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true, true, false, true, true, false, false, true, false, false, true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON}),
	DEATH_SICKLE	("Death Sickle", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CONCEAL			("Conceal", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DOOM_ARCHER		("Doom Archer", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DOUBLESHOT		("Doubleshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_FIGHT_DAMAGE, SkillEffect.HALF_FIGHT_DAMAGE}),
	AIM_ARMOR		("Aim: Armor", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AIM_WEAPON		("Aim: Weapon", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	AIM_WALLET		("Aim: Wallet", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SHEEP_COUNT		("Sheep Count", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WOOL			("Wool", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CUISINE			("Cuisine", 		32, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,  false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.FULLY_HEAL_HP}),
	TAIL_WAG		("Tail Wag", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CHOCOBO_RUSH	("Chocobo Rush", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FROGSONG		("Frogsong", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FRIEND			("Friend", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CATNIP			("Catnip", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FIRESHOT		("Fireshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.FIRE,		true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	BOLTSHOT		("Boltshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.LIGHTNING,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	ICESHOT			("Iceshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.ICE,		true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE}),
	CONFUSHOT		("Confushot", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CHARMSHOT		("Charmshot", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BLINDSHOT		("Blindshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND}),
	SILENSHOT		("Silenshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SILENCE}),
	STOPSHOT		("Stopshot", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	RED_SPRING		("Red Spring", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BLUE_SCREW		("Blue Screw", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GREEN_GEAR		("Green Gear", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SILVER_DISC		("Silver Disc", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GOLD_BATTERY	("Gold Battery", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BLACK_INGOT		("Black Ingot", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CHROMA_GEM		("Chroma Gem", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	YELLOW_SPRING	("Yellow Spring", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	POTION			("Potion", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HI_POTION		("Hi-Potion", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	X_POTION		("X-Potion", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ETHER			("Ether", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ELIXIR			("Elixir", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	PHOENIX_DOWN	("Phoenix Down", 	 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_HALF_HP}),
	ECHO_SCREEN		("Echo Screen", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MAIDEN_KISS		("Maiden Kiss", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SOFT			("Soft", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HOLY_WATER		("Holy Water", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ANTIDOTE		("Antidote", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	EYE_DROPS		("Eye Drops", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BANDAGE			("Bandage", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CUREALL			("Cureall", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DRAW_WEAPON		("Draw Weapon", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GOBLIN_PUNCH	("Goblin Punch", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MAGIC_HAMMER	("Magic Hammer", 	 8, 38, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE}),
	MUTILATE		("Mutilate", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ACID			("Acid", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SACRIFICE		("Sacrifice", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BLOWUP			("Blowup", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FLAME_ATTACK	("Flame Attack", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CHILL			("Chill", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MIGHTY_GUARD	("Mighty Guard", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	GUARD_OFF		("Guard-Off", 		10,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_WDEF_DOWN, SkillEffect.EFF1DEP_ADD_MRES_DOWN}),
	DRAGON_FORCE	("Dragon Force",	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	NIGHT			("Night", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	TWISTER			("Twister", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HAND_SLAP		("Hand Slap", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	POISON_FROG		("Poison Frog", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	KISS			("Kiss", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LV3_DEF_LESS	("LV3 Def-Less", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SANDSTORM		("Sandstorm", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LV5_DEATH		("LV5 Death", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	SUFFOCATE		("Suffocate", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	RESONATE		("Resonate", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	LIMIT_GLOVE		("Limit Glove", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MATRA_MAGIC		("Matra Magic", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MUNCH			("Munch", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	POISON_CLAW		("Poison Claw", 	 8, 30, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	false,	false,	true,	true,	false,	true,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON}),
	HASTEBREAK		("Hastebreak", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	REND			("Rend", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	BLASTER			("Blaster", 		 0,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_PETRIFY}),
	BAD_BREATH		("Bad Breath", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	STARE			("Stare", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ROULETTE		("Roulette", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DEVIL_GAZE		("Devil Gaze", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	CIRCLE			("Circle", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	DRAIN_TOUCH		("Drain Touch", 	10, 20, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false, true, false, false, true, true, false, true, false, false, true, true, new SkillEffect[] {SkillEffect.REGULAR_DAMAGE_CAPPED, SkillEffect.EFF1DEP_DRAIN}),
	LVQ_S_FLARE		("LV? S-Flare", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	WHITE_WIND		("White Wind", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ANGEL_WHISPER	("Angel Whisper", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ADRAMMALECH		("Adrammalech", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	MATEUS			("Mateus", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ULTIMA			("Ultima", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	EXODUS			("Exodus", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FAMFRIT			("Famfrit", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	OMEGA			("Omega", 			0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	ABYSS			("Abyss", 			12, 48, 3, 0, 2, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	true,	false,	false,	false,	false,	true,	true,	new  SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON}),
	LIFE_RENDER		("Life Render", 	0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	HEART_RENDER	("Heart Render", 	 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true, new SkillEffect[] {SkillEffect.MP_DAMAGE}),
	RIPCIRCLE		("Ripcircle", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null),
	FURYCIRCLE		("Furycircle", 		0, 0, 0, 0, 0, 0, null, null, true,	false, false, false, false, false, false, false, false, false, false, false, false, null);
	
	
	public final String NAME;
	public final int MP_COST, POWER, H_RADIUS, V_RADIUS, H_RANGE, V_RANGE;
	public final Targeting TARGETING;
	public final Element ELEMENT;
	public final boolean TARGET_LIVE, TARGET_DEAD, 
						 IS_PHYSICAL, SELF_TARGET, IGNORE_REACTION, IGNORE_SILENCE, COVERABLE,
						 REFLECTABLE, DOUBLECASTABLE, STEALABLE, TRIGGER_RET_MAG, TRIGGER_ABS_MP,
						 IMPLEMENTED;
	public final SkillEffect[] EFFECTS;
	
	public static final FFTASkill values[] = values();
	
	FFTASkill(String name, int mpCost, int power, int hRange, int vRange, int hRadius, int vRadius,
			  Targeting targ, Element element, boolean targetLive, boolean targetDead, boolean isPhysical,
			  boolean selfTarget, boolean ignoreReaction, boolean ignoreSilence, boolean coverable,
			  boolean reflectable, boolean doublecastable, boolean stealable, boolean triggerRetMag,
			  boolean triggerAbsMP, boolean implemented, SkillEffect[] effects)
	{
		// Name
		NAME = name;
		
		// ints
		MP_COST = mpCost;
		POWER = power;
		H_RANGE = hRange;
		V_RANGE = vRange;
		H_RADIUS = hRadius;
		V_RADIUS = vRadius;
		
		// enums
		TARGETING = targ;
		ELEMENT = element;
		
		// flags
		TARGET_LIVE = targetLive;
		TARGET_DEAD = targetDead;
		IS_PHYSICAL = isPhysical;
		SELF_TARGET = selfTarget;
		IGNORE_REACTION = ignoreReaction;
		IGNORE_SILENCE = ignoreSilence;
		COVERABLE = coverable;
		REFLECTABLE = reflectable;
		DOUBLECASTABLE = doublecastable;
		STEALABLE = stealable;
		TRIGGER_RET_MAG = triggerRetMag;
		TRIGGER_ABS_MP = triggerAbsMP;
		IMPLEMENTED = implemented;
		
		// lastly
		EFFECTS = effects;
	}
	
	public static boolean canUseSkill(FFTASkill sk, ActiveUnit au)
	{
		boolean result;
		
		int mpCost = sk.MP_COST;
		if (au.unit.support == FFTASupport.HALF_MP)
			mpCost /= 2;
		else if (au.unit.support == FFTASupport.TURBO_MP)
			mpCost *= 2;
		
		// Does the unit have sufficient MP for this skill?
		result = mpCost <= au.currMP;
		
		// Is the unit silenced and trying to use a skill that is blocked by silence?
		result = result && (au.status[StatusEffect.SILENCE.ordinal()] == 0 || sk.IGNORE_SILENCE);

		// Has this skill actually been implemented by the developer?
		result = result && sk.IMPLEMENTED;
		
		return result;
	}
}

enum WeaponReq
{
	NONE, WEAPON, SPEAR, BOW;
}