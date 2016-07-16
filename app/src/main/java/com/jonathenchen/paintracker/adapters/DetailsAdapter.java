package com.jonathenchen.paintracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jonathenchen.paintracker.R;

/**
 * Created by adhithyan-3592 on 14/07/16.
 */

public class DetailsAdapter extends ArrayAdapter<String>{
    Context context;
    String[] values;
    int height;

    public DetailsAdapter(Context context, String[] values, int height) {
        super(context, -1, values);

        this.context = context;
        this.values = values;
        this.height = height == 0 ? height : height / 10;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.details_list, parent, false);
        if(height > 0){
            rowView.getLayoutParams().height = height;
            Log.d("entries", height + "");
        }


        TextView date = (TextView)rowView.findViewById(R.id.textview_list_date);
        TextView avgPain = (TextView)rowView.findViewById(R.id.textview_list_avgpain);
        TextView energy = (TextView)rowView.findViewById(R.id.textview_list_energy);

        String[] cols = values[position].split(",");

        if(height == 0){
            date.setTextColor(context.getResources().getColor(R.color.white));
            avgPain.setTextColor(context.getResources().getColor(R.color.white));
            energy.setTextColor(context.getResources().getColor(R.color.white));
        }

        date.setText(cols[0]);
        avgPain.setText(cols[1]);
        energy.setText(cols[2]);

        if((cols[0].equals("Date") || cols[0].equals("Weekly Avg")) && height > 0){
            rowView.setBackground(context.getResources().getDrawable(R.color.grey));
        }

        return rowView;
    }
}
