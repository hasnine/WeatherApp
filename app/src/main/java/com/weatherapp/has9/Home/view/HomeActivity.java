package com.weatherapp.has9.Home.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.iamsourav.sohoz.LogUtil;
import com.weatherapp.has9.Home.HomeContact;
import com.weatherapp.has9.Home.model.List;
import com.weatherapp.has9.Home.presenter.HomePresnetreImpl;
import com.weatherapp.has9.Home.view.adapter.HomeAdapter;
import com.weatherapp.has9.MainActivity;
import com.weatherapp.has9.Map.view.MapsActivity;
import com.weatherapp.has9.R;
import com.weatherapp.has9.event.WeatherClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContact.HomeView{

    private HomeContact.HomePresenter mHomePresenter;

    @BindView(R.id.rvRecyclerView)
    RecyclerView rvRecyclerView;

    HomeAdapter mHomeAdapter;

    ProgressDialog progressDialog;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);



        initView();
        initProgressDialog();
        initPresenter();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }


    public void initView(){

    }

    public void initPresenter(){
        mHomePresenter = new HomePresnetreImpl(this);
        mHomePresenter.getHomeData();
    }

    @Override
    public Activity getApplicationMActivity() {
        return this;
    }

    @Override
    public Context getApplicationMContext() {
        return null;
    }

    @Override
    public void onLanguageChanged(int language) {

    }

    @Override
    public void showProgressbar() {

        progressDialog.show();
    }

    @Override
    public void hideProgressbar() {

        progressDialog.hide();
    }

    @Override
    public void showNetworkErrorUI(String msg) {

    }

    @Override
    public void showNoDataUI(String msg) {

    }

    @Override
    public void showLongToast(String msg) {

    }

    @Override
    public void showShortToast(String msg) {

        LogUtil.showShortToast(msg);
    }

    @Override
    public void loadedWeatherData(java.util.List<List> mCityList) {

        mHomeAdapter = new HomeAdapter(mCityList,this);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvRecyclerView.setAdapter(mHomeAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WeatherClickListener(WeatherClickListener event) {

        Log.d("KKKK",event.getmList().getCoord().getLat()+"");
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("Lat",event.getmList().getCoord().getLat()+"");
        intent.putExtra("Long",event.getmList().getCoord().getLon()+"");
        intent.putExtra("AreaName",event.getmList().getName()+"");
        intent.putExtra("State",event.getmList().getWeather().get(0).getMain()+"");
        intent.putExtra("Wind",event.getmList().getWind().getSpeed()+"");
        intent.putExtra("Max",event.getmList().getMain().getTempMax()+"");
        intent.putExtra("Min",event.getmList().getMain().getTempMin()+"");
        intent.putExtra("Hum",event.getmList().getMain().getHumidity()+"");

        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;

            }
        }, 2000);
    }
}
