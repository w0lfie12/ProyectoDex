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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pruebas.R;
import com.example.pruebas.databinding.FragmentHomeBinding;

import java.text.DateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView txtrestante,txtSalida,salida;
    Button btnNoTrabajar;
    String fentrada,fsalida,htrabajadas;
    CountDownTimer cuentaAtras;

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
        Context context;

        btnTrabajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(currentDateTimeString);
               iniciarCuenta();
                btnTrabajar.setVisibility(View.GONE);
                btnNoTrabajar.setVisibility(View.VISIBLE);
                fentrada= textView.getText().toString();

            }

        });


btnNoTrabajar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        txtSalida.setVisibility(View.VISIBLE);
        salida.setVisibility(View.VISIBLE);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        txtSalida.setText(currentDateTimeString);
        btnNoTrabajar.setVisibility(View.GONE);
        btnTrabajar.setVisibility(View.VISIBLE);
        fsalida= txtSalida.getText().toString();
        cuentaAtras.cancel();
    }
});
        return root;
    }

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