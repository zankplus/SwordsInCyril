package fftadata;
import java.io.Serializable;

import javax.swing.ImageIcon;

public enum FFTAEquip implements Serializable
{
	NONE			(" -",				EquipType.NONE,			0,	0,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	
	UNARMED			("Unarmed",			EquipType.UNARMED,		10,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),		
	
	BLOOD_SWORD		("Blood Sword",		EquipType.SWORD,		18,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.DRAIN_HP}),
	BURGLAR_SWORD	("Burglar Sword",	EquipType.SWORD,		39,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	BUSTER_SWORD	("Buster Sword",	EquipType.SWORD,		35,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	CHIRIJIRADEN	("Chirijiraden",	EquipType.SWORD,		65,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	GALE_SWORD		("Gale Sword",		EquipType.SWORD,		36,	0,	0,	0,	1,	0,	0,	0,	1,	Element.WIND,		1,	false,	new ItemEffect[] {}),
	LAGLACE_SWORD	("Laglace Sword",	EquipType.SWORD,		41,	0,	5,	0,	0,	0,	0,	0,	1,	Element.ICE,		2,	false,	new ItemEffect[] {}),
	MYTHRIL_SWORD	("Mythril Sword",	EquipType.SWORD,		33,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	ONION_SWORD		("Onion Sword",		EquipType.SWORD,		29,	5,	0,	5,	0,	0,	0,	10,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	RESTORER		("Restorer",		EquipType.SWORD,		40,	0,	5,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SHORTSWORD		("Shortsword",		EquipType.SWORD,		25,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SILVER_SWORD	("Silver Sword",	EquipType.SWORD,		30,	0,	0,	0,	2,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	VICTOR_SWORD	("Victor Sword",	EquipType.SWORD,		33,	10,	0,	10,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	VITANOVA		("Vitanova",		EquipType.SWORD,		38,	0,	2,	0,	0,	0,	0,	2,	1,	Element.HOLY,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_HOLY}),
	
	ADAMAN_BLADE	("Adaman Blade",	EquipType.BLADE,		65,	15,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	AIR_BLADE		("Air Blade",		EquipType.BLADE,		40,	0,	0,	0,	0,	0,	0,	0,	1,	Element.WIND,		1,	true,	new ItemEffect[] {ItemEffect.NULL_WIND}),
	ATMOS_BLADE		("Atmos Blade",		EquipType.BLADE,		36,	0,	0,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1,	false,	new ItemEffect[] {}),
	AYVUIR_BLUE		("Ayvuir Blue",		EquipType.BLADE,		51,	0,	0,	10,	0,	0,	0,	2,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	AYVUIR_RED		("Ayvuir Red",		EquipType.BLADE,		62,	10,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	EBON_BLADE		("Ebon Blade",		EquipType.BLADE,		84,	5,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		2,	true,	new ItemEffect[] {}),
	FLAMETONGUE		("Flametongue",		EquipType.BLADE,		38,	0,	0,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1,	false,	new ItemEffect[] {}),
	ICEBRAND		("Icebrand",		EquipType.BLADE,		42,	0,	0,	0,	0,	0,	0,	0,	1,	Element.ICE,		1,	false,	new ItemEffect[] {}),
	KWIGON_BLADE	("Kwigon Blade",	EquipType.BLADE,		40,	3,	0,	3,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MATERIA_BLADE	("Materia Blade",	EquipType.BLADE,		17,	0,	15,	10,	0,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MYTHRIL_BLADE	("Mythril Blade",	EquipType.BLADE,		32,	3,	0,	3,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	OGUN_BLADE		("Ogun Blade",		EquipType.BLADE,		42,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	PARAIBA_BLADE	("Paraiba Blade",	EquipType.BLADE,		33,	0,	10,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	PEARL_BLADE		("Pearl Blade",		EquipType.BLADE,		46,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SHADOW_BLADE	("Shadow Blade",	EquipType.BLADE,		32,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SUN_BLADE		("Sun Blade",		EquipType.BLADE,		37,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SWEEP_BLADE		("Sweep Blade",		EquipType.BLADE,		28,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	VENUS_BLADE		("Venus Blade",		EquipType.BLADE,		45,	0,	2,	0,	2,	0,	0,	0,	1,	Element.FIRE,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_FIRE,	ItemEffect.NULL_WATER}),
	
	AQUA_SABER		("Aqua Saber",		EquipType.SABER,		36,	0,	0,	0,	0,	0,	0,	6,	1,	Element.WATER,		1,	false,	new ItemEffect[] {}),
	BLUE_SABER		("Blue Saber",		EquipType.SABER,		25,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	HARPE			("Harpe",			EquipType.SABER,		42,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MANGANESE		("Manganese",		EquipType.SABER,		47,	10,	0,	0,	0,	0,	0,	3,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MYTHRIL_SABER	("Mythril Saber",	EquipType.SABER,		32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	SHAMSHIR		("Shamshir",		EquipType.SABER,		31,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SOUL_SABER		("Soul Saber",		EquipType.SABER,		39,	0,	0,	10,	0,	0,	0,	5,	1,	Element.FIRE,		2,	true,	new ItemEffect[] {}),
	TULWAR			("Tulwar",			EquipType.SABER,		55,	10,	0,	10,	0,	0,	0,	2,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
		
	APOCALYPSE		("Apocalypse",		EquipType.KNIGHTSWORD,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		1,	false,	new ItemEffect[] {}),
	ARCH_SWORD		("Arch Sword",		EquipType.KNIGHTSWORD,	48,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	DEFENDER		("Defender",		EquipType.KNIGHTSWORD,	37,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	EXCALIBUR		("Excalibur",		EquipType.KNIGHTSWORD,	47,	0,	2,	0,	1,	0,	0,	5,	1,	Element.HOLY,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_HOLY,	ItemEffect.ENHN_HOLY}),
	EXCALIBUR2		("Excalibur2",		EquipType.KNIGHTSWORD,	87,	0,	3,	0,	4,	0,	0,	5,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	LIONHEART		("Lionheart",		EquipType.KNIGHTSWORD,	34,	2,	0,	1,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LOHENGRIN		("Lohengrin",		EquipType.KNIGHTSWORD,	46,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_BRAND	("Mythril Brand",	EquipType.KNIGHTSWORD,	32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	NAGRAROK		("Nagrarok",		EquipType.KNIGHTSWORD,	75,	0,	0,	0,	6,	1,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	RAGNAROK		("Ragnarok",		EquipType.KNIGHTSWORD,	36,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SAVETHEQUEEN	("SaveTheQueen",	EquipType.KNIGHTSWORD,	45,	0,	0,	0,	0,	0,	0,	0,	1,	Element.HOLY,		1,	false,	new ItemEffect[] {ItemEffect.ENHN_HOLY}),
	SEQUENCE		("Sequence",		EquipType.KNIGHTSWORD,	32,	2,	5,	2,	2,	1,	1,	2,	1,	Element.NULL,		3,	true,	new ItemEffect[] {}),
	
	ANCIENT_SWORD	("Ancient Sword",	EquipType.GREATSWORD,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_PETRIFY}),
	BARONG			("Barong",			EquipType.GREATSWORD,	30,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	DIAMOND_SWORD	("Diamond Sword",	EquipType.GREATSWORD,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_SLOW}),
	HARDEDGE		("Hardedge",		EquipType.GREATSWORD,	42,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_DOOM}),
	ICEPRISM		("Iceprism",		EquipType.GREATSWORD,	45,	0,	0,	0,	0,	0,	0,	0,	1,	Element.ICE,		2,	true,	new ItemEffect[] {ItemEffect.NULL_SILENCE,	ItemEffect.NULL_FIRE}),
	LUREBREAKER		("Lurebreaker",		EquipType.GREATSWORD,	51,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.NULL_SLEEP}),
	MASTER_SWORD	("Master Sword",	EquipType.GREATSWORD,	59,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.NULL_KO}),
	OBLIGE			("Oblige",			EquipType.GREATSWORD,	48,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.NULL_CHARM}),
	VIGILANTE		("Vigilante",		EquipType.GREATSWORD,	37,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_CONFUSION}),
	ZANKPLUS		("Zankplus",		EquipType.GREATSWORD,	49,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.NULL_POISON}),
	
	BEASTSWORD		("Beastsword",		EquipType.BROADSWORD,	50,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	CLAYMORE		("Claymore",		EquipType.BROADSWORD,	49,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ECLIPSE			("Eclipse",			EquipType.BROADSWORD,	76,	5,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	EL_CID_SWORD	("El Cid Sword",	EquipType.BROADSWORD,	47,	10,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ESTRELEDGE		("Estreledge",		EquipType.BROADSWORD,	77,	5,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	FALCHION		("Falchion",		EquipType.BROADSWORD,	27,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	PREDATOR		("Predator",		EquipType.BROADSWORD,	37,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	RHOMPHAIA		("Rhomphaia",		EquipType.BROADSWORD,	57,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	SAMSON_SWORD	("Samson Sword",	EquipType.BROADSWORD,	32,	5,	0,	0,	0,	0,	0,	0,	1,	Element.EARTH,		1,	false,	new ItemEffect[] {}),
	STRIBORG		("Striborg",		EquipType.BROADSWORD,	33,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	TABARISE		("Tabarise",		EquipType.BROADSWORD,	47,	5,	0,	0,	2,	0,	0,	2,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	VAJRA			("Vajra",			EquipType.BROADSWORD,	45,	5,	0,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	2,	true,	new ItemEffect[] {ItemEffect.ENHN_LIGHTNING}),
	
	CINQUEDEA		("Cinquedea",		EquipType.KNIFE,		57,	0,	0,	0,	5,	0,	0,	2,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	JACK_KNIFE		("Jack Knife",		EquipType.KNIFE,		22,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	JAMBIYA			("Jambiya",			EquipType.KNIFE,		31,	0,	2,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	KARD			("Kard",			EquipType.KNIFE,		35,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	KHUKURI			("Khukuri",			EquipType.KNIFE,		37,	0,	0,	0,	2,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	KRIS_KNIFE		("Kris Knife",		EquipType.KNIFE,		30,	0,	0,	5,	0,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_KNIFE	("Mythril Knife",	EquipType.KNIFE,		32,	0,	0,	0,	0,	0,	1,	1,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	ORICHALCUM		("Orichalcum",		EquipType.KNIFE,		60,	0,	0,	2,	0,	0,	0,	1,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	RONDELL_DAGGER	("Rondell Dagger",	EquipType.KNIFE,		22,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_IMMOBILIZE,	ItemEffect.NULL_DISABLE}),
	SCRAMASAX		("Scramasax",		EquipType.KNIFE,		29,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SWORD_BREAKER	("Sword Breaker",	EquipType.KNIFE,		39,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	TIPTAPTWO		("Tiptaptwo",		EquipType.KNIFE,		35,	0,	0,	0,	15,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_SLOW}),
	TONBERRIAN		("Tonberrian",		EquipType.KNIFE,		37,	0,	0,	0,	10,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	ZORLIN_SHAPE	("Zorlin Shape",	EquipType.KNIFE,		38,	0,	0,	0,	1,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	
	AERIAL_HOLE		("Aerial Hole",		EquipType.RAPIER,		43,	0,	8,	0,	2,	0,	0,	0,	1,	Element.WIND,		2,	false,	new ItemEffect[] {}),
	COLICHEMARDE	("Colichemarde",	EquipType.RAPIER,		36,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_BERSERK}),
	DIABOLIQUE		("Diabolique",		EquipType.RAPIER,		41,	0,	0,	5,	2,	0,	0,	0,	1,	Element.DARK,		1,	true,	new ItemEffect[] {ItemEffect.NULL_DARK}),
	DJINN_FLYSSA	("Djinn Flyssa",	EquipType.RAPIER,		34,	0,	0,	2,	2,	0,	0,	2,	1,	Element.WIND,		1,	true,	new ItemEffect[] {ItemEffect.ENHN_WIND,	ItemEffect.NULL_WIND}),
	EPEPRISM		("Epeprism",		EquipType.RAPIER,		37,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.HALF_HOLY,	ItemEffect.HALF_DARK}),
	ESTOC			("Estoc",			EquipType.RAPIER,		32,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	FEMME_FATALE	("Femme Fatale",	EquipType.RAPIER,		49,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.NULL_DOOM}),
	FLAMBERGE		("Flamberge",		EquipType.RAPIER,		35,	5,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	FLEURET			("Fleuret",			EquipType.RAPIER,		27,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	GUPTI_AGA		("Gupti Aga",		EquipType.RAPIER,		38,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	JOYEUSE			("Joyeuse",			EquipType.RAPIER,		27,	0,	0,	5,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LAST_LETTER		("Last Letter",		EquipType.RAPIER,		38,	0,	0,	0,	2,	0,	0,	3,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MADU			("Madu",			EquipType.RAPIER,		33,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MAGE_MASHER		("Mage Masher",		EquipType.RAPIER,		38,	0,	5,	10,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_RAPIER	("Mythril Rapier",	EquipType.RAPIER,		32,	0,	0,	0,	2,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	SCARLETTE		("Scarlette",		EquipType.RAPIER,		27,	0,	2,	0,	2,	0,	0,	0,	1,	Element.FIRE,		1,	false,	new ItemEffect[] {}),
	SILVER_RAPIER	("Silver Rapier",	EquipType.RAPIER,		35,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	STINGER			("Stinger",			EquipType.RAPIER,		25,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	
	ASHURA			("Ashura",			EquipType.KATANA,		33,	0,	0,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1,	false,	new ItemEffect[] {}),
	CHARFIRE		("Charfire",		EquipType.KATANA,		47,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	HEAVENS_CLOUD	("Heaven's Cloud",	EquipType.KATANA,		39,	0,	0,	5,	0,	0,	0,	0,	1,	Element.HOLY,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_HOLY}),
	KIKUICHIMONJI	("Kikuichimonji",	EquipType.KATANA,		40,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	KOTETSU			("Kotetsu",			EquipType.KATANA,		37,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MASAMUNE		("Masamune",		EquipType.KATANA,		65,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MASAMUNE_100	("Masamune 100",	EquipType.KATANA,		79,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	MURASAME		("Murasame",		EquipType.KATANA,		31,	0,	0,	0,	0,	0,	0,	0,	1,	Element.WATER,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_EPEE	("Mythril Epee",	EquipType.KATANA,		32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	NINJA_KNIFE		("Ninja Knife",		EquipType.KATANA,		31,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	NOSADA			("Nosada",			EquipType.KATANA,		42,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	OSAFUNE			("Osafune",			EquipType.KATANA,		35,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	PETALCHASER		("Petalchaser",		EquipType.KATANA,		34,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SILKMOON		("Silkmoon",		EquipType.KATANA,		55,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	ZANMATO			("Zanmato",			EquipType.KATANA,		22,	0,	2,	0,	0,	0,	0,	0,	1,	Element.HOLY,		1,	true,	new ItemEffect[] {ItemEffect.ENHN_HOLY,	ItemEffect.HALF_DARK}),
	
	BLESS_STAFF		("Bless Staff",		EquipType.STAFF,		23,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	CHEER_STAFF		("Cheer Staff",		EquipType.STAFF,		32,	5,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	CURE_STAFF		("Cure Staff",		EquipType.STAFF,		29,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.HEAL_HP}),
	DREAM_WATCHER	("Dream Watcher",	EquipType.STAFF,		43,	0,	10,	15,	0,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	GARNET_STAFF	("Garnet Staff",	EquipType.STAFF,		23,	5,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	GUARD_STAFF		("Guard Staff",		EquipType.STAFF,		21,	5,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	JUDGE_STAFF		("Judge Staff",		EquipType.STAFF,		21,	0,	3,	5,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1,	false,	new ItemEffect[] {}),
	MYTHRIL_STAFF	("Mythril Staff",	EquipType.STAFF,		32,	0,	0,	5,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	NIRVANA_STAFF	("Nirvana Staff",	EquipType.STAFF,		34,	0,	0,	10,	0,	0,	0,	0,	1,	Element.HOLY,		1,	false,	new ItemEffect[] {}),
	POWER_STAFF		("Power Staff",		EquipType.STAFF,		45,	6,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	PURE_STAFF		("Pure Staff",		EquipType.STAFF,		23,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SNAKE_STAFF		("Snake Staff",		EquipType.STAFF,		29,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SPRING_STAFF	("Spring Staff",	EquipType.STAFF,		28,	0,	0,	5,	0,	0,	0,	0,	1,	Element.WATER,		1,	true,	new ItemEffect[] {ItemEffect.NULL_WATER}),
	WHITE_STAFF		("White Staff",		EquipType.STAFF,		23,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.REMOVE_DOOM}),
	
	CHILL_ROD		("Chill Rod",		EquipType.ROD,			27,	0,	2,	0,	0,	0,	0,	0,	1,	Element.ICE,		1,	true,	new ItemEffect[] {ItemEffect.ENHN_ICE}),
	FIREWHEEL_ROD	("Firewheel Rod",	EquipType.ROD,			21,	0,	2,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1,	false,	new ItemEffect[] {}),
	FLAME_ROD		("Flame Rod",		EquipType.ROD,			27,	0,	2,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1,	true,	new ItemEffect[] {ItemEffect.ENHN_FIRE}),
	FORCE_ROD		("Force Rod",		EquipType.ROD,			25,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	HERETIC_ROD		("Heretic Rod",		EquipType.ROD,			31,	0,	20,	0,	0,	0,	0,	0,	1,	Element.DARK,		2,	false,	new ItemEffect[] {}),
	MYTHRIL_ROD		("Mythril Rod",		EquipType.ROD,			32,	0,	2,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	PRINCESS_ROD	("Princess Rod",	EquipType.ROD,			35,	5,	2,	5,	0,	0,	0,	2,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ENHN_WIND,	ItemEffect.ENHN_EARTH,	ItemEffect.ENHN_WATER}),
	ROD				("Rod",				EquipType.ROD,			18,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SAPERE_AUDE		("Sapere Aude",		EquipType.ROD,			18,	2,	5,	2,	2,	1,	1,	2,	1,	Element.NULL,		3,	true,	new ItemEffect[] {}),
	SLEET_ROD		("Sleet Rod",		EquipType.ROD,			21,	0,	2,	0,	0,	0,	0,	0,	1,	Element.ICE,		1,	false,	new ItemEffect[] {}),
	STARDUST_ROD	("Stardust Rod",	EquipType.ROD,			29,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	TERRE_ROD		("Terre Rod",		EquipType.ROD,			23,	0,	2,	0,	0,	0,	0,	0,	1,	Element.EARTH,		1,	false,	new ItemEffect[] {ItemEffect.ENHN_EARTH}),
	THOR_ROD		("Thor Rod",		EquipType.ROD,			27,	0,	2,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1,	true,	new ItemEffect[] {ItemEffect.ENHN_LIGHTNING}),
	THUNDER_ROD		("Thunder Rod",		EquipType.ROD,			21,	0,	2,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1,	false,	new ItemEffect[] {}),
	
	BATTLE_MACE		("Battle Mace",		EquipType.MACE,			31,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	CACTUS_STICK	("Cactus Stick",	EquipType.MACE,			62,	2,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	DRUID_MACE		("Druid Mace",		EquipType.MACE,			33,	0,	3,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ENERGY_MACE		("Energy Mace",		EquipType.MACE,			29,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LIFE_CROSIER	("Life Crosier",	EquipType.MACE,			35,	0,	2,	10,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LOTUS_MACE		("Lotus Mace",		EquipType.MACE,			37,	0,	2,	5,	0,	0,	0,	0,	1,	Element.FIRE,		1,	true,	new ItemEffect[] {ItemEffect.ENHN_FIRE}),
	MANDRAGORA		("Mandragora",		EquipType.MACE,			37,	0,	2,	5,	0,	0,	0,	0,	1,	Element.EARTH,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_EARTH,	ItemEffect.NULL_POISON}),
	MORNING_STAR	("Morning Star",	EquipType.MACE,			33,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_MACE	("Mythril Mace",	EquipType.MACE,			32,	0,	2,	5,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	SAGE_CROSIER	("Sage Crosier",	EquipType.MACE,			31,	0,	8,	8,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SCORPION_TAIL	("Scorpion Tail",	EquipType.MACE,			38,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	VESPER			("Vesper",			EquipType.MACE,			39,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ZEUS_MACE		("Zeus Mace",		EquipType.MACE,			15,	0,	5,	5,	0,	0,	0,	0,	1,	Element.HOLY,		2,	true,	new ItemEffect[] {ItemEffect.ENHN_HOLY}),
	
	ARTEMIS_BOW		("Artemis Bow",		EquipType.BOW,			27,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	CHAR_BOW		("Char Bow",		EquipType.BOW,			21,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	CRESCENT_BOW	("Crescent Bow",	EquipType.BOW,			45,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	LONGBOW			("Longbow",			EquipType.BOW,			19,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	MALBOW			("Malbow",			EquipType.BOW,			55,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	MYTHRIL_BOW		("Mythril Bow",		EquipType.BOW,			32,	0,	0,	0,	0,	0,	0,	1,	5,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	NAIL_BOW		("Nail Bow",		EquipType.BOW,			29,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	PERSEUS_BOW		("Perseus Bow",		EquipType.BOW,			42,	0,	0,	0,	0,	0,	0,	2,	6,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SILVER_BOW		("Silver Bow",		EquipType.BOW,			23,	0,	0,	0,	0,	0,	0,	0,	6,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	TARGET_BOW		("Target Bow",		EquipType.BOW,			35,	0,	0,	0,	0,	0,	0,	5,	5,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	THORN_BOW		("Thorn Bow",		EquipType.BOW,			25,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	YOICHI_BOW		("Yoichi Bow",		EquipType.BOW,			33,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	
	ARBALEST		("Arbalest",		EquipType.GREATBOW,		42,	0,	0,	0,	0,	0,	0,	0,	5,	Element.EARTH,		2,	false,	new ItemEffect[] {}),
	CRANEQUIN		("Cranequin",		EquipType.GREATBOW,		29,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	FEY_BOW			("Fey Bow",			EquipType.GREATBOW,		31,	0,	0,	0,	0,	0,	0,	2,	6,	Element.WIND,		1,	false,	new ItemEffect[] {}),
	GASTRA_BOW		("Gastra Bow",		EquipType.GREATBOW,		51,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	HADES_BOW		("Hades Bow",		EquipType.GREATBOW,		33,	0,	0,	0,	0,	0,	0,	0,	5,	Element.DARK,		1,	false,	new ItemEffect[] {}),
	MARDUK_BOW		("Marduk Bow",		EquipType.GREATBOW,		39,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MASTER_BOW		("Master Bow",		EquipType.GREATBOW,		41,	0,	0,	0,	0,	0,	0,	2,	5,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MAXS_OATHBOW	("Max's Oathbow",	EquipType.GREATBOW,		61,	0,	2,	0,	0,	0,	0,	0,	6,	Element.DARK,		2,	true,	new ItemEffect[] {}),
	MYTHRIL_SHOT	("Mythril Shot",	EquipType.GREATBOW,		32,	0,	0,	0,	0,	0,	1,	0,	5,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	NIKE_BOW		("Nike Bow",		EquipType.GREATBOW,		37,	5,	0,	0,	0,	0,	0,	0,	5,	Element.LIGHTNING,	1,	false,	new ItemEffect[] {}),
	RANGER_BOW		("Ranger Bow",		EquipType.GREATBOW,		23,	0,	0,	0,	0,	0,	0,	0,	5,	Element.EARTH,		0,	false,	new ItemEffect[] {}),
	SEVENTH_HEAVEN	("Seventh Heaven",	EquipType.GREATBOW,		15,	0,	0,	0,	0,	0,	0,	5,	5,	Element.HOLY,		0,	false,	new ItemEffect[] {}),
	TWIN_BOW		("Twin Bow",		EquipType.GREATBOW,		31,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	WINDSLASH_BOW	("Windslash Bow",	EquipType.GREATBOW,		25,	0,	0,	0,	0,	0,	0,	2,	5,	Element.WIND,		0,	false,	new ItemEffect[] {}),
	
	BANGAA_SPIKE	("Bangaa Spike",	EquipType.SPEAR,		53,	5,	2,	5,	0,	0,	0,	0,	2,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	BEASTSPEAR		("Beastspear",		EquipType.SPEAR,		51,	10,	0,	0,	0,	0,	0,	0,	2,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	DRAGON_WHISKER	("Dragon Whisker",	EquipType.SPEAR,		45,	0,	0,	0,	0,	0,	0,	1,	2,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	GAE_BOLG		("Gae Bolg",		EquipType.SPEAR,		39,	0,	0,	0,	0,	0,	0,	0,	2,	Element.LIGHTNING,	0,	true,	new ItemEffect[] {}),
	ICE_LANCE		("Ice Lance",		EquipType.SPEAR,		35,	0,	0,	0,	0,	0,	0,	0,	2,	Element.ICE,		0,	false,	new ItemEffect[] {}),
	JAVELIN			("Javelin",			EquipType.SPEAR,		31,	0,	0,	0,	0,	0,	0,	0,	2,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	KAINS_LANCE		("Kain's Lance",	EquipType.SPEAR,		47,	0,	0,	0,	0,	0,	0,	1,	2,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LAVA_SPEAR		("Lava Spear",		EquipType.SPEAR,		33,	0,	0,	0,	0,	0,	0,	0,	2,	Element.FIRE,		0,	false,	new ItemEffect[] {}),
	MYTHRIL_SPEAR	("Mythril Spear",	EquipType.SPEAR,		32,	0,	0,	0,	0,	0,	0,	2,	2,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	ODIN_LANCE		("Odin Lance",		EquipType.SPEAR,		55,	0,	0,	0,	0,	0,	0,	0,	2,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	PARTISAN		("Partisan",		EquipType.SPEAR,		42,	0,	0,	0,	0,	0,	0,	1,	2,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	TRIDENT			("Trident",			EquipType.SPEAR,		50,	0,	2,	0,	0,	0,	0,	1,	2,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	
	AONA_FLUTE		("Aona Flute",		EquipType.INSTRUMENT,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_POISON}),
	BLACK_QUENA		("Black Quena",		EquipType.INSTRUMENT,	33,	2,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		0,	false,	new ItemEffect[] {}),
	BLOOD_STRINGS	("Blood Strings",	EquipType.INSTRUMENT,	22,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.DRAIN_HP}),
	CONCH_SHELL		("Conch Shell",		EquipType.INSTRUMENT,	31,	2,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	DARK_FIDDLE		("Dark Fiddle",		EquipType.INSTRUMENT,	45,	0,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		2,	true,	new ItemEffect[] {ItemEffect.NULL_SILENCE}),
	DEMON_BELL		("Demon Bell",		EquipType.INSTRUMENT,	22,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	EARTH_BELL		("Earth Bell",		EquipType.INSTRUMENT,	31,	3,	0,	0,	0,	0,	0,	0,	1,	Element.EARTH,		0,	true,	new ItemEffect[] {ItemEffect.ABSB_EARTH}),
	FAIRY_HARP		("Fairy Harp",		EquipType.INSTRUMENT,	29,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	FELL_CASTANETS	("Fell Castanets",	EquipType.INSTRUMENT,	47,	0,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		2,	true,	new ItemEffect[] {}),
	GLASS_BELL		("Glass Bell",		EquipType.INSTRUMENT,	25,	0,	0,	1,	0,	0,	0,	0,	1,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_SLEEP}),
	HEAL_CHIME		("Heal Chime",		EquipType.INSTRUMENT,	39,	0,	0,	0,	0,	0,	0,	1,	1,	Element.HOLY,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_BELL	("Mythril Bell",	EquipType.INSTRUMENT,	32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	SATYR_FLUTE		("Satyr Flute",		EquipType.INSTRUMENT,	35,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_CHARM}),
	WAR_TRUMPET		("War Trumpet",		EquipType.INSTRUMENT,	25,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	
	CAT_CLAWS		("Cat Claws",		EquipType.KNUCKLES,		35,	0,	0,	0,	2,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	DEATH_CLAWS		("Death Claws",		EquipType.KNUCKLES,		43,	0,	0,	0,	0,	0,	0,	1,	1,	Element.DARK,		1,	false,	new ItemEffect[] {}),
	DREAM_CLAWS		("Dream Claws",		EquipType.KNUCKLES,		39,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	GODHAND			("Godhand",			EquipType.KNUCKLES,		39,	0,	3,	0,	1,	0,	0,	5,	1,	Element.HOLY,		1,	false,	new ItemEffect[] {}),
	GREASEBURST		("Greaseburst",		EquipType.KNUCKLES,		59,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	HARD_KNUCKLES	("Hard Knuckles",	EquipType.KNUCKLES,		29,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	KAISER_KNUCKLES	("Kaiser Knuckles",	EquipType.KNUCKLES,		42,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	MAGIC_HANDS		("Magic Hands",		EquipType.KNUCKLES,		52,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MYTHRIL_CLAWS	("Mythril Claws",	EquipType.KNUCKLES,		32,	0,	0,	0,	0,	0,	1,	1,	1,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	RISING_SUN		("Rising Sun",		EquipType.KNUCKLES,		31,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SICK_KNUCKLES	("Sick Knuckles",	EquipType.KNUCKLES,		35,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	SURVIVOR		("Survivor",		EquipType.KNUCKLES,		29,	2,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	TIGER_FANGS		("Tiger Fangs",		EquipType.KNUCKLES,		41,	0,	0,	0,	0,	0,	0,	2,	1,	Element.LIGHTNING,	1,	false,	new ItemEffect[] {}),
	WHITE_FANGS		("White Fangs",		EquipType.KNUCKLES,		39,	0,	0,	0,	0,	0,	0,	1,	1,	Element.ICE,		1,	false,	new ItemEffect[] {}),
	
	BOMB_SOUL		("Bomb Soul",		EquipType.SOUL,			36,	0,	0,	0,	2,	0,	0,	0,	1,	Element.FIRE,		1,	true,	new ItemEffect[] {ItemEffect.HALF_FIRE}),
	BUG_SOUL		("Bug Soul",		EquipType.SOUL,			39,	2,	2,	0,	0,	0,	0,	0,	1,	Element.EARTH,		1,	true,	new ItemEffect[] {ItemEffect.NULL_BLIND}),
	DRAGON_SOUL		("Dragon Soul",		EquipType.SOUL,			43,	5,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_FIRE}),
	DREAD_SOUL		("Dread Soul",		EquipType.SOUL,			49,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	EYE_SOUL		("Eye Soul",		EquipType.SOUL,			45,	0,	2,	0,	0,	0,	0,	0,	1,	Element.DARK,		1,	true,	new ItemEffect[] {ItemEffect.NULL_DOOM}),
	FLAN_SOUL		("Flan Soul",		EquipType.SOUL,			34,	34,	10,	2,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1,	true,	new ItemEffect[] {ItemEffect.HALF_LIGHTNING}),
	GOBLIN_SOUL		("Goblin Soul",		EquipType.SOUL,			32,	0,	2,	0,	0,	0,	0,	1,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.HALF_ICE}),
	LAMIA_SOUL		("Lamia Soul",		EquipType.SOUL,			32,	0,	2,	0,	0,	0,	0,	0,	1,	Element.WATER,		1,	true,	new ItemEffect[] {ItemEffect.NULL_SLEEP}),
	MALBORO_SOUL	("Malboro Soul",	EquipType.SOUL,			47,	0,	2,	2,	0,	0,	0,	0,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_POISON}),
	MYTHRIL_SOUL	("Mythril Soul",	EquipType.SOUL,			32,	0,	2,	0,	0,	0,	1,	0,	1,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	PANTHER_SOUL	("Panther Soul",	EquipType.SOUL,			39,	0,	2,	0,	0,	0,	0,	2,	1,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_BERSERK}),
	RUKAVI_SOUL		("Rukavi Soul",		EquipType.SOUL,			67,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	
	AIOT_GUN		("Aiot Gun",		EquipType.GUN,			27,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	BINDSNIPE		("Bindsnipe",		EquipType.GUN,			47,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	CALLING_GUN		("Calling Gun",		EquipType.GUN,			59,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	CHAOS_RIFLE		("Chaos Rifle",		EquipType.GUN,			33,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	GIOT_GUN		("Giot Gun",		EquipType.GUN,			37,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LONGBARREL		("Longbarrel",		EquipType.GUN,			39,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LOST_GUN		("Lost Gun",		EquipType.GUN,			31,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MYTHRIL_GUN		("Mythril Gun",		EquipType.GUN,			32,	0,	0,	0,	0,	0,	1,	0,	8,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	OUTSIDER		("Outsider",		EquipType.GUN,			41,	0,	0,	0,	0,	0,	0,	0,	9,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	PEACEMAKER		("Peacemaker",		EquipType.GUN,			33,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	RIOT_GUN		("Riot Gun",		EquipType.GUN,			31,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	SILVER_CANNON	("Silver Cannon",	EquipType.GUN,			31,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	
	AEGIS_SHIELD	("Aegis Shield",	EquipType.SHIELD,		0,	5,	0,	5,	0,	0,	0,	10,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_PETRIFY}),
	BRONZE_SHIELD	("Bronze Shield",	EquipType.SHIELD,		0,	2,	0,	0,	0,	0,	0,	4,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	CHOCO_SHIELD	("Choco Shield",	EquipType.SHIELD,		0,	0,	0,	0,	0,	0,	0,	10,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	FLAME_SHIELD	("Flame Shield",	EquipType.SHIELD,		0,	6,	0,	0,	0,	0,	0,	9,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_FIRE,	ItemEffect.HALF_ICE,	ItemEffect.WEAK_WATER}),
	GENJI_SHIELD	("Genji Shield",	EquipType.SHIELD,		0,	10,	0,	7,	0,	0,	0,	10,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ICE_SHIELD		("Ice Shield",		EquipType.SHIELD,		0,	6,	0,	0,	0,	0,	0,	9,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_ICE,	ItemEffect.HALF_FIRE,	ItemEffect.WEAK_LIGHTNING}),
	LA_SERAPHICA	("La Seraphica",	EquipType.SHIELD,		0,	0,	0,	5,	0,	0,	0,	15,	0,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	OPAL_SHIELD		("Opal Shield",		EquipType.SHIELD,		0,	2,	0,	6,	0,	0,	0,	7,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	REVERIE_SHIELD	("Reverie Shield",	EquipType.SHIELD,		0,	5,	0,	10,	0,	0,	0,	10,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ROUND_SHIELD	("Round Shield",	EquipType.SHIELD,		0,	0,	0,	4,	0,	0,	0,	5,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	SACRI_SHIELD	("Sacri Shield",	EquipType.SHIELD,		0,	5,	0,	5,	0,	0,	0,	10,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.BARETTE}),
	SHIJIN_SHIELD	("Shijin Shield",	EquipType.SHIELD,		0,	0,	0,	10,	0,	0,	0,	7,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),

	BANGAA_HELM		("Bangaa Helm",		EquipType.HELM,			0,	16,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	BRONZE_HELM		("Bronze Helm",		EquipType.HELM,			0,	4,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	CROSS_HELM		("Cross Helm",		EquipType.HELM,			0,	9,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	DIAMOND_HELM	("Diamond Helm",	EquipType.HELM,			0,	11,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	GENJI_HELM		("Genji Helm",		EquipType.HELM,			0,	15,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	HANYA_HELM		("Hanya Helm",		EquipType.HELM,			0,	12,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	IRON_HELM		("Iron Helm",		EquipType.HELM,			0,	5,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	OPAL_HELM		("Opal Helm",		EquipType.HELM,			0,	7,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	PARADE_HELM		("Parade Helm",		EquipType.HELM,			0,	9,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	
	BARETTE			("Barette",			EquipType.RIBBON,		0,	2,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {ItemEffect.BARETTE}),
	CACHUSHA		("Cachusha",		EquipType.RIBBON,		0,	2,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {ItemEffect.CACHUSHA}),
	RIBBON			("Ribbon",			EquipType.RIBBON,		0,	2,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.RIBBON}),
	TIARA			("Tiara",			EquipType.RIBBON,		0,	8,	0,	20,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	
	ACACIA_HAT		("Acacia Hat",		EquipType.HAT,			0,	2,	5,	2,	2,	1,	1,	5,	0,	Element.NULL,		3,	true,	new ItemEffect[] {}),
	BLACK_HAT		("Black Hat",		EquipType.HAT,			0,	4,	4,	16,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	CIRCLET			("Circlet",			EquipType.HAT,			0,	3,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	FEATHER_CAP		("Feather Cap",		EquipType.HAT,			0,	2,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	GOLD_HAIRPIN	("Gold Hairpin",	EquipType.HAT,			0,	4,	2,	12,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_SILENCE}),
	GREEN_BERET		("Green Beret",		EquipType.HAT,			0,	2,	0,	2,	0,	0,	0,	2,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	HEADBAND		("Headband",		EquipType.HAT,			5,	6,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {}),
	THIEF_HAT		("Thief Hat",		EquipType.HAT,			0,	8,	0,	6,	0,	0,	0,	7,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_IMMOBILIZE,	ItemEffect.NULL_DISABLE}),
	WHITE_HAT		("White Hat",		EquipType.HAT,			0,	4,	0,	14,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	WIZARD_HAT		("Wizard Hat",		EquipType.HAT,			0,	3,	1,	10,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	
	ADAMAN_ARMOR	("Adaman Armor",	EquipType.ARMOR,		0,	58,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	BRONZE_ARMOR	("Bronze Armor",	EquipType.ARMOR,		0,	30,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	CARABINI_MAIL	("Carabini Mail",	EquipType.ARMOR,		0,	38,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	CUIRASS			("Cuirass",			EquipType.ARMOR,		0,	28,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	DIAMOND_ARMOR	("Diamond Armor",	EquipType.ARMOR,		0,	40,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	DRAGON_MAIL		("Dragon Mail",		EquipType.ARMOR,		0,	40,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.HALF_FIRE}),
	GENJI_ARMOR		("Genji Armor",		EquipType.ARMOR,		0,	46,	0,	12,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	GOLD_ARMOR		("Gold Armor",		EquipType.ARMOR,		0,	42,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	MATERIA_ARMOR	("Materia Armor",	EquipType.ARMOR,		0,	52,	0,	16,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MAXIMILLIAN		("Maximillian",		EquipType.ARMOR,		3,	46,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MIRROR_MAIL		("Mirror Mail",		EquipType.ARMOR,		0,	36,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.AUTO_REFLECT}),
	OPAL_ARMOR		("Opal Armor",		EquipType.ARMOR,		0,	42,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	PEYTRAL			("Peytral",			EquipType.ARMOR,		5,	28,	5,	2,	2,	1,	1,	5,	0,	Element.NULL,		3,	false,	new ItemEffect[] {}),
	PLATEMAIL		("Platemail",		EquipType.ARMOR,		0,	38,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	
	ADAMAN_VEST		("Adaman Vest",		EquipType.CLOTHING,		0,	30,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	BONE_PLATE		("Bone Plate",		EquipType.CLOTHING,		0,	42,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_DARK}),
	BRIGANDINE		("Brigandine",		EquipType.CLOTHING,		0,	37,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	BRINT_SET		("Brint Set",		EquipType.CLOTHING,		0,	28,	0,	16,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	CHAIN_PLATE		("Chain Plate",		EquipType.CLOTHING,		0,	28,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	DARK_GEAR		("Dark Gear",		EquipType.CLOTHING,		0,	32,	0,	3,	2,	0,	0,	1,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_STOP}),
	GAIA_GEAR		("Gaia Gear",		EquipType.CLOTHING,		0,	24,	0,	12,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_EARTH}),
	GALMIA_SET		("Galmia Set",		EquipType.CLOTHING,		0,	26,	0,	18,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	JUDGE_COAT		("Judge Coat",		EquipType.CLOTHING,		0,	38,	0,	28,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	JUDO_UNIFORM	("Judo Uniform",	EquipType.CLOTHING,		0,	34,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_DOOM}),
	LEATHER_GARB	("Leather Garb",	EquipType.CLOTHING,		0,	18,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	MIRAGE_VEST		("Mirage Vest",		EquipType.CLOTHING,		0,	32,	0,	16,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_KO}),
	NINJA_GEAR		("Ninja Gear",		EquipType.CLOTHING,		0,	30,	0,	6,	1,	0,	0,	2,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	ONLYONE			("Onlyone",			EquipType.CLOTHING,		0,	34,	0,	24,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	POWER_SASH		("Power Sash",		EquipType.CLOTHING,		2,	34,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	SURVIVAL_VEST	("Survival Vest",	EquipType.CLOTHING,		0,	34,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	TEMPLE_CLOTH	("Temple Cloth",	EquipType.CLOTHING,		2,	36,	2,	16,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	WYGAR			("Wygar",			EquipType.CLOTHING,		0,	35,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {ItemEffect.NULL_KO}),
	
	MINERVA_PLATE	("Minerva Plate",	EquipType.VIERA_CLOTHES,0,	24,	0,	14,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_DARK}),
	RUBBER_SUIT		("Rubber Suit",		EquipType.VIERA_CLOTHES,0,	24,	0,	14,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_LIGHTNING}),
	
	BLACK_ROBE		("Black Robe",		EquipType.ROBE,			0,	21,	2,	36,	0,	0,	0,	0,	0,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.ENHN_FIRE,	ItemEffect.ENHN_LIGHTNING,	ItemEffect.ENHN_ICE}),
	BLAZE_ROBE		("Blaze Robe",		EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_FIRE}),
	FLURRY_ROBE		("Flurry Robe",		EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_ICE}),
	HEMPEN_ROBE		("Hempen Robe",		EquipType.ROBE,			0,	15,	0,	22,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	LIGHT_ROBE		("Light Robe",		EquipType.ROBE,			0,	25,	0,	40,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	LORDLY_ROBE		("Lordly Robe",		EquipType.ROBE,			0,	28,	0,	42,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MAGIC_ROBE		("Magic Robe",		EquipType.ROBE,			0,	24,	6,	36,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	MAGUS_ROBE		("Magus Robe",		EquipType.ROBE,			0,	15,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	MISTLE_ROBE		("Mistle Robe",		EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_KO,	ItemEffect.ABSB_HOLY}),
	REAPER_CLOAK	("Reaper Cloak",	EquipType.ROBE,			0,	32,	0,	36,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	RED_ROBE		("Red Robe",		EquipType.ROBE,			0,	22,	0,	31,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	SAGE_ROBE		("Sage Robe",		EquipType.ROBE,			0,	24,	0,	52,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	SILKEN_ROBE		("Silken Robe",		EquipType.ROBE,			0,	15,	0,	28,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	SILVER_COAT		("Silver Coat",		EquipType.ROBE,			0,	30,	0,	38,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	THUNDER_ROBE	("Thunder Robe",	EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.ABSB_LIGHTNING}),
	WHITE_ROBE		("White Robe",		EquipType.ROBE,			0,	21,	0,	38,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.HALF_FIRE,	ItemEffect.HALF_LIGHTNING,	ItemEffect.HALF_ICE}),
	
	BATTLE_BOOTS	("Battle Boots",	EquipType.BOOTS,		0,	7,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	CALIGULA		("Caligula",		EquipType.BOOTS,		3,	7,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	DASH_BOOTS		("Dash Boots",		EquipType.BOOTS,		0,	2,	0,	0,	0,	1,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {}),
	FAIRY_SHOES		("Fairy Shoes",		EquipType.BOOTS,		0,	3,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {}),
	FEATHER_BOOTS	("Feather Boots",	EquipType.BOOTS,		0,	3,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	GALMIA_SHOES	("Galmia Shoes",	EquipType.BOOTS,		0,	3,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	GERMINAS		("Germinas",		EquipType.BOOTS,		0,	3,	0,	0,	0,	0,	2,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	NINJA_TABI		("Ninja Tabi",		EquipType.BOOTS,		0,	3,	0,	0,	0,	2,	0,	0,	0,	Element.NULL,		2,	true,	new ItemEffect[] {}),
	RED_BOOTS		("Red Boots",		EquipType.BOOTS,		0,	3,	0,	5,	0,	0,	0,	2,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	SPIKED_BOOTS	("Spiked Boots",	EquipType.BOOTS,		0,	4,	0,	0,	0,	0,	1,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	
	BONE_ARMLETS	("Bone Armlets",	EquipType.GLOVES,		3,	6,	0,	8,	0,	0,	0,	5,	0,	Element.NULL,		2,	false,	new ItemEffect[] {}),
	BRACERS			("Bracers",			EquipType.GLOVES,		5,	12,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {}),
	FIRE_MITTS		("Fire Mitts",		EquipType.GLOVES,		0,	8,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		2,	true,	new ItemEffect[] {ItemEffect.NULL_FIRE}),
	GAUNTLETS		("Gauntlets",		EquipType.GLOVES,		5,	5,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	GENJI_ARMLETS	("Genji Armlets",	EquipType.GLOVES,		5,	10,	2,	5,	0,	0,	0,	0,	0,	Element.NULL,		0,	false,	new ItemEffect[] {}),
	THIEF_ARMLETS	("Thief Armlets",	EquipType.GLOVES,		3,	3,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.IMPROVE_STEAL}),
	
	ANGEL_RING		("Angel Ring",		EquipType.ACCESSORY,	0,	0,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.BARETTE}),
	FORTUNE_RING	("Fortune Ring",	EquipType.ACCESSORY,	0,	3,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_SLEEP,	ItemEffect.NULL_DOOM}),
	MAGIC_RING		("Magic Ring",		EquipType.ACCESSORY,	0,	3,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		1,	false,	new ItemEffect[] {}),
	MINDU_GEM		("Mindu Gem",		EquipType.ACCESSORY,	0,	3,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.MINDU_GEM,	ItemEffect.HALF_LIGHTNING}),
	RUBY_EARRING	("Ruby Earring",	EquipType.ACCESSORY,	0,	3,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		1,	true,	new ItemEffect[] {ItemEffect.NULL_CONFUSION,	ItemEffect.NULL_CHARM,	ItemEffect.HALF_DARK}),
	SCARAB			("Scarab",			EquipType.ACCESSORY,	0,	2,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_IMMOBILIZE,	ItemEffect.NULL_DISABLE}),
	STAR_ARMLET		("Star Armlet",		EquipType.ACCESSORY,	0,	4,	6,	4,	2,	0,	0,	0,	0,	Element.NULL,		0,	true,	new ItemEffect[] {ItemEffect.NULL_STOP,	ItemEffect.NULL_SLOW});
	
	
	final static FFTAEquip[] SWORD_LIST 		= { BLOOD_SWORD, BURGLAR_SWORD, BUSTER_SWORD, CHIRIJIRADEN, GALE_SWORD, LAGLACE_SWORD, MYTHRIL_SWORD, ONION_SWORD, RESTORER, SHORTSWORD, SILVER_SWORD, VICTOR_SWORD, VITANOVA };
	final static FFTAEquip[] BLADE_LIST 		= { ADAMAN_BLADE, AIR_BLADE, ATMOS_BLADE, AYVUIR_BLUE, AYVUIR_RED, EBON_BLADE, FLAMETONGUE, ICEBRAND, KWIGON_BLADE, MATERIA_BLADE, MYTHRIL_BLADE, OGUN_BLADE, PARAIBA_BLADE, PEARL_BLADE, SHADOW_BLADE, SUN_BLADE, SWEEP_BLADE, VENUS_BLADE};
	final static FFTAEquip[] SABER_LIST 		= { AQUA_SABER, BLUE_SABER, HARPE, MANGANESE, MYTHRIL_SABER, SHAMSHIR, SOUL_SABER, TULWAR };
	final static FFTAEquip[] KNIGHTSWORD_LIST 	= { APOCALYPSE, ARCH_SWORD, DEFENDER, EXCALIBUR, EXCALIBUR2, LIONHEART, LOHENGRIN, MYTHRIL_BRAND, NAGRAROK, RAGNAROK, SAVETHEQUEEN, SEQUENCE };
	final static FFTAEquip[] GREATSWORD_LIST 	= { ANCIENT_SWORD, BARONG, DIAMOND_SWORD, HARDEDGE, ICEPRISM, LUREBREAKER, MASTER_SWORD, OBLIGE, VIGILANTE, ZANKPLUS };
	final static FFTAEquip[] BROADSWORD_LIST 	= { BEASTSWORD, CLAYMORE, ECLIPSE, EL_CID_SWORD, ESTRELEDGE, FALCHION, PREDATOR, RHOMPHAIA, SAMSON_SWORD, STRIBORG, TABARISE, VAJRA };
	final static FFTAEquip[] KNIFE_LIST 		= { CINQUEDEA, JACK_KNIFE, JAMBIYA, KARD, KHUKURI, KRIS_KNIFE, MYTHRIL_KNIFE, ORICHALCUM, RONDELL_DAGGER, SCRAMASAX, SWORD_BREAKER, TIPTAPTWO, TONBERRIAN, ZORLIN_SHAPE };
	final static FFTAEquip[] RAPIER_LIST 		= { AERIAL_HOLE, COLICHEMARDE, DIABOLIQUE, DJINN_FLYSSA, EPEPRISM, ESTOC, FEMME_FATALE, FLAMBERGE, FLEURET, GUPTI_AGA, JOYEUSE, LAST_LETTER, MADU, MAGE_MASHER, MYTHRIL_RAPIER, SCARLETTE, SILVER_RAPIER, STINGER };
	final static FFTAEquip[] KATANA_LIST 		= { ASHURA, CHARFIRE, HEAVENS_CLOUD, KIKUICHIMONJI, KOTETSU, MASAMUNE, MASAMUNE_100, MURASAME, MYTHRIL_EPEE, NINJA_KNIFE, NOSADA, OSAFUNE, PETALCHASER, SILKMOON, ZANMATO };
	final static FFTAEquip[] STAFF_LIST 		= { BLESS_STAFF, CHEER_STAFF, CURE_STAFF, DREAM_WATCHER, GARNET_STAFF, GUARD_STAFF, JUDGE_STAFF, MYTHRIL_STAFF, NIRVANA_STAFF, POWER_STAFF, PURE_STAFF, SNAKE_STAFF, SPRING_STAFF, WHITE_STAFF };
	final static FFTAEquip[] ROD_LIST 			= { CHILL_ROD, FIREWHEEL_ROD, FLAME_ROD, FORCE_ROD, HERETIC_ROD, MYTHRIL_ROD, PRINCESS_ROD, ROD, SAPERE_AUDE, STARDUST_ROD, TERRE_ROD, THOR_ROD, THUNDER_ROD};
	final static FFTAEquip[] MACE_LIST 			= { BATTLE_MACE, CACTUS_STICK, DRUID_MACE, ENERGY_MACE, LIFE_CROSIER, LOTUS_MACE, MANDRAGORA, MORNING_STAR, MYTHRIL_MACE, SAGE_CROSIER, SCORPION_TAIL, VESPER, ZEUS_MACE};
	final static FFTAEquip[] BOW_LIST 			= { ARTEMIS_BOW, CHAR_BOW, CRESCENT_BOW, LONGBOW, MALBOW, MYTHRIL_BOW, NAIL_BOW, PERSEUS_BOW, SILVER_BOW, TARGET_BOW, THORN_BOW, YOICHI_BOW };
	final static FFTAEquip[] GREATBOW_LIST 		= { ARBALEST, CRANEQUIN, FEY_BOW, GASTRA_BOW, HADES_BOW, MASTER_BOW, MARDUK_BOW, MASTER_BOW, MAXS_OATHBOW, MYTHRIL_SHOT, NIKE_BOW, RANGER_BOW, SEVENTH_HEAVEN, TWIN_BOW, WINDSLASH_BOW };	
	final static FFTAEquip[] SPEAR_LIST 		= { BANGAA_SPIKE, BEASTSPEAR, DRAGON_WHISKER, GAE_BOLG, ICE_LANCE, JAVELIN, KAINS_LANCE, LAVA_SPEAR, MYTHRIL_SPEAR, ODIN_LANCE, PARTISAN, TRIDENT};
	final static FFTAEquip[] INSTRUMENT_LIST 	= { AONA_FLUTE, BLACK_QUENA, BLOOD_STRINGS, CONCH_SHELL, DARK_FIDDLE, DEMON_BELL, EARTH_BELL, FAIRY_HARP, FELL_CASTANETS, GLASS_BELL, HEAL_CHIME, MYTHRIL_BELL, SATYR_FLUTE, WAR_TRUMPET };
	final static FFTAEquip[] KNUCKLES_LIST 		= { CAT_CLAWS, DEATH_CLAWS, DREAM_CLAWS, GODHAND, GREASEBURST, HARD_KNUCKLES, KAISER_KNUCKLES, MAGIC_HANDS, MYTHRIL_CLAWS, SICK_KNUCKLES, SURVIVOR, TIGER_FANGS, WHITE_FANGS };
	final static FFTAEquip[] SOUL_LIST 			= { BOMB_SOUL, BUG_SOUL, DRAGON_SOUL, DREAD_SOUL, EYE_SOUL, FLAN_SOUL, GOBLIN_SOUL, LAMIA_SOUL, MALBORO_SOUL, MYTHRIL_SOUL, PANTHER_SOUL, RUKAVI_SOUL };
	final static FFTAEquip[] GUN_LIST 			= { AIOT_GUN, BINDSNIPE, CALLING_GUN, CHAOS_RIFLE, GIOT_GUN, LONGBARREL, LOST_GUN, MYTHRIL_GUN, OUTSIDER, PEACEMAKER, RIOT_GUN, SILVER_CANNON };
	final static FFTAEquip[] SHIELD_LIST 		= { AEGIS_SHIELD, BRONZE_SHIELD, CHOCO_SHIELD, FLAME_SHIELD, GENJI_SHIELD, ICE_SHIELD, LA_SERAPHICA, OPAL_SHIELD, REVERIE_SHIELD, ROUND_SHIELD, SACRI_SHIELD, SHIJIN_SHIELD };
	final static FFTAEquip[] HELM_LIST 			= { BANGAA_HELM, BRONZE_HELM, CROSS_HELM, DIAMOND_HELM, GENJI_HELM, HANYA_HELM, IRON_HELM, OPAL_HELM, PARADE_HELM };
	final static FFTAEquip[] RIBBON_LIST 		= { BARETTE, CACHUSHA, RIBBON, TIARA };
	final static FFTAEquip[] HAT_LIST 			= { ACACIA_HAT, BLACK_HAT, CIRCLET, FEATHER_CAP, GOLD_HAIRPIN, GREEN_BERET, HEADBAND, THIEF_HAT, WHITE_HAT, WIZARD_HAT };
	final static FFTAEquip[] ARMOR_LIST 		= { ADAMAN_ARMOR, BRONZE_ARMOR, CARABINI_MAIL, CUIRASS, DIAMOND_ARMOR, DRAGON_MAIL, GENJI_ARMOR, GOLD_ARMOR, MATERIA_ARMOR, MAXIMILLIAN, MIRROR_MAIL, OPAL_ARMOR, PEYTRAL, PLATEMAIL };
	final static FFTAEquip[] CLOTHING_LIST 		= { ADAMAN_VEST, BONE_PLATE, BRIGANDINE, BRINT_SET, CHAIN_PLATE, DARK_GEAR, GAIA_GEAR, GALMIA_SET, JUDGE_COAT, JUDO_UNIFORM, LEATHER_GARB, MIRAGE_VEST, NINJA_GEAR, ONLYONE, POWER_SASH, SURVIVAL_VEST, TEMPLE_CLOTH, WYGAR };
	final static FFTAEquip[] VIERA_CLOTHES_LIST = { MINERVA_PLATE, RUBBER_SUIT };
	final static FFTAEquip[] ROBES_LIST 		= { BLACK_ROBE, BLAZE_ROBE, FLURRY_ROBE, HEMPEN_ROBE, LIGHT_ROBE, LORDLY_ROBE, MAGIC_ROBE, MAGUS_ROBE, MISTLE_ROBE, REAPER_CLOAK, RED_ROBE, SAGE_ROBE, SILKEN_ROBE, SILVER_COAT, THUNDER_ROBE, WHITE_ROBE };
	final static FFTAEquip[] BOOTS_LIST 		= { BATTLE_BOOTS, CALIGULA, DASH_BOOTS, FAIRY_SHOES, FEATHER_BOOTS, GALMIA_SHOES, GERMINAS, NINJA_TABI, RED_BOOTS, SPIKED_BOOTS };
	final static FFTAEquip[] GLOVES_LIST 		= { BONE_ARMLETS, BRACERS, FIRE_MITTS, GAUNTLETS, GENJI_ARMLETS, THIEF_ARMLETS };
	final static FFTAEquip[] ACCESSORY_LIST 	= { ANGEL_RING, FORTUNE_RING, MAGIC_RING, MINDU_GEM, RUBY_EARRING, SCARAB, STAR_ARMLET };
	
	public final static FFTAEquip[][] EQUIP_LIST = {SWORD_LIST, BLADE_LIST, SABER_LIST, KNIGHTSWORD_LIST, GREATSWORD_LIST, BROADSWORD_LIST, KNIFE_LIST, RAPIER_LIST,
			KATANA_LIST, STAFF_LIST, ROD_LIST, MACE_LIST, BOW_LIST, GREATBOW_LIST, SPEAR_LIST, INSTRUMENT_LIST, KNUCKLES_LIST, SOUL_LIST, GUN_LIST, 
			SHIELD_LIST, HELM_LIST, RIBBON_LIST, HAT_LIST, ARMOR_LIST, CLOTHING_LIST, VIERA_CLOTHES_LIST, ROBES_LIST, BOOTS_LIST, GLOVES_LIST, ACCESSORY_LIST};
	
	
	public final String name;
	public final EquipType type;
	
	public final int wAtk, wDef, mPow, mRes, speed, move, jump, evade;
	public final int range;
	public final Element element;
	public final int availability;	// 0 = buyable, 1 = infinite unbuyable, 2 = finite unbuyable, 3 = untradeable
	public final boolean notable;
	public final ImageIcon icon;
	public final ItemEffect[] effects;
	
	FFTAEquip(String name, EquipType type, int wAtk, int wDef, int mPow, int mRes, int speed, int move, int jump, int evade, int range,
			Element element, int availability, boolean notable, ItemEffect[] effects)
	{	
		this.name = name;
		this.type = type;
		this.wAtk = wAtk;
		this.wDef = wDef;
		this.mPow = mPow;
		this.mRes = mRes;
		this.speed = speed;
		this.move = move;
		this.jump = jump;
		this.evade = evade;
		this.range = range;
		this.element = element;
		this.availability = availability;
		this.notable = notable;
		this.icon = new ImageIcon("resources/equipment/" + name() + ".png");
		this.effects = effects;
	}
	
	public boolean isWeapon()
	{
		return type.ordinal() <= EquipType.GUN.ordinal();
	}
		
	public boolean isShield()
	{
		return type.ordinal() == EquipType.SHIELD.ordinal();
	}
	
	public boolean isHeadwear()
	{
		return type.ordinal() >= EquipType.HELM.ordinal() && 
			   type.ordinal() <= EquipType.HAT.ordinal();
	}
	
	public boolean isArmor()
	{
		return type.ordinal() >= EquipType.ARMOR.ordinal() &&
			   type.ordinal() <= EquipType.ROBE.ordinal();
	}
	
	public boolean isStealableAccessory()
	{
		return type.ordinal() == EquipType.GLOVES.ordinal() || 
			   type.ordinal() == EquipType.ACCESSORY.ordinal();
	}
	
	public String getEffectString()
	{
		StringBuilder effect = new StringBuilder();
		effect.append("<html>");
		if (wAtk > 0)	effect.append("WAtk+" + wAtk + ", ");
		if (wDef > 0)	effect.append("WDef+" + wDef + ", ");
		if (mPow > 0)	effect.append("MPow+" + mPow + ", ");
		if (mRes > 0)	effect.append("MRes+" + mRes + ", ");
		if (speed > 0)	effect.append("Speed+" + speed + ", ");
		if (jump > 0)	effect.append("Jump+" + jump + ", ");
		if (evade > 0)	effect.append("Evade+" + evade + ", ");
		if (range > 1)	effect.append("Range " + range + ", ");
		
		if (effect.length() >= 7)
		{
			effect.delete(effect.length() - 2, effect.length());
			effect.append(". ");
		}
		
		StringBuilder effect2 = new StringBuilder();
		if (element != Element.NULL)
			effect.append(element + "-elemental. ");
		switch (this)
		{
			case NONE			: effect2.append("Empty slot."); break;
			case BLOOD_SWORD	: effect2.append("Drains target's HP."); break;
			case VITANOVA		: effect2.append("Absorbs Holy."); break;
			case AIR_BLADE		: effect2.append("Absorbs Wind."); break;
			case VENUS_BLADE	: effect2.append("Absorbs Fire and halves Water."); break;
			case EXCALIBUR		: effect2.append("Enhances and nullifies Holy."); break;
			case NAGRAROK		: effect2.append("Move+1."); break;
			case SAVETHEQUEEN	: effect2.append("Enhances Holy."); break;
			case SEQUENCE		: effect2.append("Move+1. Growth item."); break;
			case ANCIENT_SWORD	: effect2.append("Nullifies Stone."); break;
			case DIAMOND_SWORD	: effect2.append("Nullifies Slow."); break;
			case HARDEDGE		: effect2.append("Nullifies Doom."); break;
			case ICEPRISM		: effect2.append("Nullifies Silence and Fire."); break;
			case LUREBREAKER	: effect2.append("Nullifies Sleep."); break;
			case MASTER_SWORD	: effect2.append("Nullifies KO."); break;
			case OBLIGE			: effect2.append("Nullifies Charm."); break;
			case VIGILANTE		: effect2.append("Nullifies Confusion."); break;
			case ZANKPLUS		: effect2.append("Nullifies Poison."); break;
			case VAJRA			: effect2.append("Enhances Lightning."); break;
			case COLICHEMARDE	: effect2.append("Nullifies Berserk."); break;
			case DIABOLIQUE		: effect2.append("Nullifies Dark."); break;
			case DJINN_FLYSSA	: effect2.append("Enhances and nullifies Wind."); break;
			case EPEPRISM		: effect2.append("Halves Holy and Dark."); break;
			case FEMME_FATALE	: effect2.append("Nullifies Doom."); break;
			case HEAVENS_CLOUD	: effect2.append("Absorbs Holy."); break;
			case ZANMATO		: effect2.append("Enhances Holy and halves Dark."); break;
			case WHITE_STAFF	: effect2.append("Removes Doom on hit."); break;
			case CURE_STAFF		: effect2.append("Heal's target's HP."); break;
			case SNAKE_STAFF	: effect2.append("Nullifies Stone."); break;
			case SPRING_STAFF	: effect2.append("Nullifies Water."); break;
			case CHILL_ROD		: effect2.append("Enhances Ice."); break;
			case FLAME_ROD		: effect2.append("Enhances Fire."); break;
			case PRINCESS_ROD	: effect2.append("Enhances Wind, Water, and Earth."); break;
			case SAPERE_AUDE	: effect2.append("Move+1. Growth item."); break;
			case THOR_ROD		: effect2.append("Enhances Lightning."); break;
			case LOTUS_MACE		: effect2.append("Enhances Fire."); break;
			case MANDRAGORA		: effect2.append("Absorbs Earth and nullifies Poison."); break;
			case ZEUS_MACE		: effect2.append("Enhances Holy."); break;
			case AONA_FLUTE		: effect2.append("Nullifies Poison."); break;
			case BLOOD_STRINGS	: effect2.append("Drains target's HP."); break;
			case DARK_FIDDLE	: effect2.append("Nullifies Silence."); break;
			case GLASS_BELL		: effect2.append("Nullifies Sleep."); break;
			case HEAL_CHIME		: effect2.append("Nullifies Doom."); break;
			case SATYR_FLUTE	: effect2.append("Nullifies Charm."); break;
			case BOMB_SOUL		: effect2.append("Halves Fire."); break;
			case BUG_SOUL		: effect2.append("Nullifies Dark."); break;
			case DRAGON_SOUL	: effect2.append("Absorbs Earth."); break;
			case EYE_SOUL		: effect2.append("Nullifies Doom."); break;
			case FLAN_SOUL		: effect2.append("Halves Lightning."); break;
			case GOBLIN_SOUL	: effect2.append("Halves Ice."); break;
			case MALBORO_SOUL	: effect2.append("Nullifies Poison."); break;
			case PANTHER_SOUL	: effect2.append("Nullifies Berserk."); break;
			case AEGIS_SHIELD	: effect2.append("Nullifies Petrify."); break;
			case FLAME_SHIELD	: effect2.append("Absorbs Fire, Halves Ice. Weak against Water."); break;
			case ICE_SHIELD		: effect2.append("Absorbs Ice, Halves Fire. Weak against Lightning."); break;
			case SACRI_SHIELD	: effect2.append("Nullifies Zombie, Darkness, Silence, Frog, Poison, Slow, Immobilize, Disable, and Doom."); break;
			case RIBBON			: effect2.append("Nullifies most debuffs."); break;
			case CACHUSHA		: effect2.append("Nullifies KO, Stone, Confuse, Berserk, Stop, Charm, and Sleep."); break;
			case BARETTE		: effect2.append("Nullifies Zombie, Darkness, Silence, Frog, Poison, Slow, Immobilize, Disable, and Doom."); break;
			
			case DASH_BOOTS		: effect2.append("Move+1."); break;
			case FEATHER_BOOTS	: effect2.append("Waterwalking."); break;
			case FAIRY_SHOES	: effect2.append("Teleportation."); break;
			case GALMIA_SHOES	: effect2.append("Ignore elevation."); break;
			case NINJA_TABI		: effect2.append("Move +2."); break;
			case FIRE_MITTS		: effect2.append("Nullifies Fire."); break;
			case THIEF_ARMLETS	: effect2.append("Improves chance to steal."); break;
			case ANGEL_RING		: effect2.append("Auto-Raise. Nullifies Zombie, Darkness, Silence, Frog, Poison, Slow, Immobilize, Disable, and Doom."); break;
			case FORTUNE_RING	: effect2.append("Nullifies Sleep and Doom."); break;
			case MINDU_GEM		: effect2.append("Nullifies Stone, Frog, Poison, Darkness, and Silence. Halves Thunder."); break;
			case RUBY_EARRING	: effect2.append("Nullifies Confuse and Charm. Halves Dark."); break;
			case SCARAB			: effect2.append("Nullifies Immobilize, Disable, and Charm."); break;
			case STAR_ARMLET	: effect2.append("Nullifies Stop and Slow."); break;
			case GOLD_HAIRPIN	: effect2.append("Nullifies Silence."); break;
			case THIEF_HAT		: effect2.append("Nullifies Immobilize and Disable."); break;
			case DRAGON_MAIL	: effect2.append("Halves Fire."); break;
			case MIRROR_MAIL	: effect2.append("Auto-Reflect."); break;
			case BONE_PLATE		: effect2.append("Absorbs Dark."); break;
			case DARK_GEAR		: effect2.append("Nullifies Stop."); break;
			case JUDO_UNIFORM	: effect2.append("Nullifies Doom."); break;
			case MINERVA_PLATE	: effect2.append("Nullifies Dark."); break;
			case PEYTRAL		: effect2.append("Move+1. Growth item."); break;
			case ACACIA_HAT		: effect2.append("Move+1. Growth item."); break;
			case MIRAGE_VEST	: effect2.append("Nullifies KO."); break;
			case RUBBER_SUIT	: effect2.append("Nullifies Lightning."); break;
			case WYGAR			: effect2.append("Nullifies KO."); break;
			case BLACK_ROBE		: effect2.append("Enhances Fire, Lightning, and Ice."); break;
			case BLAZE_ROBE		: effect2.append("Absorbs Fire."); break;
			case FLURRY_ROBE	: effect2.append("Absorbs Ice."); break;
			case MISTLE_ROBE	: effect2.append("Absorbs Holy and nullifies KO."); break;
			case THUNDER_ROBE	: effect2.append("Absorbs Lightning."); break;
			case WHITE_ROBE		: effect2.append("Halves Fire, Lightning, and Ice."); break;
			default 	: break;

		}
		if (this != FFTAEquip.NONE)
			effect2.append("<br>Flavor text will go here, eventually.");
		
		return effect.toString() + effect2.toString();
	}
	
	public boolean isTwoHanded()
	{
		return (type == EquipType.GREATSWORD || type == EquipType.BROADSWORD || type == EquipType.BOW || type == EquipType.GREATBOW);
	}

	public String toString()
	{
		return name;
	}
}

enum ItemEffect
{
	NONE(""),
	ABSB_FIRE("[absb] Fire"), NULL_FIRE("[null] Fire"), HALF_FIRE("[half] Fire"), WEAK_FIRE("[weak] Fire"), ENHN_FIRE("[enhn] Fire"),
	ABSB_ICE("[absb] Ice"), NULL_ICE("[null] Ice"), HALF_ICE("[half] Ice"), WEAK_ICE("[weak] Ice"), ENHN_ICE("[enhn] Ice"),
	ABSB_LIGHTNING("[absb] Lightning"), NULL_LIGHTNING("[null] Lightning"), HALF_LIGHTNING("[half] Lightning"), WEAK_LIGHTNING("[weak] Lightning"), ENHN_LIGHTNING("[enhn] Lightning"),
	ABSB_WIND("[absb] Wind"), NULL_WIND("[null] Wind"), HALF_WIND("[half] Wind"), WEAK_WIND("[weak] Wind"), ENHN_WIND("[enhn] Wind"),
	ABSB_EARTH("[absb] Earth"), NULL_EARTH("[null] Earth"), HALF_EARTH("[half] Earth"), WEAK_EARTH("[weak] Earth"), ENHN_EARTH("[enhn] Earth"),
	ABSB_WATER("[absb] Water"), NULL_WATER("[null] Water"), HALF_WATER("[half] Water"), WEAK_WATER("[weak] Water"), ENHN_WATER("[enhn] Water"),
	ABSB_HOLY("[absb] Holy"), NULL_HOLY("[null] Holy"), HALF_HOLY("[half] Holy"), WEAK_HOLY("[weak] Holy"), ENHN_HOLY("[enhn] Holy"),
	ABSB_DARK("[absb] Dark"), NULL_DARK("[null] Dark"), HALF_DARK("[half] Dark"), WEAK_DARK("[weak] Dark"), ENHN_DARK("[enhn] Dark"),
	
	NULL_FROG("[null] Frog"), NULL_STOP("[null] Stop"), NULL_SLOW("[null] Slow"), NULL_CHARM("[null] Charm"),
	NULL_IMMOBILIZE("[null] Immobilize"), NULL_DISABLE("[null] Disable"), NULL_BERSERK("[null] Berserk"),
	NULL_BLIND("[null] Blind"), NULL_CONFUSION("[null] Confusion"), NULL_DOOM("[null] Doom"), NULL_SLEEP("[null] Sleep"),
	NULL_PETRIFY("[null] Petrify"), NULL_KO("[null] "), NULL_SILENCE("[null] Silence"), NULL_POISON("[null] Poison"),
	RIBBON("[null] All (almost)"), CACHUSHA("[null] KO/Stone/Confuse/Berserk/Stop/Charm/Sleep"),
	BARETTE("[null] Zombie/Darkness/Silence/Frog/Poison/Slow/Immobilize/Disable/Doom"), MINDU_GEM("[null] Petrify/Frog/Confuse/Poison/Blind/Silence"),
	
	IMPROVE_STEAL("Improves chance to steal"), DRAIN_HP("Drains target's HP"), REMOVE_DOOM("Removes \"doom\" from target"),
	HEAL_HP("Heal's the target's HP"), AUTO_RAISE("Auto-Raise"), AUTO_REFLECT("Auto-Reflect");
	
	final static ItemEffect[][] elemEffs = { {ABSB_FIRE, NULL_FIRE, HALF_FIRE, WEAK_FIRE},
											 {ABSB_LIGHTNING, NULL_LIGHTNING, HALF_LIGHTNING, WEAK_LIGHTNING},
											 {ABSB_ICE, NULL_ICE, HALF_ICE, WEAK_ICE},
											 {ABSB_WIND, NULL_WIND, HALF_WIND, WEAK_WIND},
											 {ABSB_EARTH, NULL_EARTH, HALF_EARTH, WEAK_EARTH},
											 {ABSB_WATER, NULL_WATER, HALF_WATER, WEAK_WATER},
											 {ABSB_HOLY, NULL_HOLY, HALF_HOLY, WEAK_HOLY},
											 {ABSB_DARK, NULL_DARK, HALF_DARK, WEAK_DARK}};
	
	final static ItemEffect[] enhnEffs = { ENHN_FIRE, ENHN_LIGHTNING, ENHN_ICE, ENHN_WIND,
										   ENHN_EARTH, ENHN_WATER, ENHN_HOLY, ENHN_DARK };
	
	
	String desc;
	
	ItemEffect(String desc)
	{
		this.desc = desc;
	}
}


