package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class DAOManageOrder extends ConnectDatabase {
    public DAOManageOrder(@Nullable Context context) {
        super(context);
    }

    public List<ManageOrder> getAllManageOrder(){
        List<ManageOrder> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select [Order].OrderID, [Order].status, Product.ProductName, OrderDetail.quantity, Product.Image from [Order] JOIN OrderDetail ON [Order].OrderID = OrderDetail.OrderID JOIN Product ON OrderDetail.ProductID = Product.ProductID";
        Cursor cursor = lite.rawQuery(sql , null);
        while (cursor != null && cursor.moveToNext()){
            int orderId = cursor.getInt(0);
            String status = cursor.getString(1);
            String productName = cursor.getString(2);
            int quantity = cursor.getInt(3);
            int image = cursor.getInt(4);
            ManageOrder manageOrder = new ManageOrder(orderId,status,productName,quantity,image);
            list.add(manageOrder);
        }
        cursor.close();
        return list;
    }



}
