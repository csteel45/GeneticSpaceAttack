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
