package com.example.myapplication.Room;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.myapplication.Tmp.Recipe;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;

    public RecipeRepository(Context context) {
        RecipeDatabase database = Room.databaseBuilder(context, RecipeDatabase.class, "recipe-database")
                .build();
        recipeDao = database.recipeDao();
    }

    public void insertRecipe(Recipe recipe) {
        recipeDao.insert(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipeDao.update(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeDao.delete(recipe);
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }

    public boolean DoesItExists(Recipe recipe) {
        int count;
        count = recipeDao.CountRecipesByNameAndContent(recipe.getTitle(), recipe.getRecipeContent());
        return (count > 0);
    }
}
