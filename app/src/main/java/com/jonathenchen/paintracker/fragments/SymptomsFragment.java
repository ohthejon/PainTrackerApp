package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.rey.material.widget.Slider;

public class SymptomsFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);

        EntryFormUtil.morningPain = (Slider)view.findViewById(R.id.slider_morning_pain);
        EntryFormUtil.middayPain = (Slider)view.findViewById(R.id.slider_midday_pain);
        EntryFormUtil.eveningPain = (Slider)view.findViewById(R.id.slider_evening_pain);
        EntryFormUtil.energy = (Slider)view.findViewById(R.id.slider_energy);
        EntryFormUtil.nausea = (Slider)view.findViewById(R.id.slider_nausea);

        return view;
    }
}