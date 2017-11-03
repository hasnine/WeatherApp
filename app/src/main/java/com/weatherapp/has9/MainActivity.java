package com.weatherapp.has9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String lat= getIntent().getStringExtra("Lat");
        final String lon = getIntent().getStringExtra("Long");

        Log.d("LLLL",lat + lon + "");
    }
}
