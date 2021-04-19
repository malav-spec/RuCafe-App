package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.os.Bundle;

import static com.example.rucafe.MainActivity.allOrder;
import static com.example.rucafe.MainActivity.currentOrder;

public class AllOrders extends AppCompatActivity {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);
        list = findViewById(R.id.list);
        ArrayAdapter<String> List = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allOrder.makeAL());
        list.setAdapter(List);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                remove(position);
            }
        });
    }

    private void remove(int pos){
        allOrder.remove(""+pos);
        Intent intent = new Intent(this, AllOrders.class);
        startActivity(intent);
        finish();
    }

    public void mainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}