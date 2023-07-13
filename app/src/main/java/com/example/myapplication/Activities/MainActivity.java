package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Fragments.Discover;
import com.example.myapplication.Fragments.Fav;
import com.example.myapplication.Fragments.Home;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Home m_home;
    Fav m_fav;
    Discover m_discover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_home = new Home();
        m_fav = new Fav();
        m_discover = new Discover();
        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, m_home)
                    .commit();
            return true;
        }
        if (item.getItemId() == R.id.disover) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, m_discover)
                    .commit();
            return true;
        }
        if (item.getItemId() == R.id.fav) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, m_fav)
                    .commit();
            return true;
        }
        return false;
    }
}