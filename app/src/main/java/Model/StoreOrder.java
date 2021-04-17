package Model;

import java.util.ArrayList;
/**
 * Used to represent all orders
 * @author Malav Doshi and Herik Patel
 */
public class StoreOrder implements Customizable{
    /**
     * Used to represent Order Number
     */
    private int orderNumber = 0;
    /**
     * Used to Represent Arraylist that stores all previous orders
     */
    private ArrayList<String> orders = new ArrayList();
    /**
     * Constructor for StoreOrder
     */
    public StoreOrder(){

    }
    /**
     * Used to Add a order to array list
     * @param obj Order which we want to add
     * @return True if order was added successfully
     */
    @Override
    public boolean add(Object obj) {
        orderNumber++;
        String ord = (String) obj;
        orders.add(ord);
        return true;
    }

    /**
     * Used to Return strung arraylist of orders
     * @return Arraylist of String
     */
    public ArrayList<String> makeAL(){
        return orders;
    }

    /**
     * Used to remove order from the list
     * @param obj Which we want to remove
     * @return True if order was removed successfully
     */
    @Override
    public boolean remove(Object obj) {
        orderNumber--;
        orders.remove(Integer.parseInt(obj.toString()));
        return true;
    }
}
