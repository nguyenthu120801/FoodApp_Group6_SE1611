package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.User;

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

    public User getUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"ID", "FullName", "Username", "Password", "RoleName", "Email", "Phone"};
        String selection = "Username = ? AND Password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("User", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            cursor.close();
            return user;
        }
        return null;
    }
}

