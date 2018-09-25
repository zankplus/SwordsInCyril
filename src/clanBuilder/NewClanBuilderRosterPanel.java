package clanBuilder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fftadata.FFTAUnit;

public class NewClanBuilderRosterPanel extends JFrame {

	public ClanBuilderRoster roster;
	JPanel rightPanel;
	DefaultListModel rosterModel;
	public JButton btnNewUnit, btnNextRow, btnPrevRow, btnSwapLeft, btnSwapRight, btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewClanBuilderRosterPanel frame = new NewClanBuilderRosterPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewClanBuilderRosterPanel()
	{
		super();

		setLayout(new BorderLayout(0, 0));
		
		roster = new ClanBuilderRoster();
		add(roster);
		rosterModel = roster.model;
		
		rightPanel = new JPanel(new BorderLayout());
		
		JPanel rosterActions = new JPanel();
		rightPanel.add(rosterActions, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		rosterActions.setPreferredSize(new Dimension(56, 96));
		rosterActions.setLayout(new GridLayout(6, 1, 0, 0));
		
		btnNewUnit = new JButton("New Unit");
		btnNewUnit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int index = roster.getSelectedIndex() + 1 + 6*roster.currentPage;
				roster.add(index, new FFTAUnit());
				roster.setSelectedIndex(index);
				if (index % 6 == 0)
					pageForward();
				
				if (roster.unitList.size() > 6 * roster.currentPage + 6)
					btnNextRow.setEnabled(true);
				else
					btnNextRow.setEnabled(false);
				
				if (roster.unitList.size() >= 90)
					btnNewUnit.setEnabled(false);
				if (roster.unitList.size() > 1)
					btnDelete.setEnabled(true);
			}
		});
		
		btnNewUnit.setMargin(new Insets(2, 2, 2, 2));
		rosterActions.add(btnNewUnit);
		
		btnDelete = new JButton("Delete");
		if (roster.unitList.size() <= 1)
			btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				roster.unitList.remove(roster.getSelectedIndex() + 6 *roster.currentPage);
				if (roster.currentPage > roster.maxPage())
				{
					pageBackward();
					roster.updateModel();
				}
				else
				{
					int index = roster.getSelectedIndex();
					if (index + 6 * roster.currentPage >= roster.unitList.size())
					{
						System.out.println("too big");
						index = roster.unitList.size() % 6;
					}
												
					System.out.println(index);
					roster.updateModel();
					roster.setSelectedIndex(index);
				}
				if (roster.unitList.size() == 1)
					btnDelete.setEnabled(false);
			}
		});
		btnDelete.setMargin(new Insets(2, 2, 2, 2));
		rosterActions.add(btnDelete);
		
		btnPrevRow = new JButton("Prev row");
		btnPrevRow.setEnabled(false);
		btnPrevRow.setMargin(new Insets(2, 2, 2, 2));
		btnPrevRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pageBackward();
			}
		});
		rosterActions.add(btnPrevRow);
		
		btnNextRow = new JButton("Next row");
		if (roster.unitList.size() > 6)
			btnNextRow.setEnabled(true);
		else
			btnNextRow.setEnabled(false);
		btnNextRow.setMargin(new Insets(2, 2, 2, 2));
		btnNextRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pageForward();
			}
		});
		rosterActions.add(btnNextRow);
		
		btnSwapLeft = new JButton("Swap <");
		btnSwapLeft.setEnabled(false);
		btnSwapLeft.setMargin(new Insets(2, 2, 2, 2));
		btnSwapLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int index = roster.getSelectedIndex() + 6 * roster.currentPage;
				if (index > 0)
				{
					FFTAUnit temp = roster.unitList.get(index);
					roster.unitList.set(index, roster.unitList.get(index - 1));
					roster.unitList.set(index - 1, temp);
					roster.updateModel();
					if ((index - 1) % 6 == 5) 
						pageBackward();
					roster.setSelectedIndex( (index - 1) % 6);
				}
			}
		});
		rosterActions.add(btnSwapLeft);
		
		btnSwapRight = new JButton("Swap >");
		btnSwapRight.setMargin(new Insets(2, 2, 2, 2));
		btnSwapRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int index = roster.getSelectedIndex() + 6 * roster.currentPage;
				if (index < roster.unitList.size() - 1)
				{
					FFTAUnit temp = roster.unitList.get(index);
					roster.unitList.set(index, roster.unitList.get(index + 1));
					roster.unitList.set(index + 1, temp);
					roster.updateModel();
					if ((index + 1) % 6 == 0) 
						pageForward();
					roster.setSelectedIndex( (index + 1) % 6);
				}
			}
		});
		rosterActions.add(btnSwapRight);
		
		roster.setSelectedIndex(0);
	}
	
	public void pageForward()
	{
		roster.currentPage++;
		roster.updateModel();
		
		if (roster.maxPage() > roster.currentPage)
			btnNextRow.setEnabled(true);
		else
			btnNextRow.setEnabled(false);
		
		btnPrevRow.setEnabled(true);
	}
	
	public void pageBackward()
	{
		roster.currentPage--;
		if (roster.currentPage == 0)
			btnPrevRow.setEnabled(false);
		roster.updateModel();
		btnNextRow.setEnabled(true);
	}
}
