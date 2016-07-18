package com.jonathenchen.paintracker.views;

import android.support.v4.app.FragmentManager;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import android.content.Context;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.listeners.NavBarTabSelectedListener;

/**
 * Created by adhithyan-3592 on 15/07/16.
 */

/**
 * from main activity nav bar is set by calling this class.
 */

public class NavBar {
    BottomNavigationBar bottomNavigationBar;
    Context context;
    FragmentManager supportFragmentManager;
    android.app.FragmentManager fragmentManager;
    

    public NavBar(Context context, BottomNavigationBar bottomNavigationBar, FragmentManager supportFragmentManager, android.app.FragmentManager fragmentManager){
        this.context = context;
        this.bottomNavigationBar = bottomNavigationBar;
        this.supportFragmentManager = supportFragmentManager;
        this.fragmentManager = fragmentManager;
    }

    public void set(){
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.black);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_timeline_black_24dp, "Timeline"))
                .addItem(new BottomNavigationItem(R.drawable.ic_add_white_24dp, "Add"))
                .addItem(new BottomNavigationItem(R.drawable.ic_list_black_24dp, "Details"))
                .addItem(new BottomNavigationItem(R.drawable.ic_email_black_24dp, "Placeholder"))
                .initialise();


        bottomNavigationBar.setTabSelectedListener(new NavBarTabSelectedListener(context, supportFragmentManager, fragmentManager));
    }
}
