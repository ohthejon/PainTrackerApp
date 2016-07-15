package com.jonathenchen.paintracker.listeners;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.fragments.DetailsFragment;
import com.jonathenchen.paintracker.fragments.FragmentDialog;

/**
 * Created by adhithyan-3592 on 12/07/16.
 */
public class NavBarTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {
    Context context;
    FragmentManager supportfragmentManager;
    android.app.FragmentManager fragmentManager;

    public NavBarTabSelectedListener(Context context, FragmentManager supportfragmentManager, android.app.FragmentManager fragmentManager){
        this.context = context;
        this.supportfragmentManager = supportfragmentManager;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onTabSelected(int position) {
        switch(position){
            case 2:
                FragmentDialog overlay = new FragmentDialog();
                overlay.show(supportfragmentManager, "Form");
                break;

            case 3:
                DetailsFragment detailsFragment = new DetailsFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, detailsFragment, "Details").commit();
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
