/*
 * MoveKeyListener.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.controller;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fortmoon.genetic.game.model.ships.Defender;
import com.fortmoon.genetic.game.view.GameView;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 2:23:26 PM
 */
public class MoveKeyListener implements KeyListener, Runnable {
	private Defender defender;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean spacePressed = false;

	private MoveKeyListener() {	
		Thread t = new Thread(this);
		t.start();
	}
	
	public MoveKeyListener(Defender ship) {
		this();
		this.defender = ship;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		// Escape will exit the game no matter what.
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		// If the intro is showing, any key besides Escape will kill the intro and start the game
		if(GameView.isIntro()) {
			GameView.setIntro(false);
			return;
		}

		// If the defender has been hit and is null, return;
		if(defender == null)
			return;
				
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT ) {
			leftPressed = true;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT ) {
			rightPressed = true;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if(spacePressed == true)
				return;
			spacePressed = true;
			defender.fire();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			defender.setAutoPilot(!defender.getAutoPilot());
		}
		
	}
	
	public void run() {
		while(true) {
			if (leftPressed) {
				if(defender != null)
					defender.moveLeft();
			}
			if (rightPressed) {
				if(defender != null)
					defender.moveRight();
			}
			sleep(2);
		}
	}
	
	public void sleep(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			spacePressed = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public void removeDefender() {
		defender = null;
	}

	public void addDefender(Defender defender) {
		this.defender = defender;
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.addKeyListener(new MoveKeyListener());
		frame.setVisible(true);
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
