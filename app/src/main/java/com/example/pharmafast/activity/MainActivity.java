package com.example.pharmafast.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.pharmafast.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        onOptionsItemSelected(bottomNavigationView);
    }
    public void onOptionsItemSelected(@NonNull BottomNavigationView item) {
        item.setOnItemSelectedListener(v -> {
            int itemId = v.getItemId();
            if (itemId == R.id.Home) {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
            }
            return false;
        });
    }

}