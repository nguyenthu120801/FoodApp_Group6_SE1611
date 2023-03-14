package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Entity.User;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.R;

public class LoginActivity extends AppCompatActivity {
    TextView tv_register;
    EditText edt_username;
    EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_register =  findViewById(R.id.tv_register);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigupActivity.class);
                startActivity(intent);
            }
        });
        ((Button)findViewById(R.id.btn_Login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(LoginActivity.this);
                String username = edt_username.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                DAOUser daoUser = new DAOUser(LoginActivity.this);
                User user = daoUser.getUser(username, password);
                if (username.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(user != null && user.getRoleName().equals(User.ROLE_CUSTOMER)){
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    sessionManager.createLoginSession(user.getUsername(), user.getPassword());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(user != null && user.getRoleName().equals(User.ROLE_SELLER)){
                    Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    sessionManager.createLoginSession(user.getUsername(), user.getPassword());
                    Intent intent = new Intent(LoginActivity.this, View_ProductActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}