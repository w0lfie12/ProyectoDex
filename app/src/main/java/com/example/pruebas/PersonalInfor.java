package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;



import org.w3c.dom.Text;

public class PersonalInfor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infor);
        TextView nombre= findViewById(R.id.textView12);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String name = b.getString("name");
        nombre.setText(name);

    }

}