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

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 9, 2011 10:12:08 AM
 */
public class Screen {
	private static int width;
	private static int height;

	private Screen() {
	}
	
	/**
	 * @return the width
	 */
	public static int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public static void setWidth(int newWidth) {
		width = newWidth;
	}
	/**
	 * @return the height
	 */
	public static int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public static void setHeight(int newHeight) {
		height = newHeight;
	}
	
	
}
