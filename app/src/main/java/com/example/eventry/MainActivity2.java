package com.example.eventry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    Button b2;
    EditText emailField, passwordField, confirmPasswordField;
    TextView loginLink;
    Spinner c1;
    String selectedCity = "";

    FirebaseAuth mAuth;  // ✅ Firebase Authentication instance

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        b2 = findViewById(R.id.button2);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        confirmPasswordField = findViewById(R.id.confirmPassword);
        loginLink = findViewById(R.id.textView2);
        c1 = findViewById(R.id.city);

        // Handle city spinner selection
        c1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCity = "";
            }
        });

        // Sign Up button click
        b2.setOnClickListener(v -> registerUser());

        // “Already have account? Login”
        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, MainActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirmPassword = confirmPasswordField.getText().toString().trim();

        // ✅ Validation
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Enter valid email");
            return;
        }
        if (password.isEmpty() || password.length() < 6) {
            passwordField.setError("Password must be at least 6 characters");
            return;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordField.setError("Passwords do not match");
            return;
        }
        if (selectedCity.isEmpty() || selectedCity.equals("Select City")) {
            Toast.makeText(this, "Please select your city", Toast.LENGTH_SHORT).show();
            return;
        }

        // ✅ Create user in Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();

                        // Save city to Realtime Database
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(userId)
                                .setValue(selectedCity);

                        Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Signup failed: " +
                                task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
