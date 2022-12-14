package com.example.pharmafast.model;

public class Product {
    private int productId;
    private String title;
    private String pic;
    private String description;
    private Double price;
    private String categoryTitle;
    private boolean isFavourite;
    private int quantity;
    private int numberInCart;

    public Product(){}

    public Product(int productId, String title, String pic, String description, Double price, String categoryTitle, boolean isFavourite, int quantity, int numberInCart) {
        this.productId = productId;
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.categoryTitle = categoryTitle;
        this.isFavourite = isFavourite;
        this.quantity = quantity;
        this.numberInCart = numberInCart;
    }

    public Product(int productId, String title, String pic, String description, Double price, String categoryTitle, boolean isFavourite, int quantity) {
        this.productId = productId;
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.categoryTitle = categoryTitle;
        this.isFavourite = isFavourite;
        this.quantity=quantity;
    }

    public Product(String title, String pic, String description, Double price, String categoryTitle, boolean isFavourite, int quantity, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.categoryTitle = categoryTitle;
        this.isFavourite = isFavourite;
        this.quantity=quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", quantity=" + quantity +
                ", numberInCart=" + numberInCart +
                '}';
    }

}
