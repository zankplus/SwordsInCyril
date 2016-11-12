package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.*;

import javax.swing.JOptionPane;

import fftadata.*;
import zank.*;
import fftamap.*;

public class ActiveGame
{
	String id;
	ActiveUser player1, player2;
	List<ActiveUser> userlist;
	GameStatus status;
	FFTAMap map;
	ArrayList<ActiveUnit> p1Units, p2Units;
	boolean finishedPrep1, finishedPrep2;
	
	TurnOrder turnOrder;
	GameState state;
	Lock gameLock;
	int turn;
	
	public ActiveGame (ActiveUser p1, ActiveUser p2, FFTAMap map)
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
		this.map = map;
//		System.out.println("\np1 is " + player1.nickname + ", p2 is " + p2.nickname);
		status = GameStatus.SETUP;
		finishedPrep1 = finishedPrep2 = false;
		turn = 0;
		
		// Initialize user list
		userlist = Collections.synchronizedList(new ArrayList<ActiveUser>());
		
		// Both players join the game
		joinRoom(p1);
		joinRoom(p2);
		
		// Initialize lock
		gameLock = new ReentrantLock();
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
		state = new GameState(turnOrder.units, map);
	}
	
	public ZankMessage getStartMessage()
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.START, id, player1.nickname, player2.nickname, null);
		return new ZankMessage(ZankMessageType.GAME, null, action);
	}
	
	public void advanceTurn() throws InterruptedException
	{
		// Determine next unit to move
		state.currentUnit = turnOrder.getNext();
		
		// Increment the current turn
		state.currentTurn++;
		
		// Grab reference to current unit
		ActiveUnit au = state.units[state.currentUnit];
		
		// Calculate poison and regen variance
		int poisonVariance = 85 + (int) (Math.random() * 30);
		int regenVariance  = 85 + (int) (Math.random() * 30);
		
		// Decrement Stop status
		state.stopTick();
		
		// Apply start of turn effects only if the target is alive and not stopped or petrified
		if (au.status[StatusEffect.STOP.ordinal()] == 0)
		{
			// Apply start of turn effects on server side
			state.startOfTurnEffects(poisonVariance, regenVariance);
			
			// Check for auto-life in case the current unit has been killed by poison or doom 
			state.checkAutoLife(au);
		}

		// Tell the client that it's this unit's turn, whether they're able to take their turn or not.
		// If the unit is dead, petrified, stopped, etc., the client will handle it accordingly.
		ZankGameAction za = new ZankGameAction(ZankGameActionType.NEXT, id, null, null,
				new int[] { state.currentUnit, poisonVariance, regenVariance });
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		player1.messageQueue.put(zm);
		player2.messageQueue.put(zm);
	
		// If the current unit has died of poison/doom this turn...
		if (au.currHP == 0)
		{	
			// See if the game has been won
			boolean gameOver = victoryCheck();
			
			// If it hasn't been, advance to the next turn
			if (!gameOver)
				advanceTurn();
		}
		
		// If the current unit is stopped or asleep...
		else if (au.status[StatusEffect.STOP.ordinal()]  > 0 ||
				 au.status[StatusEffect.SLEEP.ordinal()] > 0 )
		{
			// Force them to take the wait action, retaining the same direction
			waitUnit(state.currentUnit, au.dir);
			
			// Advance to the next turn
			advanceTurn();
		}
		
		// If the current unit is charmed, confused, or berserked
		else if (au.status[StatusEffect.CONFUSE.ordinal()]  > 0 ||
				 au.status[StatusEffect.CHARM.ordinal()] > 0    ||
				 au.status[StatusEffect.BERSERK.ordinal()] > 0)
		{
			// Force them to take the wait action, retaining the same direction
			waitUnit(state.currentUnit, au.dir);
			
			// Advance to the next turn
			advanceTurn();
		}
	}
	
	// Only use this for move actions; use a different method for knockback, since this one depletes counter
	public void moveUnit(int unit, int x, int y, int z)
	{
		ActiveUnit au = state.units[unit];
		au.counter = Math.max(au.counter - 300, 0);
		au.x = x;
		au.y = y;
		au.z = z;
	}
	
	public void waitUnit(int unit, int dir)
	{
		ActiveUnit au = state.units[unit];
		au.counter = Math.max(au.counter - 500, 0);
		au.reserve = 0;
		au.dir = dir;
	}
	
	public void doAction(FFTASkill sk, int x, int y) throws InterruptedException
	{
		ActiveUnit au = state.units[state.currentUnit];
		au.counter = Math.max(au.counter - 200, 0);
		
		executeSkill(state.currentUnit, sk, x, y);
	}
	
	public void executeSkill(int actor, FFTASkill sk, int x, int y) throws InterruptedException
	{
		ActiveUnit user = state.units[actor];
		
		// Expend skill's cost
		state.expendMP(sk);
		
		// Find skill's targets
		ArrayList<Integer> targets = state.getTargets(x, y, sk, state.units[actor]);
		
		// Boost check  
		boolean clearBoostAfterExecuting = (state.units[actor].status[StatusEffect.BOOST.ordinal()] > 0 && 
											sk != FFTASkill.BOOST);
		
		// Cover check
		if (sk.COVERABLE)
		{
			int k;
			for (int i = 0; i < targets.size(); i++)
			{
				k = state.whoCovers(targets.get(i));
				if (k != -1)
				{
					System.out.println("k = " + state.units[k].unit.name);
					System.out.println("i = " + state.units[i].unit.name);
					state.units[k].switchedInFor = targets.get(i);
					state.swapUnits(k, targets.get(i));
				}
			}
			
			// Find targets again using new locations
			targets = state.getTargets(x, y, sk, state.units[actor]);
		}
		
		// Generate effect list
		SkillEffect[] effects;
		
		// If using Double Sword, create new skill list by combining those of each weapon equipped
		if (sk == FFTASkill.DOUBLE_SWORD)
		{
			SkillEffect[] rEffs = user.getWeaponSkill(false).EFFECTS,
						  lEffs = user.getWeaponSkill( true).EFFECTS;
			
			System.out.println("Right weapon: " + user.getWeaponSkill(false));
			System.out.println("Left weapon: "  + user.getWeaponSkill(true));
			
			effects = new SkillEffect[rEffs.length + lEffs.length];
			System.out.print("1");
			for (int i = 0; i < rEffs.length; i++)
				effects[i] = rEffs[i];
			System.out.print(" - 2");
			for (int i = 0; i < lEffs.length; i++)
				effects[i + rEffs.length] = lEffs[i];
			System.out.println(" - 3");
			for (int i = 0; i < effects.length; i++)
				System.out.println("Eff" + i + ": " + effects[i]);
		}
		
		// Otherwise use skill's native effect list
		else
			 effects = sk.EFFECTS;
		
		
		// For each target, apply all effects sequentially
		for (int i = 0; i < targets.size(); i++)
		{
			boolean reflect = false;
			SkillEffectResult[] results = new SkillEffectResult[effects.length];
			int target = targets.get(i);
			
			// Reflect check
			if (sk.REFLECTABLE && state.units[target].status[StatusEffect.REFLECT.ordinal()] > 0 &&
					target != actor)
			{
				target = actor;
				reflect = true;
			}
			
			// Apply effects to target
			for (int j = 0; j < effects.length; j++)
			{
				// Make new result
				SkillEffectResult result = new SkillEffectResult(actor, target, sk, effects[j]);
				
				// Determine the results of the current effect
				SkillEffectResult prev = results[Math.max(0, j - 1)];
				results[j] = effects[j].handler.resolveEffect(result, prev, results[0], false);
				
				// Apply those results IF they are not effect1-dependent, or if they are but
				// effect1 was successful, or if it is prev-effect dependent and that was successful
				if (!results[j].dependent || results[0].success || (effects[j] == SkillEffect.DRAIN && results[j - 1].success))
					effects[j].handler.applyEffect(results[j]);
			}
			
			// If unit switched for cover, return both units to their original locations 
			if (state.units[target].switchedInFor != -1)
			{
				ActiveUnit au = state.units[target];
				results[0].cover = au.id;
				state.swapUnits(au.id, au.switchedInFor);
				au.switchedInFor = -1;
			}
			
			// Boost flag
			if (clearBoostAfterExecuting)
				results[0].boost = true;
			
			// Reflect flag
			if (reflect)
				results[0].reflect = true;
			
			// Send the message
			ZankGameAction za = new ZankGameAction(ZankGameActionType.HIT, id, null, null, results);
			ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
			player1.messageQueue.put(zm);
			player2.messageQueue.put(zm);
			
			// Check for auto-life trigger on current target
			state.checkAutoLife(state.units[targets.get(i)]);
		}
		
		// Remove boost, if necessary
		if (clearBoostAfterExecuting)
			state.units[actor].status[StatusEffect.BOOST.ordinal()] = 0;
	}
	
	// Check both teams' HP scores and status to see if either size has lost
	public boolean victoryCheck() throws InterruptedException
	{
		// Assume both teams to have not yet lost by default. If any unit on a team is alive and well,
		// change the loss flag to false.
		boolean p1lose = true, p2lose = true;
		
		// Team 1
		for (int i = 0; i < p1Units.size(); i++)
			if (p1Units.get(i).currHP > 0 && p1Units.get(i).status[StatusEffect.PETRIFY.ordinal()] == 0)
				p1lose = false;
		
		// Team 2
		for (int i = 0; i < p2Units.size(); i++)
			if (p2Units.get(i).currHP > 0 && p2Units.get(i).status[StatusEffect.PETRIFY.ordinal()] == 0)
				p2lose = false;
		
		if (p1lose || p2lose)
			status = GameStatus.COMPLETE;
		
		ZankGameAction za = new ZankGameAction(ZankGameActionType.GAMEOVER, id, null, null, new boolean[]{p1lose, p2lose});
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		player1.messageQueue.put(zm);
		player2.messageQueue.put(zm);			
		
		return (p1lose || p2lose);
	}
	
	enum GameStatus { SETUP, ONGOING, COMPLETE }

	public int intermediateFacing(int unitNumber, int x2, int y2)
	{
		ActiveUnit unit = state.units[unitNumber];
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
	
	public ActiveUnit currentUnit()
	{
		return state.units[state.currentUnit];
	}
}
