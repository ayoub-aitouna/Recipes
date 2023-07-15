package com.example.myapplication.Fragments;

import static com.example.myapplication.Utils.Utils.SetUpRecipeList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.RecipeAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Room.RecipeRepository;
import com.example.myapplication.Tmp.Recipe;
import com.example.myapplication.Utils.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class Fav extends Fragment {
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private RecipeViewModel recipeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        adapter = new RecipeAdapter(new ArrayList<>(), getActivity(), () -> {
            startActivity(new Intent(getActivity(), Recipe.class));
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recipeViewModel.getRecipeListLiveData().observe(getActivity(), recipes -> {
            adapter.setRecipes(recipes);
        });
        return (view);
    }


}
