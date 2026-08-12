/*
 * Defender.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
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
		autoPilot = val;
	}

	public boolean getAutoPilot() {
		return this.autoPilot;
	}
	
	public static Defender getDefender() {
		return instance;
	}

}
