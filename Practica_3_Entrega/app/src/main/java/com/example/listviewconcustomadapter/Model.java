package com.example.listviewconcustomadapter;

public class Model {
    private String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public int get(int position) {
        return position;
    }
}
