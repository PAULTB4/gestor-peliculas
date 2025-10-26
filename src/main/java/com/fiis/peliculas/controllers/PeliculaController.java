package com.fiis.peliculas.controllers;

import com.fiis.peliculas.entities.Actor;
import com.fiis.peliculas.services.GeneroService;
import com.fiis.peliculas.services.IActorService;
import com.fiis.peliculas.services.IArchivoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fiis.peliculas.entities.Pelicula;
import com.fiis.peliculas.services.PeliculaService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.io.IOException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PeliculaController {
    private PeliculaService service;
    private GeneroService generoService;
    private IActorService actorService;
    private IArchivoService archivoService;

    public PeliculaController(PeliculaService service, GeneroService generoService,  IActorService actorService, IArchivoService archivoService) {
        this.service = service;
        this.generoService = generoService;
        this.actorService = actorService;
        this.archivoService = archivoService;
    }

    @GetMapping("/pelicula")
    public String crear(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo", "Nueva Película");
        return "pelicula";
    }

    @GetMapping("/pelicula/{id}")
    public String editar(@PathVariable(name = "id") Long id, Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo", "Editar Película");
        return "pelicula";
    }

    @PostMapping("/pelicula")
    public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name = "ids") String ids,
                          Model model, @RequestParam("archivo") MultipartFile imagen) {
        if (br.hasErrors()) {
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("actores", actorService.findAll());
            return "pelicula";
        }
        if (!imagen.isEmpty()) {
            String archivo = pelicula.getNombre() + getExtension(imagen.getOriginalFilename());
            pelicula.setImagen(archivo);
            try {
                archivoService.guardar(archivo, imagen.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            pelicula.setImagen("_default.jpg");
        }

        List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong)
                .collect(Collectors.toList());
        List<Actor> protagonistas = actorService.findAllById(idsProtagonistas);
        pelicula.setProtagonistas(protagonistas);

        service.save(pelicula);
        return "redirect:/home";
    }

    private String getExtension(String archivo) {
        return archivo.substring(archivo.lastIndexOf("."));
    }


    @GetMapping({"/", "/home", "/index"})
    public String home(Model model) {
        model.addAttribute("peliculas",service.findAll());
        model.addAttribute("msj", "Catalogo actualizado a 2025");
        model.addAttribute("tipoMsj","success");
        return "home";
    }
}
