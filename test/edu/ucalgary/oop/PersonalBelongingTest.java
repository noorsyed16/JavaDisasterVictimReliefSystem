
package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class PersonalBelongingTest {
    private PersonalBelonging item;
    @Before
    public void setUp(){
        item = new PersonalBelonging( 1, "Green backpack", "7");
    }

    @Test
    public void testObjectCreation() {
        PersonalBelonging item = new PersonalBelonging();
        assertNotNull("Constructor should create a non-null Personal Belonging object", item);
    }

    @Test
    public void testPersonalBelongingConstructor() {
        assertEquals("Constructor should set the type correctly", "personal item", item.getType());
        assertEquals("Constructor should set the quantity correctly", 1, item.getQuantity());
        assertEquals("Constructor should set the description correctly", "Green backpack", item.getDescription());
        assertEquals("Constructor should set the supplyID correctly", "7", item.getSupplyID());
    }

    @Test
    public void testSetType(){
        String expectedType = "personalBelonging";
        item.setType(expectedType);
        assertEquals("setType() should set the type correctly", expectedType, item.getType());
    }


    @Test
    public void testSetQuantity(){
        int expectedQuantity = 1;
        item.setQuantity(expectedQuantity);
        assertEquals("setQuantity() should set the quantity correctly", expectedQuantity, item.getQuantity());
    }

    @Test 
    public void testSetSupplyID(){

        String expectedSupplyID = "9";
        item.setSupplyID(expectedSupplyID);
        assertEquals("setSupplyID() should set the supplyid correctly", expectedSupplyID, item.getSupplyID());
    }

    @Test
    public void testGetType(){
        String expectedType = "personalBelonging";
        item.setType(expectedType);
        assertEquals("getType() should return the type correctly", expectedType, item.getType());
    }

    @Test
    public void testGetQuantity(){
        int expectedQuantity = 1;
        item.setQuantity(expectedQuantity);
        assertEquals("getQuantity() should return the quantity correctly", expectedQuantity, item.getQuantity());
    }

    @Test
    public void testGetSupplyID(){
        String expectedSupplyID = "9";
        item.setSupplyID(expectedSupplyID);
        assertEquals("getSupplyID() should return the supplyid correctly", expectedSupplyID, item.getSupplyID());
    }

    @Test
    public void testSetAndGetPersonAlloatedTo(){
        Person expectedDisasterVictim = new Person("Alice", "Smith", "2001-02-17", 3, "gender_female","is injured", "403-333-1111", "5");
        item.setPersonAllocatedTo(expectedDisasterVictim);
        assertEquals("setPersonAllocatedTo() should set the disastervictim the supply is allocated to correctly and getPersonAllocatedTo should return this value", expectedDisasterVictim, item.getPersonAllocatedTo());
    }

    @Test
    public void testSetAndGetLocationAlloatedTo(){
        Location expectedLocation = new Location("Shelter A", "135 Ave");
        item.setLocationAllocatedTo(expectedLocation);
        assertEquals("setLocationAlloctaedTo() should set the location the supply is allocated to correctly and getLocationAllocatedTo should return this value", expectedLocation, item.getLocationAllocatedTo());
    }

    @Test 
    public void testSetDescription(){
        String expectedDescription = "Blue backpack";
        item.setDescription(expectedDescription);
        assertEquals("setDescription() should set the description correctly", expectedDescription, item.getDescription());
    }

    @Test
    public void testGetDescription(){
        String expectedDescription = "Green Backpack";
        item.setDescription(expectedDescription);
        assertEquals("getDescription() should return the descripion correctly", expectedDescription, item.getDescription());
    }

}
