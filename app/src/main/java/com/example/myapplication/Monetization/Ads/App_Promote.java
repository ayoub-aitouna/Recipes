package com.example.myapplication.Monetization.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.gfx.adPromote.AppPromote;
import com.gfx.adPromote.BannerPromote;
import com.gfx.adPromote.Interfaces.OnBannerListener;
import com.gfx.adPromote.Interfaces.OnInterstitialAdListener;
import com.gfx.adPromote.Interfaces.OnNativeListener;
import com.gfx.adPromote.Interfaces.OnPromoteListener;
import com.gfx.adPromote.InterstitialPromote;
import com.gfx.adPromote.InterstitialStyle;
import com.gfx.adPromote.NativePromote;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Global;
import com.example.myapplication.interfaces.InterCallback;


public class App_Promote {
    private static final String TAG = "App_Promote";
    Activity activity;
    InterstitialPromote interstitialPromote;

    public App_Promote(Activity activity) {
        this.activity = activity;
        AppPromote.initializePromote(activity, Global.URL);
        AppPromote.setOnPromoteListener(new OnPromoteListener() {
            @Override
            public void onInitializeSuccessful() {
                //AppPromote onInitializeSuccessful
                LoadInter();
            }

            @Override
            public void onInitializeFailed(String error) {
                //AppPromote onInitializeFailed
            }
        });
    }

    public void banner(ViewGroup layout) {
        BannerPromote bannerPromote = new BannerPromote(activity);
        bannerPromote.setInstallTitle("Play");
        bannerPromote.setInstallColor("#FFC107");
        bannerPromote.setDescriptionColor(R.color.black);
        bannerPromote.setOnBannerListener(new OnBannerListener() {
            @Override
            public void onBannerAdLoaded() {
                //banner loaded.
            }

            @Override
            public void onBannerAdClicked() {
                //banner clicked.
            }

            @Override
            public void onBannerAdFailedToLoad(String error) {
                //banner failed to load.
                Log.d(TAG, "onBannerAdFailedToLoad: " + error);

            }
        });
        layout.addView(bannerPromote);


    }


    public void LoadInter() {
        interstitialPromote = new InterstitialPromote(activity);
        interstitialPromote.setStyle(InterstitialStyle.Advance);
        interstitialPromote.setInstallColor(R.color.black);
        interstitialPromote.setTimer(5);//5 second to closed the Ad.
        interstitialPromote.setInstallTitle("Play Now");
        interstitialPromote.setRadiusButton(10); //corner of button radius.
        interstitialPromote.setOnInterstitialAdListener(new OnInterstitialAdListener() {
            @Override
            public void onInterstitialAdLoaded() {
                //interstitialAd loaded.
            }

            @Override
            public void onInterstitialAdClosed() {
                //interstitialAd closed.
            }

            @Override
            public void onInterstitialAdClicked() {
                //interstitialAd clicked.
            }

            @Override
            public void onInterstitialAdFailedToLoad(String error) {
                //Interstitial failed to load
            }
        });
    }

    public void Inter(InterCallback callback) {
        if (interstitialPromote != null && interstitialPromote.isAdLoaded()) {
            interstitialPromote.show();
            interstitialPromote.setOnAdClosed(callback::call);
        } else {
            callback.call();
        }
    }


    public void Native(ViewGroup layout) {
        Log.d(TAG, "Native: app promotion");
        NativePromote nativePromote = new NativePromote(activity);
        nativePromote.setButtonColor("#1C7DCA");
        nativePromote.setButtonTitle("Play Now");
        nativePromote.setRadiusButton(10);  //corner of button radius.
        nativePromote.setOnNativeListener(new OnNativeListener() {
            @Override
            public void onNativeAdLoaded() {
                //Native loaded.
            }

            @Override
            public void onNativeAdClicked() {
                //Native clicked.
            }

            @Override
            public void onNativeAdFailedToLoad(String error) {
                //"Native failed to load
                Log.d(TAG, "onNativeAdFailedToLoad: " + error);
            }
        });
        layout.addView(nativePromote);

    }
}
