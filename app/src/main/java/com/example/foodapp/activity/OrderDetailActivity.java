package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Adapter.OrderDetailAdapter;
import com.example.foodapp.Adapter.OrderDetailViewHolder;
import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.R;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<OrderDetail> orderDetails;
    DAOOrderDetail daoOrderDetail;
    TextView totalPriceText;
    OrderDetailAdapter orderDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        recyclerView = findViewById(R.id.rv_order_detail);
        daoOrderDetail = new DAOOrderDetail(this);
        Intent intent = getIntent();
        int orderId = -1;
        String orderIdString = intent.getStringExtra("order_id");
        if (orderIdString != null || !orderIdString.trim().isEmpty()) {
            orderId = Integer.parseInt(intent.getStringExtra("order_id"));
            Log.d("infoOrder", "Lấy được order id từ activity trước, order id : " + orderId);
            orderDetails = daoOrderDetail.getOrderDetails(orderId);
            Log.d("infoOrder", "List order là : ");
            for (OrderDetail orderDetail : orderDetails) {
                Log.d("infoOrder", "order detail id : " + orderDetail.getDetailID());
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            orderDetailAdapter = new OrderDetailAdapter(this, orderDetails);
            recyclerView.setAdapter(orderDetailAdapter);
            totalPriceText = findViewById(R.id.total_price);
            double total = orderDetailAdapter.getTotalPrice();
            totalPriceText.setText("Thành tiền : " + total);
        } else {
            Log.d("infoOrder", "Không lấy được order id từ activity trước");
        }

    }




}