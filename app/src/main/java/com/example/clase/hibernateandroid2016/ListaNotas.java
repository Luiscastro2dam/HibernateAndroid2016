package com.example.clase.hibernateandroid2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.clase.hibernateandroid2016.Adaptadores.AdaptadorNotas;
import com.example.clase.hibernateandroid2016.baseDatos.GestorNotas;
import com.example.clase.hibernateandroid2016.baseDatos.GestorUsuarios;
import com.example.clase.hibernateandroid2016.pojo.Nota;
import com.example.clase.hibernateandroid2016.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clase on 27/02/2016.
 */
public class ListaNotas extends AppCompatActivity {

    //declaramos el adaptador para mostrar las notas
    AdaptadorNotas ad;
    //lista para guardas las notas del usuario
    private List<Nota> listaNotas = new ArrayList<Nota>();
    //declaramos los gestores de las base de datos
    private GestorNotas gestorNotas;
    private GestorUsuarios gestorUsuarios;
    private ListView lv;
    //---id del usuario
    private int idusuario;
    Usuario usuario ;
    String idusuario1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listanotas);

        gestorNotas= new GestorNotas(this);
        gestorUsuarios=new GestorUsuarios(this);
//         usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        idusuario1=getIntent().getExtras().getString("parametro");

        System.out.println("idusuario:-->"+getIntent().getExtras().getString("parametro"));

    }
    public void mostrarNota(int idusuario){

    }
    //------------menu principal--------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_anadir: {
                //añadir nota
                Intent i = new Intent(this,DetallesNota.class);
                String idus=String.valueOf(idusuario1);
                i.putExtra("parametro", idus);
                startActivity(i);
                return true;
            }
            case R.id.menu_sincronizar: {
                //sincronizar nota

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------

    //----------------metodo de inicio
    public void init(){
        lv = (ListView) findViewById(R.id.lvNotas);
        listaNotas= new ArrayList<>();

        //obtenemos id del usuario
        idusuario=Integer.parseInt(idusuario1);
        listaNotas= gestorNotas.listanotasidusuario(idusuario);
        System.out.println("123notas--->"+listaNotas.toString());
        ad = new AdaptadorNotas(this, R.layout.item, (ArrayList<Nota>) listaNotas);
        lv.setAdapter(ad);
        this.registerForContextMenu(lv);

    }
    //---------Menu ListView-------------------------------------

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = menuInfo.position;
        switch (item.getItemId()) {
            case R.id.menu_Mostar: {
                Intent i = new Intent(this, DetalleUpdate.class);
                String idus=String.valueOf(idusuario1);
                String idnota=String.valueOf(pos);
                 int a=listaNotas.get(pos).getIdNota();
                String idn=String.valueOf(a);
                 i.putExtra("parametro", idus);
                 i.putExtra("parametro1",idn);
                 startActivity(i);
                return true;
            }
            case R.id.menu_borrar: {
                long a=  listaNotas.get(pos).getIdNota();
                gestorNotas.deleteNota(a);
                this.init();
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lisview, menu);
    }
    //-------------------------------------------------------------------
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
