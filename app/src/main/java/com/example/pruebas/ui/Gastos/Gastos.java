package com.example.pruebas.ui.Gastos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.pruebas.NuevoGasto;
import com.example.pruebas.R;
import com.example.pruebas.VerGastos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Gastos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gastos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View root;
    Button btnIntroducirGasto, btnverGastos;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Gastos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Gastos.
     */
    // TODO: Rename and change types and number of parameters
    public static Gastos newInstance(String param1, String param2) {
        Gastos fragment = new Gastos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_gastos, container, false);

        btnIntroducirGasto= root.findViewById(R.id.btnIntroducirGasto);
        btnverGastos= root.findViewById(R.id.btnVerGastos);
        btnIntroducirGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), NuevoGasto.class);
                startActivity(intent);

            }
        });
        btnverGastos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              Intent intent= new Intent(getActivity(), VerGastos.class);
              startActivity(intent);
            }
        });
        return root;
    }

}