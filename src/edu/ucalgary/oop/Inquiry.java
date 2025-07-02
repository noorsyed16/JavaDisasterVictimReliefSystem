package edu.ucalgary.oop;
/**This class represnts and inquiry made by the releif worker or any pre existing ones in the database
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inquiry {
    private String inquirerID;
    private String seekingID;
    private String locationID;
    private String dateOfInquiry;
    private String comments;
    private String inquiryID;
    /**
     * default constructor for an inquiry object
     */
    public Inquiry(){
        
    }
    /**
     * Constructor which takes in values to instantiate it
     * @param inquirerID id of teh inquirer
     * @param seekingID if of who they are seeking
     * @param locationID id of teh last knwo location or location of the inquirer
     * @param dateOfInquiry dat teh inquiry was made
     * @param comments comments about the inquiry
     * @param inquiryID id of the inquiry allocated
     */
    public Inquiry(String inquirerID, String seekingID, String locationID, String dateOfInquiry, String comments, String inquiryID){
        this.inquirerID = inquirerID;
        this.seekingID = seekingID;
        this.locationID = locationID;
        this.dateOfInquiry = dateOfInquiry;
        this.comments = comments;
        this.inquiryID = inquiryID;
    }
    /**
     * getter to return inquirer id
     * @return returns inquirer id
     */
    public String getInquirerID() {
        return inquirerID;
    }
/**
 * setter to set inquirerID in the inquiry object
 * @param inquirerID id of the person makiing the inquiry
 */
    public void setInquirerID(String inquirerID) {
        this.inquirerID = inquirerID;
    }

    /**
     * getter to return the id of the perosn tehy are looking for
     * @return returns seeking id
     */
    public String getSeekingID() {
        return seekingID;
    }
    /**
     * setter to set the id of the person they are looking for
     * @param seekingID id of the person being searched for
     */
    public void setSeekingID(String seekingID) {
        this.seekingID = seekingID;
    }

    /** 
     * getter to return the location of the seeked person or where teh inquirer is making it from 
     *  @return returns the id of the location
     */
    public String getLocationID() {
        return locationID;
    }
    /**
     * setter to set location if
     * @param locationID is of the location
     */
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
    /**
     * getter to get date iqnuiry was made
     * @return returns dat of inquiry
     */
    public String getDateOfInquiry() {
        return dateOfInquiry;
    }
    /**
     * getter to get the dat the inquiry was made
     * @param dateOfInquiry dtae inquiry was made
     * @return true or false depending on if the date was in teh valod format or not
     */
    public boolean setDateOfInquiry(String dateOfInquiry) {
        if(isValidDate(dateOfInquiry)){
            this.dateOfInquiry = dateOfInquiry;
            return false;
        }
        else{
            return false;
        }
    }
/**
 * getter to get the comments made in the inquiry
 * @return comments
 */
    public String getComments() {
        return comments;
    }
    /**
     * setter to set the comments made with the inquiry
     * @param inquiryComments comments made with the inquiry
     */
    public void setComments(String inquiryComments) {
        this.comments = inquiryComments;
    }
    /**
     * getter to get the inquiry id as set in the database
     * @return teh id of the inquiry
     */
    public String getInquiryID() {
        return inquiryID;
    }
    /**
     * setter to set the id of the inquiry
     * @param inquiryID id of teh inquiry
     */
    public void setInquiryID(String inquiryID) {
        this.inquiryID = inquiryID;
    }
    /**
     * method to check if the specified date is valid
     * @param date the date we want to check if is valid
     * @return a boolean indicating if the dat is valid, true, or invalid, false
     */
    public boolean isValidDate(String date) {
        // should be year-month-day
		Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher dateMatcher = datePattern.matcher(date);
		return dateMatcher.find();
	}
}
