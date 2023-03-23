package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.HashMap;

public class FoodDetailActivity extends AppCompatActivity {
    TextView tv_foodName;
    TextView tv_price;
    TextView tv_description, tv_quantity;
    ImageView imv, btn_plus, btn_minus;
    ImageButton btn_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        tv_foodName = findViewById(R.id.tv_foodName);
        tv_price = findViewById(R.id.tv_price);
        tv_description = findViewById(R.id.tv_description);
        imv = findViewById(R.id.img_food);
        btn_plus = findViewById(R.id.plus2);
        btn_minus =findViewById(R.id.minus2);
        btn_back = findViewById(R.id.btn_back);
        tv_quantity = findViewById(R.id.tv_quantity2);

        int id = getIntent().getIntExtra("id", 0);
        Product product = new DAOProduct(this).getProduct(id);
        SessionManager sessionManager = new SessionManager(FoodDetailActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(SessionManager.KEY_USERNAME);

        findViewById(R.id.btn_Add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username == null) {
                    startActivity(new Intent(FoodDetailActivity.this, LoginActivity.class));
                }else {
                    Intent intent = new Intent(FoodDetailActivity.this, AddToCartActivity.class);
                    int productID = product.getProductID();
                    intent.putExtra("id", productID);
                    intent.putExtra("quantity", Integer.parseInt(tv_quantity.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = Integer.parseInt(tv_quantity.getText().toString()) + 1;
                tv_quantity.setText(String.valueOf(newQuantity));
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tv_quantity.getText().toString()) > 1){
                    int newQuantity = Integer.parseInt(tv_quantity.getText().toString()) - 1;
                    tv_quantity.setText(String.valueOf(newQuantity));
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetailActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        tv_foodName.setText(product.getProductName());
        tv_price.setText(String.valueOf(product.getPrice()));
        tv_description.setText(product.getDescription());
        Glide.with(this)
                .load(product.getImage().trim())
                .into(imv);
    }
}