/**
 * 
 */
package save.our.planet;

/**
 * Class that represents the 'Go' square
 */
public class Earth extends Square {

	/**
	 * Default constructor
	 */
	public Earth() {

	}

	/**
	 * Constructor with args
	 * @param squareId
	 * @param squareName
	 * @param galaxy
	 * @throws IllegalArgumentException
	 */
	public Earth(int squareId, String squareName, Galaxy galaxy) throws IllegalArgumentException {
		super(squareId, squareName, galaxy);
	}

}
