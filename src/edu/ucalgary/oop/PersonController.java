package edu.ucalgary.oop;

import java.util.ArrayList;

public class PersonController {

    private DatabaseAccess database;
    private Person model;

    public PersonController(DatabaseAccess dBType){
        this.database = dBType;
    }

    public ArrayList<Person> addPerson(ArrayList<Person> people, String firstName, String lastName, String dateOfBirth, String gender, String comments, String phoneNumber, String groupNumber){
        // need to create it in the model
       //need to create it in the object array list we have
       //need the id to make it, so query for what the new id will be
       int intPersonID = database.queryForCurrentIDInTable("Person", "person_id") + 1;
       String personID = Integer.toString(intPersonID);
       int intGroupNumber = Integer.parseInt(groupNumber);
       Person newPerson = new Person(firstName, lastName, dateOfBirth, intGroupNumber, gender, comments, phoneNumber, personID);
       people.add(newPerson);

       //the need to add to the database
       database.insertNewPerson(firstName, lastName, dateOfBirth, gender, comments, phoneNumber, intGroupNumber);
        return people;

    }

    public ArrayList<Person> addMedicalRecordToPerson(ArrayList<Person> people, MedicalRecord medicalRecordAdded, String personID){
        for(Person victim: people){
            if(personID.equals(victim.getPersonID())){
                victim.addMedicalRecord(medicalRecordAdded);
                break;
            }
        }
        return people;

    }
    public void modifyPersonFirstName(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setFirstName(newValue);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonLastName(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setLastName(newValue);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonDateOfBirth(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setDateOfBirth(newValue);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonGender(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setGender(newValue);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonComments(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setComments(newValue);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonPhoneNumber(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setPhoneNumber(newValue);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonFamilyGroupNumber(String personID, String columnName, String newValue){
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.setFamilyGroupNumber(0);
            }
        }
        //make change in database too
        database.modifyPersonColumn(personID, columnName, newValue);

    }

    public void modifyPersonLocationID(String personID, String columnName, String locationID){
        //need location to have this person in its list of occupants 
        //and remove it from its previous locations list of occupants
        Person occupantToRemove = null;
        for(Location location : UserInterface.getLocations()){
            if(location.getOccupants() == null){

            }
            else{
                for(Person occupant : location.getOccupants()){
                if(occupant.getPersonID().equals(personID)){
                    occupantToRemove = occupant;
                }
            }
            if(occupantToRemove == null){
                continue;
            }
            location.getOccupants().remove(occupantToRemove);
            occupantToRemove = null;
            }
        }
        Person occupant = null;
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                occupant = person;
            }
        }
        for(Location location : UserInterface.getLocations()){
            if(location.getLocationID().equals(locationID)){
                location.addOccupant(occupant);
            }
        }
        //need personLocation table in database to have this association
        database.modifyPersonLocationColumnLocationID(personID, "location_id", locationID);
    }
    

}
