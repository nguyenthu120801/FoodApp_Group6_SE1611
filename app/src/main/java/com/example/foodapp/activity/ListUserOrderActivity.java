package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.foodapp.Adapter.OrderAdapter;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.OnRefreshViewListner;
import com.example.foodapp.R;

import java.util.List;

public class ListUserOrderActivity extends AppCompatActivity implements OnRefreshViewListner {
    RecyclerView recyclerView;
    List<Order> orderList;
    OrderDBHelper orderDBHelper;
    SessionManager sessionManager;
    DAOUser daoUser;

    private void LoadData() {

        orderDBHelper = new OrderDBHelper(ListUserOrderActivity.this);
        daoUser = new DAOUser(ListUserOrderActivity.this);
        sessionManager = new SessionManager(this);
        int userID = sessionManager.getUserID();
        if(userID == -1){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Log.d("infoOrder","Chưa đăng nhập, về trang login");
            finish();
            return;
        }
        Log.d("infoOrder","đã đăng nhập với userid : " + userID);
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


    @Override
    public void refreshView() {
        finish();
        startActivity(getIntent());
    }


}