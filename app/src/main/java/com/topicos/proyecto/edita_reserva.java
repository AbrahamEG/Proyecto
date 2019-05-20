package com.topicos.proyecto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;


public class edita_reserva extends Fragment {

    String codigo;
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
        guarda=(Button)view.findViewById(R.id.btnGuarda);
        buscar=(Button)view.findViewById(R.id.buttonBus);

        tel.setText(codigo);

        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return view;
    }

    public void modificar(View view)
    {
        sqlLite admin = new sqlLite(getContext(),"proyectoDesMovF1",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n = nom.getText().toString();
        String appp = app.getText().toString();
        String appm = apm.getText().toString();
        String id = tel.getText().toString();
        String corre=correo.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("sexo", res.getSelectedItem().toString());
        registro.put("nombre", n);
        registro.put("app",appp);
        registro.put("apm",appm);
        registro.put("correo",corre);

        int cant = db.update("usuario",registro, "id="+id ,null);

        db.close();

        if(cant==1)
        {
            Toast.makeText(getContext(), "Perfil modificado exitosamente",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getContext(), "No se pudo realizar la modificacion",Toast.LENGTH_SHORT).show();
        }



        db.close();

    }



}
