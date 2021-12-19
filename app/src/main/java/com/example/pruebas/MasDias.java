package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pruebas.viewModel.Userviewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MasDias extends AppCompatActivity {
    EditText txtIdDepartamento,txtIdProyecto,txtFinicio,txtFfin,txtContinente,txtPais,txtCiudad,txtDieta,txtIdUsuario;
    Button btnTicket;
    FloatingActionButton saveBtn;
    ImageView ImgTicket;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
    String curdate=sdf.format(new Date());
    DatabaseHelper databaseHelper;
    Integer IdProyecto1,IdDepartamento1,idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_dias);
        txtIdDepartamento= findViewById(R.id.txtIdDepartamento);
        txtIdProyecto= findViewById(R.id.txtIdProyecto);
        txtFinicio= findViewById(R.id.txtFinicio);
        txtFfin= findViewById(R.id.txtFfin);
        txtContinente= findViewById(R.id.txtContinente);
        txtIdUsuario= findViewById(R.id.txtIdUsuario);
        txtPais= findViewById(R.id.txtPais);
        txtCiudad= findViewById(R.id.txtCiudad);
        txtDieta= findViewById(R.id.txtDieta);
        btnTicket=findViewById(R.id.btnTicket);
        saveBtn= findViewById(R.id.saveBtn);
        ImgTicket= findViewById(R.id.ImgTicket);
        txtFfin.setText(curdate);
        databaseHelper = new DatabaseHelper(this);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String IdDepartamento= txtIdDepartamento.getText().toString();
                String IdProyecto=txtIdProyecto.getText().toString();
                String FInicio= txtFinicio.getText().toString();
                String FFin= txtFfin.getText().toString();
                String Continente= txtContinente.getText().toString();
                String Pais= txtPais.getText().toString();
                String Ciudad= txtCiudad.getText().toString();
                String IdUsuario1= txtIdUsuario.getText().toString();
                 IdDepartamento1= Integer.parseInt(IdDepartamento);
                 IdProyecto1= Integer.parseInt(IdProyecto);
                 idUsuario= Integer.parseInt(IdUsuario1);

                Double Dietas = Double.parseDouble(txtDieta.getText().toString());
                Double Total=  Dietas;
                String Precio= Total.toString();
                if(Dietas>60 && Continente.toLowerCase() == "europa"){
                    AlertDialog.Builder Error = new AlertDialog.Builder(MasDias.this);
                    Error.setTitle("Información sobre gasto.");
                    Error.setMessage("¡ERROR! El total de las dietas que se desea introducir es de "+Dietas+ "€"+ ",Por lo que mayor de 60€ que es lo establecido por la empresa en viajes dentro de Europa.");

                    final MediaPlayer mp= MediaPlayer.create(MasDias.this,R.raw.ringtone);
                    mp.start();

                    Error.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                        }
                    });

                    Error.show();

                }else if(Dietas>100){
                    AlertDialog.Builder confirmacion = new AlertDialog.Builder(MasDias.this);
                    confirmacion.setTitle("Información sobre gasto.");
                    confirmacion.setMessage("ERROR! Su gasto es de dietas es de  "+ Dietas + "€, y es mayor de 100€ que es lo establecido por la empresa en viajes fuera de Europa." );

                    final MediaPlayer mp= MediaPlayer.create(MasDias.this,R.raw.ringtone);
                    mp.start();

                    confirmacion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            //llamar al metodo de introducir los datos en la base.
                        }
                    });

                    confirmacion.show();

                } else{
                    AlertDialog.Builder confirmacion = new AlertDialog.Builder(MasDias.this);
                    confirmacion.setTitle("Información sobre gasto.");
                    confirmacion.setMessage("Los siguientes datos se van a introducir a tus gastos en la base de datos: "+ "\n"+ "Id departamento: "+ IdDepartamento+ "\n"+ "IdProyecto: "+ IdProyecto + "\n"+ "Fecha inicio: " + FInicio + "\n" + "Fecha fin: "+ FFin + "\n"+"Continente: "+ Continente + "\n"+ "País: "+ Pais + "\n"+"Ciudad: "+ Ciudad + "\n"+ "Total: "+ Total);

                    final MediaPlayer mp= MediaPlayer.create(MasDias.this,R.raw.ringtone);
                    mp.start();

                    confirmacion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            //llamar al metodo de introducir los datos en la base.
                            databaseHelper.GastoMas(IdProyecto1,IdDepartamento1,IdUsuario1,FInicio,FFin,Continente,Pais,Ciudad,Dietas,Total);

                        }
                    });

                    confirmacion.show();

                }



            }

    });
}
}