package com.example.animalhealthcarefinalyearmajorproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiseasePredictionActivity extends AppCompatActivity {

    private EditText symptom1EditText, symptom2EditText, symptom3EditText, symptom4EditText;
    private Button predictButton;
    private TextView resultTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_prediction);

        symptom1EditText = findViewById(R.id.symptom1);
        symptom2EditText = findViewById(R.id.symptom2);
        symptom3EditText = findViewById(R.id.symptom3);
        symptom4EditText = findViewById(R.id.symptom4);
        predictButton = findViewById(R.id.predictButton);
        resultTextView = findViewById(R.id.resultTextView);
        requestQueue = Volley.newRequestQueue(this);

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predictDisease();
            }
        });
    }

    private void predictDisease() {
        List<String> symptomsList = new ArrayList<>();

        String symptom1 = symptom1EditText.getText().toString().trim();
        String symptom2 = symptom2EditText.getText().toString().trim();
        String symptom3 = symptom3EditText.getText().toString().trim();
        String symptom4 = symptom4EditText.getText().toString().trim();

        if (!symptom1.isEmpty()) symptomsList.add(symptom1);
        if (!symptom2.isEmpty()) symptomsList.add(symptom2);
        if (!symptom3.isEmpty()) symptomsList.add(symptom3);
        if (!symptom4.isEmpty()) symptomsList.add(symptom4);

        // Convert the list to a JSONArray
        JSONArray symptomsArray = new JSONArray(symptomsList);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("symptoms", symptomsArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // URL of your Flask API running locally
        String url = "https://amrita-major-project.onrender.com/predict";

        // Request a JSON response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String predictedDisease = response.getString("predicted_disease");
                            resultTextView.setText("Predicted Disease: " + predictedDisease);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultTextView.setText("Error: " + error.toString());
                    }
                }
        );

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}