package save.our.planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to handle player turns - buying, renting and developing planets.
 */
public class TurnManager {
    private Square[] squares;
    private ResourceManager resourceManager;
    private List<Player> allPlayers;

    /**
     * Constructor
     * @param squares
     * @param resourceManager
     * @param allPlayers
     */
    public TurnManager(Square[] squares, ResourceManager resourceManager, List<Player> allPlayers) {
        this.squares = squares;
        this.resourceManager = resourceManager;
        this.allPlayers = allPlayers;
    }

    /**
     * Moves player round board. Core game logic; buy, develop, rent.
     * @param player
     * @param scanner
     * @return
     */
    public boolean handlePlayerTurn(Player player, Scanner scanner) {
        setPlayerTextColour(player);

        System.out.print("\n" + player.getName() + "'s turn (Resources: " + player.getResources() + ", Position: "
                + squares[player.getPosition()].getSquareName() + "). Press Enter to roll, or 'quit': ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("quit")) {
            return false;
        }

        int roll = player.rollDice();
        int oldPosition = player.getPosition();
        player.move(roll, squares.length);
        Square currentSquare = squares[player.getPosition()];

        System.out.println(player.getName() + " rolled a " + roll + " and landed on " + currentSquare.getSquareName()
                + " (ID: " + currentSquare.getSquareId() + ")");

        handleEarthPassOrLand(player, oldPosition, roll);

        if (currentSquare instanceof Planet) {
            handlePlanetLanding(player, (Planet) currentSquare, scanner);
        }

        System.out.print(Game.RESET); // Reset console colour to default
        return true;
    }

    /**
     * Grants 100 resources on passing earth
     * @param player
     * @param oldPosition
     * @param roll
     */
    private void handleEarthPassOrLand(Player player, int oldPosition, int roll) {
        if (oldPosition + roll >= squares.length || player.getPosition() == 0) {
            resourceManager.addResources(player, 100);
            System.out.println(player.getName() + " passed or landed on Earth, gained 100 resources. New total: "
                    + player.getResources());
        }
    }

    /**
     * Handles 3 cases of landing on a planet.
     * @param player
     * @param planet
     * @param scanner
     */
    private void handlePlanetLanding(Player player, Planet planet, Scanner scanner) {
        List<Planet> ownedPlanets = getAllOwnedPlanets();

        // Case 1: Unowned planet - buy or auction
        if (!ownedPlanets.contains(planet) && player.getResources() >= planet.getPrice()) {
            handleUnownedPlanet(player, planet, scanner);
        }
        // Case 2: Player owns it - develop only if galaxy owned
        else if (player.getPlayerId() == planet.getOwnerId()) {
            handleOwnedPlanet(player, planet, scanner);
        }
        // Case 3: Another player owns it - pay rent
        else if (planet.isPlanetOwned() && player.getPlayerId() != planet.getOwnerId()) {
            handleRentPayment(player, planet);
        }
    }

    /**
     * Landing on un-owned planet - buy or give to auction.
     * @param player
     * @param planet
     * @param scanner
     */
    private void handleUnownedPlanet(Player player, Planet planet, Scanner scanner) {
        System.out.print("Do you want to buy " + planet.getSquareName() + " for " + planet.getPrice() + "? (yes/no): ");
        String buyChoice = scanner.nextLine();

        if (buyChoice.equalsIgnoreCase("yes")) {
            resourceManager.buySquare(player, planet, player.getOwnedPlanets());
            System.out.println(player.getName() + " bought " + planet.getSquareName() + " for "
                    + planet.getPrice() + ". Resources left: " + player.getResources());

            handleDevelopment(player, planet, scanner);
        } else {
            System.out.println(player.getName() + " chose not to buy " + planet.getSquareName());
            boolean boughtInAuction = auctionPlanet(player, planet, scanner);
            if (!boughtInAuction) {
                System.out.println("All players chose not to buy " + planet.getSquareName());
            }
        }
    }

    /**
     * Owned planet logic - Development.
     * @param player
     * @param planet
     * @param scanner
     */
    private void handleOwnedPlanet(Player player, Planet planet, Scanner scanner) {
        System.out.println("You own this planet already.");
        if (planet.getDevelopmentLevel() < 3 && ownsAllPlanetsInGalaxy(player, planet)) {
            System.out.print("You own all planets in " + planet.getGalaxy()
                    + ". Do you want to develop this planet? (yes/no): ");
            String developChoice = scanner.nextLine();
            if (developChoice.equalsIgnoreCase("yes")) {
                if (resourceManager.developPlanet(player, planet)) {
                    // Success in Planet.develop()
                } else {
                    System.out.println("Not enough resources to develop the planet.");
                }
            }
        } else if (planet.getDevelopmentLevel() < 3) {
            System.out.println("You can’t develop " + planet.getSquareName()
                    + " yet—you don’t own all planets in " + planet.getGalaxy() + ".");
        } else {
            System.out.println("This planet is already at maximum development level.");
        }
    }

