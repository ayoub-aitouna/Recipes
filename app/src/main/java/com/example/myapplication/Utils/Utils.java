package com.example.myapplication.Utils;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activities.FullRecipePage;
import com.example.myapplication.Adapters.RecipeAdapter;
import com.example.myapplication.Tmp.Recipe;

import java.util.ArrayList;

public class Utils {
    static public void SetUpRecipeList(Context m_context, RecyclerView recyclerView, ArrayList<Recipe> suggestions) {
        RecipeAdapter adapter = new RecipeAdapter(suggestions, m_context, () -> m_context.startActivity(new Intent(m_context, FullRecipePage.class)));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
