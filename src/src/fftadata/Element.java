package fftadata;

public enum Element
{
	NULL("Non"), FIRE("Fire"), LIGHTNING("Lightning"), ICE("Ice"), WIND("Wind"), EARTH("Earth"), WATER("Water"), HOLY("Holy"), DARK("Dark"), AS_WEAPON("As Weapon");
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