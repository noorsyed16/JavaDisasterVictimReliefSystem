package edu.ucalgary.oop;
/**This class connect the program to the database, connects to user oop with password ucalgary
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/

import java.sql.*;

public class DatabaseConnection {

    private static Connection dBConnect;
    private static DatabaseConnection instance;

    /**
     * gets the database connection and catches any exception thrown. will write to error file if connection is unsucessfull
     */
    private DatabaseConnection(){
        try{
            dBConnect = DriverManager.getConnection("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");
        } catch (SQLException e) {
            //e.printStackTrace();
            WriteToErrorFile.logAndExit(e, "Connection to the database failed. Check error log file for details");
        }
    }
    /**
     * gets an instance of teh databse and ensures there is only one as it is a singleton object
     * @return returns the single instance
     */
    public static DatabaseConnection getInstance(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    /**
     * a getter to get the working connection to the database
     * @return
     */
    public static Connection getDBConnect(){
        return dBConnect;
    }
    /**
     * closes the connection if the program crashes and when it finishes
     */
    public static void close() {
        try {
            dBConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
