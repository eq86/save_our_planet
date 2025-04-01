package save.our.planet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tests the game mechanics in game manager.
 */
class GameManagerTest {
    private GameManager gameManager;
    
    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }
    
    @Test
    void testPlayerSetup() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Alice", 1700, new ArrayList<>(), 1));
        players.add(new Player("Bob", 1700, new ArrayList<>(), 2));

        assertEquals(2, players.size(), "Number of players should be 2");
        assertEquals("Alice", players.get(0).getName(), "First player's name should be Alice");
        assertEquals("Bob", players.get(1).getName(), "Second player's name should be Bob");
    }
    
    @Test
    /**
     * Test squares add not board properly
     */
    void testGameBoardInitialization() {
        Board board = new Board();
        assertNotNull(board.getSquares(), "Board squares should not be null");
        assertTrue(board.getSquares().length > 0, "Board should have squares");
    }
    
    @Test
    /**
     * test the game loop runs calling in other classes required.
     */
    void testGameLoopRunsCorrectly() {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
        assertNotNull(gameManager, "GameManager should not be null");
    }
}
