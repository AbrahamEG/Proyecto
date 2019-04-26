package com.topicos.proyecto;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText no, ap, am, co, cont, contC;
    Button acep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        no = (EditText) findViewById(R.id.nomR);
        ap = (EditText) findViewById(R.id.nomR);
        am = (EditText) findViewById(R.id.nomR);
        co = (EditText) findViewById(R.id.nomR);
        cont = (EditText) findViewById(R.id.nomR);
        contC = (EditText) findViewById(R.id.nomR);



    }

        @Override
        public void onClick (View v){
            acep = (Button) findViewById(R.id.btnAceR);
            acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    altaclase(v);
                }
            });
        }

        public void altaclase (View view)
        {
            sqlLite admin = new sqlLite(this, "proyecto", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String nom = no.getText().toString();
            String app = ap.getText().toString();
            String apm = am.getText().toString();
            String cor = co.getText().toString();
            String con = cont.getText().toString();
            String con1 = contC.getText().toString();
            ContentValues registro = new ContentValues();

            if (nom.isEmpty() || app.isEmpty() || apm.isEmpty() || con.isEmpty() || con1.isEmpty() || cor.isEmpty()) {
                Toast.makeText(this, "Hay campos incorrectos o vacios", Toast.LENGTH_SHORT).show();
            } else if (!(con.equals(con1))) {
                Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
            } else if (!(cor.isEmpty())) {
                Cursor cursor = db.rawQuery("select id from usuario where id=" + cor, null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "El usuario: " + cor + " actualmente esta en uso", Toast.LENGTH_SHORT).show();
                } else {
                   // registro.put("id", 5);
                    registro.put("nombre", nom);
                    registro.put("app", app);
                    registro.put("apm", apm);
                    registro.put("correo", cor);
                    registro.put("contraseña", con);
                    db.insert("usuario", null, registro);
                    db.close();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(true);
                    builder.setTitle("Registro Exitoso");
                    builder.setMessage("\nTu usuario para ingresar al sistema es:\n" + cor);
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Intent ListFrutas = new Intent(getApplicationContext(), MainActivity.class);
                            //startActivity(ListFrutas);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    Toast.makeText(this, "si quedo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Campo: NUMERO DE CONTROL se encuentra vacio", Toast.LENGTH_SHORT).show();
            }
        }

    }
