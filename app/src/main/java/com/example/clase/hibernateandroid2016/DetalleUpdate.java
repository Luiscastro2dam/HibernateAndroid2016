package com.example.clase.hibernateandroid2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.clase.hibernateandroid2016.baseDatos.GestorNotas;
import com.example.clase.hibernateandroid2016.pojo.Nota;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clase on 29/02/2016.
 */
public class DetalleUpdate extends AppCompatActivity {

    private GestorNotas gestorNotas;
    private EditText ettitulode,etcontenidode,etfechade;
    private String idusu,idnott;
    private int idusuario,idnota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notaupdate);
        gestorNotas= new GestorNotas(this);

        idusu=getIntent().getExtras().getString("parametro");
        idnott=getIntent().getExtras().getString("parametro1");

        idusuario=Integer.parseInt(idusu);
        idnota=Integer.parseInt(idnott);
        System.out.println("updatenotas idnota="+idnota+"idusu="+idusuario);
        ettitulode = (EditText)findViewById(R.id.etdetitu);
        etcontenidode = (EditText)findViewById(R.id.etdeconten);
        etfechade = (EditText)findViewById(R.id.etdefecha);
    }
    public void init(){
       this.mostrarNOTA();
    }
    public void mostrarNOTA(){
        List<Nota> nota = new ArrayList<>();
        nota = gestorNotas.listanotasidusuarioNota(idusuario,idnota);
        ettitulode.setText(nota.get(0).getTitulo());
        etcontenidode.setText(nota.get(0).getContenido());
        etfechade.setText(nota.get(0).getFecha());
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
    //---------------onclick de los botones----------------
    public void btupdate(View v){
        String titulo = ettitulode.getText().toString();
        String contenido = etcontenidode.getText().toString();
        String fecha = etfechade.getText().toString();
        Nota nota = new Nota();
        nota.setIdNota(idnota);
        nota.setTitulo(titulo);
        nota.setContenido(contenido);
        nota.setFecha(fecha);
        nota.setIdUsuario(idusuario);
        System.out.println("nota modificada===="+nota.toString());
        gestorNotas.updateNota(nota);
    }
    public void btupsalir(View v){
        Intent ia = new Intent(this, ListaNotas.class);
        int id=idusuario;
        String idus=String.valueOf(id);
        ia.putExtra("parametro", idus);
        startActivity(ia);
    }
    //-----------------------------------------------------

}
