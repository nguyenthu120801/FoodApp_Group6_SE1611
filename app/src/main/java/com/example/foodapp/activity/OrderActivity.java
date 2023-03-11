package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

import java.util.Date;

public class OrderActivity extends AppCompatActivity {
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(view -> insertOrder());
    }

    private void insertOrder(){
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
        order.setStatus(Order.STATUS_IN_PROGRESS);
        Log.d("infoOrder", "insert data : " + order);
        OrderDBHelper dbHelper = new OrderDBHelper(OrderActivity.this);
        boolean isSuccess = dbHelper.insertOrder(order);
        Toast.makeText(OrderActivity.this, "insert : "+isSuccess, Toast.LENGTH_SHORT).show();
        Log.d("infoOrder", "insert : " + isSuccess);
    }

}