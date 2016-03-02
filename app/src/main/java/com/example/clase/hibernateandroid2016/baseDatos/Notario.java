package com.example.clase.hibernateandroid2016.baseDatos;

import android.provider.BaseColumns;

//arreglado
public class Notario {

    private Notario(){}

    //tabla para guardar los usuarios y poder hacer el login
    public static abstract class TablaUsuario implements BaseColumns{
        public static final String TABLA= "usuarios";
        public static final String email ="email";
        public static final String contraseña= "contraseña";
    }
    //tabla para guardar los datos de las notas
    public static abstract class TablaNotas implements BaseColumns{
        public static final String TABLA= "notas";
        public static final String idusuario= "idusuario";
        public static final String contenido ="contenido";
        public static final String fecha= "fecha";
        public static final String titulo = "titulo";
    }
}
