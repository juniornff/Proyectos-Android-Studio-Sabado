package com.example.registrodeestudiantesmvp.model;

import android.content.Context;

import com.example.registrodeestudiantesmvp.EstudiantesMVP;
import com.example.registrodeestudiantesmvp.model.entities.Estudiante;
import com.example.registrodeestudiantesmvp.model.services.EstudianteServices;
import com.example.registrodeestudiantesmvp.presenter.EstudiantesPresenter;

import java.util.List;

public class EstudiantesModel implements EstudiantesMVP.model {

    private EstudiantesMVP.presenter presenter;
    private Context context;
    private EstudianteServices services;

    public EstudiantesModel(Context context, EstudiantesMVP.presenter presenter){
        this.context = context;
        this.presenter = presenter;
        services = new EstudianteServices(context);
    }

    @Override
    public void getAllEstudiantes() {
        List<Estudiante> estudianteList = services.getAllEstudianteList();
        presenter.showEstudiantes(estudianteList);
    }

    @Override
    public void getEstudiante(long id) {
        Estudiante estudiante = services.getEstudiante(id);
        presenter.showEstudiante(estudiante);
    }

    @Override
    public void addEstudiante(Estudiante estudiante) {
        services.addEstudiante(estudiante);
        presenter.notifyEstudianteAdded("Estudiante creado exitosamente");
    }

    @Override
    public void updateEstudiante(Estudiante estudiante) {
        services.updateEstudiante(estudiante);
        presenter.notifyEstudianteUpdated("Estudiante actualizado exitosamente");
    }

    @Override
    public void deleteEstudiante(long id) {
        if (services.deleteEstudiante(id) > 0){
            presenter.notifyEstudianteDeleted("Estudiante Eliminado");
        } else {
            presenter.notifyEstudianteDeleted("Estudiante no eliminado");
        }

    }
}
