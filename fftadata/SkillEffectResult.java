package fftadata;

import java.io.Serializable;

public class SkillEffectResult implements Serializable
{
	public FFTASkill skill;
	public SkillEffect effect;
	
	public int user, target, damage, hitChance, cover, slot;
	public boolean success, critical, dependent, boost, reflect, autoLife;
	
	public SkillEffectResult(int user, int target, FFTASkill skill, SkillEffect effect)
	{
		this.user = user;
		this.target = target;
		this.skill = skill;
		this.effect = effect;
		
		damage = 0;
		hitChance = 0;
		slot = -1;
		success = false;
		critical = false;
		dependent = false;
		boost = false;
		reflect = false;
		cover = -1;
		autoLife = false;
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
