package fftadata;
import java.io.Serializable;

import javax.swing.DefaultListModel;

public class EquipSet implements Serializable
{
	public final FFTAUnit unit;
	public int rightHand, leftHand, head, body, feet, arms;	// indices of slots filling these positions
	public FFTAEquip[] slots;
	
	public EquipSet(FFTAUnit unit)
	{
		this.unit = unit;
		rightHand = leftHand = head = body = feet = arms = -1;
		slots = new FFTAEquip[] {FFTAEquip.NONE, FFTAEquip.NONE, FFTAEquip.NONE, FFTAEquip.NONE, FFTAEquip.NONE};
	}
	
	//Copy constructor
	public EquipSet(EquipSet orig)
	{
		this.unit = orig.unit;
		this.rightHand = orig.rightHand;
		this.leftHand = orig.leftHand;
		this.head = orig.head;
		this.body = orig.body;
		this.feet = orig.feet;
		this.arms = orig.arms;
		
		slots = new FFTAEquip[] { orig.slots[0], orig.slots[1], orig.slots[2], orig.slots[3], orig.slots[4]};
	}
	
	public int findOpenSlot()
	{
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] == FFTAEquip.NONE)
				return i;
		}
		return -1;
	}
	
	public void unequip(int slot)
	{
		if (slots[slot] != FFTAEquip.NONE)
		{
			EquipType type = slots[slot].type;
			
			if (type.ordinal() <= EquipType.GUN.ordinal())
			{
				// If you're using Double Sword and trying to unequip the left-hand weapon, then unequip left instead of right
				if (leftHand == slot && rightHand != slot)
				{
					System.out.println("is this it");
					leftHand = -1;
				}
				else
				{
					System.out.println("zzz");
					rightHand = -1;
					// If you're using a two-handed weapon without Monkeygrip, empty the left hand as well.
					if (slots[slot].isTwoHanded() && unit.support != FFTASupport.MONKEYGRIP)
						leftHand = -1;
				}	
			}
			else if (type == EquipType.SHIELD)
				leftHand = -1;
			else if (type.ordinal() <= EquipType.HAT.ordinal())
				head = -1;
			else if (type.ordinal() <= EquipType.ROBE.ordinal())
				body = -1;
			else if (type == EquipType.BOOTS)
				feet = -1;
			else if (type == EquipType.GLOVES)
				arms = -1;
			
			slots[slot] = FFTAEquip.NONE;
		}
	}
	
	public boolean equip(FFTAEquip eq)
	{
		unequipIncompatible(eq); 
		return equipInternal(eq, findOpenSlot());
	}
	
	public boolean equip(FFTAEquip eq, int slot)
	{	
		unequip(slot);
		unequipIncompatible(eq);
		return equipInternal(eq, slot);
	}
	
	// Equips all equipment incompatible with the one you're trying to equip.
	// For all items except accessories, if you're already wearing something in the same place on your body, it will be unequipped.
	// If you're equipping a two-handed weapon, both your shield and your current weapon will be unequipped.
	// If you're equipping a shield, your weapon will also be unequipped if it is two-handed.
	public void unequipIncompatible(FFTAEquip eq)
	{
		EquipType type = eq.type;
		
		if (type.ordinal() <= EquipType.GUN.ordinal())
		{
			// If you're using Double Sword and you try to equip a one-handed weapon,
			if (unit.support == FFTASupport.DOUBLE_SWORD && !eq.isTwoHanded())
			{
				// and you already have something in your right hand,
				if (rightHand != -1)
				{
					// remove it, if it's a two-handed weapon;
					if (slots[rightHand].isTwoHanded())
					{
						System.out.println("uno");
						unequip(rightHand);
					}
					
					// remove any shields or weapons from the left hand;
					else if (leftHand != -1 &&
							(slots[leftHand].type == EquipType.SHIELD || slots[leftHand].isWeapon()) )
					{
						System.out.println("dos");
						unequip(leftHand);	
					}
				}
			}
			
			else
			{
				if (rightHand != -1)
					unequip(rightHand);
				
				// Check for 2-handed weapons
				// TODO: Can this check be removed?
				if (leftHand != -1 && eq.isTwoHanded() && unit.support != FFTASupport.MONKEYGRIP)
					unequip(leftHand);
			}
		}
		
		else if (type == EquipType.SHIELD)
		{
			if (leftHand != -1)
				unequip(leftHand);
			
			// Check for 2-handed weapons
			if (rightHand != -1 && slots[rightHand].isTwoHanded() && unit.support != FFTASupport.MONKEYGRIP)
				unequip(rightHand);
		}
		
		else if (type.ordinal() <= EquipType.HAT.ordinal())
		{
			if (head != -1)
				unequip(head);
		}
		
		else if (type.ordinal() <= EquipType.ROBE.ordinal())
		{
			if (body != -1)
				unequip(body);
		}
		
		else if (type == EquipType.BOOTS)
		{
			if (feet != -1)
				unequip(feet);
		}
		else if (type == EquipType.GLOVES)
		{
			if (arms != -1)
				unequip(arms);
		}
	}
	
	private boolean equipInternal(FFTAEquip eq, int slot)
	{
		if (slot >= 0 && slot < 5)
		{
			EquipType type = eq.type;
			
			if (type.ordinal() <= EquipType.GUN.ordinal())
			{
				if (unit.support == FFTASupport.DOUBLE_SWORD && !eq.isTwoHanded())
				{
					// If no weapon is equipped, equip the new weapon in the right hand 
					if (rightHand == -1)
					{
						rightHand = slot;
						System.out.println("option 3");
					}

					// If a one-handed weapon is equipped in the right hand but the left is empty, equip it in the left
					else if (rightHand != -1 && leftHand == -1)
					{
						// Make sure the uppermost (lowest index) weapon slot is the right hand
						if (slot > rightHand)
						{
							System.out.println("optjon 2");
							leftHand = slot;
						}
						else if (slot < rightHand)
						{
							System.out.println("ah, there's the rub");
							leftHand = rightHand;
							rightHand = slot;
						}
						else
							rightHand = slot;
					}
					else
						return false;
					
					slots[slot] = eq;
					return true;
				}
				
				else
				{
					rightHand = slot;
					if (eq.isTwoHanded() && unit.support != FFTASupport.MONKEYGRIP)
						leftHand = slot;
					slots[slot] = eq;
					return true;
				}
			}
			else if (type == EquipType.SHIELD)
			{
				leftHand = slot;
				slots[slot] = eq;
				return true;
			}
			else if (type.ordinal() <= EquipType.HAT.ordinal())
			{
				head = slot;
				slots[slot] = eq;
				return true;
			}
			else if (type.ordinal() <= EquipType.ROBE.ordinal())
			{
				body = slot;
				slots[slot] = eq;
				return true;
			}
			else if (type == EquipType.BOOTS)
			{
				feet = slot;
				slots[slot] = eq;
				return true;
			}
			else if (type == EquipType.GLOVES)
			{
				arms = slot;
				slots[slot] = eq;
				return true;
			}
			else if (type == EquipType.ACCESSORY)
			{
				slots[slot] = eq;
				return true;
			}
		}
		return false;
	}
	
	// Remove all equipped gear that the current class is not allowed to use.
	// Intended for use mainly when changing classes or S-abilities
	public void removeIllegalEquipment()
	{
		// Job check
		boolean equipOK;
		for (int i = 0; i < 5; i++)
		{
			equipOK = false;
			if (slots[i] != FFTAEquip.NONE)
				if(unit.job.canEquip(slots[i].type) || (slots[i].type == EquipType.SHIELD && unit.support == FFTASupport.SHIELDBEARER))
						equipOK = true;
			if (!equipOK)
				unequip(i);
		}
		
		// Monkeygrip check 1: remove shield if unit is holding both a 2-handed weapon and a shield without monkeygrip
		if (rightHand != -1 && leftHand != -1 && slots[rightHand].isTwoHanded() && slots[leftHand].type == EquipType.SHIELD && unit.support != FFTASupport.MONKEYGRIP)
		{
			System.out.println("a");
			unequip(leftHand);
		}
		
		
		if (rightHand != -1)
		{
			// If you're holding a 2-handed weapon with Monkeygrip and no shield, count the left hand as empty.
			if (leftHand == rightHand && unit.support == FFTASupport.MONKEYGRIP)
				leftHand = -1;
			
			// If you're holding a 2-handed weapon WITHOUT monkeygrip, make sure the left hand is pointing to it as well.
			if (unit.support != FFTASupport.MONKEYGRIP && slots[rightHand].isTwoHanded())
				leftHand = rightHand;
		}
			
		 
		// Shieldbearer check
		if (leftHand != -1 && slots[leftHand].type == EquipType.SHIELD && unit.support != FFTASupport.SHIELDBEARER && !unit.job.canEquip(EquipType.SHIELD))
		{
			System.out.println("b");
			unequip(leftHand);
		}
		
		// Double Sword check
		if (leftHand != -1 && slots[leftHand].isWeapon() && !slots[leftHand].isTwoHanded() && unit.support != FFTASupport.DOUBLE_SWORD)
		{
			System.out.println("c");
			unequip(leftHand);
		}
	}
	
	public int count()
	{
		int count = 0;
		for (int i = 0; i < 5; i++)
			if (slots[i] != FFTAEquip.NONE)
				count++;
		return count;
	}
	
	public DefaultListModel<FFTAEquip> getListModel()
	{
		DefaultListModel<FFTAEquip> result = new DefaultListModel<FFTAEquip>();
		for (int i = 0; i < 5; i++)
			result.addElement(slots[i]);
		return result;
	}
}