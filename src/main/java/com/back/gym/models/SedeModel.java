package com.back.gym.models;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "sede")
public class SedeModel{

    @Id
    private String id;
    @NotEmpty(message="El nombre del documento no puede estar vacio")
    private String nombre;
    private MunicipioModel municipio;
    private DepartamentoModel departamento;

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

    public MunicipioModel getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioModel municipio) {
        this.municipio = municipio;
    }

    public DepartamentoModel getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoModel departamento) {
        this.departamento = departamento;
    }
    
}