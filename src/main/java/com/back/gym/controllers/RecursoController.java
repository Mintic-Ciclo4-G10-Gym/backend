package com.back.gym.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.back.gym.exceptions.CustomException;
import com.back.gym.models.*;
import com.back.gym.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecursoController {

    @Autowired
    RecursoService servicio;

    @PostMapping("/recurso")
    public ResponseEntity<Map <String, String>> guardar(@Valid @RequestBody RecursoModel entidad, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }
        this.servicio.guardar(entidad);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El recurso se agrego correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recurso")
    public List<RecursoModel> listar(){
        return this.servicio.listar();
    }

    @GetMapping("/recurso/{id}")
    public RecursoModel buscar(@PathVariable String id){
        return this.servicio.buscar(id).get();
    }

    @DeleteMapping("/recurso/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable String id){
        Boolean existe = this.servicio.existe(id);
        Map<String, String> respuesta = new HashMap<>();
        if(existe){
            servicio.eliminar(id);
            respuesta.put("mensaje", "El documento ha sido eliminado");
            return ResponseEntity.ok(respuesta);
        }else{
            respuesta.put("mensaje", "No existe el documento");
            return ResponseEntity.ok(respuesta);
        }
    }

    @PutMapping("/recurso")
    public ResponseEntity<Map<String, String>> actualizar(@RequestBody RecursoModel entidad, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }
        servicio.guardar(entidad);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "El documento ha sido actualizado");
        return ResponseEntity.ok(respuesta);
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