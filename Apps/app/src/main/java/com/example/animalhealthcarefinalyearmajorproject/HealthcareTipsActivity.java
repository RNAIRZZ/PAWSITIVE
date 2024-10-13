package com.example.animalhealthcarefinalyearmajorproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthcareTipsActivity extends AppCompatActivity {

    @SuppressLint("LocalSuppress")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare_tips);

        @SuppressLint("MissingInflatedId") View rootView = findViewById(R.id.main); // Ensure this ID is correct

        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
                Insets insetsCompat = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(insetsCompat.left, insetsCompat.top, insetsCompat.right, insetsCompat.bottom);
                return insets;
            });
        }
    }
}
