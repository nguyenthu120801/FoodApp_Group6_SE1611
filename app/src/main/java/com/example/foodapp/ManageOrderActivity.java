package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.foodapp.Adapter.ManageOrderAdapter;
import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.DAOManageOrder;
import com.example.foodapp.activity.LoginActivity;
import com.example.foodapp.activity.Seller_Edit_DeleteProduct;
import com.example.foodapp.activity.Seller_ViewProduct;
import com.example.foodapp.activity.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ManageOrderActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    List<ManageOrder> manageOrderList;
    RecyclerView manageorder;
    Spinner spin_status;
    private LinearLayout homePage;
    private LinearLayout logout;
    private final List<String> listStatus = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        logout = findViewById(R.id.LogOut);
        homePage = findViewById(R.id.btn_homePage);
        manageorder = findViewById(R.id.rv_allManageOrder);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manageorder.setLayoutManager(linearLayoutManager);
        DAOManageOrder manageOrder = new DAOManageOrder(this);
        manageOrderList = manageOrder.getAllManageOrder();
        adapter = new ManageOrderAdapter(this, manageOrderList);
        manageorder.setAdapter(adapter);
        Logout();
        HomePage();
    }

    private void HomePage(){
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageOrderActivity.this, Seller_ViewProduct.class));
            }
        });
    }

    private void Logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(ManageOrderActivity.this);
                sessionManager.logoutUser();
                Intent intent = new Intent(ManageOrderActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}