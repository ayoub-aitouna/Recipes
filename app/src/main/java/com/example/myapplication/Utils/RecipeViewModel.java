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
    private LiveData<Integer> contained;

    public RecipeViewModel(Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        recipeListLiveData = recipeRepository.getAllRecipes();
//        contained = recipeRepository.DoesItExists()
    }

    public RecipeViewModel(Application application, Recipe recipe) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        contained = recipeRepository.DoesItExists(recipe);
    }

    public LiveData<List<Recipe>> getRecipeListLiveData() {
        return recipeListLiveData;
    }

    public LiveData<Integer> Included() {
        return contained;
    }

}
