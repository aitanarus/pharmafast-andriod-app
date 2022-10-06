package com.example.pharmafast.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pharmafast.R;
import com.example.pharmafast.domain.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {
    private ArrayList<Product> products;

    public ProductAdapter(ArrayList<Product> products) { this.products = products;}

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product, parent, false);
        return new ProductAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.productTitle.setText(products.get(position).getTitle());
        holder.productPrice.setText(Double.toString(products.get(position).getPrice()));
        Glide.with(holder.itemView.getContext())
                .load(products.get(position).getPic())
                .into(holder.productPic);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView productTitle;
        ImageView productPic;
        TextView productPrice;
        ConstraintLayout productLayout;

        ViewHolder(View itemView){
            super(itemView);
            productTitle = itemView.findViewById(R.id.productTitle);
            productPic = itemView.findViewById(R.id.productPic);
            productPrice = itemView.findViewById(R.id.productPrice);
            productLayout = itemView.findViewById(R.id.productByCategoryLayout);
        }
    }
}
