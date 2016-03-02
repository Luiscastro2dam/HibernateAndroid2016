package com.example.clase.hibernateandroid2016.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.hibernateandroid2016.pojo.Nota;

import java.util.ArrayList;
import java.util.List;


public class GestorNotas {
        private Ayudante abd;
        private SQLiteDatabase bd;

        public GestorNotas(Context c) {
            abd = new Ayudante(c);
        }
        public void open() {
            bd = abd.getWritableDatabase();
        }
        public void close() {
            abd.close();
        }

    public List<Nota> listanotasidnota(int idnota){
        List<Nota> lista;
        lista = new ArrayList<Nota>();
        Cursor cursor = bd.rawQuery("SELECT * FROM notas where idnota ="+idnota, null);
        cursor.moveToFirst();
        Nota nota;
        while (!cursor.isAfterLast()) {
            nota = getRowNota(cursor);
            lista.add(nota);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Nota());
            return lista;
        }
        return lista;
    }

    public List<Nota> listanotasidusuarioNota(int idusuario,int idnota){
        List<Nota> lista;
       int  idnota2=1+idnota;
        System.out.println("111idnota="+idnota2+"idusuario="+idusuario);
        lista = new ArrayList<Nota>();
        Cursor cursor = bd.rawQuery("SELECT * FROM notas where idusuario="+idusuario+" and _id="+idnota, null);
        cursor.moveToFirst();
        Nota nota;
        while (!cursor.isAfterLast()) {
            nota = getRowNota(cursor);
            lista.add(nota);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Nota());
            return lista;
        }
        return lista;
    }
    public List<Nota> listanotasidusuario(int idusuario){
            List<Nota> lista;
            lista = new ArrayList<Nota>();
            Cursor cursor = bd.rawQuery("SELECT * FROM notas where idusuario ="+idusuario, null);
             cursor.moveToFirst();
            Nota nota;
            while (!cursor.isAfterLast()) {
                nota = getRowNota(cursor);
                lista.add(nota);
                cursor.moveToNext();
            }
            cursor.close();
            if(lista==null){
                lista.add(new Nota());
                return lista;
            }
            return lista;
        }
    public Nota getRowNota(Cursor c) {
       Nota nota = new Nota();
        nota.setIdNota(c.getInt(c.getColumnIndex(Notario.TablaUsuario._ID)));
        nota.setTitulo(c.getString(1));
        nota.setContenido(c.getString(3));
        nota.setFecha(c.getString(4));
        System.out.println(nota.toString()+"cursorcurs");
        return nota;
    }
    public long insertNota(Nota ag) {
        System.out.println("entraqui1");
        ContentValues valores = new ContentValues();
        valores.put(Notario.TablaNotas._ID,
                ag.getIdNota());
        valores.put(Notario.TablaNotas.idusuario,
                ag.getIdUsuario());
        valores.put(Notario.TablaNotas.titulo,
                ag.getTitulo());
        valores.put(Notario.TablaNotas.contenido,
                ag.getContenido());
        valores.put(Notario.TablaNotas.fecha,
                ag.getFecha());
        System.out.println("notas12345"+valores.toString());
        long id= bd.insert(Notario.TablaNotas.TABLA,null,valores);
        return id;
    }
    //obtener un id nuevo para una nota-----------------
    public int devolveridNota(){
        Cursor c = bd.rawQuery("SELECT  max(_id) from notas", null);
        c.moveToFirst();
        int id = c.getInt(0);
        System.out.println("idnotanuevo="+id);
        return id+1;
    }
    //update notas
    public void updateNota(Nota ag){
        ContentValues valores = new ContentValues();
        valores.put(Notario.TablaNotas.contenido, ag.getContenido());
        valores.put(Notario.TablaNotas.titulo, ag.getTitulo());
        valores.put(Notario.TablaNotas.fecha, ag.getFecha());
        valores.put(Notario.TablaNotas.idusuario, ag.getIdUsuario());
        String condicion = Notario.TablaNotas._ID + " = ?";
        String[] argumentos = { ag.getIdNota() + "" };
        int a =bd.update(Notario.TablaNotas.TABLA, valores,
                condicion, argumentos);
        System.out.println(a+"updatenotas");
    }
    //borrar notas pasando el id de la nota
    public void deleteNota(long id){
        String condicion = Notario.TablaNotas._ID + " = ?";
        String[] argumentos = { id + "" };
        bd.delete(Notario.TablaNotas.TABLA, condicion, argumentos);
    }

}

