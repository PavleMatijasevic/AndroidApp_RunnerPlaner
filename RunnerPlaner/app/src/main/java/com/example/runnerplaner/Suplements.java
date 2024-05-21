package com.example.runnerplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Suplements extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suplements);

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        Bundle extrasNew = getIntent().getExtras();

        String imeKorisnika = extrasNew.getString("KEY_NAME");

        String odgovorStatus = extrasNew.getString("KEY_STATUS");
        int godine = extrasNew.getInt("KEY_YEARS");
        TextView paceCalc = findViewById(R.id.textViewPaceCalculator);

        String odgovorRaceType = extrasNew.getString("KEY_RACE_TYPE");
        int vreme = extrasNew.getInt("KEY_PACE");

        double brzina = 0.0;
        double b = vreme / 60.0; // Pretvaranje vremena u sate

        if (odgovorRaceType.equals("Polumaraton")) {
            brzina = 21.0 / b;
        } else if (odgovorRaceType.equals("Maraton")) {
            brzina = 42.0 / b;
        } else if (odgovorRaceType.equals("10km")) {
            brzina = 10.0 / b;
        }

        String formatiranaBrzina = String.format("%.2f", brzina);

        TextView saveti = findViewById(R.id.textViewSaveti);
        TextView probniIspis = findViewById(R.id.textView3);
        paceCalc.setText("Vas cilj je da " + odgovorRaceType + " istrcite za " + vreme + " minuta.\n"
        +"To cete uspeti ako Vam brzina bude: " + formatiranaBrzina + " km/h\n");

        probniIspis.setText(imeKorisnika + " ima " + godine + " godina i sprema se da istrci " + odgovorRaceType+"\n");


        if(odgovorRaceType.equals("Polumaraton")){
            saveti.setText("Voda: 0.5L vode ili druge tecnosti na svaka 2km: [2km, 4km, 6km, 8km, 10km, 12km, 14km, 16km, 18km, 20km]\n" +
                    "Gelovi: Na svakih 6 do 7 kilometara: [7km, 15km]\n"
            +"Voce: na kilometar pre uzimanja gela uzeti bananu ili limun: [6km, 14km]\n");

        }
        else if(odgovorRaceType.equals("Maraton")){
            saveti.setText("Voda: 0.5L vode ili druge tecnosti na svaka 2km: [2km, 4km, 6km, 8km, 10km, 12km, 14km, 16km, 18km, 20km, 22km, 24km, 26km, 28km, 30km, 32km, 34km, 36km, 38km, 40km]\n" +
                    "Gelovi: Na svakih 6 do 7 kilometara: [7km, 15km, 22km, 29km, 35km, 40km]\n"
                    +"Voce: na kilometar pre uzimanja gela uzeti bananu ili limun: [6km, 14km, 21km, 28km, 34km, 39km]\n");
        }
        else if(odgovorRaceType.equals("10km")){
            saveti.setText("Voda: 0.5L vode ili druge tecnosti na svaka 2km: [2km, 4km, 6km, 8km]\n" +
                    "Gelovi: Moze i bez gela, za svaki slucaj imati jedan kod sebe i po potrebi uzeti jedan na 7km\n"
                    +"Voce: Nema potrebe za uzimanjem voca u toku trke, jednino ako se ne kozumira gel, uzeti limun u drugoj polovini trke\n");

        }


    }


    @Override
    public void onClick(View v) {
        if (R.id.buttonBack == v.getId()){
            Intent intent = new Intent(Suplements.this, Weather.class);
            startActivity(intent);



        }
    }
}