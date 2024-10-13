package com.example.animalhealthcarefinalyearmajorproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingConfirmationActivity extends AppCompatActivity {

    private TextView textViewConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        // Initialize views
        textViewConfirmation = findViewById(R.id.textViewConfirmation);

        // Get selected doctor details
        Intent intent = getIntent();
        String doctorDetails = intent.getStringExtra("doctorDetails");

        // Set confirmation message
        if (doctorDetails != null) {
            textViewConfirmation.setText("Your booking is confirmed with: " + doctorDetails);
        } else {
            textViewConfirmation.setText("Booking confirmation details are missing.");
        }
    }
}
