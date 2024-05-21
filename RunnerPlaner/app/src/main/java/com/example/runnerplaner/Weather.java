package com.example.runnerplaner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weather extends AppCompatActivity implements View.OnClickListener {

    private TextView weatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherInfo = findViewById(R.id.textViewWeatherInfo);
        Button button = findViewById(R.id.buttonNextNext);
        button.setOnClickListener(this);

        // Postavljanje teksta na trenutni datum
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);


        Button refreshButton = findViewById(R.id.buttonRefresh);
        refreshButton.setOnClickListener(this);
    }

    private void fetchDataForDate(Date selectedDate) {
        // Formatiranje datuma
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(selectedDate);

        // Poziv API-ja sa izabranim datumom
        WeatherApiService apiService = WeatherApiClient.getWeatherApiService();
        Call<WeatherResponse> call = apiService.getWeather("Belgrade", WeatherApiClient.API_KEY);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        WeatherResponse.TemperatureInfo temperatureInfo = weatherResponse.getTemperatureInfo();
                        // jer dobijam Kelvine
                        double temperature = temperatureInfo.getTemperature() - 273;
                        String formattedTemperature = String.format("%.2f", temperature);

                        weatherInfo.append("Temperatura u Beogradu za dan " + formattedDate + " je: " + formattedTemperature + "°C.\n");
                        if (temperature < 6) {
                            weatherInfo.append("Predlazem Vam da se obucete toplo!\n");
                        } else if (temperature >= 6 && temperature <= 15) {
                            weatherInfo.append("Odlicni uslovi za trcanje!\n");
                        } else if (temperature > 15) {
                            weatherInfo.append("Bice malo toplije pa se shodno tome obucite!\nPonesite vodu!\n");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(Weather.this, "Nesto je poslo po zlu!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRefresh) {
            weatherInfo.setText("");
            Date currentDate = new Date();
            fetchDataForDate(currentDate);
        }
        else if (v.getId() == R.id.buttonNextNext){
            Intent intent = new Intent(Weather.this, MainActivity.class);
            startActivity(intent);
        }



    }
}
