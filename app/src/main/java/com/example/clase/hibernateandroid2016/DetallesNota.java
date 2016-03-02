package com.example.clase.hibernateandroid2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.clase.hibernateandroid2016.baseDatos.GestorNotas;
import com.example.clase.hibernateandroid2016.pojo.Nota;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Clase on 27/02/2016.
 */
public class DetallesNota extends AppCompatActivity {

    private EditText ettitulode,etcontenidode,etfechade;
    String idusuario1,idnota1;
    private GestorNotas gestorNotas;
    int idusuario;
    int idnota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarnota);

        gestorNotas= new GestorNotas(this);
        ettitulode = (EditText)findViewById(R.id.etmosTitulo);
        etcontenidode = (EditText)findViewById(R.id.etmoscontenido);
        etfechade = (EditText)findViewById(R.id.etmosfecha);
        idusuario1=getIntent().getExtras().getString("parametro");
        idnota1=getIntent().getExtras().getString("parametro1");
        idusuario=Integer.valueOf(idusuario1);
//        idnota=Integer.valueOf(idnota1);

        //ponemos la fecha actual por defecto
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        etfechade.setText(mydate);

        System.out.println("dettallesnotas--->"+idusuario1+"------"+idnota);
    }
    public void init(){
            //  this.mostrarReceta();
    }
    public void mostrarReceta(){
        idusuario = Integer.parseInt(idusuario1);
        List<Nota> nota = new ArrayList<>();
      //  nota = gestorNotas.listanotasidusuario(idusuario);
        ettitulode.setText(nota.get(1).getTitulo());
        etcontenidode.setText(nota.get(1).getContenido());
        etfechade.setText(nota.get(1).getFecha());

    }
    //-------para la base de datos-------------------------
    @Override
    protected void onPause() {
        gestorNotas.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gestorNotas.open();
        super.onResume();
        this.init();
    }
    //-----------------------------------------------------
    //-----------botones------------------------------------
    public void btsalirListanotas(View v){
        Intent ia = new Intent(this, ListaNotas.class);
        int id=idusuario;
        String idus=String.valueOf(id);
        ia.putExtra("parametro", idus);
        startActivity(ia);
    }
    public void btguardar(View v){
        String titulo = ettitulode.getText().toString();
        String contenido = etcontenidode.getText().toString();
        String fecha = etfechade.getText().toString();
        int idnot=gestorNotas.devolveridNota();
        Nota nota = new Nota();
        nota.setIdNota(idnot);
        nota.setTitulo(titulo);
        nota.setContenido(contenido);
        nota.setFecha(fecha);
        idusuario = Integer.parseInt(idusuario1);
        nota.setIdUsuario(idusuario);
        System.out.println("nota nueva===="+nota.toString());
        gestorNotas.insertNota(nota);

        Intent ia = new Intent(this, ListaNotas.class);
        int id=idusuario;
        String idus=String.valueOf(id);
        ia.putExtra("parametro", idus);
        startActivity(ia);

    }
}
