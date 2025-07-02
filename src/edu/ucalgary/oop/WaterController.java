package edu.ucalgary.oop;


public class WaterController {

    private DatabaseAccess database;
    private Water model;

    public WaterController(DatabaseAccess dBType){
        this.database = dBType;
    }
    public void modifyLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
        for(Water water : UserInterface.getWaters()){
            if(water.getSupplyID().equals(supplyID)){
                Location locationAllocatedTo = null;
                for(Location location: UserInterface.getLocations()){
                    if(location.getLocationID().equals(newLocationID)){
                        locationAllocatedTo = location;
                        break;
                    }
                }
                water.setLocationAllocatedTo(locationAllocatedTo);
                water.setPersonAllocatedTo(null);
            }
        }
        database.modifyWaterLocationAllocatedTo(supplyID, locationColumn, newLocationID);
    
}

public void modifyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
    for(Water water : UserInterface.getWaters()){
        if(water.getSupplyID().equals(supplyID)){
            Person personAllocatedTo = null;
            for(Person person: UserInterface.getPeople()){
                if(person.getPersonID().equals(newPersonID)){
                    personAllocatedTo = person;
                    break;
                }
            }
            water.setPersonAllocatedTo(personAllocatedTo);
            water.setLocationAllocatedTo(null);
        }
    }
    database.modifyWaterPersonAllocatedTo(supplyID, personColumn, newPersonID);
}


public void addWater(String type, String dateAllocated, String personID, String locationID){
    int newSupplyID = database.queryForCurrentIDInTable("Supply", "supply_id") + 1;
    String supplyID = Integer.toString(newSupplyID);
    Water water = new Water(1, dateAllocated, supplyID);

    if(!(personID.equals(""))){
        Person personAllocatedTo = null;
        for(Person person: UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                personAllocatedTo = person;
                break;
            }
        }
        water.setPersonAllocatedTo(personAllocatedTo);
        locationID = "0";
    } else{
        Location locationAllocatedTo = null;
                for(Location location: UserInterface.getLocations()){
                    if(location.getLocationID().equals(locationID)){
                        locationAllocatedTo = location;
                        break;
                    }
                }
        water.setLocationAllocatedTo(locationAllocatedTo);
        personID = "0";
    }

    UserInterface.getWaters().add(water);
    UserInterface.getSupplies().add(water);
    String comments = dateAllocated;
    database.insertNewSupply(type, comments, supplyID);

    database.insertNewSupplyAllocation(supplyID, personID, locationID);

}
}
