package com.weatherapp.has9.event;

import com.weatherapp.has9.Home.model.List;

/**
 * Created by has9 on 11/3/17.
 */

public class WeatherClickListener {

    List mList;

    public WeatherClickListener(List mList) {
        this.mList = mList;
    }

    public List getmList() {
        return mList;
    }
}
