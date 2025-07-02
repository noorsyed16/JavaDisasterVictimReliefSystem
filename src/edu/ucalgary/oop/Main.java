package edu.ucalgary.oop;

public class Main {

    public static void main(String[] args) {

        DatabaseConnection.getInstance();
        String inputArg = " ";

        if (args.length == 0) {
            inputArg = " ";
        } else {
            inputArg = args[0];
        }
        UserInterface userInterface = new UserInterface(inputArg);

        UserInterface.selectLanguage();

        userInterface.displayMenuOptions();

        DatabaseConnection.close();        
    }
    
}
