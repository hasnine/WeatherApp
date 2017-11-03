package com.weatherapp.has9.Map.view;

import android.app.NotificationManager;
import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iamsourav.sohoz.PreferenceUtil;
import com.weatherapp.has9.Constants;
import com.weatherapp.has9.R;
import android.support.v7.widget.Toolbar;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by has9 on 11/3/17.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private BottomSheetBehavior mBottomSheetBehavior;
    private GoogleMap mMap;
    @BindView(R.id.bottom_sheet)
    View bottomSheet;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvState)
    TextView tvState;

    @BindView(R.id.tvHum)
    TextView tvHum;

    @BindView(R.id.tvWind)
    TextView tvWind;

    @BindView(R.id.tvMaxTemp)
    TextView tvMaxTemp;

    @BindView(R.id.tvMinTemp)
    TextView tvMinTemp;

    @BindView(R.id.tvTempCel)
    TextView tvTempCel;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    double temp = 0, temp2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final String areaName= getIntent().getStringExtra("AreaName");
        final String WeatherState = getIntent().getStringExtra("State");
        final String Wind = getIntent().getStringExtra("Wind");
        final String MaxTemp = getIntent().getStringExtra("Max");
        final String MinTemp = getIntent().getStringExtra("Min");
        final String Hum = getIntent().getStringExtra("Hum");
        final String Temp = getIntent().getStringExtra("TempM");
        Log.d("KL",Temp+"");

        double mainTemp = Double.valueOf((String) MaxTemp);
        temp = mainTemp - 273.5;
        int tempInt = Integer.valueOf((int)temp);
        double mainTemp2 = Double.valueOf((String)MinTemp);
        temp2 = mainTemp2 - 273.5;
        int tempInt2 = Integer.valueOf((int)temp2);



        View bottomSheet = findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        //mBottomSheetBehavior.setPeekHeight(300);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(100);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });

        String tempMain = PreferenceUtil.getInstance().getStringValue(Constants.TEMP,"");
        tvName.setText(areaName);
        tvHum.setText("Humidity: "+Hum);
        tvState.setText(WeatherState);
        tvWind.setText("Wind Speed: "+Wind);
        tvMaxTemp.setText("Max. Temp: "+tempInt+" °C");
        tvMinTemp.setText("Min. Temp: "+tempInt2+" °C");
        tvTempCel.setText(tempMain);

        //setUpToolbar();
        initView();

    }

    public void initView(){
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latDouble, longDouble;
        final String lat= getIntent().getStringExtra("Lat");
        final String lon = getIntent().getStringExtra("Long");
        final String areaName = getIntent().getStringExtra("AreaName");

        try {

            latDouble = Double.parseDouble(lat); // Make use of autoboxing.  It's also easier to read.
            longDouble = Double.parseDouble(lon);
            LatLng sydney = new LatLng(latDouble, longDouble);
            mMap.addMarker(new MarkerOptions().position(sydney).title(areaName));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            //Log.d("LATLONG",latDouble +"-"+ longDouble+"");

        } catch (NumberFormatException e) {
            // p did not contain a valid double
        }
        // Add a marker in Sydney and move the camera

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
