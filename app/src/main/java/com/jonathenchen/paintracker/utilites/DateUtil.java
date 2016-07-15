package com.jonathenchen.paintracker.utilites;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by adhithyan-3592 on 14/07/16.
 */

public class DateUtil {

    public String[] days = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};

    public String getToday(){
        StringBuilder date = new StringBuilder();

        Calendar cal = Calendar.getInstance();

        date.append(cal.get(Calendar.MONTH) + 1);
        date.append("/");
        date.append(cal.get(Calendar.DATE));
        date.append(" ");
        date.append(days[cal.get(Calendar.DAY_OF_WEEK)]);

        return date.toString();
    }

    public String getDate(Calendar cal){
        StringBuilder date = new StringBuilder();

        date.append(cal.get(Calendar.MONTH) + 1);
        date.append("/");
        date.append(cal.get(Calendar.DATE));
        date.append(" ");
        date.append(days[cal.get(Calendar.DAY_OF_WEEK)]);

        return date.toString();
    }
}
