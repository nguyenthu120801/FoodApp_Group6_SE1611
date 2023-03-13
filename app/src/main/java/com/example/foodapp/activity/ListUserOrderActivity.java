package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.Adapter.OrderAdapter;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Entity.User;
import com.example.foodapp.LoginActivity;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;
import com.example.foodapp.SessionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ListUserOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Order> orderList;
    OrderDBHelper orderDBHelper;
    SessionManager sessionManager;
    DAOUser daoUser;

    private void LoadData() {
        orderDBHelper = new OrderDBHelper(ListUserOrderActivity.this);
        daoUser = new DAOUser(ListUserOrderActivity.this);
        sessionManager = new SessionManager(this);
        String username = sessionManager.getUsername();
        String password = sessionManager.getPassword();
        if(username == null && password == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Log.d("infoOrder","Chưa đăng nhập, về trang login");
        return;
        }
        Log.d("infoOrder","đã đăng nhập với tk : "+username+", mk : "+ password);
        int userID = daoUser.getUserId(username, password);
        orderList = orderDBHelper.searchOrder(userID);
        recyclerView = findViewById(R.id.rv_list_user_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListUserOrderActivity.this));
        recyclerView.setAdapter(new OrderAdapter(ListUserOrderActivity.this, orderList));
        TextView userIdText;
        userIdText = findViewById(R.id.testUserId);
        userIdText.setText("User Id: " + userID); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_order);
        LoadData();

    }


}