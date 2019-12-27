package com.nehemias.preguntasyrespuestas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nehemias.preguntasyrespuestas.PreguntaContract.*;

import java.util.ArrayList;

public class PreguntaDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PreguntasYRespuestas.db";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;

    public PreguntaDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREAR_TABLA_PREGUNTAS = "CREATE TABLE " +
                tablaPreguntas.TABLE_NAME + " ( " +
                tablaPreguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tablaPreguntas.COLUMN_PREGUNTA_NAME + " TEXT, " +
                tablaPreguntas.COLUMN_OPCION1 + " TEXT, " +
                tablaPreguntas.COLUMN_OPCION2 + " TEXT, " +
                tablaPreguntas.COLUMN_OPCION3 + " TEXT, " +
                tablaPreguntas.COLUMN_RESPUESTA + " INTEGER, " +
                tablaPreguntas.COLUMN_DIFICULTAD + " TEXT" +
                ")";

        db.execSQL(SQL_CREAR_TABLA_PREGUNTAS);
        llenarTablaPreguntas();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablaPreguntas.TABLE_NAME);
        onCreate(db);
    }

    private void llenarTablaPreguntas() {
        Pregunta p1 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿De cuál de los siguientes idiomas procede la palabra Biblia?", "Hebreo", "Griego", "Latín", 2, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p1);
        Pregunta p2 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Cuántos libros componen la biblia?", "66", "57", "70", 1, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p2);
        Pregunta p3 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿En cuántas partes se divide la Biblia?", "Tres", "Cuatro", "Dos", 3, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p3);
        Pregunta p4 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": A la hora de leer la Biblia teneemos que tener en cuenta que", "Es un libro de historia que nos transmite la vida de un hombre llamado Jesús", "Es la palabra de Dios dada a los hombres", "Es un libro de fantasia", 2, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p4);
        Pregunta p5 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": La Biblia se escribió hacia", "El año 100 después de Cristo", "Durante la vida de Jesús", "Tardó en escribirse varios siglos", 3, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p5);
        Pregunta p6 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": El primer libro de la Biblia es", "Éxodo", "Génesis", "Levítico", 2, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p6);
        Pregunta p7 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": Abrahán para vivir se dedicaba al siguiente oficio", "Era pastor de ovejas", "Era agricultor", "Era carpintero", 1, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p7);
        Pregunta p8 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿Cuál de las siguientes afirmaciones es verdadera?", "Nehemías tuvo que ayudar a reconstruir su tierra", "Moisés murió al cruzar el mar Rojo", "Sansón lucho contra los romanos", 1, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p8);
        Pregunta p9 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿Qué parentesco tenía Jacob con Abrahán?", "Tío", "Padre", "Nieto", 3, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p9);
        Pregunta p10 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿Que imperio dirigió Ciro?", "Griego", "Persa", "Romano", 2, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p10);
        Pregunta p11 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿Cuál de los siguientes nombres no ha recibido la tierra donde se asento el pueblo judío?", "Tierra de Canaán", "Tierra de Israel", "Tierra de Moisés", 3, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p11);
        Pregunta p12 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": El Pentateuco esta compuesto por los siguientes libros", "Genesis, Samuel, Números, Josué y Levítico", "Genesís, Éxodo, Levítico, Números y Deuteronomio", "Genesis, Samuel, Números, Josué y Levítico", 2, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p12);
        Pregunta p13 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿El Nuevo testamento está compuesto por?", "27 libros", "26 libros", "28 libros", 1, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p13);
        Pregunta p14 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": La biblia es sobretodo Palabra de Dios por eso también se la conoce como", "Relato de Dios", "El gran libro de Dios", "Sagrada Escritura", 3, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p14);
        Pregunta p15 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Cuántos animales metió Moisés en el Arca?", "Dos de cada especie", "Uno de cada especie", "No metió ninguno", 1, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p15);
        Pregunta p16 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": El Antiguo Testamento y el Nuevo resulta que son", "Igual de extensos", "El Nuevo Testamento es más extenso", "El Nuevo Testamento es menos extenso", 3, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p16);
        Pregunta p17 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": La Tierra Israel se caracteriza por un clima", "Muy humedo y lluvioso durante todo el año", "Un clima templado y suelo fertil, rodeado de zonas áridas", "Un clima seco, casí propio de un desierto, con escasez de agua", 2, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p17);
        Pregunta p18 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": La época de más paz y prosperida de Israel se da en el siguiente periodo", "Durante la época de los filisteos", "Durante la época romana", "Durante la época de la monarquía", 1, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p18);
        Pregunta p19 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Cómo se les llama a los primeros 4 libros del Nuevo Testamento?", "El Pentateuco", "Epístolas", "Los Evangelios", 3, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p19);
        Pregunta p20 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": ¿A qué compara la iglesia el Apóstol Pablo?", "A lobos vestidos de ovejas", "A un rebaño de cabras", "A un cuerpo", 3, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p20);
        Pregunta p21 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Cual es el primer libro del Nuevo Testamento?", "Génesis", "Mateo", "Apocalipsis", 2, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p21);
        Pregunta p22 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": ¿Cual libro de la Biblia no menciona el nombre de Dios?", "Rut", "2 de Crónicas", "Ester", 3, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p22);
        Pregunta p23 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿Cual es el libro con más capítulos en la Biblia?", "Exodo", "Salmos", "Apocalipsis", 2, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p23);
        Pregunta p24 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": ¿Cual era otro nombre del Apóstol Pablo?", "Saulo", "Abram", "Simón", 1, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p24);
        Pregunta p25 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Para qué dijo Jesús que había venido al mundo?", "Para condenar a los pecadores", "Para servir y rescatar", "Para preparar el camino", 2, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p25);
        Pregunta p26 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Que significa Evangelio?", "La palabra de Dios", "Es un tipo de planta", "Buenas Nuevas", 3, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p26);
        Pregunta p27 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": ¿Cual el el versículo más corto de la Biblia en español?", "Salmo 144:4", "Mateo 18:2", "Juan 11:35", 1, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p27);
        Pregunta p28 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": En 2 Reyes habla del rey Josías, ¿A qué edad empezó Josías a reinar?", "10 Años", "8 Años", "15 Años", 2, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p28);
        Pregunta p29 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": ¿Cuál capítulo en los Salmos es más largo?", "19", "119", "100", 2, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p29);
        Pregunta p30 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": En que libro esta el texto 'todo es vanidad'", "Eclesiastés", "Proverbios", "Judas", 1, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p30);
        Pregunta p31 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿Cual es el quinto mandamiento?", "No tomarás el nombre de Jehová tu Dios en Vano", "No matarés", "Honra a tu padre y a tu madre", 3, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p31);
        Pregunta p32 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": ¿Cuántos años reinó David en Jerusalén?", "40 Años", "38 Años", "33 Años", 2, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p32);
        Pregunta p33 = new Pregunta(Pregunta.DIFICULTAD_FACIL + ": ¿Quién empezó a bautizar a la gente?", "Jesús", "Juan", "Pablo", 1, Pregunta.DIFICULTAD_FACIL);
        addQuestion(p33);
        Pregunta p34 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": ¿En qué libro se encuentra los 'Señales antes del fin'?", "Mateo 24", "Marcos 24", "Apocalipsis 10", 1, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p34);
        Pregunta p35 = new Pregunta(Pregunta.DIFICULTAD_MEDIA + ": Conforme San Mateo 25, ¿cuántos talentos le dio el reino de los cielos al hombre?", "7-4-3", "5-5-5", "5-2-1", 3, Pregunta.DIFICULTAD_MEDIA);
        addQuestion(p35);
        Pregunta p36 = new Pregunta(Pregunta.DIFICULTAD_DIFICIL + ": A quien reclama Jesus en getsemaní '¿Nisiquiera una hora pudiste mantenerte despierto?'", "Juan", "Pedro", "Andres", 1, Pregunta.DIFICULTAD_DIFICIL);
        addQuestion(p36);
    }

    private void addQuestion(Pregunta pregunta) {
        ContentValues cv = new ContentValues();
        cv.put(tablaPreguntas.COLUMN_PREGUNTA_NAME, pregunta.getPreguntaName());
        cv.put(tablaPreguntas.COLUMN_OPCION1, pregunta.getOpcion1());
        cv.put(tablaPreguntas.COLUMN_OPCION2, pregunta.getOpcion2());
        cv.put(tablaPreguntas.COLUMN_OPCION3, pregunta.getOpcion3());
        cv.put(tablaPreguntas.COLUMN_RESPUESTA, pregunta.getRespuesta());
        cv.put(tablaPreguntas.COLUMN_DIFICULTAD, pregunta.getDificultad());
        if (db.insert(tablaPreguntas.TABLE_NAME, null, cv) > -1) {
            Log.i("PreguntaDBHelper", "La pregunta se agrego a la base de dadtos");
        } else {
            Log.i("PreguntaDBHelper", "La pregunta no se agrego a la base de dadtos");
        }
    }

    public ArrayList<Pregunta> getAllPreguntas () {
        ArrayList<Pregunta> preguntaList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + tablaPreguntas.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Pregunta pregunta = new Pregunta();
                pregunta.setPreguntaName(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_PREGUNTA_NAME)));
                pregunta.setOpcion1(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_OPCION1)));
                pregunta.setOpcion2(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_OPCION2)));
                pregunta.setOpcion3(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_OPCION3)));
                pregunta.setRespuesta(cursor.getInt(cursor.getColumnIndex(tablaPreguntas.COLUMN_RESPUESTA)));
                pregunta.setDificultad(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_DIFICULTAD)));
                preguntaList.add(pregunta);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return preguntaList;
    }

    public ArrayList<Pregunta> getPreguntas (String dificultad) {
        ArrayList<Pregunta> preguntaList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{dificultad};
        Cursor cursor = db.rawQuery(" Select * from " + tablaPreguntas.TABLE_NAME + " WHERE " + tablaPreguntas.COLUMN_DIFICULTAD + " = ?", selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                Pregunta pregunta = new Pregunta();
                pregunta.setPreguntaName(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_PREGUNTA_NAME)));
                pregunta.setOpcion1(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_OPCION1)));
                pregunta.setOpcion2(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_OPCION2)));
                pregunta.setOpcion3(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_OPCION3)));
                pregunta.setRespuesta(cursor.getInt(cursor.getColumnIndex(tablaPreguntas.COLUMN_RESPUESTA)));
                pregunta.setDificultad(cursor.getString(cursor.getColumnIndex(tablaPreguntas.COLUMN_DIFICULTAD)));
                preguntaList.add(pregunta);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return preguntaList;
    }
}
