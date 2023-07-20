package com.example.myapplication.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.Monetization.Distributor;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.Recipe;
import com.example.myapplication.Utils.State;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Recipe> itemList;
    private Context context;
    OnRecipeClicked onRecipeClicked;
    private static final int VIEW_TYPE_FIRST = 0;
    private static final int VIEW_TYPE_SECOND = 1;

    public RecipeAdapter(List<Recipe> itemList, Context context, OnRecipeClicked onRecipeClicked) {
        this.itemList = itemList;
        this.context = context;
        this.onRecipeClicked = onRecipeClicked;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position + 1) % 5 == 0) {
            return VIEW_TYPE_SECOND;
        } else {
            return VIEW_TYPE_FIRST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_FIRST) {
            return new ViewHolder(inflater.inflate(R.layout.recipe_tmp, parent, false));
        } else if (viewType == VIEW_TYPE_SECOND) {
            return new NativeViewHolder(inflater.inflate(R.layout.native_container, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof ViewHolder)) {
            return;
        }
        ViewHolder m_holder = (ViewHolder) holder;
        Recipe item = itemList.get(position);
        m_holder.textView.setText(item.getTitle());
        Glide.with(context).load(item.getCover()).into(m_holder.imageView);
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

    class NativeViewHolder extends RecyclerView.ViewHolder {
        public NativeViewHolder(@NonNull View itemView) {
            super(itemView);
            Distributor.ShowNative(itemView.findViewById(R.id.native_holder));
        }
    }

    public interface OnRecipeClicked {
        void Clicked();
    }
}
