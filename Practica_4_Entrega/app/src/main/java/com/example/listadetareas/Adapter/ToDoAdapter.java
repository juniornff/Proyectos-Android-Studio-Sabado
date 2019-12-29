package com.example.listadetareas.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import com.example.listadetareas.Model.ToDo;
import com.example.listadetareas.R;
import com.example.listadetareas.ToDohelper;

public class ToDoAdapter extends BaseAdapter {

    private List<ToDo> toDoList;
    private ToDo task;
    private Context context;
    private ToDohelper toDo;
    private AlertDialog alertDialog;
    private EditText editText;

    public ToDoAdapter(Context context, List<ToDo> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
        this.toDo = new ToDohelper(context);
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return toDoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("ToDoAdapter", "Se inflara el view");
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        Log.i("ToDoAdapter", "Se inflo el view");

        Log.i("ToDoAdapter", "Se asignaran los valores de los elementos");
        CheckBox checkBox = view.findViewById(R.id.cbDone);
        CheckBox favorite = view.findViewById(R.id.cbFavorite);
        TextView textView = view.findViewById(R.id.tvName);
        ImageView copy = view.findViewById(R.id.ivCopy);
        ImageView delete = view.findViewById(R.id.ivDelete);
        ImageView more = view.findViewById(R.id.ivMore);
        Log.i("ToDoAdapter", "Se asignaron los valores de los elementos");

        Log.i("ToDoAdapter", "Se crearan la variable task con atributo todolist");
        task = (ToDo)getItem(position);
        Log.i("ToDoAdapter", "Se creo la variable task con atributo todolist");

        Log.i("ToDoAdapter", "Se setearan los valores de los checkboxes y el text");
        checkBox.setChecked(task.getDone());
        favorite.setChecked(task.getFavorite());
        textView.setText(task.getTaskName());
        Log.i("ToDoAdapter", "Se setearon los valores de los checkboxes y el text");

        Log.i("ToDoAdapter", "Se iniciara el addClickHandlerToCheckBox");
        addClickHandlerToCheckBox(checkBox, task.getId());
        Log.i("ToDoAdapter", "Se inicio el addClickHandlerToCheckBox");

        Log.i("ToDoAdapter", "Se iniciara el addClickHandlerToFavorite");
        addClickHandlerToFavorite(favorite, task.getId());
        Log.i("ToDoAdapter", "Se inicio el addClickHandlerToFavorite");

        Log.i("ToDoAdapter", "Se iniciara el actionCopy");
        actionCopy(copy, task.getId());
        Log.i("ToDoAdapter", "Se inicio el actionCopy");

        Log.i("ToDoAdapter", "Se iniciara el actionDelete");
        actionDelete(delete, task.getId());
        Log.i("ToDoAdapter", "Se inicio el actionDelete");

        Log.i("ToDoAdapter", "Se iniciara el actionMore");
        actionMore(more, task.getId());
        Log.i("ToDoAdapter", "Se inicio el actionMore");

        return view;
    }

    @Override
    public void notifyDataSetChanged() { super.notifyDataSetChanged(); }

    private void addClickHandlerToCheckBox(final CheckBox checkbox, final long id) {
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                ToDo c = new ToDo();
                c.setId(id);
                c.setDone(checkbox.isChecked());
                c.setFavorite(toDoList.get((int) id).getFavorite());
                c.setTaskName(toDoList.get((int) id).getTaskName());
                c.setDescription(toDoList.get((int) id).getDescription());
                toDoList.set((int) id, c);
                toDo.AddToDo(toDoList);
                Toast.makeText(context,"Tarea Completada", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
            }
        );
    }

    private void addClickHandlerToFavorite(final CheckBox checkbox, final long id) {
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                ToDo c = new ToDo();
                c.setId(id);
                c.setDone(toDoList.get((int) id).getDone());
                c.setFavorite(checkbox.isChecked());
                c.setTaskName(toDoList.get((int) id).getTaskName());
                c.setDescription(toDoList.get((int) id).getDescription());
                toDoList.set((int) id, c);
                toDo.AddToDo(toDoList);
                Toast.makeText(context,"Tarea Marcada como Importante", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
            }
        );
    }

    private void actionCopy(final ImageView imageView, final long id){

        Log.i("actionCopy", "Se iniciara el actionCopy metodo");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("actionCopy", "Se Copiara el item");
                ToDo c = new ToDo();
                Long idFinal = id+1;
                while (idFinal == toDoList.get((int) id).getId()){
                    idFinal++;
                }
                c.setId(idFinal);
                c.setDone(toDoList.get((int) id).getDone());
                c.setFavorite(toDoList.get((int) id).getFavorite());
                c.setTaskName(toDoList.get((int) id).getTaskName() + " - Copia");
                c.setDescription(toDoList.get((int) id).getDescription());
                toDoList.add((c));
                toDo.AddToDo(toDoList);
                idFinal++;
                Toast.makeText(context,"Tarea copiada", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                Log.i("actionCopy", "Se Copio el item");
            }
        });
        Log.i("actionCopy", "Se inicio el actionCopy metodo");
    }

    private void actionDelete(final ImageView imageView, final long id){

        Log.i("actionDelete", "Se iniciara el actionDelete metodo");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("actionDelete", "Se removera el item");
                toDoList.remove((int) id);
                toDo.AddToDo(toDoList);
                Toast.makeText(context,"Tarea Eliminada", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                Log.i("actionDelete", "Se removio el item");
            }
        });
        Log.i("actionDelete", "Se inicio el actionDelete metodo");
    }

    private void actionMore(final ImageView imageView, final long id){

        Log.i("actionMore", "Se iniciara el actionMore metodo");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = new EditText(context);
                editText.setText(toDoList.get((int)id).getDescription() , TextView.BufferType.EDITABLE);

                alertDialog = new AlertDialog.Builder(context)
                        .setTitle(toDoList.get((int)id).getTaskName() + " Detalle")
                        .setMessage("Detalles de la tarea")
                        .setView(editText)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tarea = editText.getText().toString();
                                ToDo c = new ToDo();
                                c.setId(id);
                                c.setDone(toDoList.get((int) id).getDone());
                                c.setFavorite(toDoList.get((int) id).getFavorite());
                                c.setTaskName(toDoList.get((int) id).getTaskName());
                                c.setDescription(tarea);
                                toDoList.set((int) id, c);
                                Toast.makeText(context,"Detalle Modificado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();

                alertDialog.show();
            }
        });
        notifyDataSetChanged();
        Log.i("actionMore", "Se inicio el actionDelete metodo");
    }
}
