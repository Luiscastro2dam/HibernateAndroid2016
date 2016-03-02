package com.example.clase.hibernateandroid2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.clase.hibernateandroid2016.baseDatos.GestorUsuarios;
import com.example.clase.hibernateandroid2016.pojo.Usuario;

/**
 * Created by Clase on 27/02/2016.
 */
public class RegistrarUsuario extends AppCompatActivity {

    private EditText etemail,etcontraseña;
    private GestorUsuarios gestorUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resgistrarusuarios);

        etemail = (EditText)findViewById(R.id.etregisemail);
        etcontraseña = (EditText)findViewById(R.id.etregiscontr);

        gestorUsuarios = new GestorUsuarios(this);
    }
    public void btregistrar(View v){
        //recojemos los datos y creamos al usuario
        String email=etemail.getText().toString();
        String contraseña=etcontraseña.getText().toString();
        Usuario usu = new Usuario();
        int a=gestorUsuarios.devolveridUsuario();
        usu.setIdUsuario(a);
        usu.setEmail(email);
        usu.setContraseña(contraseña);
        gestorUsuarios.insert(usu);
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
    //-------para la base de datos-------------------------
    @Override
    protected void onPause() {
        gestorUsuarios.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gestorUsuarios.open();
        super.onResume();
    }
    //-----------------------------------------------------
}
