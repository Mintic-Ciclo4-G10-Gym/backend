package com.back.gym.services;

import org.springframework.stereotype.Service;
import java.util.*;
import com.back.gym.repositories.*;
import com.back.gym.models.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class SedeService {

    @Autowired
    SedeRepository repositorio;

    public void guardar(SedeModel entidad){
        this.repositorio.save(entidad);
    }

    public List<SedeModel> listar(){
        return this.repositorio.findAll();
    }
    
    public Boolean existe(String id){
        return this.repositorio.existsById(id);
    }

    public Optional<SedeModel> buscar(String id){
        return this.repositorio.findById(id);
    }

    public void eliminar(String id){
        this.repositorio.deleteById(id);
    }
}