package com.example.listviewconcustomadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Model> model = new ArrayList<>();

        Model AnimalesPrueba = new Model();

        AnimalesPrueba.setNombre("Opcion 0");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 1");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 2");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 3");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 4");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 5");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 6");
        model.add(AnimalesPrueba);

        AnimalesPrueba = new Model();
        AnimalesPrueba.setNombre("Opcion 7");
        model.add(AnimalesPrueba);

        ListView listView = findViewById(R.id.lvAnimales);

        adapter = new Adapter(this, model);

        listView.setAdapter(adapter);

        final Model finalAnimalesPrueba = AnimalesPrueba;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, SegundoActivity.class);
                String Animal = String.valueOf(finalAnimalesPrueba.get(position));
                intent.putExtra("prueba", Animal);
                startActivity(intent);
            }
        });
    }
}
