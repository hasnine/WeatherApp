package com.weatherapp.has9;

import android.app.Application;

import com.iamsourav.sohoz.SohozUtil;

/**
 * Created by has9 on 11/3/17.
 */

public class WeatherAppAplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SohozUtil.getInstance().initSohozUtil(getApplicationContext());
    }
}
