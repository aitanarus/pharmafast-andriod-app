package com.example.pharmafast.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pharmafast.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavBarFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.bottom_nav_bar, container, false);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main, new BottomNavBarFragment());
        transaction.commit();
        return view;
    }

}
