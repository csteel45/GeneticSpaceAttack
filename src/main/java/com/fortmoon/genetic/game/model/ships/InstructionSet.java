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

import java.util.ArrayList;
import java.util.Random;


/**
 * @author Christopher Steel - FortMoon Consulting
 *
 * Mar 10, 2011 3:40:52 PM
 */
public class InstructionSet extends ArrayList<Instruction> {
	protected Random random = new Random(System.currentTimeMillis());
	
	public InstructionSet() {
		super();
	}
	
	public InstructionSet(int size) {
		super(size);
	}

	private static final long serialVersionUID = 1L;
	
}
