package client;

import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;

import fftadata.ActiveUnit;
import fftadata.FFTACalc;
import fftadata.FFTAEquip;
import fftadata.FFTASkill;
import fftadata.FFTAUnit;
import fftadata.GameState;
import fftadata.SkillEffectResult;
import fftamap.FFTAMap;

import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

public class DamagePreviewPanel extends JPanel
{
	
	ActiveUnit au;
	ArrayList<ActiveUnit> targets;
	int currentCard;
	FFTASkill sk;
	private TargetPanel[] targetCards;
	GameState state;
	
	public DamagePreviewPanel(ActiveUnit au, ArrayList<ActiveUnit> targets, FFTASkill sk, GameState state)
	{
		this.au = au;
		this.targets = targets;
		this.sk = sk;
		this.state = state;
		
		setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPreferredSize(new Dimension(320, 162));
		setLayout(new BorderLayout(0, 0));
		
		JPanel outerPanel = new JPanel();
		outerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(outerPanel, BorderLayout.CENTER);
		outerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftSide = new JPanel();
		outerPanel.add(leftSide, BorderLayout.WEST);
		leftSide.setPreferredSize(new Dimension(152, 10));
		leftSide.setBackground(new Color(192, 192, 248));
		leftSide.setLayout(new BorderLayout(0, 0));
		
		JPanel attackerPanel = new JPanel();
		attackerPanel.setOpaque(false);
		leftSide.add(attackerPanel, BorderLayout.NORTH);
		attackerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel attackerSpritePanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				BufferedImage sprite = null;
				try {
					sprite = ImageIO.read(new File(au.getSpriteURL()));
					
					int r = 1, t = 32;
					if (au.isSpriteReflected())
						r = -1;
					else
						t = 0;
						
					g.drawImage(sprite, t + 2, 2, 32*r, 64, null);
				} catch(IOException e) { System.out.println("Couldn't find sprite! : " + au.getSpriteURL()); }
				
			}
		};
		attackerSpritePanel.setOpaque(false);
		attackerSpritePanel.setPreferredSize(new Dimension(34, 80));
		attackerPanel.add(attackerSpritePanel, BorderLayout.WEST);
		
		JPanel attackerStatsPanel = new JPanel();
		attackerStatsPanel.setOpaque(false);
		attackerPanel.add(attackerStatsPanel, BorderLayout.CENTER);
		attackerStatsPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblAttackerName = new JLabel(au.unit.name + " Lv. " + au.unit.getLevel());
		lblAttackerName.setHorizontalAlignment(SwingConstants.CENTER);
		attackerStatsPanel.add(lblAttackerName);
		
		JPanel attackerHPPanel = new JPanel();
		attackerHPPanel.setOpaque(false);
		attackerStatsPanel.add(attackerHPPanel);
		attackerHPPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblAttackerHP = new JLabel("HP");
		lblAttackerHP.setHorizontalAlignment(SwingConstants.CENTER);
		attackerHPPanel.add(lblAttackerHP);
		
		JLabel lblAttackerCurrHP = new JLabel("<html><strong>" + au.currHP);
		lblAttackerCurrHP.setHorizontalAlignment(SwingConstants.CENTER);
		attackerHPPanel.add(lblAttackerCurrHP);
		
		JLabel lblAttackerMaxHP = new JLabel("<html>/ <strong>" + (int) au.unit.maxHP);
		attackerHPPanel.add(lblAttackerMaxHP);
		
		JPanel attackerMPPanel = new JPanel();
		attackerMPPanel.setOpaque(false);
		attackerStatsPanel.add(attackerMPPanel);
		attackerMPPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblAttackerMP = new JLabel("MP");
		lblAttackerMP.setHorizontalAlignment(SwingConstants.CENTER);
		attackerMPPanel.add(lblAttackerMP);
		
		JLabel lblAttackerCurrMP = new JLabel("<html><strong>" + au.currMP);
		lblAttackerCurrMP.setHorizontalAlignment(SwingConstants.CENTER);
		attackerMPPanel.add(lblAttackerCurrMP);
		
		JLabel lblAttackerMaxMP = new JLabel("<html>/ <strong>" + (int) au.unit.maxMP);
		attackerMPPanel.add(lblAttackerMaxMP);
		
		JPanel attackerJPPanel = new JPanel();
		attackerJPPanel.setOpaque(false);
		attackerStatsPanel.add(attackerJPPanel);
		attackerJPPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblAttackerJP = new JLabel("JP");
		lblAttackerJP.setHorizontalAlignment(SwingConstants.CENTER);
		attackerJPPanel.add(lblAttackerJP);
		
		JLabel lblAttackerCurrJP = new JLabel("<html><strong>" + au.jp);
		lblAttackerCurrJP.setHorizontalAlignment(SwingConstants.CENTER);
		attackerJPPanel.add(lblAttackerCurrJP);
		
		JPanel targetCardHolder = new JPanel();
		outerPanel.add(targetCardHolder, BorderLayout.EAST);
		targetCardHolder.setPreferredSize(new Dimension(152, 10));
		targetCardHolder.setBackground(new Color(248, 192, 192));
		targetCardHolder.setLayout(new CardLayout(0, 0));
		
		
		JPanel southPanel = new JPanel();
		outerPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel hitPanel = new JPanel();
		hitPanel.setBackground(new Color(248, 248, 192));
		hitPanel.setPreferredSize(new Dimension(10, 54));
		hitPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		southPanel.add(hitPanel, BorderLayout.CENTER);
		hitPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblHit = new JLabel("<html><strong>HIT:");
		lblHit.setHorizontalAlignment(SwingConstants.CENTER);
		hitPanel.add(lblHit);
		
		JLabel lblDmg = new JLabel("176 dmg");
		lblDmg.setHorizontalAlignment(SwingConstants.CENTER);
		hitPanel.add(lblDmg);
		
		JLabel lblHitRate = new JLabel("100%\r\n");
		lblHitRate.setHorizontalAlignment(SwingConstants.CENTER);
		hitPanel.add(lblHitRate);
		
		JPanel bottomLeftPanel = new JPanel();
		bottomLeftPanel.setBackground(new Color(192, 192, 248));
		bottomLeftPanel.setPreferredSize(new Dimension(100, 10));
		southPanel.add(bottomLeftPanel, BorderLayout.WEST);
		bottomLeftPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnPrev = new JButton("<");
		btnPrev.setOpaque(false);
		btnPrev.setMargin(new Insets(2, 5, 2, 5));
		bottomLeftPanel.add(btnPrev, BorderLayout.EAST);
		
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setPreferredSize(new Dimension(100, 10));
		southPanel.add(bottomRightPanel, BorderLayout.EAST);
		bottomRightPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNext = new JButton(">");
		btnNext.setOpaque(false);
		btnNext.setMargin(new Insets(2, 5, 2, 5));
		bottomRightPanel.add(btnNext, BorderLayout.WEST);
		
		// PREV button listener
		btnPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				currentCard = (currentCard + 1) % targets.size();
				CardLayout cl = (CardLayout) targetCardHolder.getLayout();
				cl.next(targetCardHolder);
				
				bottomRightPanel.setBackground(targetCards[currentCard].getBackground());
				lblHitRate.setText("" + targetCards[currentCard].hit + "%");
				lblDmg.setText("" + targetCards[currentCard].dmg + " dmg");
			}
		});
		
		// NEXT button listener
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				currentCard = (currentCard + 1) % targets.size();
				CardLayout cl = (CardLayout) targetCardHolder.getLayout();
				cl.next(targetCardHolder);
				
				bottomRightPanel.setBackground(targetCards[currentCard].getBackground());
				lblHitRate.setText("" + targetCards[currentCard].hit + "%");
				lblDmg.setText("" + targetCards[currentCard].dmg + " dmg");
			}
		});
		
		// Set up TargetPanel cards
		targetCards = new TargetPanel[targets.size()];
		for (int i = 0; i < targets.size(); i++)
		{
			targetCards[i] = new TargetPanel(targets.get(i));
			targetCardHolder.add(targetCards[i], String.valueOf(i));
			System.out.println("added " + targets.get(i).unit.name);
		}
		lblHitRate.setText("" + targetCards[0].hit + "%");
		lblDmg.setText("" + targetCards[0].dmg + " dmg");
		
		if (targetCards.length <= 1)
		{
			btnNext.setEnabled(false);
			btnPrev.setEnabled(false);
		}
		
		// Fix bottomRightPanel bgcolor
		bottomRightPanel.setBackground(targetCards[currentCard].getBackground());
	}

