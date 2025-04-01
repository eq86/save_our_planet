/**
 * 
 */
package save.our.planet;

/**
 * Abstract class that represents a square on the board before being given a
 * field.
 */
public abstract class Square {

	// Constants
	private static final int SQUAREID_MIN = 1;
	private static final int SQUARE_NAME_LENGTH_MIN = 1;

	// Instance Vars
	private int squareId;
	private String squareName;
	private Galaxy galaxy;

	// Constructors
	/**
	 * Default Constructor
	 */
	public Square() {

	}

	/**
	 * Constructor with args.
	 * 
	 * @param squareId
	 * @param squareName
	 * @param galaxy
	 */
	public Square(int squareId, String squareName, Galaxy galaxy) throws IllegalArgumentException {
		this.setSquareId(squareId);
		this.setSquareName(squareName);
		this.galaxy = galaxy;
	}

	// Getters and Setters
	/**
	 * @return the squareId
	 */
	public int getSquareId() {
		return squareId;
	}

	/**
	 * @param squareId the squareId to set
	 */
	public void setSquareId(int squareId) throws IllegalArgumentException {
		if (squareId >= SQUAREID_MIN) {
			this.squareId = squareId;
		} else {
			throw new IllegalArgumentException("SquareId must be greater than 0");
		}
	}

	/**
	 * @return the squareName
	 */
	public String getSquareName() {
		return squareName;
	}

	/**
	 * @param squareName the squareName to set
	 */
	public void setSquareName(String squareName) throws IllegalArgumentException {
		if (squareName.length() >= SQUARE_NAME_LENGTH_MIN) {
			this.squareName = squareName;
		} else {
			throw new IllegalArgumentException("Please enter square name");
		}
	}

	/**
	 * @return the galaxy
	 */
	public Galaxy getGalaxy() {
		return galaxy;
	}

	/**
	 * @param galaxy the galaxy to set
	 */
	public void setGalaxy(Galaxy galaxy) {
		this.galaxy = galaxy;
	}

	// Methods
	@Override
	/**
	 * Prints square info
	 */
	public String toString() {
		return "Square [squareId=" + squareId + ", squareName=" + squareName + ", galaxy=" + galaxy + "]";
	}

}
