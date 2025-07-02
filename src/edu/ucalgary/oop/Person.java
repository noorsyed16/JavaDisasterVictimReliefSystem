package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int familyGroupNumber;
    private String gender;
    private String comments;
    private String phoneNumber;
    private String personID;
    private ArrayList<Inquiry> inquiries; 
    private ArrayList<MedicalRecord> medicalRecords;
    private String ENTRY_DATE;
    

    public Person(){
        this.inquiries = new ArrayList<Inquiry>();
        this.medicalRecords = new ArrayList<MedicalRecord>();
        
    }

    public Person(String firstName, String lastName, String dateOfBirth, int familyGroupNumber, String gender, String comments, String phoneNumber, String personID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.familyGroupNumber = familyGroupNumber;
        this.gender = gender;
        this.comments = comments;
        this.phoneNumber = phoneNumber;
        this.personID = personID;
        this.inquiries = new ArrayList<Inquiry>();
        this.medicalRecords = new ArrayList<>();
    }

    public ArrayList<Inquiry> getInquiries(){
        return this.inquiries;
    }

    public void setInquiries(ArrayList<Inquiry> inquiries){
        this.inquiries = inquiries;
    }

    public void addInquiry(Inquiry inquiry){
        this.inquiries.add(inquiry);
    }

    public void removeInquiry(Inquiry inquiry){
        this.inquiries.remove(inquiry);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean setDateOfBirth(String dateOfBirth) {
        if(isValidDate(dateOfBirth)){
            this.dateOfBirth = dateOfBirth;
            return true;
        }
        else{
            return false;
        }
        
    }

    public int getFamilyGroupNumber() {
        return familyGroupNumber;
    }

    public void setFamilyGroupNumber(int familyGroupNumber) {
        this.familyGroupNumber = familyGroupNumber;
    }

    public String getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        if(isValidGender(gender)){
            this.gender = gender;
            return true;
        }
        return false;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isValidDate(String date) {
        // should be year-month-day
		Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Matcher dateMatcher = datePattern.matcher(date);
		return dateMatcher.find();
	}

    public boolean isValidGender(String gender){
        if (gender == "man" || gender =="woman" || gender == "non-binary person"){
            return true;
        }
        return false;
    }

    public void setPersonID(String personID){
        this.personID = personID;
    }

    public String getPersonID(){
        return this.personID;
    }

    
    public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords){
        this.medicalRecords = medicalRecords;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord){
        if(this.medicalRecords == null){
            ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
            setMedicalRecords(medicalRecords);
        }
        this.medicalRecords.add(medicalRecord);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecords.remove(medicalRecord);
    }

    public ArrayList<MedicalRecord> getMedicalRecords(){
        return medicalRecords;
    }

    public boolean setEntryDate(String entryDate){
        if(isValidDate(entryDate)){
            this.ENTRY_DATE = entryDate;
            return true;
        }
        else{
            return false;
        } 
    }

    public String getEntryDate(){
        return this.ENTRY_DATE;
    }


}
