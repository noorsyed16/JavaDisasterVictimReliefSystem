package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class LoadFromDatabase {
    private static Connection dBConnect; 


    public LoadFromDatabase(){}
    public static void loadFullDatabase(){
        dBConnect = DatabaseConnection.getDBConnect();
        loadSupplies();
        loadPeople();
        loadLocations();
        loadMedicalRecords();
        loadInquiries();
        loadPersonLocationAssociation();
        loadSupplyAllocationAssociation();
    }
    public static void deleteExpiredWater() {
        try {
            Statement myStmt = dBConnect.createStatement();
            String deleteAllocationQuery = "DELETE FROM SupplyAllocation WHERE location_id IS NULL AND allocation_date < CURRENT_TIMESTAMP - INTERVAL '1 day' AND supply_ID IN (SELECT supply_id FROM Supply WHERE type = 'water')";
            myStmt.executeUpdate(deleteAllocationQuery);

            String query = "DELETE FROM Supply WHERE supply_id NOT IN (SELECT supply_id FROM SupplyAllocation) AND type = 'water'";
            myStmt.executeUpdate(query);
            myStmt.close();
           
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadSupplies(){
        //load supplies
        deleteExpiredWater();
        UserInterface.setCots(new ArrayList<Cot>());
        UserInterface.setWaters(new ArrayList<Water>());
        UserInterface.setPersonalBelongings(new ArrayList<PersonalBelonging>());
        UserInterface.setBlankets(new ArrayList<Blanket>());
        UserInterface.setSupplies(new ArrayList<Supply>());
        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Supply");

            while(results.next()){

                // for all we want to make a new Person, add its attributes, then add to the list
                String supplyID = (results.getString("supply_id"));
                String type = (results.getString("type"));
                String comments = (results.getString("comments"));
                
                if(type.equals("cot")){
                    Cot cot = new Cot();
                    cot.setQuantity(1);
                    cot.setType(type);
                    cot.setSupplyID(supplyID);
                    String[] temp = comments.split(" ");
                    cot.setRoomNumber(temp[0]);
                    cot.setGrid(temp[1]);

                    UserInterface.getCots().add(cot);
                    UserInterface.getSupplies().add(cot);

                    //create a cot and add it
                }
                else if (type.equals("water")) {
                    Water water = new Water();
                    water.setQuantity(1);
                    water.setSupplyID(supplyID);
                    water.setType(type);
                
                    String sql = "SELECT allocation_date FROM SupplyAllocation WHERE supply_id = ?";
                    PreparedStatement allocationStmt = dBConnect.prepareStatement(sql);
                    
                    // Convert supplyID to integer before passing it to the query
                    allocationStmt.setInt(1, Integer.parseInt(supplyID)); 
                
                    ResultSet waterResults = allocationStmt.executeQuery();
                    if (waterResults.next()) {
                        String allocationDate = waterResults.getString("allocation_date");
                        water.setDateAllocated(allocationDate);
                    }
                
                    UserInterface.getWaters().add(water);
                    UserInterface.getSupplies().add(water);
                
                    waterResults.close();
                    allocationStmt.close();
                
                
                } else if(type.equals("personal item")){
                    PersonalBelonging item = new PersonalBelonging();
                    item.setDescription(comments);
                    item.setSupplyID(supplyID);
                    item.setQuantity(1);
                    item.setType(type);

                    UserInterface.getPersonalBelongings().add(item);
                    UserInterface.getSupplies().add(item);

                } else if(type.equals("blanket")){
                    Blanket blanket = new Blanket();
                    blanket.setQuantity(1);
                    blanket.setSupplyID(supplyID);
                    blanket.setType(type);

                    UserInterface.getBlankets().add(blanket);
                    UserInterface.getSupplies().add(blanket);
                }
                    
                }
            results.close();
            myStmt.close();
            
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        }
    }

    public static void loadPeople(){
        //load people
        UserInterface.setPeople(new ArrayList<Person>());
        
        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Person");

            while(results.next()){
                // for all we want to make a new Person, add its attributes, then add to the list
                Person person = new Person();

                person.setFirstName(results.getString("first_name"));
                person.setLastName(results.getString("last_name"));
                person.setDateOfBirth((results.getDate("date_of_birth") == null) ? "" : results.getDate("date_of_birth").toString());
                person.setGender(results.getString("gender"));
                person.setPhoneNumber(results.getString("phone_number"));
                person.setFamilyGroupNumber(results.getInt("family_group"));
                person.setPersonID(((results.getString("person_id")) == null) ? "" : results.getString("person_id").toString());

                UserInterface.getPeople().add(person);

            }

            results.close();
            myStmt.close();
            
        } catch (SQLException ex2) {
            ex2.printStackTrace();
        }
    }

    public static void loadLocations(){
        UserInterface.setLocations(new ArrayList<Location>());

        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Location");

            while(results.next()){
                // for all we want to make a new Person, add its attributes, then add to the list
                Location location = new Location();
                location.setName(results.getString("name"));
                location.setAddress(results.getString("address"));
                location.setLocationID(((results.getString("location_id")) == null) ? "" : results.getString("location_id").toString());

                UserInterface.getLocations().add(location);

            }

            results.close();
            myStmt.close();
            
        } catch (SQLException ex3) {
            ex3.printStackTrace();
        }

    }

    public static void loadMedicalRecords(){
        
        UserInterface.setMedicalRecords(new ArrayList<MedicalRecord>());

        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM MedicalRecord");

            while(results.next()){
                // for all we want to make a new MedicalRecord, add its attributes, then add to the list
                MedicalRecord medicalRecord = new MedicalRecord();

                medicalRecord.setMedicalRecordID(((results.getString("medical_record_id")) == null) ? "" : results.getString("medical_record_id").toString());
                medicalRecord.setDateOfTreatment((results.getDate("date_of_treatment") == null) ? "" : results.getDate("date_of_treatment").toString());
                medicalRecord.setTreatmentDetails(results.getString("treatment_details"));
                
                String medicalRecordLocationID = (((results.getString("location_id")) == null) ? "" : results.getString("location_id").toString());
                //need to add Location Object to MedicalRecord, so iterate through locations we just got and add to correct one
                for(Location loc: UserInterface.getLocations()){
                    if(loc.getLocationID().equals(medicalRecordLocationID)){
                        medicalRecord.setLocation(loc);
                    }
                }

                //need to add medical record to corrcet Person if applicable
                String medicalRecordPersonID = (((results.getString("person_id")) == null) ? "" : results.getString("person_id").toString());
                for(Person person: UserInterface.getPeople()){
                    if(medicalRecordPersonID.equals(person.getPersonID())){
                        person.addMedicalRecord(medicalRecord);
                    }
                }
                UserInterface.getMedicalRecords().add(medicalRecord);

            }

            results.close();
            myStmt.close();
            
        } catch (SQLException ex4) {
            ex4.printStackTrace();
        }
    }

    public static void loadInquiries(){
        UserInterface.setInquiries(new ArrayList<Inquiry>());

        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Inquiry");

            while(results.next()){
                // for all we want to make a new MedicalRecord, add its attributes, then add to the list
                Inquiry inquiry = new Inquiry();

                inquiry.setInquiryID(results.getString("inquiry_id").toString());
                inquiry.setDateOfInquiry((results.getDate("date_of_inquiry") == null) ? "" : results.getDate("date_of_inquiry").toString());
                inquiry.setComments(results.getString("comments"));
                inquiry.setSeekingID(results.getString("seeking_id").toString());
                inquiry.setLocationID(results.getString("location_id").toString());
                
                
                //need to add inquiry to correct Person if applicable
                String inquiryInquirerID = (((results.getString("inquirer_id")) == null) ? "" : results.getString("inquirer_id").toString());
                for(Person person: UserInterface.getPeople()){
                    if(inquiryInquirerID.equals(person.getPersonID())){
                        person.addInquiry(inquiry);
                    }
                }

                inquiry.setInquirerID(inquiryInquirerID);
                
                UserInterface.getInquiries().add(inquiry);

            }
            results.close();
            myStmt.close();
            
        } catch (SQLException ex5) {
            ex5.printStackTrace();
        }
    }

    public static void loadPersonLocationAssociation(){
        //location needs a list of occupants so we need to use the PersonLocation Table
        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM PersonLocation");

            while(results.next()){

                //want to take the personID and minus 1 to get it location in our arrayList
                //then want to set that person'slocation to be , 
                // for all we want to make a new MedicalRecord, add its attributes, then add to the list
                //need to add inquiry to correct Person if applicable
                String personIDFromLoc = (((results.getString("person_id")) == null) ? "" : results.getString("person_id").toString());
                String locationToAddToID = (((results.getString("location_id")) == null) ? "" : results.getString("location_id").toString());
                Person inhabitant = null;
                for(Person person: UserInterface.getPeople()){
                    if(personIDFromLoc.equals(person.getPersonID())){
                        inhabitant = person;
                        break;
                    }
                }

                for(Location location: UserInterface.getLocations()){
                    if(locationToAddToID.equals(location.getLocationID())){
                        location.addOccupant(inhabitant);
                    }
                }
            }
            results.close();
            myStmt.close();
        
        } catch (SQLException ex5) {
            ex5.printStackTrace();
        }
    }

    public static void loadSupplyAllocationAssociation(){
        //every supply will be allocated to certain peolpe or locations
        try{
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM SupplyAllocation");

            while(results.next()){
                String personID = (((results.getString("person_id")) == null) ? "" : results.getString("person_id").toString());
                String locationID = (((results.getString("location_id")) == null) ? "" : results.getString("location_id").toString());
                String supplyID = (((results.getString("supply_id")) == null) ? "" : results.getString("supply_id").toString());
                
                for(Supply supply: UserInterface.getSupplies()){
                    if(supplyID.equals(supply.getSupplyID())){
                        Location locationAllocatedTo = null;
                        for(Location location: UserInterface.getLocations()){
                            if(location.getLocationID().equals(locationID)){
                                locationAllocatedTo = location;
                                break;
                            }
                        }
                        supply.setLocationAllocatedTo(locationAllocatedTo);

                        Person personAllocatedTo = null;
                        for(Person person: UserInterface.getPeople()){
                            if(person.getPersonID().equals(personID)){
                                personAllocatedTo = person;
                                break;
                            }
                        }
                        supply.setPersonAllocatedTo(personAllocatedTo);
                        break;
                    }
                }
            }
            results.close();
            myStmt.close();
        
        } catch (SQLException ex5) {
            ex5.printStackTrace();
        }
    }

    
}
