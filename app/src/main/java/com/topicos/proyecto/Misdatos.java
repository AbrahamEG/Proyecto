package com.topicos.proyecto;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Misdatos extends Fragment {
    String texto;

    EditText nom,app,apm,sex,correo,tel;


    public Misdatos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_misdatos, container, false);

        nom = (EditText) view.findViewById(R.id.txtNom);
        app = (EditText) view.findViewById(R.id.txtApp);
        apm=(EditText) view.findViewById(R.id.txtApm);
        sex=(EditText) view.findViewById(R.id.txtSexo);
        correo=(EditText) view.findViewById(R.id.txtCorreo);
        tel=(EditText) view.findViewById(R.id.txtTel);
        texto = getArguments().getString("textFromActivityB");

        consulta(view);
        return view;
    }

    public void consulta(View v) {
        {
            //texto = getArguments().getString("textFromActivityB");
            sqlLite admin = new sqlLite(getContext(), "proyectoDesMov", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor fila = db.rawQuery("select nombre, app, apm,tel, sexo, correo from usuario where id="+ "12345", null);
            if (fila.moveToFirst()) {
                nom.setText(fila.getString(0));
                app.setText(fila.getString(1));
                apm.setText(fila.getString(2));
                tel.setText(fila.getString(3));
                sex.setText(fila.getString(4));
                correo.setText(fila.getString(5));

            }else {
                Toast.makeText(getContext(), "No existe ningún dato", Toast.LENGTH_SHORT).show();
                db.close();
            }

        }

    }

    public interface OnFragmentInteractionListener {
    }
}
