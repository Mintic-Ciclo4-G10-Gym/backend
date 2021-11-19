package com.back.gym.services;

import org.springframework.stereotype.Service;
import java.util.*;
import com.back.gym.repositories.*;
import com.back.gym.models.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository repositorio;

    public void guardar(RecursoModel entidad){
        this.repositorio.save(entidad);
    }

    public List<RecursoModel> listar(){
        return this.repositorio.findAll();
    }
    
    public Boolean existe(String id){
        return this.repositorio.existsById(id);
    }

    public Optional<RecursoModel> buscar(String id){
        return this.repositorio.findById(id);
    }

    public void eliminar(String id){
        this.repositorio.deleteById(id);
    }
}