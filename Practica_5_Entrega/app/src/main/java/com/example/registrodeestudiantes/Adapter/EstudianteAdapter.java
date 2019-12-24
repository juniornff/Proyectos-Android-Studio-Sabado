package com.example.registrodeestudiantes.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registrodeestudiantes.EstudianteHelper;
import com.example.registrodeestudiantes.Model.Estudiante;
import com.example.registrodeestudiantes.R;

import java.util.List;

public class EstudianteAdapter extends BaseAdapter {

    private List<Estudiante> estudianteList;
    private Context context;
    private Estudiante estu;
    private EstudianteHelper helper;

    public EstudianteAdapter (Context context, List<Estudiante> estudianteList) {
        this.context = context;
        this.estudianteList = estudianteList;
        this.helper = new EstudianteHelper(context);
    }

    @Override
    public int getCount() {
        return estudianteList.size();
    }

    @Override
    public Object getItem(int position) {
        return estudianteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return estudianteList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        TextView tvName = view.findViewById(R.id.tvName);
        ImageView ivDelete = view.findViewById(R.id.ivDelete);

        estu = (Estudiante)getItem(position);

        tvName.setText(estu.getName());

        actionDelete(ivDelete,estudianteList, estu.getId());

        return view;
    }

    @Override
    public void notifyDataSetChanged() { super.notifyDataSetChanged(); }

    private void actionDelete(final ImageView imageView,final List<Estudiante> estudianteList, final int id){

        Log.i("actionDelete", "Se iniciara el actionDelete metodo");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("actionDelete", "Se removera el item");
                estudianteList.remove(id);
                if (helper.deleteEstudiante(id) > 0) {
                    Toast.makeText(context,"Estudiante Eliminado", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    Log.i("actionDelete", "Se removio el item");
                }else {
                    Log.i("actionDelete", "No se removio el item");
                }
            }
        });
        Log.i("actionDelete", "Finalizo el actionDelete metodo");
    }
}
