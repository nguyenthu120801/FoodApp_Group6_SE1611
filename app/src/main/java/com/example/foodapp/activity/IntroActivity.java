package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.foodapp.Entity.User;
import com.example.foodapp.ManageOrderActivity;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.R;

import java.util.HashMap;


public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                //
            }
        });

    }


}