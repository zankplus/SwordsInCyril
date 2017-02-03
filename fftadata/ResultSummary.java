package fftadata;

public class ResultSummary
{
	SkillEffectResult[] results;
	boolean reactionApplies;
	boolean autoLife;
	
	public ResultSummary(SkillEffectResult[] results, boolean reactionApplies, boolean autoLife)
	{
		this.results = results;
		this.reactionApplies = reactionApplies;
		this.autoLife = autoLife;
	}
}
