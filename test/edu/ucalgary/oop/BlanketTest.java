
package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class BlanketTest {

    private Blanket blanket;

    @Before
    public void setup(){
        blanket = new Blanket(1, "6");
    }

    @Test
    public void testObjectCreation() {
        Blanket newBlanket = new Blanket();
        assertNotNull("Constructor should create a non-null Blanket object", blanket);
    }

    @Test
    public void testCotConstructor() {
        assertEquals("Constructor should set the type correctly", "Blanket", blanket.getType());
        assertEquals("Constructor should set the quantity correctly", 1, blanket.getQuantity());
        assertEquals("Constructor should set the supplyID correctly", "6", blanket.getSupplyID());
    }

    @Test
    public void testSetType(){
        String expectedType = "Blanket";
        blanket.setType(expectedType);
        assertEquals("setType() should set the type correctly", expectedType, blanket.getType());
    }

    @Test
    public void testSetQuantity(){
        int expectedQuantity = 1;
        blanket.setQuantity(expectedQuantity);
        assertEquals("setQuantity() should set the quantity correctly", expectedQuantity, blanket.getQuantity());
    }

    @Test 
    public void testSetSupplyID(){
        String expectedSupplyID = "6";
        blanket.setSupplyID(expectedSupplyID);
        assertEquals("setSupplyID() should set the supplyid correctly", expectedSupplyID, blanket.getSupplyID());
    }

    @Test
    public void testGetType(){
        String expectedType = "Blanket";
        assertEquals("getType() should return the type correctly", expectedType, blanket.getType());
    }

    @Test
    public void testGetQuantity(){
        Blanket newBlanket = new Blanket();
        int expectedQuantity = 1;
        newBlanket.setQuantity(expectedQuantity);
        assertEquals("getQuantity() should return the quantity correctly", expectedQuantity, newBlanket.getQuantity());
    }

    @Test
    public void testGetSupplyID(){
        Blanket newBlanket = new Blanket();
        String expectedSupplyID = "9";
        newBlanket.setSupplyID(expectedSupplyID);
        assertEquals("getSupplyID() should return the supplyid correctly", expectedSupplyID, newBlanket.getSupplyID());
    }

    @Test
    public void testSetAndGetPersonAllocatedTo(){
        Person expectedDisasterVictim = new Person("Alice", "Smith", "2001-02-17", 3, "gender_female", "is injured", "403-333-1111", "5");
        blanket.setPersonAllocatedTo(expectedDisasterVictim);
        assertEquals("setPersonAlloctaedTo() should set the disastervictim the supply is allocated to correctly and getPersonAllocatedTo should return this value", expectedDisasterVictim, blanket.getPersonAllocatedTo());
    }

    @Test
    public void testSetAndGetLocationAllocatedTo(){
        Location expectedLocation = new Location("Shelter A", "135 Ave");
        blanket.setLocationAllocatedTo(expectedLocation);
        assertEquals("setLocationAlloctaedTo() should set the location the supply is allocated to correctly and getLocationAllocatedTo should return this value", expectedLocation, blanket.getLocationAllocatedTo());
    }

  
}
