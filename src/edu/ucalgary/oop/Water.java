package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern; 
import java.time.LocalDate;

public class Water extends Supply{
    private String dateAllocated;
    private boolean isExpired;

    public Water(){
        super();
    }

    public Water(int quantity, String dateAllocated, String supplyID){
        super("water", quantity, supplyID);
        this.dateAllocated = dateAllocated;
    }

    public boolean setDateAllocated(String dateAllocated) {
        if(isValidDate(dateAllocated)){
            this.dateAllocated = dateAllocated;
            return true;
        }
        else{
            return false;
        }
    }

    public String getDateAllocated() {
        return dateAllocated;
    }

    public boolean isValidDate(String date) {
        // should be year-month-day
		Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher dateMatcher = datePattern.matcher(date);
		return dateMatcher.find();
	}

    public boolean isExpired(){
        if(this.getLocationAllocatedTo() != null){
            return true;
        }
        String dateAllocated = this.getDateAllocated();
        String[] allocatedParts = dateAllocated.split("-");
        int allocatedYear = Integer.parseInt(allocatedParts[0]);
        int allocatedMonth = Integer.parseInt(allocatedParts[1]);
        int allocatedDay = Integer.parseInt(allocatedParts[2]);
    
        String[] localDateParts = LocalDate.now().toString().split("-");
        int todayYear = Integer.parseInt(localDateParts[0]);
        int todayMonth = Integer.parseInt(localDateParts[1]);
        int todayDay = Integer.parseInt(localDateParts[2]);
    
        // Compare dates
        if ((todayYear == allocatedYear) && (todayMonth == allocatedMonth)){
            if(todayDay == allocatedDay){
                this.isExpired = false;
                return false;
            }
            else if (todayDay == (allocatedDay - 1)){
                this.isExpired = false;
                return false;
            }
        }
    
        this.isExpired = true;
        return true;
    }
    

    public void setIsExpired(boolean isExpired){
        this.isExpired = isExpired;
    }

    public boolean getIsExpired(){
        return this.isExpired;

    }
    
    
}
