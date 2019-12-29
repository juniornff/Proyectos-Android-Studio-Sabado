package com.example.listadetareas;

import com.example.listadetareas.Model.ToDo;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ToDohelper {

    private SharedPreferences preferences;
    private Context context;

    public ToDohelper(Context context){
        Log.i("ToDoHelper", "Se iniciara el context");
        this.context = context;
        this.preferences = context.getSharedPreferences("ToDoPreference",0);
        Log.i("ToDoHelper", "Se inicio el context");
    }

    public void AddToDo(List<ToDo> toDo){

        Log.i("ToDoHelper", "Se iniciara el AddToDo");
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(toDo);
        editor.clear();
        editor.putString("list",json);
        editor.commit();
        Log.i("ToDoHelper", "Funcionó el AddToDo");
    }


    public List<ToDo> getAllToDo(){
        Log.i("ToDoHelper", "Se iniciara el getAllToDo");
        List<ToDo> todo;
        Gson gson = new Gson();
        String json = preferences.getString("list", null);
        Type type = new TypeToken<List<ToDo>>() {}.getType();
        todo = gson.fromJson(json,type);

        if (todo == null){
            todo = new ArrayList<>();
        }
        Log.i("ToDoHelper", "Funcionó el getAllToDo");
        return  todo;
    }

}
