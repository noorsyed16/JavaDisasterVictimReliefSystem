package edu.ucalgary.oop;

import java.util.ArrayList;

public class MedicalRecordController {

    private DatabaseAccess database;
    private MedicalRecord model;

    public MedicalRecordController(DatabaseAccess dBType){
        this.database = dBType;
    }

    public ArrayList<MedicalRecord> addMedicalRecord(ArrayList<MedicalRecord> medicalRecords, Location locationAdded, String locationID, String personID, String dateOfTreatement, String treatmentDetails){
        int intMedicalRecordID = database.queryForCurrentIDInTable("MedicalRecord", "medical_record_id") + 1;
        String medicalRecordID = Integer.toString(intMedicalRecordID);

        MedicalRecord medicalRecord = new MedicalRecord(locationAdded, treatmentDetails, dateOfTreatement,medicalRecordID);
        medicalRecords.add(medicalRecord);

        //a person has a list of medical records so need to add the medical record to the list
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(personID)){
                person.addMedicalRecord(medicalRecord);
            }
        }

        database.insertNewMedicalRecord(locationID, personID, dateOfTreatement, treatmentDetails);
        return medicalRecords;

    }


    public void modifyMedicalRecordLocationID(String medicalRecordID, String columnName, String newValue){
        for(MedicalRecord medicalRecord : UserInterface.getMedicalRecords()){
            if(medicalRecord.getMedicalRecordID().equals(medicalRecordID)){
                medicalRecord.setLocationID(newValue);
            }
        }
        //make change in database too
        database.modifyMedicalRecordColumn(medicalRecordID, columnName, newValue);

    }

    public void modifyMedicalRecordPersonID(String medicalRecordID, String columnName, String newValue){
        for(MedicalRecord medicalRecord : UserInterface.getMedicalRecords()){
            if(medicalRecord.getMedicalRecordID().equals(medicalRecordID)){
                medicalRecord.setPersonID(newValue);
            }
        }
        //make change in database too
        database.modifyMedicalRecordColumn(medicalRecordID, columnName, newValue);

    }

    public void modifyMedicalRecordDateOfTreatment(String medicalRecordID, String columnName, String newValue){
        for(MedicalRecord medicalRecord : UserInterface.getMedicalRecords()){
            if(medicalRecord.getMedicalRecordID().equals(medicalRecordID)){
                medicalRecord.setDateOfTreatment(newValue);
            }
        }
        //make change in database too
        database.modifyMedicalRecordColumn(medicalRecordID, columnName, newValue);

    }

    public void modifyMedicalRecordTreatmentDetails(String medicalRecordID, String columnName, String newValue){
        for(MedicalRecord medicalRecord : UserInterface.getMedicalRecords()){
            if(medicalRecord.getMedicalRecordID().equals(medicalRecordID)){
                medicalRecord.setTreatmentDetails(newValue);
            }
        }
        //make change in database too
        database.modifyMedicalRecordColumn(medicalRecordID, columnName, newValue);

    }


}
