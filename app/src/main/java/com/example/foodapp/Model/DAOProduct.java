package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Product;

import java.util.*;

public class DAOProduct extends ConnectDatabase{
    public DAOProduct(@Nullable Context context) {
        super(context);
    }

    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select * from Product";
        Cursor cursor = lite.rawQuery(sql , null);
        while(cursor!= null && cursor.moveToNext()){
            int ProductID = cursor.getInt(0);
            String ProductName = cursor.getString(1);
            String image = cursor.getString(2);
            double price = cursor.getDouble(3);
            int CategoryID = cursor.getInt(4);
            Product product = new Product(ProductID, ProductName,image,price,CategoryID);
            list.add(product);
        }
        return list;
    }

    public long AddProduct (Product product){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ProductID",product.getProductID());
        values.put("ProductName",product.getProductName());
        values.put("Image", product.getImage());
        values.put("price",product.getPrice());
        values.put("CategoryID",product.getCategoryID());
        return lite.insert("Product",null,values);
    }

    public Product getProduct(int ProductID){
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select * from Product where ProductID = ?";
        String[] selectionArgs = {String.valueOf(ProductID)};
        Cursor cursor = lite.rawQuery(sql , selectionArgs);
        // if get data successful
        if(cursor!= null && cursor.moveToNext()){
            String ProductName = cursor.getString(1);
            String image = cursor.getString(2);
            double price = cursor.getDouble(3);
            int CategoryID = cursor.getInt(4);
            Product product = new Product(ProductID, ProductName,image,price,CategoryID);
            return product;
        }
        return null;
    }

    public int UpdateProduct(Product product){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = "ProductID = ?";
        String[] whereArgs = {String.valueOf(product.getProductID())};
        values.put("ProductName",product.getProductName());
        values.put("Image", product.getImage());
        values.put("price",product.getPrice());
        values.put("CategoryID",product.getCategoryID());
        return lite.update("Product",values,whereClause,whereArgs);
    }

    public int DeleteProduct(int ProductID){
        SQLiteDatabase lite = getWritableDatabase();
        String whereClause = "ProductID = ?";
        String[] whereArgs = {String.valueOf(ProductID)};
        return lite.delete("Product",whereClause,whereArgs);
    }
}
