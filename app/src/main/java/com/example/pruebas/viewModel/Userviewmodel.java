package com.example.pruebas.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebas.Empleado;
import com.example.pruebas.User;

public class Userviewmodel extends ViewModel {
private final MutableLiveData<User> user= new MutableLiveData<User> ();
private final MutableLiveData<Empleado> empleado= new MutableLiveData<Empleado>();

  public void setEmpleado(Empleado empleado1) {
     empleado.setValue(empleado1);
  }



    public void setUser(User userID){
      user.setValue(userID);

    }


    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<Empleado> getEmpleado() {
        return empleado;
    }
}
