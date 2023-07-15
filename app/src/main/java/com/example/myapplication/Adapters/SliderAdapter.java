package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.SliderData;
import com.example.myapplication.Utils.State;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    private final List<SliderData> mSliderItems;
    OnSliderClick onSliderClick;

    public SliderAdapter(Context context, ArrayList<SliderData> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.onSliderClick = null;
    }

    public SliderAdapter(Context context, ArrayList<SliderData> sliderDataArrayList, OnSliderClick onSliderClick) {
        this.mSliderItems = sliderDataArrayList;
        this.onSliderClick = onSliderClick;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
        final SliderData sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.itemView).load(sliderItem.getImgUrl()).fitCenter().into(viewHolder.imageViewBackground);
        viewHolder.imageViewBackground.setOnClickListener(view -> {
            if (onSliderClick != null) {
                State.SelectedRecipe = State.m_data.getFeatured().get(position);
                onSliderClick.Click();
            }
        });
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    public class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }

    public interface OnSliderClick {
        void Click();
    }
}
