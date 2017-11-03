package com.weatherapp.has9.splash.presenter;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.weatherapp.has9.MainActivity;
import com.weatherapp.has9.splash.SplashContact;

/**
 * Created by has9 on 11/3/17.
 */

public class SplashPresenterImpl implements SplashContact.SplashPresenter {

    SplashContact.SplashView mSplashView;
    public static String TAG = "WeatherApp";

    public SplashPresenterImpl(SplashContact.SplashView mSplashView) {
        this.mSplashView = mSplashView;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        haltSplash();
    }

    public void haltSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashView.navigateToHome();
            }
        }, 1200);

    }

}
