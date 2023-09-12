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

import com.fortmoon.genetic.game.model.ships.AttackerFactory;
import com.fortmoon.genetic.game.model.ships.Instruction;
import com.fortmoon.genetic.game.model.ships.InstructionSet;


/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 10, 2011 11:25:15 PM
 */
public class Chromosome extends InstructionSet implements Comparable<Chromosome> {
	private static final long serialVersionUID = 1L;
	private static Integer counter = 0;
	private int number = 0;
	private int numHits = 0;
	private Long fitness;
	private long birthTime;
	private long deathTime;
	
	public Chromosome() {
		super();
		synchronized(counter) {
			counter++;
			number = counter;
		}
		// Need to add some random Genes (actions)
		this.addAll(AttackerFactory.getRandomInstructionSet());
	}

	public long calculateFitness() {
		// Count every hit as an extra minute of life
		System.out.println("CHROMOSOME " + this.number + " has this many hits: " + this.numHits);
		fitness = Long.valueOf((getLifetime() + (numHits * 60)));
		if(fitness.longValue() == 0l) {
			System.out.println("0 Fitness: birth: " + this.birthTime + " death: " + this.deathTime + " hits: " + this.numHits);
		}
		return fitness.longValue();
	}

	/**
	 * @return the numHits
	 */
	public int getNumHits() {
		return numHits;
	}

	/**
	 * @param numHits the numHits to set
	 */
	public void setNumHits(int numHits) {
		this.numHits = numHits;
	}

	public void mutate() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.size(); i++) {
			if(random.nextInt(100) > 98)
				// Set on of the genes to a new Random gene.
				this.set(i, Instruction.getRandom());
		}
	}
	
	/*
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Chromosome o) {
		if (o instanceof Chromosome) {
			Chromosome c = (Chromosome) o;
			return (this.fitness.compareTo(c.fitness) < 0 ? -1 : (this.fitness == c.fitness ? 0 : 1));
		}
		return 0;
	}

	/**
	 * @param lifetime the lifetime to set
	 */
	public long getLifetime() {
		if(deathTime > 0)
			return (deathTime - birthTime) / 1000;
		else 
			return (System.currentTimeMillis() - birthTime)/1000;
	} 
	
	public synchronized void setBirthTime() {
		deathTime = 0l;
		birthTime = System.currentTimeMillis();
	}
	
	public synchronized void setDeathTime() {
		deathTime = System.currentTimeMillis();
		//System.out.println("Chromosome.setDeathTime lifetime = " + this.getLifetime());
	}
	
	public int getNumber() {
		return number;
	}
	
	public String toString() {
		return "Chromsome " + number + ": " + super.toString();
	}
}
