package com.jonathenchen.paintracker.db.model;

import com.orm.SugarRecord;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class YourDay extends SugarRecord {
    public int activityLevel, stressLevel, sleepLength;
    public String sleepTime;

    public YourDay(){
    }

    public YourDay(int activityLevel, int stressLevel, int sleepLength, String sleepTime){
        this.activityLevel = activityLevel;
        this.stressLevel = stressLevel;
        this.sleepLength = sleepLength;
        this.sleepTime = sleepTime;
    }
}
