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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProduct#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentProduct extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView productList;
    private ProductAdapter productAdapter;
    private TextView categoryTitle;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProduct newInstance(String param1, String param2) {
        FragmentProduct fragment = new FragmentProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentProduct() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        productList= view.findViewById(R.id.productsByCategoryRecyclerView);
        recyclerViewProductList();
        return view;
    }

    private void recyclerViewProductList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        productList.hasFixedSize();
        productList.setLayoutManager(gridLayoutManager);
        ArrayList<Product> products = new ArrayList<>();
        productAdapter = new ProductAdapter(products);
        productList.setAdapter(productAdapter);
        products.add(new Product(1, "Mask", "https://images.ctfassets.net/xuuihvmvy6c9/j6xzqE7F99rN7JrnX1RrK/7898f21341a23b4b1fd0535fa3afd6a3/Web_1920________25.png", "Some mask description", 23.99, "Covid Essentials"));
        products.add(new Product(2, "Vitamins", "https://pngimg.com/uploads/vitamins/vitamins_PNG75.png", "Some vitamins", 13.59, "Personal Care"));
        products.add(new Product(3, "Eye Mascara", "https://www.pngall.com/wp-content/uploads/12/Makeup-PNG-Image-HD.png", "Some eye mascara", 19.99, "Beauty"));
        products.add(new Product(4, "Nivea Cream", "https://images-us.nivea.com/-/media/local/gb/advice/vitamins-for-skin/rich-nourishing-body-lotion.png", "Some cream", 4.99, "Skin Care"));
        products.add(new Product(5, "Protein powder", "https://www.bodylab.dk/images/products/whey-100-1kg-3x-2019.png", "Some proteins", 59.99, "Fitness"));
    }

}