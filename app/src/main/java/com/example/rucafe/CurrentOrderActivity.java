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

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.rucafe.MainActivity.currentOrder;
import static com.example.rucafe.MainActivity.allOrder;


public class CurrentOrderActivity extends AppCompatActivity {

    private ListView list;
    private EditText current_total;
    private EditText current_total_tax;
    private EditText taxAmount;
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
        int taxInt = (int)((100*currentOrder.getTotalWithTax())-(100*currentOrder.getTotal()));
        double tax = (double) taxInt;
        if(tax!=0.0) {
            tax = tax / 100;
            tax = roundOff(tax);
        }

        taxAmount.setText(""+tax);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                updateTotal(selectedItem);
                remove(position);
            }
        });
    }

    private void remove(int pos){
        currentOrder.remove(""+pos);
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
        finish();
    }

    public void addToAllOrders(View view){
        ArrayList<String> temp = currentOrder.makeAL();
        String str = "";
        for(int i=0;i<temp.size();i++){
            str = str + temp.get(i) +"\n";
        }
        if(currentOrder.getTotal()==0.0){
            finish();
        }
        str = str + "Total Amount = $" + currentOrder.getTotalWithTax() + "\n";
        allOrder.add(str);
        currentOrder.clearAll();
        finish();
    }

    private void updateTotal(String str){
        StringTokenizer st = new StringTokenizer(str, ",");
        String amountInString="";
        while(st.hasMoreTokens()){
            amountInString = st.nextToken();
        }
        Double amount = Double.parseDouble(amountInString);
        currentOrder.setTotal(currentOrder.getTotal() - amount);
    }
    private double roundOff(double number){ //rounds off a number to two decimal places
        return Math.round(number * 100.0) / 100.0;
    }

}