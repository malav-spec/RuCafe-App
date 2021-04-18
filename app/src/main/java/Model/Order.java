package Model;

import java.util.ArrayList;
/**
 * Used to represent Current order
 * @author Malav Doshi and Herik Patel
 */
public class Order implements Customizable {
    /**
     * Represent Order number
     */
    private int orderNumber = 0 ;
    /**
     * Represent Total
     */
    private double total = 0.0;
    /**
     * Represent Total with tax
     */
    private double totalWithTax = 0.0;

    /**
     * Represent list of items of orders
     */
    ArrayList<MenuItem> itemsList = new ArrayList<>();

    /**
     * Used to get particular order as String
     * @param obj Instance passed whose String value we want
     * @return String value of instance
     */
    public String toString(Object obj){
        if (obj instanceof Coffee) {
            MenuItem coffeeItem = (MenuItem) obj;
            return coffeeItem.getDetails();
        } else if (obj instanceof Donuts) {
            MenuItem donutItem = (MenuItem) obj;
            return donutItem.getDetails();
        }
        return "";
    }

    /**
     * Used to calculate tax on order
     * @return Amount with tax
     */
    public double getTotalWithTax() {
        totalWithTax = total + total*0.0625;
        totalWithTax = Math.round(totalWithTax * 100.0) / 100.0;
        return totalWithTax;
    }

    /**
     * Used to get total price without tax
     * @return Total price
     */
    public double getTotal() {
        return total;
    }

    /**
     * Used to set Total price of order
     * @param total New total which we want to update
     */
    public void setTotal(double total){
        this.total=total;
    }

    /**
     * Used to add to list of current orders
     * @param obj Object which we want to add
     * @return True if added else returns False
     */
    @Override
    public boolean add(Object obj) {
        orderNumber++;
        if (obj instanceof Coffee) {
            MenuItem coffeeItem = (MenuItem) obj;
            itemsList.add(coffeeItem);
            return true;
        } else if (obj instanceof Donuts) {
            MenuItem donutItem = (MenuItem) obj;
            itemsList.add(donutItem);
            return true;
        }
        return false;
    }

    /**
     * Used to remove element from list
     * @param obj Object which we want to remove
     * @return True if removed else returns False
     */
    @Override
    public boolean remove(Object obj) {
        if(orderNumber<0) {
            return false;
        }
        orderNumber--;
        itemsList.remove(Integer.parseInt(obj.toString()));
        return true;
    }

    public int getOrderNumber(){
        return orderNumber;
    }

    /**
     *Used to Array List of String for Order values
     * @return ArrayList of String
     */
    public ArrayList<String> makeAL() {
        ArrayList<String> orders = new ArrayList();
        for(int i=0;i<itemsList.size();i++)
        {
            orders.add(toString(itemsList.get(i)));
        }
        return orders;
    }

    /**
     * Used to remove all elements from the list
     */
    public void clearAll(){
        itemsList.clear();
        this.total=0.0;
    }
}
