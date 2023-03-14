package com.example.foodapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodapp.Model.DAOUser;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LoginSession";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String username, String password) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getString(KEY_USERNAME, null) != null;
    }

    public String getUsername() {
        HashMap<String, String> hashMap = getUserDetail();
        String username = hashMap.get(SessionManager.KEY_USERNAME);
        return username;
    }

    public String getPassword() {
        HashMap<String, String> hashMap = getUserDetail();
        String password = hashMap.get(SessionManager.KEY_PASSWORD);
        return password;
    }

    public int getUserID() {
        DAOUser daoUser = new DAOUser(context.getApplicationContext());
        if (getUsername() == null || getPassword() == null) {
            return -1;
        }
        return daoUser.getUserId(getUsername(), getPassword());

    }
}
