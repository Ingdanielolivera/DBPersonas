package com.example.labsoftware1.personasbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lab Software 1 on 12/05/2017.
 */

public class Persona {
    private String foto;
    private String cedula;
    private String nombre;
    private String apellido;
    private String sexo;
    private String pasatiempo;

    public Persona(String foto, String cedula, String nombre, String apellido, String sexo, String pasatiempo) {
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.pasatiempo = pasatiempo;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPasatiempo() {
        return pasatiempo;
    }

    public void setPasatiempo(String pasatiempo) {
        this.pasatiempo = pasatiempo;
    }

    public void guardar (Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexión de base de datos en modo escritura
        PersonaSQLiteOpenHelper aux =new PersonaSQLiteOpenHelper(contexto,"DBPersonas",null,1);
        db = aux.getWritableDatabase();

      /*  //insertar forma 1
        sql ="INSERT INTO Personas Values('"+this.getFoto()+"','"+this.getCedula()+"','"+this.getNombre()+"','"+this.getApellido()+"','"+this.getSexo()+"','"+this.getPasatiempo()+"')";
        db.execSQL(sql);*/
        //insertar forma 2

        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getApellido());
        nuevoRegistro.put("sexo",this.getSexo());
        nuevoRegistro.put("pasatiempo",this.getPasatiempo());

        db.insert("Personas",null, nuevoRegistro);
        db.close();
    }






}
