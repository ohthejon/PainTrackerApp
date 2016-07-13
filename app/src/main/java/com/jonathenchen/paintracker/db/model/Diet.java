package com.jonathenchen.paintracker.db.model;

import com.orm.SugarRecord;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class Diet extends SugarRecord {
    public String glutenIntake, sugarIntake, carbIntake, alcoholIntake, diaryIntake;

    public Diet(){
    }

    public Diet(String gluten, String sugar, String carb, String alcohol, String diary){
        glutenIntake = gluten;
        sugarIntake = sugar;
        carbIntake = carb;
        alcoholIntake = alcohol;
        diaryIntake = diary;
    }
}
