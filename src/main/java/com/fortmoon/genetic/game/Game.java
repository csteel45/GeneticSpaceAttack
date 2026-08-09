/*
 * Game.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.fortmoon.genetic.game.controller.GameController;
import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.view.GameView;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 1, 2011 1:01:52 PM
 */
public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GameView view = new GameView();
		JFrame frame = new JFrame();
		 GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode mode = myDevice.getDisplayMode();
		int width = mode.getWidth();
		System.out.println("Width = " + width);
		int height = mode.getHeight();
		System.out.println("Height = " + height);
		Screen.setWidth(width);
		Screen.setHeight(height);

		frame.setSize(width, height);
	    frame.setUndecorated(true);
	    frame.setResizable(false);
        frame.setIgnoreRepaint(true);
	    frame.setForeground(Color.BLACK);

		frame.add(view);
		view.setFocusable(true);
		frame.setVisible(true);
		GameController controller = new GameController(view);

		view.init();    


	}

}
