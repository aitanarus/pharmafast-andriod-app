package com.example.pharmafast.repository;

import androidx.lifecycle.LiveData;

import com.example.pharmafast.dao.CartDAO;
import com.example.pharmafast.model.Product;

import java.util.List;

public class CartRepository {

    private CartDAO cartDAO;
    private static CartRepository instance;

    private CartRepository(){
        cartDAO = cartDAO.getInstance();
    }

    public static synchronized CartRepository getInstance(){
        if (instance == null){
            instance = new CartRepository();
        }
        return instance;
    }

    public void addProductToCart(Product product){
        cartDAO.addProductToCart(product);
    }

    public void deleteProductFromCart(Product product){
        cartDAO.deleteProductFromCart(product);
    }

    public LiveData<List<Product>> getCartProducts() {
        return cartDAO.getCartProducts();
    }
}
