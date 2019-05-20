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
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class editarPerfil extends Fragment {

    Spinner sex;
    EditText nom,ap,am,tel,correo, sexx;
    Button button;
    String texto;


    public editarPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);


        sex= (Spinner) view.findViewById(R.id.sSex);
        nom=(EditText) view.findViewById(R.id.tNom);
        ap=(EditText) view.findViewById(R.id.tApp);
        am=(EditText) view.findViewById(R.id.tApm);
        tel=(EditText) view.findViewById(R.id.tTel);
        correo=(EditText) view.findViewById(R.id.tCorreo);
        button = (Button) view.findViewById(R.id.bMod);

     consulta(view);

        tel.setText(texto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valida(v);
            }
        });

        final ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(getContext(), R.array.Opciones, android.R.layout.simple_spinner_item);
        sex.setAdapter(adapter);

        return view;
    }


    public void modificar(View view)
    {
        sqlLite admin = new sqlLite(getContext(),"proyectoDesMovF1",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n = nom.getText().toString();
        String appp = ap.getText().toString();
        String id = tel.getText().toString();
        String ammm = am.getText().toString();
        String corre=correo.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("sexo", sex.getSelectedItem().toString());
        registro.put("nombre", n);
        registro.put("app",appp);
        registro.put("apm",ammm);
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

    public void envio(View v) {
        {
            texto = getArguments().getString("textFromActivityB");

            sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
          //  Cursor fila = db.rawQuery("select nombre,app,apm,correo from usuario where id=" + texto , null);
            Cursor fila = db.rawQuery("select nombre, app, apm,tel, sexo, correo from usuario where id="+ texto, null);
            if (fila.moveToFirst()) {
                nom.setText(fila.getString(0));
                ap.setText(fila.getString(1));
                am.setText(fila.getString(2));
                correo.setText(fila.getString(3));
                Cursor fila1 = db.rawQuery("select tel from alumno where id=" + texto, null);
                if (fila1.moveToFirst()) {

                    String aux = fila1.getString(0);
                }else{
                }
            }else {
                Toast.makeText(getContext(), "No existe ningún dato", Toast.LENGTH_SHORT).show();
                db.close();
            }


        }


    }

    public void consulta(View v) {
        {
            //texto=getString(0);
            texto = getArguments().getString("textFromActivityB");
            sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor fila = db.rawQuery("select nombre, app, apm,tel, sexo, correo from usuario where id="+ texto, null);
            if (fila.moveToFirst()) {
                nom.setText(fila.getString(0));
                ap.setText(fila.getString(1));
                am.setText(fila.getString(2));
                tel.setText(fila.getString(3));
                //sex.setText(fila.getString(4));
//                sex.setSelection(Integer.parseInt(fila.getString(4)));
                correo.setText(fila.getString(5));

            }else {
                Toast.makeText(getContext(), "No existe ningún dato", Toast.LENGTH_SHORT).show();
                db.close();
            }

        }

    }


    public void valida(View view){
        ContentValues registro = new ContentValues();

        if(nom.getText().toString().equals("")){
            Toast.makeText(getContext(), "Campo: NOMBRE se encuentra vacio",Toast.LENGTH_SHORT).show();
        }else if(ap.getText().toString().equals("")){
            Toast.makeText(getContext(), "Campo: APELLIDO PATERNO se encuentra vacio",Toast.LENGTH_SHORT).show();
        }else if(am.getText().toString().equals("")){
            Toast.makeText(getContext(), "Campo: APELLIDO MATERNO se encuentra vacio",Toast.LENGTH_SHORT).show();
        }else if(tel.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Campo: NUMERO DE CONTROL se encuentra vacio", Toast.LENGTH_SHORT).show();
        }else if ( sex.getSelectedItem().toString().equals("Semestre:")||sex.getSelectedItem().toString().equals("")){
            Toast.makeText(getContext(), "Campo: SEMESTRE se encuentra vacio",Toast.LENGTH_SHORT).show();
        }else{
            modificar(view);
        }

    }

}
