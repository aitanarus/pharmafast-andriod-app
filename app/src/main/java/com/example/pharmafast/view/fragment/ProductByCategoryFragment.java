package com.example.pharmafast.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmafast.R;
import com.example.pharmafast.model.Product;
import com.example.pharmafast.view.adapter.ProductAdapter;
import com.example.pharmafast.viewmodel.ProductViewModel;

import java.util.List;

public class ProductByCategoryFragment extends Fragment {
    private RecyclerView productList;
    private ProductAdapter productAdapter;
    private TextView categoryTitle;
    private String categoryTitleString;
    private Button addProductCartButton;
    private Button deleteProductCartButton;
    private LiveData<List<Product>> products;

    private ProductViewModel viewModel;

    public ProductByCategoryFragment() {
        // Required empty public constructor
    }

    public static ProductByCategoryFragment newInstance() {
        ProductByCategoryFragment fragment = new ProductByCategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            categoryTitleString = bundle.getString("CATEGORY NAME");
        }
        products = new MutableLiveData<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_by_category, container, false);
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        products = new MutableLiveData<>();
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
        products = viewModel.getProductsByCategory(categoryTitleString);

        viewModel.getProductsByCategory(categoryTitleString).observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productAdapter = new ProductAdapter(products);
                productList.setAdapter(productAdapter);
            }
        });
    }
}