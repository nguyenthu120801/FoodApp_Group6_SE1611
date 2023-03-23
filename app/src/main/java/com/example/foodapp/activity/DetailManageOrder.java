package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.foodapp.Adapter.DetailManageOrderAdapter;
import com.example.foodapp.Adapter.OrderDetailAdapter;
import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.R;

import java.util.List;

public class DetailManageOrder extends AppCompatActivity {

    RecyclerView recyclerView;
    List<OrderDetail> orderDetailsManage;
    DAOOrderDetail daoOrderDetail;
    TextView totalPriceText;
    DetailManageOrderAdapter detailManageOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_manage_order);

        recyclerView = findViewById(R.id.rv_orderDetailManage);
        daoOrderDetail = new DAOOrderDetail(this);
        Intent intent = getIntent();
        int orderId = -1;

            orderId = intent.getIntExtra("Detail", -1);
            Log.d("infoOrder", "Lấy được order id từ activity trước, order id : " + orderId);
            orderDetailsManage = daoOrderDetail.getOrderDetails(orderId);
            Log.d("infoOrder", "List order là : ");
            for (OrderDetail orderDetail : orderDetailsManage) {
                Log.d("infoOrder", "order detail id : " + orderDetail.getDetailID());
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            detailManageOrderAdapter = new DetailManageOrderAdapter(this, orderDetailsManage);
            recyclerView.setAdapter(detailManageOrderAdapter);
            //totalPriceText = findViewById(R.id.total_price);
            //double total = detailManageOrderAdapter.getTotalPrice();
            //totalPriceText.setText("Thành tiền : " + total);



    }
}