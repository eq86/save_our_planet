/**
 * 
 */
package save.our.planet;

/**
 * This class holds the game board. It auto populates when instantiated, holding
 * each Square object in an array called "squares".
 */
public class Board {

	private static final int NUMBER_OF_SQUARES = 12;

	private Square[] squares = new Square[NUMBER_OF_SQUARES];

	/**
	 * Populate board
	 */
	public Board() {

		// Earth "Go" square
		squares[0] = new Earth();
		squares[0].setSquareId(1);
		squares[0].setSquareName("Earth");
		squares[0].setGalaxy(Galaxy.FREE_SQUARE);

		// First block (A in diagram)
		squares[1] = new Planet();
		squares[1].setSquareId(2);
		squares[1].setSquareName("Mars");
		squares[1].setGalaxy(Galaxy.MILKYWAY);

		squares[2] = new Planet();
		squares[2].setSquareId(3);
		squares[2].setSquareName("Jupiter");
		squares[2].setGalaxy(Galaxy.MILKYWAY);

		squares[3] = new Planet();
		squares[3].setSquareId(4);
		squares[3].setSquareName("Saturn");
		squares[3].setGalaxy(Galaxy.MILKYWAY);

		// Second block (B in diagram)
		squares[4] = new Planet();
		squares[4].setSquareId(5);
		squares[4].setSquareName("Alpheratz");
		squares[4].setGalaxy(Galaxy.ANDROMEDA);

		squares[5] = new Planet();
		squares[5].setSquareName("Mirach");
		squares[5].setSquareId(6);
		squares[5].setGalaxy(Galaxy.ANDROMEDA);

		// Space Diner (Free Parking)
		squares[6] = new SpaceDiner();
		squares[6].setSquareId(7);
		squares[6].setSquareName("Space Diner");
		squares[6].setGalaxy(Galaxy.FREE_SQUARE);

		// First block (C in diagram)
		squares[7] = new Planet();
		squares[7].setSquareId(8);
		squares[7].setSquareName("Supermassive Black Hole");
		squares[7].setGalaxy(Galaxy.MESSIER87);

		squares[8] = new Planet();
		squares[8].setSquareId(9);
		squares[8].setSquareName("Globular Cluster");
		squares[8].setGalaxy(Galaxy.MESSIER87);

		squares[9] = new Planet();
		squares[9].setSquareId(10);
		squares[9].setSquareName("Plasma Jet");
		squares[9].setGalaxy(Galaxy.MESSIER87);

		// Second block (D in diagram)
		squares[10] = new Planet();
		squares[10].setSquareId(11);
		squares[10].setSquareName("H II Region");
		squares[10].setGalaxy(Galaxy.TRIANGULUM);

		squares[11] = new Planet();
		squares[11].setSquareId(12);
		squares[11].setSquareName("Supernova Remnant");
		squares[11].setGalaxy(Galaxy.TRIANGULUM);

		/*
		 * to add prices for buying planets
		 */
		for (Square square : squares) {
			if (square instanceof Planet) {
				Planet planet = (Planet) square;
				if (planet.getSquareId() == 5 || planet.getSquareId() == 6) {
					planet.setPrice(400);
				} else if (planet.getSquareId() == 11 || planet.getSquareId() == 12) {
					planet.setPrice(700);
				} else {
					planet.setPrice(500);
				}
			}
		}
	}

	/**
	 * 
	 * @return squares
	 */
	public Square[] getSquares() {
		return squares;
	}

}
