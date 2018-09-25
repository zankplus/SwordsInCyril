package clanBuilder;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fftadata.FFTAUnit;

class JobIconPanel extends JPanel
	{
		BufferedImage icon;
		FFTAUnit unit;
			
		public JobIconPanel(FFTAUnit unit)
		{
			this.unit = unit;
		}
		
		public void update()
		{
			try {
				icon = ImageIO.read(new File("resources/icons/icon_" + unit.job.name() + ".png"));
			} catch (IOException e) {}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g); 
			g.drawImage(icon, 10, 4, null);
		}
	}