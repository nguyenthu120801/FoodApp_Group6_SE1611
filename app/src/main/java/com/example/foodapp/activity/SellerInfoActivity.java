package com.example.foodapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.Entity.User;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.R;

public class SellerInfoActivity extends AppCompatActivity {
    private EditText fullNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText genderEditText;
    private SessionManager sessionManager;
    private DAOUser daoUser;
    public static  String money = "";
    private TextView moneyTxt;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_infor);
        img_back = findViewById(R.id.btn_OrderDetailback);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerInfoActivity.this, Seller_ViewProduct.class));
            }
        });
        daoUser = new DAOUser(this);
        sessionManager = new SessionManager(this);
        int userID = sessionManager.getUserID();
        moneyTxt = findViewById(R.id.user_money);
        if (userID == -1) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Log.d("infoOrder", "Chưa đăng nhập, về trang login");
            finish();
            return;
        }
        Log.d("infoOrder", "đã đăng nhập với userid : " + userID);
        User userLoggedIn = daoUser.getUser(String.valueOf(userID));
        fullNameEditText = findViewById(R.id.fullNameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        genderEditText = findViewById(R.id.genderEditText);
        fullNameEditText.setText(userLoggedIn.getFullName());
        phoneEditText.setText(userLoggedIn.getPhone());
        emailEditText.setText(userLoggedIn.getEmail());
        genderEditText.setText(userLoggedIn.getGender());
        moneyTxt.setText(String.valueOf(userLoggedIn.getMoney()));

        Button updateButton = findViewById(R.id.updateUser);
        updateButton.setOnClickListener(view -> updateUserInfo(view, userID));

    }



    private void updateUserInfo(View view, int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Do you really want to change your information?");
        builder.setPositiveButton("No", (dialog, which) -> {
            // Xử lý sự kiện khi người dùng chọn nút Không
        });
        builder.setNegativeButton("Yes", (dialog, which) -> {
            // Xử lý sự kiện khi người dùng chọn nút Có
            User user = new User();
            user.setID(userID);
            user.setFullName(fullNameEditText.getText().toString());
            user.setPhone(phoneEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setGender(genderEditText.getText().toString());

            int result = daoUser.updateUser(user);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            if (result != -1) {
                builder1.setTitle("Notify");
                builder1.setMessage("Info changed!");
                builder1.setPositiveButton("OK", null);
            } else {
                builder1.setTitle("Notify");
                builder1.setMessage("Can't change info!");
                builder1.setNegativeButton("OK", null);
            }
            builder1.show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}