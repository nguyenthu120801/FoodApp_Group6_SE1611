package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodapp.activity.OrderActivity;

public class AddToCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
    }

    public void checkOutOrder(View view) {
        Intent intent = new Intent(AddToCartActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}