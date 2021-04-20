package com.example.rucafe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.StringTokenizer;
import static com.example.rucafe.MainActivity.currentOrder;
import static com.example.rucafe.MainActivity.allOrder;


/**
 * Activity for current order view
 * @author Malav Doshi and Herik Patel
 */

public class CurrentOrderActivity extends AppCompatActivity {

    /**
     * Used to represent Listview for the current order items
     */
    private ListView list;
    /**
     * Used to represent edit text for different amount
     */
    private EditText current_total, current_total_tax, taxAmount;

    /**
     * Used to create and display view when the activity is invoked
     * @param savedInstanceState It is State of the instance of type Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        list = findViewById(R.id.list);
        ArrayAdapter<String> List = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currentOrder.makeAL());
        list.setAdapter(List);
        currentOrder.setTotal(roundOff(currentOrder.getTotal()));
        current_total = findViewById(R.id.current_total);
        current_total_tax = findViewById(R.id.current_total_tax);
        taxAmount=findViewById(R.id.taxAmount);
        String total = "" + currentOrder.getTotal();
        String total_with_tax = "" + currentOrder.getTotalWithTax();
        current_total.setText(total);
        current_total_tax.setText(total_with_tax);
        int taxInt = (int)((100*currentOrder.getTotalWithTax()) - (100*currentOrder.getTotal()));
        double tax = (double) taxInt;
        if(tax != 0.0) {
            tax = tax / 100;
            tax = roundOff(tax);
        }

        taxAmount.setText("" + tax);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                updateTotal(selectedItem);
                remove(position);
            }
        });
    }

    /**
     * Used to remove an Item from order
     * @param pos Position of item which we want to remove
     */
    private void remove(int pos){
        currentOrder.remove("" + pos);
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Used to add to store order when order is placed
     * @param view Parameter of type View is passed
     */
    public void addToAllOrders(View view){
        ArrayList<String> temp = currentOrder.makeAL();
        String str = "";
        for(int i = 0; i < temp.size(); i++){
            str = str + temp.get(i) + "\n";
        }
        if(currentOrder.getTotal() == 0.0){
            finish();
            return;
        }
        str = str + "Total Amount = $" + currentOrder.getTotalWithTax() + "\n";
        allOrder.add(str);
        currentOrder.clearAll();
        finish();
    }

    /**
     * Used to update total when an item is removed from the list
     * @param str String of the order
     */
    private void updateTotal(String str){
        StringTokenizer st = new StringTokenizer(str, ",");
        String amountInString = "";
        while(st.hasMoreTokens()){
            amountInString = st.nextToken();
        }
        Double amount = Double.parseDouble(amountInString);
        currentOrder.setTotal(currentOrder.getTotal() - amount);
    }

    /**
     * Used to round off a Double value to two decimal places
     * @param number The value which we want to round off
     * @return A double value round off to two decimal places
     */

    private double roundOff(double number){ //rounds off a number to two decimal places
        return Math.round(number * 100.0) / 100.0;
    }

}