package com.example.pharmafast.domain;

public class Product {
    private int productId;
    private String title;
    private String pic;
    private String description;
    private Double price;
    private String categoryTitle;
    private int numberInCart;

    public Product(int productId, String title, String pic, String description, Double price, String categoryTitle) {
        this.productId = productId;
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }

    public Product(String title, String pic, String description, Double price, String categoryTitle, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.categoryTitle = categoryTitle;
        this.numberInCart = numberInCart;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
