package edu.ucalgary.oop;

import java.io.BufferedWriter;
    import java.io.*;
    import java.time.LocalDateTime;

public class WriteToErrorFile {

    public static void logAndExit(Exception ex, String userMessage) {
        BufferedWriter writer = null;

        try {
            File errorLogFile = new File("data", "errorlog.txt");

            //want the reader in append mode
            writer = new BufferedWriter(new FileWriter(errorLogFile, true));

            //Error log should contain timestamp of error and exception
            String timestamp = LocalDateTime.now().toString();
            writer.write("Timestamp: " + timestamp + " Exception: " + ex.toString());
            writer.newLine();

            // Inform the user
            System.out.println(userMessage);
        } catch (IOException e) {
            //catch any exceptions in writing to the file
            System.out.println("Unable to write to error log.");
            e.printStackTrace();
        } finally {
            // Make sure writer is closed at the end and program is terminated
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close error log file.");
                    e.printStackTrace();
                }
            }
        }
        System.exit(1);
    }
    
}
