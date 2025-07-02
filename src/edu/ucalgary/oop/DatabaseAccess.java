package edu.ucalgary.oop;

public interface DatabaseAccess {
    //interface with all databse methods that are used in controllers so they can be tested

    public int queryForCurrentIDInTable(String tableName, String tableID);

    public void insertNewPerson(String firstName, String lastName, String dateOfBirth, String gender, String comments, String phoneNumber, int intGroupNumber);

    public void insertNewLocation(String locationName, String locationAddress);

    public void insertNewPersonLocationAssociation(String personID, String locationID);

    public void insertNewMedicalRecord(String locationID, String personID, String dateOfTreatement, String treatmentDetails);

    public void insertNewInquiry(String inquirerID, String seekingID, String locationID, String dateOfInquiry, String comments);

    public void modifyPersonColumn(String personID, String columnName, String newValue);

    public void modifyLocationColumn(String locationID, String columnName, String newValue);

    public void modifyInquiryColumn(String inquiryID, String columnName, String newValue);

    public void modifyMedicalRecordColumn(String medicalRecordID, String columnName, String newValue);

    public void modifyPersonLocationColumnLocationID(String personID, String columnName, String locationID);

    public void modifySupplyColumn(String supplyID, String columnName, String newDescription);

    public void modifySupplyPersonAllocatedTo(String supplyID, String personColumn, String newPersonID);

    public void modifySupplyLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID);

    public void modifyBlanketLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID);

    public void modifyBlanketPersonAllocatedTo(String supplyID, String personColumn, String newPersonID);

    public void modifyPersonalBelongingLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID);

    public void modifyPersonalBelongingPersonAllocatedTo(String supplyID, String personColumn, String newPersonID);

    public void modifyPersonalBelongingDescription(String supplyID, String columnName, String newValue);

    public void modifyWaterLocationAllocatedTo(String supplyID, String locationColumn, String newLocationID);

    public void modifyWaterPersonAllocatedTo(String supplyID, String personColumn, String newPersonID);

    public void insertNewSupply(String type, String comments, String supplyID);

    public void insertNewSupplyAllocation(String supplyID, String personID, String locationID);

    public void insertNewSupply(String type, String comments);

    public String getNextSupplyID();
}
