/*
 * Instruction.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships;

import java.util.Random;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * @since Mar 22, 2011 1:31:08 PM
 * @version 1.0
 */
public class Instruction {
	Action action;
	public static Random random = new Random(System.currentTimeMillis());
	
	public Instruction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public String toString() {
		return action.toString();
	}

	public static Instruction getRandom() {
		Instruction instruction = new Instruction(Action.values()[random.nextInt(Action.values().length)]);
		return instruction;
	}
}
