/*
 * Ship.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships;

import java.util.ArrayList;

import com.fortmoon.genetic.game.model.GameObject;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * @since Mar 24, 2011 1:32:25 PM
 * @version 1.0
 */
public abstract class Ship extends GameObject {
	protected InstructionSet instructions;
	protected int maxShots = 5; 		// This is the max number of shots before a reload (clip size)
	protected int shots = maxShots; 	// This is the number of shots left. Start out with a max clip
	protected long sleepTime = 1;	// This is the number of milliseconds to sleep during a pause or between moves
	protected Action action;
	protected long numHits = 0;

	
	public void execute(Instruction instruction) {
		switch(instruction.getAction()) {
			case MOVE_LEFT: moveLeft();
				break;
			case MOVE_RIGHT: moveRight();
				break;
			case RELOAD: reload();
				break;
			case PAUSE: sleep();
				break;
		}
	}
	
	public void reload() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {

		}
		shots = maxShots;
	}	
	
	public void showExplosion() {
		this.setImage("explosion.gif");
	}
	
	
	public void sleep() {
		try {
			Thread.sleep(sleepTime);
		} 
		catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
		
	public void setInstructionSet(InstructionSet instructions) {
		this.instructions = instructions;
	}

	public InstructionSet getInstructionSet() {
		return instructions;
	}

	public long getNumHits() {
		return numHits;
	}

	public void setNumHits(long numHits) {
		this.numHits = numHits;		
	}
	
	protected void setSleepTime(int i) {
		this.sleepTime = i;
	}
}
