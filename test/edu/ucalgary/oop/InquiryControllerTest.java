package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;


public class InquiryControllerTest {

    private InquiryController controller;
    private MockDatabase mockDB;
    private String personColumn;
   
    @Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setInquiries(new ArrayList<Inquiry>());
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());

        mockDB = new MockDatabase();
        controller = new InquiryController(mockDB);
        //inquiry = new Inquiry(inquirerID, seekingID, locationID, dateOfInquiry, comments, inquiryID);
        personColumn = "person_id";

    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null CotController object", controller);
    }

    @Test
    public void testAddInquiry(){
        String inquirerID = "2";
        Person inquirer = new Person();
        inquirer.setPersonID(inquirerID);
        UserInterface.getPeople().add(inquirer);

        String seekingID = "2";
        Person seeked = new Person();
        inquirer.setPersonID(seekingID);
        UserInterface.getPeople().add(seeked);

        String locationID = "1";
        Location location = new Location();
        location.setLocationID(locationID);
        UserInterface.getLocations().add(location);

        String dateOfInquiry = "2020-10-22";
        String comments = "Looking for sister";

        int originalSize = UserInterface.getInquiries().size();
        controller.addInquiry(inquirerID, seekingID, locationID, dateOfInquiry, comments);
        // this method will add the person and location to the blanket and give it a location allocated to
        // it ultimately adds a blanket object to the blankets list and supplies list in the user interface
        // so we check that those were added by checking that the length increases by 1
        int newSize = UserInterface.getInquiries().size();

        assertEquals("Adding a cot should increase the length of the cots list in the UI by 1", 1, (newSize - originalSize));

    }

@Test
public void testModifyDateOfInquiry(){
     String inquiryID = "5";
     Inquiry inquiry = new Inquiry();
     inquiry.setInquiryID(inquiryID);
     UserInterface.getInquiries().add(inquiry);

     String newDateOfInquiry = "2022-10-01";
     controller.modifyInquiryDateOfInquiry(inquiryID, personColumn, newDateOfInquiry);
    
     String newDateOfInquiryAllocated = "";
     for(Inquiry inquiryAdded : UserInterface.getInquiries()){
         if(inquiryAdded.getInquiryID().equals(inquiryID)){
             newDateOfInquiryAllocated = inquiryAdded.getDateOfInquiry();
             break;
         }
     }
     assertEquals("The inquiry's new date of inquiry shoudl match what the user passes in ", newDateOfInquiry, newDateOfInquiryAllocated);

}

@Test
public void testModifyInquiryLocationID(){
     String inquiryID = "5";
     Inquiry inquiry = new Inquiry();
     inquiry.setInquiryID(inquiryID);

    String locationID = "1";
    Location location1 = new Location();
    location1.setLocationID(locationID);
    UserInterface.getLocations().add(location1);
    inquiry.setLocationID("1");

     UserInterface.getInquiries().add(inquiry);

    String newLocationID = "2";
    Location location = new Location();
    location.setLocationID(newLocationID);
    UserInterface.getLocations().add(location);

    controller.modifyInquiryLocationID(inquiryID, personColumn, newLocationID);
    
     String newLocationIDAllocated = "";
     for(Inquiry inquiryAdded : UserInterface.getInquiries()){
         if(inquiryAdded.getInquiryID().equals(inquiryID)){
             newLocationIDAllocated = inquiryAdded.getLocationID();
             break;
         }
     }
     assertEquals("The inquiry's new date of inquiry should match what the user passes in ", newLocationID, newLocationIDAllocated);

}


@Test
public void testModifyInquiryComments(){
     String inquiryID = "5";
     Inquiry inquiry = new Inquiry();
     inquiry.setInquiryID(inquiryID);
     UserInterface.getInquiries().add(inquiry);

     String newInquiryComments = "looking for brother";
     controller.modifyInquiryComments(inquiryID, personColumn, newInquiryComments);
    
     String newInquiryCommentsAllocated = "";
     for(Inquiry inquiryAdded : UserInterface.getInquiries()){
         if(inquiryAdded.getInquiryID().equals(inquiryID)){
             newInquiryCommentsAllocated = inquiryAdded.getComments();
             break;
         }
     }
     assertEquals("The inquiry's new comments should match what the user passes in ", newInquiryComments, newInquiryCommentsAllocated);

}



}
