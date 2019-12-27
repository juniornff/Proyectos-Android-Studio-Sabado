package com.nehemias.preguntasyrespuestas;

import android.provider.BaseColumns;

public final class PreguntaContract {

    public PreguntaContract() {}

    public static class tablaPreguntas implements BaseColumns {
        public static final String TABLE_NAME = "Preguntas_Respuestas";
        public static final String COLUMN_PREGUNTA_NAME = "pregunta_name";
        public static final String COLUMN_OPCION1 = "opcion1";
        public static final String COLUMN_OPCION2 = "opcion2";
        public static final String COLUMN_OPCION3 = "opcion3";
        public static final String COLUMN_RESPUESTA = "respuesta";
        public static final String COLUMN_DIFICULTAD = "dificultad";
    }

}
