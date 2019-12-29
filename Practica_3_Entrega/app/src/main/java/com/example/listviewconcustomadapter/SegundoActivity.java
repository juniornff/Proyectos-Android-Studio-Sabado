package com.example.listviewconcustomadapter;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SegundoActivity extends AppCompatActivity {

    private TextView tvPersonal;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.segundo_activity);

        tvPersonal = findViewById(R.id.tvpersonal);
        tvPersonal.setTextSize(18);
        tvPersonal.setTextColor(Color.RED);

        String prueba = getIntent().getStringExtra("prueba");
        tvPersonal.setText("Veo que elegiste la opcion " + prueba + ", yo huebiera elegido la 10");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CicloVida","Entre al onStart Activity 2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CicloVida","Entre al onResume Activity 2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CicloVida","Entre al onPause Activity 2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CicloVida","Entre al onStop Activity 2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CicloVida","Entre al onDestroy Activity 2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CicloVida","Entre al onRestart Activity 2");
    }
}
