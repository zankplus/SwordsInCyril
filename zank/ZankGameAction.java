package zank;

import java.io.Serializable;

public class ZankGameAction implements Serializable
{
	public final ZankGameActionType type;
	public final String gameID;
	public final String player1, player2;
	public final Object data;
	
	public ZankGameAction (ZankGameActionType type, String gameID, String player1, String player2, Object data)
	{
		this.type = type;
		this.gameID = gameID;
		this.player1 = player1;
		this.player2 = player2;
		this.data = data;
	}
	
	public String toString()
	{
		return "[" + type + " " + gameID + " " + data + "]";
	}
}
