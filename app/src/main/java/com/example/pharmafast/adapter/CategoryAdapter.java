package com.example.pharmafast.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pharmafast.R;
import com.example.pharmafast.domain.Category;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> categories;

    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryTitle.setText(categories.get(position).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(categories.get(position).getPic())
                .into(holder.cateooryPic);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;
        ImageView cateooryPic;
        MaterialCardView categoryLayout;

        ViewHolder(View itemView){
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryText);
            cateooryPic = itemView.findViewById(R.id.categoryPic);
            categoryLayout = itemView.findViewById(R.id.productLayout);
        }
    }
}