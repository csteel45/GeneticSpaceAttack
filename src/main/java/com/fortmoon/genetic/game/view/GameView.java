/*
 * GameView.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JPanel;

import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.model.bullets.Bullet;
import com.fortmoon.genetic.game.model.bullets.Bullets;
import com.fortmoon.genetic.game.model.ships.Attacker;
import com.fortmoon.genetic.game.model.ships.Attackers;
import com.fortmoon.genetic.game.model.ships.Defender;
import com.fortmoon.genetic.game.model.stars.Star;
import com.fortmoon.genetic.game.model.stars.Starfield;
import com.fortmoon.genetic.game.model.stats.Stats;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 1, 2011 10:40:54 AM
 */
public class GameView extends Canvas {
	private static final long serialVersionUID = 1L;
	private GraphicsDevice myDevice;
    private Window window;
	private Graphics2D graphics;
	private Starfield starfield;
	private int width;
	private int height;
	private boolean stop = false;
	private Font largefont = new Font("Helvetica", Font.PLAIN, 48);
	private Font smallfont = new Font("Helvetica", Font.PLAIN, 20);
	private FontMetrics fmsmall, fmlarge;
	private Random random = new Random(System.currentTimeMillis());
	protected static boolean intro = true;
	private BufferStrategy		strategy;
	
	static {     
		System.setProperty("sun.java2d.transaccel", "True");     
		// System.setProperty("sun.java2d.trace", "timestamp,log,count");     
		// System.setProperty("sun.java2d.opengl", "True");     
		System.setProperty("sun.java2d.d3d", "True");     
		System.setProperty("sun.java2d.ddforcevram", "True");
	}
    
