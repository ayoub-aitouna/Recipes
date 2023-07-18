package com.example.myapplication.Room;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.myapplication.Tmp.Recipe;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private ExecutorService executorService;

    public RecipeRepository(Context context) {
        RecipeDatabase database = Room.databaseBuilder(context, RecipeDatabase.class, "recipe-database").build();
        recipeDao = database.recipeDao();
        executorService = Executors.newSingleThreadExecutor();

    }

    public void insertRecipe(Recipe recipe) {
        executorService.execute(() -> {
            recipeDao.insert(recipe);
        });
    }

    public void updateRecipe(Recipe recipe) {
        executorService.execute(() -> {
            recipeDao.update(recipe);
        });
    }

    public void deleteRecipe(Recipe recipe) {
        executorService.execute(() -> {
            Log.d("deleteRecipe", "deleteRecipe: Deleteing Recipe " + recipe.getTitle() + " " + recipe.getRecipeContent());
            recipeDao.delete(recipe.getTitle(), recipe.getRecipeContent());
        });
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }

    public LiveData<Integer> DoesItExists(Recipe recipe) {
        return (recipeDao.CountRecipesByNameAndContent(recipe.getTitle(), recipe.getRecipeContent()));
    }
}
