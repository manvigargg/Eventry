package com.example.eventry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    ImageView i1;
    ImageButton b3, b4, b5, b6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        i1 = findViewById(R.id.icon);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);

        // Profile icon click
        i1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(intent);
        });

        // Category buttons
        b3.setOnClickListener(v -> openCategory("Music Events", com.example.eventry.MusicActivity.class));
        b4.setOnClickListener(v -> openCategory("Comedy Shows", ComedyActivity.class));
        b5.setOnClickListener(v -> openCategory("Sports Events", SportsActivity.class));
        b6.setOnClickListener(v -> openCategory("Shows", ShowsActivity.class));
    }

    // ✅ Helper function to open a category
    private void openCategory(String categoryName, Class<?> targetActivity) {
        Toast.makeText(this, "Opening " + categoryName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity3.this, targetActivity);
        startActivity(intent);
    }
}
