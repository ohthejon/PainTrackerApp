package com.jonathenchen.paintracker.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.avtechlabs.peacock.Peacock;
import com.avtechlabs.peacock.enums.Utility;
import com.avtechlabs.peacock.utilities.ToastUtil;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.adapters.DetailsAdapter;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.DetailsUtil;
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
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        String title = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7 * DetailsUtil.weekIncrement);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String[] dates = new String[7];
        DateUtil dateUtil = new DateUtil();
        for(int i=0; i<7; i++){

            dates[i] = dateUtil.getDate(cal);

            if(i==0)
                title = dateUtil.getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.DAY_OF_MONTH) + "- ";
            if(i==6)
                title += dateUtil.getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.DAY_OF_MONTH) + "," + cal.get(Calendar.YEAR);

            cal.add(Calendar.DATE, 1);
        }

        getActivity().setTitle(title);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_action_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_prev:
                DetailsUtil.weekIncrement++;
                EntryFormUtil.bottomNavigationBar.selectTab(3);
                break;
            case R.id.action_next:
                DetailsUtil.weekIncrement--;
                EntryFormUtil.bottomNavigationBar.selectTab(3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
