package server;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import fftadata.*;
import zank.*;


public class ActiveGame
{
	String id;
	ActiveUser player1, player2;
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
		System.out.println("\np1 is " + player1.nickname + ", p2 is " + p2.nickname);
		status = GameStatus.SETUP;
		finishedPrep1 = finishedPrep2 = false;
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
		System.out.println(au.unit.name + "-->" + au.counter);
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
	
	enum GameStatus { SETUP, ONGOING, COMPLETE };
}
