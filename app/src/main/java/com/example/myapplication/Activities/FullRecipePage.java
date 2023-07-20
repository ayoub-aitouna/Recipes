package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MVVM.RecipeViewModelFactory;
import com.example.myapplication.Monetization.Distributor;
import com.example.myapplication.R;
import com.example.myapplication.Room.RecipeRepository;
import com.example.myapplication.Utils.RecipeViewModel;
import com.example.myapplication.Utils.State;
import com.example.myapplication.interfaces.InterCallback;

public class FullRecipePage extends AppCompatActivity {
    ImageView favoriteButton;
    RecipeRepository recipeRepository;
    RecipeViewModel recipeViewModel;
    RecipeViewModelFactory factory;
    boolean hearted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipe);
        Distributor.ShowBanner(findViewById(R.id.banner));
        factory = new RecipeViewModelFactory(getApplication(), State.SelectedRecipe);
        favoriteButton = findViewById(R.id.fav);
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);
        recipeRepository = new RecipeRepository(this);
        recipeViewModel.Included().observe(this, data -> {
            hearted = data > 0;
            Log.d("Recipe", "onCreate: " + hearted);
            favoriteButton.setTag(hearted ? "1" : "0");
            toggle_btn();
        });
        favoriteButton.setOnClickListener(v -> {
            Distributor.ShowInter(() -> {
                if (favoriteButton.getTag() != "0")
                    recipeRepository.insertRecipe(State.SelectedRecipe);
                else recipeRepository.deleteRecipe(State.SelectedRecipe);
                LoadAnimation();
            });
        });
        LoadData();
    }

    private void LoadData() {
        Glide.with(this).load(State.SelectedRecipe.getCover()).fitCenter().into((ImageView) findViewById(R.id.img));
        ((TextView) findViewById(R.id.conte)).setText(State.SelectedRecipe.getRecipeContent());
    }

    private void LoadAnimation() {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.fav_btn);
        animatorSet.setTarget(favoriteButton);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toggle_btn();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Animation canceled
                toggle_btn();
            }
        });
        animatorSet.start();
    }

    private void toggle_btn() {
        favoriteButton.setScaleX(1.0f);
        favoriteButton.setScaleY(1.0f);
        Log.d("RESPI", "toggle_btn: " + favoriteButton.getTag());
        if (favoriteButton.getTag() != "0") {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_24);
            favoriteButton.setTag("0");
        } else {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24);
            favoriteButton.setTag("1");
        }
    }
}