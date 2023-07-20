package com.example.myapplication.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.Utils.Config;
import com.example.myapplication.Utils.State;
import com.squareup.picasso.Picasso;

public class UpdateDialog extends Dialog {

    Context context;

    public UpdateDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_dialog_layout);
        ((TextView) findViewById(R.id.title)).setText(State.m_data.getAds().getSettings().getTitle());
        ((TextView) findViewById(R.id.message)).setText(State.m_data.getAds().getSettings().getMessage());

        Picasso.get().load(State.m_data.getAds().getSettings().getImg()).into((ImageView) findViewById(R.id.img));

        findViewById(R.id.downloadApp).setOnClickListener(v -> {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + State.m_data.getAds().getSettings().getUpdatePackageName())));
            } catch (android.content.ActivityNotFoundException e) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + State.m_data.getAds().getSettings().getUpdatePackageName())));
            }

        });
    }

}
