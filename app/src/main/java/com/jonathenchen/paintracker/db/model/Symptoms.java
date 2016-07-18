package com.jonathenchen.paintracker.db.model;

import android.util.Log;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

/*
    we use sugar orm for db manipulation.
    this class is used to create symptoms table and related db manipulation.
 */
public class Symptoms extends SugarRecord {
    public int morningPain, middayPain, eveningPain, energy, nausea;
    String date;


    public Symptoms(){
    }

    public Symptoms(int morningPain, int middayPain, int eveningPain, int energy, int nausea, String date){
        this.morningPain = morningPain;
        this.middayPain = middayPain;
        this.eveningPain = eveningPain;
        this.energy = energy;
        this.nausea = nausea;
        this.date = date;
        //log();
    }

    public void log(){
        JSONObject json = new JSONObject();

        try{
            json.put("morning", morningPain);
            json.put("midday", middayPain);
            json.put("evening", eveningPain);
            json.put("energy", energy);
            json.put("nausea", nausea);
        } catch (JSONException ex){
            ex.printStackTrace();
        }

        Log.d("entry", json.toString());
    }
}
