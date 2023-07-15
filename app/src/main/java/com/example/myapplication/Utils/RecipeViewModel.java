package com.example.myapplication.Utils;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.Room.RecipeRepository;
import com.example.myapplication.Tmp.Recipe;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> recipeListLiveData;

    public RecipeViewModel(Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        recipeListLiveData = recipeRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getRecipeListLiveData() {
        return recipeListLiveData;
    }

    public boolean DoesItExists(Recipe recipe) {
        return false;
    }

}
