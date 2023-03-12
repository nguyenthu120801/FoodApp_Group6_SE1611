package com.example.foodapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

public class UserCancelOrderActivity extends AppCompatActivity {

    Button cancelBtn;
    OrderDBHelper orderDBHelper;
    TextView orderIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        orderDBHelper = new OrderDBHelper(UserCancelOrderActivity.this);
        orderIdText = findViewById(R.id.order_id_text);
        cancelBtn = findViewById(R.id.cancel_order_btn);
        cancelBtn.setOnClickListener(view -> cancelOrder(""));


    }

    private void cancelOrder(String orderId) {
        boolean isSuccess = orderDBHelper.removeOrder(orderId);
        Toast.makeText(this, "is success : " + isSuccess, Toast.LENGTH_SHORT).show();
    }
}
