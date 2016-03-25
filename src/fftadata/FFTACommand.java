package fftadata;
import java.io.Serializable;

public enum FFTACommand implements Serializable
{
	 NONE				(" - "),
	 ITEM				("Item"),
	 BATTLE_TECH_B		("Battle Tech"),
	 DRAGON_TECH		("Dragon Tech"),
	 DEFEND				("Defend"),
	 SPELLBLADE_TECH	("Spellblade Tech"),
	 MONK_TECH			("Monk Tech"),
	 PRAYER				("Prayer Tech"),
	 SACRED_TECH		("Sacred Tech"),
	 BATTLE_TECH_H		("Battle Tech"),
	 CHIVALRY			("Chivalry"),
	 FIGHTER_TECH		("Fighter Tech"),
	 STEAL				("Steal"),
	 NINJA_SKILL		("Ninja Skill"),
	 WHITE_MAGIC		("White Magic"),
	 BLACK_MAGIC		("Black Magic"),
	 PHANTASM_SKILL		("Phantasm Skill"),
	 BLUE_MAGIC			("Blue Magic"),
	 AIM				("Aim"),
	 HUNT				("Hunt"),
	 CALL				("Call"),
	 CHARGE				("Charge"),
	 GUNMANSHIP			("Gunmanship"),
	 STUNT				("Stunt"),
	 PANDORA			("Pandora"),
	 TIME_MAGIC			("Time Magic"),
	 ALCHEMY_PLUS		("Alchemy Skill/Item"),
	 ALCHEMY_SKILL		("Alchemy Skill"),
	 CONTROL			("Control"),
	 MORPH				("Morph"),
	 SAGACITY_SKILL		("Sagacity Skill"),	
	 LUNGE_TECH			("Lunge Tech"),
	 SPIRIT_MAGIC		("Spirit Magic"),
	 RED_MAGIC			("Red Magic"),
	 SUMMON_MAGIC		("Summon Magic"),
	 CORNER				("Corner"),
	 SHARPSHOOT			("Sharpshoot");
	
	final String name;
	
	FFTACommand(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}
