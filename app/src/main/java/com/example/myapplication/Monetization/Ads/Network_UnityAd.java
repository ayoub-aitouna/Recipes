package com.example.myapplication.Monetization.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.myapplication.Utils.Config;
import com.example.myapplication.Utils.State;
import com.example.myapplication.interfaces.InterCallback;
import com.example.myapplication.interfaces.RewardCall;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class Network_UnityAd {
    static final String TAG = "UnityAds";
    Activity activity;
    private boolean InterLoaded = false;

    public Network_UnityAd(Activity activity) {
        this.activity = activity;
        UnityAds.initialize(activity, State.m_data.getAds().getAds_info().getUnity().getId(), false, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                Log.e("UnityAdsExample", "Unity Ads initialization complete ");
                LoadInter();

            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
                Log.e("UnityAdsExample", "Unity Ads initialization failed with error: [" + error + "] " + message);

            }
        });


    }


    public void ShowBanner(ViewGroup layout) {
        BannerView bottomBanner = new BannerView(activity, State.m_data.getAds().getAds_info().getUnity().getBanner(), new UnityBannerSize(320, 50));
        bottomBanner.setListener(new BannerView.Listener() {
            @Override
            public void onBannerLoaded(BannerView bannerAdView) {
                super.onBannerLoaded(bannerAdView);
                Log.d(TAG, "onBannerLoaded: " + bannerAdView.getPlacementId());
            }

            @Override
            public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
                super.onBannerFailedToLoad(bannerAdView, errorInfo);
                Log.d(TAG, "onBannerFailedToLoad: " + errorInfo.errorMessage);
            }

            @Override
            public void onBannerClick(BannerView bannerAdView) {
                super.onBannerClick(bannerAdView);
            }

            @Override
            public void onBannerLeftApplication(BannerView bannerAdView) {
                super.onBannerLeftApplication(bannerAdView);
            }
        });
        bottomBanner.load();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        bottomBanner.setLayoutParams(params);
        layout.addView(bottomBanner);
    }

    public void ShowInter(InterCallback callback) {
        if (InterLoaded) {
            UnityAds.show((Activity) activity, State.m_data.getAds().getAds_info().getUnity().getInter(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    callback.call();
                }

                @Override
                public void onUnityAdsShowStart(String placementId) {

                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                    callback.call();

                }
            });

        } else
            callback.call();

    }


    public void LoadInter() {
        UnityAds.load(State.m_data.getAds().getAds_info().getUnity().getInter(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                InterLoaded = true;
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                InterLoaded = false;
                Log.e("UnityAdsExample", "Unity Ads failed to load ad for " + placementId + " with error: [" + error + "] " + message);
                //  LoadInter();

            }
        });
    }

    Boolean LoadedReward = false;

    public void LoadReward() {
        UnityAds.load(State.m_data.getAds().getAds_info().getUnity().getReward(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                LoadedReward = true;
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                LoadedReward = false;
            }
        });
    }

    public void ShowReward(RewardCall call) {
        if (LoadedReward) {
            UnityAds.show(activity, State.m_data.getAds().getAds_info().getUnity().getReward(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    call.error();
                }

                @Override
                public void onUnityAdsShowStart(String placementId) {

                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                    call.call("", 0);
                }
            });
        } else {
            call.error();
        }


    }
}
