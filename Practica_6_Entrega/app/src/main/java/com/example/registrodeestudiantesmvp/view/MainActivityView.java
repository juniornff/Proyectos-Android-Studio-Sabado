package com.example.registrodeestudiantesmvp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.registrodeestudiantesmvp.EstudiantesMVP;
import com.example.registrodeestudiantesmvp.model.entities.Estudiante;
import com.example.registrodeestudiantesmvp.presenter.EstudiantesPresenter;
import com.example.registrodeestudiantesmvp.view.adapater.MainActivityAdapter;
import com.example.registrodeestudiantesmvp.R;
import com.google.gson.Gson;

import java.util.List;

public class MainActivityView extends AppCompatActivity implements EstudiantesMVP.view{

    private Estudiante estudiante;
    private MainActivityAdapter adapter;
    private SharedPreferences preferences;
    private ListView listView;

    private EstudiantesMVP.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.app_name));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.ic_person));

        listView = findViewById(R.id.lvEstudiantesList);

        presenter = new EstudiantesPresenter(this, this);

        presenter.getAllEstudiantes();

    }


    @Override
    protected void onResume() {
        super.onResume();
        reloadAdapter();
    }

    private void reloadAdapter(){
        Log.i("MainActivity", "Se reiniciara el adapter");
        adapter.notifyDataSetChanged();
        Log.i("MainActivity", "Se reinicio el adapter");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("MainActivity", "Se inflara el menu");
        getMenuInflater().inflate(R.menu.main,menu);
        Log.i("MainActivity", "Se inflo el menu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuItemNew:
                Log.i("MenuOptions", "Has seleccionado la opcion de New");
                Intent intent = new Intent(MainActivityView.this, EstudianteEditarActivityView.class);
                intent.putExtra("seleccionado", false);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showEstudiantes(List<Estudiante> estudianteList) {
        final List<Estudiante> estudianteList1Final = estudianteList;

        adapter = new MainActivityAdapter(this, estudianteList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivityView.this, EstudianteEditarActivityView.class);
                intent.putExtra("seleccionado", true);

                Log.i("MainActivity", "Se seleccionara un estudiante y se mandara por SP");
                preferences = getSharedPreferences("EstudiantePreference",0);
                SharedPreferences.Editor editor = preferences.edit();
                Gson gson = new Gson();
                estudiante = estudianteList1Final.get(position);
                String json = gson.toJson(estudiante);
                editor.clear();
                editor.putString("mandado",json);
                editor.apply();
                Log.i("MainActivity", "Se selecciono un estudiante y se mando por SP");

                startActivity(intent);
            }
        });
    }
}
