package com.weatherapp.has9.Home.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iamsourav.sohoz.PreferenceUtil;
import com.weatherapp.has9.Constants;
import com.weatherapp.has9.R;
import com.weatherapp.has9.event.WeatherClickListener;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by has9 on 11/3/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private List<com.weatherapp.has9.Home.model.List> mCityList;
    private Context mContext;
    double temp = 0;


    public HomeAdapter(List<com.weatherapp.has9.Home.model.List> mCityList, Context mContext) {
        this.mCityList = mCityList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.city_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        double temp2 = mCityList.get(position).getMain().getTemp()-273.5;
        int mainTemp = Integer.valueOf((int) temp2);
        holder.tvCityName.setText(mCityList.get(position).getName()+"");
        holder.tvWeatherState.setText(mCityList.get(position).getWeather().get(0).getMain() + "");
        holder.tvTemp.setText(mainTemp+ " °C");

        Log.d("LLLLLL",mainTemp+"");

    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCityName)
        TextView tvCityName;

        @BindView(R.id.tvWeatherState)
        TextView tvWeatherState;

        @BindView(R.id.tvTemp)
        TextView tvTemp;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new WeatherClickListener(mCityList.get(getAdapterPosition())));
                }
            });


        }
    }
}
