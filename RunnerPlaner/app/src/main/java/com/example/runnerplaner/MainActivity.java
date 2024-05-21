package com.example.runnerplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newPlan = findViewById(R.id.buttonNewPlan);
        newPlan.setOnClickListener(this);

        Button old = findViewById(R.id.buttonOld);
        old.setOnClickListener(this);

        TextView textViewAppName1 = findViewById(R.id.textViewNaslov1);
        TextView textViewAppName2 = findViewById(R.id.textViewNaslov2);

        Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);

        textViewAppName1.startAnimation(blinkAnimation);
        textViewAppName2.startAnimation(blinkAnimation);


    }

    @Override
    public void onClick(View v) {
    if (R.id.buttonNewPlan == v.getId()){
        Intent intent = new Intent(MainActivity.this, Profil.class);
        startActivity(intent);
    } else if (R.id.buttonOld == v.getId()) {
        Intent intent1 = new Intent(MainActivity.this, Old.class);
        startActivity(intent1);
        
    }


    }
}