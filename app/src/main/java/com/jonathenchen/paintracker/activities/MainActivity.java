package com.jonathenchen.paintracker.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/*
    implementing location listener class, so that we can handle get location co-ordinates
    and get weather data for that location
 */
public class MainActivity extends AppCompatActivity implements LocationListener {
    BottomNavigationBar bottomNavigationBar;
    FloatingActionButton fab;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            binding navigation bar with resource file and adding on click listener to it.
         */
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        new NavBar(this, bottomNavigationBar, getSupportFragmentManager(), getFragmentManager()).set();

        /*
            saving support fragment manager to a global variable, since app mainly uses fragments
            to achieve required functionality.
         */
        EntryFormUtil.supportFragmentManager = getSupportFragmentManager();
        EntryFormUtil.bottomNavigationBar = bottomNavigationBar;
         //open details tab at launch

        /*
            for android os from marshmallow, certain permissions are to be granted runtime.
            here we are checking if location permission is granted and turning on gps to get
            user co-ordinates to pull weather data
         */
        checkAndAskPermission();
        checkAndEnableGPS();

    }


    public void setNavBar() {

    }

    /*
        if location permission is granted and gps is turned on, then this function will be notified.
        Then extract latitude and longitude from the current location and get weather for the location using
        forecast task which is an async task.
     */
    @Override
    public void onLocationChanged(Location location) {
        double lon = location.getLongitude();
        double lat = location.getLatitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try{
            List<Address> locations = geocoder.getFromLocation(lat, lon, 1);
            if(locations.size() > 0){
                Address address = locations.get(0);
                EntryFormUtil.currentLocation = address.getLocality();
            }else{
                EntryFormUtil.location = lat + "," + lon;
            }
        }catch(Exception ex){

        }

        Log.d("loca", lat + "," + lon);
        if (lat != 0d && lon != 0d) {
            EntryFormUtil.location = lat + "," + lon;
            new ForecastTask(this).execute();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(this);
            }
        }
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

    /*
        we need location permission which should be granted by user for os >= marshmallow.
        The following function checks and asks location permission to be granted if not is granted.
     */
    public void checkAndAskPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionList = new LinkedList<String>();

        for(String permission: permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                permissionList.add(permission);
        }

        if(permissionList.size() > 0)
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 100);
    }

    /*
        after checking for location permission, we must ask user to turn on gps to get co-ordinates.
     */
    public void checkAndEnableGPS(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(network){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 1, this);
                return;
            }

            if(gps){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, this);
                return;
            }

            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("Turn on GPS")
                    .setMessage("GPS is required to pull weather data for the current location.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setCancelable(false);
            alert.show();
        }else
            checkAndAskPermission();

    }
}
