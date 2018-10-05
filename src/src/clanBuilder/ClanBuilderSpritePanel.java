package clanBuilder;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fftadata.FFTAJob;

class ClanBuilderSpritePanel extends JPanel
{
	BufferedImage charSprite;
	BufferedImage bgSprite;
	
	static String bgFile = "charbg_large.png";
	
	public ClanBuilderSpritePanel(FFTAJob job)
	{
		super();
		setJob(job);
		setPreferredSize(new Dimension(40, 68));
		
		try
		{
			bgSprite = ImageIO.read(new File("resources/misc/" + bgFile));
		}
		catch (IOException e) { System.err.println("Couldn't find " + bgFile); bgSprite = null; }
	}

	public void setJob(FFTAJob job)
	{
		try {
			charSprite = ImageIO.read(new File("resources/jobs/" + job.name() + "_ally_stand_sw.png"));
		} catch (IOException e) {
			try
			{
				charSprite = ImageIO.read(new File("resources/jobs/soldier_ally_stand_sw.png"));
			} catch (IOException e2) {}
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bgSprite, 2, 2, null);
		// g.drawImage(charSprite, 4, 2, null);
		g.drawImage(charSprite, 26, 2, 48, 96, null);
	}
}
