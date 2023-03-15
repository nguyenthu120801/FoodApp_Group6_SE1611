package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.foodapp.Adapter.ViewProductAdapter;
import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller_ViewProduct extends AppCompatActivity {
    private ListView listView;
    private Spinner spinnerSearch;
    private ViewProductAdapter adapter;
    private final Map<String, Integer> map = new HashMap<>();
    private List<Category> listCategory;
    private List<Product> listProduct;
    private final List<String> listName = new ArrayList<>();
    private final DAOCategory daoCategory = new DAOCategory(this);
    private final DAOProduct daoProduct = new DAOProduct(this);
    private ArrayAdapter adapterArr;
    private Button buttonNext;
    private Button buttonBack;
    private int page = 1;
    private int numberPage;
    private Integer CategoryID = null;
    private Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_view_product);
        listView = findViewById(R.id.list);
        spinnerSearch = findViewById(R.id.spinner_search);
        numberPage = daoProduct.getNumberOfPage(null);
        buttonBack = findViewById(R.id.btnBack);
        buttonNext = findViewById(R.id.btnNext);
        numberPage = daoProduct.getNumberOfPage(CategoryID);
        buttonAdd = findViewById(R.id.btnAdd);
        setDataSearch();
        setMap();
        DisplayListProduct(page,CategoryID);
        setSelectedSearch();
        backPage();
        nextPage();
        setSelectedItem();
        AddProductActivity();
    }

    private void AddProductActivity(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Seller_ViewProduct.this , OrderManageActivity.Seller_AddProduct.class));
            }
        });
    }

    private void setSelectedItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) adapter.getItem(i);
                Seller_Edit_DeleteProduct.ProductID = product.getProductID();
                startActivity(new Intent(Seller_ViewProduct.this,Seller_Edit_DeleteProduct.class));
            }
        });

    }

    private void setDataSearch(){
        listCategory = daoCategory.getAllCategory();
        listName.add("ALL");
        for(Category category: listCategory){
            listName.add(category.getName());
        }
        adapterArr =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listName);
        adapterArr.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerSearch.setAdapter(adapterArr);
    }


    private void setMap(){
        for(Category category: listCategory){
            map.put(category.getName(),category.getID());
        }
    }
    private void DisplayListProduct(int page ,Integer CategoryID){
        listProduct = daoProduct.getListProduct(page,CategoryID);
        adapter = new ViewProductAdapter(listProduct);
        listView.setAdapter(adapter);
    }

    private void setSelectedSearch(){
        spinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                page = 1;
                CategoryID = map.get(listName.get(i));
                numberPage = daoProduct.getNumberOfPage(CategoryID);
                spinnerSearch.setSelection(adapterArr.getPosition(listName.get(i)));
                DisplayListProduct(page ,CategoryID);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void backPage(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page--;
                if(page < 1){
                    page = 1;
                }
                DisplayListProduct(page,CategoryID);
            }
        });
    }

    private void nextPage(){
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                if(page > numberPage){
                    page = numberPage;
                }
                DisplayListProduct(page,CategoryID);
            }
        });
    }
}