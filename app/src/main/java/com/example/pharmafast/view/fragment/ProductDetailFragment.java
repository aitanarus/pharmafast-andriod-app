package com.example.pharmafast.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pharmafast.R;
import com.example.pharmafast.model.Product;
import com.example.pharmafast.viewmodel.CartViewModel;
import com.example.pharmafast.viewmodel.ProductViewModel;

public class ProductDetailFragment extends Fragment {
    private Button backButton;
    private Button addProductButton;
    private TextView productDetailTitle;
    private TextView productDetailDescription;
    private TextView productDetailPrice;
    private TextView productDetailId;
    private ImageView productDetailPic;
    private AutoCompleteTextView autoCompleteTextView;
    private String productDetailTitleString;

    private LiveData<Product> selectedProduct;

    private ProductViewModel viewModel;
    //Maybe put in product vm?
    private CartViewModel cartViewModel;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(String param1, String param2) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            productDetailTitleString = bundle.getString("PRODUCT NAME");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        selectedProduct = new MutableLiveData<>();
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        productDetailTitle=view.findViewById(R.id.productDetailTitle);
        productDetailDescription = view.findViewById(R.id.productDetailDescription);
        productDetailPrice = view.findViewById(R.id.productDetailPrice);
        productDetailId = view.findViewById(R.id.productDetailId);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteQuantity);
        productDetailPic=view.findViewById(R.id.productDetailPic);
        addProductButton=view.findViewById(R.id.addProductBtn);
        backButton=view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment cartFragment = new CartFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_main, cartFragment).commit();
                cartViewModel.addProductToCart(selectedProduct.getValue());
            }
        });

        getProductByName();
        return view;
    }

    private void getProductByName() {
        selectedProduct = viewModel.getProductByName(productDetailTitleString);
        System.out.println("Fragment: " + selectedProduct.getValue().toString());

        viewModel.getProductByName(productDetailTitleString).observe(getViewLifecycleOwner(), new Observer<Product>() {

            @Override
            public void onChanged(Product product) {
                productDetailTitle.setText(selectedProduct.getValue().getTitle());
                productDetailDescription.setText(selectedProduct.getValue().getDescription());
                productDetailPrice.setText(String.valueOf(selectedProduct.getValue().getPrice()));
                productDetailId.setText(String.valueOf("#"+selectedProduct.getValue().getProductId()));

                // product quantity
                Integer[] quantity = new Integer[selectedProduct.getValue().getQuantity()];
                for(int i = 0; i < selectedProduct.getValue().getQuantity(); i++){
                    quantity[i] = i;
                }
                ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter(getContext(), R.layout.list_item, quantity);
                    // default value
                autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
                autoCompleteTextView.setAdapter(arrayAdapter);

                // product pic
                Glide.with(getContext())
                        .load(selectedProduct.getValue().getPic())
                        .into(productDetailPic);
                    }
                });
    }
}