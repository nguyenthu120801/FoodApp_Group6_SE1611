package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodDetailActivity extends AppCompatActivity {
    TextView tv_foodName;
    TextView tv_price;
    TextView tv_description;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        tv_foodName = findViewById(R.id.tv_foodName);
        tv_price = findViewById(R.id.tv_price);
        tv_description = findViewById(R.id.tv_description);

        ((Button)findViewById(R.id.btn_Add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetailActivity.this, AddToCartActivity.class);
                int productID = 4;
                intent.putExtra("id", productID);
                startActivity(intent);
            }
        });
    }
}