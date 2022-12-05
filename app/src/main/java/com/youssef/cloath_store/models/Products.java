package com.youssef.cloath_store.models;

public class Products {


    private long id;
    private  String Category;
    private  int count;
    private String Title;
    private int price;
    private int numberSold;
    private  byte[]  image;

    public Products(String category, int count, String title, int price, int numberSold, byte[] image) {
        Category = category;
        this.count = count;
        Title = title;
        this.price = price;
        this.numberSold = numberSold;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
