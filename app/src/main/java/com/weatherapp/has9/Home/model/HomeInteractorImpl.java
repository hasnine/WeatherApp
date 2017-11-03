package com.weatherapp.has9.Home.model;

import android.content.Context;
import android.util.Log;

import com.weatherapp.has9.Home.HomeContact;
import com.weatherapp.has9.api.ApiEndPoint;
import com.weatherapp.has9.api.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by has9 on 11/3/17.
 */

public class HomeInteractorImpl implements HomeContact.HomeInteractor {
    @Override
    public void reqForHomeData(final HomeContact.OnCompleteListener listener, Context context) {

        Retrofit retrofit = RetrofitSingleton.getInstance();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);

        apiEndPoint.getWeatherData().enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()){

                    listener.loadHomeData(response.body().getList());
                } else {

                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                listener.onError("Request Failed");
            }
        });
    }
}
