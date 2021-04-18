package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import Model.Order;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import Model.Donuts;
import Model.Order;
import Model.StoreOrder;
import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {

       /**
     * Represent current order in the cart
     */
    public static Order currentOrder = new Order();
    /**
     * Represent All previous orders
     */
    public static StoreOrder allOrder = new StoreOrder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        Donuts dnt = new Donuts(22.0,"Chocolate Glazed",2);
        currentOrder.add(dnt);
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        Button editText = (Button) findViewById(R.id.butt);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void toCoffee(View view){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    public void toDonut(View view){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }
}
