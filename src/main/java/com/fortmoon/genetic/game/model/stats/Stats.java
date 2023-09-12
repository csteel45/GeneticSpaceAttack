/*
 * FortMoon Consulting Corporation, 44311 Ladiesburg Place, Ashburn, VA 20147.
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
