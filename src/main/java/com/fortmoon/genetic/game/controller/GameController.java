package com.fortmoon.genetic.game.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.fortmoon.genetic.game.model.Screen;
import com.fortmoon.genetic.game.model.ships.Attacker;
import com.fortmoon.genetic.game.model.ships.AttackerFactory;
import com.fortmoon.genetic.game.model.ships.AttackerType;
import com.fortmoon.genetic.game.model.ships.Attackers;
import com.fortmoon.genetic.game.model.ships.Defender;
import com.fortmoon.genetic.game.model.stats.Stats;
import com.fortmoon.genetic.game.view.GameView;

public class GameController implements Observer {
    private static final Logger LOG = Logger.getLogger(GameController.class.getName());

//	private GameModel model;
	private GameView view;
	private Attacker attacker;
	private MoveKeyListener keyListener;
	private GameController instance;
	private Random random = new Random(System.currentTimeMillis());
	private int numAttackers = 1;

	public GameController(GameView view) {
		instance = this;
//		this.model = model;
		this.view = view;
//		model.addObserver(this);
		Defender ship = new Defender(Screen.getWidth()/2, Screen.getHeight() - 106, "ship.gif");
		LOG.info("Defender height = " + ship.getHeight());
		keyListener = new MoveKeyListener(ship);
	    view.addKeyListener(keyListener);
	    ship.registerObserver(this);
	    ship.start();

	    attacker = AttackerFactory.getAttacker(AttackerType.Random);
	    attacker.registerObserver(this);
	    Attackers.getInstance().add(attacker);
	    attacker.start();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Defender) {
			keyListener.removeDefender();
//			Stats.getStats().setProgrammedScore(Stats.getStats().getProgrammedScore() + 1000);

			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(5000);
					} 
					catch (InterruptedException e) {
					}
					Defender ship = new Defender(Screen.getWidth() / 2, Screen.getHeight() - 106, "ship.gif");
					keyListener.addDefender(ship);
					ship.registerObserver(instance);
					ship.start();
				}
			});
			t.start();
		}
		else {
			synchronized (this) {
				numAttackers--;

				if (numAttackers < 5) {
					for (int i = 0; i < 2; i++) {

						// 50% Genetic, 25% Random, 25% Programmed.
						//if (random.nextBoolean()) {
						if (true) {
							attacker = AttackerFactory
									.getAttacker(AttackerType.Genetic);
						} 
						else {
							if (random.nextBoolean()) {
								attacker = AttackerFactory
										.getAttacker(AttackerType.Programmed);
								LOG.info("Programmed type");
							} else {
								attacker = AttackerFactory
										.getAttacker(AttackerType.Random);
								LOG.info("Random type");
							}
						}
						attacker.registerObserver(this);
						Attackers.getInstance().add(attacker);
						attacker.start();
					}
					numAttackers += 2;
					Stats.getStats().setPlayerScore(Stats.getStats().getPlayerScore() + 100);
				}
			}
		}
	}

}
