package com.example.foodapp.Entity;

public class Product {
    private int ProductID;
    private String ProductName;
    private String image;
    private double price;
    private int CategoryID;
    private String description;

    public Product() {
    }

    public Product(int productID, String productName, String image, double price, int categoryID, String description) {
        ProductID = productID;
        ProductName = productName;
        this.image = image;
        this.price = price;
        CategoryID = categoryID;
        this.description = description;
    }

    public Product(String productName, String image, double price, int categoryID, String description) {
        ProductName = productName;
        this.image = image;
        this.price = price;
        CategoryID = categoryID;
        this.description = description;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}