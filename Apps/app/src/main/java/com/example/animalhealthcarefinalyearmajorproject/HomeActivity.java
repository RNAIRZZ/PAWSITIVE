package com.example.animalhealthcarefinalyearmajorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView diseaseprediction = findViewById(R.id.card1);
        diseaseprediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DiseasePredictionActivity.class));
            }
        });

        CardView realtime = findViewById(R.id.card2);
        realtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RealTimeActivity.class));
            }
        });

        CardView vaccination = findViewById(R.id.card3);
        vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, VaccinationActivity.class));
            }
        });

        CardView healthcaretips = findViewById(R.id.card4);
        healthcaretips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, HealthcareTipsActivity.class));
            }
        });

        CardView appointments = findViewById(R.id.card5);
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AppointmentRegistrationActivity.class));
            }
        });
    }
}