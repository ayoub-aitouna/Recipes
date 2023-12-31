package com.example.myapplication.Tmp;

import java.util.ArrayList;

public class Data {
    AppConfig Config;
    AdsData Ads;
    ArrayList<Category> categories;
    ArrayList<Recipe> Featured;
    ArrayList<Recipe> Suggestions;

    public Data(AppConfig config, AdsData ads, ArrayList<Category> categories, ArrayList<Recipe> featured, ArrayList<Recipe> suggestions) {
        Config = config;
        Ads = ads;
        this.categories = categories;
        Featured = featured;
        Suggestions = suggestions;
    }

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

    public AdsData getAds() {
        return Ads;
    }

    public AppConfig getConfig() {
        return Config;
    }
}
