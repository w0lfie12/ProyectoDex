package com.example.pruebas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.content.Intent;

import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.pruebas.ui.Tarjeta.AESCryptt;
import com.example.pruebas.ui.home.HomeFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;
    DatabaseHelper databaseHelper;
    EncryptPass EncryptPass= new EncryptPass();
    String pass,user,encoded;
    String password="KeyMustBe16ByteOR24ByteOR32ByT5!";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        databaseHelper = new DatabaseHelper(LoginActivity.this);
        AlertDialog.Builder errorr= new AlertDialog.Builder(LoginActivity.this);
        errorr.setTitle("Error");
        errorr.setMessage("Usuario o contraseña incorrectos.");
                errorr.setPositiveButton("Aceptar", null);
        AlertDialog error= errorr.create();


        AlertDialog.Builder vacio= new AlertDialog.Builder(LoginActivity.this);
        vacio.setTitle("Error");
        vacio.setMessage("Los campos no pueden estar vacios");
        vacio.setPositiveButton("Aceptar", null);
        AlertDialog empty= vacio.create();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user= edtUsername.getText().toString();
            pass=edtPassword.getText().toString();
                try {
                     encoded= EncryptPass.encriptar(pass,password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                boolean isExist = databaseHelper.checkUserExist(user, pass);

                if (edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    empty.show();
                }
                   else if(isExist == true){

                        Intent intent= new Intent(LoginActivity.this, ProgressBar.class);
                    String name=edtUsername.getText().toString();
                    Intent intentt=new Intent(LoginActivity.this,MenuPrincipal.class);
                    Bundle b = new Bundle();
                    User user= databaseHelper.obtenerIdUsername(edtUsername.getText().toString());

                  //  intentt.putExtra("name", name);
                    b.putSerializable("com.example.pruebas.User", (Serializable) user);
                    intentt.putExtras(b);
                    /*PRUEBAS*/
                    //databaseHelper.id(edtUsername.getText().toString());

                    Empleado empleado=databaseHelper.nombre(edtUsername.getText().toString());
                    Bundle c= new Bundle();
                    c.putSerializable("com.example.pruebas.Empleado",(Serializable) empleado);
                    intentt.putExtras(c);


                    /*------*/
                    startActivity(intentt);




                    //startActivity(intentt);
                    String text= "Bienvenido de nuevo "+ edtUsername.getText().toString();
                    /*Pasar datos de los usuarios*/
                  Bundle bundle= new Bundle();
                    bundle.putSerializable("com.example.pruebas.User",(Serializable) user);
                    Intent i = new Intent(LoginActivity.this, MenuPrincipal.class);
                   // i.putParcelableArrayListExtra("Lista",userInfo);
                    i.putExtras(bundle);

                    /*poner datos de los empleados*/
                    Bundle bundleEmpleado= new Bundle();
                    bundleEmpleado.putSerializable("com.example.pruebas.Empleado",(Serializable) empleado);
                    i.putExtras(bundleEmpleado);
                    startActivity(i);
                    /*----------------------------*/

                      //  intent.putExtra("username", edtUsername.getText().toString());
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,text, Toast.LENGTH_SHORT).show();

                    } else {
                       edtUsername.setText(null);
                        edtPassword.setText(null);
                       //Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                       error.show();
                    }
                }
            });
        };


    }
