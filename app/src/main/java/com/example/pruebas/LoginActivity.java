package com.example.pruebas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;
    DatabaseHelper databaseHelper;



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
                boolean isExist = databaseHelper.checkUserExist(edtUsername.getText().toString(), edtPassword.getText().toString());
                /*--------------Encrypt decrypt--------------*/
               // byte[]MD5Input= edtPassword.getText().toString().getBytes();
               // BigInteger md5Data= null;
             /* md5Data= new BigInteger(1,md5)*/

                /*--------------Encrypt decrypt--------------*/
                if (edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    empty.show();
                }
                   else if(isExist == true){
                        Intent intent= new Intent(LoginActivity.this, ProgressBar.class);
                    String name=edtUsername.getText().toString();
                    Intent intentt=new Intent(LoginActivity.this,PersonalInfor.class);
                    intentt.putExtra("name", name);
                    startActivity(intentt);


                      //  intent.putExtra("username", edtUsername.getText().toString());
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Bienvenido de nuevo" , Toast.LENGTH_SHORT).show();

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
