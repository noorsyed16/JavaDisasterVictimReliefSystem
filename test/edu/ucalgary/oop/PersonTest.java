package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class PersonTest {
    private Person person;

@Before
public void setUp(){
    person = new Person("Alice", "Smith", "2001-02-17", 3, "gender_female", "is injured", "403-333-1111", "5");
}

@Test
public void testObjectCreation() {
    Person person = new Person();
    assertNotNull("Constructor should create a non-null Inquirer object", person);
}
    @Test
    public void testPersonConstructor() {
        assertEquals("Constructor should set the firstName correctly", "Alice", person.getFirstName());
        assertEquals("Constructor should set the lastName correctly", "Smith", person.getLastName());
        assertEquals("Constructor should set the dateOfBirth correctly", "2001-02-17", person.getDateOfBirth());
        assertEquals("Constructor should set the familyGroupNumber correctly", 3, person.getFamilyGroupNumber());
        assertEquals("Constructor should set the gender correctly", "gender_female", person.getGender());
        assertEquals("Constructor should set the phoneNumber correctly", "403-333-1111", person.getPhoneNumber());
        assertEquals("Constructor should set the disasterVictimID correctly", "5", person.getPersonID());
    }

    @Test
    public void testSetFirstName(){
        String expectedFirstName = "Joe";
        person.setFirstName(expectedFirstName);
        assertEquals("setFirstName() should set the firstName correctly", expectedFirstName, person.getFirstName());
    }

    @Test
    public void testSetLastName(){
        String expectedLastName = "Green";
        person.setLastName(expectedLastName);
        assertEquals("setLastName() should set the LastName correctly", expectedLastName, person.getLastName());
    }
    @Test
    public void testGetFirstName() {
        String expectedFirstName = "Joe";
        person.setFirstName(expectedFirstName);
        assertEquals("getFirstName() should return victim's first name", expectedFirstName, person.getFirstName());
    }
	
    @Test
    public void testGetLastName() {
        String expectedLastName = "Watson";
        person.setLastName(expectedLastName);
        assertEquals("getLastName() should return victim's first name", expectedLastName, person.getLastName());
    }

    @Test
    public void testSetDateOfBirth(){
        String expectedDateOfBirth = "2000-01-12";
        person.setDateOfBirth(expectedDateOfBirth);
        assertEquals("setDateofBirth() should set the dateOfBirth correctly", expectedDateOfBirth, person.getDateOfBirth());
    }


    @Test
    public void testSetDateOfBirthWithInvalidFormat() {
        String invalidDate = "15/13/2025";
        // This format should cause an exception which will be caught in try ctach an returned as false
        assertFalse(person.setDateOfBirth(invalidDate)); 
    }
	

    @Test
    public void testSetFamilyGroupNumber(){
        int expectedFamily = 4;
        person.setFamilyGroupNumber(expectedFamily);
        assertEquals("setFamilyGroupNumber() should set the familyGroupNumber correctly", expectedFamily, person.getFamilyGroupNumber());
    }

    @Test
    public void testGetDateOfBirth() {
        String expectedDateOfBirth = "1999-01-01";
        person.setDateOfBirth(expectedDateOfBirth);
        assertEquals("getDateOfBirth() should return victim's date of birth", expectedDateOfBirth, person.getDateOfBirth());
    }
	
    @Test
    public void testGetFamilyGroupNumber() {
        int expectedFamily = 7;
        person.setFamilyGroupNumber(expectedFamily);
        assertEquals("getFamilyGroupNumber() should return victim's family/Group number", expectedFamily, person.getFamilyGroupNumber());
    }

    @Test
    public void testSetGender(){
        String expectedGender = "woman";
        person.setGender(expectedGender);
        assertEquals("setGender() should set the gender correctly", expectedGender, person.getGender());
    }

    @Test
    public void testSetComments(){
        String expectedComments = "Looking for daughter";
        person.setComments(expectedComments);
        assertEquals("setComments() should set the comments correctly", expectedComments, person.getComments());
    }

    @Test
    public void testGetGender() {
        String expectedGender = "man";
        person.setGender(expectedGender);
        assertEquals("getGender() should return inquirer's gender", expectedGender, person.getGender());
    }
	
    @Test
    public void testGetComments() {
        String expectedComments = "Looking for daughter";
        person.setComments(expectedComments);
        assertEquals("getComments() should return inquirer's comments", expectedComments, person.getComments());
    }

    @Test
    public void testIsValidGenderWithValidGender(){
        String validGender = "man";
        assertEquals("isValidGender should return true when given a valid gender", true, person.isValidGender(validGender));

    }
    
    @Test
    public void testIsValidGenderWithInValidGender(){
        String inValidGender = "guy";
        assertEquals("isValidGender should return false when given an invalid gender", false, person.isValidGender(inValidGender));

    }

    @Test
    //is Valid Date
    public void testIsValidDateWithValidInput(){
        String validDate = "2020-03-19";
        assertEquals("isValidDate should return true when given a valid date", true, person.isValidDate(validDate));

    }

    @Test
    //is inValid Date
    public void testIsValidDateWithInValidInput(){
        String inValidDate = "2020_03/19";
        assertEquals("isValidDate should return false when given an invalid date", false, person.isValidDate(inValidDate));

    }

    @Test
    public void testSetPhoneNumber(){
        String expectedPhoneNum = "403-333-3333";
        person.setPhoneNumber(expectedPhoneNum);
        assertEquals("setPhoneNumber() should set the phone Number correctly", expectedPhoneNum, person.getPhoneNumber());
    }

    @Test
    public void testGetPhoneNumber() {
        String expectedPhoneNum = "403-333-3333";
        person.setPhoneNumber(expectedPhoneNum);
        assertEquals("getPhoneNumber() should return person's phoneNumber", expectedPhoneNum, person.getPhoneNumber());
    }

    @Test
    public void testSetPersonID(){
        String expectedPersonID = "7";
        person.setPersonID(expectedPersonID);
        assertEquals("setPersonID() should set the PersonID correctly", expectedPersonID, person.getPersonID());
    }

    @Test
    public void testGetPersonID() {
        String expectedPersonID = "7";
        person.setPersonID(expectedPersonID);
        assertEquals("getPerson() should return PersonID", expectedPersonID, person.getPersonID());
    }

    @Test
    public void testSetEntryDate(){
        String expectedEntryDate = "2007-09-10";
        person.setEntryDate(expectedEntryDate);
        assertEquals("setEntryDate() should set the EntryDate correctly", expectedEntryDate, person.getEntryDate());
    }

    @Test
    public void testGetEntryDate() {
        String expectedEntryDate = "2020-01-19";
        person.setEntryDate(expectedEntryDate);
        assertEquals("getEntryDate() should return EntryDate", expectedEntryDate, person.getEntryDate());
    }
    
    @Test
    public void testAddAndSetMedicalRecords() {
        Location location = new Location("Shelter A", "135 Ave");
        MedicalRecord medicalRecord = new MedicalRecord(location, "broken leg treated", "2020-02-17", "3");
        ArrayList<MedicalRecord> list = new ArrayList<>();
        list.add(medicalRecord);
        person.setMedicalRecords(list);
        assertTrue("addMedicalRecord should add a medicalRecord to the medicalRecords list", person.getMedicalRecords().contains(medicalRecord));
    }


}





