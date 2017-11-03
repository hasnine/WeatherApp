package com.weatherapp.has9;

import android.app.Activity;
import android.content.Context;

/**
 * Created by has9 on 11/3/17.
 */

public interface BaseView {

    Activity getApplicationMActivity();
    Context getApplicationMContext();

    void onLanguageChanged(int language);

    void showProgressbar();
    void hideProgressbar();

    void showNetworkErrorUI(String msg);
    void showNoDataUI(String msg);

    void showLongToast(String msg);
    void showShortToast(String msg);
}
