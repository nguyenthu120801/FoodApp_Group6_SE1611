package com.example.foodapp.Entity;

public class User {
    private int ID;
    private String FullName;
    private String username;
    private String password;
    private String RoleName;
    private String email;
    private String phone;
    public static final String ROLE_CUSTOMER = "Customer";
    public static final String ROLE_SELLER = "Seller";
    public User() {
    }

    public User(int ID, String fullName, String username, String password, String roleName, String email, String phone) {
        this.ID = ID;
        FullName = fullName;
        this.username = username;
        this.password = password;
        RoleName = roleName;
        this.email = email;
        this.phone = phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
