package com.topicos.proyecto;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class cancelaReservacion extends Fragment {
    String texto;
    EditText idCr;
    Button enviar, cancel, regre;

    public cancelaReservacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cancela_reservacion, container, false);


        idCr=(EditText)view.findViewById(R.id.idCR);
        String idcancela=idCr.getText().toString();
        final ListView l=(ListView)view.findViewById(R.id.listCR) ;
        enviar=(Button)view.findViewById(R.id.btnRR);
        regre=(Button)view.findViewById(R.id.btnRRe);
        cancel=(Button)view.findViewById(R.id.btnCR);
        enviar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Cursor cursor = getRegistrosProductos();
        ArrayList<String> reg =getPROD(cursor);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,reg);
        l.setAdapter(adaptador);
    }
    });

       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(idCr.getText().toString()==""){
                   Toast.makeText(getContext(), "Llene el campo", Toast.LENGTH_SHORT).show();
               } else {
                   Cursor cursor=getEliminar();
                   ArrayList<String> reg =getPROD(cursor);
                   ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,reg);
                   l.setAdapter(adaptador);

                   Toast.makeText(getContext(), "Registro Eliminado", Toast.LENGTH_SHORT).show();
                   Intent in= new Intent(getContext(),menu.class);
                   startActivity(in);
               }

           }
       });




        return view;
    }

    public Cursor getRegistrosProductos(){
        String i=idCr.getText().toString();
        if (i.isEmpty()){
            Toast.makeText(getContext(), "Campo vacio", Toast.LENGTH_SHORT).show();
        }

            sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            return db.rawQuery("SELECT * FROM reservaciones where id=" + i, null);

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
                item+="Hora: "+cur.getString(9)+"\r\n";
                listData.add(item);
                item="";
            }while (cur.moveToNext());
        }
        return listData;
    }

    public Cursor  getEliminar(){
        String i=idCr.getText().toString();
        //int entero = Integer.parseInt(i2);
        sqlLite admin = new sqlLite(getContext(), "proyectoDesMovF1", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        return db.rawQuery("DELETE FROM reservaciones where id="+i,null);
    }

}
