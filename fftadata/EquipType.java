package fftadata;

public enum EquipType
{
	SWORD("Swords"), BLADE("Blades"), SABER("Sabers"), KNIGHTSWORD("Knightswords"), GREATSWORD("Greatswords"), BROADSWORD("Broadswords"),
	KNIFE("Knives"), RAPIER("Rapiers"), KATANA("Katanas"), STAFF("Staves"), ROD("Rods"), MACE("Maces"),  BOW("Bows"), GREATBOW("Greatbows"),
	SPEAR("Spears"), INSTRUMENT("Instruments"), KNUCKLES("Knuckles"), SOUL("Souls"), GUN("Guns"), SHIELD("Shields"), HELM("Helms"), RIBBON("Ribbons"),
	HAT("Hats"), ARMOR ("Armors"), CLOTHING("Clothing"), VIERA_CLOTHES("Clothing (Viera)"), ROBE("Robes"), BOOTS("Boots"),
	GLOVES("Gloves"), ACCESSORY("Accessories"), UNARMED("Unarmed"), NONE("None");
	
	public final String name;
	
	EquipType(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}