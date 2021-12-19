package com.example.pruebas.ui.home;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.pruebas.DatabaseHelper;
import com.example.pruebas.LoginActivity;
import com.example.pruebas.MenuPrincipal;
import com.example.pruebas.R;
import com.example.pruebas.databinding.FragmentHomeBinding;
import com.example.pruebas.viewModel.Userviewmodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView txtrestante,txtSalida,salida,date;
    Button btnNoTrabajar;
    String fentrada,fsalida,IdProyecto, dia;
    CountDownTimer cuentaAtras;
    EditText edtIdProyecto;
    DatabaseHelper databaseHelper;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
    String curdate=sdf.format(new Date());






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnTrabajar= root.findViewById(R.id.btnTrabajar);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        btnNoTrabajar= root.findViewById(R.id.btnNoTrabajar);
        TextView textView= root.findViewById(R.id.txtHoraEntrada);
         txtrestante= root.findViewById(R.id.txtRestante);
         txtSalida= root.findViewById(R.id.txtSalida);
         salida= root.findViewById(R.id.salida);
         date= root.findViewById(R.id.date);
       edtIdProyecto= root.findViewById(R.id.edtIdProyecto);
       date.setText(curdate);
        SimpleDateFormat stf= new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String curtimeentrada=stf.format(new Date());
        Context context;

        /*Viewmodels de los usuario que devuelve el id del usuario*/

        Userviewmodel umodel = new ViewModelProvider(requireActivity()).get(Userviewmodel.class);
       String resultado1= umodel.getUser().getValue().getIdUser();

        /*--------------*/

        btnTrabajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textView.setText(currentDateTimeString);
                textView.setText(curtimeentrada);
               iniciarCuenta();
                btnTrabajar.setVisibility(View.GONE);
                btnNoTrabajar.setVisibility(View.VISIBLE);


            }

        });


btnNoTrabajar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        txtSalida.setVisibility(View.VISIBLE);
        salida.setVisibility(View.VISIBLE);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        SimpleDateFormat stf= new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String curtime=stf.format(new Date());
        txtSalida.setText(curtime);
        btnNoTrabajar.setVisibility(View.GONE);
        btnTrabajar.setVisibility(View.VISIBLE);
        fsalida= txtSalida.getText().toString();
        fentrada= textView.getText().toString();
        dia=date.getText().toString();
        cuentaAtras.cancel();
        IdProyecto= edtIdProyecto.getText().toString();
        databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.fichar(resultado1,dia,fentrada,fsalida,IdProyecto);
        AlertDialog.Builder confirmacion = new AlertDialog.Builder(getActivity());
        confirmacion.setTitle("Confirmación");
        confirmacion.setMessage("Se han insertado los siguientes datos en la base:"+"\n"+"Dia:"+dia +"\n"+"Hora de entrada:" + fentrada  +"\n"+"Hora de salida:" + fsalida+"\n"+"ID del proyecto:" + IdProyecto);

        final MediaPlayer mp= MediaPlayer.create(getActivity(),R.raw.ringtone);
        mp.start();

        confirmacion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int i) {
            }
        });

        confirmacion.show();


    }
});

        return root;
    }
    /*Metodo que establece el tiempo de trabajo e inicia la cuenta atras*/
    private void iniciarCuenta(){
        //int hor=7;
        int min=(0*60)*1000;
        int seg=10*1000;
        long valor=/*hor*/ + min + seg;

         cuentaAtras= new CountDownTimer(valor,1000) {

            @Override

            public void onTick(long l) {
            long tiempo= l/1000;
            int minutos= (int) (tiempo/60);
            long segundos= tiempo % 60;
            String minutos_mostrar= String.format("%02d",minutos);
            String segundos_mostrar=String.format("%02d",segundos);
           // String horas_mostrar= String.format("%02d",horas);


                txtrestante.setText(""+minutos_mostrar+" : "+segundos_mostrar);
            }

            @Override
            public void onFinish() {
        txtrestante.setText("00:00");

                AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
        alerta.setMessage("Ya han finalizado sus horas de trabajo, recuerde fichar en la salida.");
        alerta.setTitle("Recordatorio de fichar");
        final MediaPlayer mp= MediaPlayer.create(getActivity(),R.raw.ringtone);
        mp.start();

        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int i) {
               String texto="Hasta la proxima!";
               Toast.makeText(getActivity(),texto, Toast.LENGTH_SHORT).show();

            }
            });
        alerta.show();
        }

        }.start();


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}