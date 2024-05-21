package com.example.runnerplaner;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("main")
    private TemperatureInfo temperatureInfo;

    public TemperatureInfo getTemperatureInfo() {
        return temperatureInfo;
    }


    public class TemperatureInfo {
        @SerializedName("temp")
        private double temperature;
        public double getTemperature() {
            return temperature;
        }

    }

}
