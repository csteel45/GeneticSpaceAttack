/*
 * Stats.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.stats;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 9, 2011 8:15:09 PM
 */
public class Stats {
	private long playerScore;
	private long programmedScore;
	private long geneticScore;
	private long randomScore;
	private static Stats instance = null;
	
	private Stats() {
	}
	
	public static Stats getStats() {
		if(instance == null) {
			instance = new Stats();
		}
		return instance;
	}

	/**
	 * @return the playerScore
	 */
	public long getPlayerScore() {
		return playerScore;
	}

	/**
	 * @param playerScore the playerScore to set
	 */
	public void setPlayerScore(long playerScore) {
		this.playerScore = playerScore;
	}

	/**
	 * @return the programmedScore
	 */
	public long getProgrammedScore() {
		return programmedScore;
	}

	/**
	 * @param programmedScore the programmedScore to set
	 */
	public void setProgrammedScore(long programmedScore) {
		this.programmedScore = programmedScore;
	}

	/**
	 * @return the geneticScore
	 */
	public long getGeneticScore() {
		return geneticScore;
	}

	/**
	 * @param geneticScore the geneticScore to set
	 */
	public void setGeneticScore(long geneticScore) {
		this.geneticScore = geneticScore;
	}

	/**
	 * @return the randomScore
	 */
	public long getRandomScore() {
		return randomScore;
	}

	/**
	 * @param randomScore the randomScore to set
	 */
	public void setRandomScore(long randomScore) {
		this.randomScore = randomScore;
	}
}
