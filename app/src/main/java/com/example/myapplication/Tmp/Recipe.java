package com.example.myapplication.Tmp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cover")
    private String cover;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "recipe_content")
    private String recipeContent;

    public Recipe() {

    }

    public Recipe(String cover, String title, String recipeContent) {
        this.cover = cover;
        this.title = title;
        this.recipeContent = recipeContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipeContent() {
        return recipeContent;
    }

    public void setRecipeContent(String recipeContent) {
        this.recipeContent = recipeContent;
    }
}
