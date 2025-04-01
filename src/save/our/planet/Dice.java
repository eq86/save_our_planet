/**
 * 
 */
package save.our.planet;

import java.util.Random;

/**
 * Class to store dice rolling method used in the game.
 */
public class Dice {
	
	/**
	 * to roll the dice and return the total
	 * 
	 * @return the total of the dice roll
	 */
	public static int roll() {
		Random die = new Random();
		int die1 = die.nextInt(5) + 1;
		int die2 = die.nextInt(5) + 1;
		return die1 + die2;
	}

}
