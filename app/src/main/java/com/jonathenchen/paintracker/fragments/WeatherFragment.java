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

/*
show weather data.
after forecast task get's executed, weather info will be set to related varibles in EntryformUtil.
if variables are not set, we assume some error occured while pulling weather data and
show related message.
 */
public class WeatherFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        TextView loc = (TextView)view.findViewById(R.id.textview_loc);
        TextView temperature = (TextView)view.findViewById(R.id.textview_temp);
        TextView pressure = (TextView)view.findViewById(R.id.textview_pressure);
        TextView humdity = (TextView)view.findViewById(R.id.textview_humidity);
        TextView precipitation = (TextView)view.findViewById(R.id.textview_precipitation);

        /*if(EntryFormUtil.temperature.equals("0")){
            temperature.setText("Connect to internet to pull macrodata");
        }else {*/
            loc.setText(EntryFormUtil.currentLocation);
            temperature.setText(EntryFormUtil.temperature + " F");
            pressure.setText(EntryFormUtil.pressure + " in");
            humdity.setText(EntryFormUtil.humidity + "%");
            precipitation.setText(EntryFormUtil.precipitation + " in");
        //}


        return view;
    }
}
