package edu.ucalgary.oop;

import java.util.ArrayList;

public class LocationController {
    private DatabaseAccess database;
    private Location model;

    public LocationController(DatabaseAccess dBType){
        this.database = dBType;
    }

    public void addLocation(String locationName, String locationAddress){
        
        int newLocationID = database.queryForCurrentIDInTable("Location", "location_id") + 1;
        String locationID = Integer.toString(newLocationID);
        Location location = new Location(locationName, locationAddress, locationID);
        UserInterface.getLocations().add(location);
    
        database.insertNewLocation(location.getName(), location.getAddress());
    }


    public ArrayList<Location> addLocationToPerson(ArrayList<Location> locations, ArrayList<Person> people, String locationID, String personID){
        // to add a location to a person, we need the location to have that person in the occupants list. 
        // in terms of the database we need the PersonLocation table to have the correct personid matching to the correct locationid
        Person occupant = null;
        for(Person person: people){
            if(personID.equals(person.getPersonID())){
                occupant = person;
            }
        }
        for(Location location : locations){
            if(locationID.equals(location.getLocationID())){
                location.addOccupant(occupant);
            }
        }

        // now for the database
        database.insertNewPersonLocationAssociation(personID, locationID);


        return locations;
    }
    public void modifyLocationName(String locationID, String columnName, String newValue){
        for(Location location : UserInterface.getLocations()){
            if(location.getLocationID().equals(locationID)){
                location.setName(newValue);
            }
        }

        //make the update in the database too
        database.modifyLocationColumn(locationID, columnName, newValue);

    }

    public void modifyLocationAddress(String locationID, String columnName, String newValue){
        for(Location location : UserInterface.getLocations()){
            if(location.getLocationID().equals(locationID)){
                location.setAddress(newValue);
            }
        }

        //make the update in the database too
        database.modifyLocationColumn(locationID, columnName, newValue);

    }
    
}
