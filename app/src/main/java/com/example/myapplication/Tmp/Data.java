package com.example.myapplication.Tmp;

import java.util.ArrayList;

public class Data {
    ArrayList<Category> categories;
    ArrayList<Recipe> Featured;
    ArrayList<Recipe> Suggestions;

    public Data() {
        this.categories = new ArrayList<>();
        this.Featured = new ArrayList<>();
        this.Suggestions = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Recipe> getFeatured() {
        return Featured;
    }

    public ArrayList<Recipe> getSuggestions() {
        return Suggestions;
    }
}
