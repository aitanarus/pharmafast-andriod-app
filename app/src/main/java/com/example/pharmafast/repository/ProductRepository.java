package com.example.pharmafast.repository;

import androidx.lifecycle.LiveData;

import com.example.pharmafast.dao.ProductDAO;
import com.example.pharmafast.model.Product;

import java.util.List;

public class ProductRepository {

    private ProductDAO productDAO;
    private static ProductRepository instance;

    private ProductRepository(){
        productDAO = productDAO.getInstance();
    }

    public static synchronized ProductRepository getInstance(){
        if (instance == null){
            instance = new ProductRepository();
        }
        return instance;
    }

    public LiveData<List<Product>> getProductsByCategory(String category) {
        return productDAO.getProductsByCategory(category);
    }

    public LiveData<Product> getProductByName(String name){
        return productDAO.getProductByName(name);
    }
}
