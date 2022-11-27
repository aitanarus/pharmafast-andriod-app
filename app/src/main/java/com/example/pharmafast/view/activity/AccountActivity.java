package com.example.pharmafast.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmafast.R;
import com.example.pharmafast.view.fragment.CartFragment;
import com.example.pharmafast.viewmodel.LoginViewModel;
import com.example.pharmafast.viewmodel.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private Button editUserButton;
    private LoginViewModel loginViewModel;
    private ProfileViewModel profileViewModel;

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_account);
        userEmail = findViewById(R.id.editTextTextEmailAddress);
        userPassword = findViewById(R.id.editTextTextPassword);
        editUserButton = findViewById(R.id.editUserBtn);
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = profileViewModel.updateCurrentUser(userEmail.getText().toString(), userPassword.getText().toString());
                System.out.println(msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        cartButton = findViewById(R.id.Cart);
        bottomNavigationView.setBackground(null);
        onOptionsItemSelected(bottomNavigationView, cartButton);

        userEmail.setHint(profileViewModel.getUserEmail());
        userPassword.setHint(profileViewModel.getUserPassword());

    }

    private void checkIfSignedIn() {
       loginViewModel.getCurrentUser().observe(this, user -> {
            if (user == null)
                goToLoginActivity();
        });
    }

    private void goToLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void signOut(View v) {
        loginViewModel.signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
            CartFragment cartFragment = new CartFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main, cartFragment).commit();
        });
    }
}
