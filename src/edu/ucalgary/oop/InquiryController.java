package edu.ucalgary.oop;
/**This class represnts a controller that controlls an inquiry object and its relations with the program and the database
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/
public class InquiryController {
    private DatabaseAccess database;
    private Inquiry model;
    /**
     * constrcutor which specifies the mock or real database
     * @param database specfies which implementation of teh interface DatabaseAcess to use
     */
    // will take in mock dtatabse or real query the databse depending on what i pass in. 
    public InquiryController(DatabaseAccess database) {
        this.database = database;
    }
    
    /**
     * Method to add an inquiry to teh program and to the database
     * @param inquirerID id of the inquirer
     * @param seekingID id of the perosn being seeked
     * @param locationID id of the last known location or location of inquirer
     * @param dateOfInquiry date the inquiry was made
     * @param comments any comments associated with the inquiry
     */
    public void addInquiry(String inquirerID, String seekingID, String locationID, String dateOfInquiry, String comments){
        int intInquiryID = database.queryForCurrentIDInTable("Inquiry", "inquiry_id")+ 1;
        String inquiryID = Integer.toString(intInquiryID);

        Inquiry inquiry = new Inquiry(inquirerID, seekingID, dateOfInquiry, dateOfInquiry, comments, inquiryID);
        // person needs a list of inquiries
        for(Person person : UserInterface.getPeople()){
            if(person.getPersonID().equals(inquirerID)){
                person.addInquiry(inquiry);
                break;
            }
        }
        
        UserInterface.getInquiries().add(inquiry);

        database.insertNewInquiry(inquirerID, seekingID, locationID, dateOfInquiry, comments);


    }
    /**
     * method to modify the inquir's date in teh program and the database
     * @param inquiryID id of the inquiry
     * @param columnName name of the column being modified in the database
     * @param newValue new value for the date of inquiry
     */
    public void modifyInquiryDateOfInquiry(String inquiryID, String columnName, String newValue){
        for(Inquiry inquiry : UserInterface.getInquiries()){
            if(inquiry.getInquiryID().equals(inquiryID)){
                inquiry.setDateOfInquiry(newValue);
            }
        }
        //make change in database too
        database.modifyInquiryColumn(inquiryID, columnName, newValue);

    }
    /**
     * method to modify the person teh inquirer is looking fo rin the program and in the database
     * @param inquiryID id of the inquiry
     * @param columnName name of database column being modfified
     * @param newValue new value for the seekerID
     */
    public void modifyInquirySeekingID(String inquiryID, String columnName, String newValue){
        for(Inquiry inquiry : UserInterface.getInquiries()){
            if(inquiry.getInquiryID().equals(inquiryID)){
                inquiry.setSeekingID(newValue);
            }
        }
        //make change in database too
        database.modifyInquiryColumn(inquiryID, columnName, newValue);

    }
    /**
     * method to modify the location associated with the inquiry
     * @param inquiryID id of the inquiry
     * @param columnName name of the column in the database being modified
     * @param newValue new value for the locationID
     */
    public void modifyInquiryLocationID(String inquiryID, String columnName, String newValue){
        for(Inquiry inquiry : UserInterface.getInquiries()){
            if(inquiry.getInquiryID().equals(inquiryID)){
                inquiry.setLocationID(newValue);
            }
        }
        //make change in database too
        database.modifyInquiryColumn(inquiryID, columnName, newValue);

    }
    /**
     * method to modify the inquiry comments in the database and the program
     * @param inquiryID id of the inquiry
     * @param columnName name of the column in the databse being modififed
     * @param newValue new value for the comments of the inquiry
     */
    public void modifyInquiryComments(String inquiryID, String columnName, String newValue){
        for(Inquiry inquiry : UserInterface.getInquiries()){
            if(inquiry.getInquiryID().equals(inquiryID)){
                inquiry.setComments(newValue);
            }
        }
        //make change in database too
        database.modifyInquiryColumn(inquiryID, columnName, newValue);

    }




}
