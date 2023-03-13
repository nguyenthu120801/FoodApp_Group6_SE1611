package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.Entity.Cart;
import com.example.foodapp.Entity.Food;
import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCart;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.activity.OrderActivity;

import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    TextView tv_total;
    TextView tv_notification;
    List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        tv_total = findViewById(R.id.tv_totalPrice);
        tv_notification = findViewById(R.id.tv_noti);
        List<Cart> cartList = new DAOCart(this).getListCart(2);
        rcv = findViewById(R.id.rv_category);
        int id = getIntent().getIntExtra("id", 0);
        LoadRecyclerView(cartList, id);

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

    @Override
    public void onDeleteItem(int cartID) {
        Log.d("size", String.valueOf(cartID));
        new DAOCart(this).DeleteCart(cartID);
        List<Cart> cartList = new DAOCart(this).getListCart(2);
        LoadRecyclerView(cartList, -1);
    }

    public void LoadRecyclerView(List<Cart> cartList, int id){
        double total = 0;
        int pID = 0;

        Product product = new DAOProduct(this).getProduct(id);
        if(product != null) {
            for (Cart c : cartList) {
                if(c.getProductID() == id){
                    pID = id;
                    c.setQuantity(c.getQuantity() + 1);
                    int n = new DAOCart(this).UpdateCart(c);
                }
            }
            if(pID != id){
                cartList.add(new Cart(2, product.getProductID(), 1));
                new DAOCart(this).AddCart(new Cart(2, product.getProductID(), 1));
            }
        }
        if(cartList.size() != 0) {
            for (Cart c : cartList){
                Product p = new DAOProduct(this).getProduct(c.getProductID());
                total += p.getPrice() * c.getQuantity();
                productList.add(p);
            }
            FoodAdapter adapter = new FoodAdapter(cartList, total, productList, this);
            rcv.setLayoutManager(new LinearLayoutManager(this));
            rcv.setAdapter(adapter);
        }else{

            tv_notification.setText("Cart is empty, please buy food to continues");
            tv_notification.setVisibility(View.VISIBLE);
            tv_notification.setTextColor(Color.RED);
            ((Button)findViewById(R.id.btn_checkout)).setVisibility(View.GONE);
            tv_total.setVisibility(View.GONE);
            ((TextView)findViewById(R.id.textView10)).setVisibility(View.GONE);
        }
    }
}