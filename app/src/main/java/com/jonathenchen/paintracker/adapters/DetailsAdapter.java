package com.jonathenchen.paintracker.adapters;

import android.content.Context;
import android.os.Build;
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

/*
    In week details this adapter will be set.
    * For every details drawn on screen. this will be called twice.
    * --- a small hack to make list view fill entire screen ---
    * for first time when it is called, height is zero we just keep on appending to the screen with a white text.
      this will enable us to calculate the height of screen when full invisble text is added to screen.
    * the height of list view will be set in caller view.
    * from that caller view we obtain height and again call this adapter
    * we divide the height by 10 since we need to a show 9 rows in list view and some extra space for beauty.
    * --- hack ends ---
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
            if(Build.VERSION.SDK_INT >= 16)
                rowView.setBackground(context.getResources().getDrawable(R.color.grey));
        }

        return rowView;
    }
}
