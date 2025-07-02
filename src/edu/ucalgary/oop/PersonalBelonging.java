package edu.ucalgary.oop;

public class PersonalBelonging extends Supply{

    private String description;

    public PersonalBelonging(){
        super();
    }

    public PersonalBelonging(int quantity, String description, String supplyID){
        super("personal item", quantity, supplyID);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
