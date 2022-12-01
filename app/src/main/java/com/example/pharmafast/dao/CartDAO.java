package com.example.pharmafast.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pharmafast.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CartDAO {
    private static CartDAO instance;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    private MutableLiveData<List<Product>> productsCart;

    private CartDAO(){
        ref = database.getReference("cart");
        productsCart = new MutableLiveData<>();
    }

    public static synchronized CartDAO getInstance() {
        if(instance == null)
            instance = new CartDAO();
        return instance;
    }

    public void addProductToCart(Product product){
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(product.getProductId())).setValue(product);
    }

    public void deleteProductFromCart(Product product){
        if (product.getNumberInCart()>0)
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(product.getProductId())).setValue(product);
        else
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(product.getProductId())).removeValue();
    }

    public LiveData<List<Product>> getCartProducts() {
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Product> currentCartSneakers = new ArrayList<>();
                try {
                    Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                    DataSnapshot subSnapshot;
                    while (iterator.hasNext()) {
                        subSnapshot = iterator.next();
                        currentCartSneakers.add(subSnapshot.getValue(Product.class));
                    }
                } catch (Exception e) {
                    Log.e("Firebase error", e.getMessage());
                }
                productsCart.setValue(currentCartSneakers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return productsCart;
    }
}

