package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.asynctask.ForecastTask;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;

/**
 * Created by adhithyan-3592 on 15/07/16.
 */

public class WeatherFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        TextView temperature = (TextView)view.findViewById(R.id.textview_temp);
        TextView pressure = (TextView)view.findViewById(R.id.textview_pressure);
        TextView humdity = (TextView)view.findViewById(R.id.textview_humidity);
        TextView precipitation = (TextView)view.findViewById(R.id.textview_precipitation);

        if(EntryFormUtil.temperature.equals("0")){
            temperature.setText("Connect to internet to pull macrodata");
        }else {
            temperature.setText("Weather: " + EntryFormUtil.temperature + " F");
            pressure.setText("Pressure: " + EntryFormUtil.pressure + " in");
            humdity.setText("Humidity: " + EntryFormUtil.humidity + "%");
            precipitation.setText("Precip: " + EntryFormUtil.precipitation + "in");
        }


        return view;
    }
}
