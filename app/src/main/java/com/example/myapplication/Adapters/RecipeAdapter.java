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
import com.example.myapplication.Tmp.Recipe;
import com.example.myapplication.Utils.State;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> itemList;
    private Context context;
    OnRecipeClicked onRecipeClicked;

    public RecipeAdapter(List<Recipe> itemList, Context context, OnRecipeClicked onRecipeClicked) {
        this.itemList = itemList;
        this.context = context;
        this.onRecipeClicked = onRecipeClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_tmp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe item = itemList.get(position);
        holder.textView.setText(item.getTitle());
        Glide.with(context).load(item.getCover()).into(holder.imageView);
    }
    public void setRecipes(List<Recipe> recipes) {
        this.itemList = recipes;
        notifyDataSetChanged();
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
            itemView.setOnClickListener(view -> {
                State.SelectedRecipe = itemList.get(getAdapterPosition());
                onRecipeClicked.Clicked();
            });
        }
    }

    public interface OnRecipeClicked {
        void Clicked();
    }
}
