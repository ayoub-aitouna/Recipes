package com.example.myapplication.Tmp;

import java.util.ArrayList;

public class AppConfig {
    String Splash;
    ArrayList<String> latest_recipes_imgs;
    int waiting_duration_sec;

    public AppConfig(String splash, ArrayList<String> latest_recipes_imgs, int waiting_duration_sec) {
        Splash = splash;
        this.latest_recipes_imgs = latest_recipes_imgs;
        this.waiting_duration_sec = waiting_duration_sec;
    }

    public int getWaiting_duration_sec() {
        return waiting_duration_sec;
    }

    public String getSplash() {
        return Splash;
    }

    public ArrayList<String> getLatest_recipes_imgs() {
        return latest_recipes_imgs;
    }
}
