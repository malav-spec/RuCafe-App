package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import Model.Order;
import Model.StoreOrder;

/**
 * Activity for Main view
 * @author Malav Doshi and Herik Patel
 */
public class MainActivity extends AppCompatActivity {

       /**
     * Represent current order in the cart
     */
    public static Order currentOrder = new Order();
    /**
     * Represent All previous orders
     */
    public static StoreOrder allOrder = new StoreOrder();

    /**
     * Used to create and display view when the activity is invoked
     * @param savedInstanceState It is State of the instance of type Bundle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /**
     * Used to change the current activity to CoffeeActivity
     * @param view Parameter of type View is passed
     */
    public void toCoffee(View view){
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Used to change the current activity to DonutActivity
     * @param view Parameter of type View is passed
     */
    public void toDonut(View view){
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Used to change the current activity to CurrentOrderActivity
     * @param view Parameter of type View is passed
     */
    public void toCurrentOrder(View view){
        Intent intent = new Intent(this, OrderDetails.class);
        startActivity(intent);
    }

    /**
     * Used to change the current activity to AllOrders
     * @param view Parameter of type View is passed
     */
    public void toPrev(View view){
        Intent intent = new Intent(this, StoreOrders.class);
        startActivity(intent);
    }
}
