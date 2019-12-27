package com.nehemias.preguntasyrespuestas;

import android.os.Parcel;
import android.os.Parcelable;

public class Pregunta implements Parcelable {

    public static final String DIFICULTAD_CERO = "Todos";
    public static final String DIFICULTAD_FACIL = "Facil";
    public static final String DIFICULTAD_MEDIA = "Media";
    public static final String DIFICULTAD_DIFICIL = "Dificil";

    private String preguntaName;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private int respuesta;
    private String dificultad;

    public Pregunta() {}

    public Pregunta(String preguntaName, String opcion1, String opcion2, String opcion3, int respuesta, String dificultad) {
        this.preguntaName = preguntaName;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.respuesta = respuesta;
        this.dificultad = dificultad;
    }

    protected Pregunta(Parcel in) {
        preguntaName = in.readString();
        opcion1 = in.readString();
        opcion2 = in.readString();
        opcion3 = in.readString();
        respuesta = in.readInt();
        dificultad = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(preguntaName);
        dest.writeString(opcion1);
        dest.writeString(opcion2);
        dest.writeString(opcion3);
        dest.writeInt(respuesta);
        dest.writeString(dificultad);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pregunta> CREATOR = new Creator<Pregunta>() {
        @Override
        public Pregunta createFromParcel(Parcel in) {
            return new Pregunta(in);
        }

        @Override
        public Pregunta[] newArray(int size) {
            return new Pregunta[size];
        }
    };

    public String getPreguntaName() {
        return preguntaName;
    }

    public void setPreguntaName(String preguntaName) {
        this.preguntaName = preguntaName;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public static String[] getAllDificultades(){
        return new String[]{DIFICULTAD_CERO,DIFICULTAD_FACIL,DIFICULTAD_MEDIA,DIFICULTAD_DIFICIL};
    }
}
