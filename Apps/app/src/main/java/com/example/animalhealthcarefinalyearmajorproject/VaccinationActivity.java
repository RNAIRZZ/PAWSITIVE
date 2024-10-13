package com.example.animalhealthcarefinalyearmajorproject;


import static android.content.ContentValues.TAG;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class VaccinationActivity extends AppCompatActivity {

    private static final int RC_NOTIFICATION = 99;
    Button showDateTimeButton, setAlarmButton;
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        showDateTimeButton = findViewById(R.id.showDateTimeButton);
        setAlarmButton = findViewById(R.id.setAlarmButton);

        showDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDateTimeVisibility();
            }
        });

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, RC_NOTIFICATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void setAlarm() {

        cancelPreviousAlarm();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(VaccinationActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(VaccinationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(VaccinationActivity.this, "Alarm set for"+ day + "/"+ (month+1)+ "/"+ year + "at" + hour + ":" + minute, Toast.LENGTH_SHORT).show();

    }

    private void cancelPreviousAlarm() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(VaccinationActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(VaccinationActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        if (pendingIntent != null){
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    private void toggleDateTimeVisibility(){
        int datevisibility = datePicker.getVisibility();
        int timevisibility = timePicker.getVisibility();

        if (datevisibility == View.GONE & timevisibility == View.GONE){
            datePicker.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.VISIBLE);
            setAlarmButton.setVisibility(View.VISIBLE);
        }else {
            datePicker.setVisibility(View.GONE);
            timePicker.setVisibility(View.GONE);
            setAlarmButton.setVisibility(View.GONE);
        }
    }

}