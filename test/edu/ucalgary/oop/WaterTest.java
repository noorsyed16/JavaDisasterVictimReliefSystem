
package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class WaterTest {
    private Water water;
    
    @Before
    public void setUp(){
        water = new Water(1, "2025-01-12", "6");
    }

    @Test
    public void testObjectCreation() {
        Water water = new Water();
        assertNotNull("Constructor should create a non-null Water object", water);
    }

    @Test
    public void testWaterConstructor() {
        assertEquals("Constructor should set the type correctly", "water", water.getType());
        assertEquals("Constructor should set the quantity correctly", 1, water.getQuantity());
        assertEquals("Constructor should set the dateAllocated correctly", "2025-01-12", water.getDateAllocated());
        assertEquals("Constructor should set the supplyID correctly", "6", water.getSupplyID());
    }

    @Test 
    public void testSetType(){
        String expectedType = "Water";
        water.setType(expectedType);
        assertEquals("setType() should set the type correctly", expectedType, water.getType());
    }

    @Test
    public void testSetQuantity(){
        int expectedQuantity = 1;
        water.setQuantity(expectedQuantity);
        assertEquals("setQuantity() should set the quantity correctly", expectedQuantity, water.getQuantity());
    }

    @Test 
    public void testSetSupplyID(){
        String expectedSupplyID = "9";
        water.setSupplyID(expectedSupplyID);
        assertEquals("setSupplyID() should set the supplyid correctly", expectedSupplyID, water.getSupplyID());
    }

    @Test
    public void testGetType(){
        String expectedType = "water";
        water.setType(expectedType);
        assertEquals("getType() should return the type correctly", expectedType, water.getType());
    }

    @Test
    public void testGetQuantity(){
        int expectedQuantity = 1;
        water.setQuantity(expectedQuantity);
        assertEquals("getQuantity() should return the quantity correctly", expectedQuantity, water.getQuantity());
    }

    @Test
    public void testGetSupplyID(){
        String expectedSupplyID = "9";
        water.setSupplyID(expectedSupplyID);
        assertEquals("getSupplyID() should return the supplyid correctly", expectedSupplyID, water.getSupplyID());
    }

    @Test
    public void testSetAndGetPersonAlloatedTo(){
        Person expectedDisasterVictim = new Person("Alice", "Smith", "2001-02-17", 3, "gender_female", "is injured", "403-333-1111", "5");
        water.setPersonAllocatedTo(expectedDisasterVictim);
        assertEquals("setPersonAlloctaedTo() should set the disastervictim the supply is allocated to correctly and getPersonAllocatedTo should return this value", expectedDisasterVictim, water.getPersonAllocatedTo());
    }

    @Test
    public void testSetAndGetLocationAlloatedTo(){
        Location expectedLocation = new Location("Shelter A", "135 Ave");
        water.setLocationAllocatedTo(expectedLocation);
        assertEquals("setLocationAlloctaedTo() should set the location the supply is allocated to correctly and getLocationAllocatedTo should return this value", expectedLocation, water.getLocationAllocatedTo());
    }

    @Test 
    public void testsetDateAllocated(){
        String expectedDate = "2025-03-12";
        water.setDateAllocated(expectedDate);
        assertEquals("setDateAllocated() should set the date allocated correctly", expectedDate, water.getDateAllocated());
    }

    @Test
    public void testSetDateAllocatedWithInvalidFormat() {
        String invalidDate = "2025.01/12";
         // Will return false from the try catch block which catches the exception thrown since is invalid date
        assertFalse(water.setDateAllocated(invalidDate)); 
    }

    @Test
    public void testGetDateAllocated(){
        String expectedDateAllocated = "2022-01-09";
        water.setDateAllocated(expectedDateAllocated);
        assertEquals("getDateAllocated() should return the date allocated correctly", expectedDateAllocated, water.getDateAllocated());
    }

    @Test
    //is Valid Date
    public void testIsValidDateWithValidInput(){
        String validDate = "2020-03-19";
        assertEquals("isValidDate should return true when given a valid date", true, water.isValidDate(validDate));

    }

    @Test
    //is invalid Date
    public void testIsValidDateWithInValidInput(){
        String inValidDate = "2020_03/19";
        assertEquals("isValidDate should return false when given an invalid date", false, water.isValidDate(inValidDate));
    }

    @Test
    //is expired
    public void testIsExpiredWhenExpired() {
        water.setDateAllocated("2020-02-19");
        assertTrue("isExpired should return true when water is over a day old", water.isExpired());
    }

    @Test
    //is not expired
    public void testIsExpiredWhenNotExpired() {
        water.setDateAllocated(LocalDate.now().toString()); // Today's date
        assertFalse("isExpired should return false when water is not expired", water.isExpired());
    }

}
