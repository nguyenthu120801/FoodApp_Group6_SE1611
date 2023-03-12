package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.foodapp.Adapter.OrderAdapter;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

import java.util.List;

public class ListUserOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Order> orderList;
    OrderDBHelper orderDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_order);
        Context context = new ListUserOrderActivity();
        orderDBHelper = new OrderDBHelper(context);
        orderList = orderDBHelper.getAllOrders();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new OrderAdapter(context,orderList));
    }
}