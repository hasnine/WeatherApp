package com.weatherapp.has9.Home.presenter;

import com.iamsourav.sohoz.NetworkUtil;
import com.weatherapp.has9.Constants;
import com.weatherapp.has9.Home.HomeContact;
import com.weatherapp.has9.Home.model.HomeInteractorImpl;
import com.weatherapp.has9.Home.model.List;

/**
 * Created by has9 on 11/3/17.
 */

public class HomePresnetreImpl implements HomeContact.HomePresenter,HomeContact.OnCompleteListener {

    public HomeContact.HomeView mHomeView;
    public HomeContact.HomeInteractor mHomeInteractor;

    public HomePresnetreImpl(HomeContact.HomeView mHomeView) {
        this.mHomeView = mHomeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void getHomeData() {
        mHomeInteractor.reqForHomeData(this, mHomeView.getApplicationMContext());
        mHomeView.showProgressbar();
        /*if (NetworkUtil.isNetworkConnected(mHomeView.getApplicationMContext())) {
            mHomeInteractor.reqForHomeData(this, mHomeView.getApplicationMContext());
        } else {
            mHomeView.showShortToast(Constants.NETWORK_ERROR);
        }*/
    }

    @Override
    public void loadHomeData(java.util.List<List> mCityList) {

        mHomeView.loadedWeatherData(mCityList);
        mHomeView.hideProgressbar();
    }

    @Override
    public void onError(String msg) {

        mHomeView.showShortToast(msg);
    }
}
