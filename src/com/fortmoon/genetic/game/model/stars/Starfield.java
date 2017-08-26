/*
 * Copyright (©) 2011 FortMoon Consulting
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of FortMoon
 * Consulting Corporation ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FortMoon.
 *
 * FORTMOON MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FORTMOON SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.fortmoon.genetic.game.model.stars;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 8:58:32 PM
 */
public class Starfield extends Thread implements Serializable, Runnable {

	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private int speed = 30;
	private int numStars;
	private ArrayList<Star> stars;

	private boolean stop = false;
	private Random random = new Random(System.currentTimeMillis());
	
	public Starfield(int numStars, int width, int height) {
		this.numStars = numStars;
		// Create a new Array of size numStars
		stars = new ArrayList<Star>(numStars);
		// The width and height of the field (this will be the screen size)
		this.width = width;
		this.height = height;
		initStars();
	}

	@Override
	public void run() {
		while(!stop ) {
			moveStars();
			try {
				Thread.sleep(speed);
			} 
			catch (InterruptedException e) {
				// Nothing
			}
		}
	}
	
	// This creates the starfield in the background
	public void initStars() {
		//System.out.println("Stars size = " + stars.size());
		for (int i = 0; i < numStars; i++) {
			Star star = new Star(random.nextInt(width-1) + 1, random.nextInt(height-1) + 1, randomColor());
			stars.add(star);
		}
		//System.out.println("Stars size = " + stars.size());
	}
	
	public void moveStars() {
		for(Star star : stars) {
			star.setY(star.getY() + star.getSpeed());
			// Check if the star just went off screen
			if(star.getY() > height) {
				star.setX(random.nextInt(width - 1) + 1);
				star.setY(0);
			}
		}
	}
	
	public Color randomColor() {
			int[] rgb;
			int t;
			rgb = new int[3];
			// Set RBG to 0,0,0
			for (int i = 0; i < 3; i++)
				rgb[i] = 0;
			
			//Randomly create a red, yellow, or blue star;
			t = random.nextInt(3);
			rgb[t] = (int) (Math.random() * 128 + 1) + 127;
			//if(t == 1) // Green, so add red to make yellow
				//rgb[0] = 255;

			if(t == 2) {
				rgb[0] = 100;
				rgb[1] = 100;
			}
			
			return new Color(rgb[0], rgb[1], rgb[2]);
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the stars
	 */
	public ArrayList<Star> getStars() {
		return stars;
	}

	/**
	 * @param stars the stars to set
	 */
	public void setStars(ArrayList<Star> stars) {
		this.stars = stars;
	}

	/**
	 * @return the stop
	 */
	public boolean isStop() {
		return stop;
	}

	/**
	 * @param stop the stop to set
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	public static void main(String args[]) {
		Starfield field = new Starfield(250, 1440, 1080);
		Thread thread = new Thread(field);
		thread.start();
	}

}
