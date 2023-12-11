package com.svalero.roadrunner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.view.races.FavRacesListView;
import com.svalero.roadrunner.view.races.RacesListView;
import com.svalero.roadrunner.view.registrations.RegistrationsListView;

public class MainActivity extends AppCompatActivity {

    Button racesList;
    Button registrationsList;
    Button favRacesList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        racesList = findViewById(R.id.races_list_button);
        racesList.setOnClickListener(view -> {
            Intent intent = new Intent(this, RacesListView.class);
            startActivity(intent);
        });

        registrationsList = findViewById(R.id.registrations_list_button);
        registrationsList.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistrationsListView.class);
            startActivity(intent);
        });

        favRacesList = findViewById(R.id.fav_races_list_button);
        favRacesList.setOnClickListener(view -> {
            Intent intent = new Intent(this, FavRacesListView.class);
            startActivity(intent);
        });

        login();
    }

    private void login() {
        SharedPreferences sharedPref = getSharedPreferences("mainSharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("userId", 1);
        editor.commit();
    }
}