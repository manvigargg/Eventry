package com.example.eventry;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ComedyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t = new TextView(this);
        t.setTextSize(20);
        t.setPadding(40, 40, 40, 40);
        String eventTitle = getIntent().getStringExtra("eventTitle");
        t.setText("Details for: " + eventTitle);
        setContentView(t);
    }
}
