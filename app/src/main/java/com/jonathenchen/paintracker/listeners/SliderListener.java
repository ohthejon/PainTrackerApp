package com.jonathenchen.paintracker.listeners;

import android.widget.TextView;

import com.rey.material.widget.Slider;

import java.awt.font.TextAttribute;

/**
 * Created by adhithyan-3592 on 21/07/16.
 */
public class SliderListener implements Slider.OnPositionChangeListener {
    TextView textView;

    public SliderListener(TextView textView){
        this.textView = textView;
    }

    @Override
    public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
        textView.setText(newPos + "");
    }
}
