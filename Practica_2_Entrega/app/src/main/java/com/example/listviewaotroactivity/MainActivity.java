package com.example.listviewaotroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> Animales = new ArrayList<>();
        Animales.add("Perro");
        Animales.add("Gato");
        Animales.add("Koala");
        Animales.add("Mono");
        Animales.add("Serpiente");
        Animales.add("Vaca");
        Animales.add("Lombriz");
        Animales.add("Raton");

        ListView listView = findViewById(R.id.lvAnimales);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Animales);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, SegundoActivity.class);
                intent.putExtra("prueba",Animales.get(position));
                startActivity(intent);
            }
        });

    }
}
