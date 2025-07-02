package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class CotControllerTest {
    private Cot cot;
    private CotController controller;
    private MockDatabase mockDB;
    private String personColumn;

   @Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setCots(new ArrayList<Cot>());
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());
        UserInterface.setSupplies(new ArrayList<Supply>());

        mockDB = new MockDatabase();
        controller = new CotController(mockDB);
        cot = new Cot(1, "232", "B32", "2");
        personColumn = "person_id";

    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null CotController object", controller);
    }

    
    @Test
    public void testAddCot(){
        String personID = "2";
        Person person = new Person();
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String locationID = "1";
        Location location = new Location();
        location.setLocationID(locationID);
        UserInterface.getLocations().add(location);

        int originalSize = UserInterface.getCots().size();
        controller.addCot("cot", "444", "B90", personID, locationID);
        // this method will add the person and location to the blanket and give it a location allocated to
        // it ultimately adds a blanket object to the blankets list and supplies list in the user interface
        // so we check that those were added by checking that the length increases by 1
        int newSize = UserInterface.getCots().size();

        assertEquals("Adding a cot should increase the length of the cots list in the UI by 1", 1, (newSize - originalSize));

    }


    @Test
    public void testModifyCotLocationllocatedTo(){
        //first need to add a cot to the arrayList, then test to make sure it value is modified
        String supplyID = "4";
        Cot cot = new Cot(1, "444", "B90", supplyID);
        UserInterface.getCots().add(cot);

        String newLocationID = "1";
        Location location = new Location();
        location.setLocationID(newLocationID);
        UserInterface.getLocations().add(location);

        controller.modifyLocationAllocatedTo(supplyID, "location_id", newLocationID);
        
        String newLocationIDAllocatedTo = "";
        for(Cot cotAdded : UserInterface.getCots()){
            if(cotAdded.getSupplyID().equals(supplyID)){
                newLocationIDAllocatedTo = cotAdded.getLocationAllocatedTo().getLocationID();
                break;
            }
        }
        assertEquals("The new location id the cot is allocated to should match the newLocationID passed in", newLocationID, newLocationIDAllocatedTo);
    }

    @Test
    public void testModifyLocationAllocatedToAnInvalidValue(){
        //first need to add a blanket to the arrayList, then test to make sure it value is modified
        String supplyID = "4";
        Cot cot = new Cot(1, "444", "B90", supplyID);
        UserInterface.getCots().add(cot);

        String newLocationID = "1";
        Location location = new Location();
        location.setLocationID(newLocationID);
        //location not added to locations list

        controller.modifyLocationAllocatedTo(supplyID, "location_id", newLocationID);
        
       Location newLocationAllocatedTo = null;
        for(Cot cotAdded: UserInterface.getCots()){
            if(cotAdded.getSupplyID().equals(supplyID)){
                newLocationAllocatedTo = cotAdded.getLocationAllocatedTo();
                break;
            }
        }
        assertNull("The new location id the cot is allocated to should be null if it was not a location existing in the user interface", newLocationAllocatedTo);
    }


@Test
public void testModifyPersonAllocatedTo(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     String supplyID = "5";
     Cot cot = new Cot(1, "444", "B90", supplyID);
     UserInterface.getCots().add(cot);

     String newPersonID = "6";
     Person person = new Person();
     person.setPersonID(newPersonID);
     UserInterface.getPeople().add(person);

     controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
    
     String newPersonIDAllocatedTo = "";
     for(Cot cotAdded : UserInterface.getCots()){
         if(cotAdded.getSupplyID().equals(supplyID)){
             newPersonIDAllocatedTo = cotAdded.getPersonAllocatedTo().getPersonID();
             break;
         }
     }
     assertEquals("The new person id the cot is allocated to should match the newPersonID passed in", newPersonID, newPersonIDAllocatedTo);

}

@Test
public void testModifyPersonAllocatedToWithInvalidPerson(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     String supplyID = "5";
     Cot cot = new Cot(1, "444", "B90", supplyID);
     UserInterface.getCots().add(cot);

     String newPersonID = "6";
     Person person = new Person();
     person.setPersonID(newPersonID);
     //person not added to list

     controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
    
     Person newPersonAllocatedTo = null;
     for(Cot cotAdded : UserInterface.getCots()){
         if(cotAdded.getSupplyID().equals(supplyID)){
             newPersonAllocatedTo = cotAdded.getPersonAllocatedTo();
             break;
         }
     }
     assertNull("The new person id the cot is allocated to should be null if the person passed in does not exist in the UI", newPersonAllocatedTo);

}

@Test
public void testModifyCotRoomNumber(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     String supplyID = "5";
     Cot cot = new Cot(1, "444", "B90", supplyID);
     UserInterface.getCots().add(cot);

     String newRoomNumber = "234";
     controller.modifyCotRoomNumber(supplyID, personColumn, newRoomNumber);
    
     String newRoomNumberAllocated = "";
     for(Cot cotAdded : UserInterface.getCots()){
         if(cotAdded.getSupplyID().equals(supplyID)){
             newRoomNumberAllocated = cotAdded.getRoomNumber();
             break;
         }
     }
     assertEquals("The new room number the cot is allocated to should match the new room number passed in", newRoomNumber, newRoomNumberAllocated);

}

@Test
public void testModifyCotGrid(){
     //first need to add a blanket to the arrayList, then test to make sure it value is modified
     String supplyID = "5";
     Cot cot = new Cot(1, "444", "B90", supplyID);
     UserInterface.getCots().add(cot);

     String newGrid = "B12";
     controller.modifyCotGrid(supplyID, personColumn, newGrid);
    
     String newGridAllocated = "";
     for(Cot cotAdded : UserInterface.getCots()){
         if(cotAdded.getSupplyID().equals(supplyID)){
             newGridAllocated = cotAdded.getGrid();
             break;
         }
     }
     assertEquals("The new grid the cot is allocated to should match the new grid passed in", newGrid, newGridAllocated);

}


    
}
