package com.example.myapplication.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.myapplication.R;

public class ImageUtils {

    private static final String[] imageUrls = {
            "https://img.freepik.com/free-photo/top-view-table-full-delicious-food-composition_23-2149141353.jpg",
            "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe",
            "https://content3.jdmagicbox.com/comp/def_content/fast-food/22-fast-food-9-20wsq.jpg"
    };

    private static int currentIndex = 0;

    public static void startImageRotation(Context context, ImageView imageView) {
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadImage(context, imageUrls[currentIndex], imageView, new ImageLoadListener() {
                    @Override
                    public void onLoadFailed() {
                    }

                    @Override
                    public void onLoadComplete() {
                        Log.d("IMAGES", "onLoadComplete: ");

                    }
                });
                currentIndex = (currentIndex + 1) % imageUrls.length;
                handler.postDelayed(this, (int)(1.2 * 1000));
            }
        };
        handler.postDelayed(runnable,  (int)(0 * 1000));
    }

    public static void stopImageRotation() {

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.removeCallbacksAndMessages(null);
    }
    public static void loadImage(Context context, String imageUrl, ImageView imageView, final ImageLoadListener listener) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        DrawableTransitionOptions transitionOptions = DrawableTransitionOptions.withCrossFade();

        Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .transition(transitionOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onLoadFailed();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onLoadComplete();
                        }
                        return false;
                    }
                })
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        animateImageChange(imageView, resource);

                        if (listener != null) {
                            listener.onLoadComplete();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Optional: handle when the resource is cleared
                    }
                });
    }

    private static void animateImageChange(ImageView imageView, Drawable newImage) {
        // Slide-out animation for the old image
        imageView.animate()
                .translationX(imageView.getWidth())
                .alpha(0)
                .setDuration(500)
                .withEndAction(() -> {
                    // Set the new image
                    imageView.setImageDrawable(newImage);
                    imageView.setAlpha(1);

                    // Slide-in animation for the new image
                    imageView.setTranslationX(-imageView.getWidth());
                    imageView.animate()
                            .translationX(0)
                            .setDuration(500)
                            .start();
                })
                .start();
    }
    public interface ImageLoadListener {
        void onLoadFailed();
        void onLoadComplete();
    }
}
