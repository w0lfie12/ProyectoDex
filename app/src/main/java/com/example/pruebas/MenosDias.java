package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MenosDias extends AppCompatActivity {
    EditText txtFecha,txtHora,txtTransporte,txtDistancia,txtPeaje,txtParking,txtDietas,txtIdUser,txtIdProy,txtIdDept;
    FloatingActionButton saveBtn;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
    String curdate=sdf.format(new Date());
    SimpleDateFormat stf= new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    String curtime=stf.format(new Date());
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menos_dias);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtHora = (EditText) findViewById(R.id.txtHora);
        txtTransporte = (EditText) findViewById(R.id.txtTransporte);
        txtDistancia = (EditText) findViewById(R.id.txtDistancia);
        txtPeaje = (EditText) findViewById(R.id.txtPeaje);
        txtParking = (EditText) findViewById(R.id.txtParking);
        txtDietas = (EditText) findViewById(R.id.txtDietas);
        txtIdUser=(EditText) findViewById(R.id.txtIdUser);
        txtIdDept= findViewById(R.id.txtIdDept);
        txtIdProy= findViewById(R.id.txtIdProy);
        txtFecha.setText(curdate);
        txtHora.setText(curtime);
        saveBtn= findViewById(R.id.saveBtn);
        databaseHelper = new DatabaseHelper(this);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double Distancia = Double.parseDouble(txtDistancia.getText().toString());
                Double PagoKm=Distancia * 0.3;
                Double Peaje = Double.parseDouble(txtPeaje.getText().toString());
                Double Parking = Double.parseDouble( txtParking.getText().toString());
                Double Dietas = Double.parseDouble(txtDietas.getText().toString());
                Double Total= PagoKm + Peaje + Parking + Dietas;
                String IdUser= txtIdUser.getText().toString();
                String IdDept= txtIdDept.getText().toString();
                String IdProy= txtIdProy.getText().toString();
                String Fecha= txtFecha.getText().toString();
                String Hora= txtHora.getText().toString();
                String Transporte= txtTransporte.getText().toString();
                String Precio= Total.toString();
                if(Dietas>60){
                    AlertDialog.Builder Error = new AlertDialog.Builder(MenosDias.this);
                    Error.setTitle("Información sobre gasto.");
                    Error.setMessage("¡ERROR! El total de las dietas que se desea introducir es de "+Dietas+", Por loq que mayor que 60€ que es lo establecido por la empresa en viajes dentro de Europa.");

                    final MediaPlayer mp= MediaPlayer.create(MenosDias.this,R.raw.ringtone);
                    mp.start();

                    Error.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                        }
                    });

                    Error.show();

                }else{

                    AlertDialog.Builder confirmacion = new AlertDialog.Builder(MenosDias.this);
                    confirmacion.setTitle("Información sobre gasto.");
                    confirmacion.setMessage("Se va a introducir el siguiente gasto en la base de datos."+"\n"+"Pago por Kilometros: " + PagoKm+"€" + "\n"+ "Peaje: "+ Peaje+"€" + "\n"+ "Parking: "+ Parking+"€" + "\n"+ "Dietas: "+ Dietas+"€"+ "\n"+"El precio total del gasto que se va a introducir es de: "+Precio+"€");

                    final MediaPlayer mp= MediaPlayer.create(MenosDias.this,R.raw.ringtone);
                    mp.start();

                    confirmacion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            //llamar al metodo de introducir los datos en la base.
                            databaseHelper.GastoMenos(IdDept,IdProy,IdUser,Transporte,Fecha,Hora,PagoKm,Peaje,Parking,Dietas,Precio);

                        }
                    });

                    confirmacion.show();
                }



            }
        });

        };


    }
