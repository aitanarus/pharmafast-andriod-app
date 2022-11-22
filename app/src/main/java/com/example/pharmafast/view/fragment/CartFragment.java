package com.example.pharmafast.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmafast.R;
import com.example.pharmafast.model.Product;
import com.example.pharmafast.view.adapter.CartAdapter;
import com.example.pharmafast.viewmodel.CartViewModel;

import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView productList;
    private CartAdapter cartAdapter;
    private LiveData<List<Product>> cartProducts;
    private TextView cartTotalItems;
    private TextView cartTotalPrice;

    private CartViewModel viewModel;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartProducts = new MutableLiveData<>();
        productList = view.findViewById(R.id.cartRecyclerView);
        cartTotalItems = view.findViewById(R.id.itemsTotalCart);
        cartTotalPrice = view.findViewById(R.id.priceTotalCart);
        recyclerViewProductList();
        return view;
    }

    private void recyclerViewProductList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        productList.hasFixedSize();
        productList.setLayoutManager(linearLayoutManager);
        cartProducts = viewModel.getCartProducts();

        viewModel.getCartProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                cartAdapter = new CartAdapter(products);
                productList.setAdapter(cartAdapter);
                cartTotalItems.setText(Integer.toString(cartAdapter.getItemsTotalCart()));
                cartTotalPrice.setText(Double.toString(cartAdapter.getPriceTotalCart()));
            }
        });
    }
}