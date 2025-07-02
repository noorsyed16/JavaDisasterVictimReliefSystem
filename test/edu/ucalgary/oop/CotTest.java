package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class CotTest {
    private Cot cot;

    @Before
    public void setUp() {
        cot = new Cot(1, "223", "B8", "6"); 
    }
    @Test
    public void testObjectCreation() {
        Cot newCot = new Cot();
        assertNotNull("Constructor should create a non-null Cot object", newCot);
    }

    @Test
    public void testCotConstructor() {
        assertEquals("Constructor should set the type correctly", "Cot", cot.getType());
        assertEquals("Constructor should set the quantity correctly", 1, cot.getQuantity());
        assertEquals("Constructor should set the room number correctly", "223", cot.getRoomNumber());
        assertEquals("Constructor should set the supplyID correctly", "6", cot.getSupplyID());
    }

    @Test
    public void testSetType(){
        String expectedType = "Cot";
        cot.setType(expectedType);
        assertEquals("setType() should set the type correctly", expectedType, cot.getType());
    }

    @Test
    public void testSetQuantity(){
        int expectedQuantity = 1;
        cot.setQuantity(expectedQuantity);
        assertEquals("setQuantity() should set the quantity correctly", expectedQuantity, cot.getQuantity());
    }

    @Test 
    public void testSetSupplyID(){
        String expectedSupplyID = "9";
        cot.setSupplyID(expectedSupplyID);
        assertEquals("setSupplyID() should set the supplyid correctly", expectedSupplyID, cot.getSupplyID());
    }

    @Test
    public void testGetType(){
        String expectedType = "Cot";
        assertEquals("getType() should return the type correctly", expectedType, cot.getType());
    }

    @Test
    public void testGetQuantity(){
        Cot newCot = new Cot();
        int expectedQuantity = 1;
        newCot.setQuantity(expectedQuantity);
        assertEquals("getQuantity() should return the quantity correctly", expectedQuantity, newCot.getQuantity());
    }

    @Test
    public void testGetSupplyID(){
        Cot newCot = new Cot();
        String expectedSupplyID = "9";
        newCot.setSupplyID(expectedSupplyID);
        assertEquals("getSupplyID() should return the supplyid correctly", expectedSupplyID, newCot.getSupplyID());
    }

    @Test
    public void testSetAndGetPersonAllocatedTo(){
        Cot newCot = new Cot();
        Person expectedDisasterVictim = new Person("Alice", "Smith", "2001-02-17", 3, "gender_female", "is injured", "403-333-1111", "5");
        cot.setPersonAllocatedTo(expectedDisasterVictim);
        assertEquals("setPersonAlloctaedTo() should set the disastervictim the supply is allocated to correctly and getPersonAllocatedTo should return this value", expectedDisasterVictim, cot.getPersonAllocatedTo());
    }

    @Test
    public void testSetAndGetLocationAllocatedTo(){
        Location expectedLocation = new Location("Shelter A", "135 Ave");
        cot.setLocationAllocatedTo(expectedLocation);
        assertEquals("setLocationAlloctaedTo() should set the location the supply is allocated to correctly and getLocationAllocatedTo should return this value", expectedLocation, cot.getLocationAllocatedTo());
    }


    @Test
    public void testGetGrid(){
        Cot newCot = new Cot();
        String expectedGrid = "B11";
        newCot.setGrid(expectedGrid);
        assertEquals("getGrid() should return the grid correctly", expectedGrid, newCot.getGrid());
    }

    @Test
    public void testGetRoomNumber(){
        Cot newCot = new Cot();
        String expectedRoomNum = "1";
        newCot.setRoomNumber(expectedRoomNum);
        assertEquals("getRoomNumber() should return the room number correctly", expectedRoomNum, newCot.getRoomNumber());
    }

    @Test 
    public void testSetGrid(){
        String expectedGrid = "B19";
        cot.setGrid(expectedGrid);
        assertEquals("setGrid() should set the grid correctly", expectedGrid, cot.getGrid());
    }

    @Test
    public void testSetRoomNumber(){
        String expectedRoomNum = "133";
        cot.setRoomNumber(expectedRoomNum);
        assertEquals("setRoomNumber() should set the room number correctly", expectedRoomNum, cot.getRoomNumber());
    }



}
