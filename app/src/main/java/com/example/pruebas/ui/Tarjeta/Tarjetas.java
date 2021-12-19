package com.example.pruebas.ui.Tarjeta;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebas.R;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.SecretKey;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tarjetas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tarjetas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static SecretKey secretKeyTemp;
    View view;
    //TextView textView;
    RequestQueue requestQueue;
    String informacion;
    TextView Plano;

    private static final String URL1= "http://10.122.27.162:4000/infocards5/user1";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tarjetas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tarjetas.
     */
    // TODO: Rename and change types and number of parameters
    public static Tarjetas newInstance(String param1, String param2) {
        Tarjetas fragment = new Tarjetas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        requestQueue= Volley.newRequestQueue(getActivity());
        // ui



        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);




        }

    }

    public void stringRequest(){
        StringRequest request= new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //textView.setText(response);
                informacion=response;
                //   textView.setText(informacion);

                /*DESENCRIPTAR*/
                String password="KeyMustBe16ByteOR24ByteOR32ByT5!";
                String decoded= null;
                try{
                    decoded= AESCryptt.decrypt(password,informacion);

                    try {
                        //   Bundle Datos=
                        String lastactive_eur,lastactive_int,card_europe, card_international,current_card;
                        TextView lastActive= view.findViewById(R.id.lastActive);
                        ImageView imageView2= view.findViewById(R.id.imageView2);
                        TextView cardNumber= view.findViewById(R.id.cardNumber);

                        TextView curCard= view.findViewById(R.id.curCard);
                        // declare json object
                        JSONObject obj = new JSONObject(decoded);
                        // fetch JSONObject named employee
                        // JSONObject card = obj.getJSONObject("obj");
                        // get credit card information
                        lastactive_eur = obj.getString("lastactive_eur");
                        lastactive_int = obj.getString("lastactive_int");
                        card_europe = obj.getString("card_europe");
                        card_international = obj.getString("card_international");
                        current_card = obj.getString("current_card");
                        lastActive.setText(lastactive_eur);
                        cardNumber.setTextColor(Color.WHITE);
                        curCard.setTextColor(Color.WHITE);
                        cardNumber.setText(card_europe);
                        imageView2.setImageResource(R.drawable.international);
                        curCard.setText("INTERNATIONAL");

                        Switch sw = view.findViewById(R.id.switch1);
                        sw.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(sw.isChecked()){
                                    lastActive.setText(lastactive_eur);

                                    cardNumber.setText(card_europe);
                                    imageView2.setImageResource(R.drawable.international);
                                    curCard.setText("EUROPE");

                                }else{
                                    lastActive.setText(lastactive_int);

                                    cardNumber.setText(card_international);

                                    curCard.setText("INTERNATIONAL");
                                    imageView2.setImageResource(R.drawable.international);

                                }

                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Plano.setText(decoded);
                    System.out.println(decoded);
                }catch(Exception e){
//Plano.setText(e.getMessage());
                    e.printStackTrace();
                }
            }



        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  textView.setText(error.getMessage());
                        System.out.println("ESTE ES EL ERROR");
                        System.out.println(error.getMessage());

                    }
                }
        );
        requestQueue.add(request);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tarjetas, container, false);
        stringRequest();
        return view;

    }

}