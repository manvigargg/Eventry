package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ComedyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedy);

        // --- Get references to cards ---
        FrameLayout card4 = findViewById(R.id.card4);
        FrameLayout card5 = findViewById(R.id.card5);
        FrameLayout card6 = findViewById(R.id.card6);

        // --- Get references to book ticket buttons ---
        Button bookButton1 = card4.findViewById(R.id.btn_book_ticket);
        Button bookButton2 = card5.findViewById(R.id.btn_book_ticket2);
        Button bookButton3 = card6.findViewById(R.id.btn_book_ticket3);

        // --- Book Ticket button click listeners (with dynamic data) ---
        bookButton1.setOnClickListener(v -> {
            Intent intent = new Intent(ComedyActivity.this, BookingActivity.class);
            intent.putExtra("event_title", "Samay Raina Unfiltered | Indore");
            intent.putExtra("event_details", "Fri, 7 Nov | 7:30 PM onwards | Ravindra Natya Grah, Indore");
            intent.putExtra("silver_phase1", 5350);
            intent.putExtra("silver_phase2", 5890);
            intent.putExtra("gold_phase1", 6660);
            intent.putExtra("gold_phase2", 7250);
            intent.putExtra("bronze_phase1", 4499);
            intent.putExtra("bronze_phase2", 4999);
            startActivity(intent);
        });

        bookButton2.setOnClickListener(v -> {
            Intent intent = new Intent(ComedyActivity.this, BookingActivity.class);
            intent.putExtra("event_title", "Gurleen Pannu India Tour | Indore");
            intent.putExtra("event_details", "Sun, 23 Nov | 8 PM onwards | Lata Mangeshkar Sabhagrha IDA Auditorium, Indore");
            intent.putExtra("silver_phase1", 2300);
            intent.putExtra("silver_phase2", 2990);
            intent.putExtra("gold_phase1", 3499);
            intent.putExtra("gold_phase2", 4199);
            intent.putExtra("bronze_phase1", 1299);
            intent.putExtra("bronze_phase2", 1800);
            startActivity(intent);
        });

        bookButton3.setOnClickListener(v -> {
            Intent intent = new Intent(ComedyActivity.this, BookingActivity.class);
            intent.putExtra("event_title", "Aakash Gupta World Tour | Indore");
            intent.putExtra("event_details", "Sat, 13 Dec | 9 PM onwards | Cafe Terazza, Indore");
            intent.putExtra("silver_phase1", 4099);
            intent.putExtra("silver_phase2", 4800);
            intent.putExtra("gold_phase1", 5299);
            intent.putExtra("gold_phase2", 5750);
            intent.putExtra("bronze_phase1", 3199);
            intent.putExtra("bronze_phase2", 3599);
            startActivity(intent);
        });


        // --- Get references to save/bookmark buttons ---
        ImageButton btnSave1 = card4.findViewById(R.id.btn_save1);
        ImageButton btnSave2 = card5.findViewById(R.id.btn_save2);
        ImageButton btnSave3 = card6.findViewById(R.id.btn_save3);

        // --- Bookmark toggle setup ---
        setupBookmarkToggle(btnSave1);
        setupBookmarkToggle(btnSave2);
        setupBookmarkToggle(btnSave3);

        // --- Optional: click on full card to open MusicDetailActivity ---
        View.OnClickListener openDetail = v -> {
            Intent intent = new Intent(ComedyActivity.this, ComedyDetailActivity.class);
            startActivity(intent);
        };

        card4.setOnClickListener(openDetail);
        card5.setOnClickListener(openDetail);
        card6.setOnClickListener(openDetail);
    }

    // --- Bookmark animation + toggle logic ---
    private void setupBookmarkToggle(ImageButton button) {
        button.setOnClickListener(v -> {
            Object tag = button.getTag();
            boolean isSaved = tag != null && (boolean) tag;

            if (isSaved) {
                button.setImageResource(R.drawable.ic_bookmark_border);
                button.setTag(false);
            } else {
                button.setImageResource(R.drawable.ic_bookmark);
                button.setTag(true);
            }

            button.animate().scaleX(1.3f).scaleY(1.3f).setDuration(150)
                    .withEndAction(() -> button.animate().scaleX(1f).scaleY(1f).setDuration(150));
        });
    }
}