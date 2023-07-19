package com.example.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    private static final String PREFERENCES_NAME = "my_preferences";

    public static void writeToPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }
}
