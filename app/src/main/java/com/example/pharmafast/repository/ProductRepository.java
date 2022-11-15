package com.example.pharmafast.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pharmafast.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ProductRepository {
    private static ProductRepository instance;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;

    private MutableLiveData<List<Product>> productsByCategory;
    private MutableLiveData<List<Product>> productsPopular;

    private ProductRepository(){
        ref = database.getReference("products");
        productsByCategory = new MutableLiveData<>();
        productsPopular = new MutableLiveData<>();
    }

    public static synchronized ProductRepository getInstance(Application app) {
        if(instance == null)
            instance = new ProductRepository();
        return instance;
    }

    public LiveData<List<Product>> getProductsByCategory(String category) {
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Product> products = new ArrayList<>();
                try {
                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                    DataSnapshot subSnapshot;
                    while (iterator.hasNext()) {
                        //Improve this? Fetch from database using category directly?
                        subSnapshot = iterator.next();
                        Product value = subSnapshot.getValue(Product.class);
                        if (value.getCategoryTitle().equals(category))
                            products.add(subSnapshot.getValue(Product.class));
                    }
                } catch (Exception e){
                    Log.e("Firebase error", e.getMessage());
                }
                productsByCategory.setValue(products);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return productsByCategory;
    }

    public LiveData<List<Product>> getProductById(int id){
        return null;
    }
}
