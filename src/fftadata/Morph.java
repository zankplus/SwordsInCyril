package fftadata;

public enum Morph
{
	NONE		("None", 		null), 
	GOBLIN		("Goblin",		FFTACommand.GOBLIN), 
	FLAN 		("Flan",		FFTACommand.FLAN),
	BOMB 		("Bomb",		FFTACommand.BOMB),
	DRAGON		("Dragon",		FFTACommand.DRAGON),
	LAMIA		("Lamia",		FFTACommand.LAMIA),
	BUG			("Bug",			FFTACommand.BUG),
	PANTHER		("Panther",		FFTACommand.PANTHER),
	MALBORO		("Malboro",		FFTACommand.MALBORO),
	FLOATEYE	("Floateye",	FFTACommand.FLOATEYE);
	
	public String name;
	public FFTACommand skillset;
	
	Morph(String name, FFTACommand skillset)
	{
		this.name = name;
		this.skillset = skillset;
	}
}
