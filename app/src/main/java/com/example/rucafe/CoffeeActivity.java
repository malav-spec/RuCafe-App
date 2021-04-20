package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.widget.Toast;
import Model.Coffee;
import static com.example.rucafe.MainActivity.currentOrder;

/**
 * Activity for Coffee view
 * @author Malav Doshi and Herik Patel
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Used to represent Checkbox for the coffee selection
     */
    private CheckBox milk, syrup, cream, caramel, whippedCream;
    /**
     * Used to represent Spinner for the coffee selection
     */
    private Spinner sizeSpinner;
    /**
     *Used to represent Total for the coffee order
     */
    private EditText total;
    /**
     * Used to store coffee order
     */
    Coffee coffeeOrder = new Coffee();
    /**
     * Used to represent Alert dialog for the coffee selection
     */
    private AlertDialog alertDialog;

    /**
     * Used to create and display view when the activity is invoked
     * @param savedInstanceState It is State of the instance of type Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        sizeSpinner = (Spinner) findViewById(R.id.SizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.size_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);

        milk = findViewById(R.id.milkCheckBox);
        syrup = findViewById(R.id.syrupCheckBox);
        cream = findViewById(R.id.creamCheckBox);
        caramel = findViewById(R.id.caramelCheckBox);
        whippedCream = findViewById(R.id.wcCheckBox);

        total = findViewById(R.id.totalEditText);
        sizeSpinner.setOnItemSelectedListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialogMesg)
                .setTitle(R.string.dialogTitle);

        builder.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                currentOrder.add(coffeeOrder);
                currentOrder.setTotal(Double.parseDouble(coffeeOrder.toString().substring(1))+ currentOrder.getTotal());
                currentOrder.setTotal(roundOff(currentOrder.getTotal()));
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

    /**
     * Used to round off a Double value to two decimal places
     * @param number The value which we want to round off
     * @return A double value round off to two decimal places
     */
    private double roundOff(double number){ //rounds off a number to two decimal places
        return Math.round(number * 100.0) / 100.0;
    }

    /**
     * Used to open new Activity when invoked
     */
    public void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Used to get total value of coffee order
     * @param view Parameter of type View is passed
     */
    public void getTotal(View view){
        total.setText("");
        coffeeOrder.setAddOn(0);

        coffeeOrder.setSize(sizeSpinner.getSelectedItem().toString());

        if(cream.isChecked()){
            coffeeOrder.increaseAddOn(cream.getText().toString());
        }
        if( milk.isChecked()){
            coffeeOrder.increaseAddOn(milk.getText().toString());
        }
        if(syrup.isChecked()){
            coffeeOrder.increaseAddOn(syrup.getText().toString());
        }
        if(caramel.isChecked()){
            coffeeOrder.increaseAddOn(caramel.getText().toString());
        }
        if(whippedCream.isChecked()){
            coffeeOrder.increaseAddOn(whippedCream.getText().toString());
        }

        total.setText(coffeeOrder.toString());
    }

    /**
     * Used show dialog when invoked
     * @param view Parameter of type View is passed
     */
    public void showDialog(View view){
        alertDialog.show();
    }


    /**
     * Used to get total when an item is selected
     * @param parent It is of type AdapterView
     * @param view Parameter of type View is passed
     * @param position position of the selected item
     * @param id value of id for selected item
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getTotal(null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}