package com.example.pharmafast.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pharmafast.R;
import com.example.pharmafast.adapter.CategoryAdapter;
import com.example.pharmafast.adapter.ProductAdapter;
import com.example.pharmafast.domain.Category;
import com.example.pharmafast.domain.Product;
import java.util.ArrayList;

public class FragmentMain extends Fragment {
    private RecyclerView categoryList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView productList;
    private ProductAdapter productAdapter;

    public FragmentMain() {
        // Required empty public constructor
    }

    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        categoryList = view.findViewById(R.id.categoryRecyclerview);
        productList= view.findViewById(R.id.productsByCategoryRecyclerView);

        recyclerViewCategoryList();
        recyclerViewProductList();
        return view;
    }


    private void recyclerViewCategoryList() {
        ArrayList<Category> categories = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        categoryList.hasFixedSize();
        categoryList.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(categories);
        categoryList.setAdapter(categoryAdapter);
        categories.add(new Category(1, "Covid", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png"));
        categories.add(new Category(2, "Personal Care", "https://pngimg.com/uploads/vitamins/vitamins_PNG75.png"));
        categories.add(new Category(3, "Beauty", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png"));
        categories.add(new Category(4, "Skin Care", "https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png"));
        categories.add(new Category(5, "Fitness", "https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png"));
    }

    private void recyclerViewProductList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        productList.hasFixedSize();
        productList.setLayoutManager(linearLayoutManager);
        ArrayList<Product> products = new ArrayList<>();
        productAdapter = new ProductAdapter(products);
        productList.setAdapter(productAdapter);
        products.add(new Product(1, "Mask", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png", "Some mask description", 23.99, "Covid Essentials"));
        products.add(new Product(2, "Vitamins","https://pngimg.com/uploads/vitamins/vitamins_PNG75.png", "Some vitamins", 13.59, "Personal Care"));
        products.add(new Product(3, "Eye Mascara", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png", "Some eye mascara", 19.99, "Beauty"));
        products.add(new Product(4, "Nivea Cream","https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png", "Some cream", 4.99, "Skin Care"));
        products.add(new Product(5, "Protein powder","https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png", "Some proteins", 59.99, "Fitness"));
    }
}