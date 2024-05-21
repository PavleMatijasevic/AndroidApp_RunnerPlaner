package com.example.runnerplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioButton;

public class Profil extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Button buttonNext = findViewById(R.id.buttonGoToTraining);
        buttonNext.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonGoToTraining){
            Intent intent = new Intent(Profil.this, Training.class);

            Bundle extras = new Bundle();

            EditText editTextName = findViewById(R.id.editTextName);
            String ime = editTextName.getText().toString();

            EditText years = findViewById(R.id.editTextNumberYears);
            int godine = Integer.parseInt(years.getText().toString());

            EditText pace = findViewById(R.id.editTextFinishTime);
            int paceWish = Integer.parseInt(pace.getText().toString());


            RadioGroup radioGender = findViewById(R.id.GenderGroup);
            int selectedID_gender = radioGender.getCheckedRadioButtonId();
            RadioButton radioButtonGender = findViewById(selectedID_gender);
            String odgovorPol = radioButtonGender.getText().toString();

            RadioGroup radioStatus = findViewById(R.id.RunnerStatus);
            int selectedID_status = radioStatus.getCheckedRadioButtonId();
            RadioButton radioButtonStatus = findViewById(selectedID_status);
            String odgovorStatus = radioButtonStatus.getText().toString();


            RadioGroup radioRaceType = findViewById(R.id.radioGroupRaceType);
            int selectedID_race = radioRaceType.getCheckedRadioButtonId();
            RadioButton radioButtonRaceType = findViewById(selectedID_race);
            String odgovorRaceType = radioButtonRaceType.getText().toString();

            extras.putString("KEY_NAME", ime);

            extras.putInt("KEY_YEARS", godine);
            extras.putString("KEY_GENDER", odgovorPol);
            extras.putString("KEY_STATUS", odgovorStatus);
            extras.putString("KEY_RACE_TYPE", odgovorRaceType);
            extras.putInt("KEY_PACE", paceWish);
            intent.putExtras(extras);
            startActivity(intent);


        }
    }
}