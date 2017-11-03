package com.weatherapp.has9.splash.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weatherapp.has9.Home.view.HomeActivity;
import com.weatherapp.has9.MainActivity;
import com.weatherapp.has9.R;
import com.weatherapp.has9.splash.SplashContact;
import com.weatherapp.has9.splash.presenter.SplashPresenterImpl;

/**
 * Created by has9 on 11/3/17.
 */

public class SplashActivity extends AppCompatActivity implements SplashContact.SplashView{

    private SplashContact.SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        initView();
        initPresenter();
    }

    public void initView(){

    }

    public void initPresenter(){
        mSplashPresenter=new SplashPresenterImpl(this);
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

    }

    @Override
    public void hideProgressbar() {

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSplashPresenter.onResume();

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
