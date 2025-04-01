/**
 * 
 */
package save.our.planet;

import java.util.List;

/**
 * 
 */
public class ResourceManager {

	/**
	 * to add resources (e.g. landing at Earth)
	 * 
	 * @param payee  the player to receive resources
	 * @param amount the amount of resources to add
	 */
	public void addResources(Player payee, int amount) {
		payee.setResources(payee.getResources() + amount);
	}

	/**
	 * Removes resources from a player (e.g., buying/developing a square). Ensures
	 * resources do not go negative.
	 *
	 * @param payer  the player paying resources
	 * @param amount the amount of resources to remove
	 * @return true if the transaction was successful, false otherwise
	 */
	public boolean removeResources(Player payer, int amount) {
		if (payer.getResources() >= amount) {
			payer.setResources(payer.getResources() - amount);
			System.out.println(payer.getName() + " now has " + payer.getResources() + " resources after deduction.");
			return true;
		} else {
			System.out.println(
					"Not enough resources! " + payer.getName() + " has only " + payer.getResources() + " resources.");
			return false;
		}
	}

	/**
	 * to transfer resources (e.g. landing on an owned square)
	 * 
	 * @param payer  the player to pay resources
	 * @param payee  the player to receive the resources
	 * @param amount the amount to transfer
	 */
	public boolean payResources(Player payer, Player payee, int amount) {
		if (payer.getResources() >= amount) {
			payer.setResources(payer.getResources() - amount);
			payee.setResources(payee.getResources() + amount);
			return true;
		} else {
			System.out.println("Not enough resources to pay!");
			return false;
		}
	}

	/**
	 * 
	 * @param player
	 * @param planet
	 * @param planets
	 */
	public void buySquare(Player player, Planet planet, List<Planet> planets) {
		if (planet.isPlanetOwned()) {
			System.out.println("Planet is already owned!");
			return;
		}

		if (removeResources(player, planet.getPrice())) {
			System.out.println("You have enough resources to buy the planet.");

			planet.setPlanetOwned(true); // Make sure planet is marked as owned
			planet.setOwnerId(player.getPlayerId()); // Assign the owner properly
			planets.add(planet);
			player.setOwnedPlanets(planets);

			System.out.println(player.getName() + " now owns " + planet.getSquareName()
					+ ". Terraforming begins! (Owner ID: " + planet.getOwnerId() + ")");
		} else {
			System.out.println("Not enough resources to buy " + planet.getSquareName());
		}
	}

	/**
	 * Allows a player to develop a planet, if they own the galaxy, by increasing
	 * its development level. Deducts the correct development cost from the player
	 * and increases the planet's rent.
	 *
	 * @param player The player attempting to develop the planet.
	 * @param planet The planet being developed.
	 * @return True if development was successful, false otherwise.
	 */
	public boolean developPlanet(Player player, Planet planet) {
		if (!planet.isPlanetOwned() || planet.getOwnerId() != player.getPlayerId()) {
			System.out.println("You do not own this planet!");
			return false;
		}
		// check: own all planets in galaxy
		int totalInGalaxy = 0;
		int ownedInGalaxy = 0;
		for (Planet p : player.getOwnedPlanets()) {
			if (p.getGalaxy() == planet.getGalaxy()) {
				totalInGalaxy++;
				if (p.isPlanetOwned() && p.getOwnerId() == player.getPlayerId()) {
					ownedInGalaxy++;
				}
			}
		}
		
		//count planets owned in galaxy
		if (totalInGalaxy != ownedInGalaxy || totalInGalaxy == 0) {
			System.out.println("You can’t develop " + planet.getSquareName() + "—you don’t own all planets in "
					+ planet.getGalaxy() + ".");
			return false;
		}

		if (planet.getDevelopmentLevel() >= 3) {
			System.out.println(planet.getSquareName() + " is already at the maximum development level.");
			return false;
		}

		int cost = planet.getDevelopmentCost();
		System.out.println("Development cost: " + cost + " resources. You have: " + player.getResources());

		if (!removeResources(player, cost)) {
			System.out.println("Not enough resources to develop " + planet.getSquareName() + ".");
			return false;
		}

		return planet.develop();
	}
}