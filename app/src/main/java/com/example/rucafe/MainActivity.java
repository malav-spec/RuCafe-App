package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import Model.Order;

public class MainActivity extends AppCompatActivity {

    public static Order currentOrder = new Order();

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
}