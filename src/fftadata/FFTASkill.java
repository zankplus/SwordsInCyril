package fftadata;

import java.io.Serializable;
import java.util.ArrayList;

public enum FFTASkill implements Serializable
{
	//FIGHT("-", 0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	FIGHT			("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE	},	"Attack with equipped weapon."),
	FIGHT_L			("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE_LEFT},	"Attack with equipped weapon."),
	DRAIN_WEAPON	("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE, SkillEffect.DRAIN},	"Attack with equipped weapon."),
	DRAIN_WEAPON_L	("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE_LEFT, SkillEffect.DRAIN},	"Attack with equipped weapon."),
	DOUBLE_SWORD	("Fight", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIGHT_DAMAGE},	"Attack with equipped weapons."),
	RETURN_FIRE		("Return Fire",		 0, -2, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.ENEMY_WEAP,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.RETURN_FIRE},	"."),
	CURE			("Cure",			 6, 40, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X},	"Restores HP in a small area."),
	CURA			("Cura", 			10, 60, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X},	"Restores HP in a small area."),
	CURAGA			("Curaga", 			16, 80, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X},	"Restores HP in a small area."),
	ESUNA			("Esuna", 			18,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ESUNA_EFFECT},	"Removes (most) negative status effects from units in a small area."),
	LIFE			("Life", 			10,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		false,	true,	true,	true,	false,	false,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_HALF_HP},	"Revives a single unit with half their max HP."),
	FULL_LIFE		("Full-Life", 		20,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		false,	true,	true,	true,	false,	false,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_FULL_HP},	"Revives a single unit with full HP."),
	AUTO_LIFE		("Auto-Life", 		16,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_AUTO_LIFE},	"Automatically revives a single unit should they fall."),
	SHELL			("Shell", 			 6,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SHELL},	"Grants temporary protection against magical damage in a small area."),
	PROTECT			("Protect", 		 6,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_PROTECT},	"Grants temporary protection against physical damage in a small area."),
	DISPEL			("Dispel", 			12,  0, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DISPEL_EFFECT},	"Removes certain magical effects in a small area."),
	HOLY			("Holy", 			32, 50, 3, 0, 1, 1, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	BARRIER			("Barrier", 		10,  0, 3, 0, 0, 0, Targeting.FREE_SELECT, Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SHELL, SkillEffect.ADD_PROTECT},	"Adds Shell and Protect to a single unit."),
	JUDGE			("Judge", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	BREAK			("Break", 			20,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_PETRIFY},	"Inflicts Petrify on a single unit."),
	WATER			("Water", 			12, 34, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.WATER,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	AERO			("Aero", 			12, 34, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.WIND,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	DRAIN			("Drain", 			12, 30, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE_CAPPED, SkillEffect.DRAIN},	"Deals magical damage and restores the caster's HP."),
	BLIND			("Blind", 			12,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new	SkillEffect[] {SkillEffect.ADD_BLIND},	"Inflicts Darkness on a single unit."),
	RAISE			("Raise",			22, 45, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	true,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.REVIVE_HALF_HP},	"Restores HP to living units in an area and revives dead units with half their max HP."),
	GIGA_FLARE		("Giga Flare",		40, 65, 3, 0, 1, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	BIO				("Bio", 			12, 45, 3, 0, 2, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON},	"Deals magical damage and inflicts Poison in a small area."),
	ULTIMA_BLOW		("Ultima Blow", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	true,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE},	"Deals triple physical damage to a single unit."),
	FIRE			("Fire",			 6, 30, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	FIRA			("Fira",			12, 40, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	FIRAGA			("Firaga",			24, 50, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	THUNDER			("Thunder", 		 6, 30, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	THUNDARA		("Thundara",		12, 40, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	THUNDAGA		("Thundaga", 		24, 50, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	BLIZZARD		("Blizzard", 		 6, 30, 4, 0, 1, 1, Targeting.FREE_SELECT,	Element.ICE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	BLIZZARA		("Blizzara",		12, 40, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.ICE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	BLIZZAGA		("Blizzaga", 		24, 50, 4, 0, 1, 3, Targeting.FREE_SELECT,	Element.ICE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	SLEEP			("Sleep", 			10,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLEEP},	"Inflicts Sleep in a small area."),
	DOUBLECAST		("Doublecast", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	true,	null,	""),
	QUICKEN			("Quicken", 		24,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_QUICK},	"Grants an immediate turn to a single unit."),
	SLOW			("Slow", 			12, 0, 3, 0, 1, 2,	Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLOW},	"Inflicts Slow in a small area."),
	REFLECT			("Reflect",			8,  0, 3, 0, 0, 0,  Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_REFLECT},	"Grants Reflect to a single unit."),
	STOP			("Stop", 			24, 0, 3, 0, 1, 2,	Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_STOP},	"Inflicts Stop in a small area."),
	QUARTER			("Quarter", 		10,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.QUARTER_HP},	"Decreases a single target's HP by 1/4 of its current value."),
	DEMI			("Demi", 			24,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	true,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.HALVE_HP},	"Decreases a single target's HP by 1/2 of its current value."),
	SILENCE			("Silence", 		 8,  0, 3, 0, 1, 2,	Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SILENCE},	"Inflicts Silence in a small area."),
	HASTE			("Haste", 			24,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_HASTE},	"Grants Haste to a single target."),
	PROMINENCE		("Prominence", 		32, 20, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.FIRE,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	TEMPEST			("Tempest", 		32, 17, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.LIGHTNING,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	FREEZEBLINK		("Freezeblink", 	32, 20, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.ICE,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	STAR_CROSS		("Starcross", 		32, 17, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.HOLY,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	STARDUST		("Stardust", 		32, 20, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.FIRE,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	DELUGE			("Deluge", 			32, 17, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.WATER,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	SOIL_EVIDENCE	("Soil Evidence", 	32, 17, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.EARTH,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	WILD_TORNADO	("Wild Tornado", 	32, 17, 0, 0, 0, 0, Targeting.ALL_ENEMIES,	Element.WIND,		true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to all enemy units."),
	FIRE_WHIP		("Fire Whip", 		12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_DISABLE},	"Deals magical damage and inflicts Disable on a single unit."),
	EARTH_HEAL		("Earth Heal", 		12, 40, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X},	"Restores HP to a single target."),
	WHITE_FLAME		("White Flame", 	24, 40, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	true,	false,	false,	false,	true,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X},	"Restores HP in a small area."),
	SHINING_AIR		("Shining Air", 	12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.WIND,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND},	"Deals magical damage and inflicts Darkness on a single unit."),
	EVIL_GAZE		("Evil Gaze", 		12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.DARK,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	false,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_CONFUSE},	"Deals magical damage and inflicts Confusion on a single unit."),
	HEAVY_DUST		("Heavy Dust",		12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.EARTH,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_IMMOBILIZE},	"Deals magical damage and inflicts Immobilize on a single unit."),
	SLIPRAIN		("Sliprain", 		12, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.WATER,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SLOW},	"Deals magical damage and inflicts Slow on a single unit."),
	ELEMENTALSHIFT	("Elementalshift", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	ASTRA			("Astra", 			8,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_ASTRA},	"Grants protection from negative status effects to units in a small area."),
	RASP			("Rasp", 			24, 60, 3, 0, 1, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	true,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE},	"Deals magical damage to MP to units in a small area."),
	DEATH			("Death", 			36,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.DARK,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DEATH},	"Instantly kills a single unit."),
	METEOR			("Meteor", 			40, 50, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage in a small area."),
	FLARE			("Flare", 			36, 65, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals magical damage to a single target."),
	POISON			("Poison", 			10,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.DARK,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_POISON},	"Inflicts Poison on units in a small area."),
	TOAD			("Toad", 			36,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	true,	false,	true,	new SkillEffect[] {SkillEffect.ADD_FROG},	"Inflicts Frog on a single unit."),
	UNICORN			("Unicorn", 		12, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.ESUNA_EFFECT},	"Restores HP and removes negative status effects from units in a large area."),
	IFRIT			("Ifrit", 			18, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Inflicts magical damage to units in a large area."),
	RAMUH			("Ramuh", 			18, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Inflicts magical damage to units in a large area."),
	SHIVA			("Shiva",			18, 40, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.ICE,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Inflicts magical damage to units in a large area."),
	KIRIN			("Kirin", 			24,  0, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_REGEN},	"Grants Regen to units in a large area."),
	CARBUNCLE		("Carbuncle", 		12,  0, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_REFLECT},	"Grants Reflect to units in a large area."),
	PHOENIX			("Phoenix", 		24,  0, 4, 0, 2, 2, Targeting.FREE_SELECT, 	Element.HOLY, 		false,	true,	true,	true,	false,	true,	false,	false,	false,	false,	true,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_FULL_HP},	"Revives units in a large area with full HP."),
	MADEEN			("Madeen", 			36, 52, 4, 0, 2, 2, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	true,	true,	true,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Inflicts magical damage to units in a large area."),
	FIRST_AID		("First Aid",		 0, 25, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.ESUNA_EFFECT},	"Restores the user's HP and removes negative status effects."),
	POWERBREAK		("Powerbreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_WATK_DOWN},	"Decreases a single unit's Weapon Attack."),
	MINDBREAK		("Mindbreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_MPOW_DOWN},	"Decreases a single unit's Magic Power."),
	MAGICBREAK		("Magicbreak", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE},	"Deals weapon damage to a single unit's MP."),
	SPEEDBREAK		("Speedbreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SPEED_DOWN},	"Decreases a single unit's Speed."),
	MUG				("Mug", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	PROVOKE			("Provoke", 		 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.ADD_BERSERK},	"Inflicts Berserk on a single unit."),
	BODY_SLAM		("Body Slam", 		 0, 45, 1, 1, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.RECOIL_DAMAGE},	"Deals physical damage to a single target. The user takes 25% of the damage dealt as recoil."),
	GREASED_BOLT	("Greased Bolt", 	 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	true,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals weapon damage to a single unit, ignoring their R-Ability."),
	DOWNSIZE		("Downsize", 		24,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.HALVE_HP},	"Decreases a single unit's HP by 1/2 of its current value."),
	NURSE			("Nurse", 			 0, 30, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.ESUNA_EFFECT},	"Restores HP to the user and surrounding units and removes negative status effects."),
	COVER			("Cover", 			 0,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	false,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.COVER_EFFECT},	"Protects an ally from damage until the user's next turn."),
	SUBDUE			("Subdue", 			 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.SUBDUE_EFFECT},	"Deals insignificant damage to a single unit."),
	PARLEY			("Parley", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	SAINT_CROSS		("Saint Cross", 	24, 35, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.HOLY,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	"Deals physical damage to all neighboring units."),
	HOLY_BLADE		("Holy Blade",		32, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.HOLY,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.DOUBLE_DAMAGE},	"Deals double weapon damage to a single unit."),
	DEFENSE			("Defense", 		 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DEFENSE},	"Improves the user's Weapon Defense and Magic Resistance until their next turn."),
	DROP_WEAPON		("Drop Weapon", 	 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DROP_WEAPON},	"Unequips a single enemy's weapon."),
	TREMOR			("Tremor", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	HIBERNATE		("Hibernate", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	MOW_DOWN		("Mow Down", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	AURA			("Aura", 			 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_AUTO_LIFE, SkillEffect.ADD_REGEN},	"Grants the user Regen and Auto-Life."),
	EXPERT_GUARD	("Expert Guard", 	 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_EXPERT_GUARD},	"Negates all damage to the user until their next turn, but prevents them from evading attacks."),
	MELTDOWN		("Meltdown", 		 0,  0, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.USER_HP_DAMAGE, SkillEffect.SELFDESTRUCT},	"Deals damage equal to the user's current HP to all neighboring units. The user is killed in the process."),
	WHIRLWIND		("Whirlwind", 		 0, 25, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	EARTH_RENDER	("Earth Render", 	 0, 34, 15,0, 0, 0, Targeting.DIRECTIONAL,	Element.EARTH,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	CHAKRA			("Chakra", 			 0, 35, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.ESUNA_EFFECT},	""),
	REVIVE			("Revive", 			 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		false,	true,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_HALF_HP},	""),
	HOLY_SIGN		("Holy Sign", 		 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DISPEL_EFFECT},	""),
	AIR_RENDER		("Air Render", 		 0, 45, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""), 
	FAR_FIST		("Far Fist", 		 0, 35, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	true,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	AIR_BLAST		("Air Blast", 		 0, 35, 0, 0, 0, 2, Targeting.CONE,			Element.WIND,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BACKDRAFT		("Backdraft", 		 0, 60, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.FIERY_RECOIL},	""),
	RUSH			("Rush", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	WILD_SWING		("Wild Swing", 		0, -1, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.AS_WEAPON,	true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BEATDOWN		("Beatdown", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DOUBLE_DAMAGE_HALF_HIT},	""),
	BLITZ			("Blitz", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_DAMAGE_DOUBLE_HIT},	""),
	FIRE_SWORD		("Fire Sword", 		10, 54, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BOLT_SWORD		("Bolt Sword", 		10, 54, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.LIGHTNING,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	ICE_SWORD		("Ice Sword", 		10, 54, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.ICE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	ULTIMA_SWORD	("Ultima Sword", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE},	""),
	WARCRY			("Warcry", 			 0,  0, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SPEED_DOWN},	""),
	CHEER			("Cheer", 			 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_BOOST},	""),
	SOUL_SPHERE		("Soul Sphere", 	 0, -1, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE},	""),
	LIFEBREAK		("Lifebreak", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.DARK,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HP_LOST_DAMAGE},	""), 
	JUMP			("Jump", 			 0, -1, 4, 0, 0, 0,	Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	LANCET			("Lancet", 			 0, 35, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE_CAPPED, SkillEffect.DRAIN},	""),
	FIRE_BREATH		("Fire Breath", 	 0, 40, 0, 0, 0, 2, Targeting.CONE,			Element.FIRE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BOLT_BREATH		("Bolt Breath", 	 0, 40, 0, 0, 0, 2, Targeting.CONE,			Element.LIGHTNING,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	ICE_BREATH		("Ice Breath", 		 0, 40, 0, 0, 0, 2, Targeting.CONE,			Element.ICE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	WYRMKILLER		("Wyrmkiller", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BANGAA_CRY		("Bangaa Cry", 		 0, 45, 0, 0, 0, 2, Targeting.CONE,			Element.NULL,		true,	false,	true,	true,	true,	false,	false,	false,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	MOG_ATTACK		("Mog Attack", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	MOG_GUARD		("Mog Guard", 		 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DEFENSE},	""),
	MOG_LANCE		("Mog Lance", 		0, -1, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	MOG_RUSH		("Mog Rush", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DOUBLE_DAMAGE_HALF_HIT},	""),
	MOG_SHIELD		("Mog Shield", 		 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_ASTRA},	""),
	MOG_AID			("Mog Aid", 		 0, 35, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.ESUNA_EFFECT},	""),
	ULTIMA_CHARGE	("Ultima Charge", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE},	""),
	SWARMSTRIKE		("Swarmstrike", 	 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	true,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON},	""),
	SHADOWSTICK		("Shadowstick",		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SPEED_DOWN},	""),
	CHECKMATE		("Checkmate", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DOOM},	""),
	FEATHERBLOW		("Featherblow", 	 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_DAMAGE_DOUBLE_HIT},	""),
	SWALLOWTAIL		("Swallowtail", 	 0, -1, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.AS_WEAPON,	true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	MANASTRIKE		("Manastrike", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE},	""),
	PIERCETHROUGH	("Piercethrough", 	 0, -1, 2, 2, 0, 0,	Targeting.DIRECTIONAL,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	NIGHTHAWK		("Nighthawk", 		 0, -1, 4, 0, 0, 0,	Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	THROW			("Throw", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	WOOD_VEIL		("Wood Veil", 		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_IMMOBILIZE},	""),
	FIRE_VEIL		("Fire Veil", 		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	false,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_CONFUSE},	""),
	EARTH_VEIL		("Earth Veil",		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SLOW, SkillEffect.EFF1DEP_DELAY},	""),
	METAL_VEIL		("Metal Veil", 		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND},	""),
	WATER_VEIL		("Water Veil", 		 4, 15, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.WATER,		true,	false,	true,	true,	false,	false,	false,	false,	true,	false,	false,	true,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SILENCE},	""),
	UNSPELL			("Unspell", 		 4,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.DISPEL_EFFECT},	""),
	OBLIVION		("Oblivion", 		24,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_ADDLE},	""),
	SHADOWBIND		("Shadowbind", 		12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_STOP},	""),
	LAST_BREATH		("Last Breath", 	32,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DEATH},	""),
	APHONIA			("Aphonia", 		12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SILENCE},	""),
	NIGHTMARE		("Nightmare", 		18,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DOOM, SkillEffect.ADD_SLEEP},	""),
	AGUE			("Ague", 			12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLOW},	""),
	ROCKSEAL		("Rockseal", 		32,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_PETRIFY},	""),
	ULTIMA_MASHER	("Ultima Masher", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE},	""),
	STEAL_ARMOR		("Steal: Armor",	 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.STEAL_ARMOR},	""),
	STEAL_SHIELD	("Steal: Shield", 	 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.STEAL_SHIELD},	""),
	STEAL_ACCESS	("Steal: Access", 	 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.STEAL_ACCESSORY},	""),
	STEAL_HELM		("Steal: Helm", 	 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.STEAL_HELM},	""),
	STEAL_WEAPON	("Steal: Weapon", 	 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.STEAL_WEAPON},	""),
	STEAL_JP		("Steal: JP", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	STEAL_ABILITY	("Steal: Ability", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	BOOST			("Boost", 			 0,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_BOOST},	""),
	AIM_LEGS		("Aim: Legs", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_IMMOBILIZE},	""),
	AIM_ARM			("Aim: Arm", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DISABLE},	""),
	CUPID			("Cupid", 			 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.ADD_CHARM},	""),
	TAKE_AIM		("Take Aim", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_DAMAGE_NEVER_MISS},	""),
	FASTER			("Faster", 			 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	true,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BLACKOUT		("Blackout", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_BLIND},	""),	
	SONIC_BOOM		("Sonic Boom", 		 0, -1, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	true,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	ADVICE			("Advice", 			 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_ADVICE},	""),
	AIM_VITALS		("Aim: Vitals", 	 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.AIM_VITALS},	""),
	HUNTING			("Hunting", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	ADDLE			("Addle", 			 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_ADDLE},	""),
	ULTIMA_SHOT		("Ultima Shot", 	60, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.TRIPLE_DAMAGE},	""),
	SIDEWINDER		("Sidewinder", 		 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	GOBLIN			("Goblin", 			 0, 0, 0, 0, 0, 1, 	Targeting.SELF_CENTER,	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	FLAN			("Flan", 			 0, 0, 0, 0, 0, 2, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	BOMB			("Bomb", 			 0, 0, 0, 0, 0, 3, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	DRAGON			("Dragon", 			 0, 0, 0, 0, 0, 4, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	LAMIA			("Lamia", 			 0, 0, 0, 0, 0, 5, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	BUG				("Bug", 			 0, 0, 0, 0, 0, 6, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	PANTHER			("Panther",			 0, 0, 0, 0, 0, 7, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	MALBORO			("Malboro", 		 0, 0, 0, 0, 0, 8, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	FLOATEYE		("Floateye", 		 0, 0, 0, 0, 0, 9, 	Targeting.SELF_CENTER, 	Element.NULL, 		true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MORPH},	""),
	HURL			("Hurl", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	RING			("Ring", 			 0,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_STOP},	""),
	FIREBOMB		("Firebomb", 		 0, 30, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BERSERK},	""),
	BALL			("Ball", 			 0,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.ADD_CONFUSE},	""),
	DAGGER			("Dagger", 			 0, 35, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_DISABLE},	""),		
	SMILE			("Smile", 			 0,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_QUICK},	""),
	GIL_TOSS		("Gil Toss", 		 0,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIXED_DAMAGE_30},	""),
	BESO_TOXICO		("Beso Toxico", 	 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	true,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON},	""),
	DEATH_SICKLE	("Death Sickle", 	 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_DOOM},	""),
	DOOM_ARCHER		("Doom Archer", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	DOUBLESHOT		("Doubleshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.HALF_FIGHT_DAMAGE, SkillEffect.HALF_FIGHT_DAMAGE},	""),
	AIM_ARMOR		("Aim: Armor", 		 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.BREAK_ARMOR},	""),
	AIM_WEAPON		("Aim: Weapon", 	 0,  0, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.BREAK_WEAPON},	""),
	SHEEP_COUNT		("Sheep Count", 	 8,  0, 4, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLEEP},	""),
	HUNDY_WOOL		("100% Wool", 		 8,  0, 0, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SHELL, SkillEffect.ADD_PROTECT},	""),
	CUISINE			("Cuisine", 		32,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	 false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.FULLY_HEAL_HP},	""),
	TAIL_WAG		("Tail Wag", 		 8,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.ADD_CHARM},	""),
	CHOCOBO_RUSH	("Chocobo Rush", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	FROGSONG		("Frogsong", 		18,  0, 4, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_FROG},	""),
	FRIEND			("Friend", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	CATNIP			("Catnip", 			12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.ADD_BERSERK},	""),
	FIRESHOT		("Fireshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.FIRE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BOLTSHOT		("Boltshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.LIGHTNING,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	ICESHOT			("Iceshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.ICE,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	CONFUSHOT		("Confushot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_CONFUSE},	""),
	CHARMSHOT		("Charmshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	false,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_CHARM},	""),
	BLINDSHOT		("Blindshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND},	""),
	SILENSHOT		("Silenshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_SILENCE},	""),
	STOPSHOT		("Stopshot", 		 0, -1, 0, 0, 0, 0, Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_STOP},	""),
	RED_SPRING		("Red Spring", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	BLUE_SCREW		("Blue Screw", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	GREEN_GEAR		("Green Gear", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	SILVER_DISC		("Silver Disc", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	GOLD_BATTERY	("Gold Battery", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	BLACK_INGOT		("Black Ingot", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	CHROMA_GEM		("Chroma Gem", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	YELLOW_SPRING	("Yellow Spring", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	POTION			("Potion", 			 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIXED_HEALING_25},	""),
	HI_POTION		("Hi-Potion", 		 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIXED_HEALING_50},	""),
	X_POTION		("X-Potion", 		 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIXED_HEALING_150},	""),
	ETHER			("Ether", 			 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FIXED_MP_HEALING_80},	""),
	ELIXIR			("Elixir", 			 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FULLY_HEAL_HP, SkillEffect.FULLY_HEAL_MP},	""),
	PHOENIX_DOWN	("Phoenix Down", 	 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		false,	true,	true,	true,	false,	false,	false,	true,	false,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.REVIVE_HALF_HP},	""),
	ECHO_SCREEN		("Echo Screen", 	 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEAL_SILENCE},	""),
	MAIDEN_KISS		("Maiden Kiss", 	 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEAL_FROG},	""),
	SOFT			("Soft", 			 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEAL_PETRIFY},	""),
	ANTIDOTE		("Antidote", 		 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEAL_POISON},	""),
	EYE_DROPS		("Eye Drops", 		 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEAL_DARKNESS},	""),
	BANDAGE			("Bandage", 		 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEAL_BIND},	""),
	CUREALL			("Cureall", 		 0, 0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.CUREALL_EFFECT},	""),
	DRAW_WEAPON		("Draw Weapon", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	GOBLIN_PUNCH	("Goblin Punch", 	 8, 30, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.GOBLIN_PUNCH_EFFECT},	""),
	MAGIC_HAMMER	("Magic Hammer", 	 8, 38, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE},	""),
	MUTILATE		("Mutilate", 		18,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[]{SkillEffect.MUTILATE_EFFECT, SkillEffect.DRAIN},	""),
	ACID			("Acid", 			12,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ACID},	""),
