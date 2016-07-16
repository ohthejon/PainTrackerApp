package com.jonathenchen.paintracker.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.avtechlabs.peacock.Peacock;
import com.avtechlabs.peacock.enums.Utility;
import com.avtechlabs.peacock.utilities.ToastUtil;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.adapters.DetailsAdapter;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by adhithyan-3592 on 15/07/16.
 */
public class DetailsFragment extends ListFragment {
    ToastUtil toast;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, -7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String[] dates = new String[7];
        DateUtil dateUtil = new DateUtil();
        for(int i=0; i<7; i++){

            dates[i] = dateUtil.getDate(cal);
            Log.d("adddate", new DateUtil().getDate(cal));
            cal.add(Calendar.DATE, 1);
        }

        DecimalFormat df = new DecimalFormat(("#.#"));
        df.setRoundingMode(RoundingMode.CEILING);

        String[] rows = new String[9];
        String firstRow = "Date,Avg. Pain, Energy";
        rows[0] = firstRow;
        int n = 0;
        double totalAvgPain = 0d;
        double totalEnergy = 0d;
        for(int i=1; i<=7; i++){
            List<Symptoms> symptomsList = Symptoms.find(Symptoms.class, "date = ?", dates[i-1]);
            if(symptomsList.size() <= 0)
                rows[i] = dates[i-1] + ",-,-";
            else{
                Symptoms symptoms = symptomsList.get(0);
                float avgPain = (symptoms.morningPain + symptoms.middayPain + symptoms.eveningPain)/3;
                totalAvgPain += avgPain;
                totalEnergy += symptoms.energy;
                rows[i] = dates[i-1] + "," + df.format(avgPain) + "," + df.format(symptoms.energy);
                n++;
            }
        }
        totalAvgPain = (totalAvgPain == 0d) ? 0 : totalAvgPain/n;
        totalEnergy = (totalEnergy == 0d) ? 0 : totalEnergy/n;
        String lastRow = "Weekly Avg," + df.format(totalAvgPain) + "," + df.format(totalEnergy);
        rows[8] = lastRow;

        ArrayAdapter<String> adapter = new DetailsAdapter(getActivity(), rows);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        if(position >= 1 && position <= 7){
            String touchedDate = l.getAdapter().getItem(position).toString().split(",")[0];
            EntryFormUtil.date = touchedDate;
            EntryFormUtil.show();
        }

    }
}