	public GameView() {
		try {
			this.setBackground(Color.black);
		    myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		    if(myDevice.isFullScreenSupported()) {
		    	myDevice.setFullScreenWindow(this.window);
		    	//for(DisplayMode mode : myDevice.getDisplayModes()) {
		    	//}
			    window = myDevice.getFullScreenWindow();
		    }
			DisplayMode mode = myDevice.getDisplayMode();
			width = mode.getWidth();
			System.out.println("Width = " + width);
			height = mode.getHeight();
			System.out.println("Height = " + height);
			Screen.setWidth(width);
			Screen.setHeight(height);
		} 
		catch (Exception e) {
		    myDevice.setFullScreenWindow(null);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void init() {

		// create a strategy that uses two buffers, or is double buffered.
		this.createBufferStrategy(2);

		// get a reference to the strategy object, for use in our render method
		// this isn't necessary but it eliminates a call during rendering.
		strategy = this.getBufferStrategy();

		//graphics = (Graphics2D) myDevice.getFullScreenWindow().getGraphics();
		graphics = (Graphics2D)strategy.getDrawGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		//myDevice.setDisplayMode(new DisplayMode(1280, 1024, 16, 60));
 		
        graphics.setFont(smallfont);
		fmsmall = graphics.getFontMetrics();
		graphics.setFont(largefont);
		fmlarge = graphics.getFontMetrics();
		graphics = (Graphics2D)strategy.getDrawGraphics();
		graphics.setPaint(Color.black);
		graphics.fillRect(0,0,width,height);
		strategy.show();
		
		try {
			int numStars = (width * height) / 1000; //Create a uniform density across screen resolutions
			starfield = new Starfield(numStars, width, height);
			starfield.start();
		    	long start = 0;
			while (!stop) {
				start = System.currentTimeMillis();
				render();
				long renderTime = System.currentTimeMillis() - start;
//				try {
					// ~60 frames/second
					Thread.sleep(renderTime < 15 ? 16 - renderTime : 0); 
//				}
//				catch (InterruptedException e) {
					// Ignore these types of exceptions
//				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			stop  = true;
		    myDevice.setFullScreenWindow(null);
		}
		System.exit(0);
	}
	
	public void render() {
		graphics = (Graphics2D)strategy.getDrawGraphics();
		graphics.clearRect(0, 0, width, height); // Clear the whole screen from 0,0 to width,height
		graphics.setPaint(Color.black);
		graphics.fillRect(0,0,width,height);

		// Paint the different entities from the bottom (depth) to top, so that ships pass over stars, etc.
		paintStarfield(graphics);
		if(intro ) {
			paintIntro(graphics);
		}
		else {
			paintAttackers(graphics);
			paintShip(graphics);
			paintBullets(graphics);
			paintStats(graphics);
		}

		graphics.dispose();
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
/*		    
		if (window != null) {
			BufferStrategy strategy = window.getBufferStrategy();
			if (strategy != null) {
				System.out.println("Showing strategy");
				strategy.show();
			}
		}
*/		
	}
	
	public void paintIntro(Graphics2D graphics) {
		int scoreheight = 20;
		int borderwidth = 0;
		int score = 0;
		Dimension d = new Dimension(Screen.getWidth(), Screen.getHeight());
		
		graphics.setColor(new Color(0xff0000));
		graphics.setFont(largefont);
		
		String s = "Genetic Space Attack";		
		graphics.drawString(s, (d.width - fmlarge.stringWidth(s)) / 2,
				(d.height - scoreheight - borderwidth) / 2 - 40);
		graphics.setColor(Color.cyan);
		
		s = "\u00a92011 by Christopher and Brandon Steel";
		graphics.setFont(smallfont);
		graphics.drawString(s, (d.width - fmsmall.stringWidth(s)) / 2,
				(d.height - scoreheight - borderwidth) / 2 + 10);

		s = "csteel@fortmoon.com";
		graphics.setColor(new Color(0xbb00bb));
		graphics.drawString(s, (d.width - fmsmall.stringWidth(s)) / 2,
				(d.height - scoreheight - borderwidth) / 2 + 30);
		

		graphics.setFont(smallfont);
		graphics.setColor(new Color(0xffff00));
		s = "Hit the space bar to start the game";
		graphics.drawString(s, (d.width - fmsmall.stringWidth(s)) / 2,
				(d.height - scoreheight - borderwidth) / 2 + 170);
		graphics.setColor(new Color(0x00ff00));
		s = "Use the cursor keys to move, press SPACE to fire";
		graphics.drawString(s, (d.width - fmsmall.stringWidth(s)) / 2,
				(d.height - scoreheight - borderwidth) / 2 + 190);
		graphics.setColor(new Color(0xff00ff));
		s = "LAST SCORE: " + score;
		graphics.drawString(s, (d.width - fmsmall.stringWidth(s)) / 2,
				(d.height - scoreheight - borderwidth) / 2 + 240);

	}

	public void paintStarfield(Graphics2D graphics) {
		for (Star star : starfield.getStars()) {
			graphics.setColor(star.getColor());
			int size = random.nextInt(3) + 1;
			//graphics.fillRect(star.getX(), star.getY(), size, size); 
			// Add random size to blink the stars
			graphics.fillOval(star.getX(), star.getY(), star.getSize(), star.getSize());

			//graphics.setColor(star.getColor().darker());
			//graphics.setStroke(new BasicStroke(1.0f));
			//graphics.drawOval(star.getX(), star.getY(), star.getSize(), star.getSize());
		}		
	}

	public void paintBullets(Graphics2D graphics) {
		for(Bullet bullet : Bullets.getBullets()) {
			if(bullet != null) {
				graphics.setColor(bullet.getColor());
				graphics.fillRoundRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight(), 2, 2); // Add random size to blink the stars	
			}
		}
	}
	private void paintAttackers(Graphics2D graphics2) {
		for(Attacker attacker : Attackers.getAttackerList()) {
			paint(graphics, attacker.getImage(), (int)attacker.getX(), (int)attacker.getY()); // Add random size to blink the stars	
		}
	}

	private void paintShip(Graphics2D graphics2) {
		Defender ship = Defender.getDefender();
		if(ship == null)
			return;	
		
		paint(graphics2, ship.getImage(), (int)ship.getX(), (int)ship.getY());
	}
	
	private void paintStats(Graphics2D graphics) {
		graphics.setFont(Font.getFont("Cambria"));
		Stats stats = Stats.getStats();
		String playerScore = "Player: " + stats.getPlayerScore();
		char[] chars = playerScore.toCharArray();
		graphics.setColor(Color.YELLOW.brighter());
		graphics.drawChars(chars, 0, chars.length, 20, 20);

		String programmedScore = "Programmed: " + stats.getProgrammedScore();
		chars = programmedScore.toCharArray();
		graphics.setColor(Color.CYAN.brighter());
		graphics.drawChars(chars, 0, chars.length, (int)(Screen.getWidth()*.3), 20);

		String randomScore = "Random: " + stats.getProgrammedScore();
		chars = randomScore.toCharArray();
		graphics.setColor(Color.RED.brighter());
		graphics.drawChars(chars, 0, chars.length, (int)(Screen.getWidth()*.6), 20);

		String geneticScore = "Genetic: " + stats.getProgrammedScore();
		chars = geneticScore.toCharArray();
		graphics.setColor(Color.GREEN.brighter());
		graphics.drawChars(chars, 0, chars.length, Screen.getWidth()-100, 20);
	}

	private void paint(Graphics2D graphics2, Image img, int x, int y) {
	    graphics2.drawImage(img, x, y, null);
	}

	/**
	 * @return the intro
	 */
	public static boolean isIntro() {
		return intro;
	}

	/**
	 * @param intro the intro to set
	 */
	public static void setIntro(boolean intro) {
		GameView.intro = intro;
	}
			
}
