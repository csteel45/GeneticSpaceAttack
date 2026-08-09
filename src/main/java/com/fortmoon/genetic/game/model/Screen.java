/*
 * Screen.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
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
