package com.example.myapplication.Tmp;

import java.util.ArrayList;

public class Category {
    String Name;
    String Cover;
    ArrayList<Recipe> Content;

    public Category() {
        Content = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public String getCover() {
        return Cover;
    }

    public ArrayList<Recipe> getContent() {
        return Content;
    }
}
