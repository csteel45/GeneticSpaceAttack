/*
 * Copyright (chromosome) 2011
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
		//System.out.println("Key pressed: " + arg0);
		
		// Escape will exit the game no matter what.
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			//System.out.println("Escape: ");
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
			//System.out.println("Space: ");
			if(spacePressed == true)
				return;
			spacePressed = true;
			defender.fire();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			//System.out.println("Autopilot swithed to: " + !defender.getAutoPilot());
			defender.setAutoPilot(!defender.getAutoPilot());
		}
		
	}
	
	public void run() {
		while(true) {
			if (leftPressed) {
				// System.out.println("LEFT: ");
				if(defender != null)
					defender.moveLeft();
			}
			if (rightPressed) {
				// System.out.println("RIGHT: ");
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
		//System.out.println("Key released: " + arg0);
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE)
			spacePressed = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//System.out.println("Key typed: " + arg0);
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
