package com.example.myapplication.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Tmp.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Query("delete FROM recipes where  title = :recipeName AND recipe_content = :Content")
    void delete(String recipeName, String Content);

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT COUNT(*) FROM recipes WHERE title = :recipeName AND recipe_content = :Content")
    LiveData<Integer> CountRecipesByNameAndContent(String recipeName, String Content);
}
