package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.foodapp.Adapter.ManageOrderAdapter;
import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Model.DAOManageOrder;

import java.util.List;

public class ManageOrderActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
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
        adapter = new ManageOrderAdapter(this, manageOrderList);
        manageorder.setAdapter(adapter);
        String[] spin_status = {"Completed", "Cancel"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spin_status );


    }
}