package Model;
/**
 * Used to represent MenuItems
 * @author Malav Doshi and Herik Patel
 */
public class MenuItem {

    /**
     * Used to represent Price
     */
    private Double price;

    /**
     * Constructor for MenuItem class
     */
    public MenuItem(){
    }

    /**
     * Used to get details of Menuitem
     * @return Empty strings overridden in child class
     */
    public String getDetails(){
        return "";
    }

    /**
     * Constructor for MenuItem class
     * @param price Prices which we want to set
     */
    public MenuItem(Double price){
        this.price = price;
    }

    /**
     *Used to set price of item
     * @param p Price which we want to set
     */
    public void setPrice(Double p){
        this.price = p;
    }

    /**
     * Used to get price
     * @return Price of menu item
     */
    public Double getPrice(){
        return price;
    }
    /**
     * Used to get quantity
     * @return Quantity of menu item
     */
    public int getQuantity() {
        return 0;
    }
    /**
     * Used to get flavor
     * @return Null overridden in child class
     */
    public String getFlavor() {
        return null;
    }

    /**
     * Used to get type of item
     * @return Null overridden in child class
     */
    public String getType() {
        return null;
    }

}
