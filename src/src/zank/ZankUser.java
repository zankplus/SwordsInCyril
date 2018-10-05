package zank;

import java.io.Serializable;

public class ZankUser implements Comparable<ZankUser>, Serializable
{
	public String name;
	public String status;
	public String battleID;
	
	public ZankUser(String name)
	{
		this.name = name;
		status = "Ready";
		battleID = null;
	}

	@Override
	public int compareTo(ZankUser other)
	{
		return name.compareTo(other.name);
	}
}
