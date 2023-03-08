package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.foodapp.R;
import com.example.foodapp.database.OrderDBHelper;
import com.example.foodapp.entity.Order;

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
        Date shipDate = new Date();
        int status = 0;

        Order order = new Order(userId, address, orderDate, shipDate, status);
        Log.d("infoOrder", "insert data : " + order);
        OrderDBHelper dbHelper = new OrderDBHelper(OrderActivity.this);
        boolean isSuccess = dbHelper.insertOrder(order);
        Log.d("infoOrder", "insert : " + isSuccess);
    }

}