package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.Entity.Food;
import com.example.foodapp.activity.OrderActivity;

import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    TextView tv_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        tv_total = findViewById(R.id.tv_totalPrice);
        List<Food> foodList = new ArrayList<>();
        double total = 0;
        //Food food = (Food) getIntent().getSerializableExtra("nunu");
        for(int i =0; i<3; i++) {
            Food f1 = new Food(i, "món ngon" + i, R.drawable.pizza, 8);
            foodList.add(f1);
            total+= f1.getPrice();
        }
        
        FoodAdapter adapter = new FoodAdapter(total, foodList, this::onPriceChange);
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
    public void onPriceChange(double price) {
        tv_total.setText("$" + price);
    }
}