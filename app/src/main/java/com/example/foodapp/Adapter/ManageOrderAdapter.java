package com.example.foodapp.Adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.foodapp.R;

import java.util.List;

public class ManageOrderAdapter extends RecyclerView.Adapter<ManageOrderAdapter.ViewHolder>  {
    private Context context;
    List<ManageOrder> listManageOrder;

    public ManageOrderAdapter(Context context, List<ManageOrder> listManageOrder) {
        this.context = context;
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
        holder.id.setText(String.valueOf(listManageOrder.get(position).getOrderID()));
        holder.name.setText(listManageOrder.get(position).getProductName());

        //holder.status.setText(listManageOrder.get(position).getStatus());

        holder.quantity.setText(String.valueOf(listManageOrder.get(position).getQuantity()));
        holder.img_Product.setImageResource(listManageOrder.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return listManageOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView id, name, quantity;
        ImageView img_Product;
        Spinner spin_status;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            //status = itemView.findViewById(R.id.status);
            quantity = itemView.findViewById(R.id.quantity);
            img_Product = itemView.findViewById(R.id.img_manageorder);
            //btn_update = itemView.findViewById(R.id.btn_update);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, new String[]{"Completed", "Cancel"});
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_status.setAdapter(adapter);




        }





    }



}
