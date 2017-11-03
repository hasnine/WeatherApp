package com.weatherapp.has9.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iamsourav.sohoz.PreferenceUtil;
import com.weatherapp.has9.Constants;
import com.weatherapp.has9.Home.view.HomeActivity;
import com.weatherapp.has9.settings.SettingsActivity;
import com.weatherapp.has9.splash.view.SplashActivity;

/**
 * Created by Jaison on 17/06/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        Log.d(TAG, "onReceive: ");
        String tempMain = PreferenceUtil.getInstance().getStringValue(Constants.TEMP,"");
        //Trigger the notification
        NotificationScheduler.showNotification(context, SettingsActivity.class,
                "WeatherApp", "Current Temperature: "+tempMain);

    }
}


