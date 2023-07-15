package com.example.myapplication.Activities;

import static com.example.myapplication.Utils.Utils.SetUpRecipeList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.Adapters.RecipeAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Utils.State;

public class RecipeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        SetUpRecipeList(this, findViewById(R.id.recyler), State.SelectedCategory.getContent());
    }
}