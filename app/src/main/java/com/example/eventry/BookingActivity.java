package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    TextView eventTitle, eventDetails;
    LinearLayout goldLayout, silverLayout, bronzeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        eventTitle = findViewById(R.id.eventTitle);
        eventDetails = findViewById(R.id.eventDetails);
        goldLayout = findViewById(R.id.goldLayout);
        silverLayout = findViewById(R.id.silverLayout);
        bronzeLayout = findViewById(R.id.bronzeLayout);

        // ✅ Get event data from MusicActivity
        String title = getIntent().getStringExtra("event_title");
        String details = getIntent().getStringExtra("event_details");

        if (title != null) eventTitle.setText(title);
        if (details != null) eventDetails.setText(details);

        // ✅ Prepare intent data for seat screens
        silverLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, SilverActivity.class);
            intent.putExtra("event_title", eventTitle.getText().toString());
            intent.putExtra("event_details", eventDetails.getText().toString());
            intent.putExtra("phase1_price", getIntent().getIntExtra("silver_phase1", 0));
            intent.putExtra("phase2_price", getIntent().getIntExtra("silver_phase2", 0));
            startActivity(intent);
        });

        goldLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, GoldActivity.class);
            intent.putExtra("event_title", eventTitle.getText().toString());
            intent.putExtra("event_details", eventDetails.getText().toString());
            intent.putExtra("phase1_price", getIntent().getIntExtra("gold_phase1", 0));
            intent.putExtra("phase2_price", getIntent().getIntExtra("gold_phase2", 0));
            startActivity(intent);
        });

        bronzeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, BronzeActivity.class);
            intent.putExtra("event_title", eventTitle.getText().toString());
            intent.putExtra("event_details", eventDetails.getText().toString());
            intent.putExtra("phase1_price", getIntent().getIntExtra("bronze_phase1", 0));
            intent.putExtra("phase2_price", getIntent().getIntExtra("bronze_phase2", 0));
            startActivity(intent);
        });

    }
}
