package com.example.pharmafast.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.pharmafast.repository.UserRepository;

public class ProfileViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public ProfileViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public String getUserEmail(){
        return userRepository.getUserEmail();
    }

    public String updateCurrentUser(String email, String password) {
       return userRepository.updateCurrentUser(email, password);
    }
}
