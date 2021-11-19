package com.back.gym.models;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "departamento")
public class DepartamentoModel{

    @Id
    private String id;
    @NotEmpty(message="El nombre del recurso no puede estar vacio")
    private String nombre;

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
}