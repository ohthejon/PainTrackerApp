package com.jonathenchen.paintracker.utilites;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jonathenchen.paintracker.db.model.Diet;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.db.model.YourDay;
import com.jonathenchen.paintracker.fragments.FragmentDialog;
import com.rey.material.widget.Slider;

/**
 * Created by adhithyan-3592 on 14/07/16.
 */


/*
this is the main class based on which entry form details are populated.
all the variables are static, since we need them for the entire app cycle.
 */
public class EntryFormUtil {

    //General fragment
    public static Slider activityLevel = null;
    public static Slider stressLevel = null;
    public static Slider sleepLength = null;
    //public static Slider activityLevel =  null;

    //Symptoms fragment
    public static Slider morningPain = null;
    public static Slider middayPain = null;
    public static Slider eveningPain = null;
    public static Slider energy = null;
    public static Slider nausea = null;

    //Diet Fragment
    public static Slider glutenIntake = null;
    public static Slider sugarIntake = null;
    public static Slider carbIntake = null;
    public static Slider diaryIntake = null;
    public static Slider alcoholIntake = null;

    private static int activity, stress, sleepL;
    public static String sleepT = "";
    private static int morningP, middayP, eveningP, energyVal, nauseaVal;
    private static int gluten, sugar, carb, diary, alcohol;
    public static String date = new DateUtil().getToday();

    public static boolean genralEntry = false;
    public static boolean symptomsEntry = false;
    public static boolean dietEntry = false;

    public static String currentLocation = "";
    public static String temperature = "0";
    public static String pressure = "0";
    public static String humidity = "0";
    public static String precipitation = "0";

    public static FragmentManager supportFragmentManager = null;
    public static BottomNavigationBar bottomNavigationBar = null;

    public static String location = "";
    /*private static Symptoms symptoms;
    private static YourDay yourDay;
    private static Diet diet;*/

    public static YourDay getGeneralFragmentValues(){
        activity = (activityLevel != null) ? (int)activityLevel.getExactValue() : 0;
        stress = (activityLevel != null) ? (int)stressLevel.getExactValue() : 0;
        sleepL = (activityLevel != null) ? (int)sleepLength.getExactValue() : 0;
        //activity = (activityLevel != null) ? (int)activityLevel.getExactValue() : 0;

        return new YourDay(activity, stress, sleepL, sleepT, date);
    }

    public static Symptoms getSymptomsFragmentValues(){
        morningP = (morningPain != null) ? (int)morningPain.getExactValue() : 0;
        middayP = (middayPain != null) ? (int)middayPain.getExactValue() : 0;
        eveningP = (eveningPain != null) ? (int)eveningPain.getExactValue() : 0;
        energyVal = (energy != null) ? (int)energy.getExactValue() : 0;
        nauseaVal = (nausea != null) ? (int)nausea.getExactValue() : 0;

        return new Symptoms(morningP, middayP, eveningP, energyVal, nauseaVal, date);
    }

    public static Diet getDietFragmentValues(){
        gluten = (glutenIntake != null) ? (int)glutenIntake.getExactValue() : 0;
        sugar = (sugarIntake != null) ? (int)sugarIntake.getExactValue() : 0;
        carb = (carbIntake != null) ? (int)carbIntake.getExactValue() : 0;
        alcohol = (alcoholIntake != null) ? (int)alcoholIntake.getExactValue() : 0;
        diary = (diaryIntake != null) ? (int)diaryIntake.getExactValue() : 0;

        return new Diet(getIntakeLevel(gluten), getIntakeLevel(sugar), getIntakeLevel(carb), getIntakeLevel(alcohol), getIntakeLevel(diary), date);
    }

    public static String getIntakeLevel(int val){
        if(val < 3)
            return "None";
        else if(val >= 3 && val < 7)
            return "Some";
        else
            return "A Lot";
    }

    public static float getIntakeVal(String intake){
        if(intake.equals("None"))
            return 0;
        else if(intake.equals("Some"))
            return 5;
        else
            return 10;
    }

    /*
    when user click ok in entry form extract all tab values.
    check if a entry for a date is present. if present just update the values.
    else add new entry;
     */
    public static void commit(){

        //entry for a chosen date is not present. Then save to db.
        if(YourDay.find(YourDay.class, "date = ?", date).size() == 0){
            getGeneralFragmentValues().save();
            getDietFragmentValues().save();
            getSymptomsFragmentValues().save();
            Log.d("entries", "save");
        }else{
                /*
                    Entry for a date is present in db.
                    Then we need to update.
                    We use sugar orm, and it has no inbuilt support for inplace updates.
                    So we use a hack:
                        -we remove the previous entry for the current date and
                        -insert with new entry for the current date.
                 */
                YourDay.deleteAll(YourDay.class, "date = ?", date);
                Diet.deleteAll(Diet.class, "date = ?", date);
                Symptoms.deleteAll(Symptoms.class, "date = ?", date);

                getGeneralFragmentValues().save();
                getDietFragmentValues().save();
                getSymptomsFragmentValues().save();
                Log.d("entries", "up");
        }

    }

    public static void show(){
        FragmentDialog overlay = new FragmentDialog();
        overlay.show(EntryFormUtil.supportFragmentManager, "entry" + Math.random());
    }
}
