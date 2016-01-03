package com.example.narayankoirala.androidreside;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import me.relex.circleindicator.CircleIndicator;


public class WelcomeScreen extends FragmentActivity {
    ViewPager viewpager;


    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.welcom);
        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new MyAdapters(getSupportFragmentManager()));
        viewpager.setPageTransformer(true, new RotateUpTransformer());
        CircleIndicator circular = (CircleIndicator) findViewById(R.id.indicator);
        circular.setViewPager(viewpager);
    }

}

class MyAdapters extends FragmentPagerAdapter {

    public MyAdapters(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment fragment = null;
        if (arg0 == 0) {
            fragment = new Welcom1Fragment();
        } else if (arg0 == 1) {
            fragment = new Welcom2Fragment();

        } else if (arg0 == 2) {
            fragment = new Welcom3Fragment();
        } else if (arg0 == 3) {
            fragment = new LoginFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 4;
    }
}
