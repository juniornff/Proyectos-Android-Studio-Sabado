package com.example.reproductordesonido;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class SonidosAdapter extends BaseAdapter {

    private Context context;
    private List<Sonidos> sonidosList;
    public Sonidos sonidos;
    public AnimationDrawable animation;
    public MediaPlayer mediaPlayer;
    public ImageView imageView;
    public Button btnReproducir;

    public SonidosAdapter(Context context, List<Sonidos> sonidosList){
        this.context = context;
        this.sonidosList = sonidosList;
    }

    @Override
    public int getCount() {
        return sonidosList.size();
    }

    @Override
    public Object getItem(int position) {
        return sonidosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sonidosList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, null);

        imageView = view.findViewById(R.id.ivImage);
        btnReproducir = view.findViewById(R.id.btnReproducir);

        sonidos = sonidosList.get(position);

        imageView.setBackgroundResource(sonidos.getImages());
        Log.i("animacion", "Para cancion: " + sonidos.getName() + " numero: " + sonidos.getId());
        animation = (AnimationDrawable) imageView.getBackground();
        animation.start();

        btnReproducir.setText(sonidos.getName());
        Log.i("Boton", "Para cancion: " + sonidos.getName() + " numero: " + sonidos.getId());

        stratSound(sonidos.getName(),sonidos.getSound());

        return view;
    }

    public void stratSound (final String soundName ,final int soundId) {
        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(context, soundId);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.i("Reporductor", "Desetear cancion: " + soundName + " numero: " + soundId);
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                });
                mediaPlayer.start();
            }
        });
    }
}
