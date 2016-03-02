package com.example.clase.hibernateandroid2016.gestion;

import android.content.Context;

import com.example.clase.hibernateandroid2016.baseDatos.GestorNotas;

/**
 * Created by Clase on 29/02/2016.
 */
public class GestionNota {

    private GestorNotas gestorNotas;
    private String urlDestino = "http://192.10:8080/Keep/go";

    public GestionNota(Context context) {
        this.gestorNotas = new GestorNotas(context);
    }

}
