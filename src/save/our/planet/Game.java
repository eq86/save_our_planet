package save.our.planet;

/**
 * Starting point for application. Shows introduction graphics and description,
 * and begins the Game Manager for setting up a game
 */
public class Game {

	// colour codes for console
	public static final String RESET = "\u001B[0m"; // Reset colour
	public static final String WHITE = "\u001B[37m";
	public static final String GREEN = "\u001B[32m";
	public static final String MAGENTA = "\u001B[35m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE =  "\u001B[34m";
	public static final String AQUA = "\u001B[36m";
	public static final String BROWN = "\u001B[38;5;130m";
	public static final String BLACK_BG = "\u001B[40m"; // Black background
	public static final String DEFAULT_BG = "\u001B[49m"; // Default background
	public static final String ROCKET = "\uD83D\uDE80"; // Rocket emoji

	/**
	 * Application entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			showTitle();
			// begin the game
			GameManager gameManager = new GameManager();
			gameManager.startGame();
		} catch (InterruptedException e) {
			System.err.println("Unexpected interruption in the thread, quitting game.");
		} catch (Exception e) {
			System.err.println("Unexpected error, quitting game.");
		}
	}

	/**
	 * Displays title graphics and text to screen
	 * 
	 * @throws InterruptedException
	 */
	private static void showTitle() throws InterruptedException {
		System.out.print(BLACK_BG);
		System.out.println(WHITE + "                   .        .   .       *       .      .    .       *");
		System.out.println(GREEN + "        .      *        .     .   *      .   .   .         *    .    ");
		System.out.println(MAGENTA + "   *       .      .    *    .      .  .       *       .   .        * ");
		System.out.println(AQUA + "      .   *     .      .      *      .  *        *      .     *      ");
		System.out.println(MAGENTA + "  *        .       *      *      .    .     .       .   *           .");
		Thread.sleep(600);
		System.out.println(GREEN + "                      * SAVE *                                       ");
		Thread.sleep(1000);
		System.out.println("                             . OUR .                                 ");
		Thread.sleep(1000);
		System.out.println("                                   * PLANET *                        ");
		Thread.sleep(600);
		System.out.println(WHITE + "        *    .        .      *       .       *       .    .  *       ");
		System.out.println(GREEN + "     .        *    .    .   *      .      .      .        *          ");
		System.out.println(MAGENTA + "      .  *       .     .  *       *       .       *       .          ");
		System.out.println(AQUA + "         .     .    *       .     .      .    .                    * ");
		System.out.println("                                                                     ");
		Thread.sleep(1000);
		System.out.println("               Earth is at risk of resource depletion!               \n"
				+ " Save our planet by exloring space and terraforming cosmic entities. \n"
				+ "                     For the benefit of humanity!                    ");
		System.out.println(RESET + DEFAULT_BG + "                              " + ROCKET + ROCKET + ROCKET + ROCKET);
		System.out.println();
		Thread.sleep(1000);
	}

}