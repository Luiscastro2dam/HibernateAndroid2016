package com.example.clase.hibernateandroid2016.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.clase.hibernateandroid2016.R;
import com.example.clase.hibernateandroid2016.pojo.Nota;

import java.util.ArrayList;

/**
 * Created by 2dam on 05/10/2015.
 */
public class AdaptadorNotas extends ArrayAdapter<Nota>{

    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Nota> valores;
    private Context con;

    static class ViewHolder{
        public TextView tvTitulo,tvfecha;

    }


    public AdaptadorNotas(Context context, int resource, ArrayList<Nota> objects) {
        super(context, resource, objects);
        this.res= resource; // LAYOUT
        this.valores= objects; // LISTA DE VALORES
        this.con= context;
        lInflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv= new ViewHolder();
        if(convertView==null){
            convertView= lInflator.inflate(res, null);
            TextView tvTitulo= (TextView)convertView.findViewById(R.id.tvtitulointem);
            gv.tvTitulo=tvTitulo;
            TextView tvfecha= (TextView)convertView.findViewById(R.id.tvfechaitem);
            gv.tvfecha=tvfecha;

            convertView.setTag(gv);
        }else{
            gv= (ViewHolder) convertView.getTag();
        }
        gv.tvTitulo.setText(valores.get(position).getTitulo());
        gv.tvfecha.setText(valores.get(position).getFecha());

        return convertView;
    }


}
