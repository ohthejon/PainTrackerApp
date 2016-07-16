package com.jonathenchen.paintracker.activities;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.adapters.DetailsAdapter;
import com.jonathenchen.paintracker.asynctask.ForecastTask;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;
import com.jonathenchen.paintracker.views.NavBar;

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

        new ForecastTask(this).execute();

        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        new NavBar(this, bottomNavigationBar, getSupportFragmentManager(), getFragmentManager()).set();

        EntryFormUtil.supportFragmentManager = getSupportFragmentManager();
        EntryFormUtil.bottomNavigationBar = bottomNavigationBar;


    }


    public void setNavBar(){

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
