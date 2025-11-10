package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StandActivity extends AppCompatActivity {

    int count1 = 0, count2 = 0;
    int price1 = 0, price2 = 0;

    TextView totalTickets, totalAmount, headerTitle, headerDetails, priceText1, priceText2;
    LinearLayout bottomCheckout;
    Button btnCheckout;

    Button btnAdd1, btnAdd2;
    LinearLayout counterLayout1, counterLayout2;
    TextView btnMinus1, btnPlus1, btnMinus2, btnPlus2, ticketCount1, ticketCount2;

    String eventTitle = "", eventDetails = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);

        // ---------- Header ----------
        headerTitle = findViewById(R.id.headerTitle);
        headerDetails = findViewById(R.id.headerDetails);
        priceText1 = findViewById(R.id.price1);
        priceText2 = findViewById(R.id.price2);

        // ---------- Add buttons & counters ----------
        btnAdd1 = findViewById(R.id.btnAdd1);
        btnAdd2 = findViewById(R.id.btnAdd2);
        counterLayout1 = findViewById(R.id.counterLayout1);
        counterLayout2 = findViewById(R.id.counterLayout2);

        btnMinus1 = findViewById(R.id.btnMinus1);
        btnPlus1 = findViewById(R.id.btnPlus1);
        ticketCount1 = findViewById(R.id.ticketCount1);

        btnMinus2 = findViewById(R.id.btnMinus2);
        btnPlus2 = findViewById(R.id.btnPlus2);
        ticketCount2 = findViewById(R.id.ticketCount2);

        // ---------- Bottom bar ----------
        bottomCheckout = findViewById(R.id.bottomCheckout);
        totalTickets = findViewById(R.id.totalTickets);
        totalAmount = findViewById(R.id.totalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        // ---------- Intent Data ----------
        eventTitle = getIntent().getStringExtra("event_title");
        eventDetails = getIntent().getStringExtra("event_details");
        price1 = getIntent().getIntExtra("phase1_price", 0);
        price2 = getIntent().getIntExtra("phase2_price", 0);

        // ---------- Set Dynamic Data ----------
        if (eventTitle != null) headerTitle.setText(eventTitle);
        if (eventDetails != null) headerDetails.setText(eventDetails);
        priceText1.setText("₹" + price1);
        priceText2.setText("₹" + price2);

        // Initially hide counters
        counterLayout1.setVisibility(View.GONE);
        counterLayout2.setVisibility(View.GONE);

        // ---------- Handle Add Buttons ----------
        btnAdd1.setOnClickListener(v -> {
            btnAdd1.setVisibility(View.GONE);
            counterLayout1.setVisibility(View.VISIBLE);
            count1 = 1;
            ticketCount1.setText(String.valueOf(count1));
            updateBottomBar();
        });

        btnAdd2.setOnClickListener(v -> {
            btnAdd2.setVisibility(View.GONE);
            counterLayout2.setVisibility(View.VISIBLE);
            count2 = 1;
            ticketCount2.setText(String.valueOf(count2));
            updateBottomBar();
        });

        // ---------- Counter Button Logic ----------
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

        btnMinus2.setOnClickListener(v -> {
            if (count2 > 0) count2--;
            if (count2 == 0) {
                counterLayout2.setVisibility(View.GONE);
                btnAdd2.setVisibility(View.VISIBLE);
            }
            ticketCount2.setText(String.valueOf(count2));
            updateBottomBar();
        });

        btnPlus2.setOnClickListener(v -> {
            count2++;
            ticketCount2.setText(String.valueOf(count2));
            updateBottomBar();
        });

        // ---------- Checkout Button ----------
        btnCheckout.setOnClickListener(v -> {
            int totalTicketsCount = count1 + count2;
            int totalPrice = (count1 * price1) + (count2 * price2);

            if (totalTicketsCount > 0) {
                Toast.makeText(this, "Proceeding to payment...", Toast.LENGTH_SHORT).show();

                // Send data to PaymentActivity
                Intent intent = new Intent(StandActivity.this, PaymentActivity.class);
                intent.putExtra("event_title", eventTitle);
                intent.putExtra("total_amount", (double) totalPrice);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select at least one ticket before checkout.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ---------- Update Bottom Checkout Bar ----------
    private void updateBottomBar() {
        int totalTicketsCount = count1 + count2;
        int totalPrice = (count1 * price1) + (count2 * price2);

        if (totalTicketsCount > 0) {
            bottomCheckout.setVisibility(View.VISIBLE);
            totalTickets.setText(totalTicketsCount + " ticket" + (totalTicketsCount > 1 ? "s" : ""));
            totalAmount.setText("₹" + totalPrice);
        } else {
            bottomCheckout.setVisibility(View.GONE);
        }
    }
}
