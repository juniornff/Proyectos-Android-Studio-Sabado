package com.nehemias.preguntasyrespuestas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PREGUNTAS = 1;
    public static final String EXTRA_DIFICULTAD = "extraDificultad";

    public static final String sharedPreferences = "sharedPreferences";
    public static final String keyPuntuacion = "keyPuntuacion";

    private TextView tvPuntuacion;
    private Spinner spDificultad;

    private int puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPuntuacion = findViewById(R.id.tvHighscore);
        spDificultad = findViewById(R.id.spDificultad);

        String[] nivelesDificultad = Pregunta.getAllDificultades();

        ArrayAdapter<String> dificultadAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nivelesDificultad);
        dificultadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDificultad.setAdapter(dificultadAdapter);

        cargarPuntuacion();

        Button btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empezarQuiz();
            }
        });

    }

    private void empezarQuiz() {
        String dificultad = spDificultad.getSelectedItem().toString();
        Intent intent = new Intent(MainActivity.this,PreguntasActivity.class);
        intent.putExtra(EXTRA_DIFICULTAD, dificultad);
        startActivityForResult(intent,REQUEST_CODE_PREGUNTAS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PREGUNTAS){
            if (resultCode == RESULT_OK) {
                int resultado = data.getIntExtra(PreguntasActivity.EXTRA_PUNTUACION, 0);
                if (resultado > puntuacion){
                    actualizarPuntuacion(resultado);
                }
            }
        }
    }

    private void cargarPuntuacion(){
        SharedPreferences preferences = getSharedPreferences(sharedPreferences, MODE_PRIVATE);
        puntuacion = preferences.getInt(keyPuntuacion, 0);
        tvPuntuacion.setText("Puntuacion Mas Alta: " + puntuacion);
    }

    private void actualizarPuntuacion(int puntuacionNueva) {
        puntuacion = puntuacionNueva;
        tvPuntuacion.setText("Puntuacion Mas Alta: " + puntuacion);

        SharedPreferences preferences = getSharedPreferences(sharedPreferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(keyPuntuacion, puntuacion);
        editor.apply();
    }
}
