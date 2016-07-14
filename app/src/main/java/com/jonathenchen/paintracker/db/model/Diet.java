package com.jonathenchen.paintracker.db.model;

import android.util.Log;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class Diet extends SugarRecord {
    public String glutenIntake, sugarIntake, carbIntake, alcoholIntake, diaryIntake, date;

    public Diet(){
    }

    public Diet(String gluten, String sugar, String carb, String alcohol, String diary, String date){
        glutenIntake = gluten;
        sugarIntake = sugar;
        carbIntake = carb;
        alcoholIntake = alcohol;
        diaryIntake = diary;
        this.date = date;
        log();
    }

    public void log(){
        JSONObject json = new JSONObject();

        try{
            json.put("gluten", glutenIntake);
            json.put("sugar", sugarIntake);
            json.put("carb", carbIntake);
            json.put("alcohol", alcoholIntake);
            json.put("diary", diaryIntake);
        } catch (JSONException ex){
            ex.printStackTrace();
        }

        Log.d("entry", json.toString());
    }
}
