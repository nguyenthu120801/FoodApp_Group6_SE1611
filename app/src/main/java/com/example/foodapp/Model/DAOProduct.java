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

    private static final int MAX_PRODUCT_IN_PAGE = 6;

    public boolean CheckProductExist(String name){
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select * from Product where ProductName = ?";
        String [] selectionArgs = {name};
        Cursor cursor = lite.rawQuery(sql, selectionArgs);
        // if get data successful
        if(cursor!= null && cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public int getNumberOfPage(Integer CategoryID){
        SQLiteDatabase lite = getReadableDatabase();
        String sql;
        String [] selectionArgs;
        if(CategoryID == null){
            sql = "select count(ProductID) from Product";
            selectionArgs = null;
        }else{
            sql = "select count(ProductID) from Product where CategoryID = ?";
            selectionArgs = new String[]{CategoryID.toString()};
        }
        Cursor cursor = lite.rawQuery(sql,selectionArgs);
        double number = 0;
        if(cursor!= null && cursor.moveToNext()){
            number = cursor.getInt(0);
        }
        if (number <= MAX_PRODUCT_IN_PAGE) {
            return 1;
        } else if ((number / MAX_PRODUCT_IN_PAGE) > (Math.round(number / MAX_PRODUCT_IN_PAGE))) {
            number = Math.floor(number / MAX_PRODUCT_IN_PAGE) + 1;
        } else {
            number = Math.round(number / MAX_PRODUCT_IN_PAGE);

        }
        return (int) number;
    }

    public List<Product> getListProduct(int page,Integer CategoryID){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql;
        String [] selectionArgs;
        // if all product
        if(CategoryID == null){
            sql = "select * from Product\n"
                    + "	order by ProductID" + "\n"
                    + "LIMIT "+MAX_PRODUCT_IN_PAGE+" OFFSET " + MAX_PRODUCT_IN_PAGE + "*" + (page - 1);
            selectionArgs = null;
        }else{
            sql = "select * from Product\n"
                    + "     where CategoryID = ?" + "\n"
                    + "	order by ProductID\n"
                    + "LIMIT "+MAX_PRODUCT_IN_PAGE +" OFFSET " + MAX_PRODUCT_IN_PAGE + "*" + (page - 1);
            selectionArgs = new String[]{CategoryID.toString()};
        }
        Cursor cursor = lite.rawQuery(sql , selectionArgs);
        while(cursor!= null && cursor.moveToNext()){
            int ProductID = cursor.getInt(0);
            String ProductName = cursor.getString(1);
            String image = cursor.getString(2);
            double price = cursor.getDouble(3);
            int categoryID = cursor.getInt(4);
            String description = cursor.getString(5);
            Product product = new Product(ProductID, ProductName,image,price,categoryID,description);
            list.add(product);
        }
        return list;
    }

    public long AddProduct (Product product){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ProductName",product.getProductName());
        values.put("Image", product.getImage());
        values.put("price",product.getPrice());
        values.put("CategoryID",product.getCategoryID());
        values.put("Description",product.getDescription());
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
            String description = cursor.getString(5);
            Product product = new Product(ProductID, ProductName,image,price,CategoryID,description);
            return product;
        }
        return null;
    }

    // this Thu write this functions
    public List<Product> get5Product(){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "SELECT * FROM Product LIMIT 5";
        Cursor cursor = lite.rawQuery(sql , null);
        while (cursor != null && cursor.moveToNext()){
            int ID = cursor.getInt(0);
            String ProductName = cursor.getString(1);
            String image = cursor.getString(2);
            double price = cursor.getDouble(3);
            int CategoryID = cursor.getInt(4);
            String description = cursor.getString(5);
            Product product = new Product(ID, ProductName,image,price,CategoryID,description);
            list.add(product);
        }
        cursor.close();
        return list;
    }
    public List<Product> ListProduct(){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "SELECT * FROM Product";
        Cursor cursor = lite.rawQuery(sql , null);
        while (cursor != null && cursor.moveToNext()){
            int ID = cursor.getInt(0);
            String ProductName = cursor.getString(1);
            String image = cursor.getString(2);
            double price = cursor.getDouble(3);
            int CategoryID = cursor.getInt(4);
            String description = cursor.getString(5);
            Product product = new Product(ID,ProductName,image,price,CategoryID,description);
            list.add(product);
        }
        cursor.close();
        return list;
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
        values.put("Description",product.getDescription());
        return lite.update("Product",values,whereClause,whereArgs);
    }

    public int DeleteProduct(int ProductID){
        SQLiteDatabase lite = getWritableDatabase();
        String whereClause = "ProductID = ?";
        String[] whereArgs = {String.valueOf(ProductID)};
        return lite.delete("Product",whereClause,whereArgs);
    }

    public List<Product> getProductByCategory (int categoryID){
        List<Product> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select * from Product where CategoryID = ?";
        String[] selectionArgs = {String.valueOf(categoryID)};
        Cursor cursor = lite.rawQuery(sql , selectionArgs);
        // if get data successful
        while (cursor!= null && cursor.moveToNext()){
            int ProductID = cursor.getInt(0);
            String ProductName = cursor.getString(1);
            String image = cursor.getString(2);
            double price = cursor.getDouble(3);
            String description = cursor.getString(5);
            Product product = new Product(ProductID, ProductName,image,price,categoryID,description);
            list.add(product);

        }
        return list;
    }
}

