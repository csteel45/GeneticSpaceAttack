/*
 * AttackerPopulation.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships.organic;

import java.util.ArrayList;
import java.util.Random;

import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.model.ships.Attacker;
import com.fortmoon.genetic.game.model.ships.AttackerType;
import com.fortmoon.genetic.game.model.ships.InstructionSet;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * @since Mar 24, 2011 1:13:44 PM
 * @version 1.0
 */
public class AttackerPopulation extends Population {
	private static AttackerPopulation instance;
	private static int size = 10; // Population size
	private ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>(size);
	//private Random random = new Random(System.currentTimeMillis());
	
	private AttackerPopulation() {
		super(size);
		for(int i = 0; i < size; i++) { 
			chromosomes.add(new Chromosome()); 
		}
	}

	public static AttackerPopulation getInstance() {
		if(instance == null) {
			instance = new AttackerPopulation();
		}
		return instance;
	}

	public InstructionSet getGeneticInstructionSet() {
		if(instance == null) {
			instance = new AttackerPopulation();
		}
		return this.getNext();
	}
}
