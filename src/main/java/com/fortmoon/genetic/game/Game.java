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
import javax.swing.SwingUtilities;

import com.fortmoon.genetic.game.controller.GameController;
import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.view.GameView;

/**
 * Entry point for the Genetic Space Attack game.
 *
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 1, 2011 1:01:52 PM
 */
public class Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::launch);
    }

    private static void launch() {
        GameView view = new GameView();
        JFrame frame = new JFrame();
        GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode mode = myDevice.getDisplayMode();
        int width = mode.getWidth();
        int height = mode.getHeight();
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
        new GameController(view);
        view.init();
    }
}
