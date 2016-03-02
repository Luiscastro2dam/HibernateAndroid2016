package com.example.clase.hibernateandroid2016.pojo;

import java.io.Serializable;

/**
 * Created by Clase on 27/02/2016.
 */
public class Usuario implements Serializable {
    private int idUsuario;
    private String email, contraseña;

    public Usuario() {
    }

    public Usuario(int idUsuario, String email, String contraseña) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
