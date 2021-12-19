package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NuevoGasto extends AppCompatActivity {
    Button  btnMenosDias,btnMasDias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_gasto);
        btnMenosDias= findViewById(R.id.btnMenosDias);
        btnMasDias= findViewById(R.id.btnMasDias);

        btnMenosDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NuevoGasto.this,MenosDias.class);
                startActivity(intent);
            }
        });
        btnMasDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NuevoGasto.this,MasDias.class);
                startActivity(intent);

            }
        });
    }
}