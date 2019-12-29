package com.example.reproductordesonido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Sonidos> sonidosList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);

        sonidosList =  Sonidos.getAllSonidos(this);

        SonidosAdapter adapter = new SonidosAdapter(this, sonidosList);
        gridView.setAdapter(adapter);

    }
}
