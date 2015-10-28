package com.example.poblenou.eltemps;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.poblenou.eltemps.json.Forecast;
import com.example.poblenou.eltemps.json.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


import retrofit.Callback;
import retrofit.Response;
import retrofit.http.Query;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;

interface OpenWeatherMapService {
    @GET("forecast/daily")
    Call<Forecast> getLocation(
            @Query("q") String city,
            @Query("mode") String format,
            @Query("units") String units,
            @Query("cnt") Integer num,
            @Query("appid") String appid);
}

public class OwnApiClient {
    private final OpenWeatherMapService service;
    private final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String CITY = "Barcelona,es";
    private final String APPID = "0fe2ee29290ffcf8b046700e49fbda6a";

    public OwnApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORECAST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(OpenWeatherMapService.class);
    }
    public void updateForecasts(final ArrayAdapter<String> adapter, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String city = preferences.getString("city", "Barcelona,es");
        Call<Forecast> forecastCall = service.getLocation(
                city, "json", "metric", 7, APPID
        );
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();
                ArrayList<String> forecastStrings = new ArrayList<>();
                for (List list : forecast.getList()) {
                    String forecastString = getForecastString(list);
                    forecastStrings.add(forecastString);
                }
                adapter.clear();
                adapter.addAll(forecastStrings);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Forecasts", Arrays.toString(t.getStackTrace()));
            }
        });
    }
    private String getForecastString(List list) {
        Long dt = list.getDt();
        java.util.Date date = new java.util.Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
        String dateString = dateFormat.format(date);
        String description = list.getWeather().get(0).getDescription();
        Long min = Math.round(list.getTemp().getMin());
        Long max = Math.round(list.getTemp().getMax());
        return String.format("%s - %s - %s/%s",
                dateString, description, min, max
        );
    }
}
