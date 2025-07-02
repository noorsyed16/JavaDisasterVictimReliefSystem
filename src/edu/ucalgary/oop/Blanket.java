package edu.ucalgary.oop;
/**This class is to represent a blanket in the supply and extends the abstract class supply 
 * @author Noor Syed
 * @version 1.0
 * Date: 2025-04-10
*/
public class Blanket extends Supply{
    /**default constructor */
    public Blanket(){
        super();
    }
    /**
     * Contructor for blanket class which taken in quantity and supplyID and creates a blanket object
     * @param quantity how many blankets are stored together
     * @param supplyID the supplyID of the blanket as in the database
     */
    public Blanket(int quantity, String supplyID){
        super("Blanket", quantity, supplyID);
    }
    
}
