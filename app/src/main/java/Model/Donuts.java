package Model;
import java.util.ArrayList;
/**
 * Used to represent Donut Object
 * @author Malav Doshi and Herik Patel
 */
public class Donuts extends MenuItem implements Customizable{

    /**
     * Used to represent type of donut
     */
    private String type= "";
    /**
     * Used to quantity of donut
     */
    private int quantity = 0;
    /**
     * Used to represent flavor of donut
     */
    private String flavor= "";
    /**
     * Used to represent order as list of donut
     */
    private ArrayList<Donuts> donutList = new ArrayList<>();

    /**
     *  Constructor for Donut class
     * @param price Price of donut
     * @param type Kind of donut
     * @param flavor of donut
     * @param quantity Number of donuts
     */
    public Donuts(Double price, String type, String flavor, int quantity){
        super(price);
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Constructor for Donut class
     */
    public Donuts(){
    }

    /**
     * Used to get type of donut
     * @return Type of donut
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Used to set type of Donut
     * @param type Donut type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Used to get amount of Donuts in an order
     * @return Number of items
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Used to set amount of Donuts in an order
     * @param quantity Number of items
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    /**
     * Used to get flavor of donut
     * @return Donut flavor
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Used to set flavor of donut
     * @param flavor Donut flavor
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    /**
     * Used to roundoff item price
     * @param number Price is passed
     * @return Double value which is rounded price
     */
    public double roundOff(double number){ //rounds off a number to two decimal places
        return Math.round(number * 100.0) / 100.0;
    }

    /**
     * Used to get price according to donut type
     * @return Price according to donut
     */
    public double itemPrice(){

        double price_type = 0;

        if(this.type.equals("Yeast Donut") || this.type.equals("YeastDonut")){
            price_type = 1.39;
        }
        else if(this.type.equals("Cake Donut") || this.type.equals("CakeDonut")){
            price_type = 1.59;
        }
        else if(this.type.equals("Donut Holes") || this.type.equals("DonutHoles")){
            price_type = 0.33;
        }

        super.setPrice(roundOff(price_type * this.quantity));

        return roundOff(price_type * quantity);
    }

    /**
     * Used to get details about a order
     * @return Order details as String
     */
    @Override
    public String getDetails(){
        return type + ", " + flavor + ", " + quantity  + ", " + itemPrice();
    }

    /**
     * Used to return item price as string value
     * @return Item price as String
     */
    @Override
    public String toString(){
        return "$"+itemPrice();
    }

    /**
     * Used to Donuts to list
     * @param obj Donut passed as object
     * @return True is added else returns False
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Donuts){
            Donuts donut = (Donuts) obj;
            donutList.add(donut);
            return true;
        }
        return false;
    }

    /**
     * Used to remove donut item from order
     * @param obj obj Donut passed as object
     * @return False
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
