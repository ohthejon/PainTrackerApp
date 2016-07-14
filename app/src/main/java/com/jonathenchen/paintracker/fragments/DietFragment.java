package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.rey.material.widget.Slider;

public class DietFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        EntryFormUtil.carbIntake = (Slider)view.findViewById(R.id.slider_carb);
        EntryFormUtil.sugarIntake = (Slider)view.findViewById(R.id.slider_sugar);
        EntryFormUtil.glutenIntake = (Slider)view.findViewById(R.id.slider_gluten);
        EntryFormUtil.alcoholIntake = (Slider)view.findViewById(R.id.slider_alcohol);
        EntryFormUtil.diaryIntake = (Slider)view.findViewById(R.id.slider_diary);

        return view;
    }
}