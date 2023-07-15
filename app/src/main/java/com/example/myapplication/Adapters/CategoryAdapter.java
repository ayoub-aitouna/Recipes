package com.example.myapplication.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> itemList;
    private Context context;
    OnCategorySelected onCategorySelected;

    public CategoryAdapter(List<Category> itemList, Context context, OnCategorySelected onCategorySelected) {
        this.itemList = itemList;
        this.context = context;
        this.onCategorySelected = onCategorySelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category item = itemList.get(position);
        holder.textView.setText(item.getName());
        Glide.with(context).load(item.getCover()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.cover);
            itemView.setOnClickListener(view -> onCategorySelected.Click(getAdapterPosition()));
        }
    }

    public interface OnCategorySelected {
        void Click(int id);
    }
}

