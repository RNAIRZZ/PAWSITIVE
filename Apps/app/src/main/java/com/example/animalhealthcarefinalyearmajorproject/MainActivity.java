package com.example.animalhealthcarefinalyearmajorproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonSelectDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        buttonSelectDoctor = findViewById(R.id.buttonSelectDoctor);

        // Setup select doctor button click listener
        buttonSelectDoctor.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DoctorSelectionActivity.class);
            startActivity(intent);
        });
    }
}