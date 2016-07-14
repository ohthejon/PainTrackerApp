package com.jonathenchen.paintracker.listeners;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jonathenchen.paintracker.fragments.FragmentDialog;

/**
 * Created by adhithyan-3592 on 12/07/16.
 */
public class NavBarTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {
    Context context;
    FragmentManager fragmentManager;

    public NavBarTabSelectedListener(Context context, FragmentManager fragmentManager){
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onTabSelected(int position) {
        switch(position){
            case 2:
                FragmentDialog overlay = new FragmentDialog();
                overlay.show(fragmentManager, "Form");


                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("How was your day?")
                        .setView(overlay.getView());

                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);

                builder.show();*/
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
