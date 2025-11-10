package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity1 extends AppCompatActivity {

    Button btnPavilion, btnStandLeft, btnStandRight, btnGallery;
    CheckBox termsCheckBox;
    TextView eventTitle, eventDetails;

    int pavilionPrice, standPhase1Price, standPhase2Price, galleryPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking1); // Your XML file with stadium layout

        // Initialize UI
        btnPavilion = findViewById(R.id.btnPavilion);
        btnStandLeft = findViewById(R.id.btnStandLeft);
        btnStandRight = findViewById(R.id.btnStandRight);
        btnGallery = findViewById(R.id.btnGallery);
        termsCheckBox = findViewById(R.id.termsCheckBox);
        eventTitle = findViewById(R.id.eventTitle);
        eventDetails = findViewById(R.id.eventDetails);

        // Get dynamic event info and prices from SportsActivity
        String title = getIntent().getStringExtra("event_title");
        String details = getIntent().getStringExtra("event_details");
        pavilionPrice = getIntent().getIntExtra("pavilion", 0);
        standPhase1Price = getIntent().getIntExtra("stand_phase1", 0);
        standPhase2Price = getIntent().getIntExtra("stand_phase2", 0);
        galleryPrice = getIntent().getIntExtra("gallery", 0);

        // Set event info at top dynamically
        if (title != null) eventTitle.setText(title);
        if (details != null) eventDetails.setText(details);

        // ✅ Pavilion - single ticket type
        btnPavilion.setOnClickListener(v -> {
            if (!termsCheckBox.isChecked()) {
                Toast.makeText(this, "Please agree to pre-book the tickets first!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, PavilionActivity.class);
            intent.putExtra("event_title", title + " - Pavilion");
            intent.putExtra("event_details", details);
            intent.putExtra("price", pavilionPrice);
            startActivity(intent);
        });

        // ✅ Stand A - 2 Phases
        btnStandLeft.setOnClickListener(v -> {
            if (!termsCheckBox.isChecked()) {
                Toast.makeText(this, "Please agree to pre-book the tickets first!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, StandActivity.class);
            intent.putExtra("event_title", title + " - Stand A");
            intent.putExtra("event_details", details);
            intent.putExtra("phase1_price", standPhase1Price);
            intent.putExtra("phase2_price", standPhase2Price);
            startActivity(intent);
        });

        // ✅ Stand B - 2 Phases (same as Stand A)
        btnStandRight.setOnClickListener(v -> {
            if (!termsCheckBox.isChecked()) {
                Toast.makeText(this, "Please agree to pre-book the tickets first!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, StandActivity.class);
            intent.putExtra("event_title", title + " - Stand B");
            intent.putExtra("event_details", details);
            intent.putExtra("phase1_price", standPhase1Price);
            intent.putExtra("phase2_price", standPhase2Price);
            startActivity(intent);
        });

        // ✅ Gallery - single price
        btnGallery.setOnClickListener(v -> {
            if (!termsCheckBox.isChecked()) {
                Toast.makeText(this, "Please agree to pre-book the tickets first!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, GalleryActivity.class);
            intent.putExtra("event_title", title + " - Gallery");
            intent.putExtra("event_details", details);
            intent.putExtra("price", galleryPrice);
            startActivity(intent);
        });
    }
}