//	SACRIFICE		("Sacrifice", 		 0,  0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	BLOWUP			("Blowup", 			 2, 60, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.SELFDESTRUCT},	""),
	FLAME_ATTACK	("Flame Attack", 	 0, 40, 3, 0, 1, 0, Targeting.FREE_SELECT,	Element.FIRE,		true,	false,	true,	true,	false,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	CHILL			("Chill", 			 0, 40, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.ICE,		true,	false,	true,	true,	false,	true,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.FULL_DAMAGE_NEVER_MISS},	""),
	MIGHTY_GUARD	("Mighty Guard", 	 8,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_WDEF_UP, SkillEffect.ADD_MRES_UP},	""),
	GUARD_OFF		("Guard-Off", 		10,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_WDEF_DOWN, SkillEffect.EFF1DEP_ADD_MRES_DOWN},	""),
	DRAGON_FORCE	("Dragon Force",	12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_WATK_UP,	SkillEffect.ADD_WDEF_UP, SkillEffect.ADD_MPOW_UP, SkillEffect.ADD_MRES_UP},	""),
	NIGHT			("Night", 			24,  0, 0, 0, 0, 0, Targeting.ALL,			Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_SLEEP},	""),
	TWISTER			("Twister", 		20,  0, 3, 0, 2, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.HALVE_HP},	""),
	HAND_SLAP		("Hand Slap",		 0, 45, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_DELAY},	""),
	POISON_FROG		("Poison Frog", 	 0,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_POISON, SkillEffect.EFF1DEP_ADD_FROG},	""),
	KISS			("Kiss", 			 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	false,	new SkillEffect[] {SkillEffect.ADD_DOOM, SkillEffect.EFF1DEP_ADD_CHARM},	""),
	LV3_DEF_LESS	("LV3 Def-Less", 	 12,  0, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.LV3_WDEF_DOWN, SkillEffect.LV3_MRES_DOWN},	""),
	SANDSTORM		("Sandstorm", 		 0, 40, 0, 0, 1, 2, Targeting.SELF_CENTER,	Element.EARTH,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_BLIND},	""),
	LV5_DEATH		("LV5 Death", 		 24,  0, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.LV5_DEATH},	""),
	SUFFOCATE		("Suffocate", 		 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.DELAY},	""),
