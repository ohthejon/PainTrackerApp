package com.jonathenchen.paintracker.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.adapters.DetailsAdapter;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        String[] values = new String[]{
          "Date,Avg. Pain, Energy", "6/27  MONDAY,3.4,3.2", "6/27  TUESDAY,3.4,3.2", "6/28  WEDNESDAY,3.4,3.2", "6/29  THURSDAY,3.4,3.2",
                "6/30  FRIDAY,3.4,3.2", "7/01  SATURDAY,3.4,3.2", "7/02  SUNDAY,3.4,3.2", "Weekly Avg,4.1,3.4"
        };
        ArrayAdapter<String> adapter = new DetailsAdapter(this, values);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

}
