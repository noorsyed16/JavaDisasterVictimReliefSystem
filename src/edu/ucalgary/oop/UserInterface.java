package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;

public class UserInterface {
    private PersonController personController;
    private MedicalRecordController medicalRecordController;
    private LocationController locationController;
    private InquiryController inquiryController;
    private BlanketController blanketController;
    private PersonalBelongingController personalBelongingController;
    private CotController cotController;
    private WaterController waterController;
    private static ArrayList<Blanket> blankets;
    private static ArrayList<Cot> cots;
    private static ArrayList<Inquiry> inquiries;
    private static ArrayList<Location> locations;
    private static ArrayList<MedicalRecord> medicalRecords;
    private static ArrayList<Person> people;
    private static ArrayList<PersonalBelonging> personalBelongings;
    private static ArrayList<Water> waters;
    private static ArrayList<Supply> supplies;
    private static ManageLanguage languageManager;
    private QueryTheDatabase queryTheDatabase;
    private static String languageCode;

    public UserInterface(String languageID){
        blankets = new ArrayList<Blanket>();
        cots = new ArrayList<Cot>();
        inquiries = new ArrayList<Inquiry>();
        locations = new ArrayList<Location>();
        medicalRecords = new ArrayList<MedicalRecord>();
        people = new ArrayList<Person>();
        personalBelongings = new ArrayList<PersonalBelonging>();
        waters = new ArrayList<Water>();
        supplies = new ArrayList<Supply>();

        queryTheDatabase = new QueryTheDatabase();

        medicalRecordController = new MedicalRecordController(queryTheDatabase);
        locationController = new LocationController(queryTheDatabase);
        inquiryController = new InquiryController(queryTheDatabase);
        personController = new PersonController(queryTheDatabase);
        blanketController = new BlanketController(queryTheDatabase);
        personalBelongingController = new PersonalBelongingController(queryTheDatabase);
        waterController = new WaterController(queryTheDatabase);
        cotController = new CotController(queryTheDatabase);


        languageManager = new ManageLanguage();
        languageCode = languageID;
    }

    public void loadDatabase(){
        //this method takes eveyrthing currently in the database and converts it into objects for the program
        LoadFromDatabase.loadFullDatabase();

    }

    public static void selectLanguage(){

        if (languageCode == null || languageCode.isEmpty()) {
            languageCode = "en-CA";
        }
        languageManager.readFromXML(languageCode);

    }

    public static ArrayList<Blanket> getBlankets() {
        return blankets;
    }
    
    public static void setBlankets(ArrayList<Blanket> blankets) {
        UserInterface.blankets = blankets;
    }
    
    public static ArrayList<Cot> getCots() {
        return cots;
    }
    
    public static void setCots(ArrayList<Cot> cots) {
        UserInterface.cots = cots;
    }
    
    public static ArrayList<Inquiry> getInquiries() {
        return inquiries;
    }
    
    public static void setInquiries(ArrayList<Inquiry> inquiries) {
        UserInterface.inquiries = inquiries;
    }
    
    public static ArrayList<Location> getLocations() {
        return locations;
    }
    
    public static void setLocations(ArrayList<Location> locations) {
        UserInterface.locations = locations;
    }
    
    public static ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
    
