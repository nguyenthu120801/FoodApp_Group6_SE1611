package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Category;

import java.util.ArrayList;
import java.util.List;

public class DAOCategory extends ConnectDatabase{
    public DAOCategory(@Nullable Context context) {
        super(context);
    }

    public long AddCategory(Category category){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", category.getName());
        values.put("Image",category.getImage());
        return lite.insert("Category",null,values);
    }

    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select * from Category";
        Cursor cursor = lite.rawQuery(sql , null);
        while (cursor != null && cursor.moveToNext()){
            int ID = cursor.getInt(0);
            String name = cursor.getString(1);
            int image = cursor.getInt(2);
            Category category = new Category(ID,name,image);
            list.add(category);
        }
        cursor.close();
        return list;
    }

    public String getCategoryName(Integer categoryID){
        String categoryname="";
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select Name from Category where ID=?";
        Cursor cursor = lite.rawQuery(sql, new String[]{String.valueOf(categoryID)});
        while (cursor != null && cursor.moveToNext()){
            categoryname = cursor.getString(0);
        }
        cursor.close();
        return categoryname;

    }
}
