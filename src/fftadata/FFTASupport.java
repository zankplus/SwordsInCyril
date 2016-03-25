package fftadata;

public enum FFTASupport
{
	 NONE			(" -"),
	 CONCENTRATE	("Concentrate"),
	 DOUBLE_SWORD	("Double Sword"),
	 DOUBLEHAND		("Doublehand"),
	 GEOMANCY		("Geomancy"),
	 HALF_MP		("Half MP"),
	 IMMUNITY		("Immunity"),
	 LEARNING		("Learning"),
	 MPOW_PLUS		("Magic Pow+"),
	 MAINTENANCE	("Maintenance"),
	 MONKEYGRIP		("Monkeygrip"),
	 SHIELDBEARER	("Shieldbearer"),
	 TURBO_MP		("Turbo MP"),
	 WATK_PLUS		("Weapon Atk+"),
	 WDEF_PLUS		("Weapon Def+");
	
	final String name;
	
	FFTASupport(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}
