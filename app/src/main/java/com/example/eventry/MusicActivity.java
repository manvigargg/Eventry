package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // --- Get references to cards ---
        FrameLayout card1 = findViewById(R.id.card1);
        FrameLayout card2 = findViewById(R.id.card2);
        FrameLayout card3 = findViewById(R.id.card3);

        // --- Get references to book ticket buttons ---
        Button bookButton1 = card1.findViewById(R.id.btn_book_ticket);
        Button bookButton2 = card2.findViewById(R.id.btn_book_ticket2);
        Button bookButton3 = card3.findViewById(R.id.btn_book_ticket3);

        // --- Book Ticket button click listeners (with dynamic data) ---
        bookButton1.setOnClickListener(v -> {
            Intent intent = new Intent(MusicActivity.this, BookingActivity.class);
            intent.putExtra("event_title", "Seedhe Maut India Tour 2025 | Indore");
            intent.putExtra("event_details", "Sat, 8 Nov | 9 PM onwards | Phoenix Citadel Mall, Indore");
            intent.putExtra("silver_phase1", 2000);
            intent.putExtra("silver_phase2", 2500);
            intent.putExtra("gold_phase1", 3000);
            intent.putExtra("gold_phase2", 3500);
            intent.putExtra("bronze_phase1", 1249);
            intent.putExtra("bronze_phase2", 1549);
            startActivity(intent);
        });

        bookButton2.setOnClickListener(v -> {
            Intent intent = new Intent(MusicActivity.this, BookingActivity.class);
            intent.putExtra("event_title", "Sunidhi Chauhan | Indore");
            intent.putExtra("event_details", "Fri, 21 Nov | 6 PM onwards | The Park, Indore");
            intent.putExtra("silver_phase1", 3200);
            intent.putExtra("silver_phase2", 3600);
            intent.putExtra("gold_phase1", 4200);
            intent.putExtra("gold_phase2", 4800);
            intent.putExtra("bronze_phase1", 2499);
            intent.putExtra("bronze_phase2", 2800);
            startActivity(intent);
        });

        bookButton3.setOnClickListener(v -> {
            Intent intent = new Intent(MusicActivity.this, BookingActivity.class);
            intent.putExtra("event_title", "Karan Aujla World Tour | Indore");
            intent.putExtra("event_details", "Sun, 7 Dec | 8 PM onwards | Essentia, Indore");
            intent.putExtra("silver_phase1", 7499);
            intent.putExtra("silver_phase2", 8749);
            intent.putExtra("gold_phase1", 10000);
            intent.putExtra("gold_phase2", 13000);
            intent.putExtra("bronze_phase1", 5999);
            intent.putExtra("bronze_phase2", 6499);
            startActivity(intent);
        });


        // --- Get references to save/bookmark buttons ---
        ImageButton btnSave1 = card1.findViewById(R.id.btn_save1);
        ImageButton btnSave2 = card2.findViewById(R.id.btn_save2);
        ImageButton btnSave3 = card3.findViewById(R.id.btn_save3);

        // --- Bookmark toggle setup ---
        setupBookmarkToggle(btnSave1);
        setupBookmarkToggle(btnSave2);
        setupBookmarkToggle(btnSave3);

        // --- Optional: click on full card to open MusicDetailActivity ---
        View.OnClickListener openDetail = v -> {
            Intent intent = new Intent(MusicActivity.this, MusicDetailActivity.class);
            startActivity(intent);
        };

        card1.setOnClickListener(openDetail);
        card2.setOnClickListener(openDetail);
        card3.setOnClickListener(openDetail);
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


