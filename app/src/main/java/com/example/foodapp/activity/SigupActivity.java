package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.R;


public class SigupActivity extends AppCompatActivity {

    private Button btn_Register;
    RadioButton male, female;
    EditText fullname, username, password, email, phone;
    DAOUser DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        btn_Register = findViewById(R.id.btn_Register);

        fullname = findViewById(R.id.edt_fullname);
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        male = findViewById(R.id.rb_male);
        female = findViewById(R.id.rb_female);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        DB = new DAOUser(this);


        btn_Register.setOnClickListener(new View.OnClickListener() {
            String gt = "";
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gt = "Male";
                }
                else{
                    gt = "Female";
                }
                String fname = fullname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();

                String mail = email.getText().toString();
                String phone_number = phone.getText().toString();

                if(TextUtils.isEmpty(fname) || TextUtils.isEmpty(user) ||TextUtils.isEmpty(pass) ||TextUtils.isEmpty(mail) ||TextUtils.isEmpty(phone_number) ){
                    return;
                }
                Boolean insert = DB.insertUser(fname,user,pass,gt,mail,phone_number,"Customer");
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