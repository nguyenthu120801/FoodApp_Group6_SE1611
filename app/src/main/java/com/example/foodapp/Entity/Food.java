package com.example.foodapp.Entity;

public class Food {
    private int foodID;
    private String foodName;
    private int image;
    private float price;
    private int category;
    private String descirption;

    public Food(int foodID, String foodName, int image, int category, String descirption) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.image = image;
        this.category = category;
        this.descirption = descirption;
    }

    public Food(int foodID, String foodName, int image, float price, String descirption) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.image = image;
        this.price = price;
        this.descirption = descirption;
    }

    public Food(int foodID, String foodName, int image, float price) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.image = image;
        this.price = price;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }
}
