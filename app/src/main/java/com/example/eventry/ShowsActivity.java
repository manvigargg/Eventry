package com.example.eventry;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ShowsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);

        Button btnNotify = findViewById(R.id.btnNotify);
        btnNotify.setOnClickListener(v ->
                Toast.makeText(
                        ShowsActivity.this,
                        "✅ You’ll be notified when new shows are available!",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}
