package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.ManageOrderActivity;
import com.example.foodapp.Model.DAOManageOrder;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

public class UpdateManageOrder extends AppCompatActivity {
    private final DAOManageOrder dao = new DAOManageOrder(this);
    private Spinner spinStatus;
    private Button btnUpdateStatus;
    private ManageOrder manageOrder;
    String selectedItem ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_manage_order);

        spinStatus = findViewById(R.id.spin_status);
        btnUpdateStatus = findViewById(R.id.btn_updateStatus);


        /*String status = getIntent().getStringExtra("status");
        int status_number = -1;

        if(status.equals("New")){
            status_number = 0;
        } else if (status.equals("Completed")) {
            status_number = 1;
        }

        spinStatus.setSelection(status_number);*/



        int id = getIntent().getIntExtra("ID", 0);

        //spinStatus.setSelection(new DAOManageOrder(this).getStatus(id));



        spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boolean check = false;
                DAOManageOrder dao = new DAOManageOrder(UpdateManageOrder.this);
                selectedItem = adapterView.getItemAtPosition(i).toString();

                dao.UpdateStatus(id, selectedItem);
                return;
                //Order order = new Order(idc,7 ,"sdfsdfsdf","sdfsdf", "sdfsdf","sdfsdf");
                //Toast.makeText(itemView.getContext(),"Update success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("Messsafa","ádasdasad" );
            }
        });
        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dao.UpdateStatus(id, selectedItem);
                Intent intent = new Intent(UpdateManageOrder.this, ManageOrderActivity.class);
                startActivity(intent);
            }
        });





    }

}