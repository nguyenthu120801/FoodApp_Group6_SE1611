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

import com.example.foodapp.Entity.Category;

import java.util.ArrayList;

import com.example.foodapp.activity.ListUserOrderActivity;
import com.example.foodapp.activity.OrderActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    ImageView img_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
    }


    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.rv_category);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        FloatingActionButton toOrderBtn = findViewById(R.id.to_order_btn);
        toOrderBtn.setOnClickListener(view -> toOrder());
        img_user =  findViewById(R.id.img_user);
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

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