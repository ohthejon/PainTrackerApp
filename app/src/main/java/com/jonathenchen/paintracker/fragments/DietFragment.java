package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.db.model.Diet;
import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.rey.material.widget.Slider;

import java.util.List;

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

        List<Diet> dietList = Diet.find(Diet.class, "date = ?", new DateUtil().getToday());
        if(dietList.size() > 0){
            Diet diet = dietList.get(0);
            float carb = EntryFormUtil.getIntakeVal(diet.carbIntake);
            float sugar = EntryFormUtil.getIntakeVal(diet.sugarIntake);
            float gluten = EntryFormUtil.getIntakeVal(diet.glutenIntake);
            float alcohol = EntryFormUtil.getIntakeVal(diet.alcoholIntake);
            float diary = EntryFormUtil.getIntakeVal(diet.diaryIntake);

            EntryFormUtil.carbIntake.setValue(carb, false);
            EntryFormUtil.sugarIntake.setValue(sugar, false);
            EntryFormUtil.glutenIntake.setValue(gluten, false);
            EntryFormUtil.alcoholIntake.setValue(alcohol, false);
            EntryFormUtil.diaryIntake.setValue(diary, false);

            EntryFormUtil.dietEntry = true;
        }

        return view;
    }
}