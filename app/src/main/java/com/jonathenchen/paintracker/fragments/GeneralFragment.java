package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.rey.material.widget.Slider;

public class GeneralFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        EntryFormUtil.activityLevel = (Slider)view.findViewById(R.id.slider_activity_level);
        EntryFormUtil.stressLevel = (Slider)view.findViewById(R.id.slider_stress_level);
        EntryFormUtil.sleepLength = (Slider)view.findViewById(R.id.slider_sleep_length);

        return view;
    }
}
