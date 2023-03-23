package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Entity.User;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.R;

public class RechargeActivity extends AppCompatActivity {
    public static int UserID = 0;
    private final DAOUser dao = new DAOUser(this);
    private EditText editID;
    private EditText editName;
    private EditText editPrice;
    private EditText editPass;
    private EditText editBankName;
    private EditText editBankNumber;
    private User user;
    private Button btnRecharge;
    private static final String PASSWORD_RECHARGE = "123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        editID = findViewById(R.id.edit_id);
        editName = findViewById(R.id.edit_full_name);
        editPrice = findViewById(R.id.edit_price);
        editPass = findViewById(R.id.edt_password);
        editBankName = findViewById(R.id.edit_bank_name);
        editBankNumber = findViewById(R.id.edit_bank_number);
        editID.setEnabled(false);
        editName.setEnabled(false);
        editBankNumber.setEnabled(false);
        editBankName.setEnabled(false);
        btnRecharge = findViewById(R.id.recharge);
        user = dao.getInfoUser(UserID);
        editID.setText(user.getID() + "");
        editName.setText(user.getFullName());
        editBankNumber.setText("28401837511");
        editBankName.setText("MB Bank");
        Recharge();
    }

    private void Recharge(){
       btnRecharge.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String Price = editPrice.getText().toString().trim();
               String password = editPass.getText().toString();
               if(Price.isEmpty()){
                   Toast.makeText(RechargeActivity.this, "You have to input price",Toast.LENGTH_SHORT).show();
               }else if(Double.parseDouble(Price) == 0){
                   Toast.makeText(RechargeActivity.this, "Price must be greater than 0",Toast.LENGTH_SHORT).show();
               }else if(!password.equals(PASSWORD_RECHARGE)){
                   Toast.makeText(RechargeActivity.this, "Invalid password",Toast.LENGTH_SHORT).show();
               }else{
                   user.setMoney(user.getMoney() + Double.parseDouble(Price));
                   Log.e("Money",user.getMoney() + "");
                   int number = dao.UpdateMoney(user.getMoney() , user.getID());
                   if(number > 0){
                       Toast.makeText(RechargeActivity.this, "Recharge successful",Toast.LENGTH_SHORT).show();
                   }
               }
           }
       });
    }
}