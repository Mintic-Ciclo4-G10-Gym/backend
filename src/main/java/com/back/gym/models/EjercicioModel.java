package com.back.gym.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

@Document(collection = "ejercicio")
public class EjercicioModel{

    @Id
    private String id;
    private UsuarioModel usuario;
    private RecursoModel recurso;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public RecursoModel getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoModel recurso) {
        this.recurso = recurso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}