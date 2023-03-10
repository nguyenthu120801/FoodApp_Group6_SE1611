package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.foodapp.Adapter.ProductAdapter;
import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View_ProductActivity extends AppCompatActivity {
    private ListView view;
    private Spinner spinner;
    private ProductAdapter adapter;
    private final Map<String, Integer> map = new HashMap<>();
    private final List<Category> listCategory = new ArrayList<>();
    private final DAOCategory daoCategory = new DAOCategory(this);
    private final DAOProduct daoProduct = new DAOProduct(this);
    private void AddCategory(){
        daoCategory.AddCategory(new Category("Hai san",0));
        daoCategory.AddCategory(new Category("Pho",0));
        daoCategory.AddCategory(new Category("Bun",0));
        daoCategory.AddCategory(new Category("Com rang",0));
    }

    private void AddProduct(){
        daoProduct.AddProduct(new Product("Oc huong",0,15.25,1,null));
        daoProduct.AddProduct(new Product("Pho bo",1,13.75,2,""));
        daoProduct.AddProduct(new Product("Bun oc",1,16.00,3,""));
        daoProduct.AddProduct(new Product("Com rang cua",1,18.75,4,""));
    }

    private void setMap(){
        for(Category category: listCategory){
            map.put(category.getName(),category.getID());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        spinner = findViewById(R.id.spinner);
        setMap();
    }
}