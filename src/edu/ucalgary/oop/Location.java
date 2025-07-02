package edu.ucalgary.oop;
import java.util.ArrayList;


public class Location {
    private String name;
    private String address;
    private ArrayList<Person> occupants;
    private String locationID;
    

    public Location(){

    }

    public Location(String name, String address){
        this.name = name;
        this.address = address;
    }

    public Location(String name, String address, String locationID){
        this.name = name;
        this.address = address;
        this.locationID = locationID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setLocationID(String locationID){
        this.locationID = locationID;
    }
    public String getLocationID(){
        return this.locationID;
    }
    public void addOccupant(Person person){
        if(occupants == null){
            occupants = new ArrayList<Person>();
        }
        occupants.add(person);
    }

    public void removeOccupant(Person person){
        occupants.remove(person);
    }

    public void setOccupants(ArrayList<Person> occupants){
        this.occupants = occupants;
    }

    public ArrayList<Person> getOccupants(){
        return this.occupants;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }
}
