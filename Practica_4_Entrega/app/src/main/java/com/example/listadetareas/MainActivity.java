package com.example.listadetareas;

import com.example.listadetareas.Model.ToDo;
import com.example.listadetareas.Adapter.ToDoAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ToDo> toDoList = new ArrayList<>();
    private EditText editText;
    private AlertDialog alertDialog;
    private ToDohelper toDo;
    private ToDoAdapter adapter;
    private int numero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "Se va a iniciar el Ationbar");
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.app_name));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.ic_assignment_turned_in_black_24dp));
        Log.i("MainActivity", "Funcionó el Ationbar");

        Log.i("MainActivity", "Se va a asignar el todolist al listview");
        ListView listView = findViewById(R.id.lvTodoList);
        Log.i("MainActivity", "Se asigno el todolist al listview");

        Log.i("MainActivity", "Se inicia un Todohelper");
        toDo = new ToDohelper(this);
        Log.i("MainActivity", "Se inicio un Todohelper");

        Log.i("MainActivity", "Se acoplaran los datos del todohelper al todolist");
        toDoList = toDo.getAllToDo();
        Log.i("MainActivity", "Se acoplaron los datos del todohelper al todolist");

        Log.i("MainActivity", "Se creara el nuevo adapter con el Todoalist");
        adapter = new ToDoAdapter(this, toDoList);
        Log.i("MainActivity", "Se creo el nuevo adapter con el Todoalist");

        Log.i("MainActivity", "Se seteara el adapter al listview");
        listView.setAdapter(adapter);
        Log.i("MainActivity", "Se seteo el adapter al listview");

        crearAlerta();

    }

    private void crearAlerta(){
        editText = new EditText(this);

        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Agregar Tarea")
                .setMessage("Que planeas hacer?")
                .setView(editText)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("MenuOptions", "Le dio click agregar");
                        String tarea = editText.getText().toString();

                        if (!tarea.trim().isEmpty())
                        {
                            if (toDoList.size() != 0){
                                while (Long.valueOf(numero).equals(toDoList.get(numero).getId())){
                                    numero++;
                                }
                            }

                            Log.i("MainActivity", "Se añadira la nueva tarea");
                            ToDo t = new ToDo();
                            t.setId((long) numero);
                            t.setDone(false);
                            t.setFavorite(false);
                            t.setTaskName(tarea);
                            t.setDescription("");
                            toDoList.add(t);
                            toDo.AddToDo(toDoList);
                            numero++;
                            Log.i("MainActivity", "Se añadio la nueva tarea");
                            reloadAdapter();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("MenuOptions", "Le dio click a cancelar");
                    }
                })
                .create();
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

        if (item.getItemId() == R.id.menuItemNew) {
            Log.i("MenuOptions", "Has seleccionado la opcion de New");
            editText.setText("");
            alertDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
