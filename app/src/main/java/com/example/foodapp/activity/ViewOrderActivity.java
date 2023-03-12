package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

public class ViewOrderActivity extends AppCompatActivity {

    Button cancelOrderBtn;
    TextView orderIdText;
    OrderDBHelper orderDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        orderDBHelper = new OrderDBHelper(ViewOrderActivity.this);
        orderIdText = findViewById(R.id.order_id_text);
        cancelOrderBtn=  findViewById(R.id.cancel_order_btn);
        String orderId = orderIdText.getText().toString();
        cancelOrderBtn.setOnClickListener(view -> cancelOrder(orderId));
    }

    private void cancelOrder(String orderId){
       boolean isSuccess =  orderDBHelper.removeOrder(orderId);
        Toast.makeText(this, "is success : " + isSuccess, Toast.LENGTH_SHORT).show();
    }
}