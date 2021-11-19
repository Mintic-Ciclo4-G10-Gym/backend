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
public class DepartamentoController {

    @Autowired
    DepartamentoService servicio;

    @PostMapping("/departamento")
    public ResponseEntity<Map <String, String>> guardar(@Valid @RequestBody DepartamentoModel entidad, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }
        this.servicio.guardar(entidad);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El documento se agrego correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/departamento")
    public List<DepartamentoModel> listar(){
        return this.servicio.listar();
    }

    @GetMapping("/departamento/{id}")
    public DepartamentoModel buscar(@PathVariable String id){
        return this.servicio.buscar(id).get();
    }

    @DeleteMapping("/departamento/{id}")
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

    @PutMapping("/departamento")
    public ResponseEntity<Map<String, String>> actualizar(@RequestBody DepartamentoModel entidad, Errors error){
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