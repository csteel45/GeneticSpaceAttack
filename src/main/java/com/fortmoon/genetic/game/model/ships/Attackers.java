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

package com.fortmoon.genetic.game.model.ships;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 7, 2011 11:08:30 PM
 */
public class Attackers implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Attacker> attackers;
	private static Attackers instance;
	
	/**
	 * Private constructor so no one else can create and instance. You have to use the getInstance method.
	 * This is known as the Singleton design pattern. It ensures all other classes get the same one instance.
	 */
	private Attackers() {
		attackers = new ArrayList<Attacker>();
	}
	
	public static synchronized Attackers getInstance() {
		if(instance == null) {
			instance = new Attackers();
		}
		return instance;
	}
	
	public synchronized void add(Attacker attacker) {
		attackers.add(attacker);
	}

	public synchronized void remove(Attacker attacker) {
		attackers.remove(attacker);
	}

	/**
	 * @return the attackers
	 */
	public static ArrayList<Attacker> getAttackerList() {
		if(attackers == null) 
			Attackers.getInstance();
		return (ArrayList<Attacker>) attackers.clone();
	}
}
