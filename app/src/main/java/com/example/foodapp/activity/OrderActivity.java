package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.foodapp.Entity.Adapter.OrderAdapter;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    Button btnSubmit;
    Button btnViewOrder;
    OrderDBHelper orderDBHelper;
    EditText editTextOrderId;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderDBHelper = new OrderDBHelper(OrderActivity.this);
        btnSubmit = findViewById(R.id.btn_submit);
        btnViewOrder = findViewById(R.id.btn_view_order);
        editTextOrderId = findViewById(R.id.text_order_id);
        btnSubmit.setOnClickListener(view -> insertOrder());
        btnViewOrder.setOnClickListener(view -> searchOrder(editTextOrderId.getText().toString()));

    }

    private void insertOrder() {
        EditText etUserId = findViewById(R.id.et_user_id);
        EditText etAddress = findViewById(R.id.et_address);

        int userId = Integer.parseInt(etUserId.getText().toString());
        String address = etAddress.getText().toString();
        Date orderDate = new Date();

        Order order = new Order();
        order.setUserID(userId);
        order.setAddress(address);
        order.setOrderDate(orderDate.toString());
        order.setShipDate(orderDate.toString());
        order.setStatus(Order.STATUS_NEW);
        Log.d("infoOrder", "Begin call database");
        int orderid = orderDBHelper.insertOrder(order);
        Log.d("infoOrder", "End call database, is success : " + orderid);
    }

    private void searchOrder(String orderId) {
        List<Order> orderList = new ArrayList<>();
        if (orderId.trim().equals("") || orderId.isEmpty()) {
            orderList = orderDBHelper.getAllOrders();
        } else {
            Order order = orderDBHelper.searchOrder(orderId);
            Log.d("infoOrder", "Dữ liệu trả về : " + order);
            orderList.add(order);

        }
        recyclerView = findViewById(R.id.rvViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        recyclerView.setAdapter(new OrderAdapter(OrderActivity.this, orderList));
    }



}