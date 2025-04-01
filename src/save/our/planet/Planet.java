package save.our.planet;

/**
 * Class that represents a planet
 */
public class Planet extends Square {
	//Constants
	private static final int DEVELOPMENT_LEVEL_MIN = 0;
	private static final int DEVELOPMENT_LEVEL_MAX = 3;
	
	//Instance Vars
	private boolean planetOwned;
	private int ownerId;
	private int price;
	private int developmentLevel;
	private int rent;
	private int developmentCost;
	private String developmentStage; // New field for theme

	/**
	 * Default constructor
	 */
	public Planet() {
		super(1, "Unnamed Planet", Galaxy.FREE_SQUARE); // Minimal valid Square, Board overrides
		this.planetOwned = false;
		this.ownerId = -1;
		this.price = 0;
		this.developmentLevel = 0;
		this.rent = 0;
		this.developmentCost = 0;
		this.developmentStage = "Terraforming"; // Default on creation

	}

	/**
	 * Constructor with args
	 * @param squareId
	 * @param squareName
	 * @param galaxy
	 * @param planetOwned
	 * @param ownerId
	 * @param price
	 * @param developmentLevel
	 * @param rent
	 * @param developmentCost
	 * @throws IllegalArgumentException
	 */
	public Planet(int squareId, String squareName, Galaxy galaxy, boolean planetOwned, int ownerId, int price,
			int developmentLevel, int rent, int developmentCost) throws IllegalArgumentException {
		super(squareId, squareName, galaxy);
		this.planetOwned = planetOwned;
		this.ownerId = ownerId;
		this.price = price;
		this.developmentLevel = developmentLevel;
		this.rent = rent;
		this.developmentCost = developmentCost;
		this.developmentStage = "Terraforming"; // Set on construction
		updateCostsAndRent();
	}

	/**
	 * Method to update costs and rent on board as player buys/develops.
	 */
	private void updateCostsAndRent() {
		switch (developmentLevel) {
		case 0:
			rent = (int) (price * 0.15); // 20 (400), 25 (500), 35 (700)
			developmentCost = price <= 400 ? 100 : (price == 500 ? 150 : 200);
			developmentStage = "Terraforming";
			break;
		case 1:
			rent = (int) (price * 0.3); // 60, 75, 105
			developmentCost = price <= 400 ? 200 : (price == 500 ? 250 : 300);
			developmentStage = "Solar";
			break;
		case 2:
			rent = (int) (price * 0.5); // 120, 150, 210
			developmentCost = price <= 400 ? 300 : (price == 500 ? 350 : 400);
			developmentStage = "Wind";
			break;
		case 3:
			rent = (int) (price * 0.75); // 200, 250, 350
			developmentCost = 0;
			developmentStage = "Sustainable Ecosystem";
			break;
		}
	}
	
	/**
	 * Method to allow player to develop square
	 * @return
	 */
	public boolean develop() {
		if (developmentLevel < 3) {
			developmentLevel++;
			updateCostsAndRent();
			System.out.println("Success! " + getSquareName() + " now uses " + developmentStage + " (Level "
					+ developmentLevel + "). Rent is now " + rent);
			return true;
		} else {
			System.out.println(
					getSquareName() + " is already at the maximum development level: " + developmentStage + ".");
			return false;
		}
	}
	
	// Getters and setters
	/**
	 * 
	 * @return development stage 1-3.
	 */
    public String getDevelopmentStage() {
        return developmentStage;
    }

    /**
     * 
     * @return planet owned true/false
     */
	public boolean isPlanetOwned() {
		return planetOwned;
	}

	/**
	 * 
	 * @param planetOwned
	 */
	public void setPlanetOwned(boolean planetOwned) {
		this.planetOwned = planetOwned;
	}

	/**
	 * 
	 * @return owner id
	 */
	public int getOwnerId() {
		return ownerId;
	}

	/**
	 * 
	 * @param ownerId
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
		updateCostsAndRent();
	}

	/**
	 * 
	 * @return development level
	 */
	public int getDevelopmentLevel() {
		return developmentLevel;
	}

	/**
	 * Sets development level from 1-3
	 * @param developmentLevel
	 * @throws IllegalArgumentException
	 */
	public void setDevelopmentLevel(int developmentLevel) throws IllegalArgumentException {
		if (developmentLevel >= DEVELOPMENT_LEVEL_MIN && developmentLevel <= DEVELOPMENT_LEVEL_MAX) {
			this.developmentLevel = developmentLevel;
			updateCostsAndRent();
		} else {
			throw new IllegalArgumentException("Development level must be between 0 and 3.");
		}
	}

	/**
	 * 
	 * @return rent
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * 
	 * @param rent
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}

	/**
	 * 
	 * @return development cost
	 */
	public int getDevelopmentCost() {
		return developmentCost;
	}

	/**
	 * 
	 * @param developmentCost
	 */
	public void setDevelopmentCost(int developmentCost) {
		this.developmentCost = developmentCost;
	}

	@Override
	/**
	 * display planet info.
	 */
    public String toString() {
        return "Planet [planetOwned=" + planetOwned + ", ownerId=" + ownerId + ", price=" + price
                + ", developmentLevel=" + developmentLevel + ", rent=" + rent + ", developmentCost=" + developmentCost
                + ", stage=" + developmentStage + ", toString()=" + super.toString() + "]";
    }
}