package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;
    private String medicalRecordID;
    private String personID;
    private String locationID;

    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment, String medicalRecordID){
        this.location = location;
        this.treatmentDetails = treatmentDetails;
        this.dateOfTreatment = dateOfTreatment;
        this.medicalRecordID = medicalRecordID;
    }



    public MedicalRecord(){

    }

    
    public boolean isValidDate(String date) {
        // should be year-month-day
		Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher dateMatcher = datePattern.matcher(date);
		return dateMatcher.find();
	}

    public void setLocation(Location location){
        this.location = location;
    }

    public void setTreatmentDetails(String treatmentDetails){
        this.treatmentDetails = treatmentDetails;
    }

    public boolean setDateOfTreatment(String dateOfTreatment){
        if(isValidDate(dateOfTreatment)){
            this.dateOfTreatment = dateOfTreatment;
            return true;
        }
        else{
            return false;
        }
    }

    public Location getLocation(){
        return this.location;
    }

    public String getTreatmentDetails(){
        return this.treatmentDetails;
    }

    public String getDateOfTreatment(){
        return this.dateOfTreatment;
    }

    public String getMedicalRecordID(){
        return this.medicalRecordID;
    }
    public void setMedicalRecordID(String medicalRecordID){
        this.medicalRecordID = medicalRecordID;
    }
    public void setPersonID(String personID){
        this.personID = personID;
    }
    public String getPersonID(){
        return this.personID;
    }

    public void setLocationID(String locationID){
        this.locationID = locationID;
    }
    public String getLocationID(){
        return this.locationID;
    }


    
}
