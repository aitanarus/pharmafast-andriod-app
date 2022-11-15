package com.example.pharmafast.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.pharmafast.R;
import com.example.pharmafast.view.fragment.CartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        cartButton = findViewById(R.id.Cart);
        bottomNavigationView.setBackground(null);
        onOptionsItemSelected(bottomNavigationView, cartButton);
    }
    public void onOptionsItemSelected(@NonNull BottomNavigationView item, FloatingActionButton cart) {
        item.setOnItemSelectedListener(v -> {
            int itemId = v.getItemId();
            if (itemId == R.id.Home) {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
            }
            else if (itemId == R.id.Account){
                // check if user is logged in
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    if (auth.getCurrentUser() == null) {
                        // show login
                        Intent intent = new Intent(this, LoginActivity.class);
                        this.startActivity(intent);
                    } else {
                        // show user's account
                        Intent intent = new Intent(this, AccountActivity.class);
                        this.startActivity(intent);
                    }
            }
            return false;
        });

        cart.setOnClickListener(v -> {
            System.out.println("HI i clicked cart");
            CartFragment cartFragment = new CartFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main, cartFragment).commit();
        });
    }

}