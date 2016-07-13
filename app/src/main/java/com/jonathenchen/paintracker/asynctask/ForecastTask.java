package com.jonathenchen.paintracker.asynctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.avtechlabs.peacock.Peacock;
import com.avtechlabs.peacock.enums.Utility;
import com.avtechlabs.peacock.utilities.AlertUtil;
import com.avtechlabs.peacock.utilities.ToastUtil;
import com.jonathenchen.paintracker.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class ForecastTask extends AsyncTask<Void, Void, JSONObject> {
    Context context;
    ToastUtil toast;
    AlertUtil alert;
    String location = "/10.6573,77.0107";
    String url = "";
    MaterialDialog progressDialog;
    OkHttpClient client;

    public ForecastTask(Context context){
        this.context = context;
        toast = (ToastUtil)new Peacock().getUtility(this.context, Utility.TOAST);
        alert = (AlertUtil)new Peacock().getUtility(this.context, Utility.ALERT);
        url = context.getString(R.string.forecast_url) + context.getString(R.string.forecast_api_key) + location;
        client = new OkHttpClient();
    }

    @Override
    protected void onPreExecute(){
        progressDialog = new MaterialDialog.Builder((Activity)context)
                .title("Please wait")
                .content("Getting current forecast ...")
                .cancelable(false)
                .progress(true, 0)
                .build();
        progressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
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
        progressDialog.dismiss();

        if(forecast != null){
            StringBuilder weather = new StringBuilder();
            try{
                forecast = forecast.getJSONObject("currently");
                String temperature = "temperature", pressure = "pressure", humidity = "humidity", precipitation = "precipIntensity";

                weather.append(forecast.get(temperature));
                weather.append(",");
                weather.append(forecast.get(pressure));
                weather.append(",");
                weather.append(forecast.get(humidity));
                weather.append(",");
                weather.append(forecast.get(precipitation));

            } catch (Exception ex){
                ex.printStackTrace();
            }

            toast.showLongToast(weather.toString());
        }else{
            toast.showLongToast("unable to get forecast.");
        }

    }
}
