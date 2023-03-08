package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.Entity.Food;
import com.example.foodapp.activity.OrderActivity;

import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        rcv = findViewById(R.id.rec_foodCart);
        List<Food> foodList = new ArrayList<>();
        //Food food = (Food) getIntent().getSerializableExtra("nunu");
        for(int i =0; i<3; i++) {
            Food f1 = new Food(i, "món ngon" + i, R.drawable.pizza, 8);
            foodList.add(f1);
        }
        
        FoodAdapter adapter = new FoodAdapter(foodList, this::onPriceChange);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);

        ((Button)findViewById(R.id.btn_checkout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToCartActivity.this, OrderActivity.class);
            }
        });

    }

    @Override
    public void onPriceChange(int price) {

    }
}