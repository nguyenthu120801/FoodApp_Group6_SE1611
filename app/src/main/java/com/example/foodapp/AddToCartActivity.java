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
import android.widget.TextView;

import com.example.foodapp.Entity.Food;
import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;
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
    List<OrderDetail> orderDetailList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        rcv = findViewById(R.id.rec_foodCart);
        tv_total = findViewById(R.id.tv_totalPrice);
        tv_notification = findViewById(R.id.tv_noti);
        orderDetailList = new DAOOrderDetail(this).getListOrderDetail(2);
        double total = 0;
        int pID = 0;
        int id = getIntent().getIntExtra("id", 0);
        Product product = new DAOProduct(this).getProduct(id);
        if(product != null || orderDetailList.size() != 0) {
            for (OrderDetail o : orderDetailList) {
               if(o.getProductID() == id){
                   pID = id;
                   o.setQuantity(o.getQuantity() + 1);
                   int n = new DAOOrderDetail(this).UpdateOrderDetail(new OrderDetail(o.getDetailID(), o.getOrderID(), o.getProductID(), o.getQuantity()));
               }
               productList.add(new DAOProduct(this).getProduct(o.getProductID()));

            }
            if(pID != id){
                orderDetailList.add(new OrderDetail(2, id, 1));
                new DAOOrderDetail(this).AddOrderDetail(new OrderDetail(2, id, 1));
                productList.add(new DAOProduct(this).getProduct(id));
            }


        }
        if(productList.size() != 0) {
            for (Product p : productList) {
                total += p.getPrice();
            }
            FoodAdapter adapter = new FoodAdapter(orderDetailList, total, productList, this::onPriceChange);
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