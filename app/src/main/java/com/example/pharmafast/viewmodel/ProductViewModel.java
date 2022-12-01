package com.example.pharmafast.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pharmafast.model.Product;
import com.example.pharmafast.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private final ProductRepository productRepository;

    public ProductViewModel(Application app){
        super(app);
        productRepository = ProductRepository.getInstance();
    }

    public LiveData<List<Product>> getProductsByCategory(String category){
        return productRepository.getProductsByCategory(category);
    }

    public LiveData<Product> getProductByName(String name){
        return productRepository.getProductByName(name);
    }

    public void setFavourite(Product product){
        productRepository.setFavourite(product);
    }
}