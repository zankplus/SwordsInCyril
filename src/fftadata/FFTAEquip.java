package fftadata;
import java.io.Serializable;

import javax.swing.ImageIcon;

public enum FFTAEquip implements Serializable
{
	NONE			(" -",				null,					0,	0,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0),
	
	BLOOD_SWORD		("Blood Sword",		EquipType.SWORD,		18,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	BURGLAR_SWORD	("Burglar Sword",	EquipType.SWORD,		39,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	BUSTER_SWORD	("Buster Sword",	EquipType.SWORD,		35,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	CHIRIJIRADEN	("Chirijiraden",	EquipType.SWORD,		65,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	GALE_SWORD		("Gale Sword",		EquipType.SWORD,		36,	0,	0,	0,	1,	0,	0,	0,	1,	Element.WIND,		1),
	LAGLACE_SWORD	("Laglace Sword",	EquipType.SWORD,		41,	0,	5,	0,	0,	0,	0,	0,	1,	Element.ICE,		2),
	MYTHRIL_SWORD	("Mythril Sword",	EquipType.SWORD,		33,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	ONION_SWORD		("Onion Sword",		EquipType.SWORD,		29,	5,	0,	5,	0,	0,	0,	10,	1,	Element.NULL,		2),
	RESTORER		("Restorer",		EquipType.SWORD,		40,	0,	5,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SHORTSWORD		("Shortsword",		EquipType.SWORD,		25,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SILVER_SWORD	("Silver Sword",	EquipType.SWORD,		30,	0,	0,	0,	2,	0,	0,	2,	1,	Element.NULL,		1),
	VICTOR_SWORD	("Victor Sword",	EquipType.SWORD,		33,	10,	0,	10,	0,	0,	0,	0,	1,	Element.NULL,		1),
	VITANOVA		("Vitanova",		EquipType.SWORD,		38,	0,	2,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	
	ADAMAN_BLADE	("Adaman Blade",	EquipType.BLADE,		65,	15,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	AIR_BLADE		("Air Blade",		EquipType.BLADE,		40,	0,	0,	0,	0,	0,	0,	0,	1,	Element.WIND,		1),
	ATMOS_BLADE		("Atmos Blade",		EquipType.BLADE,		36,	0,	0,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1),
	AYVUIR_BLUE		("Ayvuir Blue",		EquipType.BLADE,		51,	0,	0,	10,	0,	0,	0,	2,	1,	Element.NULL,		2),
	AYVUIR_RED		("Ayvuir Red",		EquipType.BLADE,		65,	10,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2),
	EBON_BLADE		("Ebon Blade",		EquipType.BLADE,		84,	5,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		2),
	FLAMETONGUE		("Flametongue",		EquipType.BLADE,		38,	0,	0,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1),
	ICEBRAND		("Icebrand",		EquipType.BLADE,		42,	0,	0,	0,	0,	0,	0,	0,	1,	Element.ICE,		1),
	KWIGON_BLADE	("Kwigon Blade",	EquipType.BLADE,		40,	3,	0,	3,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MATERIA_BLADE	("Materia Blade",	EquipType.BLADE,		17,	0,	15,	10,	0,	0,	0,	0,	1,	Element.NULL,		2),
	MYTHRIL_BLADE	("Mythril Blade",	EquipType.BLADE,		32,	3,	0,	3,	0,	0,	1,	0,	1,	Element.NULL,		1),
	OGUN_BLADE		("Ogun Blade",		EquipType.BLADE,		42,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	PARAIBA_BLADE	("Paraiba Blade",	EquipType.BLADE,		33,	0,	10,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	PEARL_BLADE		("Pearl Blade",		EquipType.BLADE,		46,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SHADOW_BLADE	("Shadow Blade",	EquipType.BLADE,		32,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	SUN_BLADE		("Sun Blade",		EquipType.BLADE,		37,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	SWEEP_BLADE		("Sweep Blade",		EquipType.BLADE,		28,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	VENUS_BLADE		("Venus Blade",		EquipType.BLADE,		45,	0,	2,	0,	2,	0,	0,	0,	1,	Element.FIRE,		1),
	
	AQUA_SABER		("Aqua Saber",		EquipType.SABER,		36,	0,	0,	0,	0,	0,	0,	6,	1,	Element.WATER,		1),
	BLUE_SABER		("Blue Saber",		EquipType.SABER,		25,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	HARPE			("Harpe",			EquipType.SABER,		42,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MANGANESE		("Manganese",		EquipType.SABER,		47,	10,	0,	0,	0,	0,	0,	3,	1,	Element.NULL,		2),
	MYTHRIL_SABER	("Mythril Saber",	EquipType.SABER,		32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	SHAMSHIR		("Shamshir",		EquipType.SABER,		31,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	SOUL_SABER		("Soul Saber",		EquipType.SABER,		39,	0,	0,	10,	0,	0,	0,	5,	1,	Element.FIRE,		2),
	TULWAR			("Tulwar",			EquipType.SABER,		55,	10,	0,	10,	0,	0,	0,	2,	1,	Element.NULL,		2),
		
	APOCALYPSE		("Apocalypse",		EquipType.KNIGHTSWORD,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		1),
	ARCH_SWORD		("Arch Sword",		EquipType.KNIGHTSWORD,	48,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	DEFENDER		("Defender",		EquipType.KNIGHTSWORD,	37,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	EXCALIBUR		("Excalibur",		EquipType.KNIGHTSWORD,	47,	0,	2,	0,	1,	0,	0,	5,	1,	Element.HOLY,		1),
	EXCALIBUR2		("Excalibur2",		EquipType.KNIGHTSWORD,	87,	0,	3,	0,	4,	0,	0,	5,	1,	Element.NULL,		2),
	LIONHEART		("Lionheart",		EquipType.KNIGHTSWORD,	34,	2,	0,	1,	0,	0,	0,	0,	1,	Element.NULL,		1),
	LOHENGRIN		("Lohengrin",		EquipType.KNIGHTSWORD,	46,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MYTHRIL_BRAND	("Mythril Brand",	EquipType.KNIGHTSWORD,	32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	NAGRAROK		("Nagrarok",		EquipType.KNIGHTSWORD,	75,	0,	0,	0,	6,	1,	0,	0,	1,	Element.NULL,		2),
	RAGNAROK		("Ragnarok",		EquipType.KNIGHTSWORD,	36,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SAVETHEQUEEN	("SaveTheQueen",	EquipType.KNIGHTSWORD,	45,	0,	0,	0,	0,	0,	0,	0,	1,	Element.HOLY,		1),
	SEQUENCE		("Sequence",		EquipType.KNIGHTSWORD,	32,	2,	5,	2,	2,	1,	1,	2,	1,	Element.NULL,		3),
	
	ANCIENT_SWORD	("Ancient Sword",	EquipType.GREATSWORD,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	BARONG			("Barong",			EquipType.GREATSWORD,	30,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	DIAMOND_SWORD	("Diamond Sword",	EquipType.GREATSWORD,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	HARDEDGE		("Hardedge",		EquipType.GREATSWORD,	42,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	ICEPRISM		("Iceprism",		EquipType.GREATSWORD,	45,	0,	0,	0,	0,	0,	0,	0,	1,	Element.ICE,		2),
	LUREBREAKER		("Lurebreaker",		EquipType.GREATSWORD,	51,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	MASTER_SWORD	("Master Sword",	EquipType.GREATSWORD,	59,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	OBLIGE			("Oblige",			EquipType.GREATSWORD,	48,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	VIGILANTE		("Vigilante",		EquipType.GREATSWORD,	37,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	ZANKPLUS		("Zankplus",		EquipType.GREATSWORD,	49,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	
	BEASTSWORD		("Beastsword",		EquipType.BROADSWORD,	50,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	CLAYMORE		("Claymore",		EquipType.BROADSWORD,	49,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	ECLIPSE			("Eclipse",			EquipType.BROADSWORD,	76,	5,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	EL_CID_SWORD	("El Cid Sword",	EquipType.BROADSWORD,	47,	10,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	ESTRELEDGE		("Estreledge",		EquipType.BROADSWORD,	77,	5,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	FALCHION		("Falchion",		EquipType.BROADSWORD,	27,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	PREDATOR		("Predator",		EquipType.BROADSWORD,	37,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	RHOMPHAIA		("Rhomphaia",		EquipType.BROADSWORD,	57,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	SAMSON_SWORD	("Samson Sword",	EquipType.BROADSWORD,	32,	5,	0,	0,	0,	0,	0,	0,	1,	Element.EARTH,		1),
	STRIBORG		("Striborg",		EquipType.BROADSWORD,	33,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	TABARISE		("Tabarise",		EquipType.BROADSWORD,	47,	5,	0,	0,	2,	0,	0,	2,	1,	Element.NULL,		2),
	VAJRA			("Vajra",			EquipType.BROADSWORD,	45,	5,	0,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	2),
	
	CINQUEDEA		("Cinquedea",		EquipType.KNIFE,		57,	0,	0,	0,	5,	0,	0,	2,	1,	Element.NULL,		2),
	JACK_KNIFE		("Jack Knife",		EquipType.KNIFE,		22,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1),
	JAMBIYA			("Jambiya",			EquipType.KNIFE,		31,	0,	2,	0,	0,	0,	0,	1,	1,	Element.NULL,		1),
	KARD			("Kard",			EquipType.KNIFE,		35,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	KHUKURI			("Khukuri",			EquipType.KNIFE,		37,	0,	0,	0,	2,	0,	0,	1,	1,	Element.NULL,		1),
	KRIS_KNIFE		("Kris Knife",		EquipType.KNIFE,		30,	0,	0,	5,	0,	0,	0,	1,	1,	Element.NULL,		1),
	MYTHRIL_KNIFE	("Mythril Knife",	EquipType.KNIFE,		32,	0,	0,	0,	0,	0,	1,	1,	1,	Element.NULL,		1),
	ORICHALCUM		("Orichalcum",		EquipType.KNIFE,		60,	0,	0,	2,	0,	0,	0,	1,	1,	Element.NULL,		2),
	RONDELL_DAGGER	("Rondell Dagger",	EquipType.KNIFE,		22,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1),
	SCRAMASAX		("Scramasax",		EquipType.KNIFE,		29,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1),
	SWORD_BREAKER	("Sword Breaker",	EquipType.KNIFE,		39,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	TIPTAPTWO		("Tiptaptwo",		EquipType.KNIFE,		35,	0,	0,	0,	15,	0,	0,	0,	1,	Element.NULL,		1),
	TONBERRIAN		("Tonberrian",		EquipType.KNIFE,		37,	0,	0,	0,	10,	0,	0,	0,	1,	Element.NULL,		2),
	ZORLIN_SHAPE	("Zorlin Shape",	EquipType.KNIFE,		38,	0,	0,	0,	1,	0,	0,	1,	1,	Element.NULL,		1),
	
	AERIAL_HOLE		("Aerial Hole",		EquipType.RAPIER,		43,	0,	8,	0,	2,	0,	0,	0,	1,	Element.WIND,		2),
	COLICHEMARDE	("Colichemarde",	EquipType.RAPIER,		36,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	DIABOLIQUE		("Diabolique",		EquipType.RAPIER,		41,	0,	0,	5,	2,	0,	0,	0,	1,	Element.DARK,		1),
	DJINN_FLYSSA	("Djinn Flyssa",	EquipType.RAPIER,		34,	0,	0,	2,	2,	0,	0,	2,	1,	Element.WIND,		1),
	EPEPRISM		("Epeprism",		EquipType.RAPIER,		37,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	ESTOC			("Estoc",			EquipType.RAPIER,		32,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	FEMME_FATALE	("Femme Fatale",	EquipType.RAPIER,		49,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2),
	FLAMBERGE		("Flamberge",		EquipType.RAPIER,		35,	5,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	FLEURET			("Fleuret",			EquipType.RAPIER,		27,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	GUPTI_AGA		("Gupti Aga",		EquipType.RAPIER,		38,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	JOYEUSE			("Joyeuse",			EquipType.RAPIER,		27,	0,	0,	5,	2,	0,	0,	0,	1,	Element.NULL,		1),
	LAST_LETTER		("Last Letter",		EquipType.RAPIER,		38,	0,	0,	0,	2,	0,	0,	3,	1,	Element.NULL,		2),
	MADU			("Madu",			EquipType.RAPIER,		33,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2),
	MAGE_MASHER		("Mage Masher",		EquipType.RAPIER,		38,	0,	5,	10,	2,	0,	0,	0,	1,	Element.NULL,		1),
	MYTHRIL_RAPIER	("Mythril Rapier",	EquipType.RAPIER,		32,	0,	0,	0,	2,	0,	1,	0,	1,	Element.NULL,		1),
	SCARLETTE		("Scarlette",		EquipType.RAPIER,		27,	0,	2,	0,	2,	0,	0,	0,	1,	Element.FIRE,		1),
	SILVER_RAPIER	("Silver Rapier",	EquipType.RAPIER,		35,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	STINGER			("Stinger",			EquipType.RAPIER,		25,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		1),
	
	ASHURA			("Ashura",			EquipType.KATANA,		33,	0,	0,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1),
	CHARFIRE		("Charfire",		EquipType.KATANA,		47,	0,	0,	0,	2,	0,	0,	0,	1,	Element.NULL,		2),
	HEAVENS_CLOUD	("Heaven's Cloud",	EquipType.KATANA,		39,	0,	0,	5,	0,	0,	0,	0,	1,	Element.HOLY,		1),
	KIKUICHIMONJI	("Kikuichimonji",	EquipType.KATANA,		40,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	KOTETSU			("Kotetsu",			EquipType.KATANA,		37,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MASAMUNE		("Masamune",		EquipType.KATANA,		65,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MASAMUNE_100	("Masamune 100",	EquipType.KATANA,		79,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	MURASAME		("Murasame",		EquipType.KATANA,		31,	0,	0,	0,	0,	0,	0,	0,	1,	Element.WATER,		1),
	MYTHRIL_EPEE	("Mythril Epee",	EquipType.KATANA,		32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	NINJA_KNIFE		("Ninja Knife",		EquipType.KATANA,		31,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	NOSADA			("Nosada",			EquipType.KATANA,		42,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	OSAFUNE			("Osafune",			EquipType.KATANA,		35,	5,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	PETALCHASER		("Petalchaser",		EquipType.KATANA,		34,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SILKMOON		("Silkmoon",		EquipType.KATANA,		55,	0,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		2),
	ZANMATO			("Zanmato",			EquipType.KATANA,		22,	0,	2,	0,	0,	0,	0,	0,	1,	Element.HOLY,		1),
	
	BLESS_STAFF		("Bless Staff",		EquipType.STAFF,		23,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	CHEER_STAFF		("Cheer Staff",		EquipType.STAFF,		32,	5,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	CURE_STAFF		("Cure Staff",		EquipType.STAFF,		29,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	DREAM_WATCHER	("Dream Watcher",	EquipType.STAFF,		43,	0,	10,	15,	0,	0,	0,	0,	1,	Element.NULL,		2),
	GARNET_STAFF	("Garnet Staff",	EquipType.STAFF,		23,	5,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	GUARD_STAFF		("Guard Staff",		EquipType.STAFF,		21,	5,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	JUDGE_STAFF		("Judge Staff",		EquipType.STAFF,		21,	0,	3,	5,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1),
	MYTHRIL_STAFF	("Mythril Staff",	EquipType.STAFF,		32,	0,	0,	5,	0,	0,	1,	0,	1,	Element.NULL,		1),
	NIRVANA_STAFF	("Nirvana Staff",	EquipType.STAFF,		34,	0,	0,	10,	0,	0,	0,	0,	1,	Element.HOLY,		1),
	POWER_STAFF		("Power Staff",		EquipType.STAFF,		45,	6,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		2),
	PURE_STAFF		("Pure Staff",		EquipType.STAFF,		23,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SNAKE_STAFF		("Snake Staff",		EquipType.STAFF,		29,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SPRING_STAFF	("Spring Staff",	EquipType.STAFF,		28,	0,	0,	5,	0,	0,	0,	0,	1,	Element.WATER,		1),
	WHITE_STAFF		("White Staff",		EquipType.STAFF,		23,	0,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	
	CHILL_ROD		("Chill Rod",		EquipType.ROD,			27,	0,	2,	0,	0,	0,	0,	0,	1,	Element.ICE,		1),
	FIREWHEEL_ROD	("Firewheel Rod",	EquipType.ROD,			21,	0,	2,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1),
	FLAME_ROD		("Flame Rod",		EquipType.ROD,			27,	0,	2,	0,	0,	0,	0,	0,	1,	Element.FIRE,		1),
	FORCE_ROD		("Force Rod",		EquipType.ROD,			25,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	HERETIC_ROD		("Heretic Rod",		EquipType.ROD,			31,	0,	20,	0,	0,	0,	0,	0,	1,	Element.DARK,		2),
	MYTHRIL_ROD		("Mythril Rod",		EquipType.ROD,			32,	0,	2,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	PRINCESS_ROD	("Princess Rod",	EquipType.ROD,			35,	5,	2,	5,	0,	0,	0,	2,	1,	Element.NULL,		1),
	ROD				("Rod",				EquipType.ROD,			18,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SAPERE_AUDE		("Sapere Aude",		EquipType.ROD,			18,	2,	5,	2,	2,	1,	1,	2,	1,	Element.NULL,		3),
	SLEET_ROD		("Sleet Rod",		EquipType.ROD,			21,	0,	2,	0,	0,	0,	0,	0,	1,	Element.ICE,		1),
	STARDUST_ROD	("Stardust Rod",	EquipType.ROD,			29,	0,	5,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	TERRE_ROD		("Terre Rod",		EquipType.ROD,			23,	0,	2,	0,	0,	0,	0,	0,	1,	Element.EARTH,		1),
	THOR_ROD		("Thor Rod",		EquipType.ROD,			27,	0,	2,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1),
	THUNDER_ROD		("Thunder Rod",		EquipType.ROD,			21,	0,	2,	0,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1),
	
	BATTLE_MACE		("Battle Mace",		EquipType.MACE,			31,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	CACTUS_STICK	("Cactus Stick",	EquipType.MACE,			62,	2,	0,	5,	0,	0,	0,	0,	1,	Element.NULL,		2),
	DRUID_MACE		("Druid Mace",		EquipType.MACE,			33,	0,	3,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	ENERGY_MACE		("Energy Mace",		EquipType.MACE,			29,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	LIFE_CROSIER	("Life Crosier",	EquipType.MACE,			35,	0,	2,	10,	0,	0,	0,	0,	1,	Element.NULL,		1),
	LOTUS_MACE		("Lotus Mace",		EquipType.MACE,			37,	0,	2,	5,	0,	0,	0,	0,	1,	Element.FIRE,		1),
	MANDRAGORA		("Mandragora",		EquipType.MACE,			37,	0,	2,	5,	0,	0,	0,	0,	1,	Element.EARTH,		1),
	MORNING_STAR	("Morning Star",	EquipType.MACE,			33,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MYTHRIL_MACE	("Mythril Mace",	EquipType.MACE,			32,	0,	2,	5,	0,	0,	1,	0,	1,	Element.NULL,		1),
	SAGE_CROSIER	("Sage Crosier",	EquipType.MACE,			31,	0,	8,	8,	0,	0,	0,	0,	1,	Element.NULL,		1),
	SCORPION_TAIL	("Scorpion Tail",	EquipType.MACE,			38,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	VESPER			("Vesper",			EquipType.MACE,			39,	0,	2,	5,	0,	0,	0,	0,	1,	Element.NULL,		1),
	ZEUS_MACE		("Zeus Mace",		EquipType.MACE,			15,	0,	5,	5,	0,	0,	0,	0,	1,	Element.HOLY,		2),
	
	ARTEMIS_BOW		("Artemis Bow",		EquipType.BOW,			27,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		1),
	CHAR_BOW		("Char Bow",		EquipType.BOW,			21,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0),
	CRESCENT_BOW	("Crescent Bow",	EquipType.BOW,			45,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		2),
	LONGBOW			("Longbow",		EquipType.BOW,			19,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0),
	MALBOW			("Malbow",			EquipType.BOW,			55,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		1),
	MYTHRIL_BOW		("Mythril Bow",		EquipType.BOW,			32,	0,	0,	0,	0,	0,	0,	1,	5,	Element.NULL,		1),
	NAIL_BOW		("Nail Bow",		EquipType.BOW,			29,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0),
	PERSEUS_BOW		("Perseus Bow",		EquipType.BOW,			42,	0,	0,	0,	0,	0,	0,	2,	6,	Element.NULL,		1),
	SILVER_BOW		("Silver Bow",		EquipType.BOW,			23,	0,	0,	0,	0,	0,	0,	0,	6,	Element.NULL,		0),
	TARGET_BOW		("Target Bow",		EquipType.BOW,			35,	0,	0,	0,	0,	0,	0,	5,	5,	Element.NULL,		1),
	THORN_BOW		("Thorn Bow",		EquipType.BOW,			25,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0),
	YOICHI_BOW		("Yoichi Bow",		EquipType.BOW,			33,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		1),
	
	ARBALEST		("Arbalest",		EquipType.GREATBOW,		42,	0,	0,	0,	0,	0,	0,	0,	5,	Element.EARTH,		2),
	CRANEQUIN		("Cranequin",		EquipType.GREATBOW,		29,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0),
	FEY_BOW			("Fey Bow",			EquipType.GREATBOW,		31,	0,	0,	0,	0,	0,	0,	2,	6,	Element.WIND,		1),
	GASTRA_BOW		("Gastra Bow",		EquipType.GREATBOW,		51,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		2),
	HADES_BOW		("Hades Bow",		EquipType.GREATBOW,		33,	0,	0,	0,	0,	0,	0,	0,	5,	Element.DARK,		1),
	MARDUK_BOW		("Marduk Bow",		EquipType.GREATBOW,		39,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		2),
	MASTER_BOW		("Master Bow",		EquipType.GREATBOW,		41,	0,	0,	0,	0,	0,	0,	2,	5,	Element.NULL,		1),
	MAXS_OATHBOW	("Max's Oathbow",	EquipType.GREATBOW,		61,	0,	2,	0,	0,	0,	0,	0,	6,	Element.DARK,		2),
	MYTHRIL_SHOT	("Mythril Shot",	EquipType.GREATBOW,		32,	0,	0,	0,	0,	0,	1,	0,	5,	Element.NULL,		1),
	NIKE_BOW		("Nike Bow",		EquipType.GREATBOW,		37,	5,	0,	0,	0,	0,	0,	0,	5,	Element.LIGHTNING,	1),
	RANGER_BOW		("Ranger Bow",		EquipType.GREATBOW,		23,	0,	0,	0,	0,	0,	0,	0,	5,	Element.EARTH,		0),
	SEVENTH_HEAVEN	("Seventh Heaven",	EquipType.GREATBOW,		15,	0,	0,	0,	0,	0,	0,	5,	5,	Element.HOLY,		0),
	TWIN_BOW		("Twin Bow",		EquipType.GREATBOW,		31,	0,	0,	0,	0,	0,	0,	0,	5,	Element.NULL,		0),
	WINDSLASH_BOW	("Windslash Bow",	EquipType.GREATBOW,		25,	0,	0,	0,	0,	0,	0,	2,	5,	Element.WIND,		0),
	
	BANGAA_SPIKE	("Bangaa Spike",	EquipType.SPEAR,		53,	5,	2,	5,	0,	0,	0,	0,	2,	Element.NULL,		2),
	BEASTSPEAR		("Beastspear",		EquipType.SPEAR,		51,	10,	0,	0,	0,	0,	0,	0,	2,	Element.NULL,		2),
	DRAGON_WHISKER	("Dragon Whisker",	EquipType.SPEAR,		45,	0,	0,	0,	0,	0,	0,	1,	2,	Element.NULL,		0),
	GAE_BOLG		("Gae Bolg",		EquipType.SPEAR,		39,	0,	0,	0,	0,	0,	0,	0,	2,	Element.LIGHTNING,	0),
	ICE_LANCE		("Ice Lance",		EquipType.SPEAR,		35,	0,	0,	0,	0,	0,	0,	0,	2,	Element.ICE,		0),
	JAVELIN			("Javelin",			EquipType.SPEAR,		31,	0,	0,	0,	0,	0,	0,	0,	2,	Element.NULL,		0),
	KAINS_LANCE		("Kain's Lance",	EquipType.SPEAR,		47,	0,	0,	0,	0,	0,	0,	1,	2,	Element.NULL,		1),
	LAVA_SPEAR		("Lava Spear",		EquipType.SPEAR,		33,	0,	0,	0,	0,	0,	0,	0,	2,	Element.FIRE,		0),
	MYTHRIL_SPEAR	("Mythril Spear",	EquipType.SPEAR,		32,	0,	0,	0,	0,	0,	0,	2,	2,	Element.NULL,		1),
	ODIN_LANCE		("Odin Lance",		EquipType.SPEAR,		55,	0,	0,	0,	0,	0,	0,	0,	2,	Element.NULL,		2),
	PARTISAN		("Partisan",		EquipType.SPEAR,		42,	0,	0,	0,	0,	0,	0,	1,	2,	Element.NULL,		0),
	TRIDENT			("Trident",			EquipType.SPEAR,		50,	0,	2,	0,	0,	0,	0,	1,	2,	Element.NULL,		1),
	
	AONA_FLUTE		("Aona Flute",		EquipType.INSTRUMENT,	32,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	BLACK_QUENA		("Black Quena",		EquipType.INSTRUMENT,	33,	2,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		0),
	BLOOD_STRINGS	("Blood Strings",	EquipType.INSTRUMENT,	22,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		1),
	CONCH_SHELL		("Conch Shell",		EquipType.INSTRUMENT,	31,	2,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	DARK_FIDDLE		("Dark Fiddle",		EquipType.INSTRUMENT,	45,	0,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		2),
	DEMON_BELL		("Demon Bell",		EquipType.INSTRUMENT,	22,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		0),
	EARTH_BELL		("Earth Bell",		EquipType.INSTRUMENT,	31,	3,	0,	0,	0,	0,	0,	0,	1,	Element.EARTH,		0),
	FAIRY_HARP		("Fairy Harp",		EquipType.INSTRUMENT,	29,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	FELL_CASTANETS	("Fell Castanets",	EquipType.INSTRUMENT,	47,	0,	0,	0,	0,	0,	0,	0,	1,	Element.DARK,		2),
	GLASS_BELL		("Glass Bell",		EquipType.INSTRUMENT,	25,	0,	0,	1,	0,	0,	0,	0,	1,	Element.NULL,		0),
	HEAL_CHIME		("Heal Chime",		EquipType.INSTRUMENT,	39,	0,	0,	0,	0,	0,	0,	1,	1,	Element.HOLY,		1),
	MYTHRIL_BELL	("Mythril Bell",	EquipType.INSTRUMENT,	32,	0,	0,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	SATYR_FLUTE		("Satyr Flute",		EquipType.INSTRUMENT,	35,	0,	0,	0,	0,	0,	0,	0,	1,	Element.NULL,		0),
	WAR_TRUMPET		("War Trumpet",		EquipType.INSTRUMENT,	25,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0),
	
	CAT_CLAWS		("Cat Claws",		EquipType.KNUCKLES,		35,	0,	0,	0,	2,	0,	0,	1,	1,	Element.NULL,		1),
	DEATH_CLAWS		("Death Claws",		EquipType.KNUCKLES,		43,	0,	0,	0,	0,	0,	0,	1,	1,	Element.DARK,		1),
	DREAM_CLAWS		("Dream Claws",		EquipType.KNUCKLES,		39,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0),
	GODHAND			("Godhand",			EquipType.KNUCKLES,		39,	0,	3,	0,	1,	0,	0,	5,	1,	Element.HOLY,		1),
	GREASEBURST		("Greaseburst",		EquipType.KNUCKLES,		59,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		2),
	HARD_KNUCKLES	("Hard Knuckles",	EquipType.KNUCKLES,		29,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0),
	KAISER_KNUCKLES	("Kaiser Knuckles",	EquipType.KNUCKLES,		42,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0),
	MAGIC_HANDS		("Magic Hands",		EquipType.KNUCKLES,		52,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		2),
	MYTHRIL_CLAWS	("Mythril Claws",	EquipType.KNUCKLES,		32,	0,	0,	0,	0,	0,	1,	1,	1,	Element.NULL,		1),
	RISING_SUN		("Rising Sun",		EquipType.KNUCKLES,		31,	0,	0,	0,	0,	0,	1,	1,	1,	Element.NULL,		1),
	SICK_KNUCKLES	("Sick Knuckles",	EquipType.KNUCKLES,		35,	0,	0,	0,	0,	0,	0,	1,	1,	Element.NULL,		0),
	SURVIVOR		("Survivor",		EquipType.KNUCKLES,		29,	2,	0,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	TIGER_FANGS		("Tiger Fangs",		EquipType.KNUCKLES,		41,	0,	0,	0,	0,	0,	0,	2,	1,	Element.LIGHTNING,	1),
	WHITE_FANGS		("White Fangs",		EquipType.KNUCKLES,		39,	0,	0,	0,	0,	0,	0,	1,	1,	Element.ICE,		1),
	
	BOMB_SOUL		("Bomb Soul",		EquipType.SOUL,			36,	0,	0,	0,	2,	0,	0,	0,	1,	Element.FIRE,		1),
	BUG_SOUL		("Bug Soul",		EquipType.SOUL,			39,	2,	2,	0,	0,	0,	0,	0,	1,	Element.EARTH,		1),
	DRAGON_SOUL		("Dragon Soul",		EquipType.SOUL,			43,	5,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		1),
	DREAD_SOUL		("Dread Soul",		EquipType.SOUL,			49,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	EYE_SOUL		("Eye Soul",		EquipType.SOUL,			45,	0,	2,	0,	0,	0,	0,	0,	1,	Element.DARK,		1),
	FLAN_SOUL		("Flan Soul",		EquipType.SOUL,			34,	34,	10,	2,	0,	0,	0,	0,	1,	Element.LIGHTNING,	1),
	GOBLIN_SOUL		("Goblin Soul",		EquipType.SOUL,			32,	0,	2,	0,	0,	0,	0,	1,	1,	Element.NULL,		1),
	LAMIA_SOUL		("Lamia Soul",		EquipType.SOUL,			32,	0,	2,	0,	0,	0,	0,	0,	1,	Element.WATER,		1),
	MALBORO_SOUL	("Malboro Soul",	EquipType.SOUL,			47,	0,	2,	2,	0,	0,	0,	0,	1,	Element.NULL,		1),
	MYTHRIL_SOUL	("Mythril Soul",	EquipType.SOUL,			32,	0,	2,	0,	0,	0,	1,	0,	1,	Element.NULL,		1),
	PANTHER_SOUL	("Panther Soul",	EquipType.SOUL,			39,	0,	2,	0,	0,	0,	0,	2,	1,	Element.NULL,		1),
	RUKAVI_SOUL		("Rukavi Soul",		EquipType.SOUL,			67,	0,	2,	0,	0,	0,	0,	0,	1,	Element.NULL,		2),
	
	AIOT_GUN		("Aiot Gun",		EquipType.GUN,			27,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		0),
	BINDSNIPE		("Bindsnipe",		EquipType.GUN,			47,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		2),
	CALLING_GUN		("Calling Gun",		EquipType.GUN,			59,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		2),
	CHAOS_RIFLE		("Chaos Rifle",		EquipType.GUN,			33,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		0),
	GIOT_GUN		("Giot Gun",		EquipType.GUN,			37,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1),
	LONGBARREL		("Longbarrel",		EquipType.GUN,			39,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1),
	LOST_GUN		("Lost Gun",		EquipType.GUN,			31,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1),
	MYTHRIL_GUN		("Mythril Gun",		EquipType.GUN,			32,	0,	0,	0,	0,	0,	1,	0,	8,	Element.NULL,		1),
	OUTSIDER		("Outsider",		EquipType.GUN,			41,	0,	0,	0,	0,	0,	0,	0,	9,	Element.NULL,		1),
	PEACEMAKER		("Peacemaker",		EquipType.GUN,			33,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		1),
	RIOT_GUN		("Riot Gun",		EquipType.GUN,			31,	0,	0,	0,	0,	0,	0,	0,	8,	Element.NULL,		0),
	SILVER_CANNON	("Silver Cannon",	EquipType.GUN,			31,	0,	0,	0,	0,	0,	0,	0,	7,	Element.NULL,		0),
	
	AEGIS_SHIELD	("Aegis Shield",	EquipType.SHIELD,		0,	5,	0,	5,	0,	0,	0,	10,	0,	Element.NULL,		1),
	BRONZE_SHIELD	("Bronze Shield",	EquipType.SHIELD,		0,	2,	0,	0,	0,	0,	0,	4,	0,	Element.NULL,		0),
	CHOCO_SHIELD	("Choco Shield",	EquipType.SHIELD,		0,	0,	0,	0,	0,	0,	0,	10,	0,	Element.NULL,		1),
	FLAME_SHIELD	("Flame Shield",	EquipType.SHIELD,		0,	6,	0,	0,	0,	0,	0,	9,	0,	Element.NULL,		1),
	GENJI_SHIELD	("Genji Shield",	EquipType.SHIELD,		0,	10,	0,	7,	0,	0,	0,	10,	0,	Element.NULL,		1),
	ICE_SHIELD		("Ice Shield",		EquipType.SHIELD,		0,	6,	0,	0,	0,	0,	0,	9,	0,	Element.NULL,		1),
	LA_SERAPHICA	("La Seraphica",	EquipType.SHIELD,		0,	0,	0,	5,	0,	0,	0,	15,	0,	Element.NULL,		2),
	OPAL_SHIELD		("Opal Shield",		EquipType.SHIELD,		0,	2,	0,	6,	0,	0,	0,	7,	0,	Element.NULL,		0),
	REVERIE_SHIELD	("Reverie Shield",	EquipType.SHIELD,		0,	5,	0,	10,	0,	0,	0,	10,	0,	Element.NULL,		1),
	ROUND_SHIELD	("Round Shield",	EquipType.SHIELD,		0,	0,	0,	4,	0,	0,	0,	5,	0,	Element.NULL,		0),
	SACRI_SHIELD	("Sacri Shield",	EquipType.SHIELD,		0,	5,	0,	5,	0,	0,	0,	10,	0,	Element.NULL,		1),
	SHIJIN_SHIELD	("Shijin Shield",	EquipType.SHIELD,		0,	0,	0,	10,	0,	0,	0,	7,	0,	Element.NULL,		1),

	BANGAA_HELM		("Bangaa Helm",		EquipType.HELM,			0,	16,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		2),
	BRONZE_HELM		("Bronze Helm",		EquipType.HELM,			0,	4,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		0),
	CROSS_HELM		("Cross Helm",		EquipType.HELM,			0,	9,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		1),
	DIAMOND_HELM	("Diamond Helm",	EquipType.HELM,			0,	11,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		1),
	GENJI_HELM		("Genji Helm",		EquipType.HELM,			0,	15,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		2),
	HANYA_HELM		("Hanya Helm",		EquipType.HELM,			0,	12,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		2),
	IRON_HELM		("Iron Helm",		EquipType.HELM,			0,	5,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0),
	OPAL_HELM		("Opal Helm",		EquipType.HELM,			0,	7,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0),
	PARADE_HELM		("Parade Helm",		EquipType.HELM,			0,	9,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		2),
	
	BARETTE			("Barette",			EquipType.RIBBON,		0,	2,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		2),
	CACHUSHA		("Cachusha",		EquipType.RIBBON,		0,	2,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		2),
	RIBBON			("Ribbon",			EquipType.RIBBON,		0,	2,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		2),
	TIARA			("Tiara",			EquipType.RIBBON,		0,	8,	0,	20,	0,	0,	0,	0,	0,	Element.NULL,		2),
	
	ACACIA_HAT		("Acacia Hat",		EquipType.HAT,			0,	2,	5,	2,	2,	1,	1,	5,	0,	Element.NULL,		3),
	BLACK_HAT		("Black Hat",		EquipType.HAT,			0,	4,	4,	16,	0,	0,	0,	0,	0,	Element.NULL,		2),
	CIRCLET			("Circlet",			EquipType.HAT,			0,	3,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0),
	FEATHER_CAP		("Feather Cap",		EquipType.HAT,			0,	2,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		0),
	GOLD_HAIRPIN	("Gold Hairpin",	EquipType.HAT,			0,	4,	2,	12,	0,	0,	0,	0,	0,	Element.NULL,		1),
	GREEN_BERET		("Green Beret",		EquipType.HAT,			0,	2,	0,	2,	0,	0,	0,	2,	0,	Element.NULL,		0),
	HEADBAND		("Headband",		EquipType.HAT,			5,	6,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		0),
	THIEF_HAT		("Thief Hat",		EquipType.HAT,			0,	8,	0,	6,	0,	0,	0,	7,	0,	Element.NULL,		1),
	WHITE_HAT		("White Hat",		EquipType.HAT,			0,	4,	0,	14,	0,	0,	0,	0,	0,	Element.NULL,		2),
	WIZARD_HAT		("Wizard Hat",		EquipType.HAT,			0,	3,	1,	10,	0,	0,	0,	0,	0,	Element.NULL,		0),
	
	ADAMAN_ARMOR	("Adaman Armor",	EquipType.ARMOR,		0,	58,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		2),
	BRONZE_ARMOR	("Bronze Armor",	EquipType.ARMOR,		0,	30,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0),
	CARABINI_MAIL	("Carabini Mail",	EquipType.ARMOR,		0,	38,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1),
	CUIRASS			("Cuirass",			EquipType.ARMOR,		0,	28,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		0),
	DIAMOND_ARMOR	("Diamond Armor",	EquipType.ARMOR,		0,	40,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		1),
	DRAGON_MAIL		("Dragon Mail",		EquipType.ARMOR,		0,	40,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1),
	GENJI_ARMOR		("Genji Armor",		EquipType.ARMOR,		0,	46,	0,	12,	0,	0,	0,	0,	0,	Element.NULL,		1),
	GOLD_ARMOR		("Gold Armor",		EquipType.ARMOR,		0,	42,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0),
	MATERIA_ARMOR	("Materia Armor",	EquipType.ARMOR,		0,	52,	0,	16,	0,	0,	0,	0,	0,	Element.NULL,		2),
	MAXIMILLIAN		("Maximillian",		EquipType.ARMOR,		3,	46,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		1),
	MIRROR_MAIL		("Mirror Mail",		EquipType.ARMOR,		0,	36,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1),
	OPAL_ARMOR		("Opal Armor",		EquipType.ARMOR,		0,	42,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		1),
	PEYTRAL			("Peytral",			EquipType.ARMOR,		5,	28,	5,	2,	2,	1,	1,	5,	0,	Element.NULL,		3),
	PLATEMAIL		("Platemail",		EquipType.ARMOR,		0,	38,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0),
	
	ADAMAN_VEST		("Adaman Vest",		EquipType.CLOTHING,		0,	30,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		0),
	BONE_PLATE		("Bone Plate",		EquipType.CLOTHING,		0,	42,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		1),
	BRIGANDINE		("Brigandine",		EquipType.CLOTHING,		0,	37,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0),
	BRINT_SET		("Brint Set",		EquipType.CLOTHING,		0,	28,	0,	16,	0,	0,	0,	0,	0,	Element.NULL,		2),
	CHAIN_PLATE		("Chain Plate",		EquipType.CLOTHING,		0,	28,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		0),
	DARK_GEAR		("Dark Gear",		EquipType.CLOTHING,		0,	32,	0,	3,	2,	0,	0,	1,	0,	Element.NULL,		1),
	GALMIA_SET		("Galmia Set",		EquipType.CLOTHING,		0,	26,	0,	18,	0,	0,	0,	0,	0,	Element.NULL,		2),
	JUDGE_COAT		("Judge Goat",		EquipType.CLOTHING,		0,	38,	0,	28,	0,	0,	0,	0,	0,	Element.NULL,		2),
	JUDO_UNIFORM	("Judo Uniform",	EquipType.CLOTHING,		0,	34,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		0),
	LEATHER_GARB	("Leather Garb",	EquipType.CLOTHING,		0,	18,	0,	4,	0,	0,	0,	0,	0,	Element.NULL,		0),
	MIRAGE_VEST		("Mirage Vest",		EquipType.CLOTHING,		0,	32,	0,	16,	0,	0,	0,	0,	0,	Element.NULL,		1),
	NINJA_GEAR		("Ninja Gear",		EquipType.CLOTHING,		0,	30,	0,	6,	1,	0,	0,	2,	0,	Element.NULL,		1),
	ONLYONE			("Onlyone",			EquipType.CLOTHING,		0,	34,	0,	24,	0,	0,	0,	0,	0,	Element.NULL,		2),
	POWER_SASH		("Power Sash",		EquipType.CLOTHING,		2,	34,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		0),
	SURVIVAL_VEST	("Survival Vest",	EquipType.CLOTHING,		0,	34,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		0),
	TEMPLE_CLOTH	("Temple Cloth",	EquipType.CLOTHING,		2,	36,	2,	16,	0,	0,	0,	0,	0,	Element.NULL,		0),
	WYGAR			("Wygar",			EquipType.CLOTHING,		0,	35,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		1),
	
	MINERVA_PLATE	("Minerva Plate",	EquipType.VIERA_CLOTHES,0,	24,	0,	14,	0,	0,	0,	0,	0,	Element.NULL,		1),
	RUBBER_SUIT		("Rubber Suit",		EquipType.VIERA_CLOTHES,0,	24,	0,	14,	0,	0,	0,	0,	0,	Element.NULL,		1),
	
	BLACK_ROBE		("Black Robe",		EquipType.ROBE,			0,	21,	2,	36,	0,	0,	0,	0,	0,	Element.NULL,		2),
	BLAZE_ROBE		("Blaze Robe",		EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		1),
	FLURRY_ROBE		("Flurry Robe",		EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		1),
	HEMPEN_ROBE		("Hempen Robe",		EquipType.ROBE,			0,	15,	0,	22,	0,	0,	0,	0,	0,	Element.NULL,		0),
	LIGHT_ROBE		("Light Robe",		EquipType.ROBE,			0,	25,	0,	40,	0,	0,	0,	0,	0,	Element.NULL,		1),
	LORDLY_ROBE		("Lordly Robe",		EquipType.ROBE,			0,	28,	0,	42,	0,	0,	0,	0,	0,	Element.NULL,		1),
	MAGIC_ROBE		("Magic Robe",		EquipType.ROBE,			0,	24,	6,	36,	0,	0,	0,	0,	0,	Element.NULL,		2),
	MAGUS_ROBE		("Magus Robe",		EquipType.ROBE,			0,	15,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		0),
	MISTLE_ROBE		("Mistle Robe",		EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		0),
	REAPER_CLOAK	("Reaper Cloak",	EquipType.ROBE,			0,	32,	0,	36,	0,	0,	0,	0,	0,	Element.NULL,		1),
	RED_ROBE		("Red Robe",		EquipType.ROBE,			0,	22,	0,	31,	0,	0,	0,	0,	0,	Element.NULL,		2),
	SAGE_ROBE		("Sage Robe",		EquipType.ROBE,			0,	24,	0,	52,	0,	0,	0,	0,	0,	Element.NULL,		2),
	SILKEN_ROBE		("Silken Robe",		EquipType.ROBE,			0,	15,	0,	28,	0,	0,	0,	0,	0,	Element.NULL,		0),
	SILVER_COAT		("Silver Coat",		EquipType.ROBE,			0,	30,	0,	38,	0,	0,	0,	0,	0,	Element.NULL,		2),
	THUNDER_ROBE	("Thunder Robe",	EquipType.ROBE,			0,	19,	0,	30,	0,	0,	0,	0,	0,	Element.NULL,		1),
	WHITE_ROBE		("White Robe",		EquipType.ROBE,			0,	21,	0,	38,	0,	0,	0,	0,	0,	Element.NULL,		1),
	
	BATTLE_BOOTS	("Battle Boots",	EquipType.BOOTS,		0,	7,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0),
	CALIGULA		("Caligula",		EquipType.BOOTS,		3,	7,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		2),
	DASH_BOOTS		("Dash Boots",		EquipType.BOOTS,		0,	2,	0,	0,	0,	1,	0,	0,	0,	Element.NULL,		0),
	FAIRY_SHOES		("Fairy Shoes",		EquipType.BOOTS,		0,	3,	0,	2,	0,	0,	0,	0,	0,	Element.NULL,		1),
	FEATHER_BOOTS	("Feather Boots",	EquipType.BOOTS,		0,	3,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		1),
	GALMIA_SHOES	("Galmia Shoes",	EquipType.BOOTS,		0,	3,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		2),
	GERMINAS		("Germinas",		EquipType.BOOTS,		0,	3,	0,	0,	0,	0,	2,	0,	0,	Element.NULL,		1),
	NINJA_TABI		("Ninja Tabi",		EquipType.BOOTS,		0,	3,	0,	0,	0,	2,	0,	0,	0,	Element.NULL,		2),
	RED_BOOTS		("Red Boots",		EquipType.BOOTS,		0,	3,	0,	5,	0,	0,	0,	2,	0,	Element.NULL,		1),
	SPIKED_BOOTS	("Spiked Boots",	EquipType.BOOTS,		0,	4,	0,	0,	0,	0,	1,	0,	0,	Element.NULL,		0),
	
	BONE_ARMLETS	("Bone Armlets",	EquipType.GLOVES,		3,	6,	0,	8,	0,	0,	0,	5,	0,	Element.NULL,		2),
	BRACERS			("Bracers",			EquipType.GLOVES,		5,	12,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0),
	FIRE_MITTS		("Fire Mitts",		EquipType.GLOVES,		0,	8,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		2),
	GAUNTLETS		("Gauntlets",		EquipType.GLOVES,		5,	5,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0),
	GENJI_ARMLETS	("Genji Armlets",	EquipType.GLOVES,		5,	10,	2,	5,	0,	0,	0,	0,	0,	Element.NULL,		0),
	THIEF_ARMLETS	("Thief Armlets",	EquipType.GLOVES,		3,	3,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		0),
	
	ANGEL_RING		("Angel Ring",		EquipType.ACCESSORY,	0,	0,	0,	0,	0,	0,	0,	0,	0,	Element.NULL,		1),
	FORTUNE_RING	("Fortune Ring",	EquipType.ACCESSORY,	0,	3,	0,	5,	0,	0,	0,	0,	0,	Element.NULL,		0),
	MAGIC_RING		("Magic Ring",		EquipType.ACCESSORY,	0,	3,	0,	10,	0,	0,	0,	0,	0,	Element.NULL,		1),
	MINDU_GEM		("Mindu Gem",		EquipType.ACCESSORY,	0,	3,	0,	3,	0,	0,	0,	0,	0,	Element.NULL,		1),
	RUBY_EARRING	("Ruby Earring",		EquipType.ACCESSORY,	0,	3,	0,	6,	0,	0,	0,	0,	0,	Element.NULL,		1),
	SCARAB			("Scarab",			EquipType.ACCESSORY,	0,	2,	0,	8,	0,	0,	0,	0,	0,	Element.NULL,		0),
	STAR_ARMLET		("Star Armlet",		EquipType.ACCESSORY,	0,	4,	6,	4,	2,	0,	0,	0,	0,	Element.NULL,		0);
	
	
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
	final static FFTAEquip[] CLOTHING_LIST 		= { ADAMAN_VEST, BONE_PLATE, BRIGANDINE, BRINT_SET, CHAIN_PLATE, DARK_GEAR, GALMIA_SET, JUDGE_COAT, JUDO_UNIFORM, LEATHER_GARB, MIRAGE_VEST, NINJA_GEAR, ONLYONE, POWER_SASH, SURVIVAL_VEST, TEMPLE_CLOTH, WYGAR };
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
	public final ImageIcon icon;
	
	FFTAEquip(String name, EquipType type, int wAtk, int wDef, int mPow, int mRes, int speed, int move, int jump, int evade, int range, Element element, int availability)
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
		this.icon = new ImageIcon("resources/equipment/" + name() + ".png");
	}
	
	public boolean isWeapon()
	{
		return (type.ordinal() <= EquipType.GUN.ordinal());
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
			case AEGIS_SHIELD	: effect2.append("Nullifies Slow."); break;
			case FLAME_SHIELD	: effect2.append("Absorbs Fire, Halves Ice. Weak against Water."); break;
			case ICE_SHIELD		: effect2.append("Absorbs Ice, Halves Fire. Weak against Lightning."); break;
			case SACRI_SHIELD	: effect2.append("Nullifies Zombie, Darkness, Silence, Frog, Poison, Slow, Immobilize, Disable, and Doom."); break;
			case RIBBON			: effect2.append("Nullifies most debuffs."); break;
			case CACHUSHA		: effect2.append("Nullifies KO, Stone, Confuse, Berserk, Stop, Charm, and Sleep."); break;
			case BARETTE		: effect2.append("Nullifies Zombie, Darkness, Silence, Frog, Poison, Slow, Immobilize, Disable, and Doom."); break;
			
			case DASH_BOOTS		: effect2.append("Move+1."); break;
			case FEATHER_BOOTS	: effect2.append("Waterwalking."); break;
			case FAIRY_SHOES	: effect2.append("Teleportation."); break;
			case GERMINAS		: effect2.append("Ignore elevation."); break;
			case NINJA_TABI		: effect2.append("Move +2."); break;
			case FIRE_MITTS		: effect2.append("Nullifies Fire."); break;
			case THIEF_ARMLETS	: effect2.append("Improves chance to steal."); break;
			case ANGEL_RING		: effect2.append("Auto-Raise. Nullifies Zombie, Darkness, Silence, Frog, Poison, Slow, Immobilize, Disable, and Doom."); break;
			case FORTUNE_RING	: effect2.append("Nullifies Sleep and Doom."); break;
			case MINDU_GEM		: effect2.append("Nullifies multiple debuffs (mouse-over for list)."); break;
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

enum Element
{
	NULL("Non"), FIRE("Fire"), LIGHTNING("Lightning"), ICE("Ice"), WIND("Wind"), WATER("Water"), EARTH("Earth"), HOLY("Holy"), DARK("Dark");
	String name;
	Element(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
}

enum Status
{
	PETRIFY, BERSERK, FROG, POISON, DARKNESS, SLEEP, SILENCE, CONFUSION, IMMOBILIZE, DISABLE,
	AUTO_LIFE, REGEN, ASTRA, REFLECT, DOOM, HASTE, SLOW, STOP, PROTECT, CHARM, ADDLE,
	KO, ZOMBIE, WATK_UP, WATK_DOWN, WDEF_UP, WDEF_DOWN, MPOW_UP, MPOW_DOWN, MRES_UP, MRES_DOWN, SPEED_DOWN;
}