package com.topicos.proyecto;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Reservar extends Fragment {
    String texto;
    EditText nom,app,apm,tel,correo;
TextView Rfecha,Rhora;
    Spinner res, mesa;
    RatingBar cali;
    Button reserva;

    public Reservar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_reservar, container, false);

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
        reserva=(Button)view.findViewById(R.id.btnReserva);
        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar(v);
            }
        });


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.places, android.R.layout.simple_spinner_item);
        res.setAdapter(adapter);

        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Mesas, android.R.layout.simple_spinner_item);
       mesa.setAdapter(adapter2);

        consulta(view);

        Rfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment=new PickersActivity();
                newFragment.show(getFragmentManager(),"g");
            }
        });

        Rhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment=new PickersActivity2();
                newFragment.show(getFragmentManager(),"g");
            }
        });

        return view;
    }

    public void consulta(View v) {
        {

            texto = getArguments().getString("textFromActivityB");
            sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor fila = db.rawQuery("select nombre, app, apm,tel, correo from usuario where id="+ texto, null);
            if (fila.moveToFirst()) {
                nom.setText(fila.getString(0));
                app.setText(fila.getString(1));
                apm.setText(fila.getString(2));
                tel.setText(fila.getString(3));
                correo.setText(fila.getString(4));

            }else {
                Toast.makeText(getContext(), "No existe ningún dato", Toast.LENGTH_SHORT).show();
                db.close();
            }

        }

    }

    public void enviar(View view)
    {
        sqlLite admin = new sqlLite(getContext(),"proyectoDesMovF1",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n = nom.getText().toString();
        String ap = app.getText().toString();
        String tele = tel.getText().toString();
        String amm = apm.getText().toString();
        String corre=correo.getText().toString();
        String resta=res.getSelectedItem().toString();
        String fe=Rfecha.getText().toString();
        String ho=Rhora.getText().toString();
        String me=mesa.getSelectedItem().toString();

        ContentValues registro = new ContentValues();

        if (fe.isEmpty()||ho.isEmpty()||n.isEmpty()||ap.isEmpty()||amm.isEmpty()||corre.isEmpty()||tele.isEmpty()) {
            Toast.makeText(getContext(), "Hay campos incorrectos o vacios", Toast.LENGTH_SHORT).show();
        }else {
            registro.put("nombre", n);
            registro.put("app", ap);
            registro.put("apm", amm);
            registro.put("tel", tele);
            registro.put("correo", corre);
            registro.put("restaurante", resta);
            registro.put("fecha", fe);
            registro.put("hora", ho);
            registro.put("mesa",me);
            db.insert("reservaciones", null, registro);

            db.close();

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("Reservacion realizada");
            builder.setMessage("\nGracias por su reservacion: " + n + " " + ap + " " + amm+
                    "\nReservacion para el dia: "+fe+ " A las: "+ho);

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getContext(), menu.class);
                    startActivity(intent);

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

}
