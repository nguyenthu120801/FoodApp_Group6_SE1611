package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodapp.Entity.Adapter.ProductAdapter;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;
import com.example.foodapp.onProductItemClick;

import java.util.HashMap;
import java.util.List;

public class ShowAllProduct extends AppCompatActivity implements onProductItemClick {
    private RecyclerView.Adapter adapter;
    RecyclerView rv_allProduct;
    List<Product> productList;
    String username;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_product);
        id = getIntent().getIntExtra("cate_id", -1);
        recyclerViewAllProduct();
        SessionManager sessionManager = new SessionManager(ShowAllProduct.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        username = user.get(SessionManager.KEY_USERNAME);

    }
    private void recyclerViewAllProduct(){
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_allProduct = findViewById(R.id.rv_allProdcut);
        rv_allProduct.setLayoutManager(linearLayoutManager);
        DAOProduct daoProduct = new DAOProduct(this);
        if(id > 0){
            productList = daoProduct.getProductByCategory(id);
        }else {
            productList = daoProduct.ListProduct();
        }
        adapter = new ProductAdapter(this, productList, this);
        rv_allProduct.setAdapter(adapter);
    }

    @Override
    public void onProductClick(int id, String activity) {
        if (username == null) {
            startActivity(new Intent(ShowAllProduct.this, LoginActivity.class));
        } else {
            if (activity.equals("AddToCart")) {
                Intent intent = new Intent(this, AddToCartActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }
        if(activity.equals("FoodDetail")) {
            Intent intent = new Intent(this, FoodDetailActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    @Override
    public void onCategoryItemClick(int id) {

    }
}