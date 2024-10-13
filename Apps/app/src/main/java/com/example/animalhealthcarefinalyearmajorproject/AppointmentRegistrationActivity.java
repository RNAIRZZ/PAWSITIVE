package com.example.animalhealthcarefinalyearmajorproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppointmentRegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private Button buttonRegister;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_registration);

        mAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(AppointmentRegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!isValidEmail(email)) {
                editTextEmail.setError("Enter a valid email");
            } else {
                checkIfUserExists(email, name, password, phone);
            }
        });

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(AppointmentRegistrationActivity.this, AppointmentLoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void checkIfUserExists(String email, String name, String password, String phone) {
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().getSignInMethods().isEmpty()) {
                    Toast.makeText(AppointmentRegistrationActivity.this, "User already registered. Redirecting to login...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AppointmentRegistrationActivity.this, AppointmentLoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    registerUser(name, email, password, phone);
                }
            } else {
                Toast.makeText(AppointmentRegistrationActivity.this, "Error checking user existence: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(String name, String email, String password, String phone) {
        Log.d("AppointmentRegistrationActivity", "registerUser: Attempting to register");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(AppointmentRegistrationActivity.this, "Registration successful. Check your email for verification.", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(AppointmentRegistrationActivity.this, AppointmentLoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(AppointmentRegistrationActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(AppointmentRegistrationActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
