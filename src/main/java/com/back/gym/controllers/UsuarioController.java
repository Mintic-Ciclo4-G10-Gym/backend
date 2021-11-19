package com.back.gym.controllers;

import java.util.*;

import javax.validation.Valid;

import com.back.gym.exceptions.CustomException;
import com.back.gym.models.UsuarioModel;
import com.back.gym.services.UsuarioService;
import com.back.gym.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService servicio;

    @PostMapping("/usuario")
    public ResponseEntity<Map <String, String>> guardar(@Valid @RequestBody UsuarioModel entidad, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        entidad.setClave(BCrypt.hashpw(entidad.getClave(), BCrypt.gensalt()));
        UsuarioModel u = this.servicio.buscarPorNombre(entidad.getNombre());
        if(u.getId() == null){
            this.servicio.guardar(entidad);
            respuesta.put("mensaje", "El documento se agrego correctamente");
        }else{
            respuesta.put("mensaje", "El documento ya existe");
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/usuario")
    public List<UsuarioModel> listar(){
        return this.servicio.listar();
    }

    @GetMapping("/usuario/{id}")
    public UsuarioModel buscar(@PathVariable String id){
        return this.servicio.buscar(id).get();
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable String id){
        Boolean existe = this.servicio.existe(id);
        Map<String, String> respuesta = new HashMap<>();
        if(existe){
            this.servicio.eliminar(id);
            respuesta.put("mensaje", "El documento ha sido eliminado");
            return ResponseEntity.ok(respuesta);
        }else{
            respuesta.put("mensaje", "No existe el documento");
            return ResponseEntity.ok(respuesta);
        }
    }

    @PutMapping("/usuario")
    public ResponseEntity<Map<String, String>> actualizar(@RequestBody UsuarioModel entidad, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }
        this.servicio.guardar(entidad);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "El documento ha sido actualizado");
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/usuario/ingreso")
    public ResponseEntity<UsuarioModel> ingreso(@RequestBody UsuarioModel entidad){
        UsuarioModel u = this.servicio.buscarPorNombre(entidad.getNombre());
        if(u.getNombre()==null){
            throw new CustomException("Nombre de usuario o clave incorrecta");
        }
        if(!BCrypt.checkpw(entidad.getClave(), u.getClave())){
            throw new CustomException("Nombre de usuario o clave incorrecta");
        }
        String hash = "";
        long tiempo = System.currentTimeMillis();
        if(u.getId() != ""){
            hash = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
            .setSubject(u.getNombre())
            .setIssuedAt(new Date(tiempo))
            .setExpiration(new Date(tiempo + 9000000))
            .claim("nombre", u.getNombre())
            .compact();
        }
        u.setHash(hash);
        return ResponseEntity.ok(u);
    }

    private void throwError(Errors error){
        String message = "";
        int index = 0;
        for(ObjectError e: error.getAllErrors()){
            if(index > 0){
                message += " | ";
            }
            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(), e.getDefaultMessage());
        }
        throw new CustomException(message);
    }
}