package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        // --- Get references to cards ---
        FrameLayout card7 = findViewById(R.id.card7);
        FrameLayout card8 = findViewById(R.id.card8);
        FrameLayout card9 = findViewById(R.id.card9);

        // --- Get references to book ticket buttons ---
        Button bookButton1 = card7.findViewById(R.id.btn_book_ticket);
        Button bookButton2 = card8.findViewById(R.id.btn_book_ticket2);
        Button bookButton3 = card9.findViewById(R.id.btn_book_ticket3);

        // --- Book Ticket button click listeners (with dynamic data) ---
        bookButton1.setOnClickListener(v -> {
            Intent intent = new Intent(SportsActivity.this, BookingActivity1.class);
            intent.putExtra("event_title", "India v/s Pakistan 2nd Test Match");
            intent.putExtra("event_details", "Sat, 22 Nov | 9 AM onwards | Holkar Stadium, Indore");
            intent.putExtra("pavilion",3499 );
            intent.putExtra("stand_phase1", 1200);
            intent.putExtra("stand_phase2", 1649);
            intent.putExtra("gallery", 700);
            startActivity(intent);
        });

        bookButton2.setOnClickListener(v -> {
            Intent intent = new Intent(SportsActivity.this, BookingActivity1.class);
            intent.putExtra("event_title", "RCB v/s MI, IPL 2025");
            intent.putExtra("event_details", "Sun, 16 Nov | 10 AM onwards | Holkar Stadium, Indore");
            intent.putExtra("pavilion", 8799);
            intent.putExtra("stand_phase1", 3549);
            intent.putExtra("stand_phase2", 4999);
            intent.putExtra("gallery", 1999);
            startActivity(intent);
        });

        bookButton3.setOnClickListener(v -> {
            Intent intent = new Intent(SportsActivity.this, BookingActivity1.class);
            intent.putExtra("event_title", "NGTA- December Week2 UTR Matchplay");
            intent.putExtra("event_details", "Sun, 14 Dec | 1 PM onwards | Tennis Club, Indore");
            intent.putExtra("pavilion", 1660);
            intent.putExtra("stand_phase1", 750);
            intent.putExtra("stand_phase2", 1049);
            intent.putExtra("gallery", 349);
            startActivity(intent);
        });


        // --- Get references to save/bookmark buttons ---
        ImageButton btnSave1 = card7.findViewById(R.id.btn_save1);
        ImageButton btnSave2 = card8.findViewById(R.id.btn_save2);
        ImageButton btnSave3 = card9.findViewById(R.id.btn_save3);

        // --- Bookmark toggle setup ---
        setupBookmarkToggle(btnSave1);
        setupBookmarkToggle(btnSave2);
        setupBookmarkToggle(btnSave3);

        // --- Optional: click on full card to open MusicDetailActivity ---
        View.OnClickListener openDetail = v -> {
            Intent intent = new Intent(SportsActivity.this, SportsDetailActivity.class);
            startActivity(intent);
        };

        card7.setOnClickListener(openDetail);
        card8.setOnClickListener(openDetail);
        card9.setOnClickListener(openDetail);
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
