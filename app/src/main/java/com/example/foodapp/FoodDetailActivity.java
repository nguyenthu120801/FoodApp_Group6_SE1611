package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;

public class FoodDetailActivity extends AppCompatActivity {
    TextView tv_foodName;
    TextView tv_price;
    TextView tv_description;
    ImageView imv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        tv_foodName = findViewById(R.id.tv_foodName);
        tv_price = findViewById(R.id.tv_price);
        tv_description = findViewById(R.id.tv_description);
        imv = findViewById(R.id.img_food);
        //int id = getIntent().getIntExtra("id", 0);
        int id = 4;
        Product product = new DAOProduct(this).getProduct(id);

        Product p = new DAOProduct(this).getProduct(3);

        ((Button)findViewById(R.id.btn_Add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetailActivity.this, AddToCartActivity.class);
                int productID = product.getProductID();
                intent.putExtra("id", productID);
                startActivity(intent);
            }
        });

        tv_foodName.setText(product.getProductName());
        tv_price.setText("$" + product.getPrice());
        tv_description.setText(product.getDescription());
        imv.setImageResource(product.getImage());
    }
}