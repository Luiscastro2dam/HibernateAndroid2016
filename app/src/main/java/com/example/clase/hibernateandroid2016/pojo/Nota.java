package com.example.clase.hibernateandroid2016.pojo;

import java.io.Serializable;

/**
 * Created by Clase on 27/02/2016.
 */
public class Nota implements Serializable {
    private int idNota,idUsuario;
    private String contenido,fecha,titulo;

    public Nota(int idNota,int idUsuario, String contenido, String fecha, String titulo) {
       this.idUsuario=idUsuario;
        this.idNota = idNota;
        this.contenido = contenido;
        this.fecha = fecha;
        this.titulo = titulo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Nota() {
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "idNota=" + idNota +
                ", contenido='" + contenido + '\'' +
                ", fecha='" + fecha + '\'' +
                ", titulo='" + titulo + '\'' +
                ", IDUSUARIO='" + idUsuario + '\'' +
                '}';
    }
}
