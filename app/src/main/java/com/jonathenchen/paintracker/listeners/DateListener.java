package com.jonathenchen.paintracker.listeners;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.DetailsUtil;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;

import java.util.Calendar;

/**
 * Created by adhithyan-3592 on 15/07/16.
 */

public class DateListener implements View.OnClickListener {
    final TextView date;
    Activity activity;

    public DateListener(TextView date, Activity activity){
        this.date = date;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        final int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newCal = Calendar.getInstance();
                newCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                newCal.set(Calendar.YEAR, year);
                newCal.set(Calendar.MONTH, monthOfYear);

                int chosenDayOfYear = newCal.get(Calendar.DAY_OF_YEAR);
                int diff = (dayOfYear - chosenDayOfYear) / 7;
                diff = diff + 1;
                DetailsUtil.weekIncrement = diff;


                String dateChosen = new DateUtil().getDate(newCal);
                date.setText(dateChosen);
                EntryFormUtil.date = dateChosen;

            }
        }, year, month, day);
        datePickerDialog.setTitle("Choose date:");
        datePickerDialog.show();



    }
}
