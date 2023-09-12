/*
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

package com.fortmoon.genetic.game.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;



/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 8, 2011 10:24:38 PM
 */
public abstract class GameObject extends Rectangle implements Runnable {
	private static final long serialVersionUID = 1L;
	protected Image image;
	protected boolean alive = false; // This is set to false when the object is destroyed
	protected long sleepTime = 5; // This is the number of milliseconds to sleep during a pause or between moves
	protected GameObservable observable = new GameObservable();
	protected Random random = new Random(System.currentTimeMillis());
	protected int moveIncrement = 1;

	public Image getImage() {
		return image;
	}
	
	public void setImage(String filename) {
		try {
			System.out.println("loading image: " + filename);
			InputStream stream = getClass().getClassLoader().getResourceAsStream("images/"+filename);
			Image img = ImageIO.read(stream);
			if (img == null)
				System.out.println("Defender image is null");
			// System.out.println("Image = " + img.getWidth(null));

			image = img.getScaledInstance(80, -1, Image.SCALE_AREA_AVERAGING);
			width = image.getWidth(null);
			height = image.getHeight(null);
		} catch (Exception e) {
			System.out.println("Exception loading file " + filename + ": " + e);
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		this.alive = false;
		observable.setChanged();
		observable.notifyObservers(this);
		observable.deleteObservers();
	}

	public void moveLeft() {
		// System.out.println("Moving Left");

		// If we are at the left bound, moves us the right side
		for (int i = 0; i < moveIncrement; i++) {
			if (x < 0){
				x = Screen.getWidth() - width;
//				x = Screen.getWidth();
				return;
			}

			// Move to the left one pixel
			x -= 1;
		}
	}

	public void moveRight() {
		// If we are at the left bound, move us to the left
		//System.out.println("Move increment is: " + moveIncrement);
		for (int i = 0; i < moveIncrement; i++) {
			if (x >= Screen.getWidth() - width) {
//			if (x >= Screen.getWidth()) {
				x = 0;
				return;
			}

			// Move to the right one pixel
			x += 1;
		}
	}
	
	public void start() {
		if(this.alive) {
			System.out.println("Error: trying to start an object that is already alive.");
			return;
		}
		alive = true;
		Thread t = new Thread(this);
		t.start();
	}

	public void sleep() {
		try {
			Thread.sleep(sleepTime);
		} 
		catch (InterruptedException e) {
			// Do nothing, just ignore.
		}
	}
	
	protected void setMoveIncrement(int i) {
		this.moveIncrement = i;
	}
	
	public void registerObserver(Observer observer) {
		observable.addObserver(observer);
		//System.out.println("Observers = " + observable.countObservers());
	}

		
	/**
	 * This method gets called when the class is cleaned up. You can ignore it, 
	 * it does nothing and doesn't really need to be here.
	 */
	public void finalize() {
		//System.out.println(this.getClass().getSimpleName() +  " Finalized.");
	}
	
}

class GameObservable extends Observable {
	public void setChanged() {
		super.setChanged();
	}
}
