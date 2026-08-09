/*
 * Attackers.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 11:08:30 PM
 */
public class Attackers implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Attacker> attackers;
	private static Attackers instance;
	
	/**
	 * Private constructor so no one else can create and instance. You have to use the getInstance method.
	 * This is known as the Singleton design pattern. It ensures all other classes get the same one instance.
	 */
	private Attackers() {
		attackers = new ArrayList<Attacker>();
	}
	
	public static synchronized Attackers getInstance() {
		if(instance == null) {
			instance = new Attackers();
		}
		return instance;
	}
	
	public synchronized void add(Attacker attacker) {
		attackers.add(attacker);
	}

	public synchronized void remove(Attacker attacker) {
		attackers.remove(attacker);
	}

	/**
	 * @return the attackers
	 */
	public static ArrayList<Attacker> getAttackerList() {
		if(attackers == null) 
			Attackers.getInstance();
		return (ArrayList<Attacker>) attackers.clone();
	}
}
