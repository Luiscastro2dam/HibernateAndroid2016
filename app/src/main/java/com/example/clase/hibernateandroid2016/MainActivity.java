package com.example.clase.hibernateandroid2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clase.hibernateandroid2016.baseDatos.GestorNotas;
import com.example.clase.hibernateandroid2016.baseDatos.GestorUsuarios;
import com.example.clase.hibernateandroid2016.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declaramos los elementos del activity_main
    private EditText etemail,etcontraseña;
    private Button btentrar,btregistrar;

    //declaramos los gestores de las base de datos
    private GestorNotas gestorNotas;
    private GestorUsuarios gestorUsuarios;

    //array list para guardar los usuarios de la base de datos
    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //conectamos con los elementos interfaz
        btentrar=(Button)findViewById(R.id.btentrar);
        btregistrar=(Button)findViewById(R.id.btregistrar);
        etemail = (EditText)findViewById(R.id.etemail);
        etcontraseña=(EditText)findViewById(R.id.etcontrasena);
        //iniciamos los gestores de la base de datos
        gestorNotas= new GestorNotas(this);
        gestorUsuarios=new GestorUsuarios(this);
    }
    //metodo que se ejecuta al inicio
    public void init(){


    }
    //
    //------------botones del main-------------------------
    public void btentrar(View v){
        listaUsuarios = gestorUsuarios.selectUsuarios();
        System.out.println("listaUsuarios:--->"+listaUsuarios.toString());
       for(int i=0;i<listaUsuarios.size();i++){
           if(listaUsuarios.get(i).getEmail().equalsIgnoreCase(etemail.getText().toString())
                   && listaUsuarios.get(i).getContraseña()
                   .equalsIgnoreCase(etcontraseña.getText().toString())){
               //email valido
                     //contraseña valida entramos en nuestras notas
                    Intent ia = new Intent(this, ListaNotas.class);
                 int id=listaUsuarios.get(i).getIdUsuario();
                String idus=String.valueOf(id);
                    ia.putExtra("parametro", idus);
                    startActivity(ia);

               System.out.println("1234 todo correcto");


       }
       }


    }
    public void btregistrar(View v){
        Intent i = new Intent(this,RegistrarUsuario.class);
        startActivity(i);
    }
    //-----------------------------------------------------
    //-------para la base de datos-------------------------
    @Override
    protected void onPause() {
        gestorNotas.close();
        gestorUsuarios.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gestorUsuarios.open();
        gestorNotas.open();
        super.onResume();
        this.init();
    }
    //-----------------------------------------------------
}
