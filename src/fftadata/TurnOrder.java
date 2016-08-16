package fftadata;

import java.util.ArrayList;

public class TurnOrder
{
public ActiveUnit[] units;
	
	public TurnOrder(ArrayList<ActiveUnit> p1Units, ArrayList<ActiveUnit> p2Units)
	{
		units = new ActiveUnit[p1Units.size() + p2Units.size()];
		
		for (int i = 0; i < p1Units.size(); i++)
			units[i] = p1Units.get(i);
		
		for (int i = 0; i < p2Units.size(); i++)
			units[i + p1Units.size()] = p2Units.get(i);
		
		assignPriority();
	}
	
	// Advance the clock by one tick
	public void tick()
	{
		ActiveUnit max = units[0];
		
		for (ActiveUnit au : units)
		{
			if (au.currHP > 0)
			{
				au.counter += au.getTickSpeed();
				au.counter += au.reserve;
				au.reserve = 0;
				
				if (au.counter > max.counter)
					max = au;		
			}
			
		}
		
		if (max.counter >= 1000)
		{
			int baseReserve = Math.min(max.counter - 1000, 500);
//			int baseReserve = max.counter - 1000;
			
			
			for (ActiveUnit au : units)
			{
				if (au.currHP > 0)
				{
					int res = Math.min(baseReserve, au.counter);
					au.counter -= res;
					au.reserve = res;
				}
			}
		}
					
		System.out.print("\t");
		for (ActiveUnit au : units)
		{
			System.out.print(au.counter + "\t" + au.reserve + "\t");
			if (au.counter > 2000)
				System.exit(1);
		}
		System.out.println();
//
//		//////
//		
//		for (ActiveUnit au : units)
//		{
//			if (au.counter == 1000)
//			{
//				au.counter = 0;
//				au.reserve = 0;
//			}
//		}
	}
	
	// Returns the index of the next unit to move
	public int getNext()
	{
		int result = -1;
		boolean loop = true;
		
		// 1. Check for Quick status
		for (int i = 0; i < units.length; i++)
			if (units[i].status[ActiveUnit.QUICK] > 0)
			{
				result = i;
				i = units.length;
			}
		
		if (result == -1)
		{
			while (loop)
			{				
				// 2. Check for units with 1000 counter. 
				for (int i = 0; i < units.length; i++)
					if (units[i].counter >= 1000)
						if ((result == -1 || units[i].priority < units[result].priority) && units[i].currHP > 0)	// lowest priority wins. also, don't let dead people take turns
						{
							result = i;							
						}
				
				if (result != -1)
					loop = false;
				else
					// 3. If no units have 1000 counter, advance the clock by a tick and check again.
					tick();
			}
		}
			
		return result;
		
	}
	
	public void assignPriority()
	{
//		for (int i = 0; i < units.length; i++)
//			units[i].priority = (i + 3) % units.length;
		
		for (int i = 0; i < units.length; i++)
		{
			String name = units[i].unit.name;
			if (name.equals("Kilov"))
				units[i].priority = 1;
			else if (name.equals("Georg"))
				units[i].priority = 2;
			else if (name.equals("Seneka"))
				units[i].priority = 3;
			else if (name.equals("Istavan"))
				units[i].priority = 4;
			else if (name.equals("Shara"))
				units[i].priority = 5;
			else if (name.equals("Mondo"))
				units[i].priority = 6;
			else if (name.equals("Cesare"))
				units[i].priority = 7;
		}
		
	}
	
	public static void main(String[] args)
	{
		FFTAUnit u1 = new FFTAUnit(), u2 = new FFTAUnit(), u3 = new FFTAUnit(), u4 = new FFTAUnit(), u5 = new FFTAUnit(), u6 = new FFTAUnit(), u7 = new FFTAUnit();
		u1.speed = 195; u2.speed = 174; u3.speed = 138; u4.speed = 136; u5.speed = 164; u6.speed = 160; u7.speed = 127;
		ActiveUnit au1  = new ActiveUnit(u1, 0, 0, 0, 0); au1.unit.name = "SHARA";
		ActiveUnit au2  = new ActiveUnit(u2, 0, 0, 0, 0); au2.unit.name = "CESARE";
		ActiveUnit au3  = new ActiveUnit(u3, 0, 0, 0, 0); au3.unit.name = "GEORG";
		ActiveUnit au4  = new ActiveUnit(u4, 0, 0, 0, 0); au4.unit.name = "SENEKA";
		ActiveUnit au5  = new ActiveUnit(u5, 0, 0, 0, 0); au5.unit.name = "MONDO";
		ActiveUnit au6  = new ActiveUnit(u6, 0, 0, 0, 0); au6.unit.name = "ISTAVAN";
		ActiveUnit au7  = new ActiveUnit(u7, 0, 0, 0, 0); au7.unit.name = "KILOV";
		ArrayList<ActiveUnit> units1 = new ArrayList<ActiveUnit>();
		ArrayList<ActiveUnit> units2 = new ArrayList<ActiveUnit>();
		
		units1.add(au1);
		units1.add(au2);
		units1.add(au3);
		units2.add(au4);
		units2.add(au5);
		units2.add(au6);
		units2.add(au7);
		
		TurnOrder to = new TurnOrder(units1, units2);
		
		for (int i = 1; i <= 12; i++)
		{
			int aui = to.getNext();
			System.out.print(to.units[aui].unit.name + " ");
			if (i == 1 || i == 3 || i == 10 || i == 11)
			{
				to.units[aui].counter -= 1000;
				System.out.println("moves and acts");
			}
			else if (i == 2 || i == 9)
			{
				to.units[aui].counter -= 500;
				System.out.println("waits");
			}
			else
			{
				to.units[aui].counter -= 800;
				System.out.println("moves");
			}
			to.units[aui].reserve = 0;
		}
	}
}
