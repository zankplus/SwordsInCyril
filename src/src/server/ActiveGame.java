package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.*;

import fftadata.*; 
import SwordsInCyril_Old.zank.*; 
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
	boolean doublecast;
	private int[] lastNextData;
	private int[] lastTurnOrderData;
	
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
		synchronized(userlist)
		{
			userlist.add(user);
		}
	}
	
	public void leaveRoom(ActiveUser user)
	{
		synchronized(userlist)
		{
			// Remove user from the list
			userlist.remove(user);
			user.game = null;
			ZankGameAction za = new ZankGameAction(ZankGameActionType.EXIT, id, null, null, user.nickname);
			ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
			try
			{
				sendToAll(zm);
			} catch (Exception e) { e.printStackTrace(); }
		}
		
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
	
	public ZankMessage getStartMessage(boolean spectator)
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.START, id, player1.nickname, player2.nickname, null);
		ZankMessage msg;
		if (spectator)
			msg = new ZankMessage(ZankMessageType.GAME, "spec", action);
		else
			msg = new ZankMessage(ZankMessageType.GAME, null, action);
		return msg;
	}
	
	public ZankMessage getSpecNotice(String username)
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.SPECNOTICE, id, null, null, username);
		return new ZankMessage(ZankMessageType.GAME, null, action);
	}
	
	public ZankMessage getSpecJoinMessage()
	{
		Object[] data = new Object[3];
		data[0] = state;
		data[1] = lastNextData;
		data[2] = lastTurnOrderData;
		
		ZankGameAction action = new ZankGameAction(ZankGameActionType.SPECJOIN, id, null, null, data);
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
		
		lastNextData = new int[] { state.currentUnit, poisonVariance, regenVariance }; 
		ZankGameAction za = new ZankGameAction(ZankGameActionType.NEXT, id, null, null,
				lastNextData);
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		sendToAll(zm);
	
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
			waitUnit(state.currentUnit, au.dir, true);
			
			// Advance to the next turn
			advanceTurn();
		}
		
		// If the current unit is charmed, confused, or berserked
		else if (au.status[StatusEffect.CONFUSE.ordinal()]  > 0 ||
				 au.status[StatusEffect.CHARM.ordinal()] > 0    ||
				 au.status[StatusEffect.BERSERK.ordinal()] > 0)
		{
			// Force them to take the wait action, retaining the same direction
			waitUnit(state.currentUnit, au.dir, true);
			
			// Advance to the next turn
			advanceTurn();
		}
		
		// If this turn isn't being skipped, send a turn order update
		else
		{
			lastTurnOrderData = predictTurnOrder(); 
			za = new ZankGameAction(ZankGameActionType.TURN_ORDER, id, null, null,
					lastTurnOrderData);
			zm = new ZankMessage(ZankMessageType.GAME, null, za);
			sendToAll(zm);
		}
	}
	
	public int[] predictTurnOrder()
	{
		boolean keepGoing = true;
		int round = 0;
		
		ArrayList<Turn> turnList = new ArrayList<Turn>();
		
		
		
		int speed = state.units[state.currentUnit].unit.getTotalSpeed();
		Turn wait = 	new Turn(-5,	(int) Math.ceil( 500.0 / speed), speed - (500 % speed));
		Turn actOnly = 	new Turn(-7,	(int) Math.ceil( 700.0 / speed), speed - (700 % speed));
		Turn moveOnly = new Turn(-8,	(int) Math.ceil( 800.0 / speed), speed - (800 % speed));
		Turn moveAct = 	new Turn(-10,	(int) Math.ceil(1000.0 / speed), speed - (500 % speed));
		
		turnList.add(wait);
		turnList.add(actOnly);
		turnList.add(moveOnly);
		turnList.add(moveAct);
		
		while (keepGoing)
		{
			keepGoing = false;
			for (int i = 0; i < state.units.length; i++)
			{
				if (!(i == state.currentUnit && round == 1) && state.units[i].currHP > 0)
				{
					speed = state.units[i].unit.getTotalSpeed();
					int ctLeft = 1000 * (round + 1) - (state.units[i].counter + state.units[i].reserve);
					int ticksTilTurn = (int) Math.ceil(1.0 * ctLeft / speed);
					ticksTilTurn = Math.max(0, ticksTilTurn);
					int remainder = (speed * ticksTilTurn) - ctLeft;
					
					if (turnList.size() < 20 || ticksTilTurn < turnList.get(19).ticksTilTurn)
					{
						Turn to = new Turn(i, ticksTilTurn, remainder);
						turnList.add(to);
						Collections.sort(turnList);
						keepGoing = true;
					}
				}
			}
			round++;
		}
		
		int[] turnOrder = new int[20];
		for (int i = 0; i < 20; i++)
		{
			turnOrder[i] = turnList.get(i).unit;
			if (turnOrder[i] >= 0)
				System.out.println(turnList.get(i));
		}
		
		return turnOrder;
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
	
	public void waitUnit(int unit, int dir, boolean idle)
	{
		ActiveUnit au = state.units[unit];
		if (idle)
			au.counter = 0;
		else
			au.counter = Math.max(au.counter - 500, 0);
		au.reserve = 0;
		au.dir = dir;
	}
	
	public void doAction(FFTASkill sk, int x, int y) throws InterruptedException
	{
		System.out.println ("Doing " + sk.NAME);
		ActiveUnit au = state.units[state.currentUnit];
		if (au.currHP == 0)
			return; 
		au.counter = Math.max(au.counter - 200, 0);
		
		state.reacting = false;
		SkillEffectResult[][] allResults = executeSkill(state.currentUnit, sk, x, y, false);
		
		System.out.println("Results length: " + allResults.length);
		
		state.reacting = true;
		
		int whichEffect;
		
		for (int i = 0; i < allResults.length; i++)
		{
			ActiveUnit target = state.units[allResults[i][0].target];
			
			if (sk == FFTASkill.RAISE && target.currHP == 0)
				whichEffect = 1;
			else
				whichEffect = 0;
			
			if (/*1*/ state.reactionApplies(au, target, sk, allResults[i][whichEffect].damage > 0) &&
				/*2*/ target.currHP > 0 &&
				/*3*/ !allResults[i][allResults[i].length - 1].autoLife &&
				/*4*/ allResults[i][0].cover == -1)
			{
				target.reacting = true;
				switch(target.unit.reaction)
				{
					case ABSORB_MP:
					{
						boolean miss = true;
						for (int j = 0; j < allResults[i].length; j++)
							if (allResults[i][j] != null && allResults[i][j].success)
								miss = false;
						
						if (/*1*/ !miss)
						{
							int cost = sk.MP_COST;
							if (au.unit.support == FFTASupport.HALF_MP)
								cost /= 2;
							else if (au.unit.support == FFTASupport.TURBO_MP)
								cost *= 2;
							
							state.applyMPHealing(target.id, cost);
							sendReaction(target.id, FFTAReaction.ABSORB_MP, cost);
						}
						break;
					}
				
					case AUTO_REGEN:
					{
						if( /*1*/ allResults[i][whichEffect].success)	// if damaging effect hit
						{
							state.applyStatus(target, StatusEffect.REGEN);
							sendReaction(target.id, FFTAReaction.AUTO_REGEN, 0);
						}
						break;
					}
					
					case BONECRUSHER:
					{
						boolean hit = false;
						for (int j = 0; j < allResults[i].length; j++)
							if (allResults[i][j].success)
								hit = true;
							
						if (/*1*/	hit					&&
							/*2*/	au.currHP > 0 		)
						{
							System.out.println(target.unit.name + " crushes bone!");
							sendReaction(target.id, FFTAReaction.BONECRUSHER, 0);
							
							int facing = intermediateFacing(target.id, au.x, au.y);
							faceUnit(target.id, facing);
							
							executeSkill(target.id, target.getFightSkill(), au.x, au.y, true);
						}
						else
							System.out.println(target.unit.name + " does not crush bone.");
						
						break;
					}
					
					case COUNTER:
						if (/*3*/	au.currHP > 0)
						{
							System.out.println(target.unit.name + " counterattacks!");
							sendReaction(target.id, FFTAReaction.COUNTER, 0);
							
							int facing = intermediateFacing(target.id, au.x, au.y);
							faceUnit(target.id, facing);
							
							executeSkill(target.id, target.getFightSkill(), au.x, au.y, false);
						}
						else
							System.out.println(target.unit.name + " does not counterattack.");
							
						break;
						
					case DRAGONHEART:
					{
						if (/*1*/ allResults[i][whichEffect].success)
						{
							state.applyStatus(target, StatusEffect.AUTO_LIFE);
							sendReaction(target.id, FFTAReaction.DRAGONHEART, 0);
						}
						
						break;
					}
					
					case LAST_HASTE:
					{
						if (/*1*/ target.currHP <= (target.unit.maxHP / 4) &&
							/*2*/ allResults[i][whichEffect].damage + target.currHP > target.unit.maxHP / 4)
						{
							state.applyStatus(target, StatusEffect.HASTE);
							sendReaction(target.id, FFTAReaction.LAST_HASTE, 0);
						}
						break;
					}
					
					case LAST_QUICKEN:
					{
						if (/*1*/ target.currHP <= (target.unit.maxHP / 4) &&
							/*2*/ allResults[i][whichEffect].damage + target.currHP > target.unit.maxHP / 4)
						{
							state.applyStatus(target, StatusEffect.QUICK);
							sendReaction(target.id, FFTAReaction.LAST_QUICKEN, 0);
						}
						break;
					}
					
					case STRIKEBACK:
					{
						if (/*1*/	au.currHP > 0)
						{
							System.out.println(target.unit.name + " strikes back!");
							sendReaction(target.id, FFTAReaction.STRIKEBACK, 0);
							
							int facing = intermediateFacing(target.id, au.x, au.y);
							faceUnit(target.id, facing);
							executeSkill(target.id, target.getFightSkill(), au.x, au.y, false);
						}
						break;
					}
					
					case RETURN_FIRE:
					{
						if (/*1*/ au.currHP > 0)
						{
							sendReaction(target.id, FFTAReaction.RETURN_FIRE, 0);
							
							int facing = intermediateFacing(target.id, au.x, au.y);
							faceUnit(target.id, facing);
							executeSkill(target.id, FFTASkill.RETURN_FIRE, au.x, au.y, false);
						}
						break;
					}
					
					case RETURN_MAGIC:
					{
						int mpCost = sk.MP_COST;
						if (target.unit.support == FFTASupport.HALF_MP)
							mpCost /= 2;
						else if (target.unit.support == FFTASupport.TURBO_MP)
							mpCost *= 2;
						
						if (/*1*/	au.currHP > 0 &&
							/*2*/	mpCost < target.currMP)
						{
							sendReaction(target.id, FFTAReaction.RETURN_MAGIC, 0);
							
							int facing = intermediateFacing(target.id, au.x, au.y);
							faceUnit(target.id, facing);
							executeSkill(target.id, sk, au.x, au.y, false);
						}
						break;
					}
					
					case REFLEX:
					{
						int facing = intermediateFacing(target.id, au.x, au.y);
						faceUnit(target.id, facing);
					}
					
					default:
						System.out.println(target.unit.name + " makes no reaction.");
						break;
				}
				target.reacting = false;
			}
			
			// If unit switched for cover, return both units to their original locations 
			if (state.units[target.id].switchedInFor != -1)
			{
				allResults[i][0].cover = target.id;
				state.swapUnits(target.id, target.switchedInFor);
				target.switchedInFor = -1;
			}
		}
	}
	
	public SkillEffectResult[][] executeSkill(int actor, FFTASkill sk, int x, int y, boolean bonecrusher)
			throws InterruptedException
	{
		ActiveUnit user = state.units[actor];
		
		// Expend skill's cost
		state.expendMP(sk);
		
		// Find skill's targets
		ArrayList<Integer> targets = state.getTargets(x, y, sk, state.units[actor], state.reacting);
		
		// Boost check  
		boolean clearBoostAfterExecuting = (state.units[actor].status[StatusEffect.BOOST.ordinal()] > 0 && 
											sk != FFTASkill.BOOST);
		
		// Cover check
		if (sk.COVERABLE && !state.reacting)	// Cover doesn't apply to reactions
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
			targets = state.getTargets(x, y, sk, state.units[actor], false);
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
		
		// create empty array to hold results
		SkillEffectResult[][] allResults = new SkillEffectResult[targets.size()][];
		
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
				SkillEffectResult result = new SkillEffectResult(actor, target, sk, effects[j], i);
				
				// Determine the results of the current effect
				SkillEffectResult prev = results[Math.max(0, j - 1)];
				results[j] = effects[j].handler.resolveEffect(result, prev, results[0], state, false, bonecrusher);
				
				// Apply those results IF they are not effect1-dependent, or if they are but
				// effect1 was successful, or if it is prev-effect dependent and that was successful
				if (!results[j].dependent || results[0].success || (effects[j] == SkillEffect.DRAIN && results[j - 1].success))
					effects[j].handler.applyEffect(results[j], state);
			}
			
			// Return Fire modifiers
			if (state.reacting && actor == target)
			{
				results[0].success = true;
				results[0].hitChance = 100;
				results[0].damage *= 1.2 + Math.random() * 0.4;
			}
			
			// Boost flag
			if (clearBoostAfterExecuting)
				results[0].boost = true;
			
			// Reflect flag
			if (reflect)
				results[0].reflect = true;
			
			// Add this target's results to master record
			allResults[i] = results;

			// Check for auto-life trigger on current target
			results[results.length - 1].autoLife = state.checkAutoLife(state.units[targets.get(i)]);
			
			// Astra removal check
			for (int j = 0; j < results.length; j++)
			{
				if (results[j].astra)
				{
					state.units[target].status[StatusEffect.ASTRA.ordinal()] = 0;
					j = results.length;
				}
			}
			
			// Send the message
			ZankGameActionType t = ZankGameActionType.HIT;
			if (doublecast && !state.reacting)
				t = ZankGameActionType.DCHIT;
			ZankGameAction za = new ZankGameAction(t, id, null, null, results);
			ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
			sendToAll(zm);			
		}
		
		// Remove boost, if necessary
		if (clearBoostAfterExecuting)
			state.units[actor].status[StatusEffect.BOOST.ordinal()] = 0;
		
		return allResults;
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
		sendToAll(zm);			
		
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
	
	public void faceUnit(int unitNumber, int dir) throws InterruptedException
	{
		state.units[unitNumber].dir = dir;
		ZankGameAction za = new ZankGameAction(ZankGameActionType.WAIT, id, null, null,
									new int[]{unitNumber, dir});
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		sendToAll(zm);
	}
	
	public void sendToAll(ZankMessage msg) throws InterruptedException
	{
		synchronized(userlist)
		{
			for (ActiveUser user : userlist)
				user.messageQueue.put(msg);
		}
	}
	
	public void sendToSpectators(ZankMessage msg) throws InterruptedException
	{
		synchronized(userlist)
		{
			for (ActiveUser user : userlist)
				if (user != player1 && user != player2)
				user.messageQueue.put(msg);
		}
	}
	
	public void sendReaction(int unit, FFTAReaction r, int x) throws InterruptedException
	{
		int[] data = new int[] {unit, r.ordinal(), x};
		ZankGameAction za = new ZankGameAction(ZankGameActionType.REACTION, id, null, null, data);
		ZankMessage zm = new ZankMessage(ZankMessageType.GAME, null, za);
		sendToAll(zm);
	}
	
	public ActiveUnit currentUnit()
	{
		return state.units[state.currentUnit];
	}
	
	class Turn implements Comparable<Turn>
	{ 
		int unit;
		int ticksTilTurn;	// number of ticks that must pass before this unit breaks 1000 combined
							// reserve + counter
		int remainder;		// excess of combined reserve/counter when this unit reaches 1000
		
		public Turn(int unit, int ticksTilTurn, int remainder)
		{
			this.unit = unit;
			this.ticksTilTurn = ticksTilTurn;
			this.remainder = remainder;
		}
		
		@Override
		public int compareTo(Turn t)
		{
			if (t.ticksTilTurn > ticksTilTurn)
				return -1;
			else if (t.ticksTilTurn < ticksTilTurn)
				return 1;
			else if (t.ticksTilTurn == ticksTilTurn)
			{
				if (t.remainder > remainder)
					return 1;
				else if (t.remainder < remainder)
					return -1;
				else if (this.unit < 0)	// for non-unit predictors
					return 1;
				else if (t.unit < 0)
					return -1;
				
				// use priority to resolve speed ties
				else if (state.units[unit].priority > state.units[t.unit].priority)
					return -1;
			}
			return 1;
		}
		
		public String toString()
		{
			return state.units[unit].unit.name + "\t" + state.units[unit].counter + " + " +
					state.units[unit].reserve + "\t" + ticksTilTurn + "R" + remainder;  
		}
	}

	public void unmorph(int data)
	{
		state.units[data].morph = Morph.NONE;
	}

	
}
