/*
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
