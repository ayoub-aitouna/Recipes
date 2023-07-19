package com.example.myapplication.Monetization.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Utils.Config;
import com.example.myapplication.Utils.State;
import com.example.myapplication.interfaces.InterCallback;
import com.example.myapplication.interfaces.RewardCall;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.MobileAds;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;
import com.yandex.mobile.ads.nativeads.NativeAd;
import com.yandex.mobile.ads.nativeads.NativeAdLoadListener;
import com.yandex.mobile.ads.nativeads.NativeAdLoader;
import com.yandex.mobile.ads.nativeads.NativeAdMedia;
import com.yandex.mobile.ads.nativeads.NativeAdRequestConfiguration;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;

public class Yandex {
    private String TAG = "Network_Yandex";
    private Activity activity;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;

    public Yandex(Activity activity) {
        MobileAds.initialize(activity, () -> Log.d(TAG, "SDK initialized"));
        MobileAds.enableDebugErrorIndicator(true);
        this.activity = activity;
    }

    RewardCall call;

    public void LoadReward() {
        mRewardedAd = new RewardedAd(activity);
        mRewardedAd.setAdUnitId(State.m_data.getAds().getAds_info().getYandex().getReward());
        final AdRequest adRequest = new AdRequest.Builder().build();
        mRewardedAd.setRewardedAdEventListener(new RewardedAdEventListener() {
            @Override
            public void onRewarded(final Reward reward) {
                call.call("", 0);
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(final AdRequestError adRequestError) {

            }

            @Override
            public void onAdShown() {

            }

            @Override
            public void onAdDismissed() {
                call.error();
            }

            @Override
            public void onLeftApplication() {

            }

            @Override
            public void onReturnedToApplication() {

            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });

        // Loading ads.
        mRewardedAd.loadAd(adRequest);
    }

    public void ShowReward(RewardCall call) {
        this.call = call;
        if (mRewardedAd.isLoaded()) {
            mRewardedAd.show();
        } else {
            call.error();
        }
    }

    public void Banner(ViewGroup banner) {
        BannerAdView mBannerAdView = new BannerAdView(activity);
        mBannerAdView.setAdUnitId(State.m_data.getAds().getAds_info().getYandex().getBanner());
        mBannerAdView.setAdSize(AdSize.BANNER_320x50);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded: ");

            }

            @Override
            public void onAdFailedToLoad(AdRequestError adRequestError) {
                Log.d(TAG, "onAdFailedToLoad: " + adRequestError.getDescription());
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });
        // Loading the ad.
        banner.addView(mBannerAdView);
        mBannerAdView.loadAd(adRequest);
    }

    InterCallback callback;

    public void LoadInter() {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(State.m_data.getAds().getAds_info().getYandex().getBanner());
        final AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setInterstitialAdEventListener(new InterstitialAdEventListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Inter onAdLoaded: ");

            }

            @Override
            public void onAdFailedToLoad(AdRequestError adRequestError) {
                Log.d(TAG, "Inter onAdFailedToLoad: " + adRequestError.getDescription());
            }

            @Override
            public void onAdShown() {

            }

            @Override
            public void onAdDismissed() {
                callback.call();
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onLeftApplication() {

            }

            @Override
            public void onReturnedToApplication() {

            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });

    }

    public void ShowInter(InterCallback callback) {
        this.callback = callback;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            callback.call();
        }


    }


    private NativeAdLoader mNativeAdLoader;

    public void LoadNative(ViewGroup layout) {
        mNativeAdLoader = new NativeAdLoader(activity);
        mNativeAdLoader.setNativeAdLoadListener(new NativeAdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull final NativeAd nativeAd) {
                bind_NativeAd(nativeAd, layout);
                //  callback.call();
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                Log.d("SAMPLE_TAG", adRequestError.getDescription());
                //   callback.call();
            }
        });
        final NativeAdRequestConfiguration nativeAdRequestConfiguration =
                new NativeAdRequestConfiguration.Builder(State.m_data.getAds().getAds_info().getYandex().getNative())
                        .setShouldLoadImagesAutomatically(true)
                        .build();
        mNativeAdLoader.loadAd(nativeAdRequestConfiguration);


    }

    private void bind_NativeAd(@NonNull final NativeAd nativeAd, ViewGroup layout) {
        NativeBannerView mNativeBannerView = new NativeBannerView(activity);
        mNativeBannerView.setAd(nativeAd);
        mNativeBannerView.setVisibility(View.VISIBLE);
        layout.addView(mNativeBannerView);
    }


    private void configureMediaView(@NonNull final NativeAd nativeAd) {
        final NativeAdMedia nativeAdMedia = nativeAd.getAdAssets().getMedia();
        if (nativeAdMedia != null) {
            //you can use the aspect ratio if you need it to determine the size of media view.
            final float aspectRatio = nativeAdMedia.getAspectRatio();
        }
    }


    public void destroy() {
        if (mRewardedAd != null) {
            mRewardedAd.setRewardedAdEventListener(null);
            mRewardedAd.destroy();
        }
    }
}
