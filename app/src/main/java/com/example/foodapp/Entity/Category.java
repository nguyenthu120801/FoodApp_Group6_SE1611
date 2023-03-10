package com.example.foodapp.Entity;

public class Category {
    private int ID;
    private String name;
    private int image;

    public Category() {
    }

    public Category(int ID, String name, int image) {
        this.ID = ID;
        this.name = name;
        this.image = image;
    }

    public Category(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

