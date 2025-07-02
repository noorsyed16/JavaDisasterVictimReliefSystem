package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class LocationControllerTest {

    private LocationController controller;
    private MockDatabase mockDB;

@Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());
        UserInterface.setSupplies(new ArrayList<Supply>());

        mockDB = new MockDatabase();
        controller = new LocationController(mockDB);

    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null CotController object", controller);
    }

    
    @Test
    public void testAddLocation(){
        String locationID = "1";
        Location location = new Location();
        location.setName("Calgary Zoo");
        location.setAddress("downtown calgary");
        location.setLocationID(locationID);
        UserInterface.getLocations().add(location);

        int originalSize = UserInterface.getLocations().size();
        controller.addLocation(location.getName(), location.getAddress());
        int newSize = UserInterface.getLocations().size();

        assertEquals("Adding a location should increase the length of the cots list in the UI by 1", 1, (newSize - originalSize));

    }

    @Test
    public void testModifyLocationName(){
        String locationID = "5";
        Location location = new Location();
        location.setLocationID(locationID);
        String name = "original name";
        location.setName(name);
        UserInterface.getLocations().add(location);

        String newLocationName = "new name";
        controller.modifyLocationName(locationID, "location_id", newLocationName);
        
        String newLocationNameAllocated = "";
        for(Location locationAdded : UserInterface.getLocations()){
            if(locationAdded.getLocationID().equals(locationID)){
                newLocationNameAllocated = locationAdded.getName();
                break;
            }
        }
        assertEquals("The locations new name should match what the user passes in ", newLocationName, newLocationNameAllocated);

    }

    @Test
    public void testModifyLocationAddress(){
        String locationID = "5";
        Location location = new Location();
        location.setLocationID(locationID);
        String address = "original address";
        location.setAddress(address);
        UserInterface.getLocations().add(location);

        String newLocationAddress = "new address";
        controller.modifyLocationAddress(locationID, "location_id", newLocationAddress);
        
        String newLocationAddressAllocated = "";
        for(Location locationAdded : UserInterface.getLocations()){
            if(locationAdded.getLocationID().equals(locationID)){
                newLocationAddressAllocated = locationAdded.getAddress();
                break;
            }
        }
        assertEquals("The locations new address should match what the user passes in ", newLocationAddress, newLocationAddressAllocated);

    }

    @Test
    public void testAddLocationToPerson(){
        String locationID = "5";
        Location location = new Location();
        location.setLocationID(locationID);
        UserInterface.getLocations().add(location);

        String personID = "2";
        Person person = new Person();
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        ArrayList<Location> newLocations = controller.addLocationToPerson(UserInterface.getLocations(), UserInterface.getPeople(), locationID, personID);
        //need to check that the person added is an occupant of the location
        String occupantAllocatedID = "";
        for(Location loc : newLocations){
            if(loc.getLocationID().equals(locationID)){
                for(Person occupant: loc.getOccupants()){
                    if(occupant.getPersonID().equals(personID)){
                        occupantAllocatedID = occupant.getPersonID();
                    }
                }
            }
        }
        assertEquals("The person was successfully added to the correct list of occupants", personID, occupantAllocatedID);

    }




    
}
