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

    public User getUser(String username, String password) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                "ID", "FullName", "Phone", "Email", "Gender", "Username", "Password", "RoleName"};
        String selection = "Username = ? AND Password = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(
                "User",
                projection,
                selection,
                selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("FullName"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow( "Email"));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String roleName = cursor.getString(cursor.getColumnIndexOrThrow("RoleName"));
            user = new User(id, fullName, phone, email, gender, username, password, roleName);
        }
        cursor.close();
        return user;
    }
    // Lấy ID từ bảng User dựa trên username và password
    public int getUserId( String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"ID"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(
                "User", projection, selection, selectionArgs, null, null, null);
        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
        }
        cursor.close();
        db.close();
        return userId;
    }
}

