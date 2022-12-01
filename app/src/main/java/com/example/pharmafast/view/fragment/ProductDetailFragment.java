package com.example.pharmafast.view.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
import com.google.android.material.button.MaterialButton;

public class ProductDetailFragment extends Fragment {
    private Button backButton;
    private Button addProductButton;
    private MaterialButton favouriteButton;
    private TextView productDetailTitle;
    private TextView productDetailDescription;
    private TextView productDetailPrice;
    private TextView productDetailId;
    private ImageView productDetailPic;
    private AutoCompleteTextView autoCompleteTextView;
    private String productDetailTitleString;

    private ProgressBar TempBar;
    private CountDownTimer countDownTimer;
    private int i = 0;

    private LiveData<Product> selectedProduct;

    private ProductViewModel productViewModel;
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
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
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
        favouriteButton = view.findViewById(R.id.favouriteBtn);

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

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(selectedProduct.getValue().isFavourite());
                if (selectedProduct.getValue().isFavourite()) {
                    selectedProduct.getValue().setFavourite(false);
                }
                else{
                    selectedProduct.getValue().setFavourite(true);
                }
                productViewModel.setFavourite(selectedProduct.getValue());
            }
        });

        getProductByName();
        return view;
    }

    private void getProductByName() {
        selectedProduct = productViewModel.getProductByName(productDetailTitleString);
        productViewModel.getProductByName(productDetailTitleString).observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                productDetailTitle.setText(selectedProduct.getValue().getTitle());
                productDetailDescription.setText(selectedProduct.getValue().getDescription());
                productDetailPrice.setText(String.valueOf(selectedProduct.getValue().getPrice()));
                productDetailId.setText(String.valueOf("#"+selectedProduct.getValue().getProductId()));

                favouriteButton.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_favorite_border_24));
                favouriteButton.setIconTintResource(R.color.red);

                /*System.out.println("Fragment: " + productViewModel.getFavourite(productDetailTitleString));
                if(productViewModel.getFavourite(productDetailTitleString)) {
                    System.out.println("HI is true");
                    favouriteButton.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_favorite_24));
                    favouriteButton.setIconTintResource(R.color.red);
                }
                else {
                    favouriteButton.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_favorite_border_24));
                    favouriteButton.setIconTintResource(R.color.red);
                }*/


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