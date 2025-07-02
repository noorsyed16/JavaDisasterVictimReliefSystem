package edu.ucalgary.oop;
/**This class is a blanket controller which controls modification and addition of Blanket objects in the 
 * database and program 
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/

public class BlanketController {

    private DatabaseAccess database;
    private Blanket model;
    /**
     * This constructor takes in the DBType which enables the class to pick between using the mock 
     * database for tests and queryTheDatabase for the regular program
     * @param dBType specfies the class to use as an implementation of the interface DatabaseAccess
     */
    public BlanketController(DatabaseAccess dBType){
        this.database = dBType;
    }
    /**
     * modifies the location teh blanket is allocated to by changing it in the program and calling a database 
     * function to make the change in the databse as well
     * @param supplyID is the supplyID corresponding to the supply object being modified
     * @param locationColumn is the name of the coulmn we are modifying in the database
     * @param newLocationID is the id of the new location we will allocate to
     */
    public void modifyLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
        for(Blanket blanket : UserInterface.getBlankets()){
            if(blanket.getSupplyID().equals(supplyID)){
                Location locationAllocatedTo = null;
                for(Location location: UserInterface.getLocations()){
                    if(location.getLocationID().equals(newLocationID)){
                        locationAllocatedTo = location;
                        break;
                    }
                }
                blanket.setLocationAllocatedTo(locationAllocatedTo);
                blanket.setPersonAllocatedTo(null);
            }
            
        }
        database.modifyBlanketLocationAllocatedTo(supplyID, locationColumn, newLocationID);
        
    }
    /**
     * modifies the person the blanket is allocated to in the program and calls a database function
     * @param supplyID is the supplyID corresponding to the supply object being modified
     * @param personColumn is the name of the coulmn we are modifying in the database
     * @param newPersonID is the id of the new location we will allocate the blanket to
     */
    public void modifyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        for(Blanket blanket : UserInterface.getBlankets()){
            if(blanket.getSupplyID().equals(supplyID)){
                Person personAllocatedTo = null;
                for(Person person: UserInterface.getPeople()){
                    if(person.getPersonID().equals(newPersonID)){
                        personAllocatedTo = person;
                        break;
                    }
                }
                blanket.setPersonAllocatedTo(personAllocatedTo);
                blanket.setLocationAllocatedTo(null);
            }
        }
        database.modifyBlanketPersonAllocatedTo(supplyID, personColumn, newPersonID);
    }

    /**
     * Adds a blanket type object to the program and the database
     * @param type is type of supply
     * @param comments comments of the supply stored in the database
     * @param personID person the supply will be allocated to
     * @param locationID location supply will be allocated to
     */
    public void addBlanket(String type, String comments, String personID, String locationID){
        int newBlanketID = database.queryForCurrentIDInTable("Supply", "supply_id") + 1;
        String supplyID = Integer.toString(newBlanketID);
        Blanket blanket = new Blanket(1, supplyID);

        if(!(personID.equals(""))){
            Person personAllocatedTo = null;
            for(Person person: UserInterface.getPeople()){
                if(person.getPersonID().equals(personID)){
                    personAllocatedTo = person;
                    break;
                }
            }
            blanket.setPersonAllocatedTo(personAllocatedTo);
            locationID = "0";
        } else{
            Location locationAllocatedTo = null;
                for(Location location: UserInterface.getLocations()){
                    if(location.getLocationID().equals(locationID)){
                        locationAllocatedTo = location;
                        break;
                    }
                }
            blanket.setLocationAllocatedTo(locationAllocatedTo);
            personID = "0";
        }

        UserInterface.getBlankets().add(blanket);
        UserInterface.getSupplies().add(blanket);
    
        database.insertNewSupply(type, comments, supplyID);

        database.insertNewSupplyAllocation(supplyID, personID, locationID);

    }
    
    
}
