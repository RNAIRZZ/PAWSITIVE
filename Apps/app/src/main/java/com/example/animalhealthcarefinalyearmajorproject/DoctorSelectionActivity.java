package com.example.animalhealthcarefinalyearmajorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorSelectionActivity extends AppCompatActivity {

    private ListView listViewDoctors;
    private List<Doctor> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_selection);

        // Initialize views
        listViewDoctors = findViewById(R.id.listViewDoctors);

        // Initialize doctor list
        doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Priya Sharma", "Degree: BVS, Experience: 6 years, Fee: ₹650"));
        doctors.add(new Doctor("Dr. Rajesh Patel", "Degree: MVS, Experience: 8 years, Fee: ₹700"));
        doctors.add(new Doctor("Dr. Neha Singh", "Degree: DVM, Experience: 5 years, Fee: ₹600"));
        doctors.add(new Doctor("Dr. Arjun Kumar", "Degree: BVS, Experience: 7 years, Fee: ₹550"));
        doctors.add(new Doctor("Dr. Ananya Mehta", "Degree: DVM, Experience: 9 years, Fee: ₹750"));
        doctors.add(new Doctor("Dr. Rohit Gupta", "Degree: MVS, Experience: 10 years, Fee: ₹800"));
        doctors.add(new Doctor("Dr. Sneha Reddy", "Degree: DVM, Experience: 6 years, Fee: ₹650"));
        doctors.add(new Doctor("Dr. Vikram Sharma", "Degree: BVS, Experience: 8 years, Fee: ₹700"));
        doctors.add(new Doctor("Dr. Meera Joshi", "Degree: DVM, Experience: 5 years, Fee: ₹600"));
        doctors.add(new Doctor("Dr. Sandeep Chandra", "Degree: BVS, Experience: 7 years, Fee: ₹550"));

        // Setup custom adapter
        DoctorAdapter adapter = new DoctorAdapter(doctors);
        listViewDoctors.setAdapter(adapter);

        // Setup item click listener
        listViewDoctors.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Doctor selectedDoctor = doctors.get(position);
            Toast.makeText(DoctorSelectionActivity.this, "Selected: " + selectedDoctor.getName(), Toast.LENGTH_SHORT).show();

            // Navigate to BookingConfirmationActivity
            Intent intent = new Intent(DoctorSelectionActivity.this, BookingConfirmationActivity.class);
            intent.putExtra("doctorDetails", selectedDoctor.getName() + ", " + selectedDoctor.getDetails());
            startActivity(intent);
        });
    }

    private class Doctor {
        private String name;
        private String details;

        public Doctor(String name, String details) {
            this.name = name;
            this.details = details;
        }

        public String getName() {
            return name;
        }

        public String getDetails() {
            return details;
        }
    }

    private class DoctorAdapter extends ArrayAdapter<Doctor> {
        public DoctorAdapter(List<Doctor> doctors) {
            super(DoctorSelectionActivity.this, R.layout.doctor_item, doctors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.doctor_item, parent, false);
            }

            Doctor doctor = getItem(position);

            TextView textViewName = convertView.findViewById(R.id.textViewDoctorName);
            TextView textViewDetails = convertView.findViewById(R.id.textViewDetails);

            if (doctor != null) {
                textViewName.setText(doctor.getName());
                textViewDetails.setText(doctor.getDetails());
            }

            return convertView;
        }
    }
}
