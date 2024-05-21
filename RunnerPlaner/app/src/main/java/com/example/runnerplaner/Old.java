package com.example.runnerplaner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Old extends AppCompatActivity  {

    private TrainingDbHelper dbHelper;
    private ListView listViewTraining;
    private TrainingAdapter trainingAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OldActivity", "Old activity created");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old);
        dbHelper = new TrainingDbHelper(this);
        dbHelper.getWritableDatabase();

        listViewTraining = findViewById(R.id.listViewTraining);
        displayTrainingList();

    }

    private void displayTrainingList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                TrainingContract.TrainingEntry.COLUMN_DATE,
                TrainingContract.TrainingEntry.COLUMN_TYPE,
                TrainingContract.TrainingEntry.COLUMN_DISTANCE,
                TrainingContract.TrainingEntry.COLUMN_TIME
        };

        Cursor cursor = db.query(
                TrainingContract.TrainingEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<OneTraining> trainingList = new ArrayList<>();

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(TrainingContract.TrainingEntry.COLUMN_DATE));
            @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex(TrainingContract.TrainingEntry.COLUMN_TYPE));
            @SuppressLint("Range") int distance = cursor.getInt(cursor.getColumnIndex(TrainingContract.TrainingEntry.COLUMN_DISTANCE));
            @SuppressLint("Range") int time = cursor.getInt(cursor.getColumnIndex(TrainingContract.TrainingEntry.COLUMN_TIME));

            OneTraining training = new OneTraining(time, distance,type , date);
            trainingList.add(training);
        }

        trainingAdapter = new TrainingAdapter(this, trainingList);
        listViewTraining.setAdapter(trainingAdapter);

        cursor.close();
        dbHelper.close();
    }

}
