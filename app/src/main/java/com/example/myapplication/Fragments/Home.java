package com.example.myapplication.Fragments;

import static com.example.myapplication.Utils.Utils.SetUpRecipeList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activities.FullRecipePage;
import com.example.myapplication.Activities.RecipeList;
import com.example.myapplication.Adapters.CategoryAdapter;
import com.example.myapplication.Adapters.RecipeAdapter;
import com.example.myapplication.Adapters.SliderAdapter;
import com.example.myapplication.Monetization.Distributor;
import com.example.myapplication.R;
import com.example.myapplication.Tmp.Recipe;
import com.example.myapplication.Tmp.SliderData;
import com.example.myapplication.Utils.State;
import com.example.myapplication.interfaces.InterCallback;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Home extends Fragment {
    RecyclerView recyclerView;
    SliderView sliderView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Distributor.ShowBanner(view.findViewById(R.id.banner));
        recyclerView = view.findViewById(R.id.categories);
        sliderView = view.findViewById(R.id.slider);
        SetUpFeatured();
        SetUpCategories();
        SetUpRecipeList(getActivity(), view.findViewById(R.id.Suggestions), State.m_data.getSuggestions());
        return view;
    }

    private void SetUpFeatured() {
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        for (Recipe item : State.m_data.getFeatured()) {
            sliderDataArrayList.add(new SliderData(item.getCover()));
        }
        SliderAdapter adapter = new SliderAdapter(getActivity(), sliderDataArrayList, () -> {
            Distributor.ShowInter(() -> startActivity(new Intent(getActivity(), FullRecipePage.class)));
        });
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    void SetUpCategories() {
        CategoryAdapter adapter = new CategoryAdapter(State.m_data.getCategories(), getActivity(), id -> {
            Distributor.ShowInter(() -> {
                State.SelectedCategory = State.m_data.getCategories().get(id);
                startActivity(new Intent(getActivity(), RecipeList.class));
            });
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
