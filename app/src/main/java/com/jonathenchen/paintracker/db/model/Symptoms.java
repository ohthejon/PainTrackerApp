package com.jonathenchen.paintracker.db.model;

import com.orm.SugarRecord;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class Symptoms extends SugarRecord {
    public int morningPain, middayPain, eveningPain, energy, nausea;

    public Symptoms(){
    }

    public Symptoms(int morningPain, int middayPain, int eveningPain, int energy, int nausea){
        this.morningPain = morningPain;
        this.middayPain = middayPain;
        this.eveningPain = eveningPain;
        this.energy = energy;
        this.nausea = nausea;
    }
}
