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
import android.view.ViewTreeObserver;
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

/*
    this fragment will be called when details list view is needed to be shown on screen.
    this calls detailsadapter twice.
    check code for two calls and related explanation.
 */
public class DetailsFragment extends ListFragment {
    ToastUtil toast;
    View view;
    int height, count = 0;
    String[] rows;
    ArrayAdapter<String> adapter;
    boolean setLayout = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_details, container, false);
        setHasOptionsMenu(true);
        toast = (ToastUtil)new Peacock().getUtility(getActivity(), Utility.TOAST);
        /*
        Check onActivityCreated function comment before reading this.
        this listener will be notified with measured after we append white text to adapter.
        from the height obtained, we again
         */
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = view.getMeasuredHeight();

                if(height > 0 && count == 0){
                    adapter = new DetailsAdapter(getActivity(), rows, height);
                    setListAdapter(adapter);
                    Log.d("entries", height + "");
                    count ++;
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        /*
          we extract symptoms for the desired week (for 7 days) from db
            - calculate avgpain from morning, evening and midday pain
            - energy is pulled directly

          after taking all the above values, calculate weekly avgpain and weekly energy and display.

          for getting desired week the global variable in Detailsutil.weekIncrement variable is used.
          when the activity is called first time - weekIncrement will be one since we need the current week data
          and get the seven days of current week from calendar and related entries of symptoms from db
          using date as query parameter.

          for previous week entries we increment weekIncrement, pull from db repeat above steps and show list.
          similarly for next week entries we decrement weekIncrement and repeat above process.
         */

        if(DetailsUtil.weekIncrement < 0){
            DetailsUtil.weekIncrement = 0;
            toast.showLongToast("You can't go beyond current week.");
        }

        String title = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7 * DetailsUtil.weekIncrement);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
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
        DecimalFormat df = new DecimalFormat(("#.##"));
        df.setRoundingMode(RoundingMode.CEILING);

        rows = new String[9];
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
                double avgPain = (symptoms.morningPain + symptoms.middayPain + symptoms.eveningPain)/3;
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

        /*
        the first call to details adapter will be from here and we pass height as 0.
        this is because only after layout is drawn on screen. we will get height.
        so in this call, we just append the screen with white text which won't be visible.
        after appending the global layout listener will be notified.
         */
        adapter = new DetailsAdapter(getActivity(), rows, 0);
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
        /*
            *action_prev - go to previous week
            *action_next - go to next week
            set the global variable of week increment in details tab based on chosen action.
         */
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
