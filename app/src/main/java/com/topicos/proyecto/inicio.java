package com.topicos.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class inicio extends AppCompatActivity implements  View.OnClickListener{

    Button ingreso;
    EditText usr, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        TextView t=(TextView)findViewById(R.id.reg);

        usr=(EditText)findViewById(R.id.user);
        pwd=(EditText)findViewById(R.id.pass);
        ingreso=(Button)findViewById(R.id.bingre);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(inicio.this, Registro.class);
                startActivity(in);
            }
        });

       /* ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), navega.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });*/





    }
    public void consulta(View view) {
        sqlLite admin = new sqlLite(this, "proyectoDesMov", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String numc = usr.getText().toString();
        String con = pwd.getText().toString();

        Cursor f=db.rawQuery("select id from usuario where id="+numc,null);

       // Cursor fila = db.rawQuery("select ID from usuario where CORREO=" + numc , null);
        Cursor fila1 = db.rawQuery("select cont from usuario where id=" + numc , null);

        if (f.moveToFirst()) {
            if (fila1.moveToFirst())
            {
                String a1 =f.getString(0);
                String a2=fila1.getString(0);

                if(a1.equals(numc)) {
                    if (a2.equals(con)){
                       ChooseAccount.setControl(usr.getText().toString());
                        Bundle bundle=new Bundle();
                        bundle.putString("variable",numc);
                       //Intent intent = new Intent(getApplicationContext(), Registro.class);
                       Intent intent= new Intent(inicio.this, MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show();
                        pwd.setText("");
                    }
                }else{
                    Toast.makeText(this, "El usuario es incorrecto", Toast.LENGTH_SHORT).show();
                   pwd.setText("");
                }
            }
        }else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
            //contra.setText("");
            db.close();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
