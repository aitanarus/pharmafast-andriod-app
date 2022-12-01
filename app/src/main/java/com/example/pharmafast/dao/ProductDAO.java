package com.example.pharmafast.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pharmafast.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ProductDAO {
    private static ProductDAO instance;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;

    private MutableLiveData<List<Product>> productsByCategory;
    private MutableLiveData<List<Product>> productsPopular;
    private MutableLiveData<Product> productDetails;

    private ProductDAO(){
        ref = database.getReference("products");
        productsByCategory = new MutableLiveData<>();
        productsPopular = new MutableLiveData<>();
        productDetails = new MutableLiveData<>();
    }

    public static synchronized ProductDAO getInstance() {
        if(instance == null)
            instance = new ProductDAO();
        return instance;
    }

    public LiveData<List<Product>> getProductsByCategory(String category) {
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Product> productsResult = new ArrayList<>();
                try {
                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                    DataSnapshot subSnapshot;
                    while (iterator.hasNext()) {
                        //Improve this? Fetch from database using category directly?
                        subSnapshot = iterator.next();
                        Product value = subSnapshot.getValue(Product.class);
                        if (value.getCategoryTitle().equals(category) && value.getQuantity() > 0)
                            productsResult.add(subSnapshot.getValue(Product.class));
                    }
                } catch (Exception e){
                    Log.e("Firebase error", e.getMessage());
                }
                productsByCategory.setValue(productsResult);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return productsByCategory;
    }

    public LiveData<Product> getProductByName(String name){
        Query queryByName = ref.orderByChild("title").equalTo(name);
        queryByName.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        Product product = ds.getValue(Product.class);
                        System.out.println("Repo: " + product.toString());
                        productDetails.setValue(product);
                    }
                } else {
                    Log.d("Firebase error", task.getException().getMessage());
                }
            }
        });
        System.out.println("HI");
        productDetails.setValue(new Product(1, "Mask", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png", "Some mask description", 23.99, "Covid Essentials", 10));
        return productDetails;
    }
}