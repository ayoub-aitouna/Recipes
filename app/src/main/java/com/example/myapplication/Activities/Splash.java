package com.example.myapplication.Activities;

import static com.example.myapplication.Utils.Global.connectionType;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Dialog.Retry;
import com.example.myapplication.Dialog.UpdateDialog;
import com.example.myapplication.Monetization.Distributor;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.Data;
import com.example.myapplication.Utils.Config;
import com.example.myapplication.Utils.Global;
import com.example.myapplication.Utils.State;
import com.google.gson.Gson;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends AppCompatActivity {
    private static String TAG;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(getString(R.string.one_signal_id));
        LoadData();

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
        Log.d("loadJSONFromAsset", json);
        return json;
    }


    private void LoadData() {
        if (connectionType == Config.ConnectionType.Online) {
            request = new JsonObjectRequest(Request.Method.GET, Global.URL, null, response -> {
                State.m_data = new Gson().fromJson(String.valueOf(response), Data.class);
                Distributor.Init(Splash.this);
                OpenActivity();
            }, error -> {
                Log.d(TAG, "LoadData: " + error.getMessage());
                Show_Error_Dialog();
            });
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
            requestQueue.getCache().clear();
        } else {

            State.m_data = new Gson().fromJson(String.valueOf(loadJSONFromAsset()), Data.class);
            Distributor.Init(Splash.this);
            OpenActivity();
        }
    }

    private void OpenActivity() {
        if (State.m_data.getAds().getSettings().isSuspended()) ShowDialog();
        else {
            Glide.with(this).load(State.m_data.getConfig().getSplash()).into((ImageView) findViewById(R.id.bg));
            new Handler().postDelayed(() -> {
                startActivity(new Intent(Splash.this, SeconPage.class));
                finish();
            }, 6 * 1000);
        }
    }

    private void Show_Error_Dialog() {
        Retry retry = new Retry(this, this::LoadData);
        retry.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        retry.getWindow().setGravity(Gravity.CENTER);
        retry.setCancelable(false);
        retry.show();
    }

    private void ShowDialog() {
        UpdateDialog updateDialog = new UpdateDialog(this);
        updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        updateDialog.getWindow().setGravity(Gravity.CENTER);
        updateDialog.setCancelable(false);
        updateDialog.show();
    }


}