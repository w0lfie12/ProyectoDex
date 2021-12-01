package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class ProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(ProgressBar.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}