package com.example.pharmafast.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pharmafast.R;
import com.example.pharmafast.adapter.CategoryAdapter;
import com.example.pharmafast.adapter.ProductAdapter;
import com.example.pharmafast.domain.Category;
import com.example.pharmafast.domain.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView categoryList;
private CategoryAdapter categoryAdapter;
    private RecyclerView productList;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        recyclerViewCategoryList();
        recyclerViewProductList();
    }

    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryList=findViewById(R.id.categoryRecyclerview);
        categoryList.hasFixedSize();
        categoryList.setLayoutManager(linearLayoutManager);
        ArrayList<Category> categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categories);
        categoryList.setAdapter(categoryAdapter);
        categories.add(new Category("Covid Essentials", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png"));
        categories.add(new Category("Personal Care", "https://pngimg.com/uploads/vitamins/vitamins_PNG75.png"));
        categories.add(new Category("Beauty", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png"));
        categories.add(new Category("Skin Care", "https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png"));
        categories.add(new Category("Fitness", "https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png"));
    }

    private void recyclerViewProductList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        productList=findViewById(R.id.productRecyclerView);
        productList.hasFixedSize();
        productList.setLayoutManager(linearLayoutManager);
        ArrayList<Product> products = new ArrayList<>();
        productAdapter = new ProductAdapter(products);
        productList.setAdapter(productAdapter);
        products.add(new Product("Mask", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png", "Some mask description", 23.99, "Covid Essentials"));
        products.add(new Product("Vitamins","https://pngimg.com/uploads/vitamins/vitamins_PNG75.png", "Some vitamins", 13.59, "Personal Care"));
        products.add(new Product("Eye Mascara", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png", "Some eye mascara", 19.99, "Beauty"));
        products.add(new Product("Nivea Cream","https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png", "Some cream", 4.99, "Skin Care"));
        products.add(new Product("Protein powder","https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png", "Some proteins", 59.99, "Fitness"));
    }
}