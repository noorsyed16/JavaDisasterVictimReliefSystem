package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.filechooser.FileView;


public class PersonalBelongingControllerTest {

    private PersonalBelonging personalBelonging;
    private PersonalBelongingController controller;
    private MockDatabase mockDB;
    private String personColumn;

   @Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setPersonalBelongings(new ArrayList<PersonalBelonging>());
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());
        UserInterface.setSupplies(new ArrayList<Supply>());

        mockDB = new MockDatabase();
        controller = new PersonalBelongingController(mockDB);
        personalBelonging = new PersonalBelonging(1, "description", "2");
        personColumn = "person_id";

        }
    @Test
        public void testObjectCreation() {
            assertNotNull("Constructor should create a non-null BlanketController object", controller);
        }

     @Test
        public void testAddPersonalBelonging(){
            String personID = "2";
            Person person = new Person();
            person.setPersonID(personID);
            UserInterface.getPeople().add(person);
    
            int originalSize = UserInterface.getPersonalBelongings().size();
            controller.addPersonalBelonging("personal item", "comments", personID);
            int newSize = UserInterface.getPersonalBelongings().size();
    
            assertEquals("Adding a personalBelonging should increase the length of the supplies list in the UI by 1", 1, (newSize - originalSize));
    
        }


        @Test
        public void testModifyPersonAllocatedTo(){

             String supplyID = "5";
             PersonalBelonging personalBelonging =  new PersonalBelonging(1, "description", supplyID);
             UserInterface.getPersonalBelongings().add(personalBelonging);
        
             String newPersonID = "6";
             Person person = new Person();
             person.setPersonID(newPersonID);
             UserInterface.getPeople().add(person);
        
             controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
            
             String newPersonIDAllocatedTo = "";
             for(PersonalBelonging itemAdded : UserInterface.getPersonalBelongings()){
                 if(itemAdded.getSupplyID().equals(supplyID)){
                     newPersonIDAllocatedTo = itemAdded.getPersonAllocatedTo().getPersonID();
                     break;
                 }
             }
             assertEquals("The new person id the personal belonging is allocated to should match the newPersonID passed in", newPersonID, newPersonIDAllocatedTo);
        
        }
        
        @Test
        public void testModifyPersonAllocatedToWithInvalidPerson(){
             //first need to add a blanket to the arrayList, then test to make sure it value is modified
             String supplyID = "5";
             PersonalBelonging personalBelonging =  new PersonalBelonging(1, "description", supplyID);
             UserInterface.getPersonalBelongings().add(personalBelonging);
        
        
             String newPersonID = "6";
             Person person = new Person();
             person.setPersonID(newPersonID);
             //person not added to list
        
             controller.modifyPersonAllocatedTo(supplyID, personColumn, newPersonID);
            
             Person newPersonAllocatedTo = null;
             for(PersonalBelonging itemAdded : UserInterface.getPersonalBelongings()){
                 if(itemAdded.getSupplyID().equals(supplyID)){
                     newPersonAllocatedTo = itemAdded.getPersonAllocatedTo();
                     break;
                 }
             }
             assertNull("The new person id the cot is allocated to should be null if the person passed in does not exist in the UI", newPersonAllocatedTo);
        
        }
        
    
    
}
