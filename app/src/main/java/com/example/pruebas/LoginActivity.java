package com.example.pruebas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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



                if (edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    empty.show();
                }
                   else if(isExist == true){
                        Intent intent= new Intent(LoginActivity.this, ProgressBar.class);

                      //  intent.putExtra("username", edtUsername.getText().toString());
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "hola", Toast.LENGTH_SHORT).show();

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
