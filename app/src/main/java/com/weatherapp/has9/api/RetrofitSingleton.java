package com.weatherapp.has9.api;

import android.support.compat.BuildConfig;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by has9 on 11/3/17.
 */

public class RetrofitSingleton {


    private static Retrofit mRetrofit;
    private static String HOST = "http://api.openweathermap.org/data/2.5/";


    public synchronized static Retrofit getInstance() {


        if (mRetrofit == null) {
            createRetrofit();
        }
        return mRetrofit;
    }

    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
            new GsonBuilder()

                    .setLenient()
                    .create());

    private static void createRetrofit() {
        /*OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();*/

        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();

        OkHttpClient client = clientBuilder.readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

/*        Gson gson = new GsonBuilder().disableHtmlEscaping().create();*/

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOST)
                //.addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }
}