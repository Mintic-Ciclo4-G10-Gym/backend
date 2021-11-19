package com.back.gym.repositories;

import java.util.Optional;

import com.back.gym.models.UsuarioModel;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel,String>{
    public Optional<UsuarioModel> findByNombre(String nombre);
}