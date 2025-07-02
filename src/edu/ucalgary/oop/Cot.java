package edu.ucalgary.oop;
/**This class represnts a cot type supply in the program 
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/

public class Cot extends Supply{

    private String roomNumber;
    private String grid;
    /**
     * This is the default constructor which calls super to constrcut the supply type base object first
     */
    public Cot(){
        super();
    }
    /**
     * Creates a cot object with the specfied values
     * @param quantity how many cots stored together
     * @param roomNumber roomNumber the cot is set to
     * @param grid location of the cot
     * @param supplyID id of the cot as allocated in the database
     */
    public Cot(int quantity, String roomNumber, String grid, String supplyID){
        super("Cot", quantity, supplyID);
        this.roomNumber = roomNumber;
        this.grid = grid;
    }
    /**
     * getter to return the grid value of the cot
     * @return the grid location of the cot
     */
    public String getGrid(){
        return this.grid;
    }
    /**
     * getter to return teh room number of the cot
     * @return room numebr of the cot
     */
    public String getRoomNumber(){
        return this.roomNumber;
    }
    /**
     * setter to set the room number in the cot
     * @param roomNumber to set the cot to
     */
    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }
    /**
     * setter to set the grid in the cot
     * @param grid the grid to set the cot to have
     */
    public void setGrid(String grid){
        this.grid = grid;
    }
    
}
