package edu.ucalgary.oop;


public class PersonalBelongingController {
    private DatabaseAccess database;
    private PersonalBelonging model;

    public PersonalBelongingController(DatabaseAccess dBType){
        this.database = dBType;
    }
    public void modifyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        for(PersonalBelonging item : UserInterface.getPersonalBelongings()){
            if(item.getSupplyID().equals(supplyID)){
                Person personAllocatedTo = null;
                for(Person person: UserInterface.getPeople()){
                    if(person.getPersonID().equals(newPersonID)){
                        personAllocatedTo = person;
                        break;
                    }
                }
                item.setPersonAllocatedTo(personAllocatedTo);
            }
        }
        database.modifyPersonalBelongingPersonAllocatedTo(supplyID, personColumn, newPersonID);
    }

    public void modifyPersonalBelongingDescription(String supplyID, String columnName, String newValue) {
        for(PersonalBelonging item : UserInterface.getPersonalBelongings()){
            if(item.getSupplyID().equals(supplyID)){
                item.setDescription(newValue);
            }
        }
        database.modifyPersonalBelongingDescription(supplyID, columnName, newValue);

    }

    public void addPersonalBelonging(String type, String comments, String personID){
        int newSupplyID = database.queryForCurrentIDInTable("Supply", "supply_id") + 1;
        String supplyID = Integer.toString(newSupplyID);
        PersonalBelonging personalBelonging = new PersonalBelonging(1, comments, supplyID);
        Person personAllocatedTo = null;
        for(Person person: UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                personAllocatedTo = person;
                break;
            }
        }
        personalBelonging.setPersonAllocatedTo(personAllocatedTo);

        UserInterface.getPersonalBelongings().add(personalBelonging);
        UserInterface.getSupplies().add(personalBelonging);
    
        database.insertNewSupply(type, comments, supplyID);

        database.insertNewSupplyAllocation(supplyID, personID, "0");

    }
}


