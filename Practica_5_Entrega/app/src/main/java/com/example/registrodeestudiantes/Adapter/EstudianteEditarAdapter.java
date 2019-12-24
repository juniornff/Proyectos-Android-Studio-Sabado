package com.example.registrodeestudiantes.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registrodeestudiantes.EstudianteHelper;
import com.example.registrodeestudiantes.Model.Estudiante;
import com.example.registrodeestudiantes.R;

import java.util.List;

public class EstudianteEditarAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener {

    private List<Estudiante> estudianteList;
    private Context context;
    private Estudiante estu;
    private EstudianteHelper helper;

    public EstudianteEditarAdapter(Context context, List<Estudiante> estudianteList) {
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

        EditText etName = view.findViewById(R.id.etName);
        EditText etLastName = view.findViewById(R.id.etlastName);
        EditText etPhone = view.findViewById(R.id.etPhone);
        EditText etEmail = view.findViewById(R.id.etEmail);
        Spinner  spDia = view.findViewById(R.id.spDay);
        ArrayAdapter<CharSequence> adapterDia = ArrayAdapter.createFromResource(context,R.array.Dia, android.R.layout.simple_spinner_item);
        adapterDia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDia.setAdapter(adapterDia);
        spDia.setOnItemSelectedListener(this);
        Spinner  spMes = view.findViewById(R.id.spMonth);
        Spinner  spAño = view.findViewById(R.id.spYear);
        Spinner  spSexo = view.findViewById(R.id.spSexo);
        Spinner  spCarrera = view.findViewById(R.id.spCarrera);
        Button btnGuardar = view.findViewById(R.id.btnGuardar);

        estu = (Estudiante)getItem(position);


        return view;
    }

    @Override
    public void notifyDataSetChanged() { super.notifyDataSetChanged(); }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
