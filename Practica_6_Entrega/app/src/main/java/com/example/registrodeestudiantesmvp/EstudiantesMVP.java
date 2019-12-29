package com.example.registrodeestudiantesmvp;

import java.util.List;

import com.example.registrodeestudiantesmvp.model.entities.Estudiante;

public interface EstudiantesMVP {

    interface view {
        void showEstudiantes (List<Estudiante> estudianteList);
    }

    interface viewAdapter {
        void notifyEstudianteDeleted(String msg);
    }

    interface editarView {
        void showEstudiante (Estudiante estudiante);
        void notifyEstudianteAdded(String msg);
        void notifyEstudianteUpdated(String msg);
    }

    interface presenter {
        void showEstudiantes (List<Estudiante> estudianteList);
        void getAllEstudiantes();
        void showEstudiante (Estudiante estudiante);
        void getEstudiante(long id);
        void notifyEstudianteAdded(String msg);
        void notifyEstudianteDeleted(String msg);
        void notifyEstudianteUpdated(String msg);
        void addEstudiante(Estudiante estudiante);
        void updateEstudiante(Estudiante estudiante);
        void deleteEstudiante(long id);
    }

    interface model {
        void getAllEstudiantes ();
        void getEstudiante(long id);
        void addEstudiante(Estudiante estudiante);
        void updateEstudiante(Estudiante estudiante);
        void deleteEstudiante(long id);
    }
}
