/*
 * GeneticAttacker.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships.organic;

import com.fortmoon.genetic.game.model.ships.Attacker;
import com.fortmoon.genetic.game.model.ships.AttackerType;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * @since Apr 4, 2011 8:31:12 PM
 * @version 1.0
 */
public class GeneticAttacker extends Attacker {	
	private Population population = AttackerPopulation.getInstance();

	/**
	 * @param x
	 * @param type
	 */
	public GeneticAttacker(int x, AttackerType type) {
		super(x, type);
	}
	
	public synchronized void destroy() {
		super.destroy();
		population.kill((Chromosome)instructions);
	}
	
	public void setNumHits(long numHits) {
		super.setNumHits(numHits);
		((Chromosome)this.instructions).setNumHits((int)numHits);
	}
}
