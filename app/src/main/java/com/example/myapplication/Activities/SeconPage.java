package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.Adapters.SliderAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.SliderData;
import com.example.myapplication.Utils.ImageUtils;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class SeconPage extends AppCompatActivity {
    // Urls of our images.
    private static final String[] imageUrls = {
            "https://img.freepik.com/free-photo/top-view-table-full-delicious-food-composition_23-2149141353.jpg",
            "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe",
            "https://content3.jdmagicbox.com/comp/def_content/fast-food/22-fast-food-9-20wsq.jpg"
    };
    private CountDownTimer countDownTimer;
Button next;
    private final long COUNTDOWN_DURATION = 5000;
    private final long COUNTDOWN_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon_page);
        next = findViewById(R.id.next);
        next.setEnabled(false);
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = findViewById(R.id.slider);
        sliderDataArrayList.add(new SliderData(imageUrls[0]));
        sliderDataArrayList.add(new SliderData(imageUrls[1]));
        sliderDataArrayList.add(new SliderData(imageUrls[2]));
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(1);
        sliderView.setAutoCycle(true);
        sliderView.setEnabled(false);
        sliderView.startAutoCycle();
        startCountdown();
        next.setOnClickListener(view -> {
            startActivity(new Intent(SeconPage.this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = (millisUntilFinished / 1000) + 1;
                next.setText(secondsRemaining + "S Remaining." );
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