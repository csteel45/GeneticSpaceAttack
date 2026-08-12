/*
 * AttackerFactory.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships;

import java.util.Random;

import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.model.ships.organic.AttackerPopulation;
import com.fortmoon.genetic.game.model.ships.organic.GeneticAttacker;


/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 10, 2011 2:45:45 PM
 */
public class AttackerFactory {
	public enum InstructionType {RANDOM, PROGRAMMED, GENETIC};
	private static Random random = new Random(System.currentTimeMillis());
	
	public static InstructionSet getInstructionSet(InstructionType type) {
		if(type == InstructionType.RANDOM) {
			return getRandomInstructionSet();
		}
		if(type == InstructionType.PROGRAMMED) {
			return getProgrammedInstructionSet();
		}
		if(type == InstructionType.GENETIC) {
			//return getGeneticInstructionSet();
			return null;
		}
		return null;
	}
	
	/**
	 * Returns a chromosome from the AttackerPopulation as an InstructionSet.
	 * @return chromosome from the AttackerPopulation
	 */
	private static InstructionSet getGeneticInstructionSet() {
		AttackerPopulation pop = AttackerPopulation.getInstance();
		InstructionSet instructionSet = pop.getGeneticInstructionSet();
		return instructionSet;
	}

	/**
	 * Returns a pre-programmed <code>InstructionSet</code><br>
	 * Should be modified to read a property file and may want to add a UI editor.
	 * @return A pre-programmed <code>InstructionSet</code>
	 * @see InstructionSet
	 */
	private static InstructionSet getProgrammedInstructionSet() {
		InstructionSet instructions = new InstructionSet();

		instructions.add(new Instruction(Action.MOVE_TOWARD));
		instructions.add(new Instruction(Action.MOVE_TOWARD));
		instructions.add(new Instruction(Action.MOVE_TOWARD));
		instructions.add(new Instruction(Action.FIRE));
		instructions.add(new Instruction(Action.FIRE));
		instructions.add(new Instruction(Action.FIRE));
		instructions.add(new Instruction(Action.MOVE_AWAY));
		instructions.add(new Instruction(Action.MOVE_AWAY));
		instructions.add(new Instruction(Action.MOVE_AWAY));
		instructions.add(new Instruction(Action.MOVE_TOWARD));
		instructions.add(new Instruction(Action.RELOAD));
		
		return instructions;
	}

	public static InstructionSet getRandomInstructionSet() {
		int size = random.nextInt(11) + 5; // A random set size of between 5 - 15 instructions 
		InstructionSet instructions = new InstructionSet(size);
		for(int i = 0; i < size; i++) {
			instructions.add(new Instruction(Action.values()[random.nextInt(Action.values().length)]));
		}
		
		return instructions;
	}

	public static Attacker getAttacker(AttackerType type) {
		Attacker attacker = null;
		InstructionSet instructionSet = null;
		Defender defender = Defender.getDefender();
		int swidth = Screen.getWidth();

		int dx = random.nextInt(swidth);
		if(defender != null)
			dx = defender.x;
		
		if(type == AttackerType.Programmed)
			instructionSet = getProgrammedInstructionSet();
		if(type == AttackerType.Random)
			instructionSet = getRandomInstructionSet();
		if(type == AttackerType.Genetic) {
			instructionSet = getGeneticInstructionSet();
			attacker = new GeneticAttacker(dx > swidth/2 ? 
						(dx - swidth/2 + random.nextInt(400)) : 
						(dx + swidth/2 - random.nextInt(400)), 
						type);
		}
		else {
			attacker = new Attacker(dx > swidth/2 ? dx - swidth/2 : dx + swidth/2, type);
		}

		
		attacker.setInstructionSet(instructionSet);
		return attacker;
	}
}
