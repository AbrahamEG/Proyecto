package com.topicos.proyecto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;



public class edita_reserva extends Fragment {

    String texto;
    EditText nom,app,apm,tel,correo;
    TextView Rfecha,Rhora;
    Spinner res, mesa;
    RatingBar cali;
    Button guarda, buscar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_edita_reserva, container, false);

        nom=(EditText) view.findViewById(R.id.Rnom);
        apm=(EditText)view.findViewById(R.id.RApp);
        app=(EditText)view.findViewById(R.id.RApm);
        tel=(EditText)view.findViewById(R.id.RTel);
        correo=(EditText)view.findViewById(R.id.RCorreo);
        res=(Spinner)view.findViewById(R.id.sEstablecimiento);
        mesa=(Spinner)view.findViewById(R.id.sMesas);
        Rfecha=(TextView)view.findViewById(R.id.RFecha);
        Rhora=(TextView)view.findViewById(R.id.RHora);
        tel.setText(texto);
        guarda=(Button)view.findViewById(R.id.btnResevar);
        buscar=(Button)view.findViewById(R.id.buttonBus);

        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return view;
    }


}
