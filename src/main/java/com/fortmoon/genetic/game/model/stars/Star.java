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

package com.fortmoon.genetic.game.model.stars;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 9:01:05 PM
 */
public class Star implements Serializable {

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Color color;
	private int speed;
	private int size;
	private static Random random = new Random(System.currentTimeMillis());
	
	public Star(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		for(int i = 0; i < random.nextInt(8); i++)
			this.color = this.color.brighter();
		this.size = random.nextInt(3) + 1; //1, 2, or 3
		this.speed = (size * 2) + random.nextInt(4);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
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

	public int getSpeed() {
		return speed;
	}

	public int getSize() {
		return size;
	}

}
