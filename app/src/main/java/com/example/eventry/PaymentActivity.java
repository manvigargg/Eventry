package com.example.eventry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private TextView orderAmount, gstAmount, bookingFee, grandTotal;
    private EditText upiInput, cardNumber, expiryDate, cvv, couponInput;
    private Spinner netBankingSpinner;
    private Button payNowBtn, applyCouponBtn;

    private double totalAmount = 0.0;
    private double gst = 0.0;
    private double fee = 49.0;
    private double grand = 0.0;
    private boolean couponApplied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Bind views
        orderAmount = findViewById(R.id.orderAmount);
        gstAmount = findViewById(R.id.gstAmount);
        bookingFee = findViewById(R.id.bookingFee);
        grandTotal = findViewById(R.id.grandTotal);
        upiInput = findViewById(R.id.upiInput);
        cardNumber = findViewById(R.id.cardNumber);
        expiryDate = findViewById(R.id.expiryDate);
        cvv = findViewById(R.id.cvv);
        couponInput = findViewById(R.id.couponInput);
        applyCouponBtn = findViewById(R.id.applyCouponBtn);
        netBankingSpinner = findViewById(R.id.netBankingSpinner);
        payNowBtn = findViewById(R.id.payNowBtn);

        // 🔹 Retrieve dynamic amount from previous activity
        Intent intent = getIntent();
        totalAmount = intent.getDoubleExtra("total_amount", 0.0);
        String title = intent.getStringExtra("event_title");

        // Debug toast (optional, to confirm)
        Toast.makeText(this, "Amount Received: ₹" + totalAmount, Toast.LENGTH_SHORT).show();

        // 🔹 Calculate GST + Booking Fee + Total
        gst = totalAmount * 0.18;
        grand = totalAmount + gst + fee;

        // 🔹 Display properly formatted values
        orderAmount.setText(String.format("Order Amount: ₹ %.2f", totalAmount));
        gstAmount.setText(String.format("GST (18%%): ₹ %.2f", gst));
        bookingFee.setText(String.format("Booking Fee: ₹ %.2f", fee));
        grandTotal.setText(String.format("Grand Total: ₹ %.2f", grand));

        // 🔹 Setup bank dropdown
        String[] banks = {"Select Bank", "Kotak Mahindra Bank", "HDFC Bank", "ICICI Bank", "SBI", "Axis Bank"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, banks);
        netBankingSpinner.setAdapter(adapter);

        // 🔹 Coupon apply
        applyCouponBtn.setOnClickListener(v -> {
            String code = couponInput.getText().toString().trim();
            if (couponApplied) {
                Toast.makeText(this, "Coupon already applied!", Toast.LENGTH_SHORT).show();
            } else if (code.equalsIgnoreCase("DISCOUNT50")) {
                grand *= 0.5;
                grandTotal.setText(String.format("Grand Total: ₹ %.2f", grand));
                Toast.makeText(this, "Coupon applied! 50% off 🎉", Toast.LENGTH_SHORT).show();
                couponApplied = true;
            } else if (code.isEmpty()) {
                Toast.makeText(this, "Please enter a coupon code", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid coupon code", Toast.LENGTH_SHORT).show();
            }
        });

        // 🔹 Pay Now logic
        payNowBtn.setOnClickListener(v -> {
            String upi = upiInput.getText().toString().trim();
            String card = cardNumber.getText().toString().trim();
            String bank = netBankingSpinner.getSelectedItem().toString();

            // One method required
            if (upi.isEmpty() && card.isEmpty() && (bank.equals("Select Bank") || bank.isEmpty())) {
                Toast.makeText(this, "Please select at least one payment method", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate UPI if entered
            if (!upi.isEmpty() && !upi.matches("^[\\w.-]+@[\\w.-]+$")) {
                Toast.makeText(this, "Invalid UPI ID (e.g. name@bank)", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Processing payment...", Toast.LENGTH_SHORT).show();

            // Go to success page
            Intent successIntent = new Intent(PaymentActivity.this, SuccessActivity.class);
            successIntent.putExtra("event_title", title);
            successIntent.putExtra("amount_paid", grand);
            startActivity(successIntent);
            finish();
        });
    }
}

