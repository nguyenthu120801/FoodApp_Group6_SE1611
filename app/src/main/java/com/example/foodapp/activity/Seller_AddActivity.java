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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller_AddActivity extends AppCompatActivity {
    private final DAOProduct daoProduct = new DAOProduct(this);
    private final DAOCategory daoCategory = new DAOCategory(this);
    private final Map<String, Integer> mapInt = new HashMap<>();
    private final List<String> listName = new ArrayList<>();
    private static final int MY_REQUEST_CODE = 10;
    public static final String TAG = Seller_AddActivity.class.getName();
    private List<Category> listCategory;
    private Spinner spinner;
    private ArrayAdapter adapterArr;
    private EditText editProductName;
    private EditText editPrice;
    private ImageView image;
    private EditText editDes;
    private Button buttonBack;
    private Button buttonAdd;
    private Button buttonUpload;
    private TextView textMess;
    private ActivityResultLauncher<Intent> activity = registerForActivityResult(
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
        setContentView(R.layout.activity_seller_add);
        spinner = findViewById(R.id.spinner_category);
        editProductName = findViewById(R.id.edit_product_name);
        editPrice = findViewById(R.id.edit_price);
        image = findViewById(R.id.imageView);
        editDes = findViewById(R.id.edit_des);
        buttonBack = findViewById(R.id.btnBackList);
        buttonAdd = findViewById(R.id.btnAddProduct);
        buttonUpload = findViewById(R.id.btnUpload);
        textMess = findViewById(R.id.text_mess);
        setDataCategory();
        setMap();
        BackToList();
        UploadImage();
        AddProduct();
    }

    private void AddProduct(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ProductName = editProductName.getText().toString().trim();
                String Price = editPrice.getText().toString().trim();
                String des =  editDes.getText().toString().trim();
                int CategoryID= mapInt.get(spinner.getSelectedItem().toString());
                textMess.setTextColor(Color.RED);
                if(ProductName.isEmpty()){
                    textMess.setText("You have to input product name");
                }else if(Price.isEmpty()){
                    textMess.setText("You have to input price");
                }else if(Double.parseDouble(Price) == 0){
                    textMess.setText("Price must be greater than 0");
                } else{
                    String image = "data:image/jpeg;base64,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";
                    Product product = new Product(ProductName,image,Double.parseDouble(Price),CategoryID,des.isEmpty() ? null : des);
                    long number = daoProduct.AddProduct(product);
                    if(number > 0){
                        textMess.setTextColor(Color.GREEN);
                        textMess.setText("Add successful");
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
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.launch(Intent.createChooser(intent, "Select picture"));
    }

    private void BackToList(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Seller_AddActivity.this, Seller_ViewProduct.class));
            }
        });
    }

    private void setMap(){
        for(Category category: listCategory){
            mapInt.put(category.getName(),category.getID());
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