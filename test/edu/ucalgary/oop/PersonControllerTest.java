package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class PersonControllerTest {
    private PersonController controller;
    private MockDatabase mockDB;

    @Before
    public void setUp(){
        //ensure lists are initially empty and then add to them
        UserInterface.setPeople(new ArrayList<Person>());
        UserInterface.setLocations(new ArrayList<Location>());
        UserInterface.setSupplies(new ArrayList<Supply>());
        UserInterface.setMedicalRecords(new ArrayList<MedicalRecord>());

        mockDB = new MockDatabase();
        controller = new PersonController(mockDB);

    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a non-null CotController object", controller);
    }

    @Test
    public void testAddPerson(){
        String firstName = "Anna";
        String lastName = "Smith";
        String dateOfBirth = "1997-01-10";
        String gender = "woman";
        String comments = "Is a disaster victim";
        String phoneNumber = "302-222-2200";
        String groupNumber = "2";

        int originalSize = UserInterface.getPeople().size();
        ArrayList<Person> updatedPeopleList = controller.addPerson(UserInterface.getPeople(), firstName, lastName, dateOfBirth, gender, comments, phoneNumber, groupNumber);
        int newSize = updatedPeopleList.size();

        assertEquals("Adding a person should increase the size of the list of people", 1, (newSize - originalSize));
    }

    @Test
    public void testAddMedicalRecordToPerson(){
        String firstName = "Anna";
        String lastName = "Smith";
        String dateOfBirth = "1997-01-10";
        String gender = "woman";
        String comments = "Is a disaster victim";
        String phoneNumber = "302-222-2200";
        String groupNumber = "2";
        Person person = new Person(firstName, lastName, dateOfBirth, 0, gender, comments, phoneNumber, groupNumber);
        
        String personID = "2";
        person.setPersonID(personID);

        UserInterface.getPeople().add(person);

        String medicalRecordID = "2";
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordID(medicalRecordID);

        ArrayList<Person> updatedPeopleList = controller.addMedicalRecordToPerson(UserInterface.getPeople(), medicalRecord, personID);
        
        String newMedicalRecordID = "";
        for(Person victim: updatedPeopleList){
            if(victim.getPersonID().equals(personID)){
                for(MedicalRecord medRecord : victim.getMedicalRecords()){
                    if(medRecord.getMedicalRecordID().equals(medicalRecordID)){
                        newMedicalRecordID = medRecord.getMedicalRecordID();
                    }
                }
                break;
            }
        }
        assertEquals("Adding a medical record to the person should ensure it is in their medical records list", medicalRecordID, newMedicalRecordID);
    }

    @Test
    public void testModifyPersonFirstName(){
        String personID = "2";
        Person person = new Person();
        person.setFirstName("Hailey");
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "Anna";

        controller.modifyPersonFirstName(personID, "first_name", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                if(personModified.getFirstName().equals(newValue)){
                    currentValue = personModified.getFirstName();
                }
            }
        }
        assertEquals("The locations new first name should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyPersonLastName(){
        String personID = "2";
        Person person = new Person();
        person.setLastName("Smith");
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "Wu";

        controller.modifyPersonLastName(personID, "last_name", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                if(personModified.getLastName().equals(newValue)){
                    currentValue = personModified.getLastName();
                }
            }
        }
        assertEquals("The locations new last name should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyPersonDateOfBirth(){
        String personID = "2";
        Person person = new Person();
        person.setDateOfBirth("2000-01-01");
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "2001-01-10";

        controller.modifyPersonDateOfBirth(personID, "date_of_birth", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                if(personModified.getDateOfBirth().equals(newValue)){
                    currentValue = personModified.getDateOfBirth();
                }
            }
        }
        assertEquals("The locations new DateOfBirth should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyPersonGender(){
        String personID = "2";
        Person person = new Person();
        person.setGender("man");
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "woman";

        controller.modifyPersonGender(personID, "gender", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                if(personModified.getGender().equals(newValue)){
                    currentValue = personModified.getGender();
                }
            }
        }
        assertEquals("The locations new Gender should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyPersonComments(){
        String personID = "2";
        Person person = new Person();
        person.setComments("was a victim");
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "is an inquirer";

        controller.modifyPersonComments(personID, "comments", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                if(personModified.getComments().equals(newValue)){
                    currentValue = personModified.getComments();
                }
            }
        }
        assertEquals("The locations new Comments should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyPhoneNumber(){
        String personID = "2";
        Person person = new Person();
        person.setPhoneNumber("587-777-7878");
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "403-303-3030";

        controller.modifyPersonPhoneNumber(personID, "phone_number", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                if(personModified.getPhoneNumber().equals(newValue)){
                    currentValue = personModified.getPhoneNumber();
                }
            }
        }
        assertEquals("The locations new PhoneNumber should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyFamilyGroupNumber(){
        String personID = "2";
        Person person = new Person();
        person.setFamilyGroupNumber(2);
        person.setPersonID(personID);
        UserInterface.getPeople().add(person);

        String newValue = "4";

        controller.modifyPersonFamilyGroupNumber(personID, "phone_number", newValue);

        //make sure the value was changed
        String currentValue = "";

        for(Person personModified : UserInterface.getPeople()){
            if(personModified.getPersonID().equals(personID)){
                currentValue = "4";
            }
        }
        assertEquals("The locations new family group number should macth the one sent into the modify function for the corrcet person id", newValue, currentValue);

    }

    @Test
    public void testModifyPersonLocationID(){
        String personID = "2";
        Person person = new Person();
        person.setPhoneNumber("587-777-7878");
        person.setPersonID(personID);
        
        UserInterface.getPeople().add(person);

        String locationID = "1";
        Location location = new Location();
        location.setLocationID(locationID);
        ArrayList<Person> occupants = new ArrayList<Person>();
        occupants.add(person);
        controller.modifyPersonLocationID(personID, "location_id:", locationID);
        location.setOccupants(occupants);
        UserInterface.getLocations().add(location);
        boolean personInCorrectLocation = false;
        for(Location location1: UserInterface.getLocations()){

            if(location1.getLocationID().equals(locationID)){
                for(Person victim : location1.getOccupants()){
                    if(victim.getPersonID().equals(personID)){
                        personInCorrectLocation = true;
                    }
                }
            }
        }
        assertTrue("The person should be in the corrcet list of occupanst in the corrcet location", personInCorrectLocation);

    }

    
}
