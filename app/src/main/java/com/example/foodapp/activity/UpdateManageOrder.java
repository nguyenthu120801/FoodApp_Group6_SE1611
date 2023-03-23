package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.ManageOrderActivity;
import com.example.foodapp.Model.DAOManageOrder;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateManageOrder extends AppCompatActivity {
    private final DAOManageOrder dao = new DAOManageOrder(this);
    private Spinner spinStatus;
    private Button btnUpdateStatus;
    String selectedItem ;
    private boolean isUpdated = false;
    private List<String> listStatus;
    private ArrayAdapter<String> adapter;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_manage_order);
        spinStatus = findViewById(R.id.spin_status);
        btnUpdateStatus = findViewById(R.id.btn_updateStatus);
        listStatus = new ArrayList<>();
        setDataStatus();
        int id = getIntent().getIntExtra("ID", 0);
        order = dao.getOrder(id);
        setSelectedStatus();
        Update();
    }

    private void Update(){
        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItem = spinStatus.getSelectedItem().toString();
                dao.UpdateStatus(order.getOrderID(), selectedItem);
                Intent intent = new Intent(UpdateManageOrder.this, ManageOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSelectedStatus(){
        spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String status;
                if(isUpdated){
                    status = listStatus.get(i);
                }else{
                    status = order.getStatus();
                    isUpdated = true;
                }
                spinStatus.setSelection(adapter.getPosition(status));
                //dao.UpdateStatus(id, selectedItem);
                return;
                //Order order = new Order(idc,7 ,"sdfsdfsdf","sdfsdf", "sdfsdf","sdfsdf");
                //Toast.makeText(itemView.getContext(),"Update success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("Messsafa","ádasdasad" );
            }
        });
    }

    private void setDataStatus(){
        listStatus.add(Order.STATUS_NEW);
        listStatus.add(Order.STATUS_SHIPPING);
        listStatus.add(Order.STATUS_IS_PAID);
        listStatus.add(Order.STATUS_COMPLETED);
        listStatus.add(Order.STATUS_CANCELLED);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listStatus);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinStatus.setAdapter(adapter);
    }

}