package com.nehemias.preguntasyrespuestas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class PreguntasActivity extends AppCompatActivity {

    public static final String EXTRA_PUNTUACION = "extraPuntuacion";
    private static long CONTEO_EN_MILI = 30000;

    private static final String KEY_PUNTUACION = "keyPuntuacion";
    private static final String KEY_CONTEO_PREGUNTA = "keyConteoPregunta";
    private static final String KEY_MILIS_FALTANTES = "keyMilisFaltantes";
    private static final String KEY_RESPONDIDO = "keyRespondido";
    private static final String KEY_LISTA_PREGUNTAS = "keyListaPreguntas";

    private TextView tvPregunta;
    private TextView tvScore;
    private TextView tvPregunascount;
    private TextView tvDificultad;
    private TextView tvConteo;
    private RadioGroup rgGrupo;
    private RadioButton rbOpcion1;
    private RadioButton rbOpcion2;
    private RadioButton rbOpcion3;
    private Button btnConfirmarNext;

    private ColorStateList colorStateList;
    private ColorStateList colorStateListConteo;

    private CountDownTimer countDownTimer;
    private long tiempoRestanteMili;

    private ArrayList<Pregunta> preguntaList;
    private int contadorPreguntas;
    private int contadorTotalPreguntas;
    private Pregunta preguntaActual;

    private String dificultad;

    private int score;
    private boolean respuesta;

    private long atrasTiempoPresionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        tvPregunta = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvPregunascount = findViewById(R.id.tvQuestionCount);
        tvDificultad = findViewById(R.id.tvDificultad);
        tvConteo = findViewById(R.id.tvCountdown);
        rgGrupo = findViewById(R.id.rgQuestions);
        rbOpcion1 = findViewById(R.id.rbRespuesta1);
        rbOpcion2 = findViewById(R.id.rbRespuesta2);
        rbOpcion3 = findViewById(R.id.rbRespuesta3);
        btnConfirmarNext = findViewById(R.id.btnConfirmNext);

        colorStateList = rbOpcion1.getTextColors();
        colorStateListConteo = tvConteo.getTextColors();

        Intent intent = getIntent();
        dificultad = intent.getStringExtra(MainActivity.EXTRA_DIFICULTAD);

        tvDificultad.setText("Dificultad: " + dificultad);

        if(savedInstanceState == null){
            PreguntaDBHelper dbHelper = new PreguntaDBHelper(this);

            switch (dificultad) {
                case Pregunta.DIFICULTAD_CERO:
                    preguntaList = dbHelper.getAllPreguntas();
                    break;
                case Pregunta.DIFICULTAD_FACIL:
                    preguntaList = dbHelper.getPreguntas(Pregunta.DIFICULTAD_FACIL);
                    break;
                case Pregunta.DIFICULTAD_MEDIA:
                    preguntaList = dbHelper.getPreguntas(Pregunta.DIFICULTAD_MEDIA);
                    break;
                case Pregunta.DIFICULTAD_DIFICIL:
                    preguntaList = dbHelper.getPreguntas(Pregunta.DIFICULTAD_DIFICIL);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dificultad);
            }

            contadorTotalPreguntas = preguntaList.size();
            Collections.shuffle(preguntaList);

            mostrarSigtePregunta();
        } else {
            preguntaList = savedInstanceState.getParcelableArrayList(KEY_LISTA_PREGUNTAS);
            contadorTotalPreguntas = preguntaList.size();
            contadorPreguntas = savedInstanceState.getInt(KEY_CONTEO_PREGUNTA);
            preguntaActual = preguntaList.get(contadorPreguntas - 1);
            score = savedInstanceState.getInt(KEY_PUNTUACION);
            tiempoRestanteMili = savedInstanceState.getLong(KEY_MILIS_FALTANTES);
            respuesta = savedInstanceState.getBoolean(KEY_RESPONDIDO);

            if (!respuesta) {
                empezarConteo();
            } else {
                actualizarTextoConteo();
                mostrarRespuesta();
            }
        }

        btnConfirmarNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!respuesta) {
                    if (rbOpcion1.isChecked() || rbOpcion2.isChecked() || rbOpcion3.isChecked()) {
                        comprobarRespuesta();
                    } else {
                        Toast.makeText(PreguntasActivity.this, "Por favir Selecciona una opcion",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mostrarSigtePregunta();
                }
            }
        });
    }

    private void mostrarSigtePregunta(){
        rbOpcion1.setTextColor(colorStateList);
        rbOpcion2.setTextColor(colorStateList);
        rbOpcion3.setTextColor(colorStateList);
        rgGrupo.clearCheck();

        if (contadorPreguntas < contadorTotalPreguntas){
            preguntaActual = preguntaList.get(contadorPreguntas);

            tvPregunta.setText(preguntaActual.getPreguntaName());
            rbOpcion1.setText(preguntaActual.getOpcion1());
            rbOpcion2.setText(preguntaActual.getOpcion2());
            rbOpcion3.setText(preguntaActual.getOpcion3());

            contadorPreguntas++;
            tvPregunascount.setText("Pregunta: " + contadorPreguntas + "/" + contadorTotalPreguntas);

            respuesta = false;
            btnConfirmarNext.setText("Confirmar");

            tiempoRestanteMili = CONTEO_EN_MILI;
            empezarConteo();
        } else {
            acabarPreguntas();
        }
    }

    private void empezarConteo() {
        countDownTimer = new CountDownTimer(tiempoRestanteMili, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoRestanteMili = millisUntilFinished;
                actualizarTextoConteo();
            }

            @Override
            public void onFinish() {
                tiempoRestanteMili = 0;
                actualizarTextoConteo();
                comprobarRespuesta();
            }
        }.start();
    }

    private void actualizarTextoConteo() {
        int min = (int) (tiempoRestanteMili / 1000) / 60;
        int sec = (int) (tiempoRestanteMili / 1000) % 60;

        String tiempoFormado = String.format(Locale.getDefault(), "%02d:%02d", min, sec);

        tvConteo.setText(tiempoFormado);

        if (tiempoRestanteMili < 10000) {
            tvConteo.setTextColor(Color.RED);
        } else {
            tvConteo.setTextColor(colorStateListConteo);
        }
    }

    private void comprobarRespuesta (){
        respuesta = true;

        countDownTimer.cancel();

        RadioButton rbseleccionado = findViewById(rgGrupo.getCheckedRadioButtonId());
        int respuestaNumero = rgGrupo.indexOfChild(rbseleccionado) + 1;

        if (respuestaNumero == preguntaActual.getRespuesta()){
            switch (preguntaActual.getDificultad()) {
                case Pregunta.DIFICULTAD_FACIL:
                    score++;
                    break;
                case Pregunta.DIFICULTAD_MEDIA:
                    score = score + 2;
                    break;
                case Pregunta.DIFICULTAD_DIFICIL:
                    score = score + 3;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dificultad);
            }
            tvScore.setText("Puntaje: " + score);
        }

        mostrarRespuesta();
    }

    private void mostrarRespuesta() {
        rbOpcion1.setTextColor(Color.RED);
        rbOpcion2.setTextColor(Color.RED);
        rbOpcion3.setTextColor(Color.RED);

        switch (preguntaActual.getRespuesta()){
            case 1:
                rbOpcion1.setTextColor(Color.GREEN);
                tvPregunta.setText("La opcion " + preguntaActual.getOpcion1() + " es correcta");
                break;
            case 2:
                rbOpcion2.setTextColor(Color.GREEN);
                tvPregunta.setText("La opcion " + preguntaActual.getOpcion2() + " es correcta");
                break;
            case 3:
                rbOpcion3.setTextColor(Color.GREEN);
                tvPregunta.setText("La opcion " + preguntaActual.getOpcion3() + " es correcta");
                break;
        }

        if (contadorPreguntas < contadorTotalPreguntas) {
            btnConfirmarNext.setText("Siguiente");
        } else {
            btnConfirmarNext.setText("Acabar");
        }
    }

    private void acabarPreguntas() {
        Intent resultadoIntent = new Intent();
        resultadoIntent.putExtra(EXTRA_PUNTUACION, score);
        setResult(RESULT_OK, resultadoIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (atrasTiempoPresionado + 2000 > System.currentTimeMillis()) {
            acabarPreguntas();
        } else {
            Toast.makeText(this, "Presiona denuevo para salir", Toast.LENGTH_SHORT).show();
        }
        atrasTiempoPresionado = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PUNTUACION, score);
        outState.putInt(KEY_CONTEO_PREGUNTA, contadorPreguntas);
        outState.putLong(KEY_MILIS_FALTANTES, tiempoRestanteMili);
        outState.putBoolean(KEY_RESPONDIDO, respuesta);
        outState.putParcelableArrayList(KEY_LISTA_PREGUNTAS, preguntaList);
    }
}
