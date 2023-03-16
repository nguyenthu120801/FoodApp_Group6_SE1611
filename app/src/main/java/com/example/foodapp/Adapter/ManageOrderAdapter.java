package com.example.foodapp.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.ManageOrder;
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
        TextView id, name, status, quantity;
        ImageView img_Product;
        Spinner spin_status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            spin_status = itemView.findViewById(R.id.spin_status);
            quantity = itemView.findViewById(R.id.quantity);
            img_Product = itemView.findViewById(R.id.img_manageorder);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, new String[]{"Completed", "Cancel"});
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_status.setAdapter(adapter);



            spin_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                DAOManageOrder manageOrder = new DAOManageOrder(context);
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    DAOManageOrder dao = new DAOManageOrder(context);
                    String selectedText = adapterView.getSelectedItem().toString();



                    ContentValues values = new ContentValues();

                    Toast.makeText(itemView.getContext(),"Hello" + selectedText, Toast.LENGTH_LONG).show();
                    //UpdateManageOrder(selectedText);


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }





    }



}
