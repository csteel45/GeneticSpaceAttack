/*
 * GeneticAlgorithmTest.java
 *
 * Copyright (c) 2011-2026 Chris Steel (FortMoon Consulting, Inc.)
 * SPDX-License-Identifier: MIT
 * See the LICENSE file in the project root for the full license text.
 */

package com.fortmoon.genetic.game.model.ships.organic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GeneticAlgorithmTest {

    @Test
    void newChromosomeIsSeededNonEmpty() {
        assertTrue(new Chromosome().size() > 0, "constructor should seed a random instruction set");
    }

    @Test
    void numHitsRoundTrips() {
        Chromosome c = new Chromosome();
        c.setNumHits(7);
        assertEquals(7, c.getNumHits());
    }

    @Test
    void eachHitIncreasesFitness() {
        Chromosome c = new Chromosome();
        c.setNumHits(1);
        long low = c.calculateFitness();
        c.setNumHits(10);
        long high = c.calculateFitness();
        assertTrue(high > low, "more hits should yield higher fitness");
    }

    @Test
    void mutatePreservesChromosomeSize() {
        Chromosome c = new Chromosome();
        int before = c.size();
        c.mutate();
        assertEquals(before, c.size(), "mutation replaces genes but does not add or remove them");
    }

    @Test
    void populationHasAFittestMember() {
        assertNotNull(new Population(10).getFittest());
    }

    @Test
    void crossoversAndMutationsDoNotThrow() {
        Population p = new Population(12);
        assertDoesNotThrow(() -> {
            p.doCrossovers();
            p.doMutations();
        });
        assertNotNull(p.getFittest());
    }

    @Test
    void mutationRateRoundTrips() {
        Population p = new Population(3);
        p.setMutationRate(0.7f);
        assertEquals(0.7f, p.getMutationRate(), 0.0001f);
    }
}
