package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Cart;
import com.example.foodapp.Entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class DAOCart extends ConnectDatabase{

    public DAOCart(@Nullable Context context) {
        super(context);
    }

    public List<Cart> getListCart(int UserID){
        List<Cart> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "Select * from Cart where UserID = ?";
        String[] selectionArgs = {String.valueOf(UserID)};
        Cursor cursor = lite.rawQuery(sql , selectionArgs);
        while(cursor!= null && cursor.moveToNext()){
            int cartID = cursor.getInt(0);
            int userID = cursor.getInt(1);
            int productId = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            Cart cart = new Cart(cartID, userID, productId, quantity);
            list.add(cart);
        }
        return list;
    }

    public Cart getCart(int CartID){
        Cart cart = new Cart();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "Select * from Cart where CartID = ?";
        String[] selectionArgs = {String.valueOf(CartID)};
        Cursor cursor = lite.rawQuery(sql , selectionArgs);
        while(cursor!= null && cursor.moveToNext()){
            int cartID = cursor.getInt(0);
            int userID = cursor.getInt(1);
            int productId = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            cart = new Cart(cartID, userID, productId, quantity);
        }
        return cart;
    }

    public int getMaxCartID(){
        int maxCartID = 0;
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "Select Max(CartID) from Cart";
        Cursor cursor = lite.rawQuery(sql , null);
        while(cursor!= null && cursor.moveToNext()){
             maxCartID = cursor.getInt(0);
        }
        return maxCartID;
    }

    public long AddCart (Cart cart){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID",cart.getUserID());
        values.put("ProductID", cart.getProductID());
        values.put("quantity",cart.getQuantity());
        return lite.insert("Cart",null,values);
    }

    public int UpdateCart (Cart cart){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = "CartID = ?";
        String[] whereArgs = {String.valueOf(cart.getCartID())};
        values.put("UserID",cart.getUserID());
        values.put("ProductID", cart.getProductID());
        values.put("quantity",cart.getQuantity());
        return lite.update("Cart",values,whereClause,whereArgs);
    }

    public int DeleteCart (int cartID){
        SQLiteDatabase lite = getWritableDatabase();
        String whereClause = "CartID = ?";
        String[] whereArgs = {String.valueOf(cartID)};
        return lite.delete("Cart",whereClause,whereArgs);
    }

    public void DeleteAllCart (){
        SQLiteDatabase lite = getWritableDatabase();
        String sql = "Delete from Cart";
       lite.execSQL(sql);

    }
}
