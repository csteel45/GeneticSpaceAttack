/*
 * Copyright (©) 2011
 * FortMoon Consulting Corporation, 44311 Ladiesburg Place, Ashburn, VA 20147.
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

package com.fortmoon.genetic.game.model.ships;

import java.awt.Color;

import com.fortmoon.genetic.game.model.bullets.Bullet;
import com.fortmoon.genetic.game.model.bullets.Bullets;
import com.fortmoon.genetic.game.model.bullets.Bullet.Direction;


/**
 * @author Christopher Steel - FortMoon Consulting
 * 
 *         Mar 1, 2011 11:01:34 AM
 */
public class Defender extends Ship {

	private int maxShots = 5; // This is the max number of shots before a reload (clip size)
	private int shots = maxShots; // This is the number of shots left. Start out with a max clip
	private boolean autoPilot;
	private static Defender instance;

	public Defender(int x, int y, String imageFileName) {
		super();
		this.x = x;
		this.y = y;
		setImage(imageFileName);
		instance = this;
	}

	@Override
	public void run() {
		while (alive) {
			if(this.getAutoPilot()) {
				//FIXME: Add autopilot
				//action = (Action)iter.next();
				//execute(action);
			}
			sleep();
		}
		System.out.println("Destroyed.");
		this.height = 0;
		this.width = 0;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		instance = null;
	}

	public void fire() {
//		if (instance == null || shots == 0) {
		if (shots == 0) {
			// Can't fire because we haven't reloaded
			return;
		}
		Bullet bullet = new Bullet(this, this.x + (this.width / 2), this.y - Bullet.HEIGHT, Color.MAGENTA, Direction.UP);
		bullet.start();
		Bullets.getInstance().add(bullet);
		//shots--;
	}

	public void setAutoPilot(boolean val) {
		//System.out.println("Setting auto pilot to: " + val);
		autoPilot = val;
	}

	public boolean getAutoPilot() {
		return this.autoPilot;
	}
	
	public static Defender getDefender() {
		return instance;
	}

}
