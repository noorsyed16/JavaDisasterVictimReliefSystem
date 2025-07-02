# Disaster Victim Relief System
This Java project implements a disaster relief management system designed to help relief workers manage and track information about people affected by a disaster. The system connects to a PostgreSQL database and offers a user friendly command line interface for data entry and updates.

# Key Features
User-Friendly Interface (CLI): 
Relief workers can navigate through menu options. The interface provides error handling for typos and prompts users to correct invalid entries. 

Disaster Victim Management: 
Add, view, and update records for people affected by disasters, including their personal information, medical history, location, and family

Database Integration: 
The application loads data from a PostgreSQL database and syncs all updates back to it. It makes updates to disaster victim records, medical records, inquiries, locations, and supploies.

Inventory Management: 
Supports different item types including personal belongings, cots (with room/grid info), water (expires after one day if assigned to a person), and blankets. Items can be allocated to individuals or locations based on availability

Inquirer System: 
Individuals and disaster victims can log inquiries to locate missing people. 

Supply, Location and Medical Record Allocation: 
Each person can have their location set, their medical records on file, and has a list of supplies allocated to them

Language Options: 
Users can specify the language and region (e.g., en-CA) when launching the program. Text is dynamically loaded from XML files, allowing future support for multiple languages. Currently, an xml file was created for english using the language code en-CA

Error Handling: 
All critical errors are logged to a timestamped errorlog.txt file in the data directory. Users are given error messages and the program exits cleanly.

# Project Structure
src/edu/ucalgary/oop/ — Main source code, including Main.java
test/edu/ucalgary/oop/ — Unit tests
data/ — Language files, error logs, and any required input files
UML.pdf — Diagram showing OOP structure

# To run the main:

To run the code I put the following commands in. I first navigated to my root directory, which in my case was ensf380IA. First I compiled

C:\Users\noors\ensf380IA>javac -cp ".;C:\Users\noors\OneDrive\Desktop\ensf380\postgresql-42.7.5.jar" src\edu\ucalgary\oop\*.java

Then to run the main I put the following in the command line:

java -cp ".;C:\Users\noors\OneDrive\Desktop\ensf380\postgresql-42.7.5.jar;src" edu.ucalgary.oop.Main

# To run tests:

This is an example to run CotTest.java. To run tests I put the following in the command line from my root directory ensf380IA to compile all

javac -cp ".;C:\Users\noors\OneDrive\Desktop\ensf380\hamcrest-core-1.3.jar;C:\Users\noors\OneDrive\Desktop\ensf380\junit-4.13.2.jar" src/edu/ucalgary/oop/*.java test/edu/ucalgary/oop/CotTest.java

Then to run the tests I put the following in the command line

java -cp ".;C:\Users\noors\OneDrive\Desktop\ensf380\hamcrest-core-1.3.jar;C:\Users\noors\OneDrive\Desktop\ensf380\junit-4.13.2.jar;src;test" org.junit.runner.JUnitCore edu.ucalgary.oop.CotTest

# To run the java doc

To run the javadoc I used the following command for Blanket.java the rest would follow a similar format. This will save them into a docs folder for easy access: 

javadoc -d docs -classpath ".;C:\Users\noors\OneDrive\Desktop\ensf380\postgresql-42.7.5.jar;src" src\edu\ucalgary\oop\Blanket.java

Note: Javadoc is completed for Blanket.java, BlanketController.java, Cot.java. CotController.java, DatabaseConnection.java, DatabaseView.java, Inquiry.java and InquiryController.java. 
