package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

import java.util.ArrayList;

public class WaterControllerTest {

    private Water water;
    private WaterController controller;
    private MockDatabase mockDB;
    private String personColumn;


    @Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setWaters(new ArrayList<Water>());
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());
        UserInterface.setSupplies(new ArrayList<Supply>());

        mockDB = new MockDatabase();
        controller = new WaterController(mockDB);
        water = new Water(1, "2020-10-01", "2");
        personColumn = "person_id";

    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null BlanketController object", controller);
    }

    @Test
    public void testAddWater(){
        String personID = "2";
        Person person = new Person();
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String locationID = "1";
        Location location = new Location();
        location.setLocationID(locationID);
        UserInterface.getLocations().add(location);
        //dateallocated valid
        LocalDate today = LocalDate.now();     
        String dateString = today.toString();

        int originalSize = UserInterface.getWaters().size();
        controller.addWater("water", dateString, personID, locationID);
        // this method will add the person and location to the water and give it a location allocated to
        // it ultimately adds a water object to the waters list and supplies list in the user interface
        // so we check that those were added by checking that the length increases by 1
        int newSize = UserInterface.getWaters().size();

        assertEquals("Adding a water should increase the length of the water list in the UI by 1", 1, (newSize - originalSize));

    }

    
@Test
public void testModifyPersonAllocatedTo(){
     //first need to add a water to the arrayList, then test to make sure it value is modified

     //dateallocated valid
     LocalDate today = LocalDate.now();     
     String dateAllocated = today.toString();

     String supplyID = "5";
     Water water = new Water(1, dateAllocated, supplyID);
     UserInterface.getWaters().add(water);

     String newPersonID = "6";
     Person person = new Person();
     person.setPersonID(newPersonID);
     UserInterface.getPeople().add(person);

     controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
    
     String newPersonIDAllocatedTo = "";
     for(Water waterAdded : UserInterface.getWaters()){
         if(waterAdded.getSupplyID().equals(supplyID)){
             newPersonIDAllocatedTo = waterAdded.getPersonAllocatedTo().getPersonID();
             break;
         }
     }
     assertEquals("The new person id the water is allocated to should match the newPersonID passed in", newPersonID, newPersonIDAllocatedTo);

}

@Test
public void testModifyPersonAllocatedToWithInvalidPerson(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     //dateallocated valid
     LocalDate today = LocalDate.now();     
     String dateAllocated = today.toString();

     String supplyID = "5";
     Water water = new Water(1, dateAllocated, supplyID);
     UserInterface.getWaters().add(water);

     String newPersonID = "6";
     Person person = new Person();
     person.setPersonID(newPersonID);
     //person not added to list

     controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
    
     Person newPersonAllocatedTo = null;
     for(Water waterAdded : UserInterface.getWaters()){
         if(waterAdded.getSupplyID().equals(supplyID)){
             newPersonAllocatedTo = waterAdded.getPersonAllocatedTo();
             break;
         }
     }
     assertNull("The new person id the water is allocated to should be null if the person passed in does not exist in the UI", newPersonAllocatedTo);

}
    
}
