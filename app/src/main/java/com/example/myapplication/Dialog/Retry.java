package com.example.myapplication.Dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InterCallback;


public class Retry extends Dialog {

    Context context;
    InterCallback callback;

    public Retry(@NonNull Context context, InterCallback callback) {
        super(context);
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.retry_dialog_layout);
        findViewById(R.id.retry).setOnClickListener(v -> {
            this.dismiss();
            callback.call();
        });

    }

}
