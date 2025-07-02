package edu.ucalgary.oop;
/**This class allows the user to view certain tables form the database
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/

import java.sql.*;

public class DatabaseView {

/**
 * view the medical record table in the database
 */
    public static void viewMedicalRecordTable(){
       StringBuffer medicalRecordTable = new StringBuffer();
       Connection dBConnect = DatabaseConnection.getDBConnect();
        medicalRecordTable.append(String.format("%-20s%-20s%-20s%-20s%-20s\n", "medicalRecordID", "locationID", "personID", "dateOfTreatment", "treatmentDetails"));
        medicalRecordTable.append("\n");
            try {                    
            Statement myStmt = dBConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM MedicalRecord");
            
            while (results.next()){
                medicalRecordTable.append(String.format("%-20s", results.getInt("medical_record_id")));
                medicalRecordTable.append(String.format("%-20s", results.getInt("location_id")));
                medicalRecordTable.append(String.format("%-20s", results.getInt("person_id")));
                medicalRecordTable.append(String.format("%-20s", results.getDate("date_of_treatment")));
                medicalRecordTable.append(String.format("%-20s", results.getString("treatment_details")));
                medicalRecordTable.append('\n');
            }
            
            myStmt.close();
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(medicalRecordTable.toString());
    }    

/**
 * view the personLcoation table as it is in the database
 */
    public static void viewPersonLocationTable(){

        StringBuffer personLocationTable = new StringBuffer();
        Connection dBConnect = DatabaseConnection.getDBConnect();
        personLocationTable.append(String.format("%-20s%-20s\n","personID", "locationID"));
        personLocationTable.append("\n");
             try {                    
             Statement myStmt = dBConnect.createStatement();
             ResultSet results = myStmt.executeQuery("SELECT * FROM PersonLocation");
             
             while (results.next()){
                personLocationTable.append(String.format("%-20s", results.getInt("person_id")));
                personLocationTable.append(String.format("%-20s", results.getInt("location_id")));
                personLocationTable.append('\n');
             }
             
             myStmt.close();
             results.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         System.out.println(personLocationTable.toString());

    }
/** view the inquiry table as it is in the database
 */
    public static void viewInquiryTable(){
        StringBuffer inquiryTable = new StringBuffer();
        Connection dBConnect = DatabaseConnection.getDBConnect();
         inquiryTable.append(String.format("%-20s%-20s%-20s%-20s%-20s%-20s\n", "inquiryID", "inquirerID", "seekingID", "locationID", "dateOfInquiry", "comments"));
         inquiryTable.append("\n");
             try {                    
             Statement myStmt = dBConnect.createStatement();
             ResultSet results = myStmt.executeQuery("SELECT * FROM Inquiry");
             
             while (results.next()){
                inquiryTable.append(String.format("%-20s", results.getInt("inquiry_id")));
                inquiryTable.append(String.format("%-20s", results.getInt("inquirer_id")));
                inquiryTable.append(String.format("%-20s", results.getInt("seeking_id")));
                inquiryTable.append(String.format("%-20s", results.getInt("location_id")));
                inquiryTable.append(String.format("%-20s", results.getDate("date_of_inquiry")));
                inquiryTable.append(String.format("%-20s", results.getString("comments")));
                inquiryTable.append('\n');
             }
             
             myStmt.close();
             results.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         System.out.println(inquiryTable.toString());
    }

/**
 * view the location table as it is in the database
 */
    public static void viewLocationTable(){
        StringBuffer locationTable = new StringBuffer();
        Connection dBConnect = DatabaseConnection.getDBConnect();
        locationTable.append(String.format("%-20s%-30s%-20s\n", "locationID", "name", "address"));
        locationTable.append("\n");
             try {                    
             Statement myStmt = dBConnect.createStatement();
             ResultSet results = myStmt.executeQuery("SELECT * FROM Location");
             
             while (results.next()){
                locationTable.append(String.format("%-20s", results.getInt("location_id")));
                locationTable.append(String.format("%-30s", results.getString("name")));
                locationTable.append(String.format("%-20s", results.getString("address")));
                locationTable.append('\n');
             }
             
             myStmt.close();
             results.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         System.out.println(locationTable.toString());

    }

/** view the person tble as it is in the database */
    public static void viewPersonTable(){
        StringBuffer personTable = new StringBuffer();
        Connection dBConnect = DatabaseConnection.getDBConnect();
         personTable.append(String.format("%-10s%-15s%-20s%-15s%-20s%-20s%-12s%-12s\n", "personID", "firstName", "last_name", "dateOfBirth", "gender", "comments","phoneNumber", "familyGroupNumber"));
         personTable.append("\n");
             try {                    
             Statement myStmt = dBConnect.createStatement();
             ResultSet results = myStmt.executeQuery("SELECT * FROM Person");
             
             while (results.next()){
                personTable.append(String.format("%-10s", results.getInt("person_id")));
                personTable.append(String.format("%-15s", results.getString("first_name")));
                personTable.append(String.format("%-20s", results.getString("last_name")));
                personTable.append(String.format("%-15s", results.getDate("date_of_birth")));
                personTable.append(String.format("%-20s", results.getString("gender")));
                personTable.append(String.format("%-20s", results.getString("comments")));
                personTable.append(String.format("%-12s", results.getString("phone_number")));
                personTable.append(String.format("%-12s", results.getInt("family_group")));
                personTable.append('\n');
             }
             
             myStmt.close();
             results.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         System.out.println(personTable.toString());
    }

    /**
     * view the supply table as it is in the database
     */
    public static void viewSupplyTable(){
        StringBuffer supplyTable = new StringBuffer();
        Connection dBConnect = DatabaseConnection.getDBConnect();
         supplyTable.append(String.format("%-20s%-20s%-20s\n", "supplyID", "type", "comments"));
         supplyTable.append("\n");
             try {                    
             Statement myStmt = dBConnect.createStatement();
             ResultSet results = myStmt.executeQuery("SELECT * FROM Supply");
             
             while (results.next()){
                supplyTable.append(String.format("%-20s", results.getInt("supply_id")));
                supplyTable.append(String.format("%-20s", results.getString("type")));
                supplyTable.append(String.format("%-20s", results.getString("comments")));
                supplyTable.append('\n');
             }
             
             myStmt.close();
             results.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         System.out.println(supplyTable.toString());
    }
    
    /** 
     * view the supplyalloacation as it is in the database
     */
    public static void viewSupplyAllocationTable(){
        StringBuffer supplyAllocationTable = new StringBuffer();
        Connection dBConnect = DatabaseConnection.getDBConnect();
         supplyAllocationTable.append(String.format("%-20s%-20s%-20s%-20s\n", "supplyID", "personID", "locationID", "allocationDate"));
         supplyAllocationTable.append("\n");
             try {                    
             Statement myStmt = dBConnect.createStatement();
             ResultSet results = myStmt.executeQuery("SELECT * FROM SupplyAllocation");
             
             while (results.next()){
                supplyAllocationTable.append(String.format("%-20s", results.getInt("supply_id")));
                supplyAllocationTable.append(String.format("%-20s", results.getInt("person_id")));
                supplyAllocationTable.append(String.format("%-20s", results.getInt("location_id")));
                supplyAllocationTable.append(String.format("%-20s", results.getDate("allocation_date")));
    
                supplyAllocationTable.append('\n');
             }
             
             myStmt.close();
             results.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         System.out.println(supplyAllocationTable.toString());
    }
}
