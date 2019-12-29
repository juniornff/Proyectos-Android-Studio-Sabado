package com.example.registrodeestudiantesmvp.view.adapater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registrodeestudiantesmvp.EstudiantesMVP;
import com.example.registrodeestudiantesmvp.model.entities.Estudiante;
import com.example.registrodeestudiantesmvp.model.services.EstudianteServices;
import com.example.registrodeestudiantesmvp.R;
import com.example.registrodeestudiantesmvp.presenter.EstudiantesPresenter;

import java.util.List;

public class MainActivityAdapter extends BaseAdapter implements EstudiantesMVP.viewAdapter {

    private List<Estudiante> estudianteList;
    private Context context;
    private Estudiante estu;
    private EstudianteServices helper;

    private EstudiantesMVP.presenter presenter;

    public MainActivityAdapter (Context context, List<Estudiante> estudianteList) {
        this.context = context;
        this.estudianteList = estudianteList;
        this.helper = new EstudianteServices(context);
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

        presenter = new EstudiantesPresenter(this, context);

        estu = (Estudiante)getItem(position);

        tvName.setText(estu.getName());

        actionDelete(ivDelete,estudianteList, estu.getId());

        return view;
    }

    @Override
    public void notifyDataSetChanged() { super.notifyDataSetChanged(); }

    private void actionDelete(final ImageView imageView,final List<Estudiante> estudianteList, final long id){

        Log.i("actionDelete", "Se iniciara el actionDelete metodo");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("actionDelete", "Se removera el item");
                estudianteList.remove(id);
                presenter.deleteEstudiante(id);
            }
        });
        Log.i("actionDelete", "Finalizo el actionDelete metodo");
    }

    @Override
    public void notifyEstudianteDeleted (String msg) {
        notifyDataSetChanged();
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}
