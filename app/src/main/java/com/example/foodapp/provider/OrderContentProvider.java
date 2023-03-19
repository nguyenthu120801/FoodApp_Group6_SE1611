package com.example.foodapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodapp.Model.OrderDBHelper;

public class OrderContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.foodapp.provider";
    private static final String BASE_PATH = "orders";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int ORDERS = 1;
    private static final int ORDER_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, BASE_PATH, ORDERS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ORDER_ID);
    }

    private SQLiteDatabase database;
    private  OrderDBHelper orderDBHelper;

    @Override
    public boolean onCreate() {
         orderDBHelper = new OrderDBHelper(getContext());
        database = orderDBHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(OrderDBHelper.ORDER_TABLE);

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case ORDERS:
                break;
            case ORDER_ID:
                queryBuilder.appendWhere(OrderDBHelper.ORDER_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ORDERS:
                return "vnd.android.cursor.dir/vnd.example.foodapp.orders";
            case ORDER_ID:
                return "vnd.android.cursor.item/vnd.example.foodapp.orders";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = orderDBHelper.getWritableDatabase();
        long rowId = db.insert(OrderDBHelper.ORDER_TABLE, null, values);
        if (rowId > 0) {
            Uri insertedUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(insertedUri, null);
            return insertedUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        int rowsUpdated = 0;
        switch (uriType) {
            case ORDERS:
                rowsUpdated = database.update(OrderDBHelper.ORDER_TABLE, values, selection, selectionArgs);
                break;
            case ORDER_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(OrderDBHelper.ORDER_TABLE, values, OrderDBHelper.ORDER_ID + "=" + id, null);
                } else {
                    rowsUpdated = database.update(OrderDBHelper.ORDER_TABLE, values, OrderDBHelper.ORDER_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        SQLiteDatabase db = orderDBHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case ORDERS:
                rowsDeleted = db.delete(OrderDBHelper.ORDER_TABLE, selection, selectionArgs);
                break;
            case ORDER_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(OrderDBHelper.ORDER_TABLE, OrderDBHelper.ORDER_ID + "=" + id, null);
                } else {
                    rowsDeleted = db.delete(OrderDBHelper.ORDER_TABLE, OrderDBHelper.ORDER_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }
}
