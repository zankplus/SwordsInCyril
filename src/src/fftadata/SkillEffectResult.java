package fftadata;

import java.io.Serializable;

public class SkillEffectResult implements Serializable
{
	public FFTASkill skill;
	public SkillEffect effect;
	
	public int user, target, damage, hitChance;
	public boolean success, critical;
	
	public SkillEffectResult(int user, int target, FFTASkill skill, int effectIndex)
	{
		this.user = user;
		this.target = target;
		this.skill = skill;
		this.effect = skill.EFFECTS[effectIndex];
		
		damage = 0;
		hitChance = 0;
		success = false;
		critical = false;
	}
}
