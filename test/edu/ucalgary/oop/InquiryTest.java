package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class InquiryTest {
    private Inquiry inquiry;
    private Inquiry inquiry2;
    @Before
    public void setUp(){
        inquiry = new Inquiry("7", "3", "2", "2020-01-10", "looking for sibling", "9");
        inquiry2 = new Inquiry();
    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null Inquiry object", inquiry2);
    }
    @Test
    public void testInquirerConstructor() {
        assertNotNull("Constructor should create a non-null Inquiry object", inquiry);
        assertEquals("Constructor should set the inquirerID correctly", "7", inquiry.getInquirerID());
        assertEquals("Constructor should set the seekingID correctly", "3", inquiry.getSeekingID());
        assertEquals("Constructor should set the locationID correctly", "2", inquiry.getLocationID());
        assertEquals("Constructor should set the dateOfInquiry correctly", "2020-01-10", inquiry.getDateOfInquiry());
        assertEquals("Constructor should set the comments correctly", "looking for sibling", inquiry.getComments());
        assertEquals("Constructor should set the inquiryID correctly", "9", inquiry.getInquiryID());
    }

    @Test
    public void testSetInquiryID(){
        String updatedInquiryID = "3";
        inquiry.setInquiryID(updatedInquiryID);
        assertEquals("setInquirerID() should set the InquiryID correctly", updatedInquiryID, inquiry.getInquiryID());
    }
    //cannot be seeking themselves??
    @Test
    public void testSetSeekingID(){
        String updatedSeekingID = "2";
        inquiry.setSeekingID(updatedSeekingID);
        assertEquals("setSeekingID() should set the SeekingID correctly", updatedSeekingID, inquiry.getSeekingID());
    }


    @Test
    public void testSetLocationID(){
        String updatedLocationID = "1";
        inquiry.setLocationID(updatedLocationID);
        assertEquals("setLocationID() should set the LocationID correctly", updatedLocationID, inquiry.getLocationID());
    }

    @Test
    public void testGetInquirerID(){
        String expectedInquirerID = "3";
        inquiry.setInquirerID(expectedInquirerID);
        assertEquals("getInquirerID() should return the InquirerID correctly", expectedInquirerID, inquiry.getInquirerID());
    }

    @Test
    public void testGetSeekingID(){
        String expectedSeekingID = "2";
        inquiry.setSeekingID(expectedSeekingID);
        assertEquals("getSeekingID() should return the SeekingID correctly", expectedSeekingID, inquiry.getSeekingID());
    }

    @Test
    public void testGetLocationID(){
        String expectedLocationID = "1";
        inquiry.setLocationID(expectedLocationID);
        assertEquals("getLocationID() should return the LocationID correctly", expectedLocationID, inquiry.getLocationID());
    }

    @Test
    public void testGetInquiryID(){
        String expectedInquiryID = "5";
        inquiry.setInquiryID(expectedInquiryID);
        assertEquals("getInquiryID() should return the InquiryID correctly", expectedInquiryID, inquiry.getInquiryID());
    }

    @Test
    public void testSetInquiryComments(){
        String updatedInquiryComments = "Looking for brother";
        inquiry.setComments(updatedInquiryComments);
        assertEquals("setComments() should set the Comments correctly", updatedInquiryComments, inquiry.getComments());
    }

    @Test
    public void testGetInquiryComments(){
        String expectedComments = "Looking for brother";
        inquiry.setComments(expectedComments);
        assertEquals("getComments() should return the comments correctly", expectedComments, inquiry.getComments());
    }

    @Test
    public void testSetDateOfInquiry(){
        String updatedDateOfInquiry = "2019-01-10";
        inquiry.setDateOfInquiry(updatedDateOfInquiry);
        assertEquals("setDateOfInquiry() should set the date of inquiry correctly", updatedDateOfInquiry, inquiry.getDateOfInquiry());
    }

    @Test 
    public void testSetDateOfInquiryWithInvalidDate(){
        String updatedInvalidDate = "2019\01/-10";
        // // Will return false from the try catch block which catches the exception thrown from inavlid date
        assertFalse(inquiry.setDateOfInquiry(updatedInvalidDate));
    }

    @Test
    public void testGetDateOfInquiry(){
        String expectedDateOfInquiry = "2019-01-10";
        inquiry.setDateOfInquiry(expectedDateOfInquiry);
        assertEquals("getDateOfInquiry() should return the date of inquiry correctly", expectedDateOfInquiry, inquiry.getDateOfInquiry());
    }

    @Test
    //is Valid Date
    public void testIsValidDateWithValidInput(){
        String validDate = "2020-03-19";
        assertEquals("isValidDate should return true when given a valid date", true, inquiry.isValidDate(validDate));

    }

    @Test
    //is inValid Date
    public void testIsValidDateWithInValidInput(){
        String invalidDate = "2020_03/19";
        assertEquals("isValidDate should return false when given an invalid date", false, inquiry.isValidDate(invalidDate));
    }
    

    
}
