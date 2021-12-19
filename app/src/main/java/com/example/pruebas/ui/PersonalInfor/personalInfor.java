package com.example.pruebas.ui.PersonalInfor;



import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pruebas.AgregarRegistroActivity;
import com.example.pruebas.DatabaseHelper;
import com.example.pruebas.R;
import com.example.pruebas.databinding.FragmentHomeBinding;
import com.example.pruebas.ui.home.HomeViewModel;
import com.example.pruebas.viewModel.Userviewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class personalInfor extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView txtNombre,txtAp1,txtAp2,txtDni,txtUsername,txtId,txtCorreo,txtUltimaConexion;

    FloatingActionButton cambiarFoto;
    DatabaseHelper databaseHelper;

    /*pruebas*/
    //Permiso de la clase Constants
    private  static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    //selección de imagen Constants
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;
    // matrices de permisos
    private String[] cameraPermissions; // cámara y almacenamiento
    private String [] storagePermissions;// solo almacenamiento
    // variables (constain datos para guardar)
    private Uri imageUri;
    /*------*/






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View root = FragmentHomeBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_personal_infor, container, false);
        txtNombre=root.findViewById(R.id.txtNombre);
        txtAp1= root.findViewById(R.id.txtAp1);
        txtAp2= root.findViewById(R.id.txtAp2);
        txtDni= root.findViewById(R.id.txtDni);
        txtId= root.findViewById(R.id.txtId);
        txtUsername= root.findViewById(R.id.txtUsername);
        txtCorreo= root.findViewById(R.id.txtCorreo);
        cambiarFoto=root.findViewById(R.id.fab);
    txtUltimaConexion= root.findViewById(R.id.txtUltimaConexion);
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy, HH:mm:ss", Locale.getDefault());
        String curdate=sdf.format(new Date());

        Userviewmodel umodel = new ViewModelProvider(requireActivity()).get(Userviewmodel.class);
        String resultado1= umodel.getUser().getValue().getIdUser();
        Userviewmodel emodel= new ViewModelProvider(requireActivity()).get(Userviewmodel.class);
        String nombre= emodel.getEmpleado().getValue().getNombre();
        String ap1= emodel.getEmpleado().getValue().getApellido1();
        String ap2= emodel.getEmpleado().getValue().getApellido2();
        String dni=emodel.getEmpleado().getValue().getDni();
        String username=emodel.getEmpleado().getValue().getUsername();
        String correo=emodel.getEmpleado().getValue().getCorreo();
        txtUltimaConexion.setText(curdate);



        /*Establecemos los valores almacenados en el viewmodel*/
        txtId.setText(resultado1);
        txtAp1.setText(ap1);
        txtAp2.setText(ap2);
        txtDni.setText(dni);
        txtNombre.setText(nombre);
        txtUsername.setText(username);
        txtCorreo.setText(correo);

        cambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AgregarRegistroActivity.class));


            }

        });






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}