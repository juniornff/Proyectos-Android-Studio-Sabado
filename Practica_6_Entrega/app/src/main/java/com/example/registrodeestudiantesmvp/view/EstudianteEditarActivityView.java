package com.example.registrodeestudiantesmvp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registrodeestudiantesmvp.EstudiantesMVP;
import com.example.registrodeestudiantesmvp.presenter.EstudiantesPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.registrodeestudiantesmvp.model.services.EstudianteServices;
import com.example.registrodeestudiantesmvp.model.entities.Estudiante;
import com.example.registrodeestudiantesmvp.R;

import java.lang.reflect.Type;
import java.util.List;

public class EstudianteEditarActivityView extends AppCompatActivity implements AdapterView.OnItemSelectedListener,EstudiantesMVP.editarView {

    private EstudianteServices helper;
    private List<Estudiante> estudianteList;
    private Estudiante estu;
    private SharedPreferences preferences;

    private EditText etName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etEmail;
    private Spinner spDia;
    private int dia;
    private Spinner spMes;
    private int mes;
    private Spinner spYear;
    private int year;
    private Spinner spSexo;
    private int sexo;
    private Spinner spCarrera;
    private int carrera;

    private EstudiantesMVP.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_editar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.edit));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(getResources().getDrawable(R.drawable.ic_person));

        presenter = new EstudiantesPresenter(this, this);

        helper = new EstudianteServices(this);

        estudianteList =helper.getAllEstudianteList();

        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etlastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        spDia = findViewById(R.id.spDay);
        ArrayAdapter<CharSequence> adapterDia = ArrayAdapter.createFromResource(this,R.array.Dia, android.R.layout.simple_spinner_item);
        adapterDia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDia.setAdapter(adapterDia);
        spDia.setOnItemSelectedListener(this);
        spMes = findViewById(R.id.spMonth);
        ArrayAdapter<CharSequence> adapterMes = ArrayAdapter.createFromResource(this,R.array.Mes, android.R.layout.simple_spinner_item);
        adapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMes.setAdapter(adapterMes);
        spMes.setOnItemSelectedListener(this);
        spYear = findViewById(R.id.spYear);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this,R.array.Año, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYear.setAdapter(adapterYear);
        spYear.setOnItemSelectedListener(this);
        spSexo = findViewById(R.id.spSexo);
        ArrayAdapter<CharSequence> adapterSexo = ArrayAdapter.createFromResource(this,R.array.Sexos, android.R.layout.simple_spinner_item);
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSexo.setAdapter(adapterSexo);
        spSexo.setOnItemSelectedListener(this);
        spCarrera = findViewById(R.id.spCarrera);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this,R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCarrera.setAdapter(adapterCarrera);
        spCarrera.setOnItemSelectedListener(this);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        boolean seleccionado = getIntent().getBooleanExtra("seleccionado", false);

        if (seleccionado) {
            Gson gson = new Gson();
            preferences = getSharedPreferences("EstudiantePreference",0);
            String json = preferences.getString("mandado", null);
            Type type = new TypeToken<Estudiante>() {}.getType();
            estu = gson.fromJson(json,type);
            etName.setText(estu.getName());
            etLastName.setText(estu.getLastName());
            etPhone.setText(estu.getPhone());
            etEmail.setText(estu.getEmail());
            spDia.setSelection(estu.getDay());
            spMes.setSelection(estu.getMonth());
            spYear.setSelection(estu.getYear());
            spSexo.setSelection(estu.getSexo());
            spCarrera.setSelection(estu.getCarrera());
        } else {
            btnGuardar.setText("Nuevo Estudiante");
        }
    }

    public void onClick (View view){

        boolean seleccionado = getIntent().getBooleanExtra("seleccionado", false);

        if (seleccionado){
            Estudiante actualizado = new Estudiante();
            actualizado.setId(estu.getId());
            actualizado.setName(etName.getText().toString());
            actualizado.setLastName(etLastName.getText().toString());
            actualizado.setPhone(etPhone.getText().toString());
            actualizado.setEmail(etEmail.getText().toString());
            actualizado.setDay(dia);
            actualizado.setMonth(mes);
            actualizado.setYear(year);
            actualizado.setSexo(sexo);
            actualizado.setCarrera(carrera);
            estudianteList.remove(actualizado.getId());
            estudianteList.add(actualizado);

            presenter.updateEstudiante(actualizado);

        }else {

            Estudiante nuevo = new Estudiante();
            nuevo.setId(helper.getLastEstuId());
            nuevo.setName(etName.getText().toString());
            nuevo.setLastName(etLastName.getText().toString());
            nuevo.setPhone(etPhone.getText().toString());
            nuevo.setEmail(etEmail.getText().toString());
            nuevo.setDay(dia);
            nuevo.setMonth(mes);
            nuevo.setYear(year);
            nuevo.setSexo(sexo);
            nuevo.setCarrera(carrera);
            estudianteList.add(nuevo);

            presenter.addEstudiante(nuevo);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() == spDia.getAdapter()){
            dia = position;
            Log.i("EditarActivitySpinner", "Se guardo la seleccion de dia: " + parent.getItemAtPosition(position).toString() + " en la posision: " + dia);
        }
        if (parent.getAdapter() == spMes.getAdapter()){
            mes = position;
            Log.i("EditarActivitySpinner", "Se guardo la seleccion de mes: " + parent.getItemAtPosition(position).toString() + " en la posision: " + mes);
        }
        if (parent.getAdapter() == spYear.getAdapter()){
            year = position;
            Log.i("EditarActivitySpinner", "Se guardo la seleccion de year: " + parent.getItemAtPosition(position).toString() + " en la posision: " + year);
        }
        if (parent.getAdapter() == spSexo.getAdapter()){
            sexo = position;
            Log.i("EditarActivitySpinner", "Se guardo la seleccion de sexo: " + parent.getItemAtPosition(position).toString() + " en la posision: " + sexo);
        }
        if (parent.getAdapter() == spCarrera.getAdapter()){
            carrera = position;
            Log.i("EditarActivitySpinner", "Se guardo la seleccion de carrera: " + parent.getItemAtPosition(position).toString() + " en la posision: " + carrera);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void showEstudiante(Estudiante estudiante) {

    }

    @Override
    public void notifyEstudianteAdded(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EstudianteEditarActivityView.this, MainActivityView.class);
        startActivity(intent);
    }

    @Override
    public void notifyEstudianteUpdated(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EstudianteEditarActivityView.this, MainActivityView.class);
        startActivity(intent);
    }
}
