package com.jonathenchen.paintracker.listeners;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jonathenchen.paintracker.utilites.EntryFormUtil;

import java.util.Calendar;

/**
 * Created by adhithyan-3592 on 15/07/16.
 */

public class TimeListener implements View.OnClickListener {
    final TextView timer;
    Activity activity;

    public TimeListener(TextView timer, Activity activity){
        this.timer = timer;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String meridian = selectedHour >= 12 ? "PM" : "AM";
                timer.setText( selectedHour + ":" + selectedMinute + " " + meridian);
                EntryFormUtil.sleepT = timer.getText().toString();
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Sleep Time");
        mTimePicker.show();
    }
}
