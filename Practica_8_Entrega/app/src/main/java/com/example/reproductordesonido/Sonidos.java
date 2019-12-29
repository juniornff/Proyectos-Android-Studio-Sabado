package com.example.reproductordesonido;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Sonidos {

    private int id;
    private String name;
    private int images;
    private int sound;
    private Boolean running;
    private String streamingSound;

    public Sonidos(int id, String name, int images, int sound, Boolean running, String streamingSound) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.sound = sound;
        this.running = running;
        this.streamingSound = streamingSound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getStreamingSound() {
        return streamingSound;
    }

    public void setStreamingSound(String streamingSound) {
        this.streamingSound = streamingSound;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public static List<Sonidos> getAllSonidos(Context context) {
        List<Sonidos> sonidosList = new ArrayList<>();

        sonidosList.add(new Sonidos(0,"Bait the Dust", R.drawable.btd_animation, R.raw.btd,false, ""));
        sonidosList.add(new Sonidos(1,"Sheart Heart Attack", R.drawable.sha_animation, R.raw.sha,false, ""));
        sonidosList.add(new Sonidos(2,"Tengen Toppa Gurren Langgan", R.drawable.ttgl_animation, R.raw.ttgl,false, ""));
        sonidosList.add(new Sonidos(3,"Zelda: Navi", R.drawable.zelda_animation, R.raw.zelda,false, ""));

        return sonidosList;
    }
}
