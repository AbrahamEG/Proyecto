package com.topicos.proyecto;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class listar extends Fragment {

    String texto;
    TextView nom,app,apm,tel,correo;
   TextView comtari;
    TextView res;
    TextView cali;
    Button enviar;


    public listar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_listar, container, false);

      /*  nom=(TextView) view.findViewById(R.id.t1);
        apm=(TextView)view.findViewById(R.id.t2);
        app=(TextView)view.findViewById(R.id.t3);
        tel=(TextView)view.findViewById(R.id.t4);
        correo=(TextView)view.findViewById(R.id.t5);
        res=(TextView)view.findViewById(R.id.t6);
        comtari=(TextView)view.findViewById(R.id.t7);
        cali=(TextView)view.findViewById(R.id.t8);
        tel.setText(texto);*/
        texto = getArguments().getString("textFromActivityB");
        ListView l=(ListView)view.findViewById(R.id.list) ;

        Cursor cursor = getRegistrosProductos();
        ArrayList<String> reg =getPROD(cursor);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,reg);
        l.setAdapter(adaptador);

        //consulta(view);

        return view;
    }
    public void consulta(View v) {
        {
            //texto=getString(0);
            texto = getArguments().getString("textFromActivityB");
            sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor fila = db.rawQuery("select nombre, app, apm,tel, correo, restaurante, comentario,calificacion from comentarios where tel="+ texto, null);
            if (fila.moveToFirst()) {
                nom.setText(fila.getString(0));
                app.setText(fila.getString(1));
                apm.setText(fila.getString(2));
                tel.setText(fila.getString(3));
                correo.setText(fila.getString(4));
                res.setText(fila.getString(5));
                comtari.setText(fila.getString(6));
                cali.setText(fila.getString(7));
                //cali.setText(fila.getString(8));

            }else {
                Toast.makeText(getContext(), "No existe ningún dato", Toast.LENGTH_SHORT).show();
                db.close();
            }

        }

    }

    public Cursor getRegistrosProductos(){
        sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        return db.rawQuery("SELECT * FROM comentarios where tel="+texto,null);
    }

    public ArrayList<String> getPROD(Cursor cur){
        ArrayList<String> listData=new ArrayList<String>();
        String item="";
        if(cur.moveToFirst()){
            do{
                //item+="ID: ["+cur.getInt(0)+"]\r\n";
                item+="Nombre: "+cur.getString(1)+"\r\n";
                item+="Apellido Paterno: "+cur.getString(2)+"\r\n";
                item+="Apellido Materno: "+cur.getString(3)+"\r\n";
                item+="Telefono: "+cur.getString(4)+"\r\n";
                item+="Correo: "+cur.getString(5)+"\r\n";
                item+="Establecimiento: "+cur.getString(6)+"\r\n";
                item+="Comentario: "+cur.getString(7)+"\r\n";
                item+="Calificación: "+cur.getString(8)+"\r\n";
                listData.add(item);
                item="";
            }while (cur.moveToNext());
        }
        return listData;
    }

}
