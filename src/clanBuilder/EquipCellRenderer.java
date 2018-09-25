package clanBuilder;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import fftadata.FFTAEquip;

class EquipCellRenderer implements ListCellRenderer<FFTAEquip>
{
	@Override
	public Component getListCellRendererComponent(JList<? extends FFTAEquip> list, FFTAEquip equip, int index, boolean isSelected, boolean hasCellFocus)
	{
		JLabel result = new JLabel (equip.name, equip.icon, JLabel.LEFT);
		result.setBorder(new EmptyBorder(1, 2, 1, 0));
		result.setOpaque(true);
		if (isSelected) {
            result.setBackground(Color.BLUE);
            result.setForeground(Color.WHITE);
        }
		else {
            result.setBackground(Color.WHITE);
            result.setForeground(Color.BLACK);
        }
		
		return result;
	}
}