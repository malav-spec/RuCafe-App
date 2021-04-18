package com.example.rucafe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.rucafe.MainActivity.currentOrder;
import static com.example.rucafe.MainActivity.allOrder;
public class AllOrderActivity extends AppCompatActivity {
    private ListView list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allorders);
    }
}
