package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.foodapp.Entity.Adapter.ManageOrderAdapter;
import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.DAOManageOrder;
import com.example.foodapp.activity.UpdateManageOrder;
import com.example.foodapp.activity.LoginActivity;
import com.example.foodapp.activity.Seller_ViewProduct;
import com.example.foodapp.activity.SessionManager;

import java.util.List;

public class ManageOrderActivity extends AppCompatActivity implements OnClick, OnUpdateStatus {
    private static  final int MY_REQUEST_CODE = 10;
    private RecyclerView.Adapter adapter;
    private  ManageOrderAdapter manageOrderAdapter;
    List<ManageOrder> manageOrderList;
    RecyclerView manageorder;
    Spinner spin_status;
    private LinearLayout homePage;
    private LinearLayout logout;
    private final DAOManageOrder dao = new DAOManageOrder(this);
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
        adapter = new ManageOrderAdapter(this,this::clickItem ,this::clickUpdate , manageOrderList);
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


    @Override
    public void clickItem(int id) {
        Intent intent = new Intent(ManageOrderActivity.this, UpdateManageOrder.class);
        intent.putExtra("ID", id);
        Order order = dao.getOrder(id);
        if(!order.getStatus().equalsIgnoreCase(Order.STATUS_COMPLETED) && !order.getStatus().equalsIgnoreCase(Order.STATUS_CANCELLED)){
            startActivity(intent);
        }

    }


    /*public void clickUpdate(ManageOrder manageOrder){
        Intent intent = new Intent(ManageOrderActivity.this, UpdateManageOrder.class);
        String status = manageOrder.getStatus();
        intent.putExtra("status", status);
        startActivity(intent);
    }*/

    @Override
    public void clickUpdate(String status) {
        Intent intent = new Intent(ManageOrderActivity.this, UpdateManageOrder.class);
        intent.putExtra("status", status);
        startActivity(intent);
    }
}