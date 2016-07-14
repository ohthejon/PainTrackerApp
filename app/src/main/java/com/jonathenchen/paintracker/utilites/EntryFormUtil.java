package com.jonathenchen.paintracker.utilites;

import com.jonathenchen.paintracker.db.model.Diet;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.db.model.YourDay;
import com.rey.material.widget.Slider;

/**
 * Created by adhithyan-3592 on 14/07/16.
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
    private static String sleepT;
    private static int morningP, middayP, eveningP, energyVal, nauseaVal;
    private static int gluten, sugar, carb, diary, alcohol;
    private static String date = new DateUtil().getToday();
    /*private static Symptoms symptoms;
    private static YourDay yourDay;
    private static Diet diet;*/

    public static YourDay getGeneralFragmentValues(){
        activity = (activityLevel != null) ? (int)activityLevel.getExactValue() : 0;
        stress = (activityLevel != null) ? (int)stressLevel.getExactValue() : 0;
        sleepL = (activityLevel != null) ? (int)sleepLength.getExactValue() : 0;
        //activity = (activityLevel != null) ? (int)activityLevel.getExactValue() : 0;

        return new YourDay(activity, stress, sleepL, "10:00 PM", date);
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
    public static void commit(){
        getGeneralFragmentValues().save();
        getDietFragmentValues().save();
        getSymptomsFragmentValues().save();
    }
}
