package com.example.foodapp.Adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
        holder.name.setText(listManageOrder.get(position).getFullName());

        //holder.status.setText(listManageOrder.get(position).getStatus());

        holder.address.setText(String.valueOf(listManageOrder.get(position).getAddress()));
        holder.spin_status.setSelection(new DAOManageOrder(context).getStatus(listManageOrder.get(position).getOrderID()));
        if(new DAOManageOrder(context).getStatus(listManageOrder.get(position).getOrderID()) == 0){
            holder.spin_status.setVisibility(View.INVISIBLE);
            holder.tv18.setVisibility(View.VISIBLE);
        }
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


        TextView id, name, status, address, tv18;
        ImageView img_Product;
        Spinner spin_status;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);

            //status = itemView.findViewById(R.id.status);
            //btn_update = itemView.findViewById(R.id.btn_update);



            tv18 = itemView.findViewById(R.id.textView18);

            spin_status = itemView.findViewById(R.id.spin_status);
            address = itemView.findViewById(R.id.address);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, new String[]{"Completed", "Cancel"});

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spin_status.setAdapter(adapter);



            //spin_status.setSelection(-1);

            spin_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    boolean check = false;
                    DAOManageOrder dao = new DAOManageOrder(context);
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    int idc = Integer.parseInt(id.getText().toString());

                    dao.UpdateStatus(idc, selectedItem);
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





    }



}
