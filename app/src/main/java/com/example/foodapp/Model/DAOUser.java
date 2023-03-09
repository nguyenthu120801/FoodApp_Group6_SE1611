package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DAOUser extends ConnectDatabase {


    public DAOUser(@Nullable Context context) {
        super(context);
    }

    public Boolean insertUser(String FullName, String Username, String Password, String DOB, String Email, String Phone) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FullName", FullName);
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);
        contentValues.put("DOB", DOB);
        contentValues.put("Email", Email);
        contentValues.put("Phone", Phone);

        long result = MyDatabase.insert("User", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
