package com.example.rucafe;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Model.Donuts;
import static com.example.rucafe.MainActivity.currentOrder;

/**
 * Activity for Donut view
 * @author Malav Doshi and Herik Patel
 */
public class DonutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Number Picker for selecting quantity
     */
    private NumberPicker numberPicker;

    /**
     * String array to store
     */
    private String[] pickerVals;

    /**
     * Spinner to select Donut flavors
     */
    private Spinner donutSpinner;

    /**
     * EditText for setting the subtotal
     */
    private EditText total;

    /**
     * ArrayList to store the donut order as String
     */
    private ArrayList<String> orders = new ArrayList();

    /**
     * ArrayList to store the details of donut order
     */
    private ArrayList<String> orderDetails = new ArrayList();

    /**
     * Array List to store the price of order
     */
    private ArrayList<Double> orderPrices = new ArrayList<>();

    /**
     * String Array to store the donut order in a formatted string
     */
    private String[] donutOrders;

    /**
     * AlertDialog for order confirmation
     */
    private AlertDialog alertDialog;

    /**
     * Used to take the donut order
     */
    Donuts donutOrder = new Donuts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        donutSpinner = (Spinner) findViewById(R.id.donutSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donutSpinner.setAdapter(adapter);

        pickerVals = new String[] {"1","2","3","4","5","6","7","8","9","10"};
        numberPicker = findViewById(R.id.quantityPicker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setDisplayedValues(pickerVals);

        total = findViewById(R.id.totalEditBox);
        donutSpinner.setOnItemSelectedListener(this);
        setTitle("Donuts");
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /**
             * Method invokes when values is changed in gui
             * @param dialog Type of DialogInterface
             * @param oldVal Previous value for the object
             * @param newVal New value for the object
             */
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getTotal(null);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialogMesgDonut)
                .setTitle(R.string.dialogTitle);


        builder.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
            /**
             * Method invokes when order is pressed in gui
             * @param dialog Type of DialogInterface
             * @param id Id for the button
             */
            public void onClick(DialogInterface dialog, int id) {
                if(orders.size() == -1){
                    Toast.makeText(getApplicationContext(),"No donuts to order",Toast.LENGTH_SHORT).show();
                    openNewActivity();
                    finish();
                }

                for(int i = 0; i < orders.size(); i++){


                    StringTokenizer st = new StringTokenizer(orders.get(i).replaceAll("\\s", ""), ",");
                    Donuts donut = getDonutOrder(st);
                    donut.itemPrice();
                    currentOrder.add(donut);
                    //currentOrder.add(donutOrder);
                }
                String stringTotal = getSubTotal();
                //currentOrder.setTotal(currentOrder.getTotal()+Double.parseDouble(stringTotal.substring(1)));
                Toast.makeText(getApplicationContext(),"Order added",Toast.LENGTH_SHORT).show();
                openNewActivity();
            }
        });
        builder.setNeutralButton(R.string.negative, new DialogInterface.OnClickListener() {
            /**
             * Method invokes when button is pressed in gui
             * @param dialog Type of DialogInterface
             * @param id Id for the button
             */
            public void onClick(DialogInterface dialog, int id) {
                removeFromList();
            }
        });

        alertDialog = builder.create();

        if(savedInstanceState != null){
            donutOrders = savedInstanceState.getStringArray("DONUT_ORDERS");
            orderDetails = savedInstanceState.getStringArrayList("ORDER_DETAILS");
        }

    }

    /**
     * Used to initialize donutOrders array
     */
    public void populateArray(){
        //Log.d("Size of orderDetails", Integer.toString(orderDetails.size()));
        donutOrders = new String[orderDetails.size()];
        for(int i = 0; i < donutOrders.length; i++){
            donutOrders[i] = orderDetails.get(i);
        }
    }

    /**
     * Used to show the Donut list
     * @param view Parameter type of view is passed
     */
    public void showList(View view){
        Log.d("Size", Integer.toString(orderDetails.size()));
        populateArray();
        Bundle b = new Bundle();
        b.putStringArray("ORDERS_LIST", donutOrders);

        Intent intent = new Intent(this, DonutListActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getOrders(){
        return orders;
    }

    /**
     * Used to get the donut order details like price, quantity and flavor
     * @param st String Tokenizer object
     * @return Donut object
     */
    public Donuts getDonutOrder(StringTokenizer st){
        Donuts donut;
        String dType = "";
        int quantity = 0;
        double price = 0;

        while(st.hasMoreTokens()){
            dType = st.nextToken();
            quantity = Integer.parseInt(st.nextToken());
            price = Double.parseDouble(st.nextToken());
        }

        donut = new Donuts(price, dType, quantity);
        return donut;
    }

    /**
     * Helper method to calculate and display the subtotal
     * @return String with the subtotal
     */
    public String getSubTotal(){
        double sum = 0;

        for(int i = 0; i < orderPrices.size(); i++){
            sum += orderPrices.get(i);
        }

        return "$ " + sum;
    }

    /**
     * Used to open new activity
     */
    public void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Used to set the subtotal
     * @param view Parameter of type View is passed
     */
    public void getTotal(View view){
        total.setText("");
        donutOrder.setType(donutSpinner.getSelectedItem().toString());
        donutOrder.setQuantity(numberPicker.getValue());
        total.setText(donutOrder.toString());
    }

    /**
     * Used to pop up the Alert Dialog for confirming order
     * @param view Parameter type of View is Passed
     */
    public void showDialog(View view){
        alertDialog.show();
    }

    /**
     * Used to add the Donut order to the current order
     * @param view Parameter of type View is passed
     */
    public void addToList(View view){
        orders.add(donutOrder.getDetails());
        orderDetails.add(donutOrder.getFullDonutOrder());
        double db = donutOrder.itemPrice() + currentOrder.getTotal();
        currentOrder.setTotal(db);
        showDialog(view);
    }

    /**
     * Remove donut order form the list
     */
    public void removeFromList(){
        orders.remove(donutOrder.getDetails());
    }

    /**
     * Listener for item selection for Spinner
     * @param parent Parameter of type AdapterView
     * @param view Parameter of type View
     * @param position Parameter of type int
     * @param id Parameter of type long
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getTotal(null);
    }

    /**
     * Not used
     * @param parent Parameter of type AdapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}