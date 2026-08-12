/*
 * Attacker.java
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
 * Mar 1, 2011 11:01:34 AM
 */
public class Attacker extends Ship {
	private static final long serialVersionUID = 1L;
	private Attacker instance;
	private AttackerType type;
	private static Integer counter = 0;
	private int number = 0;

	public Attacker(int x, AttackerType type) {
		super();
		synchronized(counter) {
			number = counter++;
		}
		instance = this;
		this.type = type;
		setImage("attackship.gif");
		setLocation(x, 30);
		setMoveIncrement(10);
		setSleepTime(5);
	}

	public void run() {
		while(alive) {
			for(Instruction instruction : instructions) {
				execute(instruction);
				sleep();
				if(!alive)
					break;
			}
		}
	}
	
	public void execute(Instruction instruction) {
		// Execute the default action
		super.execute(instruction);
		// If not a default action, execute attacker-specific action.
		switch(instruction.getAction()) {
			case MOVE_TOWARD: moveToward();
				break;
			case MOVE_AWAY: moveAway();
				break;
			case FIRE: fire();
				break;
			case RANDOM: execute(new Instruction(Action.values()[random.nextInt(Action.values().length - 1)]));
				// Use length - 1 and keep RANDOM as last action so we don't randomly pick RANDOM over again.
				break;
			//default: System.out.println("Unrecognized action");
		}
	}
	
	@Override
	public synchronized void destroy() {
		if(!alive) {
			return;
		}
		System.out.println("Destroy: " + this.toString());
		super.destroy();
		showExplosion();
		Runnable r = new Runnable() {
			public void run() {
				try {
					Thread.sleep(200);
					Attackers.getInstance().remove(instance);
				} catch (InterruptedException e) {
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	public void fire() {
		if(Defender.getDefender() == null) {
			return;
		}
		if(shots == 0) {
			// Can't fire because we haven't reloaded
			sleep();
			reload();
		}
		Bullet bullet = new Bullet(this, this.x + (this.width / 2), this.y + this.height, Color.YELLOW, Direction.DOWN);
		bullet.start();
		Bullets.getInstance().add(bullet);

		shots--;		
	}

	public void moveToward() {
		if(Defender.getDefender() == null || Defender.getDefender().getX() == x)
			//If we are already in line with ship, just return and do nothing.
			return;
		
		if(Defender.getDefender().getX() < x)
			moveLeft();
		else
			moveRight();
	}


	public void moveAway() {
		if(Defender.getDefender() == null)
			return;
		
		// Check if we are at same x as ship
		if(Defender.getDefender().getX() == x) {
			//We want to move away in the widest direction, so if we are on left side (or middle), move right
			if(x <= 0) 
				moveRight();
			else
				moveLeft();
		}

		if(Defender.getDefender().getX() < x)
			moveRight(); 	//The ship is to our left so move right, further away
		else
			moveLeft();		//The ship is to our right so move left, further away
	}

	public boolean isAlive() {
		return alive;
	}

	public void setType(AttackerType type) {
		this.type = type;
	}

	public AttackerType getType() {
		return type;
	}
	
	public String toString() {
		return type + ":" + number;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

}
