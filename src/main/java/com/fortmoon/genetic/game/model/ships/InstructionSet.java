/*
 * InstructionSet.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 10, 2011 3:40:52 PM
 */
public class InstructionSet extends ArrayList<Instruction> {
	protected Random random = new Random(System.currentTimeMillis());
	
	public InstructionSet() {
		super();
	}
	
	public InstructionSet(int size) {
		super(size);
	}

	private static final long serialVersionUID = 1L;
	
}
