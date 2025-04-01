package save.our.planet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the Player object class
 */
class PlayerTest {
    private Player player;
    List<Planet> ownedPlanets;

    /**
     * Before each
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        player = new Player();
        ownedPlanets = new ArrayList<>();
    }

    /**
     * Tests the default constructor and position.
     */
    @Test
    void testDefaultConstructor() {
        assertNotNull(player);
        assertEquals(0, player.getPosition());
    }

    /**
     * Tests the constructor with args.
     */
    @Test
    void testConstructorWithArgs() {
        
        player = new Player("John", 100, ownedPlanets, 1);
        
        assertEquals("John", player.getName());
        assertEquals(100, player.getResources());
        assertEquals(ownedPlanets, player.getOwnedPlanets());
        assertEquals(1, player.getPlayerId());
        assertEquals(0, player.getPosition());
    }

    /**
     * Tests the getter and setter for the name property.
     */
    @Test
    void testSetGetName() {
        player.setName("Alice");
        assertEquals("Alice", player.getName());
    }

    /**
     * Tests the getter and setter for the resources property.
     */
    @Test
    void testSetGetResources() {
        player.setResources(500);
        assertEquals(500, player.getResources());
    }

    /**
     * Tests the getter and setter for the ownedPlanets property.
     */
    @Test
    void testSetGetOwnedPlanets() {
        List<Planet> ownedPlanets = new ArrayList<>();
        player.setOwnedPlanets(ownedPlanets);
        assertEquals(ownedPlanets, player.getOwnedPlanets());
    }

    /**
     * Tests the getter and setter for the position property.
     */
    @Test
    void testSetGetPosition() {
        player.setPosition(10);
        assertEquals(10, player.getPosition());
    }

    /**
     * Tests the getter and setter for the playerId property.
     */
    @Test
    void testSetGetPlayerId() {
        player.setPlayerId(2);
        assertEquals(2, player.getPlayerId());
    }

    /**
     * Tests the move method to ensure the position is updated correctly.
     */
    @Test
    void testMove() {
        player.setPosition(5);
        player.move(3, 12);
        assertEquals(8, player.getPosition());

        player.setPosition(10);
        player.move(3, 12);
        assertEquals(1, player.getPosition()); // Passes Earth
    }
}
