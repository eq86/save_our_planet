/**
 * 
 */
package save.our.planet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Dice class - static method
 */
class DiceTest {

	int diceResult;

	/**
	 * Before each
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		diceResult = Dice.roll();

	}

	/**
	 * Tests roll() method functions correctly
	 */
	@Test
	void testRoll() {
		assertTrue(diceResult >= 2 && diceResult <= 12);
	}

}
