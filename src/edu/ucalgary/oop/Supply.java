package edu.ucalgary.oop;


public abstract class Supply {
    private String type;
    private int quantity;
    private String supplyID;
    private Person personAllocatedTo;
    private Location locationAllocatedTo;


    public Supply(){

    }

    public Supply(String type, int quantity, String supplyID){
        this.type = type;
        this.quantity = quantity;
        this.supplyID = supplyID;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setSupplyID(String supplyID){
        this.supplyID = supplyID;
    }

    public String getType(){
        return this.type;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getSupplyID(){
        return this.supplyID;
    }

    public void setPersonAllocatedTo(Person personAllocatedTo){
        this.personAllocatedTo = personAllocatedTo;
    }

    public void setLocationAllocatedTo(Location locationAllocatedTo){
        this.locationAllocatedTo = locationAllocatedTo;
    }

    public Location getLocationAllocatedTo(){
        return this.locationAllocatedTo;
    }

    public Person getPersonAllocatedTo(){
        return this.personAllocatedTo;
    }

}
