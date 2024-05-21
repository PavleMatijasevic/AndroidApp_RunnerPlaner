package com.example.runnerplaner;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {
    private static final String BASE_URL = "https://api.openweathermap.org/";

    // Postavljanje api kljuca
    protected static final String API_KEY = "3f36f9385c8bbf04145fc51f28a60853";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static WeatherApiService getWeatherApiService() {
        return getClient().create(WeatherApiService.class);
    }
}
