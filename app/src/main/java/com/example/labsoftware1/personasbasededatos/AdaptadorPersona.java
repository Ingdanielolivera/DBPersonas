package com.example.labsoftware1.personasbasededatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CUC on 13/05/2017.
 */

public class AdaptadorPersona extends BaseAdapter{
    private Context contexto;
    private ArrayList<Persona> personas;

    public AdaptadorPersona (Context contexto, ArrayList<Persona> personas){
        this.contexto = contexto;
        this.personas = personas;
    }


    @Override
    public int getCount() {
        return personas.size();
    }

    @Override
    public Object getItem(int position) {
        return personas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(personas.get(position).getCedula());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declarar variable
        TextView cajaCedula, cajaNombre, cajaApellido, cajaSexo;
        ImageView foto;
        LayoutInflater inflater;
        View itemView;

        //Uso inflater
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.item_personalizado, null);

        //Capturar objetos

        cajaCedula = (TextView) itemView.findViewById(R.id.txtCedulaP);
        cajaNombre = (TextView) itemView.findViewById(R.id.txtNombreP);
        cajaApellido = (TextView) itemView.findViewById(R.id.txtApellidoP);
        foto = (ImageView) itemView.findViewById(R.id.imgFoto);

        //Pasar la información
        foto.setImageResource(Integer.parseInt(personas.get(position).getFoto()));
        cajaCedula.setText(personas.get(position).getCedula());
        cajaNombre.setText(personas.get(position).getNombre());
        cajaApellido.setText(personas.get(position).getApellido());



        //Retornar el itemView
        return itemView;
    }
}
