package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.Entity.Food;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.activity.OrderActivity;

import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    TextView tv_total;
    TextView tv_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        rcv = findViewById(R.id.rec_foodCart);
        tv_total = findViewById(R.id.tv_totalPrice);
        tv_notification = findViewById(R.id.tv_noti);
        List<Product> productList = new ArrayList<>();
        double total = 0;
        int id = getIntent().getIntExtra("id", 0);
        Product product = new DAOProduct(this).getProduct(id);
        if(product != null) {
            productList.add(product);
        }
        if(productList.size() != 0) {
            for (Product p : productList) {
                total += p.getPrice();
            }
            FoodAdapter adapter = new FoodAdapter(total, productList, this::onPriceChange);
            rcv.setLayoutManager(new LinearLayoutManager(this));
            rcv.setAdapter(adapter);
        }else{
            tv_notification.setText("Cart is empty, please buy food to continues");
            tv_notification.setVisibility(View.VISIBLE);
            tv_notification.setTextColor(Color.RED);
            ((Button)findViewById(R.id.btn_checkout)).setVisibility(View.GONE);
            tv_total.setVisibility(View.GONE);
            ((TextView)findViewById(R.id.tv_text)).setVisibility(View.GONE);
        }
        


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