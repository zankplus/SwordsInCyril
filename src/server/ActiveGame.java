package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import fftadata.*;
import zank.*;


public class ActiveGame
{
	String id;
	ActiveUser player1, player2;
	List<ActiveUser> userlist;
	GameStatus status;
	ArrayList<ActiveUnit> p1Units, p2Units;
	boolean finishedPrep1, finishedPrep2;
	
	TurnOrder turnOrder;
	ActiveUnit[] units;
	public int currentUnit;
	
	
	public ActiveGame (ActiveUser p1, ActiveUser p2)
	{
		StringBuilder pID = new StringBuilder();
		for (int i = 0; i < 5; i++)
		{
			int x = 65;
			x += (int) (Math.random() * 2) * 32;
			x += (int) (Math.random() * 26);
			pID.append( (char) x );
		}
		id = pID.toString();
		player1 = p1;
		player2 = p2;
//		System.out.println("\np1 is " + player1.nickname + ", p2 is " + p2.nickname);
		status = GameStatus.SETUP;
		finishedPrep1 = finishedPrep2 = false;
		
		// Initialize user list
		userlist = Collections.synchronizedList(new ArrayList<ActiveUser>());
		
		// Both players join the game
		joinRoom(p1);
		joinRoom(p2);
	}
	
	public void joinRoom(ActiveUser user)
	{
		userlist.add(user);
	}
	
	public void leaveRoom(ActiveUser user)
	{
		// Remove user from the list
		userlist.remove(user);
		ZankGameAction za = new ZankGameAction(ZankGameActionType.EXIT, id, null, null, user.nickname);
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		
		try
		{
			synchronized(userlist)
			{
				for (int i = 0; i < userlist.size(); i++)
					userlist.get(i).messageQueue.put(zm);
			}
		} catch (InterruptedException e) { e.printStackTrace(); }
		

	}
	
	// leaveRoom(String): Find the ActiveUser with the username listed and call leaveRoom() on them
	public void leaveRoom(String username)
	{
		ActiveUser user = null;
		
		synchronized(userlist)
		{
			for (int i = 0; i < userlist.size(); i++)
				if (userlist.get(i).nickname.equals(username))
					user = userlist.get(i);
		}
		
		if (user != null)
			leaveRoom(user);
	}
	
	public void initializeTurnOrder()
	{
		turnOrder = new TurnOrder(p1Units, p2Units);
		units = turnOrder.units;
		
		for (int i = 0; i < units.length; i++)
			System.out.println(i + " " + units[i].unit.name);
	}
	
	public ZankMessage getStartMessage()
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.START, id, player1.nickname, player2.nickname, null);
		return new ZankMessage(ZankMessageType.GAME, null, action);
	}
	
	public void advanceTurn() throws InterruptedException
	{
		currentUnit = turnOrder.getNext();
		ZankGameAction za = new ZankGameAction(ZankGameActionType.NEXT, id, null, null, currentUnit);
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		player1.messageQueue.put(zm);
		player2.messageQueue.put(zm);
	}
	
	// Only use this for move actions; use a different method for knockback, since this one depletes counter
	public void moveUnit(int unit, int x, int y, int z)
	{
		ActiveUnit au = units[unit];
		au.counter -= 300;
		au.x = x;
		au.y = y;
		au.z = z;
	}
	
	public void waitUnit(int unit, int dir)
	{
		ActiveUnit au = units[unit];
		au.counter -= 500;
//		System.out.println(au.unit.name + "-->" + au.counter);
		au.reserve = 0;
		au.dir = dir;
	}
	
	public void executeSkill(int[] targets, FFTASkill sk) throws InterruptedException
	{
		int dmg, hitRate, success;
		int[] data = new int[3];
		
		if (sk == FFTASkill.FIGHT)
			for (int i = 0; i < targets.length; i++)
			{
				ActiveUnit au = units[currentUnit];
				ActiveUnit target = units[targets[i]];
				data[0] = targets[i];
				
				hitRate = FFTACalc.getATypeHitRate(au, target, sk);
				int rand = (int) (100 * Math.random());
				
				if (rand < hitRate)
				{
					data[1] = 1;	// Indicates that the attack landed
					
					dmg = FFTACalc.getDamage(au, target, sk);
					int variance = dmg / 10;
					dmg += (int) (2 * variance * Math.random()) - variance;
					
					// Critical check
					rand = (int) (100 * Math.random());
					if (rand < 5)
						dmg = dmg * 3 / 2;
					
					// Cap again
					dmg = Math.min(Math.max(dmg, 0), 999);
					
					// Apply changes server-side
					applyDamage(target, dmg);
					
					// Add damage to message array
					data[2] = dmg;
				}
				
				// Send the message
				ZankGameAction za = new ZankGameAction(ZankGameActionType.HIT, id, null, null, data);
				ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
				player1.messageQueue.put(zm);
				player2.messageQueue.put(zm);

			}
	}
	
	public void applyDamage(ActiveUnit au, int dmg)
	{
		au.currHP -= dmg;
		if (au.currHP <= 0)
		{
			au.currHP = 0;
			au.counter = 0;
			au.reserve = 0;
		}
	}
	
	// Check both teams' HP scores and status to see if either size has lost
	public void victoryCheck() throws InterruptedException
	{
		// Assume both teams have lost by default. If any unit on a team is alive and well,
		// change the loss flag to true.
		boolean p1lose = true, p2lose = true;
		
		// Team 1
		for (int i = 0; i < p1Units.size(); i++)
			if (p1Units.get(i).currHP > 0)
				p1lose = false;
		
		// Team 2
		for (int i = 0; i < p2Units.size(); i++)
			if (p2Units.get(i).currHP > 0)
				p2lose = false;
		
		if (p1lose || p2lose)
			status = GameStatus.COMPLETE;
		
		ZankGameAction za = new ZankGameAction(ZankGameActionType.GAMEOVER, id, null, null, new boolean[]{p1lose, p2lose});
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		player1.messageQueue.put(zm);
		player2.messageQueue.put(zm);			

	}
	
	enum GameStatus { SETUP, ONGOING, COMPLETE }

	public int intermediateFacing(int unitNumber, int x2, int y2)
	{
		ActiveUnit unit = units[unitNumber];
		int x1 = unit.x, y1 = unit.y;
		
		int d_x = x2 - x1, d_y = y2 - y1; 
		
		if (Math.abs(d_x) > Math.abs(d_y))
		{
			if (d_x > 0)
				unit.dir = 3;
			else if (d_x < 0)
				unit.dir = 1;
		}
		else if (Math.abs(d_y) > Math.abs(d_x))
		{
			if (d_y > 0)
				unit.dir = 4;
			else if (d_y < 0)
				unit.dir = 2;
		}
		
		return unit.dir;
	}
	
	public void sendToAll(ZankMessage msg) throws InterruptedException
	{
		synchronized(userlist)
		{
			for (ActiveUser user : userlist)
					user.messageQueue.put(msg);
		}
	}
}
