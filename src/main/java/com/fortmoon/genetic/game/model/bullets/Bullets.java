/*
 * Bullets.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.bullets;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 11:08:30 PM
 */
public class Bullets extends Thread implements Serializable, Runnable {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Bullet> bullets;
	private boolean stop = false;
	private static Bullets instance;
	
	/**
	 * Private constructor so no one else can create and instance. You have to use the getInstance method.
	 * This is known as the Singleton design pattern. It ensures all other classes get the same one instance.
	 */
	private Bullets() {
		bullets = new ArrayList<Bullet>();
	}
	
	public static synchronized Bullets getInstance() {
		if(instance == null) {
			instance = new Bullets();
			instance.start();
		}
		return instance;
	}
	
	public synchronized void add(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void run() {
		while(!stop ) {
			//moveBullets();
			try {
				//moveBullets();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized void moveBullets() {
		if(bullets.isEmpty()) {
			return;
		}
		ArrayList<Integer> deleted = new ArrayList<Integer>();
		for(Bullet bullet : bullets) {
			bullet.setLocation((int)bullet.getX(), (int)bullet.getY() - 5);
			if(bullet.getY() <= 0) {
				deleted.add(bullets.indexOf(bullet));
			}
		}
		for(Integer index : deleted) {
			bullets.remove(index.intValue());
		}
		deleted.clear();
	}
	
	public void remove(Object o) {
		bullets.remove(o);
	}

	/**
	 * @return the bullets
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Bullet> getBullets() {
		if(bullets == null)
			getInstance();
		return (ArrayList<Bullet>) bullets.clone();
	}
}
