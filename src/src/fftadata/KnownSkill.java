package fftadata;

import java.io.Serializable;

public class KnownSkill implements Serializable
{
	private static final long serialVersionUID = 1L;
	public FFTASkill skill;
	public boolean active;
	
	public KnownSkill(FFTASkill skill)
	{
		this.skill = skill;
		active = true;
	}
}
