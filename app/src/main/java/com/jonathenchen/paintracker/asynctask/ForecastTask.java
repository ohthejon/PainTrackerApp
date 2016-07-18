package com.jonathenchen.paintracker.asynctask;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.avtechlabs.peacock.Peacock;
import com.avtechlabs.peacock.enums.Utility;
import com.avtechlabs.peacock.utilities.AlertUtil;
import com.avtechlabs.peacock.utilities.ToastUtil;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

/*
    after we get location in main activity, this async task will be called.
    It fetches weather json for the current location and  sets the
    * temperature
    * pressure
    * humidity
    * precipitation
    to global variables found in EntryformUtil.java
 */
public class ForecastTask extends AsyncTask<Void, Void, JSONObject> {
    Context context;
    ToastUtil toast;
    AlertUtil alert;
    String location = "/";
    String url = "";
    OkHttpClient client;
    boolean internet;

    public ForecastTask(Context context){
        this.context = context;
        toast = (ToastUtil)new Peacock().getUtility(this.context, Utility.TOAST);
        alert = (AlertUtil)new Peacock().getUtility(this.context, Utility.ALERT);
        location = location + EntryFormUtil.location;
        url = context.getString(R.string.forecast_url) + context.getString(R.string.forecast_api_key) + location;
        client = new OkHttpClient();
        internet = internetConnected();
    }


    @Override
    protected JSONObject doInBackground(Void... params) {
        if(!internet)
            return null;

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;

        try{
            response = client.newCall(request).execute();

            if(!response.isSuccessful())
                return null;

            return new JSONObject(response.body().string());
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(JSONObject forecast){
        if(forecast != null){
            StringBuilder weather = new StringBuilder();
            try{
                forecast = forecast.getJSONObject("currently");
                String temperature = "temperature", pressure = "pressure", humidity = "humidity", precipitation = "precipIntensity";

                EntryFormUtil.temperature = forecast.get(temperature) + "";
                EntryFormUtil.pressure = forecast.get(pressure) + "";
                EntryFormUtil.humidity = (int)(Double.parseDouble(forecast.get(humidity) + "") * 100) + "";
                EntryFormUtil.precipitation = forecast.get(precipitation) + "";

            } catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public boolean internetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

            if(networkInfos != null)
                for(int i=0; i<networkInfos.length; i++)
                    if(networkInfos[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
        }
        return false;
    }
}
