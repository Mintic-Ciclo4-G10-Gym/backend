package com.back.gym.models;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "recurso")
public class RecursoModel{

    @Id
    private String id;
    @NotEmpty(message="El nombre del recurso no puede estar vacio")
    private String nombre;
    @NotEmpty(message="La identificacion del recurso no puede estar vacia")
    private String identificacion;
    @NotEmpty(message="La foto del recurso no puede estar vacia")
    private String foto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}