package com.example.pharmafast.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pharmafast.model.Product;
import com.example.pharmafast.repository.CartRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private final CartRepository cartRepository;

    public CartViewModel(Application app) {
        super(app);
        cartRepository = CartRepository.getInstance();
    }

    public void addProductToCart(Product product){
       cartRepository.addProductToCart(product);
    }

    public void deleteProductFromCart(Product product){
        cartRepository.deleteProductFromCart(product);
    }
    public LiveData<List<Product>> getCartProducts() {
        return cartRepository.getCartProducts();
    }
}