/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EngagementWindowRosterPanel ewrp = new EngagementWindowRosterPanel(null);
					//EngagementWindowRosterPanel ewrp = new EngagementWindowRosterPanel(new EngagementWindow(new Engagement(null, 0, null, null, null)));
					
					ActiveUnit au1 = new ActiveUnit( (FFTAUnit) ewrp.roster.getModel().getElementAt(0), 1, 11, 4, 1);
					ActiveUnit au2 = new ActiveUnit( (FFTAUnit) ewrp.roster.getModel().getElementAt(1), 1, 11, 5, 2);
					ActiveUnit au3 = new ActiveUnit( (FFTAUnit) ewrp.roster.getModel().getElementAt(2), 1, 11, 6, 1);
					ActiveUnit au4 = new ActiveUnit( (FFTAUnit) ewrp.roster.getModel().getElementAt(3), 1, 11, 7, 1);
					
					ArrayList<ActiveUnit> targets = new ArrayList<ActiveUnit>();
					targets.add(au2);
					targets.add(au3);
					targets.add(au4);
					
					JFrame frame = new JFrame();
					frame.getContentPane().add(new DamagePreviewPanel(au1, targets, FFTASkill.FIGHT));
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	class TargetPanel extends JPanel
	{
		ActiveUnit target;
		String dmg, hit;
		BufferedImage sprite;
		private boolean reactionApplies;
		
		public TargetPanel(ActiveUnit target)
		{
			this.target = target;
			
			int whichEffect;
			if (sk == FFTASkill.RAISE && target.currHP == 0)
				whichEffect = 1;
			else
				whichEffect = 0;
			
			SkillEffectResult result = sk.EFFECTS[whichEffect].handler.resolveEffect(
					new SkillEffectResult(au.id, target.id, sk, sk.EFFECTS[0], 0), null, null, state, true, false);
			
			reactionApplies = state.reactionApplies(au, target, sk, result.damage > 0);
			
			
			
			
			hit = String.valueOf(result.hitChance);
			if (result.damage < 0)
				dmg = "+ " + Math.abs(result.damage);
			else
				dmg = String.valueOf(result.damage);
			
			if (target.team == 1)
				setBackground(new Color(192, 192, 248));
			else
				setBackground(new Color(248, 192, 192));
			setLayout(new BorderLayout(0, 0));
			
			try {
				sprite = ImageIO.read(new File(target.getSpriteURL()));
			} catch(IOException e) { System.out.println("Couldn't find sprite! : " + target.getSpriteURL()); }
			
			JPanel targetSpritePanel = new JPanel(){
				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					int r = 1, t = 32;
					if (au.isSpriteReflected())
						r = -1;
					else
						t = 0;
					g.drawImage(sprite, t, 2, 32*r, 64, null);

				}
			};
			targetSpritePanel.setPreferredSize(new Dimension(34, 80));
			targetSpritePanel.setOpaque(false);
			add(targetSpritePanel, BorderLayout.EAST);
			
			
			JPanel targetStatPanel = new JPanel();
			targetStatPanel.setOpaque(false);
			add(targetStatPanel, BorderLayout.CENTER);
			targetStatPanel.setLayout(new GridLayout(5, 1, 0, 0));
			
			JLabel lblTargetName = new JLabel(target.unit.name + " Lv. " + target.unit.getLevel());
			lblTargetName.setHorizontalAlignment(SwingConstants.CENTER);
			targetStatPanel.add(lblTargetName);
			
			JPanel targetHPPanel = new JPanel();
			targetHPPanel.setOpaque(false);
			targetStatPanel.add(targetHPPanel);
			targetHPPanel.setLayout(new GridLayout(0, 4, 0, 0));
			
			JLabel lblTargetHP = new JLabel("HP");
			lblTargetHP.setHorizontalAlignment(SwingConstants.CENTER);
			targetHPPanel.add(lblTargetHP);
			
			JLabel lblTargetCurrHP = new JLabel("<html><strong>" + target.currHP);
			lblTargetCurrHP.setHorizontalAlignment(SwingConstants.CENTER);
			targetHPPanel.add(lblTargetCurrHP);
			
			JLabel lblTargetMaxHP = new JLabel("<html>/ <strong>" + (int) target.unit.maxHP);
			targetHPPanel.add(lblTargetMaxHP);
			
			JPanel targetMPPanel = new JPanel();
			targetMPPanel.setOpaque(false);
			targetStatPanel.add(targetMPPanel);
			targetMPPanel.setLayout(new GridLayout(0, 4, 0, 0));
			
			JLabel lblTargetMP = new JLabel("MP");
			lblTargetMP.setHorizontalAlignment(SwingConstants.CENTER);
			targetMPPanel.add(lblTargetMP);
			
			JLabel lblTargetCurrMP = new JLabel("<html><strong>" + target.currMP);
			lblTargetCurrMP.setHorizontalAlignment(SwingConstants.CENTER);
			targetMPPanel.add(lblTargetCurrMP);
			
			JLabel lblTargetMaxMP = new JLabel("<html>/ <strong>" + (int) target.unit.maxMP);
			targetMPPanel.add(lblTargetMaxMP);
			
			JPanel targetJPPanel = new JPanel();
			targetJPPanel.setOpaque(false);
			targetStatPanel.add(targetJPPanel);
			targetJPPanel.setLayout(new GridLayout(0, 4, 0, 0));
			
			JLabel lblTargetJP = new JLabel("JP");
			lblTargetJP.setHorizontalAlignment(SwingConstants.CENTER);
			targetJPPanel.add(lblTargetJP);
			
			JLabel lblTargetCurrJP = new JLabel("<html><strong>" + target.jp);
			lblTargetCurrJP.setHorizontalAlignment(SwingConstants.CENTER);
			targetJPPanel.add(lblTargetCurrJP);
			
			JLabel lblReaction = new JLabel(""+target.unit.reaction);
			lblReaction.setHorizontalAlignment(SwingConstants.CENTER);
			if (reactionApplies)
				lblReaction.setForeground(Color.RED);
			else
				lblReaction.setForeground(Color.GRAY);
			targetStatPanel.add(lblReaction);
		}
	}
}