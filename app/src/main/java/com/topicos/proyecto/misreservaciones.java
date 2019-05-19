package com.topicos.proyecto;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class misreservaciones extends Fragment {

    String texto;
    TextView nom,app,apm,tel,correo;
    TextView comtari;
    TextView res;
    TextView cali;
    public misreservaciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_misreservaciones, container, false);
        ListView l=(ListView)view.findViewById(R.id.listR) ;

        Cursor cursor = getRegistrosProductos();
        ArrayList<String> reg =getPROD(cursor);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,reg);
        l.setAdapter(adaptador);


        return view;
    }

    public Cursor getRegistrosProductos(){
        sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        return db.rawQuery("SELECT * FROM reservaciones",null);
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
                item+="Fecha: "+cur.getString(7)+"\r\n";
                item+="Hora: "+cur.getString(8)+"\r\n";
                item+="Mesa para: "+cur.getString(9)+"\r\n";
                listData.add(item);
                item="";
            }while (cur.moveToNext());
        }
        return listData;
    }


}
