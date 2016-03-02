package com.example.clase.hibernateandroid2016.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.hibernateandroid2016.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

//modificada
public class GestorUsuarios {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorUsuarios(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void close() {
        abd.close();
    }

    //insertar nuevos usuarios
    public long insert(Usuario ag) {
        ContentValues valores = new ContentValues();
        valores.put(Notario.TablaUsuario._ID,
                ag.getIdUsuario());
        valores.put(Notario.TablaUsuario.email,
                ag.getEmail());
        valores.put(Notario.TablaUsuario.contraseña,
                ag.getContraseña());
        long id=  bd.insert(Notario.TablaUsuario.TABLA,null,valores);
        return id;
    }
    //para devolver una lista con todos los usuarios que hay en la bd
    public List<Usuario> selectUsuarios() {
        List<Usuario> lista;
        lista = new ArrayList<Usuario>();
        Cursor cursor = bd.query(Notario.TablaUsuario.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Usuario usuario;
        while (!cursor.isAfterLast()) {
            usuario = getRowUsuario(cursor);
            lista.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Usuario());
            return lista;
        }
        return lista;
    }
    public Usuario getRowUsuario(Cursor c) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(c.getInt(c.getColumnIndex(Notario.TablaUsuario._ID)));
        usuario.setEmail(c.getString(1));
        usuario.setContraseña(c.getString(2));
        return usuario;
    }
    //obtener un id nuevo para un usuario-----------------
    public int devolveridUsuario(){
        Cursor c = bd.rawQuery("SELECT  max(_id) from usuarios", null);
        c.moveToFirst();
        int id = c.getInt(0);
        System.out.println("idusuarionuevo="+id);
        return id+1;
    }



//
//    public int delete(Keep ag) {
//        return deleteId(ag.getId());
//    }
//
//    public int deleteId(long id) {
//        String condicion = Notario.TablaKeep._ID + " = ?";
//        String[] argumentos = { id + "" };
//        int cuenta = bd.delete(
//                Notario.TablaKeep.TABLA, condicion, argumentos);
//        return cuenta;
//    }
//
//    public List<Keep> select(String condicion) {
//        List<Keep> la = new ArrayList<>();
//        Cursor cursor = bd.query(Notario.TablaKeep.TABLA, null,
//                condicion, null, null, null, null);
//        cursor.moveToFirst();
//        Keep ag;
//        while (!cursor.isAfterLast()) {
//            ag = getRow(cursor);
//            la.add(ag);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return la;
//    }
//
//    public Keep getRow(Cursor c) {
//
//        Keep ag = new Keep();
//        if(c != null) {
//            ag.setId(c.getInt(0));
//            Log.v("xxx", c.getInt(0) + "");
//            Log.v("xxx",c.getString(1) + "");
//            ag.setContenido(c.getString(1));
//            if (c.getInt(2)==1){
//                ag.setEstado(true);
//            }else{
//                ag.setEstado(false);
//            }
//
//        }else{
//            System.out.println("mal");
//        }
//
//        return ag;
//    }
//


}
