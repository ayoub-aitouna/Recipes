package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.Tmp.Data;
import com.example.myapplication.Utils.State;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            State.m_data = new Gson().fromJson(String.valueOf(loadJSONFromAsset()), Data.class);
            startActivity(new Intent(Splash.this, SeconPage.class));
            finish();
        }, 3 * 1000);
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("Data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.d("loadJSONFromAsset",  json);
        return json;
    }
}