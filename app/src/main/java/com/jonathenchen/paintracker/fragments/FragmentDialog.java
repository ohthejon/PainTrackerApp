package com.jonathenchen.paintracker.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.avtechlabs.peacock.Peacock;
import com.avtechlabs.peacock.enums.Utility;
import com.avtechlabs.peacock.utilities.ToastUtil;
import com.jonathenchen.paintracker.R;
import com.jonathenchen.paintracker.listeners.DateListener;
import com.jonathenchen.paintracker.utilites.DateUtil;
import com.jonathenchen.paintracker.utilites.EntryFormUtil;

/**
 * Created by adhithyan-3592 on 13/07/16.
 */

public class FragmentDialog extends DialogFragment {
    private SectionsPagerAdaper sectionsPagerAdaper;
    private ViewPager viewPager;
    private ImageButton imageButton;
    Button ok, cancel;
    ToastUtil toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        initButtons(view);
        initPagerTabStrip(view);
        initDate(view);

        sectionsPagerAdaper = new SectionsPagerAdaper(getChildFragmentManager());
        viewPager = (ViewPager)view.findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdaper);

        //PagerTabStrip tabStrip = (PagerTabStrip)viewPager.findViewById(R.id.pager_tab);


        toast = (ToastUtil)new Peacock().getUtility(getContext(), Utility.TOAST);

        return view;
    }

    public class SectionsPagerAdaper extends FragmentPagerAdapter{

        public SectionsPagerAdaper(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                   return new GeneralFragment();
                case 1:
                    return new SymptomsFragment();
                case 2:
                    return new DietFragment();
                case 3:
                    return new WeatherFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch(position){
                case 0:
                    return "GENERAL";
                case 1:
                    return "SYMPTOMS";
                case 2:
                    return "DIET";
                case 3:
                    return "MACRODATA";
                default:
                    return null;
            }
        }

    }

    public void initButtons(View view){
        ok = (Button)view.findViewById(R.id.buttonOk);
        cancel = (Button)view.findViewById(R.id.buttonCancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                EntryFormUtil.commit();
                EntryFormUtil.bottomNavigationBar.selectTab(3); //go to details tab after adding entry
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

    }

    public void initPagerTabStrip(View view){
        PagerTabStrip pagerTabStrip = (PagerTabStrip)view.findViewById(R.id.pager_tab);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.colorAccent));
    }

    public void initDate(View view){
        final TextView textViewDate = (TextView)view.findViewById(R.id.textViewDate);
        textViewDate.setText(EntryFormUtil.date);
        textViewDate.setOnClickListener(new DateListener(textViewDate, getActivity()));
    }

}
