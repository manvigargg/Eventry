package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {

    int count1 = 0;
    int price1 = 0;

    TextView totalTickets, totalAmount, headerTitle, headerDetails, priceText1;
    LinearLayout bottomCheckout;
    Button btnCheckout;

    Button btnAdd1;
    LinearLayout counterLayout1;
    TextView btnMinus1, btnPlus1, ticketCount1;

    String eventTitle = "", eventDetails = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery); // ✅ correct layout

        // Header
        headerTitle = findViewById(R.id.headerTitle);
        headerDetails = findViewById(R.id.headerDetails);
        priceText1 = findViewById(R.id.price1);

        // Add buttons & counters
        btnAdd1 = findViewById(R.id.btnAdd1);
        counterLayout1 = findViewById(R.id.counterLayout1);
        btnMinus1 = findViewById(R.id.btnMinus1);
        btnPlus1 = findViewById(R.id.btnPlus1);
        ticketCount1 = findViewById(R.id.ticketCount1);

        // Bottom bar
        bottomCheckout = findViewById(R.id.bottomCheckout);
        totalTickets = findViewById(R.id.totalTickets);
        totalAmount = findViewById(R.id.totalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        // ✅ Get event data dynamically
        eventTitle = getIntent().getStringExtra("event_title");
        eventDetails = getIntent().getStringExtra("event_details");
        price1 = getIntent().getIntExtra("price", 0); // ✅ correct key

        // ✅ Set dynamic data
        if (eventTitle != null) headerTitle.setText(eventTitle);
        if (eventDetails != null) headerDetails.setText(eventDetails);
        priceText1.setText("₹" + price1);

        // Hide counters initially
        counterLayout1.setVisibility(View.GONE);

        // Handle Add button → Counter toggle
        btnAdd1.setOnClickListener(v -> {
            btnAdd1.setVisibility(View.GONE);
            counterLayout1.setVisibility(View.VISIBLE);
            count1 = 1;
            ticketCount1.setText(String.valueOf(count1));
            updateBottomBar();
        });

        // Counter actions
        btnMinus1.setOnClickListener(v -> {
            if (count1 > 0) count1--;
            if (count1 == 0) {
                counterLayout1.setVisibility(View.GONE);
                btnAdd1.setVisibility(View.VISIBLE);
            }
            ticketCount1.setText(String.valueOf(count1));
            updateBottomBar();
        });

        btnPlus1.setOnClickListener(v -> {
            count1++;
            ticketCount1.setText(String.valueOf(count1));
            updateBottomBar();
        });

        // Checkout button
        btnCheckout.setOnClickListener(v -> {
            int total = count1;
            int totalPrice = count1 * price1;

            if (total > 0) {
                Toast.makeText(
                        GalleryActivity.this,
                        "Proceeding to payment...",
                        Toast.LENGTH_SHORT
                ).show();

                // ✅ Send data correctly to PaymentActivity
                Intent intent = new Intent(GalleryActivity.this, PaymentActivity.class);
                intent.putExtra("event_title", headerTitle.getText().toString());
                intent.putExtra("total_amount", (double) totalPrice);
                startActivity(intent);
            } else {
                Toast.makeText(
                        GalleryActivity.this,
                        "Please select at least one ticket before checkout.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void updateBottomBar() {
        int total = count1;
        int totalPrice = count1 * price1;

        if (total > 0) {
            bottomCheckout.setVisibility(View.VISIBLE);
            totalTickets.setText(total + " ticket" + (total > 1 ? "s" : ""));
            totalAmount.setText("₹" + totalPrice);
        } else {
            bottomCheckout.setVisibility(View.GONE);
        }
    }
}
