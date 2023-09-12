/*
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

package com.fortmoon.genetic.game.model.bullets;

import java.awt.Color;

import com.fortmoon.genetic.game.model.GameObject;
import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.model.ships.Attacker;
import com.fortmoon.genetic.game.model.ships.Attackers;
import com.fortmoon.genetic.game.model.ships.Defender;
import com.fortmoon.genetic.game.model.ships.Ship;


/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 7:06:37 PM
 */
public class Bullet extends GameObject {
	public static int WIDTH = 4;
	public static int HEIGHT = 16;
	private int speed = 20;
	private Color color;
	private Direction direction;
	private Ship ship;

	public enum Direction {
		UP, DOWN;
	}
	
	private Bullet() {
	}
	
	public Bullet(Ship ship, int x, int y, Color c, Direction direction) {
		this.ship = ship;
		color = c;
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		this.direction = direction;
	}
	
	@Override
	public void run() {
		while (alive) {
			sleep();
			move();
		}
		//System.out.println(" Bullet destroyed.");
	}
	
	public void move() {
		if(direction.equals(Direction.DOWN)) {
			this.y += 2;	
		}
		else {
			this.y -= 2;
		}
		
		if(getHit()) {
			//System.out.println("HIT!");
			ship.setNumHits(ship.getNumHits() + 1);
			destroy();
			return;
		}
		
		if(this.y >= Screen.getHeight() || this.y <= 0) {
			destroy();
		}
	}
	
	public boolean getHit() {
		Defender ship = Defender.getDefender();

// The 2 lines of code below will keep bullets from a dead ship from killing attackers.
//		if(ship == null)
//			return false;
		if(ship != null && ship.intersects(this)) {
			//System.out.println("Destroyed ship.");
			ship.destroy();
			return true;
		}
		
		Attacker deadAttacker = null;
		for(Attacker attacker : Attackers.getAttackerList()) {
			// First check if Bullet is high enough to hit and then check if it is between the edges of the attacker
			if (attacker.isAlive() && attacker.intersects(this)) {
				deadAttacker = attacker;
			}
		}
		if(deadAttacker != null) {
//			Attackers.getAttackers().remove(deadAttacker);
			deadAttacker.destroy();
			return true;	
		}
		return false;
	}
	
	@Override
	public synchronized void destroy() {
		try {
			super.destroy();
		}
		catch(Exception e) {
			System.out.println("Exception destroyinbg bullet: " + e);
			e.printStackTrace();
		}
		finally {
			Bullets.getInstance().remove(this);
		}
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
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

}
