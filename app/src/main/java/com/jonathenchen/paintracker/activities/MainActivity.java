package com.jonathenchen.paintracker.activities;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.avtechlabs.peacock.Peacock;
import com.avtechlabs.peacock.enums.Utility;
import com.avtechlabs.peacock.utilities.ToastUtil;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.asynctask.ForecastTask;
import com.jonathenchen.paintracker.db.model.Diet;
import com.jonathenchen.paintracker.db.model.Symptoms;
import com.jonathenchen.paintracker.db.model.YourDay;
import com.jonathenchen.paintracker.listeners.NavBarTabSelectedListener;

public class MainActivity extends AppCompatActivity implements LocationListener {
    BottomNavigationBar bottomNavigationBar;
    FloatingActionButton fab;
    LocationManager locationManager;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        //fab = (FloatingActionButton) findViewById(R.id.fab);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.black);

        setNavBar();
        new ForecastTask(this).execute();
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*Symptoms symptoms = new Symptoms(1,2,3,4,5);
        symptoms.save();

        Diet diet = new Diet("none", "some", "alot", "none", "some");
        diet.save();

        YourDay yourDay = new YourDay(5, 2, 8, "10.00");
        yourDay.save();

        Symptoms symptoms = Symptoms.findById(Symptoms.class, 1);
        Diet diet = Diet.findById(Diet.class, 1);
        YourDay yourDay = YourDay.findById(YourDay.class, 1);


        ToastUtil toastUtil = (ToastUtil)new Peacock().getUtility(this, Utility.TOAST);
        toastUtil.showLongToast(symptoms.morningPain + "");
        toastUtil.showLongToast(yourDay.sleepTime);
        toastUtil.showLongToast(diet.glutenIntake);*/
    }

    public void setNavBar(){
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_timeline_black_24dp, "Timeline"))
                .addItem(new BottomNavigationItem(R.drawable.ic_add_white_24dp, "Add"))
                .addItem(new BottomNavigationItem(R.drawable.ic_list_black_24dp, "Details"))
                .addItem(new BottomNavigationItem(R.drawable.ic_email_black_24dp, "Placeholder"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new NavBarTabSelectedListener());
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
