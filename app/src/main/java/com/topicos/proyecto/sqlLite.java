package com.topicos.proyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqlLite extends SQLiteOpenHelper {
    public sqlLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario (id text primary key, nombre text, app text, apm text, tel text, sexo text, correo text, cont text)");
        db.execSQL("create table comentarios (id integer primary key autoincrement, nombre text, app text, apm text,tel text, correo text, restaurante text, comentario text, calificacion text)");
        db.execSQL("create table reservaciones (id integer primary key autoincrement, nombre text, app text, apm text,tel text, correo text, restaurante text,fecha text, hora text, mesa text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario (id text primary key, nombre text, app text, apm text, tel text, sexo text, correo text, cont text)");
        db.execSQL("drop table if exists comentarios ");
        db.execSQL("create table comentarios (id integer primary key autoincrement, nombre text, app text, apm text, tel text, correo text, restaurante text, comentario text, calificacion text) ");
        db.execSQL("drop table if exists reservaciones ");
        db.execSQL("create table comentarios (id integer primary key autoincrement, nombre text, app text, apm text, tel text, correo text, restaurante text,fecha text, hora text, mesa text) ");
    }

}
