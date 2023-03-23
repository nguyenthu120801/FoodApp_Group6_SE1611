package com.example.foodapp.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.ManageOrderActivity;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller_Edit_DeleteProduct extends AppCompatActivity {
    public static int ProductID = 0;
    private final DAOProduct daoProduct = new DAOProduct(this);
    private final DAOCategory daoCategory = new DAOCategory(this);
    private final Map<String, Integer> mapInt = new HashMap<>();
    private final Map<Integer,String> mapStr = new HashMap<>();
    private final List<String> listName = new ArrayList<>();
    private static final int MY_REQUEST_CODE = 10;
    private List<Category> listCategory;
    private Spinner spinner;
    private ArrayAdapter<String> adapterArr;
    private Product product;
    private EditText editProductName;
    private EditText editPrice;
    private ImageView image;
    private EditText editDes;
    private Button buttonUpdate;
    private Button buttonUpload;
    private TextView textMessage;
    private LinearLayout home;
    private LinearLayout logout;
    private LinearLayout managerOrder;
    private boolean isUpdated = false;
    private String imageURL;
    private final ActivityResultLauncher<Intent> activity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        imageURL = uri.toString();
                        Log.e("Test",imageURL);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            image.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_edit_delete_product);
        buttonUpdate = findViewById(R.id.btnUpdate);
        buttonUpload = findViewById(R.id.btnUpload);
        spinner = findViewById(R.id.spinner_category);
        editProductName = findViewById(R.id.edit_product_name);
        editPrice = findViewById(R.id.edit_price);
        image = findViewById(R.id.imageView);
        editDes = findViewById(R.id.edit_des);
        textMessage = findViewById(R.id.text_message);
        home = findViewById(R.id.btn_homePage);
        logout = findViewById(R.id.LogOut);
        managerOrder = findViewById(R.id.manage_order);
        setDataCategory();
        setMap();
        product = daoProduct.getProduct(ProductID);
        editProductName.setText(product.getProductName());
        editPrice.setText(product.getPrice() + "");
        Glide.with(this)
                .load(product.getImage())
                .into(image);
        editDes.setText(product.getDescription() == null ? "" : product.getDescription());
        setSelectedCategory();
        UploadImage();
        Update();
        Home();
        Logout();
        ManagerOrder();
    }

    private void ManagerOrder(){
        managerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seller_Edit_DeleteProduct.this, ManageOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(Seller_Edit_DeleteProduct.this);
                sessionManager.logoutUser();
                Intent intent = new Intent(Seller_Edit_DeleteProduct.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Home(){
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Seller_Edit_DeleteProduct.this, Seller_ViewProduct.class));
            }
        });
    }
/*
    private void Delete(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = daoDetail.CheckProductExist(ProductID);
                // if exist someone buy product
                if(check){
                    textMessage.setTextColor(Color.RED);
                    textMessage.setText("This product exists person buying");
                }else{
                    int number = daoProduct.DeleteProduct(ProductID);
                    // if delete successful
                    if(number > 0){
                        textMessage.setTextColor(Color.GREEN);
                        textMessage.setText("Delete successful");
                    }
                }
            }
        });
    }
*/
    private void Update(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ProductName = editProductName.getText().toString().trim();
                String Price = editPrice.getText().toString().trim();
                String des =  editDes.getText().toString().trim();
                int CategoryID= mapInt.get(spinner.getSelectedItem().toString());
                textMessage.setTextColor(Color.RED);
                if(ProductName.isEmpty()){
                    Toast.makeText(Seller_Edit_DeleteProduct.this, "You have to input product name",Toast.LENGTH_SHORT);
                }else if(daoProduct.CheckProductExist(ProductName) && !ProductName.equalsIgnoreCase(product.getProductName())){
                    Toast.makeText(Seller_Edit_DeleteProduct.this, "Product existed",Toast.LENGTH_SHORT);
                } else if(Price.isEmpty()){
                    Toast.makeText(Seller_Edit_DeleteProduct.this, "You have to input price",Toast.LENGTH_SHORT);
                }else if(Double.parseDouble(Price) == 0){
                    Toast.makeText(Seller_Edit_DeleteProduct.this, "Price must be greater than 0",Toast.LENGTH_SHORT);
                }else{
                    String image = product.getImage();
                    product = new Product(ProductID, ProductName,imageURL == null ? image : imageURL.trim(),Double.parseDouble(Price),CategoryID,des.isEmpty() ? null : des.trim());
                    int number = daoProduct.UpdateProduct(product);
                    if(number > 0){
                        Toast.makeText(Seller_Edit_DeleteProduct.this, "Update successful",Toast.LENGTH_SHORT);
                    }
                }

            }
        });
    }

    private void UploadImage(){
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
    }

    private void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
        }else{
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else{
                String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permission, MY_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int[] grandResults){
        super.onRequestPermissionsResult(requestCode,permissions, grandResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grandResults.length > 0 && grandResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_CREATE_DOCUMENT);
        activity.launch(Intent.createChooser(intent, "Select picture"));
    }

    private void setSelectedCategory(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String CategoryName;
                if(isUpdated){
                    CategoryName = listName.get(i);
                }else{
                    CategoryName = mapStr.get(product.getCategoryID());
                    isUpdated = true;
                }
                spinner.setSelection(adapterArr.getPosition(CategoryName));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setMap(){
        for(Category category: listCategory){
            mapInt.put(category.getName(),category.getID());
            mapStr.put(category.getID(),category.getName());
        }
    }

    private void setDataCategory(){
        listCategory = daoCategory.getAllCategory();
        for(Category category: listCategory){
            listName.add(category.getName());
        }
        adapterArr =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listName);
        adapterArr.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapterArr);
    }
}