package com.example.myapplication.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.Tmp.Recipe;

@Database(entities = {Recipe.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}