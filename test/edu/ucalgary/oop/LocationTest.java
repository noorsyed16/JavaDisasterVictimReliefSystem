package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class LocationTest {
    private Location location;
    private Person victim;


    @Before
    public void setUp(){
        location = new Location("Shelter A", "135 Ave");
        victim = new Person("Alice", "Smith", "2001-02-17", 3, "gender_female", "is injured","403-333-1111", "5");
        }

    @Test
    public void testObjectCreation(){
        Location location = new Location();
        assertNotNull("Constructor should create a non-null Location object", location);
    }

    @Test
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null Location object", location);
        assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
        assertEquals("Constructor should set the address correctly", "135 Ave", location.getAddress());
    }

    @Test
    public void testSetName() {
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }

    @Test
    public void testSetAddress() {
        String newAddress = "432 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }

    @Test
    public void testAddOccupant() {
        location.addOccupant(victim);
        assertTrue("addOccupant should add a disaster victim to the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testRemoveOccupant() {
         location.addOccupant(victim); 
        location.removeOccupant(victim);
        assertFalse("removeOccupant should remove the disaster victim from the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testSetAndGetOccupants() {
        ArrayList<Person> newOccupants = new ArrayList<>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants list with the new list", location.getOccupants().containsAll(newOccupants));
    }


    @Test
    public void testGetName() {
        String expectedName = "Shelter B";
        location.setName(expectedName);
        assertEquals("getName should return the name of the location", expectedName, location.getName());
    }

    @Test
    public void testGetAddress() {
        String expectedAddress = "432 Shelter Blvd";
        location.setAddress(expectedAddress);
        assertEquals("getAddress should return the address of the location", expectedAddress, location.getAddress());
    }

    @Test
    public void testSetLocationID() {
        String newLocationID = "4";
        location.setLocationID(newLocationID);
        assertEquals("setLocationID should update the locationID of the location", newLocationID, location.getLocationID());
    }

    @Test
    public void testGetLocationID() {
        String expectedLocationID = "5";
        location.setLocationID(expectedLocationID);
        assertEquals("getLocationID should return the locationID of the location", expectedLocationID, location.getLocationID());
    }

}
