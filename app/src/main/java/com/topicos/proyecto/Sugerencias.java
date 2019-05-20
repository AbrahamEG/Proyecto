package com.topicos.proyecto;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sugerencias extends Fragment {


    String texto;
    TextView nom,app,apm,tel,correo;
    TextView comtari;
    Spinner res;
    RatingBar cali;
    Button enviar;
    DatabaseReference mDatabase;


    public Sugerencias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_sugerencias, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        nom=(TextView) view.findViewById(R.id.tViewNom);
        apm=(TextView)view.findViewById(R.id.tViewAm);
        app=(TextView)view.findViewById(R.id.tViewAp);
        tel=(TextView)view.findViewById(R.id.tViewTel);
        correo=(TextView)view.findViewById(R.id.tViewCorreo);
        res=(Spinner)view.findViewById(R.id.sRes);
        comtari=(TextView)view.findViewById(R.id.com);
        cali=(RatingBar)view.findViewById(R.id.ratingBar2);
        tel.setText(texto);
        enviar=(Button)view.findViewById(R.id.btnenvia);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviar(v);
                String nomE=nom.getText().toString();
                String amE= apm.getText().toString();
                String apE= app.getText().toString();
                String telE= tel.getText().toString();
                String correoE= correo.getText().toString();
                String comeE= comtari.getText().toString();
                String resE=res.getSelectedItem().toString();
                Float cal=cali.getRating();


                Reseña re= new Reseña(nomE,apE, amE, telE,correoE,comeE,cal,resE);
                mDatabase.child("reseñas").child(telE).setValue(re);
                Toast.makeText(getContext(),"Reseña almacenada",Toast.LENGTH_LONG).show();
            }
        });



        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.places, android.R.layout.simple_spinner_item);
        res.setAdapter(adapter);

        consulta(view);

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
        String comenta=comtari.getText().toString();
        Float ratingNumber = cali.getRating ();
        String calif = Float.toString(ratingNumber);

        ContentValues registro = new ContentValues();

        if (comenta.isEmpty()) {
            Toast.makeText(getContext(), "Hay campos incorrectos o vacios", Toast.LENGTH_SHORT).show();
        }else if (calif.isEmpty()||calif.equals("0")) {
            Toast.makeText(getContext(), "Proporciona una calificacion", Toast.LENGTH_SHORT).show();
        }else if (res.equals(" ")){
            Toast.makeText(getContext(), "Elige un establecimiento",Toast.LENGTH_SHORT).show();
        }else {
            registro.put("nombre", n);
            registro.put("app", ap);
            registro.put("apm", amm);
            registro.put("tel", tele);
            registro.put("correo", corre);
            registro.put("restaurante", resta);
            registro.put("comentario", comenta);
            registro.put("calificacion", calif);
            db.insert("comentarios", null, registro);

            db.close();

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("Comentario Enviado");
            builder.setMessage("\nGracias por su atencion: " + n + " " + ap + " " + amm);

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
