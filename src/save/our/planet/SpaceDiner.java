/**
 * 
 */
package save.our.planet;

/**
 * Class that represents 'Free parking' style square
 */
public class SpaceDiner extends Square {

	/**
	 * Default Constructor
	 */
	public SpaceDiner() {
		
	}

	/**
	 * Constructor with Args
	 * @param squareId
	 * @param squareName
	 * @param galaxy
	 * @throws IllegalArgumentException
	 */
	public SpaceDiner(int squareId, String squareName, Galaxy galaxy) throws IllegalArgumentException {
		super(squareId, squareName, galaxy);
	}

}
