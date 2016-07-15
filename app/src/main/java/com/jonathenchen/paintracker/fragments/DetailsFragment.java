package com.jonathenchen.paintracker.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.adapters.DetailsAdapter;

/**
 * Created by adhithyan-3592 on 15/07/16.
 */
public class DetailsFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[]{
                "Date,Avg. Pain, Energy", "6/27  MONDAY,3.4,3.2", "6/27  TUESDAY,3.4,3.2", "6/28  WEDNESDAY,3.4,3.2", "6/29  THURSDAY,3.4,3.2",
                "6/30  FRIDAY,3.4,3.2", "7/01  SATURDAY,3.4,3.2", "7/02  SUNDAY,3.4,3.2", "Weekly Avg,4.1,3.4"
        };
        ArrayAdapter<String> adapter = new DetailsAdapter(getActivity(), values);
        setListAdapter(adapter);
    }
}
