package com.example.labsoftware1.personasbasededatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.util.ArrayList;

public class Listado_tabla extends AppCompatActivity {

    private TableLayout tabla;
    private ArrayList<Persona> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_tabla);

        tabla=(TableLayout)findViewById(R.id.tblPersonas);
        personas
    }
}
