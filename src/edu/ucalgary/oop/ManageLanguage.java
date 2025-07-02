package edu.ucalgary.oop;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageLanguage {
    // has a function to select language
    //read xml file to get langauge keys
    // parses all outputted strings to ensure they match the key values in the xml file
    private HashMap<String, String> translations;

    public ManageLanguage(){
        this.translations = new HashMap<>();
    }

    public void readFromXML(String languageName){
        String languageFilePath = languageName + ".xml";
        File xmlFile = new File("data", languageFilePath);
        //if file does not exist default to enCA
        if(!xmlFile.exists()){
            System.out.println("This langauge was not found, default language english will be used.");
            xmlFile = new File("data", "en-CA.xml");
        }
        
        StringBuilder content = new StringBuilder();
        try{
            BufferedReader fileInput = new BufferedReader(new FileReader(xmlFile));
            String line;

            while ((line = fileInput.readLine()) != null) {
                content.append(line);
            }
            fileInput.close();
        } catch (IOException e){ 
            System.out.println("Error occured reading the file");
        }

        //Clear any trnalastions that may occur from a previous file
        this.translations.clear();
        Pattern pattern = Pattern.compile("<translation>\\s*<key>(.*?)</key>\\s*<value>(.*?)</value>\\s*</translation>");
        Matcher matcher = pattern.matcher(content.toString());

        while (matcher.find()) {
            String key = matcher.group(1);  // the tag name (e.g., greeting, error)
            String value = matcher.group(2);  // the content inside the tag
            this.translations.put(key, value);
        }
    }

    public String translateText(String key) {
        if (translations != null && translations.containsKey(key)) {
            return translations.get(key);
        } else {
            return "[Missing translation for '" + key + "']";
        }
    }
}