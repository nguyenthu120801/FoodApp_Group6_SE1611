package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.Adapter.OrderAdapter;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListUserOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Order> orderList;
    OrderDBHelper orderDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_order);
        orderDBHelper = new OrderDBHelper(ListUserOrderActivity.this);
        recyclerView = findViewById(R.id.rv_list_user_order);
        orderList = orderDBHelper.getAllOrders();
        recyclerView.setLayoutManager(new LinearLayoutManager(ListUserOrderActivity.this));
        recyclerView.setAdapter(new OrderAdapter(ListUserOrderActivity.this, orderList));
    }
}