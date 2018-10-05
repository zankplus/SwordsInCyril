package zank;


import java.io.Serializable;

public class ZankMessage implements Serializable
{
	public final ZankMessageType type;
	public final String user;
	public final Object data;
	
	public ZankMessage(ZankMessageType type, String user, Object data)
	{
		this.type = type;
		this.user = user;
		this.data = data;
	}
	
	public String toString()
	{
		return type + " " + user + " " + data;
	}
}




