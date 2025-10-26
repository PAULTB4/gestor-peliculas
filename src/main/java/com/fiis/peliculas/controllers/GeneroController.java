package com.fiis.peliculas.controllers;

import com.fiis.peliculas.entities.Genero;
import com.fiis.peliculas.services.IGeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GeneroController {
    private final IGeneroService service;

    public GeneroController(IGeneroService service) {
        this.service = service;
    }

    @PostMapping("/genero")
    public Long guardar(@RequestParam String nombre){
        Genero genero = new Genero();
        genero.setNombre(nombre);
        service.save(genero);
        return genero.getId();
    }

    @GetMapping("/genero/{id}")
    public ResponseEntity<String> buscarPorId(@PathVariable Long id){
        Genero genero = service.findById(id);   // puede ser null
        if (genero == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Género " + id + " no existe");
        }
        return ResponseEntity.ok(genero.getNombre());
    }
}
