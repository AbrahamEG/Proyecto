package com.topicos.proyecto;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.ActivityChooserModel;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.security.AccessControlContext;


public class navega extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Misdatos.OnFragmentInteractionListener{
    String texto= ChooseAccount.getControl();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navega);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTitle("Envia tu CV");
                Bundle args = new Bundle();
                //args.putString("textFromActivityB", texto);
                Misdatos fragmencl = new Misdatos();
                fragmencl.setArguments(args);
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.panel,fragmencl,"Crear");
                transaction.commit();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "Para regresar, debe cerrar sesión", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navega, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      /*  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Navega.this, Ayuda.class);
            intent.putExtra("textFromActivityB", texto);
            startActivity(intent);
        }
        if (id == R.id.action_about) {

            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("ACERCA DE...");
            builder.setMessage("\nDesarrollado por:\n"+
                    "  -Martínez Mendoza Leonardo\n" +
                    "  -González Pérez José María\n" +
                    "  -Peña Gómez Luis Ángel\n\n"+
                    "Version de prueba");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       /// Fragment frag;

        if (id == R.id.nav_gallery) {

            setTitle("Informacion de Usuario");
            Misdatos fragmencl = new Misdatos();
            Bundle args = new Bundle();
           args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel,fragmencl,"Crear");
            transaction.commit();
        }else if(id == R.id.nav_slideshow){
            setTitle("Editar perfil");


            editarPerfil fragmencl = new editarPerfil();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();
        } else if (id == R.id.nav_send) {
           /* setTitle("Envia tu CV");
            CV fragmencl = new CV();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel,fragmencl,"Crear");
            transaction.commit();*/
        } else if (id == R.id.nav_send) {
          /*  setTitle("Sugerencia de Empresa");
            Sug_Empresa fragmencl = new Sug_Empresa();
            fragmencl.setActivity(this);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel,fragmencl,"Crear");
            transaction.commit();*/
        } else if (id == R.id.nav_send) {
           /* setTitle("Catalogo de Empresas");
            Catalogo fragmencl = new Catalogo();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel,fragmencl,"Crear");
            transaction.commit();*/

        } else if (id == R.id.nav_send) {
            finish();
        }else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Prueba VinculaTec, uso esta aplicación para encontrar una empresa en la cual pueda desarrollarme profesionalmente, de una manera sencilla. Descárgala gratis desde: https://mega.nz/#F!i6Jj1IIJ!1CyGdgrgjwxVK7Glos_roQ");
            startActivity(Intent.createChooser(intent, "Share with"));

        } else if (id == R.id.nav_send) {
            Uri uri = Uri.parse("https://www.facebook.com/ResidenciasIttol");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onFragmentInteraction(Uri uri) {

    }
}
