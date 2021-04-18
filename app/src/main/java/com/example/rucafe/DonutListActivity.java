package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DonutListActivity extends AppCompatActivity {

    private ListView donutList;
    private String[] donutOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_list);

        donutList = (ListView) findViewById(R.id.donutList);

        Bundle b=this.getIntent().getExtras();
        donutOrders = b.getStringArray("ORDERS_LIST");


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list, android.R.id.text1, donutOrders);

        donutList.setAdapter(adapter);
    }


}