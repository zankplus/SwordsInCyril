package fftadata;

import java.util.ArrayList;

public class TurnOrder
{
	ActiveUnit[] units;
	
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
			au.counter += au.getTickSpeed();
			au.counter += au.reserve;
			au.reserve = 0;
			
			if (au.counter > max.counter)
				max = au;		
		}
		
		if (max.counter >= 1000)
		{
			int baseReserve = Math.min(max.counter - 1000, 500);
			
			for (ActiveUnit au : units)
			{
				if (au.counter < baseReserve)
				{
					au.reserve = au.counter;
					au.counter = 0;
				}
				else
				{
					au.counter -= baseReserve;
					au.reserve = baseReserve;
				}
			}
			
			
		}
					
		System.out.print("\t");
		for (ActiveUnit au : units)
			System.out.print(au.counter + "\t" + au.reserve + "\t");
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
	
	public ActiveUnit getNext()
	{
		ActiveUnit result = null;
		boolean loop = true;
		
		// 1. Check for Quick status
		for (int i = 0; i < units.length; i++)
			if (units[i].status[ActiveUnit.QUICK] > 0)
			{
				result = units[i];
				i = units.length;
			}
		
		if (result == null)
		{
			while (loop)
			{				
				// 2. Check for units with 1000 counter. 
				for (int i = 0; i < units.length; i++)
					if (units[i].counter == 1000)
						if (result == null || units[i].priority < result.priority)	// lowest priority wins
						{
							result = units[i];
							i = units.length;							
						}
				
				if (result != null)
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
		for (int i = 0; i < units.length; i++)
			units[i].priority = (i + 3) % units.length;
	}
	
	public static void main(String[] args)
	{
		FFTAUnit u1 = new FFTAUnit(), u2 = new FFTAUnit(), u3 = new FFTAUnit(), u4 = new FFTAUnit(), u5 = new FFTAUnit(), u6 = new FFTAUnit();
		u1.speed = 195; u2.speed = 138; u3.speed = 136; u4.speed = 164; u5.speed = 160; u6.speed = 127;
		ActiveUnit au1  = new ActiveUnit(u1, 0, 0, 0, 0); au1.unit.name = "Shara";
		ActiveUnit au2  = new ActiveUnit(u2, 0, 0, 0, 0); au2.unit.name = "Georg";
		ActiveUnit au3  = new ActiveUnit(u3, 0, 0, 0, 0); au3.unit.name = "Seneka";
		ActiveUnit au4  = new ActiveUnit(u4, 0, 0, 0, 0); au4.unit.name = "Mondo";
		ActiveUnit au5  = new ActiveUnit(u5, 0, 0, 0, 0); au5.unit.name = "Istavan";
		ActiveUnit au6  = new ActiveUnit(u6, 0, 0, 0, 0); au6.unit.name = "Kilov";
		ArrayList<ActiveUnit> units1 = new ArrayList<ActiveUnit>();
		ArrayList<ActiveUnit> units2 = new ArrayList<ActiveUnit>();
		
		units1.add(au1);
		units1.add(au2);
		units1.add(au3);
		units2.add(au4);
		units2.add(au5);
		units2.add(au6);
		
		TurnOrder to = new TurnOrder(units1, units2);
		
		for (int i = 0; i < 20; i++)
		{
			ActiveUnit au = to.getNext();
			System.out.println(au.unit.name);
			au.counter = 0; au.reserve = 0;
		}
	}
}
