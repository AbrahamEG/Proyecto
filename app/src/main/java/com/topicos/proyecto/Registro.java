package com.topicos.proyecto;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    TextView etCampoNumero;
    Button btValidar,bcancelar;
    EditText nombre, ap, am, cont, cont1, tel, correoo;
    Spinner sex;
    Button registro;
    public static final String REGEX_EMAIL ="([A-Za-z0-9]+@+(gmail|live|hotmail|outlook)+.(com)+)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText) findViewById(R.id.nomR);
        ap = (EditText) findViewById(R.id.appR);
        am = (EditText) findViewById(R.id.apmR);
        tel = (EditText) findViewById(R.id.telR);
        correoo=(EditText)findViewById(R.id.correoR);
        cont = (EditText) findViewById(R.id.conR);
        cont1 = (EditText) findViewById(R.id.conCR);
        sex = (Spinner) findViewById(R.id.spinner);
        registro = (Button) findViewById(R.id.btnAceR);
        bcancelar = (Button) findViewById(R.id.btnCanR);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altaclase(v);
            }
        });

        bcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent =new Intent(v.getContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();*/
            }
        });
        setupActionBar();

      /*  SpinnerAdapter<String> adapter = new SpinnerAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Opciones));
        sex.setAdapter(adapter);*/
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Opciones, android.R.layout.simple_spinner_item);
        sex.setAdapter(adapter);

    }
    private void setupActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void altaclase(View view)
    {
        sqlLite admin = new sqlLite(this,"proyectoDesMov",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String nom = nombre.getText().toString();
        String app = ap.getText().toString();
        String apm = am.getText().toString();
        String tele = tel.getText().toString();
        String email=correoo.getText().toString();
        String con = cont.getText().toString();
        String con1 = cont1.getText().toString();
        String a1=sex.getSelectedItem().toString();

        ContentValues registro = new ContentValues();

        if( nom.isEmpty() || app.isEmpty() || apm.isEmpty() ||  con.isEmpty() || con1.isEmpty() || email.isEmpty() || tele.isEmpty())
        {
            Toast.makeText(this, "Hay campos incorrectos o vacios",Toast.LENGTH_SHORT).show();
        }
        else if ( sex.equals("Semestre:")||sex.equals("")){
            Toast.makeText(this, "Campo: SEMESTRE se encuentra vacio",Toast.LENGTH_SHORT).show();
        }else if (!(con.equals(con1))){
            Toast.makeText(this, "Las contraseñas deben ser iguales",Toast.LENGTH_SHORT).show();
        }else if (!(tele.isEmpty())){
            Cursor cursor = db.rawQuery("select id from usuario where id=" + tele , null);
            if (cursor.moveToFirst()){
                Toast.makeText(this, "El usuario: "+tele+" actualmente esta en uso",Toast.LENGTH_SHORT).show();
            }else{
                registro.put("id", tele);
                registro.put("nombre", nom);
                registro.put("app", app);
                registro.put("apm", apm);
                registro.put("tel", tele);
                registro.put("sexo",a1);
                registro.put("correo", email);
                registro.put("cont", con);
                db.insert("usuario", null, registro);
                Toast.makeText(this, "SI INSERTA",Toast.LENGTH_SHORT).show();
                db.close();
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Registro Exitoso");
                builder.setMessage("\n Bienvenido: "+nom+" "+app+" "+apm+"\nTu usuario para ingresar al sistema es:\n"+tele);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent ListFrutas = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(ListFrutas);
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        }else{
            Toast.makeText(this, "Campo: Telefono se encuentra vacio",Toast.LENGTH_SHORT).show();
        }
    }
}
