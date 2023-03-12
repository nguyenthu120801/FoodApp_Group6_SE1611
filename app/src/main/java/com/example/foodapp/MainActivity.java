package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodapp.Entity.Category;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.foodapp.activity.ListUserOrderActivity;
import com.example.foodapp.activity.OrderActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    ImageView img_user;
    LinearLayout logout;
    TextView tv_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.LogOut);
        img_user =  findViewById(R.id.img_user);
        tv_welcome = findViewById(R.id.tv_welcome);
        recyclerViewCategory();
        // session
        SessionManager sessionManager = new SessionManager(MainActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(SessionManager.KEY_USERNAME);
        if(username != null) {
            tv_welcome.setText("Welcome, " + username);
        }
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        // when click button Logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(MainActivity.this);
                sessionManager.logoutUser();
                tv_welcome.setText("Order & Eat");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.rv_category);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        FloatingActionButton toOrderBtn = findViewById(R.id.to_order_btn);
        toOrderBtn.setOnClickListener(view -> toOrder());
        ArrayList<Category> categories = new ArrayList<>();
    }

    public void toOrder(  ) {
        Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
        startActivity(intent);
    }

    public void toListOrder(View view) {
        Intent intent = new Intent(MainActivity.this, ListUserOrderActivity.class);
        startActivity(intent);
    }
}