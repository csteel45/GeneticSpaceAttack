package com.fortmoon.genetic.game.model.ships.organic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.fortmoon.genetic.game.model.ships.Instruction;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * @since Apr 5, 2011 3:40:52 PM
 */
public class Population {
	protected ArrayList<Chromosome> chromosomes;
	protected Random random = new Random(System.currentTimeMillis());
	protected float mutationRate = 0.4f;
	private int counter;
	public static boolean DEBUG = false;
	
	public Population(int size) {
		chromosomes = new ArrayList<Chromosome>(size);
		for(int i = 0; i < size; i++) {
			chromosomes.add(new Chromosome());
		}
	}
	
	/**
	 * This method is the primary driver behind the success of the genetic algorithm.
	 * It takes the population of chromosomes and exchanges gene sequences. The chromosomes
	 * can be of differing lengths and this will exchange randomly sized sequences. This
	 * method does not actually crossover in the sense that the sequences are passed from
	 * fittest to least fit. The Fittest chromosome will never receive genes.
	 */
	public void doCrossovers() {
		Chromosome chromosome = chromosomes.get(0);
		for(int i = 1; i < chromosomes.size() - 1; i++) {
			// The target chromosome is the next one in the list. This means the fittest chromosome's
			// genes could be replicated all the way through the chromosome population.
			Chromosome target = chromosomes.get(i);
			if(DEBUG) {
				System.out.println("Chromosome: " + target);
			}

			// Start at a random gene in the chromosome
			int geneIndex = random.nextInt(chromosome.size());
			if(DEBUG)
				System.out.println("geneIndex = " + geneIndex);
			// Copy a random number of genes, starting at the random index from one chromosome to the next.
			if(target.size() <= geneIndex)
				continue;
			int randomInt = random.nextInt(target.size() - geneIndex);
			//System.out.println("chromosomeSize = " + chromosome.size() + " targetSize = " + target.size());
			for(int j = 0; j < randomInt; j++) {
				if(geneIndex + j < chromosome.size()) {
					Instruction gene = chromosome.get(geneIndex + j);
					target.set(geneIndex + j, gene);
				}
			}
			if(DEBUG) {
				System.out.println("Crossed over chromosome: " + target);
			}
			// Get the next chromosome and repeat the process.
			chromosome = chromosomes.get(i);
		}
	}
	
	/**
	 * This method performs random mutations within the chromosome population. Starting with the
	 * bottom 90% of the population, it will randomly mutate a number of chromosomes based on 
	 * the mutationRate field.<br>
	 * For a population of 48 chromosomes, with a mutation rate of 20%, it will randomly select 
	 * 9 out of the 42 least fit chromosomes. To avoid issues with local maximas/minimas, set 
	 * a higher mutation rate.
	 */
	public void doMutations() {
		// Do mutations on 20% of the population below the top 10%
		int numMutations = (int)(chromosomes.size() * mutationRate);
		int startingIndex = (int)(chromosomes.size() * .1);
		if(DEBUG)
			System.out.println("numMutations = " + numMutations + " startingIndex = " + startingIndex);

		for(int i = 0; i < numMutations; i++) {
			Chromosome c = chromosomes.get(startingIndex + random.nextInt(chromosomes.size() - startingIndex));
			c.mutate();
		}
	}

	/**
	 * @return Fittest Chromosome in the Population.
	 */
	public synchronized Chromosome getFittest() {
		sort();
		return chromosomes.get(0);
	}

	/**
	 * Sort the Population based on fittest to least fit.
	 */
	protected void sort() {
		for(Chromosome c : chromosomes) {
			c.calculateFitness();
//			System.out.println("UnSorted chromosomes: " + c);
		}

		Collections.sort(chromosomes);
		Collections.reverse(chromosomes);

//		for(Chromosome c : chromosomes)
//			System.out.println("Sorted chromosomes: " + c);
	}


	/**
	 * @return the mutationRate
	 */
	public float getMutationRate() {
		return mutationRate;
	}

	/**
	 * @param mutationRate the mutationRate to set
	 */
	public void setMutationRate(float mutationRate) {
		this.mutationRate = mutationRate;
	}
	
	public synchronized Chromosome getNext() {
		// Don't use an iterator in case we add some Chromosomes during execution.
		Chromosome c = null;
		if(counter < this.chromosomes.size()) {
			c = this.chromosomes.get(counter);
			counter++;
		}
		else {
			// Now that we have gone through the population, let's crossover and mutate
			sort();
			doCrossovers();
			doMutations();
			
			// Start again with a healthier (more fit) population.
			c = this.chromosomes.get(0);
			counter = 1;  //equivalent of setting counter = 0 and then incrementing.
			System.out.println("STARTING NEW GENERATION");
		}
		c.setBirthTime();
		c.setNumHits(0);
		return c;
	}

	public synchronized void kill(Chromosome chromosome) {
		chromosome.setDeathTime();
	}
	
	public static void main(String[] args) {
		Population pop = new Population(48);
		pop.doCrossovers();
		pop.doMutations();
	}
	
}
