package fftadata;

import java.io.Serializable;

public class SkillEffectResult implements Serializable
{
	public FFTASkill skill;
	public SkillEffect effect;
	
	public int user, target, damage, hitChance, cover;
	public boolean success, critical, dependent, reflect;
	
	public SkillEffectResult(int user, int target, FFTASkill skill, int effectIndex)
	{
		this.user = user;
		this.target = target;
		this.skill = skill;
		if (effectIndex >= 0)
			this.effect = skill.EFFECTS[effectIndex];
		else
			this.effect = null;
		
		damage = 0;
		hitChance = 0;
		success = false;
		critical = false;
		dependent = false;
		reflect = false;
		cover = -1;
	}
	
	public SkillEffectResult()
	{
		this.user = -1;
		this.target = -1;
		this.skill = null;
		this.effect = null;
		
		damage = 0;
		hitChance = 0;
		success = false;
		critical = false;
		dependent = false;
	}
}
