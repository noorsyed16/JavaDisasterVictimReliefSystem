package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class MedicalRecordTest {
    private Location location;
    private MedicalRecord medicalRecord;
    
    @Before
    public void setUp(){
        location = new Location("Shelter D", "130 Ave SE");
        medicalRecord = new MedicalRecord(location, "broken leg treated", "2020-02-17", "3");
    }
   

    @Test
    //tests Medical Record default Constructor
    public void testObjectCreation() {
        MedicalRecord testMedicalRecord = new MedicalRecord();
        assertNotNull(medicalRecord);
    }	

    @Test
    //test constructor
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null MedicalRecord object", medicalRecord);
        assertEquals("Constructor should set the location name correctly", "Shelter D", medicalRecord.getLocation().getName());
        assertEquals("Constructor should set the location address correctly", "130 Ave SE", medicalRecord.getLocation().getAddress());
        assertEquals("Constructor should set the dateOfTreatment correctly", "2020-02-17", medicalRecord.getDateOfTreatment());
        assertEquals("Constructor should set the medicalRecordID correctly", "3", medicalRecord.getMedicalRecordID());
    }
	
    @Test
    //Tests getLocation
    public void testGetLocation() {
        Location newLocation = new Location("Shelter D", "130 Ave SE");
        medicalRecord.setLocation(newLocation);
        assertEquals("getLocation should return the correct Location", newLocation, medicalRecord.getLocation());
    }

    @Test
    //test setLocation
    public void testSetLocation() {
        Location newExpectedLocation = new Location("Shelter C", "155 32 Ave NE ");
        medicalRecord.setLocation(newExpectedLocation);
        assertEquals("setLocation should update the Location", newExpectedLocation.getName(), medicalRecord.getLocation().getName());
    }

    @Test
    //test getTreatmentDetails
    public void testGetTreatmentDetails() {
        String expectedTreatmentDetails = "broken leg treated";
        medicalRecord.setTreatmentDetails(expectedTreatmentDetails);
        assertEquals("getTreatmentDetails should return the correct treatment details", expectedTreatmentDetails, medicalRecord.getTreatmentDetails());
    }
    @Test
    //test setTreatmentDetails
    public void testSetTreatmentDetails() {
	    String newExpectedTreatment = "No surgery needed";
	    medicalRecord.setTreatmentDetails(newExpectedTreatment);
        assertEquals("setTreatmentDetails should update the treatment details", newExpectedTreatment, medicalRecord.getTreatmentDetails());
    }


    @Test
    //test getDateOfTreatment
    public void testGetDateOfTreatment() {
        String expectedDate = "2020-02-17";
        medicalRecord.setDateOfTreatment(expectedDate);
        assertEquals("getDateOfTreatment should return the correct date of treatment", expectedDate, medicalRecord.getDateOfTreatment());
    }
	
	@Test
    //test setDateOfTreatment
    public void testSetDateOfTreatment() {
	    String newExpectedDateOfTreatment = "2025-02-05";
	    medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
        assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }

    @Test
    //test setDateOfTreatment
    public void testSetDateOfTreatmentWithValidDateFormate() {
	    String validDateOfTreatment = "2025-02-05";
	    medicalRecord.setDateOfTreatment(validDateOfTreatment);
        assertTrue("setDateOfTreatment should return true with valid date of treatment", medicalRecord.setDateOfTreatment(validDateOfTreatment));
    }

    @Test
    //test setDateOfTreatment
    public void testSetDateOfTreatmentWithInvalidDateFormate() {
	    String invalidDateOfTreatment = "2025\02/05";
	    medicalRecord.setDateOfTreatment(invalidDateOfTreatment);
        assertFalse("setDateOfTreatment should ureturn false with invalid format", medicalRecord.setDateOfTreatment(invalidDateOfTreatment));
    }


    @Test
    //is Valid Date
    public void testIsValidDateWithValidInput(){
        String validDate = "2020-03-19";
        assertEquals("isValidDate should return true when given a valid date", true, medicalRecord.isValidDate(validDate));

    }

    @Test
    //is inValid Date
    public void testIsValidDateWithInValidInput(){
        String inValidDate = "2020_03/19";
        assertEquals("isValidDate should return false when given an invalid date", false, medicalRecord.isValidDate(inValidDate));
    }
}
