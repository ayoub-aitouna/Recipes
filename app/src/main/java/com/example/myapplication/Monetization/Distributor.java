package com.example.myapplication.Monetization;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.ViewGroup;

import com.example.myapplication.Monetization.Ads.App_Promote;
import com.example.myapplication.Monetization.Ads.FacebookAd;
import com.example.myapplication.Monetization.Ads.Network_Admob;
import com.example.myapplication.Monetization.Ads.Network_UnityAd;
import com.example.myapplication.Monetization.Ads.Yandex;
import com.example.myapplication.Utils.Config;
import com.example.myapplication.Utils.State;
import com.example.myapplication.interfaces.InterCallback;
import com.example.myapplication.interfaces.RewardCall;

@SuppressLint("StaticFieldLeak")
public class Distributor {
    static Activity activity;
    static App_Promote app_promote;
    static Network_UnityAd unityAd;
    static Network_Admob admob;
    static FacebookAd fb;
    static Yandex yandex;

    public static void Init(Activity activity1) {
        activity = activity1;
        app_promote = new App_Promote(activity);
        fb = new FacebookAd(activity);
        admob = new Network_Admob(activity, State.m_data.getAds().getAds_info().getAdmob());
        unityAd = new Network_UnityAd(activity);
        yandex = new Yandex(activity);

        LoadInter();
        LoadReward();
    }

    static public void LoadInter() {
        switch (State.m_data.getAds().getSettings().getAdsType()) {
            case Config.fb:
                fb.LoadInter();
                break;
            case Config.Unity:
                unityAd.LoadInter();
                break;
            case Config.Admob:
                admob.LoadInter();
                break;
            case Config.Yandex:
                yandex.LoadInter();
                break;

        }
    }

    static public void ShowBanner(ViewGroup layout) {
        try {
            switch (State.m_data.getAds().getSettings().getAdsType()) {
                case Config.AppPromote:
                    app_promote.banner(layout);
                    break;
                case Config.Yandex:
                    yandex.Banner(layout);
                    break;

                case Config.fb:
                    fb.ShowBanner(layout);
                    break;
                case Config.Unity:
                    unityAd.ShowBanner(layout);
                    break;
                case Config.Admob:
                    admob.showBanner(layout);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static public void ShowInter(InterCallback callback) {
        switch (State.m_data.getAds().getSettings().getAdsType()) {
            case Config.AppPromote:
                app_promote.Inter(() -> response(callback));
                break;
            case Config.fb:
                fb.ShowInter(() -> response(callback));
                break;
            case Config.Unity:
                unityAd.ShowInter(() -> response(callback));
                break;
            case Config.Admob:
                admob.showInterstitial(() -> response(callback));
                break;

            case Config.Yandex:
                yandex.ShowInter(() -> response(callback));
                break;
            default:
                response(callback);
                break;
        }
    }

    static void response(InterCallback callback) {
        LoadInter();
        callback.call();
    }

    static public void ShowNative(ViewGroup layout) {
        switch (State.m_data.getAds().getSettings().getNativeType()) {
            case Config.AppPromote:
                app_promote.Native(layout);
                break;
            case Config.fb:
                FacebookAd.static_Native(layout, activity);
                break;
            case Config.Admob:
                admob.AdmobNative(layout);
                break;
            case Config.Yandex:
                yandex.LoadNative(layout);
                break;
        }
    }

    static public void LoadReward() {
        switch (State.m_data.getAds().getSettings().getAdsType()) {
            case Config.fb:
                fb.LoadReward();
                break;
            case Config.Unity:
                unityAd.LoadReward();
                break;
            case Config.Admob:
                admob.LoadReward();
                break;

            case Config.Yandex:
                yandex.LoadReward();
                break;
        }
    }

    static public void ShowReward(RewardCall call) {
        switch (State.m_data.getAds().getSettings().getNativeType()) {
            case Config.AppPromote:
                app_promote.Inter(() -> {

                });
                break;
            case Config.fb:
                fb.ShowReward(call);
                break;
            case Config.Admob:
                admob.ShowReward(call);
                break;

            case Config.Yandex:
                yandex.ShowReward(call);
                break;
            case Config.Unity:
                unityAd.ShowReward(call);
                break;
        }
    }

    static public void ShowOpenAd() {
        admob.showOpenAd(() -> {

        });
    }
}
