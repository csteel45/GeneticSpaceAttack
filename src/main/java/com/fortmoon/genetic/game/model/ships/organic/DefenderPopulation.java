/*
 * DefenderPopulation.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships.organic;

import java.util.ArrayList;
import java.util.Collections;

import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.model.ships.Defender;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * @since Mar 24, 2011 1:13:44 PM
 * @version 1.0
 */
public class DefenderPopulation extends ArrayList {
	private int size = 48; // Population size
	private ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>(size);
	
	public DefenderPopulation() {
		
	}
	
	public Defender getShip() {
		Defender ship = new Defender(Screen.getWidth()/2, Screen.getHeight() - 106, "ship.gif");
		ship.setAutoPilot(true);
		return ship;
	}
	
	/**
	 * @return
	 */
	public Chromosome getFittest() {
		// TODO Auto-generated method stub
		sort();
		return chromosomes.get(0);
	}
	
	/**
	 * 
	 */
	public void sort() {
		Collections.sort(chromosomes);		
	}
}
