/**
 * 
 */
package save.our.planet;

import java.util.List;

/**
 * Class that represents a player in the game
 */
public class Player {

	// Instance Vars
	private String name;
	private int resources;
	private List<Planet> ownedPlanets;
	private int position = 0;// default to zero for start
	private int playerId; // to match to planet ownerID

	// Constructors
	/**
	 * Default Constructor
	 */
	public Player() {
		this.position = 0; // start square
	}

	/**
	 * Constructor with Args.
	 * 
	 * @param name
	 * @param resources
	 * @param ownedPlanets
	 */
	public Player(String name, int resources, List<Planet> ownedPlanets, int playerId) {
		this.setName(name);
		this.setResources(resources);
		this.setOwnedPlanets(ownedPlanets);
		this.setPlayerId(playerId);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the resources
	 */
	public int getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(int resources) {
		this.resources = resources;
	}

	/**
	 * @return the ownedSquares
	 */
	public List<Planet> getOwnedPlanets() {
		return ownedPlanets;
	}

	/**
	 * @param ownedSquares the ownedSquares to set
	 */
	public void setOwnedPlanets(List<Planet> ownedPlanets) {
		this.ownedPlanets = ownedPlanets;
	}

	/**
	 * To track position of player on board
	 * 
	 * @return
	 */
	public int getPosition() {

		return position;
	}

	/**
	 * To change position of player on board
	 * 
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	// Methods
	/**
	 * to roll the dice and return the result
	 * 
	 * @return the dice roll total
	 */
	public int rollDice() {
		int diceResult = Dice.roll();
		return diceResult;
	}

	/**
	 * Moves player around board based on number from dice roll
	 * 
	 * @param diceRoll
	 * @param boardSize
	 */
	public void move(int diceRoll, int boardSize) {
		this.position = (this.position + diceRoll) % boardSize;
	}

}
