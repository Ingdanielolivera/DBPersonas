package com.example.labsoftware1.personasbasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CUC on 13/05/2017.
 */

public class PersonaSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sql = "CREATE TABLE Personas(foto text, cedula text, nombre text, apellido text, sexo text, pasatiempo text)";

    public PersonaSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Personas");
        db.execSQL(sql);
    }
}
