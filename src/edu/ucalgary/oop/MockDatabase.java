package edu.ucalgary.oop;

public class MockDatabase implements DatabaseAccess {
    //not actually tetsing the database so i have hard coded return values where i need them for tests and the insert functions ar empty

    
    @Override
    public int queryForCurrentIDInTable(String tableName, String tableID) {
        return 3;
    }

    @Override
    public void insertNewPerson(String firstName, String lastName, String dateOfBirth, String gender, String comments, String phoneNumber, int intGroupNumber) {
    
    }

    @Override
    public void insertNewLocation(String locationName, String locationAddress) {
        
    }

    @Override
    public void insertNewPersonLocationAssociation(String personID, String locationID) {
        
    }

    @Override
    public void insertNewMedicalRecord(String locationID, String personID, String dateOfTreatement, String treatmentDetails) {
        
    }

    @Override
    public void insertNewInquiry(String inquirerID, String seekingID, String locationID, String dateOfInquiry, String comments) {
        
    }

    @Override
    public void modifyPersonColumn(String personID, String columnName, String newValue) {
        
    }

    @Override
    public void modifyLocationColumn(String locationID, String columnName, String newValue) {
       
    }

    @Override
    public void modifyInquiryColumn(String inquiryID, String columnName, String newValue) {
        
    }

    @Override
    public void modifyMedicalRecordColumn(String medicalRecordID, String columnName, String newValue) {
        
    }

    @Override
    public void modifyPersonLocationColumnLocationID(String personID, String columnName, String locationID) {
        
    }

    @Override
    public void modifySupplyColumn(String supplyID, String columnName, String newDescription) {
        
    }

    @Override
    public void modifySupplyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID) {
        
    }

    @Override
    public void modifySupplyLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID) {
        
    }

    @Override
    public void modifyBlanketLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID) {
        
    }

    @Override
    public void modifyBlanketPersonAllocatedTo(String supplyID, String personColumn, String newPersonID) {
        
    }

    @Override
    public void modifyPersonalBelongingLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID) {
       
    }

    @Override
    public void modifyPersonalBelongingPersonAllocatedTo(String supplyID, String personColumn, String newPersonID) {
        
    }

    @Override
    public void modifyPersonalBelongingDescription(String supplyID, String columnName, String newValue) {
    
    }

    @Override
    public void modifyWaterLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID) {
     
    }

    @Override
    public void modifyWaterPersonAllocatedTo(String supplyID, String personColumn, String newPersonID) {
        
    }

    @Override
    public void insertNewSupply(String type, String comments, String supplyID) {
        
    }

    @Override
    public void insertNewSupplyAllocation(String supplyID, String personID, String locationID) {
       
    }

    @Override
    public void insertNewSupply(String type, String comments) {
       
    }

    @Override
    public String getNextSupplyID() {
        return "1";
    }
}
