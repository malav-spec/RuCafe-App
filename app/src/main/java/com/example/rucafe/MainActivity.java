package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import Model.Order;
import Model.StoreOrder;


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
        setContentView(R.layout.activity_main);

    }


    public void toCoffee(View view){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    public void toDonut(View view){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    public void toCurrentOrder(View view){
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    public void toPrev(View view){
        Intent intent = new Intent(this, AllOrders.class);
        startActivity(intent);
    }
}
