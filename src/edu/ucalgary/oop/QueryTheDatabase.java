package edu.ucalgary.oop;

import java.sql.*;

public class QueryTheDatabase implements DatabaseAccess{
    private static Connection dBConnect = DatabaseConnection.getDBConnect();

    @Override
    public int queryForCurrentIDInTable(String tableName, String tableID){
        //need the id that was assigned to the medicalRecord
        String selectLast = "SELECT * FROM " + tableName + " ORDER BY " + tableID + " DESC LIMIT 1";
        int id = 0;
        try{
            PreparedStatement selectStmt = dBConnect.prepareStatement(selectLast);
        
            ResultSet resultSet = selectStmt.executeQuery();
    
                if (resultSet.next()) {
                    id = resultSet.getInt(tableID);
                }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return id;
    }

    @Override
    public void insertNewPerson(String firstName, String lastName, String dateOfBirth, String gender, String comments, String phoneNumber, int intGroupNumber){
                //need to create it in the database
                String insertQuery = "INSERT INTO Person (first_name, last_name, date_of_birth, gender, comments, phone_number, family_group) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                Date dateTypeOfBirth = Date.valueOf(dateOfBirth);
        
                try {
                    PreparedStatement query = dBConnect.prepareStatement(insertQuery);
                    // if i go from the databse it would take this and instead by person.getFirstName and stuff so it makes it from
                    // model rather than from input?
                    query.setString(1, firstName);
                    query.setString(2, lastName);
                    query.setDate(3, dateTypeOfBirth);
                    query.setString(4, gender);
                    query.setString(5, comments);
                    query.setString(6, phoneNumber);
        
                    query.setInt(7, intGroupNumber);
                    
        
                    int rowsInserted = query.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new person was added successfully!");
                    }
        
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }

    @Override
    public void insertNewLocation(String locationName, String locationAddress){
        String insertQuery = "INSERT INTO Location (name, address) " + "VALUES (?, ?)";
        Connection dBConnect = DatabaseConnection.getDBConnect();

        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);

            query.setString(1, locationName);
            query.setString(2, locationAddress);
            
            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new location was added successfully!");
            }
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertNewPersonLocationAssociation(String personID, String locationID){
        String insertQuery = "INSERT INTO PersonLocation (person_id, location_id) " + "VALUES (?, ?)";
        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);
            query.setInt(1, Integer.parseInt(personID)); 
            query.setInt(2, Integer.parseInt(locationID)); 

            
            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new personLocation Association was added successfully!");
            }

            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertNewMedicalRecord(String locationID, String personID, String dateOfTreatement,String treatmentDetails){
        String insertQuery = "INSERT INTO MedicalRecord (location_id, person_id, date_of_treatment, treatment_details) " + "VALUES (?, ?, ?, ?)";
        Date dateTypeOfTreatment = Date.valueOf(dateOfTreatement);

        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);

            query.setInt(1, Integer.parseInt(locationID));
            query.setInt(2,  Integer.parseInt(personID));
            query.setDate(3, dateTypeOfTreatment);
            query.setString(4, treatmentDetails);

            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new medicalRecord was added successfully!");
            }

            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertNewInquiry(String inquirerID, String seekingID, String locationID, String dateOfInquiry, String comments){
        String insertQuery = "INSERT INTO Inquiry (inquirer_id, seeking_id, location_id, date_of_inquiry, comments) " + "VALUES (?, ?, ?, ?, ?)";


        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);

            query.setInt(1, Integer.parseInt(inquirerID));
            query.setInt(2, Integer.parseInt(seekingID));
            query.setInt(3, Integer.parseInt(locationID));
            query.setDate(4, Date.valueOf(dateOfInquiry));
            query.setString(5, comments);
            
            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new inquiry was added successfully!");
            }
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void modifyPersonColumn(String personID, String columnName, String newValue){
        try {
            String query = "UPDATE Person SET " + columnName + " = ? WHERE person_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            if ((columnName.equals("first_name")) || (columnName.equals("last_name")) || (columnName.equals("gender")) || (columnName.equals("comments")) || (columnName.equals("phone_number"))) {
                stmt.setString(1, newValue);
            } else if (columnName.equals("date_of_birth")) {
                stmt.setDate(1, Date.valueOf(newValue)); 
            } else if (columnName.equals("family_group")) {
                stmt.setInt(1, Integer.parseInt(newValue)); 
            } 
            
            stmt.setInt(2, Integer.parseInt(personID));

            stmt.executeUpdate();
    
            // Close the statement
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyLocationColumn(String locationID, String columnName, String newValue) {
        try {
            String query = "UPDATE Location SET " + columnName + " = ? WHERE location_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setString(1, newValue);
            stmt.setInt(2, Integer.parseInt(locationID));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifyInquiryColumn(String inquiryID, String columnName, String newValue) {
        Connection dBConnect = DatabaseConnection.getDBConnect();
        try {
            String query = "UPDATE Inquiry SET " + columnName + " = ? WHERE inquiry_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);

            if(columnName.equals("date_of_inquiry")){
                stmt.setDate(1, Date.valueOf(newValue)); 
            }
            else if(columnName.equals("comments")){
                stmt.setString(1, newValue); 
            }
            else{
                stmt.setInt(1, Integer.parseInt(newValue));
            }

            stmt.setInt(2, Integer.parseInt(inquiryID));
            

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyMedicalRecordColumn(String medicalRecordID, String columnName, String newValue) {
        Connection dBConnect = DatabaseConnection.getDBConnect();
        try {
            String query = "UPDATE MedicalRecord SET " + columnName + " = ? WHERE medical_record_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            if (columnName.equals("treatment_details")) {
                stmt.setString(1, newValue);
            } else if (columnName.equals("date_of_treatment")) {
                stmt.setDate(1, Date.valueOf(newValue)); 
            } else {
                stmt.setInt(1, Integer.parseInt(newValue));
            }
            
            stmt.setInt(2, Integer.parseInt(medicalRecordID));

            stmt.executeUpdate();
    
            // Close the statement
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyPersonLocationColumnLocationID(String personID, String columnName, String locationID){
        try {
            String query = "UPDATE PersonLocation SET " + columnName + " = ? WHERE person_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setInt(1, Integer.parseInt(locationID));
            stmt.setInt(2, Integer.parseInt(personID));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifySupplyColumn(String supplyID, String columnName, String newDescription){
        //QueryTheDatabase.modifySupplyColumn(supplyID, columnName, newDescription);
        try {
            String query = "UPDATE Supply SET " + columnName + " = ? WHERE supply_id = ?";
            PreparedStatement stmt = dBConnect.prepareStatement(query);
            stmt.setString(1, newDescription);
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            // Close the statement
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifySupplyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        try {
            String query = "UPDATE SupplyAllocation SET " + personColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setInt(1, Integer.parseInt(newPersonID));
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifySupplyLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
    
        try {
            String query = "UPDATE SupplyAllocation SET " + locationColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            if(newLocationID.equals("")){
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, Integer.parseInt(newLocationID));
            }
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyBlanketLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
        try {
            String query = "UPDATE SupplyAllocation SET " + locationColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            if(newLocationID.equals("")){
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, Integer.parseInt(newLocationID));
            }
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyBlanketPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        try {
            String query = "UPDATE SupplyAllocation SET " + personColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setInt(1, Integer.parseInt(newPersonID));
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyPersonalBelongingLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
        try {
            String query = "UPDATE SupplyAllocation SET " + locationColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setInt(1, Integer.parseInt(newLocationID));
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifyPersonalBelongingPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        try {
            String query = "UPDATE SupplyAllocation SET " + personColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setInt(1, Integer.parseInt(newPersonID));
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyPersonalBelongingDescription(String supplyID, String columnName, String newValue) {
        try {
            String query = "UPDATE Supply SET " + columnName + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            

            stmt.setString(1, newValue);
            
            stmt.setInt(2, Integer.parseInt(supplyID));

            stmt.executeUpdate();
    
            // Close the statement
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyWaterLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID){
        try {
            String query = "UPDATE SupplyAllocation SET " + locationColumn + " = ? WHERE supply_id = ?";

            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            if(newLocationID.equals("")){
                stmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(1, Integer.parseInt(newLocationID));
            }
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void modifyWaterPersonAllocatedTo(String supplyID, String personColumn, String newPersonID){
        try {
            String query = "UPDATE SupplyAllocation SET " + personColumn + " = ? WHERE supply_id = ?";
    
            PreparedStatement stmt = dBConnect.prepareStatement(query);
            
            //set new value depending on column
            stmt.setInt(1, Integer.parseInt(newPersonID));
            stmt.setInt(2, Integer.parseInt(supplyID));
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertNewSupply(String type, String comments, String supplyID){
        String insertQuery = "INSERT INTO Supply (supply_id, type, comments) " + "VALUES (?, ?, ?)";

        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);

            query.setInt(1, Integer.parseInt(supplyID));
            query.setString(2, type);
            query.setString(3, comments);
            
            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new supply was added successfully!");
            }
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insertNewSupplyAllocation(String supplyID, String personID, String locationID){
        String insertQuery = "INSERT INTO SupplyAllocation (supply_id, person_id, location_id) " + "VALUES (?, ?, ?)";

        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);

            query.setInt(1, Integer.parseInt(supplyID));

            if(personID.equals("0")){
                query.setNull(2, java.sql.Types.INTEGER);
            } else {
                query.setInt(2, Integer.parseInt(personID));
            }

            if(locationID.equals("0")){
                query.setNull(3, java.sql.Types.INTEGER);
            } else{
                query.setInt(3, Integer.parseInt(locationID));
            }
            
            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new supplyAllocation was added successfully!");
            }
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insertNewSupply(String type, String comments){
    String insertQuery = "INSERT INTO Supply (type, comments) " + "VALUES (?, ?)";

        try {
            PreparedStatement query = dBConnect.prepareStatement(insertQuery);

            query.setString(1, type);
            query.setString(2, comments);

            int rowsInserted = query.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Supply was added successfully!");
            }
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNextSupplyID(){
        String supplyID = null;

            //need the id that was assigned to them
            String selectLast = "SELECT * FROM Supply ORDER BY supply_id DESC LIMIT 1";
            try{
                PreparedStatement selectStmt = dBConnect.prepareStatement(selectLast);
            
                ResultSet resultSet = selectStmt.executeQuery();

                if (resultSet.next()) {
                    supplyID = resultSet.getString("supply_id");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return supplyID;
    }



}



