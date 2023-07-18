package com.example.myapplication.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Tmp.Recipe;
import com.example.myapplication.Utils.RecipeViewModel;

public class RecipeViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private Recipe recipe;

    public RecipeViewModelFactory(Application application) {
        this.application = application;
    }

    public RecipeViewModelFactory(Application application, Recipe recipe) {
        this.application = application;
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeViewModel.class)) {
            return (T) new RecipeViewModel(application, this.recipe);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}