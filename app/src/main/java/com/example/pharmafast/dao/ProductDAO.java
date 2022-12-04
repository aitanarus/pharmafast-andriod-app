package com.example.pharmafast.dao;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pharmafast.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProductDAO {
    private static ProductDAO instance;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;

    private MutableLiveData<List<Product>> productsByCategory;
    private MutableLiveData<List<Product>> productsPopular;
    private MutableLiveData<Product> productDetails;

    private boolean isFav;

    private ProductDAO(){
        ref = database.getReference("products");
        Map<String, Product> products = new HashMap<>();
        products.put("1", new Product(1, "Mask", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png", "Some mask description", 23.99, "Covid", false,  1));
        products.put("2", new Product(2, "Vitamins","https://pngimg.com/uploads/vitamins/vitamins_PNG75.png", "Some vitamins", 13.59, "Personal Care", false, 10));
        products.put("3", new Product(3, "Eye Mascara", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png", "Some eye mascara", 19.99, "Beauty", false, 2));
        products.put("4", new Product(4, "Nivea Cream","https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png", "Some cream", 4.99, "Skin Care", false, 20));
        products.put("5", new Product(5, "Protein powder","https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png", "Some proteins", 59.99, "Fitness", false, 23));
        products.put("6", new Product(6, "Hand Sanitizer", "https://www.pngall.com/wp-content/uploads/5/Hand-Sanitizer-PNG-Free-Download.png", "Some hand sanitizer", 14.50, "Covid", false, 10));
        products.put("7", new Product(7, "Sleeping pills", "https://cdn.shopify.com/s/files/1/0310/5472/5260/products/SleepWell30-min_4bab6173-e69c-4386-b73d-46340e8da87c.png?v=1636191829", "Some sleeping pills", 35.99, "Personal Care", false, 16));
        products.put("8", new Product(8, "Nitrile Gloves", "https://images.squarespace-cdn.com/content/v1/613d1cb0cb255d7b04a00cec/1632190475623-BZQT082XN9S0XC5YBATL/PNG+Front+Nitrile+Gloves.png?format=1500w", "Some gloves", 15.99, "Covid", false, 5));
        ref.setValue(products);

        productsByCategory = new MutableLiveData<>();
        productsPopular = new MutableLiveData<>();
        productDetails = new MutableLiveData<>();
    }

    public static synchronized ProductDAO getInstance() {
        if(instance == null)
            instance = new ProductDAO();
        return instance;
    }

    public synchronized LiveData<List<Product>> getProductsByCategory(String category) {
        ref = database.getReference("products");
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

    public synchronized LiveData<Product> getProductByName(String name){
        ref = database.getReference("products");
        Query queryByName = ref.orderByChild("title").equalTo(name);
        queryByName.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Product productsResult = new Product();
                    try {
                        Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                        DataSnapshot subSnapshot;
                        while (iterator.hasNext()) {
                            //Improve this? Fetch from database using category directly?
                            subSnapshot = iterator.next();
                            productsResult = subSnapshot.getValue(Product.class);
                        }
                    } catch (Exception e){
                        Log.e("Firebase error", e.getMessage());
                    }
                    productDetails.setValue(productsResult);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

        return productDetails;
    }

    public void setFavourite(Product product){
        ref = database.getReference("favourite");
        if (product.isFavourite())
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(product.getProductId())).setValue(product);
        else
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(product.getProductId())).removeValue();
    }
}
