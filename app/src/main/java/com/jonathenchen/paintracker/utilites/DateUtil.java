package com.jonathenchen.paintracker.utilites;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by adhithyan-3592 on 14/07/16.
 */

/*
this is just a helper class to handle date related taske
 */
public class DateUtil {

    public String[] days = {"", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
    public String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public String getMonth(int i){
        return months[i];
    }

    /*
        we need to show date in format MM/DD DAY_NAME in entry form.
        this function is used to do the same but return todays date in above format.
     */
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

    /*
       we need to show date in format MM/DD DAY_NAME in entry form.
       this function is used to do the same but return todays date for the argument calendar object..
    */
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