    public static void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords) {
        UserInterface.medicalRecords = medicalRecords;
    }
    
    public static ArrayList<Person> getPeople() {
        return people;
    }
    
    public static void setPeople(ArrayList<Person> people) {
        UserInterface.people = people;
    }
    
    public static ArrayList<PersonalBelonging> getPersonalBelongings() {
        return personalBelongings;
    }
    
    public static void setPersonalBelongings(ArrayList<PersonalBelonging> personalBelongings) {
        UserInterface.personalBelongings = personalBelongings;
    }
    
    public static ArrayList<Water> getWaters() {
        return waters;
    }
    
    public static void setWaters(ArrayList<Water> waters) {
        UserInterface.waters = waters;
    }

    public static ArrayList<Supply> getSupplies() {
        return supplies;
    }
    
    public static void setSupplies(ArrayList<Supply> supplies) {
        UserInterface.supplies = supplies;
    }
    

    public void displayMenuOptions(){
        Scanner scanner = new Scanner(System.in); 
        
        while (true) {
            System.out.println(languageManager.translateText("main_menu_select"));
            System.out.println("----------------" + languageManager.translateText("menu_options_label") + "----------------");
            System.out.println(languageManager.translateText("main_menu_option1"));
            System.out.println(languageManager.translateText("main_menu_option2"));
            System.out.println(languageManager.translateText("main_menu_option3"));
            System.out.println(languageManager.translateText("main_menu_option4"));
            System.out.println(languageManager.translateText("main_menu_option5"));
            System.out.println(languageManager.translateText("enter_your_choice"));

            //Load the database
            loadDatabase();

            String choice = scanner.nextLine(); // Get input from user

            // Act on the user's choice
            switch (choice) {
                case "1":
                    enterNewVictim();
                    break;
                case "2":
                    addOptions();
                    break;
                case "3":
                    viewExistingData();
                    break;
                case "4":
                    modifyExistingData();
                    break;
                
                case "5":
                    System.out.println(languageManager.translateText("closing_message"));
                    scanner.close(); // Clean up
                    System.exit(0);
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
            }

        }
    }

    public boolean isValidDate(String date) {
        // should be year-month-day
		Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher dateMatcher = datePattern.matcher(date);
		boolean validDate =  dateMatcher.find();
        return validDate;


	}

    public boolean isValidRoomNumber(String roomNum){
        Pattern roomNumPattern = Pattern.compile("^\\d{3}$");
		Matcher roomNumMatcher = roomNumPattern.matcher(roomNum);
		boolean validRoomNum =  roomNumMatcher.find();
        return validRoomNum;
    }

    public boolean isValidGrid(String grid){
        Pattern gridPattern = Pattern.compile("^[A-Z]\\d{2}$");
		Matcher gridMatcher = gridPattern.matcher(grid);
		boolean validGrid =  gridMatcher.find();
        return validGrid;
    }



    public boolean isValidGender(String gender){
        if (gender.equals("man") || gender.equals("woman") || gender.equals("non-binary person")){
            return true;
        }
        return false;
    }

    public boolean isValidPhoneNumber(String phoneNumber){
        Pattern phoneNumPattern = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
		Matcher phoneNumMatcher = phoneNumPattern.matcher(phoneNumber);
		return phoneNumMatcher.find();
    }

    public void enterNewVictim(){
        Scanner scanner = new Scanner(System.in);
        //need to error check here for all the inputs
        System.out.println(languageManager.translateText("enter_victim_fname"));
        String firstName = scanner.nextLine();

        System.out.println(languageManager.translateText("enter_victim_lname"));
        String lastName = scanner.nextLine();

        String dateOfBirth;
        String gender;
        while(true){
            System.out.println(languageManager.translateText("enter_victim_DOB"));
            dateOfBirth = scanner.nextLine();
            if(isValidDate(dateOfBirth)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }
        while(true){
            System.out.println(languageManager.translateText("enter_victim_gender"));
            gender = scanner.nextLine();
            if(isValidGender(gender)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }

        System.out.println(languageManager.translateText("enter_victim_comment"));
        String comments = scanner.nextLine();

        String phone;
        while(true){
            System.out.println(languageManager.translateText("enter_victim_phone"));;
            phone = scanner.nextLine();
            if(isValidPhoneNumber(phone)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }

        String group;
        while(true){
            System.out.println(languageManager.translateText("enter_victim_family"));
            group  = scanner.nextLine();
            try{
                int groupNumber = Integer.parseInt(group);
                break;
            } catch (NumberFormatException e) {
                System.out.println(languageManager.translateText("invalid_input"));
            }
        }

        people = personController.addPerson(people, firstName, lastName, dateOfBirth, gender, comments, phone, group);

        // should be able to add their relationships, mediclaRecords, and location
        String personID = people.get(people.size() - 1).getPersonID();
        String locationID = null;
        //start with location

        while(true){
            System.out.println(languageManager.translateText("enter_victim_location"));
            DatabaseView.viewLocationTable();
            String locationChoice = scanner.nextLine();
            if(locationChoice.equals("0")){
                System.out.println(languageManager.translateText("location_name"));
                String locationName = scanner.nextLine();
                
                System.out.println(languageManager.translateText("location_address"));
                String locationAddress  = scanner.nextLine();
                //locationid, name and address
                locationController.addLocation(locationName, locationAddress);

                locationID = locations.get(locations.size() - 1).getLocationID();

                locations = locationController.addLocationToPerson(locations, people, locationID, personID);
                break;

            }
            //check it out of bounds in which case ask for in put again
            else {
                while(true){
                    locationID = locationChoice;
                    boolean validLocationID = false;
                    for (Location location : locations) {
                        if (location.getLocationID().equals(locationID)){
                            validLocationID = true;
                            break;
                        }
                    }
                    if(validLocationID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                    System.out.println(languageManager.translateText("enter_locationID_for_row"));
                    DatabaseView.viewLocationTable();
                    locationChoice = scanner.nextLine();
                } 
            
            }
            
            locations = locationController.addLocationToPerson(locations, people, locationID, personID);
            break;
        } 
            Location locationAdded = locations.get(locations.size() - 1);
            // Act on the user's choice
            while(true){

                System.out.println(languageManager.translateText("any_medical_records"));
                String hasMedicalRecords = scanner.nextLine(); 
                
                switch (hasMedicalRecords) {
                    case "1":
                    // need loctaion in medicalRecord as well
                    String dateOfTreatment = "";
                    while(true){
                        System.out.println(languageManager.translateText("enter_DOT"));
                        dateOfTreatment = scanner.nextLine();
                        if(isValidDate(dateOfTreatment)){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                        
                    System.out.println(languageManager.translateText("enter_treatment_details"));
                        String treatmentDetails  = scanner.nextLine();

                        medicalRecords = medicalRecordController.addMedicalRecord(medicalRecords, locationAdded, locationID, personID, dateOfTreatment, treatmentDetails); 
                        MedicalRecord medicalRecordAdded = medicalRecords.get(medicalRecords.size() - 1);

                        ArrayList<Person> tempPeople = people;
                        people = personController.addMedicalRecordToPerson(tempPeople, medicalRecordAdded, personID);
                        break;
                    case "0":
                    //does not have a medical Record, move on
                        break;
                    default:
                    System.out.println(languageManager.translateText("invalid_choice"));
                }

                if(hasMedicalRecords.equals("0") || hasMedicalRecords.equals("1")){
                    break;
                }
            }   

    }
    
    public void addOptions(){
        Scanner scanner = new Scanner(System.in); 
        
        while (true) {
            System.out.println(languageManager.translateText("enter_table_to_add_to"));
            System.out.println(languageManager.translateText("table_options"));
            System.out.println(languageManager.translateText("add_an_inquiry"));
            System.out.println(languageManager.translateText("add_a_medical_record"));
            System.out.println(languageManager.translateText("add_a_supply"));
            System.out.println(languageManager.translateText("add_a_location"));

            System.out.println(languageManager.translateText("enter_your_choice"));

            String choice = scanner.nextLine(); // Get input from user

            // Act on the user's choice
            switch (choice) {
                case "1":
                    addAnInquiry();
                    break;
                case "2":
                    addAMedicalRecord();
                    break;
                case "3":
                    displayAddSupplyOptions();
                    break;
                case "4":
                    addALocation();
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }
            break;

        }
    }

    public void addALocation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(languageManager.translateText("enter_location_name"));
        String locationName  = scanner.nextLine();

        System.out.println(languageManager.translateText("enter_location_address"));
        String locationAddress  = scanner.nextLine();

        locationController.addLocation(locationName, locationAddress);
    }

    public void addAMedicalRecord(){
        Scanner scanner = new Scanner(System.in);
        String dateOfTreatment;
        String locationID;
        String personID;
        Location medLocation = null;

        while(true){
            System.out.println(languageManager.translateText("enter_DOT"));
            dateOfTreatment = scanner.nextLine();
            if(isValidDate(dateOfTreatment)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }
    
                        
        System.out.println(languageManager.translateText("enter_treatment_details"));
        String treatmentDetails  = scanner.nextLine();

        while(true){
            System.out.println(languageManager.translateText("enter_location_for_med_record"));
            DatabaseView.viewLocationTable();
            locationID = scanner.nextLine();
            boolean validLocationID = false;
            for (Location location : locations) {
                if (location.getLocationID().equals(locationID)){
                    medLocation = location;
                    validLocationID = true;
                    break;
                }
            }
            if(validLocationID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 

        while(true){
            System.out.println(languageManager.translateText("enter_med_record_personID"));
            DatabaseView.viewPersonTable();
            personID = scanner.nextLine();
            boolean validInquirerID = false;
            for (Person person : people) {
                if (person.getPersonID().equals(personID)){
                    validInquirerID = true;
                    break;
                }
            }
            if(validInquirerID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }   

        medicalRecords = medicalRecordController.addMedicalRecord(medicalRecords, medLocation, locationID, personID, dateOfTreatment, treatmentDetails); 
        MedicalRecord medicalRecordAdded = medicalRecords.get(medicalRecords.size() - 1);
        ArrayList<Person> tempPeople = people;
        people = personController.addMedicalRecordToPerson(tempPeople, medicalRecordAdded, personID);
    }
    
    public void displayAddSupplyOptions(){
        Scanner scanner = new Scanner(System.in); 
        
        while (true) {
            System.out.println(languageManager.translateText("select_supply_type"));
            System.out.println(languageManager.translateText("table_options"));
            System.out.println(languageManager.translateText("add_a_blanket"));
            System.out.println(languageManager.translateText("add_a_personalBelonging"));
            System.out.println(languageManager.translateText("add_a_cot"));
            System.out.println(languageManager.translateText("add_a_water"));

            System.out.println(languageManager.translateText("enter_your_choice"));

            String choice = scanner.nextLine(); // Get input from user

            // Act on the user's choice
            switch (choice) {
                case "1":
                    addABlanket();
                    break;
                case "2":
                    addAPersonalBelonging();
                    break;
                case "3":
                    addACot();
                    break;
                case "4":
                    addWater();
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
            }
            break;
        }
    }
    
    public void addABlanket(){
        Scanner scanner = new Scanner(System.in);
        String type = "blanket";
        String comments = null;
        String choice;
        String locationID = "";
        String personID = "";
        while(true){
        System.out.println(languageManager.translateText("select_supply_allocation"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("allocate_to_location"));
            System.out.println(languageManager.translateText("allocate_to_person"));
            System.out.println(languageManager.translateText("enter_your_choice"));    
            choice = scanner.nextLine();
            if(choice.equals("1")){
                while(true){
                    boolean validLocationID = false;
                    System.out.println(languageManager.translateText("enter_supply_locationID"));
                    DatabaseView.viewLocationTable();
                    locationID = scanner.nextLine();
                    for(Location location : locations){
                        if(location.getLocationID().equals(locationID)){
                            validLocationID = true;
                            break;
                        }
                    }
                    if(validLocationID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                }
                break;
            }
            else if(choice.equals("2")){
                while(true){
                    boolean validPersonID = false;
                    System.out.println(languageManager.translateText("enter_supply_personID"));
                    DatabaseView.viewPersonTable();
                    personID = scanner.nextLine();
                    for(Person person : people){
                        if(person.getPersonID().equals(personID)){
                            validPersonID = true;
                            break;
                        }
                    }
                    if(validPersonID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                }
                break;
            }
            else{
                System.out.println(languageManager.translateText("invalid_choice"));;
            }
        }

        blanketController.addBlanket(type, comments, personID, locationID);
    }

    public void addWater(){
        Scanner scanner = new Scanner(System.in);
        String type = "water";
        String dateAllocated = LocalDate.now().toString();

        String choice;
        String locationID = "";
        String personID = "";
        while(true){
            System.out.println(languageManager.translateText("select_supply_allocation"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("allocate_to_location"));
            System.out.println(languageManager.translateText("allocate_to_person"));
            System.out.println(languageManager.translateText("enter_your_choice"));
            choice = scanner.nextLine();
            if(choice.equals("1")){
                while(true){
                    boolean validLocationID = false;
                    System.out.println(languageManager.translateText("enter_supply_locationID"));
                    DatabaseView.viewLocationTable();
                    locationID = scanner.nextLine();
                    for(Location location : locations){
                        if(location.getLocationID().equals(locationID)){
                            validLocationID = true;
                            break;
                        }
                    }
                    if(validLocationID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                }
                break;
            }
            else if(choice.equals("2")){
                while(true){
                    boolean validPersonID = false;
                    System.out.println(languageManager.translateText("enter_supply_personID"));
                    DatabaseView.viewPersonTable();
                    personID = scanner.nextLine();
                    for(Person person : people){
                        if(person.getPersonID().equals(personID)){
                            validPersonID = true;
                            break;
                        }
                    }
                    if(validPersonID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                }
                break;
            }
            else{
                System.out.println(languageManager.translateText("invalid_choice"));
            }
        }

        waterController.addWater(type, dateAllocated, personID, locationID);
    }


    public void addAPersonalBelonging(){
        Scanner scanner = new Scanner(System.in);
        String type = "personal item";

        System.out.println(languageManager.translateText("enter_personal_belonging_description"));
        String comments = scanner.nextLine();

        String choice;
        String locationID = "";
        String personID = "";
        while(true){
            System.out.println(languageManager.translateText("supply_go_to_person"));
            boolean validPersonID = false;
            System.out.println(languageManager.translateText("enter_supply_personID"));
            DatabaseView.viewPersonTable();
            personID = scanner.nextLine();
            for(Person person : people){
                if(person.getPersonID().equals(personID)){
                    validPersonID = true;
                    break;
                }
            }
            if(validPersonID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
            }

        personalBelongingController.addPersonalBelonging(type, comments, personID);
        
    }

    public void addACot(){
        Scanner scanner = new Scanner(System.in);
        String type = "cot";
        String roomNumber = null;
        while(true){
            System.out.println(languageManager.translateText("enter_room_number"));
            roomNumber = scanner.nextLine();
            if(isValidRoomNumber(roomNumber)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
            }

        String grid = null;
        while(true){
            System.out.println(languageManager.translateText("enter_grid_number"));
            grid = scanner.nextLine();
            if(isValidGrid(grid)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }

        String choice;
        String locationID = "";
        String personID = "";
        while(true){
            System.out.println(languageManager.translateText("select_supply_allocation"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("allocate_to_location"));
            System.out.println(languageManager.translateText("allocate_to_person"));
            System.out.println(languageManager.translateText("enter_your_choice"));

            choice = scanner.nextLine();
            if(choice.equals("1")){
                while(true){
                    boolean validLocationID = false;
                    System.out.println(languageManager.translateText("enter_supply_locationID"));
                    DatabaseView.viewLocationTable();
                    locationID = scanner.nextLine();
                    for(Location location : locations){
                        if(location.getLocationID().equals(locationID)){
                            validLocationID = true;
                            break;
                        }
                    }
                    if(validLocationID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                }
                break;
            }
            else if(choice.equals("2")){
                while(true){
                    boolean validPersonID = false;
                    System.out.println(languageManager.translateText("enter_supply_personID"));
                    DatabaseView.viewPersonTable();
                    personID = scanner.nextLine();
                    for(Person person : people){
                        if(person.getPersonID().equals(personID)){
                            validPersonID = true;
                            break;
                        }
                    }
                    if(validPersonID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                }
                break;
            }
            else{
                System.out.println(languageManager.translateText("invalid_choice"));
            }
        }

        cotController.addCot(type, roomNumber, grid, personID, locationID);
    }


    public void addAnInquiry(){
        Scanner scanner = new Scanner(System.in);
        String inquirerID = null;
        String seekingID = null;
        String locationID = null;
        String dateOfInquiry = null;

        while(true){
            System.out.println(languageManager.translateText("prompt_for_inquirer"));
            DatabaseView.viewPersonTable();
            inquirerID = scanner.nextLine();
            boolean validInquirerID = false;
            for (Person person : people) {
                if (person.getPersonID().equals(inquirerID)){
                    validInquirerID = true;
                    break;
                }
            }
            if(validInquirerID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }   

        while(true){
            System.out.println(languageManager.translateText("prompt_for_seeker"));
            DatabaseView.viewPersonTable();
            seekingID = scanner.nextLine();
            boolean validSeekingID = false;
            for (Person person : people) {
                if (person.getPersonID().equals(seekingID)){
                    validSeekingID = true;
                    break;
                }
            }
            if(validSeekingID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 
        
        while(true){
            System.out.println(languageManager.translateText("prompt_for_location_involved"));
            DatabaseView.viewLocationTable();
            locationID = scanner.nextLine();
            boolean validLocationID = false;
            for (Location location : locations) {
                if (location.getLocationID().equals(locationID)){
                    validLocationID = true;
                    break;
                }
            }
            if(validLocationID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 

        while(true){
            System.out.println(languageManager.translateText("prompt_for_date_of_inquiry"));
            dateOfInquiry = scanner.nextLine();
            if(isValidDate(dateOfInquiry)){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }

        System.out.println(languageManager.translateText("prompt_for_inquiry_comments"));
        String comments = scanner.nextLine();

        inquiryController.addInquiry(inquirerID, seekingID, locationID, dateOfInquiry, comments);

    }

    public void viewExistingData(){

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(languageManager.translateText("select_table_view"));
            System.out.println(languageManager.translateText("table_options"));
            System.out.println(languageManager.translateText("person1"));
            System.out.println(languageManager.translateText("location2"));
            System.out.println(languageManager.translateText("inquiry3"));
            System.out.println(languageManager.translateText("medicalRecord4"));
            System.out.println(languageManager.translateText("personLocation5"));
            System.out.println(languageManager.translateText("supplyAllocation6"));
            System.out.println(languageManager.translateText("supply7"));
            System.out.println(languageManager.translateText("enter_your_choice"));

            String choice = scanner.nextLine(); // Get input from user

            // Act on the user's choice
            switch (choice) {
                case "1":
                    DatabaseView.viewPersonTable();
                    break;
                case "2":
                    DatabaseView.viewLocationTable();
                    break;
                case "3":
                    DatabaseView.viewInquiryTable();
                    break;
                case "4":
                    DatabaseView.viewMedicalRecordTable();
                    break;
                case "5":
                    DatabaseView.viewPersonLocationTable();
                    break;
                case "6":
                    DatabaseView.viewSupplyAllocationTable();
                    break;
                case "7":
                    DatabaseView.viewSupplyTable();
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }
            break;
        }

    }

    public void modifyExistingData(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(languageManager.translateText("select_table_modify"));
            System.out.println(languageManager.translateText("table_options"));
            System.out.println(languageManager.translateText("person1"));
            System.out.println(languageManager.translateText("location2"));
            System.out.println(languageManager.translateText("inquiry3"));
            System.out.println(languageManager.translateText("medicalRecord4"));
            System.out.println(languageManager.translateText("personLocation5"));
            System.out.println(languageManager.translateText("supply_supplyAllocation6"));
            System.out.println(languageManager.translateText("enter_your_choice"));

            String choice = scanner.nextLine(); // Get input from user

            // Act on the user's choice
            switch (choice) {
                case "1":
                    modifyPersonTable();
                    break;
                case "2":
                    modifyLocationTable();
                    break;
                case "3":
                    modifyInquiryTable();
                    break;
                case "4":
                    modifyMedicalRecordTable();
                    break;
                case "5":
                    modifyPersonLocationTable();
                    break;
                case "6":
                    modifySupplyTable();
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }
            break;
        }

    }

    public void modifyPersonTable(){
        Scanner scanner = new Scanner(System.in);
        String personID = null;
        while(true){
            System.out.println(languageManager.translateText("personID_to_modify"));
            DatabaseView.viewPersonTable();
            personID = scanner.nextLine();
            boolean validPersonID = false;
            for (Person person : people) {
                if (person.getPersonID().equals(personID)){
                    validPersonID = true;
                    break;
                }
            }
            if(validPersonID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
            } 
    
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("firstName1"));
            System.out.println(languageManager.translateText("lastName2"));
            System.out.println(languageManager.translateText("dateOfBirth3"));
            System.out.println(languageManager.translateText("gender4"));
            System.out.println(languageManager.translateText("comments5"));
            System.out.println(languageManager.translateText("phoneNum6"));
            System.out.println(languageManager.translateText("family7"));
            System.out.println(languageManager.translateText("enter_your_choice"));
    
            String choice = scanner.nextLine(); 

            switch (choice) {
                case "1":
                System.out.println(languageManager.translateText("enter_new_fname"));
                    String newFirstName = scanner.nextLine();
                    personController.modifyPersonFirstName(personID, "first_name", newFirstName);
                    break;
                case "2":
                System.out.println(languageManager.translateText("enter_new_lname"));
                    String newLastName = scanner.nextLine();
                    personController.modifyPersonLastName(personID, "last_name", newLastName);
                    break;
                case "3":
                    String newDateOfBirth = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_DOB"));
                        newDateOfBirth = scanner.nextLine();
                        if(isValidDate(newDateOfBirth)){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                    personController.modifyPersonDateOfBirth(personID, "date_of_birth", newDateOfBirth);
                    break;
                case "4":
                    String newGender = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_gender"));
                        newGender = scanner.nextLine();
                        if(isValidGender(newGender)){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                    personController.modifyPersonGender(personID, "gender", newGender);
                    break;
                case "5":
                System.out.println(languageManager.translateText("enter_new_comments"));
                    String newComments = scanner.nextLine();
                    personController.modifyPersonComments(personID, "comments", newComments);
                    break;
                case "6":
                System.out.println(languageManager.translateText("enter_new_phone"));
                    String newPhone = scanner.nextLine();
                    personController.modifyPersonPhoneNumber(personID, "phone_number", newPhone);
                    break;
                case "7":
                    String newFamilyNumber = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_family"));
                        newFamilyNumber  = scanner.nextLine();
                        try{
                            int groupNumber = Integer.parseInt(newFamilyNumber);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(languageManager.translateText("invalid_input"));
                        }
                    }
                    personController.modifyPersonFamilyGroupNumber(personID, "family_group", newFamilyNumber);
                    break;
                
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }
            break;
        }
    }

    public void modifyLocationTable(){
        Scanner scanner = new Scanner(System.in);
        String locationID = null;
        while(true){
            System.out.println(languageManager.translateText("enter_locationID_to_modify"));
            DatabaseView.viewLocationTable();
            locationID = scanner.nextLine();
            boolean validLocationID = false;
            for (Location location : locations) {
                if (location.getLocationID().equals(locationID)){
                    validLocationID = true;
                    break;
                }
            }
            if(validLocationID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 
    
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("name1"));
            System.out.println(languageManager.translateText("address2"));
            System.out.println(languageManager.translateText("enter_your_choice"));
    
            String choice = scanner.nextLine(); 
    
            System.out.println(languageManager.translateText("enter_new_value"));
            String newValue = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    locationController.modifyLocationName(locationID, "name", newValue);
                    break;
                case "2":
                    locationController.modifyLocationAddress(locationID, "address", newValue);
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }
            break;
        }
    }

    public void modifyInquiryTable(){
        Scanner scanner = new Scanner(System.in);
        String inquiryID = null;
        while(true){
            System.out.println(languageManager.translateText("enter_inquiryID_to_modify"));
            DatabaseView.viewInquiryTable();
            inquiryID = scanner.nextLine();
            boolean validInquiryID = false;
            for (Inquiry inquiry : inquiries) {
                if (inquiry.getInquiryID().equals(inquiryID)){
                    validInquiryID = true;
                    break;
                }
            }
            if(validInquiryID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_choice"));
        } 
    
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("dateOfInquiry1"));
            System.out.println(languageManager.translateText("seekingID2"));
            System.out.println(languageManager.translateText("locationID3"));
            System.out.println(languageManager.translateText("comments4"));
            System.out.println(languageManager.translateText("enter_your_choice"));
            String choice = scanner.nextLine(); 
    
            switch (choice) {
                case "1":
                    String newDateOfInquiry = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_dateOfInquiry"));
                        newDateOfInquiry = scanner.nextLine();
                        if(isValidDate(newDateOfInquiry)){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                    inquiryController.modifyInquiryDateOfInquiry(inquiryID, "date_of_inquiry", newDateOfInquiry);
                    break;
                case "2":
                    String newSeekingID = null;
                    while(true){
                            System.out.println(languageManager.translateText("enter_new_seekingID"));
                            DatabaseView.viewPersonTable();
                            boolean validSeekingID = false;
                            newSeekingID = scanner.nextLine();
                            for(Person person : UserInterface.getPeople()){
                                if(person.getPersonID().equals(newSeekingID)){
                                    validSeekingID = true;
                                    break;
                                }
                            }
                            if(validSeekingID){
                                break;
                            }
                            System.out.println(languageManager.translateText("invalid_input"));
                    }
                    inquiryController.modifyInquirySeekingID(inquiryID, "seeking_id", newSeekingID);
                    
                    break;
                case "3":
                    String newLocationID = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_locationID"));
                        DatabaseView.viewLocationTable();
                        boolean validLocationID = false;
                        newLocationID = scanner.nextLine();
                        for(Location location : UserInterface.getLocations()){
                            if(location.getLocationID().equals(newLocationID)){
                                validLocationID = true;
                                break;
                            }
                        }
                        if(validLocationID){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                    inquiryController.modifyInquiryLocationID(inquiryID, "location_id", newLocationID);
                    break;
                case "4":
                System.out.println(languageManager.translateText("enter_new_comments"));
                    String newComments = scanner.nextLine();
                    inquiryController.modifyInquiryComments(inquiryID, "comments", newComments);
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
                
            }
            break;
        }
        }


    public void modifyMedicalRecordTable(){
        Scanner scanner = new Scanner(System.in);
        String medicalRecordID = null;
        while(true){
            System.out.println(languageManager.translateText("enter_new_medicalRecordID"));
            DatabaseView.viewMedicalRecordTable();
            medicalRecordID = scanner.nextLine();
            boolean validMedicalRecordID = false;
            for (MedicalRecord medicalRecord : medicalRecords) {
                if (medicalRecord.getMedicalRecordID().equals(medicalRecordID)){
                    validMedicalRecordID = true;
                    break;
                }
            }
            if(validMedicalRecordID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 
    
        while(true){
            
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("locationID1"));
            System.out.println(languageManager.translateText("personID2"));
            System.out.println(languageManager.translateText("dateOfTreatment3"));
            System.out.println(languageManager.translateText("details4"));
            System.out.println(languageManager.translateText("enter_your_choice"));
    
            String choice = scanner.nextLine(); 
    
            switch (choice) {
                case "1":
                String newLocationID = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_locationID"));
                        DatabaseView.viewLocationTable();
                        boolean validLocationID = false;
                        newLocationID = scanner.nextLine();
                        for(Location location : UserInterface.getLocations()){
                            if(location.getLocationID().equals(newLocationID)){
                                validLocationID = true;
                                break;
                            }
                        }
                        if(validLocationID){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalidInput"));
                    }
                    medicalRecordController.modifyMedicalRecordLocationID(medicalRecordID, "location_id", newLocationID);
                    break;
                case "2":
                    String newPersonID = null;
                    String newSeekingID = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_personID"));
                            DatabaseView.viewPersonTable();
                            boolean validPersonID = false;
                            newPersonID = scanner.nextLine();
                            for(Person person : UserInterface.getPeople()){
                                if(person.getPersonID().equals(newPersonID)){
                                    validPersonID = true;
                                    break;
                                }
                            }
                            if(validPersonID){
                                break;
                            }
                            System.out.println(languageManager.translateText("invalidInput"));
                    }
                    medicalRecordController.modifyMedicalRecordPersonID(medicalRecordID, "person_id", newPersonID);
                    break;
                case "3":
                    String newDateOfTreatment = null;
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_dateOfTreatment"));
                        newDateOfTreatment = scanner.nextLine();
                        if(isValidDate(newDateOfTreatment)){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalidInput"));
                    }
                    medicalRecordController.modifyMedicalRecordDateOfTreatment(medicalRecordID, "date_of_treatment", newDateOfTreatment);
                    break;
                case "4":
                System.out.println(languageManager.translateText("enter_new_details"));
                    String newTreatmentDetails = scanner.nextLine();
                    medicalRecordController.modifyMedicalRecordTreatmentDetails(medicalRecordID, "treatment_details", newTreatmentDetails);
                    break;
                default:
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }
            break;
        }
    }
    
    public void modifyPersonLocationTable(){
        Scanner scanner = new Scanner(System.in);
        String personID = null;
        while(true){
            System.out.println(languageManager.translateText("enter_personID_modify"));
            DatabaseView.viewPersonLocationTable();
            personID = scanner.nextLine();
            boolean validPersonID = false;
            for (Person person : people) {
                if (person.getPersonID().equals(personID)){
                    validPersonID = true;
                    break;
                }
            }
            if(validPersonID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 
    
        while(true){

            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("locationID1"));
            System.out.println(languageManager.translateText("enter_your_choice"));
            
            String choice = scanner.nextLine(); 

            if(choice.equals("1")){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }
        String locationID = null;
        while(true){
            System.out.println(languageManager.translateText("enter_new_locationID"));
            DatabaseView.viewLocationTable();
            locationID = scanner.nextLine();
            boolean validLocationID = false;
            for (Location location : locations) {
                    if (location.getLocationID().equals(locationID)){
                    validLocationID = true;
                    break;
                }
            }
            if(validLocationID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        }

        personController.modifyPersonLocationID(personID, "location_id", locationID);
    }

    public void modifySupplyTable(){
        Scanner scanner = new Scanner(System.in);

        String supplyID = null;
        String type = "";
        while(true){
            System.out.println(languageManager.translateText("enter_supplyID_modify"));
            DatabaseView.viewSupplyTable();
            supplyID = scanner.nextLine();
            boolean validSupplyID = false;
            for (Supply supply : supplies) {
                if (supply.getSupplyID().equals(supplyID)){
                    validSupplyID = true;
                    
                    type = supply.getType();
                    break;
                }
            }
            if(validSupplyID){
                break;
            }
            System.out.println(languageManager.translateText("invalid_input"));
        } 
        //call correct modify function depending on the type of the supply
        if (type.equals("blanket")) {
            // in options for what you can modify, you can modify the person or the location its allocated to. 
            modifyBlanket(supplyID);
        } else if (type.equals("personal item")) {
            modifyPersonalBelonging(supplyID);
        } else if (type.equals("cot")) {
            modifyCot(supplyID);
        } else if (type.equals("water")) {
            modifyWater(supplyID);

        }
        
    }

    public void modifyBlanket(String supplyID){
        Scanner scanner = new Scanner(System.in);

        
        String currentLocationID = "";
        String currentPersonID = "";
        boolean isAllocatedToAPerson = false;
        boolean isAllocatedToALocation = false;
        for(Blanket blanket : blankets){
            if(blanket.getSupplyID().equals(supplyID)){
                if (blanket.getPersonAllocatedTo() != null) {
                    //that means its allocated to a person
                    currentPersonID = blanket.getPersonAllocatedTo().getPersonID();
                    isAllocatedToAPerson = true;
                } else {
                    currentPersonID = "";
                    isAllocatedToAPerson = false;
                }
                
                if (blanket.getLocationAllocatedTo() != null) {
                    currentLocationID = blanket.getLocationAllocatedTo().getLocationID();
                    // it is allocated to a location
                    isAllocatedToALocation = true;
                } else {
                    currentLocationID = "";
                    isAllocatedToALocation = false;
                }
                
            }
        }
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("locationAllocated1"));
            System.out.println(languageManager.translateText("personAllocated2"));
            System.out.println(languageManager.translateText("enter_your_choice"));
        
            String choice = scanner.nextLine(); 
            String locationID;

            if(choice.equals("1")){
                if(isAllocatedToAPerson){
                    System.out.println("An inventory item allocated to a person can no longer be allocated to a location");
                    break;
                }
                while(true){
                    System.out.println(languageManager.translateText("enter_new_locationIDAllocated"));
                        DatabaseView.viewLocationTable();
                        locationID = scanner.nextLine();
                        boolean validLocationID = false;
                        for (Location location : locations) {
                            if (location.getLocationID().equals(locationID)){
                                validLocationID = true;
                                break;
                            }
                        }
                        if(validLocationID){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                blanketController.modifyLocationAllocatedTo(supplyID, "location_id", locationID);
                break;
            }
            else if(choice.equals("2")){
                String currentLocationAllocatedToID = "";
                if(currentPersonID.equals("")){
                    //then need to first delete current personID
                    //currently allocated to a location
                    for(Water water : waters){
                        if(water.getSupplyID().equals(supplyID)){
                            currentLocationAllocatedToID = water.getLocationAllocatedTo().getLocationID();
                            water.setLocationAllocatedTo(null);
                        }
                    }
                }
                String personID;
                while(true){
                    System.out.println(languageManager.translateText("enter_new_personIDAllocated"));
                    DatabaseView.viewPersonTable();
                    personID = scanner.nextLine();
                    boolean validPersonID = false;
                    if(!(currentLocationAllocatedToID.equals(""))){
                        //currently allocated to a location, changing to person, need to make sure person is in location
                        for(Location location : locations){
                            if(location.getLocationID().equals(currentLocationAllocatedToID)){
                                if(location.getOccupants() != null){
                                    for(Person person : location.getOccupants()){
                                        if(person.getPersonID().equals(personID)){
                                            validPersonID = true;
                                            break;
                                        }
                                    }   
                                } 
                                break;  
                            }
                        }
                        if(!validPersonID){
                            System.out.println("The new person allocated to must be in the location the supply is currently allocated to");
                            break;
                        }
                    } else{
                        for (Person person : people) {
                            if (person.getPersonID().equals(personID)){
                                validPersonID = true;
                                break;
                            }
                        }
                    }
                    if(validPersonID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                    }
                blanketController.modifyPersonAllocatedTo(supplyID, "person_id", personID);
                //set location to be empty
                blanketController.modifyLocationAllocatedTo(supplyID, "location_id", "");
                break;
                //here
            }
             else{
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }

        }

    }

    public void modifyPersonalBelonging(String supplyID){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("personAllocated1"));
            System.out.println(languageManager.translateText("description2"));
            System.out.println(languageManager.translateText("enter_your_choice"));
        
            String choice = scanner.nextLine(); 
            String locationID;
            if(choice.equals("1")){
                    while(true){
                        System.out.println(languageManager.translateText("enter_new_locationIDAllocated"));
                        DatabaseView.viewLocationTable();
                        locationID = scanner.nextLine();
                        boolean validLocationID = false;
                        for (Location location : locations) {
                            if (location.getLocationID().equals(locationID)){
                                validLocationID = true;
                                break;
                            }
                        }
                        if(validLocationID){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                personalBelongingController.modifyPersonAllocatedTo(supplyID, "location_id", locationID);
                break;
            }
            else if(choice.equals("2")){
                System.out.println(languageManager.translateText("enter_new_description"));
                String newValue = scanner.nextLine();
                personalBelongingController.modifyPersonalBelongingDescription(supplyID, "comments", newValue);
                break;
            }
             else{
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }

        }
    }

    public void modifyCot(String supplyID){
        Scanner scanner = new Scanner(System.in);

        String currentLocationID = "";
        String currentPersonID = "";
        boolean isAllocatedToAPerson = false;
        boolean isAllocatedToALocation = false;
        for(Cot cot : cots){
            if(cot.getSupplyID().equals(supplyID)){
                if (cot.getPersonAllocatedTo() != null) {
                    //that means its allocated to a person
                    currentPersonID = cot.getPersonAllocatedTo().getPersonID();
                    isAllocatedToAPerson = true;
                } else {
                    currentPersonID = "";
                    isAllocatedToAPerson = false;
                }
                
                if (cot.getLocationAllocatedTo() != null) {
                    currentLocationID = cot.getLocationAllocatedTo().getLocationID();
                    // it is allocated to a location
                    isAllocatedToALocation = true;
                } else {
                    currentLocationID = "";
                    isAllocatedToALocation = false;
                }
                
            }
        }
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("locationAllocated1"));
            System.out.println(languageManager.translateText("personAllocated2"));
            System.out.println(languageManager.translateText("roomNum3"));
            System.out.println(languageManager.translateText("grid4"));
            System.out.println(languageManager.translateText("enter_your_choice"));
        
        
            String choice = scanner.nextLine(); 
            String locationID;
            if(choice.equals("1")){
                if(isAllocatedToAPerson){
                    System.out.println("An inventory item allocated to a person can no longer be allocated to a location");
                    break;
                }
                while(true){
                    System.out.println(languageManager.translateText("enter_new_locationIDAllocated"));
                        DatabaseView.viewLocationTable();
                        locationID = scanner.nextLine();
                        boolean validLocationID = false;
                        for (Location location : locations) {
                            if (location.getLocationID().equals(locationID)){
                                validLocationID = true;
                                break;
                            }
                        }
                        if(validLocationID){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                cotController.modifyLocationAllocatedTo(supplyID, "location_id", locationID);
                break;
            }
            else if(choice.equals("2")){
                String currentLocationAllocatedToID = "";
                if(currentPersonID.equals("")){
                    //then need to first delete current personID
                    //currently allocated to a location
                    for(Water water : waters){
                        if(water.getSupplyID().equals(supplyID)){
                            currentLocationAllocatedToID = water.getLocationAllocatedTo().getLocationID();
                            water.setLocationAllocatedTo(null);
                        }
                    }
                }
                String personID;
                while(true){
                    System.out.println(languageManager.translateText("enter_new_personIDAllocated"));
                    DatabaseView.viewPersonTable();
                    personID = scanner.nextLine();
                    boolean validPersonID = false;
                    if(!(currentLocationAllocatedToID.equals(""))){
                        //currently allocated to a location, changing to person, need to make sure person is in location
                        for(Location location : locations){
                            if(location.getLocationID().equals(currentLocationAllocatedToID)){
                                if(location.getOccupants() != null){
                                    for(Person person : location.getOccupants()){
                                        if(person.getPersonID().equals(personID)){
                                            validPersonID = true;
                                            break;
                                        }
                                    }   
                                } 
                                break;  
                            }
                        }
                        if(!validPersonID){
                            System.out.println("The new person allocated to must be in the location the supply is currently allocated to");
                            break;
                        }
                    } else{
                        for (Person person : people) {
                            if (person.getPersonID().equals(personID)){
                                validPersonID = true;
                                break;
                            }
                        }
                    }
                    if(validPersonID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                    }
                cotController.modifyPersonAllocatedTo(supplyID, "person_id", personID);
                //set location to empty
                cotController.modifyLocationAllocatedTo(supplyID, "location_id", "");
                break;
            }
            else if(choice.equals("3")){
                String newRoomNumber = null;
                while(true){
                    System.out.println(languageManager.translateText("enter_new_roomNum"));
                    newRoomNumber = scanner.nextLine();
                    if(isValidRoomNumber(newRoomNumber)){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                    }
                cotController.modifyCotRoomNumber(supplyID, "comments", newRoomNumber);
                break;
            }
            else if(choice.equals("4")){
                String newGrid = null;
                while(true){
                    System.out.println(languageManager.translateText("enter_new_grid"));
                    newGrid = scanner.nextLine();
                    if(isValidGrid(newGrid)){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                    }
                cotController.modifyCotGrid(supplyID, "comments", newGrid);
                break;
            }
             else{
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }

        }
    }
    

    public void modifyWater(String supplyID){
        Scanner scanner = new Scanner(System.in);
        
        String currentLocationID = "";
        String currentPersonID = "";
        boolean isAllocatedToAPerson = false;
        boolean isAllocatedToALocation = false;
        for(Water water : waters){
            if(water.getSupplyID().equals(supplyID)){
                if (water.getPersonAllocatedTo() != null) {
                    //that means its allocated to a person
                    currentPersonID = water.getPersonAllocatedTo().getPersonID();
                    isAllocatedToAPerson = true;
                } else {
                    currentPersonID = "";
                    isAllocatedToAPerson = false;
                }
                
                if (water.getLocationAllocatedTo() != null) {
                    currentLocationID = water.getLocationAllocatedTo().getLocationID();
                    // it is allocated to a location
                    isAllocatedToALocation = true;
                } else {
                    currentLocationID = "";
                    isAllocatedToALocation = false;
                }
                
            }
        }
        while(true){
            System.out.println(languageManager.translateText("select_a_field_to_modify"));
            System.out.println(languageManager.translateText("options"));
            System.out.println(languageManager.translateText("locationAllocated1"));
            System.out.println(languageManager.translateText("personAllocated2"));
            System.out.println(languageManager.translateText("enter_your_choice"));
        
            String choice = scanner.nextLine(); 
            String locationID;
            if(choice.equals("1")){
                if(isAllocatedToAPerson){
                    System.out.println("An inventory item allocated to a person can no longer be allocated to a location");
                    break;
                }
                while(true){
                    System.out.println(languageManager.translateText("enter_new_locationIDAllocated"));
                        DatabaseView.viewLocationTable();
                        locationID = scanner.nextLine();
                        boolean validLocationID = false;
                        for (Location location : locations) {
                            if (location.getLocationID().equals(locationID)){
                                validLocationID = true;
                                break;
                            }
                        }
                        if(validLocationID){
                            break;
                        }
                        System.out.println(languageManager.translateText("invalid_input"));
                    }
                waterController.modifyLocationAllocatedTo(supplyID, "location_id", locationID);
                break;
            }
            else if(choice.equals("2")){
                String currentLocationAllocatedToID = "";
                if(currentPersonID.equals("")){
                    //then need to first delete current personID
                    //currently allocated to a location
                    for(Water water : waters){
                        if(water.getSupplyID().equals(supplyID)){
                            currentLocationAllocatedToID = water.getLocationAllocatedTo().getLocationID();
                            water.setLocationAllocatedTo(null);
                        }
                    }
                }
                String personID;
                while(true){
                    System.out.println(languageManager.translateText("enter_new_personIDAllocated"));
                    DatabaseView.viewPersonTable();
                    personID = scanner.nextLine();
                    boolean validPersonID = false;
                    if(!(currentLocationAllocatedToID.equals(""))){
                        //currently allocated to a location, changing to person, need to make sure person is in location
                        for(Location location : locations){
                            if(location.getLocationID().equals(currentLocationAllocatedToID)){
                                if(location.getOccupants() != null){
                                    for(Person person : location.getOccupants()){
                                        if(person.getPersonID().equals(personID)){
                                            validPersonID = true;
                                            break;
                                        }
                                    }   
                                } 
                                break;  
                            }
                        }
                        if(!validPersonID){
                            System.out.println("The new person allocated to must be in the location the supply is currently allocated to");
                            break;
                        }
                    } else{
                        for (Person person : people) {
                            if (person.getPersonID().equals(personID)){
                                validPersonID = true;
                                break;
                            }
                        }
                    }
                    if(validPersonID){
                        break;
                    }
                    System.out.println(languageManager.translateText("invalid_input"));
                    }
                waterController.modifyPersonAllocatedTo(supplyID, "person_id", personID);
                //set location to be empty
                waterController.modifyLocationAllocatedTo(supplyID, "location_id", "");
                break;
            }
             else{
                System.out.println(languageManager.translateText("invalid_choice"));
                    continue;
            }

        }
    }
}



    


