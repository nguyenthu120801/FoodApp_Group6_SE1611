package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDBHelper extends ConnectDatabase {
    private static final String USER_TABLE ="User";
    private static final String USER_ID ="ID";
    private static final String USER_FULLNAME ="FullName";
    private static final String USER_USERNAME ="Username";
    private static final String USER_PASSWORD ="Password";
    private static final String USER_ROLENAME ="RoleName";
    private static final String USER_DOB ="Dob";
    private static final String USER_EMAIL ="Email";
    private static final String USER_PHONE ="Phone";

    public UserDBHelper( Context context) {
        super(context);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertUser(String FullName, String Username, String Password, String DOB, String Email, String Phone){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FullName", FullName);
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);
        contentValues.put("DOB", DOB);
        contentValues.put("Email", Email);
        contentValues.put("Phone", Phone);

        long result = MyDatabase.insert("User", null, contentValues);

        if(result  == -1){
            return false;
        }else{
            return true;
        }


    }
}
