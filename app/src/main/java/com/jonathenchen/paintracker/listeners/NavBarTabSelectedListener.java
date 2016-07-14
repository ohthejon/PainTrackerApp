package com.jonathenchen.paintracker.listeners;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jonathenchen.paintracker.activities.DetailsActivity;
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
                break;
            case 3:
                Intent intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
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
