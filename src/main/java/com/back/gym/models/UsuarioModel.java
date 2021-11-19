package com.back.gym.models;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "usuario")
public class UsuarioModel{

    @Id
    private String id;
    @NotEmpty(message="El campo nombre del documento no puede estar vacio")
    private String nombre;
    @NotEmpty(message="El campo clave del documento no puede estar vacio")
    private String clave;
    private String hash;

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    

}