package com.example.pharmafast.view.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pharmafast.R;
import com.example.pharmafast.view.activity.AccountActivity;
import com.example.pharmafast.view.activity.LoginActivity;
import com.example.pharmafast.view.activity.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class BottomNavBarFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton cartButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.bottom_nav_bar, container, false);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main, new BottomNavBarFragment());
        transaction.commit();
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        cartButton = view.findViewById(R.id.Cart);
        bottomNavigationView.setBackground(null);
        onOptionsItemSelected(bottomNavigationView, cartButton);
        return view;
    }
    public void onOptionsItemSelected(@NonNull BottomNavigationView item, FloatingActionButton cart) {
        item.setOnItemSelectedListener(v -> {
            int itemId = v.getItemId();
            if (itemId == R.id.Home) {
                Intent intent = new Intent(this.getContext(), MainActivity.class);
                this.startActivity(intent);
            }
            else if (itemId == R.id.Account){
                // check if user is logged in
                FirebaseAuth auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() == null) {
                    // show login
                    Intent intent = new Intent(this.getContext(), LoginActivity.class);
                    this.startActivity(intent);
                } else {
                    // show user's account
                    Intent intent = new Intent(this.getContext(), AccountActivity.class);
                    this.startActivity(intent);
                }
            }
            return false;
        });

        cart.setOnClickListener(v -> {
            CartFragment cartFragment = new CartFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main, cartFragment).commit();
        });
    }



}