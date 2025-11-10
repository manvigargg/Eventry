package com.example.eventry;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class SuccessActivity extends AppCompatActivity {

    TextView successTitle, successMessage, bookingIdText;
    ImageView tickIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        // Initialize views
        successTitle = findViewById(R.id.successTitle);
        successMessage = findViewById(R.id.successMessage);
        bookingIdText = findViewById(R.id.bookingId);
        tickIcon = findViewById(R.id.tickIcon);

        // Get data from PaymentActivity
        String eventTitle = getIntent().getStringExtra("event_title");
        double totalPaid = getIntent().getDoubleExtra("amount_paid", 0.0);

        // Set title and dynamic message
        successTitle.setText("Your Tickets are Booked!");
        successMessage.setText("You’re all set for " + eventTitle +
                "\nTotal Paid: ₹" + String.format("%.2f", totalPaid));

        // Generate random booking ID
        String bookingId = generateBookingId();
        bookingIdText.setText("Booking ID: " + bookingId);

        // ✅ Animate tick (scale up)
        tickIcon.setScaleX(0f);
        tickIcon.setScaleY(0f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tickIcon, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tickIcon, "scaleY", 0f, 1f);

        scaleX.setDuration(600);
        scaleY.setDuration(600);

        scaleX.start();
        scaleY.start();
    }

    // Helper: generate random booking ID
    private String generateBookingId() {
        Random random = new Random();
        int id = 100000 + random.nextInt(900000);
        return "EVT" + id;
    }
}
