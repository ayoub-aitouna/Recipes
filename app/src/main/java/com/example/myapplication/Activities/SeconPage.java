package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import com.example.myapplication.Adapters.SliderAdapter;
import com.example.myapplication.Monetization.Distributor;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.SliderData;
import com.example.myapplication.Utils.State;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class SeconPage extends AppCompatActivity {
    // Urls of our images.

    private CountDownTimer countDownTimer;
    Button next;
    private final long COUNTDOWN_DURATION = State.m_data.getConfig().getWaiting_duration_sec() * 1000L;
    private final long COUNTDOWN_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon_page);
        next = findViewById(R.id.next);
        next.setEnabled(false);
        Distributor.ShowBanner(findViewById(R.id.banner));
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = findViewById(R.id.slider);
        for (String url : State.m_data.getConfig().getLatest_recipes_imgs())
            sliderDataArrayList.add(new SliderData(url));
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(1);
        sliderView.setAutoCycle(true);
        sliderView.setEnabled(false);
        sliderView.startAutoCycle();
        startCountdown();
        next.setOnClickListener(view -> {
            Distributor.ShowInter(() -> {
                startActivity(new Intent(SeconPage.this, MainActivity.class));
                finish();
            });
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @SuppressLint("SetTextI18n")
    private void startCountdown() {
        countDownTimer = new CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = (millisUntilFinished / 1000) + 1;
                next.setText(secondsRemaining + "S Remaining.");
            }

            @Override
            public void onFinish() {
                next.setText("NEXT");
                next.setEnabled(true);
            }
        };

        countDownTimer.start(); // Start the countdown
    }
}