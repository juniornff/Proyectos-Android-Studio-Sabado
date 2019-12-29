package com.example.registrodeestudiantesmvp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EstudiantesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Estudiantes.db";

    public EstudiantesDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Estudiantes (_ID INTEGER PRIMARY KEY, NAME TEXT, LASTNAME TEXT, TELEFONO TEXT, EMAIL TEXT, DAY INTEGER, MONTH INTEGER, YEAR INTEGER, SEXO INTEGER, CARRERA INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Estudiantes");
    }
}
