package com.example.myapplication.Fragments;

import static com.example.myapplication.Utils.Utils.SetUpRecipeList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.Utils.State;

public class Discover extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        SetUpRecipeList(getActivity(), view.findViewById(R.id.recycler), State.m_data.getSuggestions());
        return (view);
    }
}
