package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTMLDocument;

import fftadata.ActiveUnit;
import fftadata.EquipType;
import fftadata.FFTAEquip;
import fftadata.FFTASkill;
import fftadata.GameState;
import fftadata.SkillEffect;
import fftadata.SkillEffectResult;
import fftadata.StatusEffect;
import fftadata.Targeting;
import zank.ZankGameAction;
import zank.ZankGameActionType;
import zank.ZankMessage;
import zank.ZankMessageType;
import zank.ZankUser;

public class Engagement
{
	Socket socket;
	ZankUser player, opponent;
	int playerNumber;
	String gameID;
	boolean gameOver;
	
	
	ObjectInputStream in;
	ObjectOutputStream out;
	ZankGameMap map;
	ZankGameAction action;
	ZankMessage message;
	
	ArrayList<ActiveUnit> p1Units, p2Units;
	
	EngagementWindow window;
	private GameState state;
	
	
	public Engagement(ZankUser player, int playerNumber, ZankUser opponent, String id, ObjectInputStream in, ObjectOutputStream out)
	{
		this.player = player;
		this.playerNumber = playerNumber;
		this.opponent = opponent;
		this.gameID = id;
		this.in = in;
		this.out = out;
		gameOver = false;
		
		map = MuscadetMapLoader.getMap();
		window = new EngagementWindow(this);
	}
	
	
	public void sendChat(String content) throws IOException
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.CHAT, gameID, null, null, content);
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendForfeit() throws IOException
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.CHAT, gameID, null, null, null);
		ZankMessage message = new ZankMessage(ZankMessageType.LOGIN, player.username, action);
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendReady() throws IOException
	{
		ZankGameAction action = new ZankGameAction(ZankGameActionType.READY, gameID, null, null, window.getYourUnits());
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendMove() throws IOException
	{
		ActiveUnit au = currentUnit();
		int[] data = {au.id, au.x, au.y, au.z};
		ZankGameAction action = new ZankGameAction(ZankGameActionType.MOVE, gameID, null, null, data);
		ZankMessage message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendAction(ArrayList<Integer> targets, FFTASkill sk, int x, int y) throws IOException
	{
		int[] data = new int[targets.size() + 3];
		for (int i = 0; i < targets.size(); i++)
			data[i] = targets.get(i);
		data[data.length - 3] = sk.ordinal();
		data[data.length - 2] = x;
		data[data.length - 1] = y;
		
		System.out.println("sendAction: target = " + data[0]);
		
		action = new ZankGameAction(ZankGameActionType.ACT, gameID, null, null, data);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendWait(int dir) throws IOException
	{
		int[] data = {state.currentUnit, dir};
		action = new ZankGameAction(ZankGameActionType.WAIT, gameID, null, null, data);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendTurnTest(int ct) throws IOException
	{
		action = new ZankGameAction(ZankGameActionType.TURNTEST, gameID, null, null, ct);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	public void sendExit() throws IOException
	{
		action = new ZankGameAction(ZankGameActionType.EXIT, gameID, null, null, null);
		message = new ZankMessage(ZankMessageType.GAME, player.username, action);
		
		synchronized(out)
		{
			out.writeObject(message);
			out.flush();
		}
	}
	
	
	// ZankMessage handlers
	// CHAT: append the message to the engagement chat and move the caret to the bottom
	public void receiveChat(String user, String msg)
	{
		window.appendToChat("<b>" + user + "</b>: " + msg);
		System.out.println("chat ew: " + window);
	}
	
	// READY: register the received units to the rival team and create a list all the game's units in gamePanel
	// before telling gamePanel to start the game.
	public void receiveReady(ArrayList<ActiveUnit> units)
	{
		if (playerNumber == 1)
			p2Units = units;
		else if (playerNumber == 2)
			p1Units = units;
		
		ActiveUnit[] aus = new ActiveUnit[p1Units.size() + p2Units.size()];
		
		for (int i = 0; i < p1Units.size(); i++)
		{
			aus[i] = p1Units.get(i);
			aus[i].id = i;
			System.out.println(i + " " + aus[i].unit.name);
		}
		
		for (int i = 0; i < p2Units.size(); i++)
		{
			aus[i + p1Units.size()] = p2Units.get(i);
			aus[i + p1Units.size()].id = i + p1Units.size();
		}
		
		state = new GameState(aus);
		beginGame();
		window.setupPreviews();
		System.out.println("Finished setting up previews, now beginning game");
		window.repaint();
	}
	
	// NEXT: initiate the given character's turn
	public void receiveNext(int[] data)
	{
		state.currentUnit = data[0];
		
		ActiveUnit au = currentUnit();
		
		// Decrement this unit's Stop counter and check whether it has abated this turn
		boolean stopEnded = state.stopTick();
		
		if (au.status[StatusEffect.STOP.ordinal()] > 0 && !stopEnded)
			{}
		else
		{
			if (au.status[StatusEffect.SLEEP.ordinal()] > 0)
				window.appendToChat("<em><span style=\"color:gray\"><strong>" + au.unit.name + "</strong> is asleep!");
			else if (stopEnded)
				window.appendToChat("<em><span style=\"color:gray\"><strong>" + au.unit.name + "</strong> is back in time!");
			else if (au.status[StatusEffect.PETRIFY.ordinal()] == 0)
				window.appendToChat("<em><span style=\"color:gray\"><strong>" + au.unit.name + "</strong> takes their turn!");
			
			// Announce status effects that are abating this turn
			window.startOfTurnAnnouncements(au);
			
			// Apply poison damage (if applicable) 
			int poisonDamage = state.startOfTurnEffects(data[1]);		// data[1] is poisonVariance
			
			// Announce poison damage (if applicable) and update displays to reflect it
			if (poisonDamage > 0)
			{
				window.appendToChat("<em><span style=\"color:gray\">...<strong>" + au.unit.name + 
						"</strong> takes " + poisonDamage + " damage from poison!");
				
				window.updateSprite(getUnits()[au.id]);
				if (au.currHP == 0)
					window.appendToChat("<em><span style=\"color:gray\">......<strong>" + au.unit.name + " falls!");
			}
			
			window.updateUnitPreview(au.id);
		}
		
		
		
		if (au.currHP > 0 && au.status[StatusEffect.PETRIFY.ordinal()] == 0 &&
							 au.status[StatusEffect.STOP.ordinal()]    == 0 && 
							 au.status[StatusEffect.SLEEP.ordinal()]   == 0 )
		{
			// Select current unit's tile
			window.selectTile(map.mapData[au.x][au.y]);
			
			// Decide which panel to show
			if (currentUnit().team == playerNumber)	// Show the action panel if it's your turn
				window.startPlayerTurn();
			else
				window.startRivalTurn();			// Show the blank panel if it's not
		}
		else
			window.startRivalTurn();
		
	}
	
	// MOVE: move the indicated unit using MapPanel's moveUnit method
	public void receiveMove(int[] data)
	{
		ActiveUnit au = state.units[data[0]];
		ZankMapTile dest = map.mapData[data[1]][data[2]];
		window.moveUnit(au, dest);
	}
	
	// ACT: announce in chat that the active unit has taken the indicated action
	public void receiveAct(int[] data)
	{
		System.out.println("receiveAct: target = " + data[0]);
		
		FFTASkill sk = FFTASkill.values[data[data.length - 3]];
		if (sk == FFTASkill.FIGHT)
			window.appendToChat("<em><span style=\"color:gray\">...<strong>" + currentUnit().unit.name +
				"</strong> attacks <strong>" + state.units[data[0]].unit.name + "</strong>!");
		else
			window.appendToChat("<em><span style=\"color:gray\">...<strong>" + currentUnit().unit.name +
					"</strong> uses " + sk.NAME + " on <strong>" + state.units[data[0]].unit.name + "</strong>!");
		
		state.expendMP(sk);
		window.updateUnitPreview(currentID());
		System.out.println("Received " + sk.NAME);
	}
	
	// HIT: apply the effects of skills specified and announce the results in chat
	public void receiveHit(SkillEffectResult[] results)
	{
		// apply each effect in sequence and append the report to chat
		for (int i = 0; i < results.length; i++)
		{
			String report = state.applyEffect(results[i]);
			
			// Update target sprite to reflect any changes in HP
			window.updateSprite(getUnits()[results[i].target]);
			
			// Update preview panels to reflect any stat changes
			window.updateUnitPreview(results[i].target);
			
			// Append report to chat
			window.appendToChat(report);
			
			ActiveUnit au = state.units[results[i].target]; 
			if (au.currHP == 0)
				window.appendToChat("<em><span style=\"color:gray\">.........<strong>" + au.unit.name + " falls!");
		}
	}
	
	// WAIT: change the indicated unit's facing in the MapPanel
	public void receiveWait(int[] data)
	{
		faceUnit(data[0], data[1]);
	}
	
	// LOSE: announce the winner's victory (or a draw, if both teams have 'lost') in chat.
	// If the current turn belongs to one of this unit's clients, and the game isn't over, bring up the facing panel.
	public void receiveGameOver(boolean[] data)
	{
		// Determine which player is p1 and which is p2
		String p1name, p2name;
		if (playerNumber == 1)
		{
			p1name = player.username;
			p2name = opponent.username;
		}
		else
		{
			p2name = player.username;
			p1name = opponent.username;
		}
		
		// If data[0] and data[1] are both true, the match has ended in a tie
		if (data[0] && data[1])
		{
			gameOver = true;
			window.setTitle(window.getTitle() + " - tie game!");
			window.appendToChat("<em><strong>The engagement has ended in a <span style=" +
					"\"text-decoration: underline; color:yellow\">tie</span></strong>");
		}
		
		// If data[0] is true, player *1* has LOST and player *2* has WON
		else if (data[0])
		{
			gameOver = true;
			window.setTitle(window.getTitle() + " - " + p2name + " wins!");
			window.appendToChat("<em><strong><span style=\"text-decoration: underline; color:blue\">" +
					p2name + "</span> has won the engagement!</strong>");
		}
		
		// If data[1] is true, player *2* has LOST and player *1* has WON
		else if (data[1])
		{
			gameOver = true;
			window.setTitle(window.getTitle() + " - " + p1name + " wins!"); 
			window.appendToChat("<em><strong><span style=\"text-decoration: underline; color:red\">" +
					p1name + "</span> has won the engagement!</strong>");
		}
		
		// If neither data[0] nor [1] is true, let the player finish their action and keep playing
		else
		{
			if (isYourTurn())
				window.finishAct();
		}
	}
	
	public void receiveExit(String username)
	{
		window.appendToChat("<em><strong>" + username + " has left the room.</strong>");
	}
	
	public void beginGame()
	{
		ArrayList<ActiveUnit> otherTeam;
		if (playerNumber == 1)
			otherTeam = p2Units;
		else
			otherTeam = p1Units;
		
		window.beginGame(otherTeam);
	}
	
	public void faceTowardTile(ActiveUnit unit, ZankMapTile tile)
	{
		int x1 = unit.x, y1 = unit.y;
		int x2 = tile.x, y2 = tile.y;
		
		int d_x = x2 - x1, d_y = y2 - y1; 
		
		if (d_x > d_y)
		{
			if (d_x > 0)
				unit.face("SW");
			else if (d_y < 0)
				unit.face("NE");
		}
		else if (d_y > d_x)
		{
			if (d_y > 0)
				unit.face("SE");
			else if (d_y < 0)
				unit.face("NW");
		}
	}
	
	public void faceUnit(int unit, int dir)
	{
		ActiveUnit au = getUnit(unit);
		au.face(dir);
		window.updateSprite(au);
	}
	
	// Returns true if the unit who is active belongs to this client's team
	public boolean isYourTurn()
	{
		return currentUnit().team == playerNumber;
	}
	
	public void setCurrentUnit(int currentUnit)
	{
		state.currentUnit = currentUnit;
	}
	
	public ActiveUnit currentUnit()
	{
		return state.units[state.currentUnit];
	}
	
	public ActiveUnit getUnit(int index)
	{
		return state.units[index];
	}
	
	public ActiveUnit[] getUnits()
	{
		return state.units;
	}
	
	public int currentID()
	{
		return state.currentUnit;
	}
	
	public ArrayList<Integer> getTargets(int x, int y, FFTASkill sk, ActiveUnit user)
	{
		return state.getTargets(x,  y, sk, user);
	}
}
