package com.example.pruebas.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebas.User;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<User> user= new MutableLiveData<User> ();

    public void setUser(User userID){
        user.setValue(userID);

    }


    public LiveData<User> getUser() {
        return user;
    }
}
