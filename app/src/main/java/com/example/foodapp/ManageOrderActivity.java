package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.foodapp.Adapter.ManageOrderAdapter;
import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Model.DAOManageOrder;
import com.example.foodapp.activity.UpdateManageOrder;

import java.util.List;

public class ManageOrderActivity extends AppCompatActivity implements OnClick {
    private RecyclerView.Adapter adapter;
    private  ManageOrderAdapter manageOrderAdapter;
    List<ManageOrder> manageOrderList;
    RecyclerView manageorder;
    Spinner spin_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);

        manageorder = findViewById(R.id.rv_allManageOrder);
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manageorder.setLayoutManager(linearLayoutManager);
        DAOManageOrder manageOrder = new DAOManageOrder(this);
        manageOrderList = manageOrder.getAllManageOrder();
        adapter = new ManageOrderAdapter(this,this::clickItem , manageOrderList);
        manageorder.setAdapter(adapter);



    }


    @Override
    public void clickItem(int id) {
        Intent intent = new Intent(ManageOrderActivity.this, UpdateManageOrder.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}