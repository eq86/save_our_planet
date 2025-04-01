package save.our.planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to set up players, display the board, play game and display final scores.
 */
public class GameManager {

	private Board gameBoard;
	private ResourceManager resourceManager;
	private List<Player> players;
	private TurnManager turnManager;
	private Scanner scanner;

	/**
	 * Constructor
	 */
	public GameManager() {
		this.gameBoard = new Board();
		this.resourceManager = new ResourceManager();
		this.players = new ArrayList<>();
		this.turnManager = new TurnManager(gameBoard.getSquares(), resourceManager, players);
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Starts game
	 */
	public void startGame() {
		setupPlayers();
		displayBoard();
		playGame();
		displayFinalStats();
		scanner.close();
	}

	/**
	 * Player registration for 2-4 players
	 */
	private void setupPlayers() {
		int numPlayers = 2; // initialised to 2 but can change based on player choice
		String userChoice;
		boolean playerMenuComplete = false;
		do {
			System.out.print("Enter the number of players (2-4): ");
			userChoice = scanner.nextLine();
			switch (userChoice) {
			case "2":
				numPlayers = 2;
				playerMenuComplete = true;
				break;
			case "3":
				numPlayers = 3;
				playerMenuComplete = true;
				break;
			case "4":
				numPlayers = 4;
				playerMenuComplete = true;
				break;
			default:
				System.err.println("Invalid number of players. Please enter a number between 2 and 4.");
			}
		} while (!playerMenuComplete);
		int playerId = 0;
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Enter name for Player " + (i + 1) + ": ");
			String playerName = scanner.nextLine();
			if (playerName.equals("")) {
				playerName = "Player " + (i + 1);
			}
			playerId++;
			Player newPlayer = new Player(playerName, 1000, new ArrayList<Planet>(), playerId);
			players.add(newPlayer);
		}
	}

	/**
	 * Prints out the board to screen
	 */
	private void displayBoard() {
		System.out.println("\nGame board squares:");
		System.out.println();
		Square[] squares = gameBoard.getSquares();
		for (Square square : squares) {
			System.out.println("Name: " + square.getSquareName());
			System.out.println("ID & Galaxy: " + square.getSquareId() + ", " + square.getGalaxy());
			System.out.println();
		}
	}

	/**
	 * Starts game and runs until payer quits.
	 */
	private void playGame() {
		System.out.println("\nStarting the game! Press Enter to roll the dice, or type 'quit' to end the game.");
		int turn = 1;
		boolean gameRunning = true;

		while (gameRunning) {
			System.out.println("\nTurn " + turn + ":");
			for (Player player : players) {
				gameRunning = turnManager.handlePlayerTurn(player, scanner);
				if (!gameRunning) {
					break; // Exit if player quits
				}
			}
			turn++;
		}
	}

	/**
	 * Displays stats to show winner
	 */
	private void displayFinalStats() {
		System.out.print(Game.RESET); // reset text colour
		System.out.println("\nGame Over! Final Player Stats:");
		for (Player player : players) {
			System.out.println(player.getName() + " - Resources: " + player.getResources() + ", Owned Planets: "
					+ player.getOwnedPlanets().size());
		}
	}
}