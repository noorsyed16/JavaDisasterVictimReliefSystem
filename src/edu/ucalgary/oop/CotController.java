package edu.ucalgary.oop;
/**This class represnts a controller for a cot type object 
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/

public class CotController {

    private DatabaseAccess database;
    private Cot model;

    public CotController(DatabaseAccess dBType){
        this.database = dBType;
    }
    /**
     * modifies the location teh cot is alloated to in the program and database
     * @param supplyID id of the cot from the database
     * @param locationColumn name of the column being modified in the database
     * @param newLocationID id of the new location we want to allocate it to
     */
    public void modifyLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
        
        for(Cot cot : UserInterface.getCots()){
            if(cot.getSupplyID().equals(supplyID)){
                //need the Location object with corresponding locationid
                Location locationAllocatedTo = null;
                for(Location location: UserInterface.getLocations()){
                    if(location.getLocationID().equals(newLocationID)){
                        locationAllocatedTo = location;
                        break;
                    }
                }
                cot.setLocationAllocatedTo(locationAllocatedTo);
                cot.setPersonAllocatedTo(null);
            }
        }
        database.modifySupplyLocationAllocatedTo(supplyID, locationColumn, newLocationID);
    }
    /**
     * modifies the person the cot is allocated to in the program and in the database
     * @param supplyID is teh id of the cot object
     * @param personColumn is the name of the person column being changed in the database
     * @param newPersonID is the new person we want to allocate to's id
     */
    public void modifyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        for(Cot cot : UserInterface.getCots()){
            //need Person with that id
            Person personAllocatedTo = null;
            for(Person person: UserInterface.getPeople()){
                if(person.getPersonID().equals(newPersonID)){
                    personAllocatedTo = person;
                    break;
                }
            }
            if(cot.getSupplyID().equals(supplyID)){
                cot.setPersonAllocatedTo(personAllocatedTo);
                cot.setLocationAllocatedTo(null);
            }
        }
        database.modifySupplyPersonAllocatedTo(supplyID, personColumn, newPersonID);
        
    }
    /**
     * modifies the cot's room number in the program and database
     * @param supplyID is the id of the cot
     * @param columnName is the name of the column being modified 
     * @param newRoomNumber is the new roomNumebr to modify it to
     */
    public void modifyCotRoomNumber(String supplyID, String columnName, String newRoomNumber){
        //want to take the current description, split it on the space, modify, and rejoin for database
        String descriptionGrid = null;
        for(Cot cot : UserInterface.getCots()){
            if(cot.getSupplyID().equals(supplyID)){
                descriptionGrid = cot.getGrid();
                cot.setRoomNumber(newRoomNumber);
            }
        }
        String newDescription = newRoomNumber.strip() + " " + descriptionGrid.strip();

        //now make the change in the database with the newDescription
        database.modifySupplyColumn(supplyID, columnName, newDescription);
    }
    

    /**
     * modifies the grid of the cot
     * @param supplyID is the id of the cot
     * @param columnName is the name of the column being modified
     * @param grid is the grid it is being modified to
     */
    public void modifyCotGrid(String supplyID, String columnName, String grid) {
        //same idea as room number
        String descriptionRoomNum = null;
        for(Cot cot : UserInterface.getCots()){
            if(cot.getSupplyID().equals(supplyID)){
                descriptionRoomNum = cot.getRoomNumber();
                cot.setGrid(grid);
            }
        }

        String newDescription = descriptionRoomNum.strip() + " " + grid.strip();
        database.modifySupplyColumn(supplyID, columnName, newDescription);
    }
    /**
     * add a new cot to the program and teh datatbase
     * @param type type of supply
     * @param roomNumber room number it is stored in
     * @param grid grid taht the cot has
     * @param personID person id it is allocated to
     * @param locationID location id it is allocated to
     */
    public void addCot(String type, String roomNumber, String grid, String personID, String locationID){
        int newBlanketID = database.queryForCurrentIDInTable("Supply", "supply_id") + 1;
        String supplyID = Integer.toString(newBlanketID);
        Cot cot = new Cot(1, roomNumber, grid, supplyID);

        if(!(personID.equals(""))){
            Person personAllocatedTo = null;
            for(Person person: UserInterface.getPeople()){
                if(person.getPersonID().equals(personID)){
                    personAllocatedTo = person;
                    break;
                }
            }
            cot.setPersonAllocatedTo(personAllocatedTo);
            locationID = "0";
        } else{
            Location locationAllocatedTo = null;
                for(Location location: UserInterface.getLocations()){
                    if(location.getLocationID().equals(locationID)){
                        locationAllocatedTo = location;
                        break;
                    }
                }
            cot.setLocationAllocatedTo(locationAllocatedTo);
            personID = "0";
        }

        UserInterface.getCots().add(cot);
        UserInterface.getSupplies().add(cot);
        String comments = roomNumber.strip() + " " + grid.strip();
        
        database.insertNewSupply(type, comments, supplyID);

        database.insertNewSupplyAllocation(supplyID, personID, locationID);

    }
}
