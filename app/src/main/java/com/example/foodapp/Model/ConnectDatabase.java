package com.example.foodapp.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ConnectDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PRM392_APP_FOOD";
    private static final int DATABASE_VERSION = 1;

    private static final String ORDER_TABLE ="Order";
    private static final String ORDER_ID ="OrderID";
    private static final String ORDER_USERID ="UserID";
    private static final String ORDER_ADDRESS ="Address";
    private static final String ORDER_ORDER_DATE ="OrderDate";
    private static final String ORDER_SHIP_DATE ="ShipDate";
    private static final String ORDER_STATUS ="Status";

    public ConnectDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUser = "CREATE TABLE User(\n" +
                "\tID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tFullName TEXT NOT NULL,\n" +
                "\tUsername TEXT NOT NULL,\n" +
                "\tPassword TEXT NOT NULL,\n" +
                "\tRoleName TEXT NOT NULL, \n" +
                "\tDob TEXT NOT NULL,\n" +
                "\tEmail TEXT NOT NULL,\n" +
                "\tPhone TEXT NOT NULL\n" +
                ");";
        String sqlCategory = "CREATE TABLE Category(\n" +
                "\tID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tName TEXT NOT NULL\n" +
                ");";
        String sqlOrder = "CREATE TABLE ["+ORDER_TABLE+"](\n" +
                "\t"+ORDER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\t"+ORDER_USERID+" INTEGER  NOT NULL,\n" +
                "\t"+ORDER_ORDER_DATE+" TEXT  NOT NULL,\n" +
                "\t"+ORDER_SHIP_DATE+" TEXT ,\n" +
                "\t"+ORDER_STATUS+" TEXT NOT NULL, \n" +
                "\t"+ORDER_ADDRESS+" TEXT NOT NULL,\n" +
                "\tFOREIGN KEY (UserID) REFERENCES User(ID)\n"  +
                ");";
        String sqlProduct = "CREATE TABLE Product(\n" +
                "\tProductID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tProductName TEXT NOT NULL ,\n" +
                "\tImage TEXT  NOT NULL,\n" +
                "\tprice REAL NOT NULL,\n" +
                "\tCategoryID INTEGER  NOT NULL,\n" +
                "\tFOREIGN KEY (CategoryID) REFERENCES Category(ID)\n" +
                ");";
        String sqlOrderDetail = "CREATE TABLE OrderDetail(\n" +
                "\tDetailID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tOrderID INTEGER  NOT NULL,\n" +
                "\tProductID INTEGER  NOT NULL,\n" +
                "\tquantity INTEGER  NOT NULL,\n" +
                "\tFOREIGN KEY (ProductID) REFERENCES Product(ProductID),\n" +
                "\tFOREIGN KEY (OrderID) REFERENCES [Order](OrderID)\n" +
                ");";
        Log.d("infoOrder", "create order table : " + sqlOrder);
        // execute sql
        sqLiteDatabase.execSQL(sqlUser);
        sqLiteDatabase.execSQL(sqlCategory);
        sqLiteDatabase.execSQL(sqlProduct);
        sqLiteDatabase.execSQL(sqlOrder);
        sqLiteDatabase.execSQL(sqlOrderDetail);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
