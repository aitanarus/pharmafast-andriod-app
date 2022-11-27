package com.example.pharmafast.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pharmafast.R;
import com.example.pharmafast.model.Product;
import com.example.pharmafast.viewmodel.CartViewModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private List<Product> products;
    private CartViewModel viewModel;

    public CartAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product_cart, parent, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) parent.getContext()).get(CartViewModel.class);
        return new CartAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.productCartTitle.setText(products.get(position).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(products.get(position).getPic())
                .into(holder.productCartPic);

        holder.productCartQuantity.setText(Integer.toString(products.get(position).getNumberInCart()));
        holder.productCartPrice.setText(Double.toString(products.get(position).getPrice()*products.get(position).getNumberInCart()));

        holder.addProductCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //make max number in cart == quantity
                int numberInCart = products.get(position).getNumberInCart();
                System.out.println(numberInCart++);
                products.get(position).setNumberInCart(numberInCart++);
                viewModel.addProductToCart(products.get(position));
            }
        });

        holder.deleteProductCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberInCart = products.get(position).getNumberInCart();
                System.out.println(numberInCart--);
                products.get(position).setNumberInCart(numberInCart--);
                viewModel.deleteProductFromCart(products.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productCartPic;
        TextView productCartTitle;
        TextView productCartPrice;
        TextView productCartQuantity;
        Button addProductCartButton;
        Button deleteProductCartButton;
        TextView itemsTotalCart;

        ViewHolder(View itemView){
            super(itemView);
            productCartPic = itemView.findViewById(R.id.productCartPic);
            productCartTitle = itemView.findViewById(R.id.productCartTitle);
            productCartQuantity = itemView.findViewById(R.id.productCartQuantity);
            productCartPrice = itemView.findViewById(R.id.productCartPrice);
            addProductCartButton = itemView.findViewById(R.id.addProductCartBtn);
            deleteProductCartButton = itemView.findViewById(R.id.deleteProductCartBtn);
            itemsTotalCart = itemView.findViewById(R.id.itemsTotalCart);
        }
    }

    public int getItemsTotalCart(){
        int totalItems = 0;
        for (int i = 0; i < products.size(); i++){
            totalItems += products.get(i).getNumberInCart();
        }
        return totalItems;
    }

    public double getPriceTotalCart(){
        double totalPrice = 0;
        for (int i = 0; i < products.size(); i++){
            totalPrice += products.get(i).getPrice()*products.get(i).getNumberInCart();
        }
        //Round to 2 decimal places
        totalPrice = Math.round(totalPrice*100.0)/100.0;
        return totalPrice;
    }
}
