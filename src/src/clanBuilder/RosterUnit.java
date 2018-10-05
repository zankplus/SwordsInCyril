package clanBuilder;

import java.io.Serializable;

import fftadata.FFTAUnit;

public class RosterUnit implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public FFTAUnit base;
	public String note;
	
	public RosterUnit (FFTAUnit unit)
	{
		this.base = unit;
		note = "";
	}
	
	public RosterUnit()
	{
		this(new FFTAUnit());
	}
}
