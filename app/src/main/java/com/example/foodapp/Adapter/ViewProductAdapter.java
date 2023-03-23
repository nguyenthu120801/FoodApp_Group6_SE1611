package com.example.foodapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;
import com.example.foodapp.activity.Seller_ViewProduct;

import java.util.List;

public class ViewProductAdapter extends BaseAdapter {
    private  List<Product> list;
    public ViewProductAdapter(List<Product> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getProductID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view =  View.inflate(viewGroup.getContext(), R.layout.item_product, null);
        }
        DAOProduct daoProduct = new DAOProduct(viewGroup.getContext());
        Product product = (Product) getItem(i);
        TextView textName = view.findViewById(R.id.tv_product_name);
        TextView textPrice = view.findViewById(R.id.tv_product_price);
        ImageView image = view.findViewById(R.id.viewImage);
        TextView category = view.findViewById(R.id.tv_category);
        textName.setText(product.getProductName().length() <= 26 ? product.getProductName() : product.getProductName().substring(0,26) + "...");
        textPrice.setText(product.getPrice() + "");
        DAOCategory dao = new DAOCategory(viewGroup.getContext());
        String CategoryName = dao.getCategoryName(product.getCategoryID());
        category.setText(CategoryName);
        Glide.with(viewGroup.getContext())
                .load(product.getImage().trim())
                .into(image);
        TextView textDelete = view.findViewById(R.id.btn_Delete);
        textDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure want to delete product \""+product.getProductName()+"\"");
                builder.setPositiveButton("No", (dialog, which) -> {
                });

                builder.setNegativeButton("Yes", (dialog, which) -> {

                    DAOOrderDetail daoDetail = new DAOOrderDetail(viewGroup.getContext());
                    boolean check = daoDetail.CheckProductExist(product.getProductID());
                    AlertDialog.Builder builderDelete = new AlertDialog.Builder(viewGroup.getContext());
                    // if exist user buy
                    if(check){
                        builderDelete.setMessage("This product exists user buying");
                        builderDelete.setPositiveButton("OK", null);
                    }else{
                         int number = daoProduct.DeleteProduct(product.getProductID());
                        // if delete successful
                        if(number > 0){
                            builderDelete.setMessage("Delete successful");
                            builderDelete.setNegativeButton("OK", null);
                            List<Product> listProduct = daoProduct.getListProduct(null);
                            ViewProductAdapter adapter = new ViewProductAdapter(listProduct);
                            Seller_ViewProduct.listView.setAdapter(adapter);
                        }
                    }
                    builderDelete.show();
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }



}
