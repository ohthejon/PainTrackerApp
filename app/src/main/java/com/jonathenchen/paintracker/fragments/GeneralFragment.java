package com.jonathenchen.paintracker.fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.db.model.YourDay;
import com.jonathenchen.paintracker.listeners.SliderListener;
import com.jonathenchen.paintracker.listeners.TimeListener;
import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.rey.material.widget.Slider;

import java.util.Calendar;
import java.util.List;

/*
show general entry form
 */
public class GeneralFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        EntryFormUtil.activityLevel = (Slider)view.findViewById(R.id.slider_activity_level);
        EntryFormUtil.stressLevel = (Slider)view.findViewById(R.id.slider_stress_level);
        EntryFormUtil.sleepLength = (Slider)view.findViewById(R.id.slider_sleep_length);

        final TextView timer = (TextView)view.findViewById(R.id.textview_timer);

        timer.setOnClickListener(new TimeListener(timer, getActivity()));

        ImageButton imageButton = (ImageButton)view.findViewById(R.id.image_button_time);
        imageButton.setOnClickListener(new TimeListener(timer, getActivity()));

        //TextView actChange = (TextView)view.findViewById(R.id.textview_sact);

        List<YourDay> generals = YourDay.find(YourDay.class, "date = ?", EntryFormUtil.date);
        if(generals.size() > 0){
            YourDay general = generals.get(0);
            float stress = general.stressLevel;
            float activity = general.activityLevel;
            float sleepL = general.sleepLength;
            String sleepTime = general.sleepTime;

            EntryFormUtil.stressLevel.setValue(stress, false);
            EntryFormUtil.activityLevel.setValue(activity, false);
            EntryFormUtil.sleepLength.setValue(sleepL, false);

            if(sleepTime.length() > 0)
                timer.setText(sleepTime);

            EntryFormUtil.genralEntry = true;
        }
        return view;
    }
}
