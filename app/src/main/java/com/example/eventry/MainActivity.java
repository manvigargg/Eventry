package com.example.eventry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button b1;
    TextView t1;
    EditText emailField, passwordField;
    FirebaseAuth mAuth; // ✅ Firebase instance

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // UI references
        b1 = findViewById(R.id.button);
        t1 = findViewById(R.id.textView);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);

        // LOGIN button
        b1.setOnClickListener(v -> loginUser());

        // SIGNUP text click
        t1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        // Validation
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Enter a valid email");
            return;
        }
        if (password.isEmpty()) {
            passwordField.setError("Enter your password");
            return;
        }

        // ✅ Firebase Login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity3.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid credentials or user not registered.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
