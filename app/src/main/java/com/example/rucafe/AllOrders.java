package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import static com.example.rucafe.MainActivity.allOrder;

/**
 * Activity for All orders view
 * @author Malav Doshi and Herik Patel
 */
public class AllOrders extends AppCompatActivity {
    /**
     * Used to represent Listview for the All orders
     */
    private ListView list;

    /**
     * Used to create and display view when the activity is invoked
     * @param savedInstanceState It is State of the instance of type Bundle
     */
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
                //String selectedItem = (String) parent.getItemAtPosition(position);
                remove(position);
            }
        });
    }


    /**
     * Used to remove elements from listview
     * @param pos Index number of which element we want to remove from the list view
     */
    private void remove(int pos){
        allOrder.remove(""+pos);
        Intent intent = new Intent(this, AllOrders.class);
        startActivity(intent);
        finish();
    }

}