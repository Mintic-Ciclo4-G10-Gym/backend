package com.back.gym.services;

import java.util.*;

import com.back.gym.models.UsuarioModel;
import com.back.gym.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repositorio;

    public void guardar(UsuarioModel entidad){
        this.repositorio.save(entidad);
    }

    public List<UsuarioModel> listar(){
        return this.repositorio.findAll();
    }
    
    public Boolean existe(String id){
        return this.repositorio.existsById(id);
    }

    public Optional<UsuarioModel> buscar(String id){
        return this.repositorio.findById(id);
    }

    public UsuarioModel buscarPorNombre(String nombre){
        return this.repositorio.findByNombre(nombre).orElse(new UsuarioModel());
    }

    public void eliminar(String id){
        this.repositorio.deleteById(id);
    }
}