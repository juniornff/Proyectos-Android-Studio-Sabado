package com.example.registrodeestudiantes;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.registrodeestudiantes.DB.EstudianteDBHelper;
import com.example.registrodeestudiantes.Model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteHelper {

    private SharedPreferences preferences;
    private Context context;

    public EstudianteHelper(Context context){
        this.context = context;
        this.preferences = context.getSharedPreferences("DBPreference",0);
    }

    public List<Estudiante> getAllEstudianteList(){
        List<Estudiante> estudianteList = new ArrayList<>();

        EstudianteDBHelper dbHelper = new EstudianteDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("Estudiantes",
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Estudiante estudiante = new Estudiante();

            estudiante.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_ID")));
            setLastEstuId(cursor.getInt(cursor.getColumnIndexOrThrow("_ID")));
            estudiante.setName(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
            estudiante.setLastName(cursor.getString(cursor.getColumnIndexOrThrow("LASTNAME")));
            estudiante.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("TELEFONO")));
            estudiante.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("EMAIL")));
            estudiante.setDay(cursor.getInt(cursor.getColumnIndexOrThrow("DAY")));
            estudiante.setMonth(cursor.getInt(cursor.getColumnIndexOrThrow("MONTH")));
            estudiante.setYear(cursor.getInt(cursor.getColumnIndexOrThrow("YEAR")));
            estudiante.setSexo(cursor.getInt(cursor.getColumnIndexOrThrow("SEXO")));
            estudiante.setCarrera(cursor.getInt(cursor.getColumnIndexOrThrow("CARRERA")));

            estudianteList.add(estudiante);
        }

        db.close();
        return estudianteList;
    }

    public long addEstudiante(Estudiante estudiante) {

        long id = 0;

        EstudianteDBHelper dbHelper = new EstudianteDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("_ID",estudiante.getId());
        values.put("NAME",estudiante.getName());
        values.put("LASTNAME",estudiante.getLastName());
        values.put("TELEFONO",estudiante.getPhone());
        values.put("EMAIL",estudiante.getEmail());
        values.put("DAY",estudiante.getDay());
        values.put("MONTH",estudiante.getMonth());
        values.put("YEAR",estudiante.getYear());
        values.put("SEXO",estudiante.getSexo());
        values.put("CARRERA",estudiante.getCarrera());

        id = db.insert("Estudiantes",null,values);

        db.close();
        return id;
    }

    public int getLastEstuId() {
        int lastIdEstu = preferences.getInt("last_id", 0);
        setLastEstuId(lastIdEstu + 1);
        return lastIdEstu;
    }

    public void setLastEstuId(int id)
    {
        preferences.edit().putInt("last_id", id).commit();
    }


    public int updateEstudiante(Estudiante estudiante){
        int countUpdated = 0;

        EstudianteDBHelper dbHelper = new EstudianteDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME",estudiante.getName());
        values.put("LASTNAME",estudiante.getLastName());
        values.put("TELEFONO",estudiante.getPhone());
        values.put("EMAIL",estudiante.getEmail());
        values.put("DAY",estudiante.getDay());
        values.put("MONTH",estudiante.getMonth());
        values.put("YEAR",estudiante.getYear());
        values.put("SEXO",estudiante.getSexo());
        values.put("CARRERA",estudiante.getCarrera());

        String[] arg = { String.valueOf(estudiante.getId()) };

        countUpdated = db.update("Estudiantes",
                values,
                " _ID = ?",
                arg);

        return countUpdated;
    }

    public int deleteEstudiante(int id){

        int countDeleted = 0;

        EstudianteDBHelper dbHelper = new EstudianteDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] arg = { String.valueOf(id) };

        countDeleted = db.delete("Estudiantes",
                " _ID = ?",
                arg);

        db.close();
        return countDeleted;

    }
}
