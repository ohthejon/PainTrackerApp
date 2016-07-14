package com.jonathenchen.paintracker.db.model;

import android.util.Log;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class YourDay extends SugarRecord {
    public int activityLevel, stressLevel, sleepLength;
    public String sleepTime;
    public String date;

    public YourDay(){
    }

    public YourDay(int activityLevel, int stressLevel, int sleepLength, String sleepTime, String date){
        this.activityLevel = activityLevel;
        this.stressLevel = stressLevel;
        this.sleepLength = sleepLength;
        this.sleepTime = sleepTime;
        this.date = date;
        log();
    }

    public void log(){
        JSONObject json = new JSONObject();

        try {
            json.put("activity", activityLevel);
            json.put("stress", stressLevel);
            json.put("sleepL", sleepLength);
            json.put("sleepT", sleepTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("entry", json.toString());
    }
}
