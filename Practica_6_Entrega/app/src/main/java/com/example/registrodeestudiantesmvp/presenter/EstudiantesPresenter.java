package com.example.registrodeestudiantesmvp.presenter;

import android.content.Context;
import java.util.List;

import com.example.registrodeestudiantesmvp.EstudiantesMVP;
import com.example.registrodeestudiantesmvp.model.entities.Estudiante;
import com.example.registrodeestudiantesmvp.model.EstudiantesModel;

public class EstudiantesPresenter implements EstudiantesMVP.presenter{

    private EstudiantesMVP.view view;
    private EstudiantesMVP.editarView editarView;
    private EstudiantesMVP.viewAdapter viewAdapter;
    private EstudiantesMVP.model model;

    public EstudiantesPresenter(EstudiantesMVP.view view, Context context) {
        this.view = view;
        model = new EstudiantesModel(context, this);
    }

    public EstudiantesPresenter(EstudiantesMVP.editarView editarView, Context context) {
        this.editarView = editarView;
        model = new EstudiantesModel(context, this);
    }

    public EstudiantesPresenter(EstudiantesMVP.viewAdapter viewAdapter, Context context) {
        this.viewAdapter = viewAdapter;
        model = new EstudiantesModel(context, this);
    }

    @Override
    public void showEstudiantes(List<Estudiante> estudianteList) {
        if (view != null) {
            view.showEstudiantes(estudianteList);
        }
    }

    @Override
    public void getAllEstudiantes() {
        model.getAllEstudiantes();
    }

    @Override
    public void showEstudiante(Estudiante estudiante) {

    }

    @Override
    public void getEstudiante(long id) {
        model.getEstudiante(id);

    }

    @Override
    public void notifyEstudianteAdded(String msg) {
        editarView.notifyEstudianteAdded(msg);
    }

    @Override
    public void notifyEstudianteDeleted(String msg) {
        viewAdapter.notifyEstudianteDeleted(msg);
    }

    @Override
    public void notifyEstudianteUpdated(String msg) {
        editarView.notifyEstudianteUpdated(msg);
    }

    @Override
    public void addEstudiante(Estudiante estudiante) {
        model.addEstudiante(estudiante);
    }

    @Override
    public void updateEstudiante(Estudiante estudiante) {
        model.updateEstudiante(estudiante);
    }

    @Override
    public void deleteEstudiante(long id) {
        model.deleteEstudiante(id);
    }
}
