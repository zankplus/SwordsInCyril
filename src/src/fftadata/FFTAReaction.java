package fftadata;

public enum FFTAReaction {

	 NONE			(" -"),
	 ABSORB_MP		("Absorb MP"),
	 AUTO_REGEN		("Auto-Regen"),
	 BONECRUSHER	("Bonecrusher"),
	 BLOCK_ARROWS	("Block Arrows"),
	 CATCH			("Catch"),
	 COUNTER		("Counter"),
	 DAMAGE_TO_MP	("Damage > MP"),
	 DRAGONHEART	("Dragonheart"),
	 LAST_BERSERK	("Last Berserk"),
	 LAST_HASTE		("Last Haste"),
	 LAST_QUICKEN	("Last Quicken"),
	 REFLEX			("Reflex"),
	 RETURN_FIRE	("Return Fire"),
	 RETURN_MAGIC	("Return Magic"),
	 STRIKEBACK		("Strikeback");
	
	final String name;
	
	FFTAReaction(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}
