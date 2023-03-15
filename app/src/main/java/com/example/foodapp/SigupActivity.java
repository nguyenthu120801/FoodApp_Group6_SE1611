package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.Model.UserDBHelper;

import java.util.Date;

public class SigupActivity extends AppCompatActivity {

    private Button btn_Register;
    EditText fullname, username, password, dob, email, phone;
    UserDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        btn_Register = findViewById(R.id.btn_Register);

        fullname = findViewById(R.id.edt_fullname);
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        dob = findViewById(R.id.edt_dob);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        DB = new UserDBHelper(this);


        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = fullname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String birth = dob.getText().toString();

                String mail = email.getText().toString();
                String phone_number = phone.getText().toString();


                Boolean insert = DB.insertUser(fname,user,pass,birth,mail,phone_number);
                if(insert == true){
                    Toast.makeText(SigupActivity.this, "Register successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SigupActivity.this, "Register NOT successfully", Toast.LENGTH_LONG).show();
                }






            }
        });
    }
}