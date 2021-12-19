package com.example.pruebas;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.pruebas.viewModel.Userviewmodel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebas.databinding.ActivityMenuPrincipalBinding;

public class MenuPrincipal extends AppCompatActivity {




    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuPrincipalBinding binding;
    User user;
    Empleado empleado;
TextView name, textViewCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Set Username on the menu header*/
        String variables;
        Bundle bundle= getIntent().getExtras();
        user= (User)bundle.getSerializable("com.example.pruebas.User");
        name= findViewById(R.id.name);
//        textViewCorreo.findViewById(R.id.textViewCorreo);
        Bundle bundleEmpleado= getIntent().getExtras();
        empleado=(Empleado) bundleEmpleado.getSerializable("com.example.pruebas.Empleado");
       // String userId= user.getIdUser().toString();
       //Bundle b = getIntent().getExtras();
        /*Viewmodels*/

        Userviewmodel umodel = new ViewModelProvider(this).get(Userviewmodel.class);
        umodel.setUser(user);

        Userviewmodel emodel = new ViewModelProvider(this).get(Userviewmodel.class);

        emodel.setEmpleado(empleado);


        /*--------------*/
        /*por que da error??*/
        //textViewCorreo.setText(emodel.getEmpleado().getValue().getCorreo());
        // name.setText(emodel.getEmpleado().getValue().getNombre() + emodel.getEmpleado().getValue().getApellido1());
        // /*------------------------*/

        binding = ActivityMenuPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenuPrincipal.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.tarjetas)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}