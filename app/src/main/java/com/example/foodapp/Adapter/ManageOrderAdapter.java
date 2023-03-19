package com.example.foodapp.Adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.ManageOrderActivity;
import com.example.foodapp.Model.ConnectDatabase;
import com.example.foodapp.Model.DAOManageOrder;
import com.example.foodapp.OnClick;
import com.example.foodapp.R;
import com.example.foodapp.activity.UpdateManageOrder;

import java.util.List;

public class ManageOrderAdapter extends RecyclerView.Adapter<ManageOrderAdapter.ViewHolder>  {
    private final Context context;
    private final OnClick onClick;
    List<ManageOrder> listManageOrder;
    public interface IClickItemManageOrder{
        void updateManageOrder(ManageOrder manageOrder);
    }

    public ManageOrderAdapter(Context context, OnClick onClick, List<ManageOrder> listManageOrder) {
        this.context = context;
        this.onClick = onClick;
        this.listManageOrder = listManageOrder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_order, parent, false);
        return new ManageOrderAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ManageOrder manageOrder = listManageOrder.get(position);
        holder.id.setText(String.valueOf(listManageOrder.get(position).getOrderID()));
        holder.name.setText(listManageOrder.get(position).getFullName());

        //holder.status.setText(listManageOrder.get(position).getStatus());

        holder.address.setText(String.valueOf(listManageOrder.get(position).getAddress()));
        holder.status.setText(String.valueOf(listManageOrder.get(position).getStatus()));
        /*if(new DAOManageOrder(context).getStatus(listManageOrder.get(position).getOrderID()) == 0){
            holder.spin_status.setVisibility(View.INVISIBLE);
            holder.tv18.setVisibility(View.VISIBLE);
        }*/

        /*else if(new DAOManageOrder(context).getStatus(listManageOrder.get(position).getOrderID()) == 2){
            holder.spin_status.setVisibility(View.INVISIBLE);
            holder.tv18.setVisibility(View.VISIBLE);
            holder.tv18.setText("Cancel");
        }*/
    }

    @Override
    public int getItemCount() {
        return listManageOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Button btnUpdate;
        TextView id, name, status, address, tv18;
        ImageView img_Product;
        Spinner spin_status;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);

            //status = itemView.findViewById(R.id.status);
            btnUpdate = itemView.findViewById(R.id.btn_update);



            tv18 = itemView.findViewById(R.id.textView18);

            status = itemView.findViewById(R.id.status);
            address = itemView.findViewById(R.id.address);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, new String[]{"Completed", "Cancel"});

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.clickItem(Integer.parseInt(id.getText().toString()));

                }
            });

            //spin_status.setSelection(-1);




        }





    }



}
