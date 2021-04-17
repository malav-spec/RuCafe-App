package com.example.rucafe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class DonutActivity extends AppCompatActivity {

    private NumberPicker numberPicker;
    private String[] pickerVals;
    private Spinner donutSpinner;
    private CardView cardView;

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

    }

    public void getTotal(View view){

    }
}