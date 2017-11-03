package com.weatherapp.has9.Home;

import android.content.Context;

import com.weatherapp.has9.BasePresenter;
import com.weatherapp.has9.BaseView;
import com.weatherapp.has9.Home.model.List;

/**
 * Created by has9 on 11/3/17.
 */

public interface HomeContact {

    interface HomeView extends BaseView {

        void loadedWeatherData(java.util.List<List> mCityList);

    }

    interface HomePresenter extends BasePresenter {

        void getHomeData();


    }

    interface HomeInteractor {

        void reqForHomeData(HomeContact.OnCompleteListener listener, Context context);
    }

    interface OnCompleteListener {
        void loadHomeData(java.util.List<List> mCityList);
        void onError(String msg);
    }

}
