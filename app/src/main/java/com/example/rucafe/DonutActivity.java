package com.example.rucafe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

public class DonutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private NumberPicker numberPicker;
    private String[] pickerVals;
    private Spinner donutSpinner;
    private EditText total;
    private final ArrayList<String> orders = new ArrayList();
    private final ArrayList<String> orderDetails = new ArrayList();
    private final ArrayList<Double> orderPrices = new ArrayList<>();
    private String[] donutOrders;
    private AlertDialog alertDialog;
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

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getTotal(null);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialogMesgDonut)
                .setTitle(R.string.dialogTitle);

        builder.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(orders.size() == 0){
                    Toast.makeText(getApplicationContext(),"No donuts to order",Toast.LENGTH_SHORT).show();
                    openNewActivity();
                }

                for(int i = 0; i < orders.size(); i++){


                    StringTokenizer st = new StringTokenizer(orders.get(i).replaceAll("\\s", ""), ",");
                    Donuts donut = getDonutOrder(st);
                    currentOrder.add(donut);
                }
                String stringTotal = getSubTotal();
                currentOrder.setTotal(currentOrder.getTotal()+Double.parseDouble(stringTotal.substring(1)));
                Toast.makeText(getApplicationContext(),"Order added",Toast.LENGTH_SHORT).show();
                openNewActivity();
            }
        });
        builder.setNeutralButton(R.string.negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        alertDialog = builder.create();

    }

    public void populateArray(){
        int i;
        donutOrders = new String[orderDetails.size()];
        for(i = 0; i < donutOrders.length; i++){
            donutOrders[i] = orderDetails.get(i);
        }
    }

    public void showList(View view){
        Log.d("Size", Integer.toString(orderDetails.size()));
        populateArray();
        Bundle b = new Bundle();
        b.putStringArray("ORDERS_LIST", donutOrders);

        Intent intent = new Intent(this, DonutListActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public ArrayList<String> getOrders(){
        return orders;
    }

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

    public String getSubTotal(){
        int i;
        double sum = 0;

        for(i = 0; i < orderPrices.size(); i++){
            sum += orderPrices.get(i);
        }

        return "$ " + sum;
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getTotal(View view){
        total.setText("");
        donutOrder.setType(donutSpinner.getSelectedItem().toString());
        donutOrder.setQuantity(numberPicker.getValue());
        total.setText(donutOrder.toString());
    }

    public void showDialog(View view){
        alertDialog.show();
    }

    public void addToList(View view){
        orders.add(donutOrder.getDetails());
        orderDetails.add(donutOrder.getFullDonutOrder());
        Toast.makeText(getApplicationContext(),"Donut added to list",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getTotal(null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}