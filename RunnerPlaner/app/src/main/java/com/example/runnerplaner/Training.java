package com.example.runnerplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Training extends AppCompatActivity implements View.OnClickListener {
    private TrainingDbHelper dbHelper;
    private String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        dbHelper = new TrainingDbHelper(this);

        String[] trainingTypes = {"Duzina", "Intervali", "Tempo", "Trka", "Gym"};
        Spinner spinnerTrainingType = findViewById(R.id.spinnerTrainingType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trainingTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrainingType.setAdapter(adapter);

        Button saveButton = findViewById(R.id.buttonSaveTraining);
        saveButton.setOnClickListener(view -> saveTraining());

        Button nextButton = findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(this);

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        });

    }
    private void saveTraining() {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();

            Spinner spinnerTrainingType = findViewById(R.id.spinnerTrainingType);
            String selectedTraining = spinnerTrainingType.getSelectedItem().toString();

            EditText distanceEditText = findViewById(R.id.editTextDistance);
            String distanceString = distanceEditText.getText().toString();
            double distance = 0.0;
            if (!TextUtils.isEmpty(distanceString)) {
                distance = Double.parseDouble(distanceString);
            }

            EditText timeEditText = findViewById(R.id.editTextTime);
            String timeString = timeEditText.getText().toString();
            int time = 0;
            if (!TextUtils.isEmpty(timeString)) {
                time = Integer.parseInt(timeString);
            }

            ContentValues values = new ContentValues();
            values.put(TrainingContract.TrainingEntry.COLUMN_DATE, selectedDate);
            values.put(TrainingContract.TrainingEntry.COLUMN_TYPE, selectedTraining);
            values.put(TrainingContract.TrainingEntry.COLUMN_DISTANCE, distance);
            values.put(TrainingContract.TrainingEntry.COLUMN_TIME, time);

            db.insert(TrainingContract.TrainingEntry.TABLE_NAME, null, values);
            Toast.makeText(this, "Trening je uspesno sacuvan!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }



    @Override
    public void onClick(View v) {

        if(R.id.buttonNext == v.getId()){
            Bundle extras = getIntent().getExtras();
                String imeKorisnika = extras.getString("KEY_NAME");
                String odgovorRaceType = extras.getString("KEY_RACE_TYPE");
                String odgovorStatus = extras.getString("KEY_STATUS");
                int godine = extras.getInt("KEY_YEARS");
                int ciljnoVreme = extras.getInt("KEY_PACE");



            Spinner spinnerTrenning = findViewById(R.id.spinnerTrainingType);
            String selectedTraining = spinnerTrenning.getSelectedItem().toString();



            Bundle extrasNew = new Bundle();
            extrasNew.putString("KEY_NAME", imeKorisnika);
            extrasNew.putString("KEY_RACE_TYPE", odgovorRaceType);
            extrasNew.putString("KEY_STATUS", odgovorStatus);
            extrasNew.putInt("KEY_YEARS", godine);
            extrasNew.putString("KEY_TRAINING_TYPE", selectedTraining);
            extrasNew.putInt("KEY_PACE", ciljnoVreme);

            Intent intent = new Intent(Training.this, Suplements.class);

            intent.putExtras(extrasNew);
            startActivity(intent);
        }
        else if(R.id.buttonSaveTraining == v.getId()){
            saveTraining();


        }


    }
}