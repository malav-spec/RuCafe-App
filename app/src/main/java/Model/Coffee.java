package Model;

/**
 * Used to represent Coffee Object
 * @author Malav Doshi and Herik Patel
 */
public class Coffee extends MenuItem implements Customizable{

    /**
     * Used to represent size of coffee
     */
    private String size = "";
    /**
     * Used to represent number of add in for coffee
     */
    private int num_add_in = 0;
    /**
     * Used to represent add in for coffee
     */
    private String[] addIn = new String[5];//List for all the addIn

    /**
     * Constructor for Coffee
     */
    public Coffee() {
    }

    /**
     * Used to increase addin
     * @param add Value of add in
     */
    public void increaseAddOn(String add){
        addIn[num_add_in] = add;
        num_add_in += 1;
    }

    /**
     * used to get Addin list
     * @return Array of String for add in
     */
    public String[] getAddInList(){
        return addIn;
    }

    /**
     * Used to get size of coffee
     * @return Size of coffee
     */
    public String getSize(){
        return size;
    }

    /**
     * Used to get Number of add in
     * @return Number of add in
     */
    public int getNum_add_in(){
        return num_add_in;
    }

    /**
     * Used to Set size
     * @param s Size of coffee
     */
    public void setSize(String s){
        size = s;
    }

    /**
     * Used to set Add in
     * @param num Number of add in
     */
    public void setAddOn(int num){
        num_add_in = num;
    }

    /**
     * Used to round of price of item
     * @param number Amount which we want to round off
     * @return Round off value
     */
    public double roundOff(double number){ //rounds off a number to two decimal places
        return Math.round(number * 100.0) / 100.0;
    }

    /**
     * Used to get item price for coffee
     * @return Price according to size
     */
    public double itemPrice(){

        double size_price = 0;

        if(size == null){
            size_price = 0.0;
        }
        else if(size.equals("Short")){
            size_price = 1.99;
        }
        else if(size.equals("Tall")){
            size_price = 2.49;
        }
        else if(size.equals("Grande")){
            size_price = 2.99;
        }
        else if(size.equals("Venti")){
            size_price = 3.49;
        }

        double add_in_cost = (0.2) * (num_add_in);
        super.setPrice(roundOff(size_price + add_in_cost));
        return size_price + add_in_cost;
    }

    /**
     * Used to return price as a String
     * @return Item price as String
     */
    @Override
    public String toString(){
        return "$" + roundOff(itemPrice());
    }

    /**
     * Used to add item to order
     * @param obj Item Which we want to ass
     * @return True if item is added else False
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * Used to get details for coffee
     * @return String as details for coffee
     */
    @Override
    public String getDetails(){
        String tostr = size + ", ";
        for(int i = 0; i < num_add_in ; i++){
            tostr = tostr+addIn[i] + ", ";
        }
        tostr = tostr + roundOff(itemPrice());
        return tostr;

    }

    /**
     * Used to items from order
     * @param obj Items which we want to remove
     * @return Boolean value
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }


}
