package com.example.myapplication.Monetization.Ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.Utils.State;
import com.example.myapplication.interfaces.RewardCall;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Config;
import com.example.myapplication.interfaces.InterCallback;
import com.example.myapplication.models.Adtype;


public class Network_Admob {
    private static final String TAG = "Admob";
    private boolean interHasBeenShown;
    private RewardedAd mRewardedAd;

    public interface AdFinished {
        void onAdFinished();
    }

    Adtype adtype;
    private final Activity mActivity;
    private InterstitialAd mInterstitialAd;

    public Network_Admob(Activity Activity, Adtype adtype) {
        this.mActivity = Activity;
        this.adtype = adtype;
        MobileAds.initialize(Activity);
    }

    public void showInterstitialLoading(final AdFinished adFinished) {
        interHasBeenShown = false;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(mActivity, adtype.getInter(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                if (!interHasBeenShown) {
                    interHasBeenShown = true;
                    mInterstitialAd.show(mActivity);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            mInterstitialAd = null;
                            adFinished.onAdFinished();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            mInterstitialAd = null;
                            adFinished.onAdFinished();
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null;
                        }
                    });
                } else {
                    adFinished.onAdFinished();
                }


            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
                adFinished.onAdFinished();
            }
        });

    }

    public void showBanner(ViewGroup adContainer) {
        AdView adView = new AdView(mActivity);
        adView.setAdUnitId(adtype.getBanner());
        adView.setAdSize(AdSize.BANNER);
        adContainer.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded: ");

            }
        });

    }

    public void LoadInter() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(mActivity, adtype.getInter(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.d(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }

    public void showInterstitial(final AdFinished adFinished) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(mActivity);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    adFinished.onAdFinished();
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;

                }
            });
        } else {
            adFinished.onAdFinished();
        }
    }


    private void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);


    }


    public void AdmobNative(ViewGroup frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(mActivity, adtype.getNative());
        builder.forNativeAd(nativeAd -> {
            nativeAd = nativeAd;
            frameLayout.setVisibility(View.VISIBLE);
            NativeAdView adView = (NativeAdView) mActivity.getLayoutInflater().inflate(R.layout.admob_native_ad_layout, null);
            populateUnifiedNativeAdView(nativeAd, adView);
            frameLayout.removeAllViews();
            frameLayout.addView(adView);

        });

        VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(false).build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
            }
        }).build();

        adLoader.loadAd(new AdManagerAdRequest.Builder().build());


    }


    public void LoadReward() {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(mActivity, "ca-app-pub-3940256099942544/5224354917", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error.
                Log.d(TAG, loadAdError.toString());
                mRewardedAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                mRewardedAd = rewardedAd;
                Log.d(TAG, "Ad was loaded.");
            }
        });
    }


    public void ShowReward(RewardCall callback) {
        if (mRewardedAd != null) {
            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Log.d(TAG, "Ad dismissed fullscreen content.");
                    mRewardedAd = null;
                    callback.error();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    Log.e(TAG, "Ad failed to show fullscreen content.");
                    mRewardedAd = null;
                    callback.error();
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d(TAG, "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d(TAG, "Ad showed fullscreen content.");
                }
            });
            mRewardedAd.show(mActivity, rewardItem -> {
                // Handle the reward.
                Log.d(TAG, "The user earned the reward.");
                int rewardAmount = rewardItem.getAmount();
                String rewardType = rewardItem.getType();
                callback.call("", 0);
            });
        } else {
            callback.error();
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
    }

    private AppOpenAd mAppOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    public void showOpenAd(InterCallback call) {
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                if (Config.ShowOpenAd) {
                    mAppOpenAd = appOpenAd;
                    mAppOpenAd.show(mActivity);
                    Config.ShowOpenAd = false;
                    call.call();
                }
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                call.call();
            }


        };
        AppOpenAd.load(mActivity, State.m_data.getAds().getAds_info().getAdmob().getOpenAd(), new AdRequest.Builder().build(), AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

    }

}
