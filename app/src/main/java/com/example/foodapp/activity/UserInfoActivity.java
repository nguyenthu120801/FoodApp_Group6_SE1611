package com.example.foodapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodapp.Entity.User;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;

public class UserInfoActivity extends AppCompatActivity {
    private EditText fullNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText genderEditText;
    private EditText roleNameEditText;
    private SessionManager sessionManager;
    private Button buttonRecharge;
    private DAOUser daoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        daoUser = new DAOUser(this);
        sessionManager = new SessionManager(this);
        int userID = sessionManager.getUserID();
        buttonRecharge = findViewById(R.id.recharge);
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
        roleNameEditText = findViewById(R.id.roleNameEditText);

        fullNameEditText.setText(userLoggedIn.getFullName());
        phoneEditText.setText(userLoggedIn.getPhone());
        emailEditText.setText(userLoggedIn.getEmail());
        genderEditText.setText(userLoggedIn.getGender());
        roleNameEditText.setText(userLoggedIn.getRoleName());


        Button updateButton = findViewById(R.id.updateUser);
        updateButton.setOnClickListener(view -> updateUserInfo(view, userID));
        Recharge(userID);
    }

    private void Recharge(int userID){
        buttonRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RechargeActivity.UserID = userID;
                Intent intent = new Intent(UserInfoActivity.this, RechargeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUserInfo(View view, int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn thay đổi thông tin không?");
        builder.setPositiveButton("Không", (dialog, which) -> {
            // Xử lý sự kiện khi người dùng chọn nút Không
        });
        builder.setNegativeButton("Có", (dialog, which) -> {
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
                builder1.setTitle("Thông báo");
                builder1.setMessage("Thay đổi thông tin thành công!");
                builder1.setPositiveButton("OK", null);
            } else {
                builder1.setTitle("Thông báo");
                builder1.setMessage("Thay đổi thông tin thất bại!");
                builder1.setNegativeButton("OK", null);
            }
            builder1.show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
