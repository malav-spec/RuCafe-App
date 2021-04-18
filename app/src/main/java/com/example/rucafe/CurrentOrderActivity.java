package com.example.rucafe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.rucafe.MainActivity.currentOrder;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        list = findViewById(R.id.list);

        ArrayList<String> tutorials = currentOrder.makeAL();
        tutorials.add("h");
        ArrayAdapter<String> List = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tutorials);
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
        setContentView(R.layout.activity_current_order);
        list = findViewById(R.id.list);
        ArrayList<String> tutorials = currentOrder.makeAL();
        tutorials.add("hp");
        ArrayAdapter<String> List = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tutorials);
        list.setAdapter(List);
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        Button editText = (Button) findViewById(R.id.butt);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        finish();



    }

}