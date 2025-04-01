package save.our.planet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Planet object class
 */
class PlanetTest {
    private Planet planet;

    /**
     * Before each
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        planet = new Planet();
    }

    /**
     * Tests the default constructor.
     */
    @Test
    void testDefaultConstructor() {
        assertNotNull(planet);
    }

    /**
     * Tests the constructor with args.
     */
    @Test
    void testConstructorWithArgs() {
        planet = new Planet(2, "Mars", Galaxy.FREE_SQUARE, true, 4, 500, 0, 75, 150);
        assertEquals(2, planet.getSquareId());
        assertEquals("Mars", planet.getSquareName());
        assertEquals(Galaxy.FREE_SQUARE, planet.getGalaxy());
        assertTrue(planet.isPlanetOwned());
        assertEquals(4, planet.getOwnerId());
        assertEquals(500, planet.getPrice());
        assertEquals(0, planet.getDevelopmentLevel());
        assertEquals(75, planet.getRent());
        assertEquals(150, planet.getDevelopmentCost());
        assertEquals("Terraforming", planet.getDevelopmentStage());
        
    }

    /**
     * Tests the getter and setter for the planetOwned property.
     */
    @Test
    void testSetGetPlanetOwned() {
        planet.setPlanetOwned(true);
        assertTrue(planet.isPlanetOwned());
    }

    /**
     * Tests the getter and setter for the ownerId property.
     */
    @Test
    void testSetGetOwnerId() {
        planet.setOwnerId(42);
        assertEquals(42, planet.getOwnerId());
    }

    /**
     * Tests the getter and setter for the price property.
     */
    @Test
    void testSetGetPrice() {
        planet.setPrice(500);
        assertEquals(500, planet.getPrice());
    }

    /**
     * Tests the getter and setter for the developmentLevel property.
     */
    @Test
    void testSetGetDevelopmentLevel() {
        planet.setDevelopmentLevel(2);
        assertEquals(2, planet.getDevelopmentLevel());
    }

    /**
     * Tests the getter and setter for the rent property.
     */
    @Test
    void testSetGetRent() {
        planet.setRent(150);
        assertEquals(150, planet.getRent());
    }

    /**
     * Tests the getter and setter for the developmentCost property.
     */
    @Test
    void testSetGetDevelopmentCost() {
        planet.setDevelopmentCost(300);
        assertEquals(300, planet.getDevelopmentCost());
    }

    /**
     * Tests the getter for the developmentStage property.
     */
    @Test
    void testGetDevelopmentStage() {
        assertEquals("Terraforming", planet.getDevelopmentStage());
    }
    
    /**
     * Tests the updateCostsAndRent method.
     */
    @Test
    void testUpdateCostsAndRent() {
    	planet.setDevelopmentLevel(1);
    	assertEquals("Solar", planet.getDevelopmentStage());
    }

}
