package com.example.projektbookshop;

public class ShoppingItem {
    private String id;
    private String name;
    private String info;
    private String price;
    private float rate;
    private int imageSrc;
    private int cartedCount;

    public ShoppingItem() {
    }

    public ShoppingItem(String name, int imageSrc, String info, float rate, String price, int cartedCount) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.rate = rate;
        this.imageSrc = imageSrc;
        this.cartedCount = cartedCount;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getPrice() {
        return price;
    }

    public float getRate() {
        return rate;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getCartedCount() {
        return cartedCount;
    }

    public void setCartedCount(int cartedCount) {
        this.cartedCount = cartedCount;
    }
    public String _getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
}
