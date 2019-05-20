package com.topicos.proyecto;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String texto= ChooseAccount.getControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTitle("Sugerencias");
                Sugerencias fragmencl = new Sugerencias();
                Bundle args = new Bundle();
                args.putString("textFromActivityB", texto);
                fragmencl.setArguments(args);
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.panel, fragmencl, "Crear");
                transaction.commit();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Restaurantes");
        CardContentFragment fragmencl = new CardContentFragment();
        Bundle args = new Bundle();
        args.putString("textFromActivityB", texto);
        fragmencl.setArguments(args);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.panel, fragmencl, "Crear");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("ACERCA DE...");
            builder.setMessage("\nDesarrollado por:\n"+
                    "  -González Pérez José María\n" +
                    "  -Estrada Garcia Abraham\n\n"+
                    "  -Esteban Dionisio Daniel \n\n"+
                    "   Desarrollo Movil");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mPerfil) {
            setTitle("Mis Datos");
            Misdatos fragmencl = new Misdatos();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();
        } else if (id == R.id.mEditarP) {
            setTitle("Editar perfil");
            editarPerfil fragmencl = new editarPerfil();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();


        } else if (id == R.id.mReserva) {
            setTitle("Reservar");
            Reservar fragmencl = new Reservar();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();

        } else if (id == R.id.mCatalogo) {
            setTitle("Restaurantes");
            CardContentFragment fragmencl = new CardContentFragment();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();

        } else if (id==R.id.mSugerencias) {
            setTitle("Sugerencias");
            Sugerencias fragmencl = new Sugerencias();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();
        } else if (id==R.id.mSalir){
           // super.onDestroy();
           // android.os.Process.killProcess(android.os.Process.myPid());
            //finishAffinity();
            super.finishAffinity();
        } else if (id==R.id.mMisReserva){
            setTitle("Mis Reservaciones");
            misreservaciones fragmencl = new misreservaciones();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();

        } if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            setTitle("Sugerencias");
          listar fragmencl = new listar();
            Bundle args = new Bundle();
            args.putString("textFromActivityB", texto);
            fragmencl.setArguments(args);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.panel, fragmencl, "Crear");
            transaction.commit();

            }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
