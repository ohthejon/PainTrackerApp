package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.db.model.Diet;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.rey.material.widget.Slider;

import java.util.List;

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

        List<Symptoms> symptomsList = Symptoms.find(Symptoms.class, "date = ?", new DateUtil().getToday());
        if(symptomsList.size() > 0){
            Symptoms symptoms = symptomsList.get(0);
            float morning = symptoms.morningPain;
            float midday = symptoms.middayPain;
            float evening = symptoms.eveningPain;
            float energy = symptoms.eveningPain;
            float nausea = symptoms.nausea;

            EntryFormUtil.morningPain.setValue(morning, false);
            EntryFormUtil.middayPain.setValue(midday, false);
            EntryFormUtil.eveningPain.setValue(evening, false);
            EntryFormUtil.energy.setValue(energy, false);
            EntryFormUtil.nausea.setValue(nausea, false);
        }

        return view;
    }
}