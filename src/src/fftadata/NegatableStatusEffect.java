package fftadata;

public enum NegatableStatusEffect 
{
	FROG			(StatusEffect.FROG,			true,	new ItemEffect[] {ItemEffect.NULL_FROG, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE}), 
	DARKNESS		(StatusEffect.DARKNESS,		true,	new ItemEffect[] {ItemEffect.NULL_BLIND, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE}), 
	CONFUSE			(StatusEffect.CONFUSE,		true,	new ItemEffect[] {ItemEffect.NULL_CONFUSION, ItemEffect.MINDU_GEM, ItemEffect.RIBBON}),
	SLEEP			(StatusEffect.SLEEP,		true,	new ItemEffect[] {ItemEffect.NULL_SLEEP, ItemEffect.RIBBON, ItemEffect.CACHUSHA}), 
	PETRIFY			(StatusEffect.PETRIFY,		true,	new ItemEffect[] {ItemEffect.NULL_PETRIFY, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.CACHUSHA}),
	SILENCE			(StatusEffect.SILENCE,		true,	new ItemEffect[] {ItemEffect.NULL_SILENCE, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE}), 
	POISON			(StatusEffect.POISON,		true,	new ItemEffect[] {ItemEffect.NULL_POISON, ItemEffect.MINDU_GEM, ItemEffect.RIBBON, ItemEffect.BARETTE}), 
	STOP			(StatusEffect.STOP,			false,	new ItemEffect[] {ItemEffect.NULL_STOP, ItemEffect.RIBBON, ItemEffect.CACHUSHA}), 
	SLOW			(StatusEffect.SLOW,			false,	new ItemEffect[] {ItemEffect.NULL_SLOW, ItemEffect.RIBBON, ItemEffect.BARETTE}), 
	CHARM			(StatusEffect.CHARM,		false,	new ItemEffect[] {ItemEffect.NULL_CHARM, ItemEffect.RIBBON, ItemEffect.CACHUSHA}), 
	IMMOBILIZE		(StatusEffect.IMMOBILIZE,	false,	new ItemEffect[] {ItemEffect.NULL_IMMOBILIZE, ItemEffect.RIBBON, ItemEffect.BARETTE}),
	DISABLE			(StatusEffect.DISABLE,		false,	new ItemEffect[] {ItemEffect.NULL_DISABLE, ItemEffect.RIBBON, ItemEffect.BARETTE}), 
	BERSERK			(StatusEffect.BERSERK,		false,	new ItemEffect[] {ItemEffect.NULL_BERSERK, ItemEffect.RIBBON, ItemEffect.CACHUSHA}),
	DOOM			(StatusEffect.DOOM,			false,	new ItemEffect[] {ItemEffect.NULL_DOOM, ItemEffect.RIBBON, ItemEffect.BARETTE}),
	DEATH			(StatusEffect.DEATH,		false,	new ItemEffect[] {ItemEffect.NULL_KO, ItemEffect.RIBBON, ItemEffect.CACHUSHA}),
	ADDLE			(StatusEffect.ADDLE,		false,	new ItemEffect[] {ItemEffect.RIBBON}), 
	MISC_EFFECT		(StatusEffect.MISC_EFFECT,	false,	new ItemEffect[] {ItemEffect.RIBBON});
	
	public final StatusEffect STATUS_EFFECT;
	public final boolean IMMUNITY_BLOCKS;
	public final ItemEffect[] NEGATORS;
	
	public static final int NUM_EFFECTS_BLOCKED_BY_IMMUNITY = 7;
	
	NegatableStatusEffect(StatusEffect eff, boolean immunityBlocks, ItemEffect[] negators)
	{
		STATUS_EFFECT = eff;
		IMMUNITY_BLOCKS = immunityBlocks;
		NEGATORS = negators;
	}
}