package com.example.pharmafast.model;

public class Category {
    private int categoryId;
    private String title;
    private String pic;

    public Category(int categoryId, String title, String pic) {
        this.categoryId = categoryId;
        this.title = title;
        this.pic = pic;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
}
