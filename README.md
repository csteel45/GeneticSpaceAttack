# Genetic Space Attack

A Swing space-shooter where the **enemy ships evolve** via a genetic algorithm that
breeds against the player's performance. A small MVC game built as a fun demonstration
of evolutionary computation.

## Build & run
```bash
mvn install
mvn exec:java        # runs com.fortmoon.genetic.game.Game
```

## How it works

The enemies aren't scripted — they **evolve**:

- Each attacker's behavior is an **`InstructionSet`** (a sequence of movement/fire
  instructions). A `Chromosome` *is* one such instruction set.
- A `Population` of chromosomes drives the attacking wave. Each chromosome's **fitness**
  rewards survival and aggression — roughly `lifetime + (hits × 60)` — so attackers that
  live longer and hit the player more are considered fitter.
- Every generation the population is **sorted by fitness**, the fittest genes are
  **crossed over** into the rest (`doCrossovers`), and genes are randomly **mutated**
  (`doMutations`, at a configurable `mutationRate`). Over successive waves the swarm
  adapts to how you play.

The code is layered model / view / controller under `com.fortmoon.genetic.game`, with the
evolutionary logic under `...model.ships.organic`.

## History & scope

A personal project — authored in 2011 (per the source headers) and committed to version
control in 2017. It's a Swing space-shooter, cleanly layered into model/view/controller,
in which the enemy ships **evolve** via a genetic algorithm that breeds against the
player's performance — a playful demonstration of evolutionary computation.

## License
MIT — see [LICENSE](LICENSE).
