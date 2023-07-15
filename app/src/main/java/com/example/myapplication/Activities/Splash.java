package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;
import com.example.myapplication.Tmp.Data;
import com.example.myapplication.Utils.State;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            State.m_data = new Data();
            startActivity(new Intent(Splash.this, SeconPage.class));
            finish();
        }, 3 * 1000);
    }
}