package save.our.planet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the game mechanics in resource manager
 */
class ResourceManagerTest {
    private ResourceManager resourceManager;
    private Player player1;
    private Player player2;
    private Planet testPlanet;

    @BeforeEach
    void setUp() {
        resourceManager = new ResourceManager();
        player1 = new Player("Alice", 2000, new ArrayList<>(), 1);
        player2 = new Player("Bob", 1500, new ArrayList<>(), 2);
        testPlanet = new Planet(1, "Mars", Galaxy.MILKYWAY, false, 0, 500, 0, 100, 200);
    }

    @Test
    /**
     * Add resource and check total
     */
    void testAddResources() {
        resourceManager.addResources(player1, 500);
        assertEquals(2500, player1.getResources(), "Player1 should have 2500 resources after addition.");
    }

    @Test
    /**
     * Remove resource and check total
     */
    void testRemoveResourcesSuccess() {
        assertTrue(resourceManager.removeResources(player1, 500), "Player1 should successfully pay 500 resources.");
        assertEquals(1500, player1.getResources(), "Player1 should have 1500 resources after deduction.");
    }

    @Test
    /**
     * Check if player can't afford
     */
    void testRemoveResourcesFailure() {
        assertFalse(resourceManager.removeResources(player2, 2000), "Player2 should not be able to pay 2000 resources.");
        assertEquals(1500, player2.getResources(), "Player2 should still have 1500 resources.");
    }

    @Test
    /**
     * Check for successful rent payment 
     */
    void testPayResourcesSuccess() {
        assertTrue(resourceManager.payResources(player1, player2, 300), "Payment should be successful.");
        assertEquals(1700, player1.getResources(), "Player1 should have 1700 resources after payment.");
        assertEquals(1800, player2.getResources(), "Player2 should have 1800 resources after receiving payment.");
    }

    @Test
    /**
     * Check if player can't afford rent
     */
    void testPayResourcesFailure() {
        assertFalse(resourceManager.payResources(player2, player1, 2000), "Payment should fail due to insufficient resources.");
        assertEquals(1500, player2.getResources(), "Player2 should still have 1500 resources.");
        assertEquals(2000, player1.getResources(), "Player1 should still have 2000 resources.");
    }

    @Test
    /**
     * Test successful buy of square
     */
    void testBuySquareSuccess() {
        List<Planet> ownedPlanets = new ArrayList<>();
        resourceManager.buySquare(player1, testPlanet, ownedPlanets);
        assertTrue(testPlanet.isPlanetOwned(), "Planet should be marked as owned.");
        assertEquals(1500, player1.getResources(), "Player1 should have 1500 resources after purchase.");
        assertEquals(1, ownedPlanets.size(), "Player1 should own 1 planet.");
    }

    @Test
    /**
     * Test if player can't afford square
     */
    void testBuySquareFailure() {
        testPlanet.setPlanetOwned(true);
        resourceManager.buySquare(player1, testPlanet, player1.getOwnedPlanets());
        assertEquals(2000, player1.getResources(), "Player1's resources should not change since the planet is already owned.");
    }

    @Test
    /**
     * Test for successful buying of development
     */
    void testDevelopPlanetSuccess() {
        player1.getOwnedPlanets().add(testPlanet);
        testPlanet.setPlanetOwned(true);
        testPlanet.setOwnerId(player1.getPlayerId());
        
        assertTrue(resourceManager.developPlanet(player1, testPlanet), "Development should succeed.");
        assertEquals(1, testPlanet.getDevelopmentLevel(), "Development level should increase to 1.");
        assertEquals(1850, player1.getResources(), "Player1 should have 1850 resources after development.");
    }

    @Test
    /**
     * Test player can't develop planet they don't own.
     */
    void testDevelopPlanetFailureNotOwner() {
        testPlanet.setPlanetOwned(true);
        testPlanet.setOwnerId(2);
        
        assertFalse(resourceManager.developPlanet(player1, testPlanet), "Development should fail because Player1 does not own the planet.");
    }
}
