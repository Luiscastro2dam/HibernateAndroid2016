package com.example.clase.hibernateandroid2016.baseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//arreglado
public class Ayudante extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "notas.sqlite";
    public static final int DATABASE_VERSION = 4;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);

    }
//---------------------consultas----------------
// INSERT INTO notas(_id,titulo,idusuario,contenido,fecha) VALUES(1,'run',1,'correr enfeda ddsfsd de','12-12-12')
//SELECT * FROM notas where idusuario = 1    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql;
       //creamos la tabla de usuarios
        sql = "create table " + Notario.TablaUsuario.TABLA +
                " (" + Notario.TablaUsuario._ID +
                " integer primary key autoincrement, " +
                Notario.TablaUsuario.email + " text, " +
                Notario.TablaUsuario.contraseña + " text)";
        db.execSQL(sql);
        //creamos la tabla de notas
        sql = "create table " + Notario.TablaNotas.TABLA +
                " (" + Notario.TablaNotas._ID +
                " integer primary key autoincrement, " +
                Notario.TablaNotas.titulo + " text, " +
                Notario.TablaNotas.idusuario + " text, " +
                Notario.TablaNotas.contenido + " text, " +
                Notario.TablaNotas.fecha+ " text)";
        db.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "drop table if exists "+Notario.TablaUsuario.TABLA;
        db.execSQL(sql);
        String sql2 = "drop table if exists "+Notario.TablaNotas.TABLA;
        db.execSQL(sql2);

        onCreate(db);

    }
}
