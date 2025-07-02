package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class BlanketControllerTest {
    private Blanket blanket;
    private BlanketController controller;
    private MockDatabase mockDB;
    private String personColumn;

    @Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setBlankets(new ArrayList<Blanket>());
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());
        UserInterface.setSupplies(new ArrayList<Supply>());

        mockDB = new MockDatabase();
        controller = new BlanketController(mockDB);
        blanket = new Blanket(1, "2");
        personColumn = "person_id";

        }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null BlanketController object", controller);
    }

    @Test
    public void testAddBlanket(){
        String personID = "2";
        Person person = new Person();
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String locationID = "1";
        Location location = new Location();
        location.setLocationID(locationID);
        UserInterface.getLocations().add(location);

        int originalSize = UserInterface.getBlankets().size();
        controller.addBlanket("blanket", null, personID, locationID);
        // this method will add the person and location to the blanket and give it a location allocated to
        // it ultimately adds a blanket object to the blankets list and supplies list in the user interface
        // so we check that those were added by checking that the length increases by 1
        int newSize = UserInterface.getBlankets().size();

        assertEquals("Adding a blanket should increase the length of the supplies list in the UI by 1", 1, (newSize - originalSize));

    }


    @Test
    public void testModifyLocationllocatedTo(){
        //first need to add a blanket to the arrayList, then test to make sure it value is modified
        String supplyID = "4";
        Blanket blanket = new Blanket(1, supplyID);
        UserInterface.getBlankets().add(blanket);

        String newLocationID = "1";
        Location location = new Location();
        location.setLocationID(newLocationID);
        UserInterface.getLocations().add(location);

        controller.modifyLocationAllocatedTo(supplyID, "location_id", newLocationID);
        
        String newLocationIDAllocatedTo = "";
        for(Blanket blanketAdded : UserInterface.getBlankets()){
            if(blanketAdded.getSupplyID().equals(supplyID)){
                newLocationIDAllocatedTo = blanketAdded.getLocationAllocatedTo().getLocationID();
                break;
            }
        }
        assertEquals("The new location id the blanket is allocated to should match the newLocationID passed in", newLocationID, newLocationIDAllocatedTo);
    }

    @Test
    public void testModifyLocationAllocatedToAnInvalidValue(){
        //first need to add a blanket to the arrayList, then test to make sure it value is modified
        String supplyID = "4";
        Blanket blanket = new Blanket(1, supplyID);
        UserInterface.getBlankets().add(blanket);

        String newLocationID = "1";
        Location location = new Location();
        location.setLocationID(newLocationID);
        //location not added to locations list

        controller.modifyLocationAllocatedTo(supplyID, "location_id", newLocationID);
        
       Location newLocationAllocatedTo = null;
        for(Blanket blanketAdded : UserInterface.getBlankets()){
            if(blanketAdded.getSupplyID().equals(supplyID)){
                newLocationAllocatedTo = blanketAdded.getLocationAllocatedTo();
                break;
            }
        }
        assertNull("The new location id the blanket is allocated to should be null if it was not a location existing in the user interface", newLocationAllocatedTo);
    }


@Test
public void testModifyPersonAllocatedTo(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     String supplyID = "5";
     Blanket blanket = new Blanket(1, supplyID);
     UserInterface.getBlankets().add(blanket);

     String newPersonID = "6";
     Person person = new Person();
     person.setPersonID(newPersonID);
     UserInterface.getPeople().add(person);

     controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
    
     String newPersonIDAllocatedTo = "";
     for(Blanket blanketAdded : UserInterface.getBlankets()){
         if(blanketAdded.getSupplyID().equals(supplyID)){
             newPersonIDAllocatedTo = blanketAdded.getPersonAllocatedTo().getPersonID();
             break;
         }
     }
     assertEquals("The new person id the blanket is allocated to should match the newPersonID passed in", newPersonID, newPersonIDAllocatedTo);

}

@Test
public void testModifyPersonAllocatedToWithInvalidPerson(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     String supplyID = "5";
     Blanket blanket = new Blanket(1, supplyID);
     UserInterface.getBlankets().add(blanket);

     String newPersonID = "6";
     Person person = new Person();
     person.setPersonID(newPersonID);
     //person not added to list

     controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
    
     Person newPersonAllocatedTo = null;
     for(Blanket blanketAdded : UserInterface.getBlankets()){
         if(blanketAdded.getSupplyID().equals(supplyID)){
             newPersonAllocatedTo = blanketAdded.getPersonAllocatedTo();
             break;
         }
     }
     assertNull("The new person id the blanket is allocated to should be null if the person passed in does not exist in the UI", newPersonAllocatedTo);

}

}
