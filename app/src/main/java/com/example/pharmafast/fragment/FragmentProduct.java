package com.example.pharmafast.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.pharmafast.R;
import com.example.pharmafast.adapter.ProductAdapter;
import com.example.pharmafast.domain.Product;
import java.util.ArrayList;

public class FragmentProduct extends Fragment {
    private RecyclerView productList;
    private ProductAdapter productAdapter;
    private TextView categoryTitle;
    private String categoryTitleString;


    public static FragmentProduct newInstance() {
        FragmentProduct fragment = new FragmentProduct();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentProduct() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            categoryTitleString = bundle.getString("CATEGORY NAME");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        categoryTitle=view.findViewById(R.id.categoryNameTitle);
        productList= view.findViewById(R.id.productsByCategoryRecyclerView);
        categoryTitle.setText(categoryTitleString);
        recyclerViewProductList();
        return view;
    }

    private void recyclerViewProductList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        productList.hasFixedSize();
        productList.setLayoutManager(gridLayoutManager);
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Product> filteredProducts = new ArrayList<>();
        productAdapter = new ProductAdapter(filteredProducts);
        productList.setAdapter(productAdapter);

        for (int i = 0;  i < products.size(); i++){
            if (categoryTitleString.equals(products.get(i).getCategoryTitle())){
                filteredProducts.add(products.get(i));
            }
        }

        products.add(new Product(1, "Mask", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png", "Some mask description", 23.99, "Covid Essentials"));
        products.add(new Product(2, "Vitamins", "https://pngimg.com/uploads/vitamins/vitamins_PNG75.png", "Some vitamins", 13.59, "Personal Care"));
        products.add(new Product(3, "Eye Mascara", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png", "Some eye mascara", 19.99, "Beauty"));
        products.add(new Product(4, "Nivea Cream", "https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png", "Some cream", 4.99, "Skin Care"));
        products.add(new Product(5, "Protein powder", "https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png", "Some proteins", 59.99, "Fitness"));
    }
}