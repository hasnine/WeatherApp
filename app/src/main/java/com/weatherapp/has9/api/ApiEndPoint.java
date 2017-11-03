package com.weatherapp.has9.api;

import com.weatherapp.has9.Home.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by has9 on 11/3/17.
 */

public interface ApiEndPoint {
    @GET("find?lat=23.68&lon=90.35&cnt=50&appid=e384f9ac095b2109c751d95296f8ea76")
    Call<WeatherModel>getWeatherData();
}
