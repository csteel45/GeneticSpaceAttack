/*
 * Copyright (©) 2011 FortMoon Consulting
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
