package com.example.foodapp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.ManageOrder;

import java.util.ArrayList;
import java.util.List;

public class DAOManageOrder extends ConnectDatabase {
    public DAOManageOrder(@Nullable Context context) {
        super(context);
    }

    public List<ManageOrder> getAllManageOrder(){
        List<ManageOrder> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select [Order].OrderID, [Order].status, User.FullName,[Order].Address from [Order] " +
                "INNER JOIN User ON [Order].UserID = User.ID ";
        Cursor cursor = lite.rawQuery(sql , null);
        while (cursor != null && cursor.moveToNext()){
            int orderId = cursor.getInt(0);
            String status = cursor.getString(1);
            String fullName = cursor.getString(2);
            String address = cursor.getString(3);
            ManageOrder manageOrder = new ManageOrder(orderId,status,fullName,address);
            list.add(manageOrder);
        }
        cursor.close();
        return list;
    }



    public void UpdateStatus(int orderId, String status){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String sql = "Update [Order] SET Status = ? WHERE OrderID = ?";
        String[] strings = {status,String.valueOf(orderId)};
        lite.execSQL(sql, strings);

        //Toast.makeText(context,"Update success", Toast.LENGTH_LONG).show();


        //Cursor cursor = lite.rawQuery(sql, strings);
        //String whereClause = "OrderID = ?";
        //String[] whereArgs = {String.valueOf(order.getOrderID())};
        /*values.put("UserID",order.getUserID());
        values.put("OrderDate",order.getOrderDate());
        values.put("ShipDate",order.getShipDate());
        values.put("Status",order.getStatus());
        values.put("Address",order.getAddress());


        return lite.update("Order",values,whereClause,whereArgs);*/

    }
    public int Update1(Order order){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = "OrderID = ?";
        String[] whereArgs = {String.valueOf(order.getStatus())};
        values.put("Status",order.getStatus());

        return lite.update("Order",values,whereClause,whereArgs);
    }

    public int getStatus(int orderId){
        int status = 0;
        SQLiteDatabase lite = getWritableDatabase();
        String sql = "Select Status from [Order] where OrderID = ? ";
        String[] strings = {String.valueOf(orderId)};
        Cursor cursor = lite.rawQuery(sql, strings);
        while (cursor != null && cursor.moveToNext()){
            boolean check = false;
            if(cursor.getString(0).equals("In progress")){
                status = 1;
            } /*else if (cursor.getString(0).equals("Cancel")) {
                status = 2;
            }*/


        }
        cursor.close();
        return status;
    }



}