//	RESONATE		("Resonate", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
//	LIMIT_GLOVE		("Limit Glove", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	MATRA_MAGIC		("Matra Magic", 	24,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.MATRA_MAGIC_EFFECT},	""),
//	MUNCH			("Munch", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	POISON_CLAW		("Poison Claw", 	 8, 30, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	true,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON},	""),
	HASTEBREAK		("Hastebreak", 		12,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HASTEBREAK_EFFECT},	""),
	REND			("Rend", 			 0, 35, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE},	""),
	BLASTER			("Blaster", 		 0,  0, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_PETRIFY},	""),
	BAD_BREATH		("Bad Breath", 		20,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.BAD_BREATH, SkillEffect.ADD_POISON, SkillEffect.ADD_BLIND, SkillEffect.ADD_SILENCE, SkillEffect.ADD_FROG, SkillEffect.ADD_SLEEP, SkillEffect.ADD_SLOW},	""),
	GOO				("Goo", 			 0,  0, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	false,	false,	true,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.ADD_IMMOBILIZE},	""),
	SOUNDWAVE		("Soundwave", 		 0,  0, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	false,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.DISPEL_EFFECT},	""),
	STARE			("Stare", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	ROULETTE		("Roulette", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	DEVIL_GAZE		("Devil Gaze", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	CIRCLE			("Circle", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	DRAIN_TOUCH		("Drain Touch", 	10, 20, 1, 2, 0, 0, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	true,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.REGULAR_DAMAGE_CAPPED, SkillEffect.DRAIN},	""),
	LVQ_S_FLARE		("LV? S-Flare", 	30, 55, 0, 0, 0, 0, Targeting.SAME_LEVEL_DIGIT,	Element.DARK,	true,	false,	true,	true,	true,	true,	false,	true,	true,	false,	true,	false,	false,	true,	true,	new SkillEffect[] {SkillEffect.FULL_DAMAGE_NEVER_MISS},	""),
	WHITE_WIND		("White Wind", 		12,  0, 3, 0, 1, 2, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	true,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.WHITE_WIND_EFFECT},	""),
	ANGEL_WHISPER	("Angel Whisper", 	24, 30, 3, 0, 0, 0, Targeting.FREE_SELECT,	Element.HOLY,		true,	false,	true,	true,	false,	true,	false,	false,	false,	false,	true,	false,	false,	false,	true,	new SkillEffect[] {SkillEffect.HEALING_1X, SkillEffect.ADD_AUTO_LIFE},	""),
	ADRAMMALECH		("Adrammalech", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	MATEUS			("Mateus", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	ULTIMA			("Ultima", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	EXODUS			("Exodus", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	FAMFRIT			("Famfrit", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	OMEGA			("Omega", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	ABYSS			("Abyss", 			12, 48, 3, 0, 2, 1, Targeting.FREE_SELECT,	Element.NULL,		true,	false,	true,	true,	false,	true,	false,	false,	true,	false,	false,	false,	false,	true,	true,	new  SkillEffect[] {SkillEffect.REGULAR_DAMAGE, SkillEffect.EFF1DEP_ADD_POISON},	""),
	LIFE_RENDER		("Life Render", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	HEART_RENDER	("Heart Render", 	 0, -1, 0, 0, 0, 0,	Targeting.AS_WEAPON,	Element.AS_WEAPON,	true,	false,	true,	true,	true,	false,	false,	true,	true,	false,	false,	true,	false,	false,	true,	new SkillEffect[] {SkillEffect.MP_DAMAGE},	""),
	RIPCIRCLE		("Ripcircle", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	FURYCIRCLE		("Furycircle", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	
	SENSOR			("Sensor", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	AIM_WALLET		("Aim: Wallet", 	0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	EXORCISE		("Exorcise", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	CAPTURE			("Capture", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	OUST			("Oust", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	CONCEAL			("Conceal", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	STEAL_GIL		("Steal: Gil", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	STEAL_EXP		("Steal: EXP", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	MOG_PEEK		("Mog Peek", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	WYRMTAMER		("Wyrmtamer", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	BURIAL			("Burial", 			0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	""),
	HOLY_WATER		("Holy Water", 		0, 0, 0, 0, 0, 0, null, null, true,	false,	true,	true,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	false,	null,	"");
	
	
	public final String NAME;
	public final int MP_COST, POWER, H_RADIUS, V_RADIUS, H_RANGE, V_RANGE;
	public final Targeting TARGETING;
	public final Element ELEMENT;
	public final boolean TARGET_LIVE, TARGET_DEAD, TARGET_ALLY, TARGET_ENEMY, 
						 IS_PHYSICAL, SELF_TARGET, IGNORE_REACTION, IGNORE_SILENCE, COVERABLE,
						 REFLECTABLE, DOUBLECASTABLE, STEALABLE, TRIGGER_RET_MAG, TRIGGER_ABS_MP,
						 IMPLEMENTED;
	public final SkillEffect[] EFFECTS;
	
	public final String DESC;
	
	public static final FFTASkill values[] = values();
	
	FFTASkill(String name, int mpCost, int power, int hRange, int vRange, int hRadius, int vRadius,
			  Targeting targ, Element element, boolean targetLive, boolean targetDead, boolean targetAlly, 
			  boolean targetEnemy, boolean isPhysical, boolean targetSelf,  boolean ignoreReaction,
			  boolean ignoreSilence, boolean coverable, boolean reflectable, boolean doublecastable,
			  boolean stealable, boolean triggerRetMag, boolean triggerAbsMP, boolean implemented,
			  SkillEffect[] effects, String desc)
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
		TARGET_ALLY = targetAlly;
		TARGET_ENEMY = targetEnemy;
		IS_PHYSICAL = isPhysical;
		SELF_TARGET = targetSelf;
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
		DESC = desc;
	}
	
	public boolean dealsMPDamage()
	{
		return (this == FFTASkill.SOUL_SPHERE && this == FFTASkill.MANASTRIKE &&
				this == FFTASkill.MAGICBREAK && this == FFTASkill.RASP && this == FFTASkill.MAGIC_HAMMER);
	}
	
	public static boolean canUseSkill(FFTASkill sk, ActiveUnit au, int offset)
	{
		boolean result;
		
		// Calculate MP required for skill
		int mpCost = sk.MP_COST;
		if (au.unit.support == FFTASupport.HALF_MP)
			mpCost /= 2;
		else if (au.unit.support == FFTASupport.TURBO_MP)
			mpCost *= 2;
		
		// Does the unit have sufficient MP for this skill?
		result = mpCost <= (au.currMP - offset);
		
		// Is the unit silenced and trying to use a skill that is blocked by silence?
		result = result && (au.status[StatusEffect.SILENCE.ordinal()] == 0 || sk.IGNORE_SILENCE);

		// Is the unit trying to use cover while under cover status?
		result = result && !(sk == FFTASkill.COVER && au.covering != -1);
		
		// Has this skill actually been implemented by the developer?
		result = result && sk.IMPLEMENTED;
		
		// Is this unit not trying to use Jump without a spear?
		result = result && (sk != FFTASkill.JUMP || au.unit.getWeapon(false).type == EquipType.SPEAR);
		
		// Return result
		return result;
	}	
	
	public String getDesc()
	{
		String result = "<center><strong>" + NAME + "</strong><br>"; 
		
		if (!IMPLEMENTED)
		{
			result = "<html><body style='width: 225px; color: gray;'>" + result;
			return result + "(This skill has not been implemented.)</span>";
		}
		
		result += DESC + "<br><span style='width:100px;'>";
		
		if (ELEMENT != null && ELEMENT != Element.NULL)
			result += "Element: " + ELEMENT.name + "<br>";
		if (POWER != 0)
		{
			result += "Power: ";
			if (POWER == -1)
				result += "As weapon<br>";
			else
				result += POWER + "<br>";
		}
		
		return "<html><body style='width: 225px'>" + result + "</span>";
	}
}

enum WeaponReq
{
	NONE, WEAPON, SPEAR, BOW;
}