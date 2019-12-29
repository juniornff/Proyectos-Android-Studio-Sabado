package com.example.listadetareas.Model;

public class ToDo {

    private Long Id;
    private Boolean Done;
    private Boolean Favorite;
    private String TaskName;
    private String Description;

    public ToDo() {

    }

    public ToDo(Long id, Boolean done, Boolean favorite, String taskName, String description) {
        Id = id;
        Done = done;
        Favorite = favorite;
        TaskName = taskName;
        Description = description;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Boolean getDone() {
        return Done;
    }

    public void setDone(Boolean done) {
        Done = done;
    }

    public Boolean getFavorite() { return Favorite; }

    public void setFavorite(Boolean favorite) { Favorite = favorite; }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

