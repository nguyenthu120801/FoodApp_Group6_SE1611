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

    public Boolean insertUser(String FullName, String Username, String Password, String Gender, String Email, String Phone, String RoleName) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FullName", FullName);
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);
        contentValues.put("Gender", Gender);
        contentValues.put("Email", Email);
        contentValues.put("Phone", Phone);
        contentValues.put("RoleName", RoleName);

        long result = MyDatabase.insert("User", null, contentValues);

        return result != -1;
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

    public int getIDUser( String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"ID"};
        String selection = "username = ?";
        String[] selectionArgs = { username};
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

    public int updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FullName", user.getFullName());
        values.put("Phone", user.getPhone());
        values.put("Email", user.getEmail());
        values.put("Gender", user.getGender());

        String selection = "ID" + " = ?";
        String[] selectionArgs = { String.valueOf(user.getID()) };

       return db.update("User", values, selection, selectionArgs);
    }

    public User getUser(String id) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                "ID", "FullName", "Phone", "Email", "Gender", "Username", "Password", "RoleName"};
        String selection = "ID = ?";
        String[] selectionArgs = { id };
        Cursor cursor = db.query(
                "User",
                projection,
                selection,
                selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int ID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("FullName"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow( "Email"));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String roleName = cursor.getString(cursor.getColumnIndexOrThrow("RoleName"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            user = new User(ID, fullName, phone, email, gender, username, password, roleName);
        }
        cursor.close();
        return user;
    }

    public User getInfoUser(int ID){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from User where ID = ?";
        String [] selectionArgs = {ID + ""};
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if(cursor!= null && cursor.moveToNext()){
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("FullName"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow( "Email"));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"));
            String roleName = cursor.getString(cursor.getColumnIndexOrThrow("RoleName"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            double money = cursor.getDouble(cursor.getColumnIndexOrThrow("Money"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            User user = new User(ID, fullName, phone, email, gender, username, password, roleName,money,address);
            return user;
        }
        return null;
    }

    public int UpdateMoney(double money , int UserID){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = "ID = ?";
        String[] whereArgs = {String.valueOf(UserID)};
        values.put("money",money);
        return lite.update("User",values,whereClause,whereArgs);
    }
}

