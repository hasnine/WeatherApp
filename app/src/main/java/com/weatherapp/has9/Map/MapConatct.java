package com.weatherapp.has9.Map;

import android.content.Context;

import com.weatherapp.has9.BasePresenter;
import com.weatherapp.has9.BaseView;
import com.weatherapp.has9.Constants;

/**
 * Created by has9 on 11/3/17.
 */

public interface MapConatct {

    public interface MapView extends BaseView {
        public void loadData();
    }

    public  interface MapPresenter extends BasePresenter {

        void getDataWeather();
    }

    public interface MapInteractor {

        void reqForWeatherDate(MapConatct.OnCompleteListener listener, Context mContext);
    }

    public interface OnCompleteListener {

        void OnDataLoad();
        void onError(String msg);
    }
}
