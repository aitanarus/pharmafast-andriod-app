package com.example.pharmafast.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pharmafast.R;
import com.example.pharmafast.model.Category;
import com.example.pharmafast.view.fragment.ProductByCategoryFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
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
                .into(holder.categoryPic);
        onClickCategoryItem(holder);
    }

    //click on category item
    private void onClickCategoryItem(ViewHolder holder) {
        Bundle bundle=new Bundle();
        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductByCategoryFragment productByCategoryFragment = new ProductByCategoryFragment();
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_main, productByCategoryFragment).commit();
                bundle.putString("CATEGORY NAME", (String) holder.categoryTitle.getText());
                productByCategoryFragment.setArguments(bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;
        ImageView categoryPic;
        MaterialCardView categoryLayout;

        ViewHolder(View itemView){
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryText);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
        }

    }
}