    /**
     * Rent payment logic.
     * @param player
     * @param planet
     */
    private void handleRentPayment(Player player, Planet planet) {
        for (Player owner : allPlayers) {
            if (owner.getPlayerId() == planet.getOwnerId()) {
                System.out.println(owner.getName() + " owns this planet. You owe them " + planet.getRent()
                        + ". Your resources: " + player.getResources());
                if (player.getResources() >= planet.getRent()) {
                    resourceManager.payResources(player, owner, planet.getRent());
                    System.out.println("You have paid " + owner.getName() + " the sum of " + planet.getRent()
                            + ". Resources left: " + player.getResources());
                } else {
                    System.out.println("You do not have enough resources to pay " + owner.getName() + " "
                            + planet.getRent() + ". You are out.");
                }
                break;
            }
        }
    }

    /**
     * Auction of planet if player refuses to buy.
     * @param player
     * @param planet
     * @param scanner
     * @return
     */
    private boolean auctionPlanet(Player player, Planet planet, Scanner scanner) {
        boolean boughtInAuction = false;
        for (Player player1 : allPlayers) {
            if (player1.getResources() >= planet.getPrice() && player1 != player) {
                System.out.println(player1.getName() + ", you have: " + player1.getResources()
                        + ". Do you want to buy " + planet.getSquareName() + "? (yes/no)");
                String buyChoice = scanner.nextLine();
                if (buyChoice.equalsIgnoreCase("yes")) {
                    resourceManager.buySquare(player1, planet, player1.getOwnedPlanets());
                    System.out.println(player1.getName() + " bought " + planet.getSquareName() + " for "
                            + planet.getPrice() + ". Resources left: " + player1.getResources());
                    boughtInAuction = true;
                    break;
                }
            }
        }
        return boughtInAuction;
    }

    /**
     * Develop planet if they own all planets in galaxy.
     * @param player
     * @param planet
     * @param scanner
     */
    private void handleDevelopment(Player player, Planet planet, Scanner scanner) {
        if (planet.getDevelopmentLevel() < 3 && ownsAllPlanetsInGalaxy(player, planet)) {
            System.out.print("You own all planets in " + planet.getGalaxy()
                    + ". Do you want to develop this planet? (yes/no): ");
            String developChoice = scanner.nextLine();
            if (developChoice.equalsIgnoreCase("yes")) {
                if (resourceManager.developPlanet(player, planet)) {
                    // Success in Planet.develop()
                } else {
                    System.out.println("Not enough resources to develop the planet.");
                }
            }
        } else if (planet.getDevelopmentLevel() < 3) {
            System.out.println("You can’t develop " + planet.getSquareName()
                    + " yet—you don’t own all planets in " + planet.getGalaxy() + ".");
        }
    }

    /**
     * Different text colours per player.
     * @param player
     */
    private void setPlayerTextColour(Player player) {
        switch (player.getPlayerId()) {
            case 1:
                System.out.print(Game.BLUE);
                break;
            case 2:
                System.out.print(Game.BROWN);
                break;
            case 3:
                System.out.print(Game.MAGENTA);
                break;
            case 4:
                System.out.print(Game.RED);
                break;
            default:
                System.out.print(Game.RESET);
        }
    }

    /**
     * List to hold all planets owned in game.
     * @return
     */
    private List<Planet> getAllOwnedPlanets() {
        List<Planet> allOwned = new ArrayList<>();
        for (Player player : allPlayers) {
            allOwned.addAll(player.getOwnedPlanets());
        }
        return allOwned;
    }

    /**
     * Checks if player owns all planets in a galaxy for development.
     * @param player
     * @param planet
     * @return
     */
    private boolean ownsAllPlanetsInGalaxy(Player player, Planet planet) {
        Galaxy targetGalaxy = planet.getGalaxy();
        int totalInGalaxy = 0;
        int ownedInGalaxy = 0;

        for (Square square : squares) {
            if (square instanceof Planet) {
                Planet p = (Planet) square;
                if (p.getGalaxy() == targetGalaxy) {
                    totalInGalaxy++;
                    if (p.isPlanetOwned() && p.getOwnerId() == player.getPlayerId()) {
                        ownedInGalaxy++;
                    }
                }
            }
        }
        return totalInGalaxy == ownedInGalaxy && totalInGalaxy > 0;
    }
}
